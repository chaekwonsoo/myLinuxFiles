package com.github.chaekwonsoo.newwebboard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.github.chaekwonsoo.newwebboard.database.SqlMethodBucket;

/**
 * @author chaekwonsoo
 * 
 * This class is for administrator that works on all-boards global level.
 * It does not deal with a board directly.
 * Each board is managed by another component, BoardManager.
 * 
 * TODO: apply the Single-ton pattern well.
 */
public class AdminManager {
	//private static ConnectionPool connectionPool = null;
	private static DataSource dataSource = null;
	protected static AdminManager adminManager = null;
	private static SqlMethodBucket sqlMethodBucket = null;
	
	// 보통 이 관리자 정보도 회원관리 DB에 들어가게 된다. 
	// 지금은 회원관리 데이터베이스가 없으므로 그냥 이렇게 한다.
	private static String id = "root";
	private static String password = "ckscjc62";
	
	static { 
		try {
			// for DB connection
			PoolProperties poolProperties = new PoolProperties();
			poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
			poolProperties.setUrl("jdbc:mysql://localhost:3306/novel");
			poolProperties.setUsername(id);
			poolProperties.setPassword(password);
			poolProperties.setInitialSize(10);
			poolProperties.setMaxActive(10);
			poolProperties.setMaxIdle(5);
			poolProperties.setMinIdle(2);
			dataSource = new DataSource(poolProperties);
			
			// one and the only one AdminManager (singleton)
			adminManager = AdminManager.getInstance();
			// one and the only one SqlMethodBucket (singleton)
			sqlMethodBucket = SqlMethodBucket.getInstance(dataSource);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected AdminManager() {}
	
	/**
	 * @return one and only one AdminManager instance
	 * 
	 * This is the only way to create the one and only one AdminManager instance.
	 */
	public static AdminManager getInstance() {
		if(adminManager == null) {
			adminManager = new AdminManager();
		}
		return adminManager;
	}
	
	
	/**
	 * @return DataSource reference
	 * 
	 * This is for test.
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static SqlMethodBucket getSqlMethodBucket() {
		return sqlMethodBucket;
	}
	/**
	 * @param id
	 * @param password
	 * @return a boolean value
	 * 
	 * Check if he/she is an administrator.
	 */
	public boolean checkAdmin(String id, String password) {
		if(AdminManager.id.equals(id) && AdminManager.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param id
	 * @return
	 * 
	 * Check if the id is administrator's.
	 */
	public boolean isAdmin(String id) {
		if(AdminManager.id.equals(id))
			return true;
		return false;
	}
	
	/**
	 * @param boardName
	 * @return a boolean value
	 * @throws SQLException 
	 * 
	 * Check if the board with the same name already exists.
	 */
	public boolean existBoard(String boardName) throws SQLException {
		String sql = "SELECT count(boardName) FROM BoardAdmin WHERE boardName='" + boardName + "'";
		int num = sqlMethodBucket.executeQueryNum(sql);
		if(num == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param boardName
	 * @return
	 * @throws SQLException
	 * 
	 * Get 'subject' of the board specified with the argument.
	 * This is only for JUnit test, and should be used only after existBoard(boardName) mothod returns true.
	 */
	public String getBoardSubject(String boardName) throws SQLException {
		String sql = "SELECT boardSubject FROM BoardAdmin WHERE boardName='" + boardName + "'";
		return sqlMethodBucket.executeQueryString(sql);
	}
	
	/**
	 * @param boardName
	 * @param boardSubject
	 * @throws SQLException 
	 * 
	 * Make a new board and update DB accordingly.
	 */
	public void makeBoard(String boardName, String boardSubject) throws SQLException {
		// Register to the global board DB table.
		String registerToGlobalSql = "INSERT INTO BoardAdmin VALUES ('" + boardName + "', '" + boardSubject + "')";
		
		// Create an actual new board DB table.
		String makeNewBoardSql = "CREATE TABLE " + boardName + "(";
		makeNewBoardSql += "num INTEGER  NOT NULL PRIMARY KEY,";
		makeNewBoardSql += "name VARCHAR(20) NOT NULL,";
		makeNewBoardSql += "subject VARCHAR(100) NOT NULL,";
		makeNewBoardSql += "content TEXT NOT NULL,";
		makeNewBoardSql += "writeDate datetime, ";
		makeNewBoardSql += "password VARCHAR(20) NOT NULL,";
		makeNewBoardSql += "count INTEGER NOT NULL,";
		makeNewBoardSql += "ref INTEGER NOT NULL,";
		makeNewBoardSql += "step INTEGER NOT NULL,";
		makeNewBoardSql += "depth INTEGER NOT NULL,";
		makeNewBoardSql += "childCount INTEGER NOT NULL";
		makeNewBoardSql += ")";
		
		sqlMethodBucket.executeUpdate(registerToGlobalSql);
		sqlMethodBucket.executeUpdate(makeNewBoardSql);
	}
	
	// Update a board's subject in the [adminBoard] table.
	/**
	 * @param boardName
	 * @param boardSubject
	 * @throws SQLException 
	 * 
	 * Update a board's subject and update the global board table accordingly.
	 */
	public void updateBoardSubject(String boardName, String boardSubject) throws SQLException {
		String sql = "UPDATE BoardAdmin SET boardSubject='" + boardSubject + "' WHERE boardName='" + boardName + "'";
		sqlMethodBucket.executeUpdate(sql);
	}
	
	/**
	 * @param boardName
	 * 
	 * Remove the board which name is boardName.
	 * @throws SQLException 
	 */
	public void deleteBoard(String boardName) throws SQLException {
		// Delete the table information from the global board DB table.
		String deleteFromAdminSql = "DELETE FROM BoardAdmin WHERE boardName='" + boardName + "'";
		// Drop the actual board to delete.
		String dropBoardSql = "DROP TABLE " + boardName;
		
		sqlMethodBucket.executeUpdate(deleteFromAdminSql);
		sqlMethodBucket.executeUpdate(dropBoardSql);
	}
	
	/**
	 * @return the boardName and boardSubject pairs as a hashtable
	 * @throws SQLException 
	 * 
	 * Return all the existing <boardName, boardSubject> pairs.
	 */
	public Hashtable<String, String> getBoardList() throws SQLException {
		Hashtable<String, String> table = new Hashtable<String, String>();
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM BoardAdmin");
			while(resultSet.next()) {
				String boardName = resultSet.getString(1);
				String boardSubject = resultSet.getString(2);
				table.put(boardName, boardSubject);
			}
			
			resultSet.close();
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return table;
	}
	
	/*
	 * methods for SQL
	 */
	/*
	public void adminExecuteUpdate(String sql) throws SQLException {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public int adminExecuteQueryNum(String sql) throws SQLException {
		int num = 0;
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				num = resultSet.getInt(1);
			}
			resultSet.close();
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return num;
	}
	
	public String adminExecuteQueryString(String sql) throws SQLException {
		String str = null;
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				str = resultSet.getString(1);
			}
			resultSet.close();
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return str;
	}
	
	public Vector<BoardData> adminExecuteQuery(String sql) throws SQLException {
		Vector<BoardData> vector = new Vector<BoardData>();
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				BoardData boardData = new BoardData();
				boardData.setNum(resultSet.getInt(1));
				boardData.setName(resultSet.getString(2));
				boardData.setSubject(resultSet.getString(3));
				boardData.setContent(resultSet.getString(4));
				boardData.setDate(resultSet.getDate(5));
				boardData.setPassword(resultSet.getString(6));
				boardData.setCount(resultSet.getInt(7));
				boardData.setRef(resultSet.getInt(8));
				boardData.setStep(resultSet.getInt(9));
				boardData.setDepth(resultSet.getInt(10));
				boardData.setChildCount(resultSet.getInt(11));
				
				vector.addElement(boardData);
			}
			resultSet.close();
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return vector;
	}
	*/
}
