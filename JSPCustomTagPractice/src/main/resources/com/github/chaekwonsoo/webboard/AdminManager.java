package com.github.chaekwonsoo.webboard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/*
 * This class is the only manager of the whole web site.
 */

// TODO: apply the Single-ton pattern.

public class AdminManager {
	//private static ConnectionPool connectionPool = null;
	private static DataSource dataSource = null;
	private static AdminManager adminManager = null;
	
	// 보통 이 관리자 정보도 회원관리 데이터베이스에 들어가게 된다. 
	// 지금은 회원관리 데이터베이스가 없으므로 그냥 이렇게 한다.
	private static String id = "root";
	private static String password = "ckscjc62";
	
	static { 
		try {
			PoolProperties poolProperties = new PoolProperties();
			poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
			poolProperties.setUrl("jdbc:mysql://localhost:3306/novel");
			poolProperties.setUsername(id);
			poolProperties.setPassword(password);
			poolProperties.setInitialSize(10);
			poolProperties.setMaxActive(10);
			poolProperties.setMaxIdle(5);
			poolProperties.setMinIdle(2);
			DataSource dataSource = new DataSource(poolProperties);
			
			adminManager = adminManager.getInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private AdminManager() {}
	
	public static AdminManager getInstance() {
		if(adminManager == null) {
			adminManager = new AdminManager();
		}
		return adminManager;
	}
	
	// Check if he/she is an administrator.
	public boolean checkAdmin(String id, String password) {
		if(this.id.equals(id) && this.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	// Check if the board with the same name already exists. 
	public boolean existBoard(String boardName) {
		String sql = "SELECT count(boardName) FROM boardAdmin WHERE boardName='" + boardName + "'";
		int num = this.adminExecuteQueryNum(sql);
		if(num == 0) {
			return false;
		}
		return true;
	}
	
	// Make a new board.
	public void makeBoard(String boardName, String boardSubject) {
		// Register to the global board DB table.
		String registerToGlobalSql = "INSERT INTO boardAdmin VALUES(" + boardName + ", " + boardSubject + ")";
		
		// Create an actual new board DB table.
		String makeNewBoardSql = "CREATE TABLE " + boardName + "(";
		makeNewBoardSql += "num INTEGER  NOT NULL PRIMARY KEY,";
		makeNewBoardSql += "name VARCHAR(20) NOT NULL,";
		makeNewBoardSql += "subject VARCHAR(100) NOT NULL,";
		makeNewBoardSql += "content TEXT NOT NULL,";
		makeNewBoardSql += "writeDate datetime,";
		makeNewBoardSql += "password VARCHAR(20) NOT NULL,";
		makeNewBoardSql += "count INTEGER NOT NULL,";
		makeNewBoardSql += "ref INTEGER NOT NULL,";
		makeNewBoardSql += "step INTEGER NOT NULL,";
		makeNewBoardSql += "depth INTERGER NOT NULL,";
		makeNewBoardSql += "childCount INTEGER NOT NULL,";
		makeNewBoardSql += ")";
		
		this.adminExecuteUpdate(registerToGlobalSql);
		this.adminExecuteUpdate(makeNewBoardSql);
	}
	
	// Update a board's subject in the [adminBoard] table.
	public void updateBoardSubject(String boardName, String boardSubject) {
		String sql = "UPDATE boardAdmin SET boardSubject='" + boardSubject + "' WHERE boardName='" + boardName + "'";
		this.adminExecuteUpdate(sql);
	}
	
	public void deleteBoard(String boardName) {
		// Delete the table information from the global board DB table.
		String deleteFromAdminSql = "DELETE FROM BoardAdmin WHERE boardName='" + boardName + "'";
		// Drop the actual board to delete.
		String dropBoardSql = "DROP TABLE " + boardName;
		
		this.adminExecuteUpdate(deleteFromAdminSql);
		this.adminExecuteUpdate(dropBoardSql);
	}
	
	// Return the current <boardName, boardSubject> pairs in the hashtable.
	public Hashtable<String, String> getBoardList() throws Exception {
		Hashtable<String, String> table = new Hashtable<String, String>();
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM boardAdmin");
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
			//connectionPool.releaseConnectoin(connection);
			// TODO: Release the connection!
		}
		return table;
	}
	
	/*
	 * methods for SQL
	 */
	public void adminExecuteUpdate(String sql) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//connectionPool.releaseConnection(connection);
			// TODO: Release the connection!
		}
	}
	
	public int adminExecuteQueryNum(String sql) {
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
			//connectionPool.releaseConnection(connection);
			// TODO: Release the connection!
		}
		return num;
	}
	
	public String adminExecutreQueryString(String sql) {
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
			//connectionPool.releaseConnection(connection);
			// TODO: Release the connection!
		}
		return str;
	}
	
	public Vector<BoardData> adminExecuteQuery(String sql) {
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
			//connectionPool.releaseConnection(connection);
			// TODO: Release the connection!
		}
		
		return vector;
	}
}
