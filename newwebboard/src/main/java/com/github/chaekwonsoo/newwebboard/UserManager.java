package com.github.chaekwonsoo.newwebboard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.github.chaekwonsoo.newwebboard.database.SqlMethodBucket;

/**
 * @author chaekwonsoo
 *
 * This class deals with users.
 * TODO:
 * TODO: singleton pattern
 */
public class UserManager {
	private static UserManager userManager = null;
	private static SqlMethodBucket sqlMethodBucket;
	private DataSource dataSource = AdminManager.getDataSource();
	
	static {
		userManager = UserManager.getInstance();
	}
	
	private UserManager() {
		sqlMethodBucket = AdminManager.getSqlMethodBucket();
	}
	
	/**
	 * @return one and only one UserManager instance
	 * 
	 * This is the only way to create the one and only one UserManager instance.
	 */
	public static UserManager getInstance() {
		if(userManager == null) {
			userManager = new UserManager();
		}
		return userManager;
	}
	
	/**
	 * @param email e-mail address as id
	 * @param password
	 * @return If the id and password match, return true. Otherwise false.
	 * 
	 * Check if he/she is an administrator.
	 */
	public boolean checkUser(String email, String password) throws SQLException {
		// TODO
		String sql = "SELECT password FROM User WHERE email='" + email + "'";
		String dbPassword = sqlMethodBucket.executeQueryString(sql);
		if(password != null && password.equals(dbPassword)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param email e-mail address as id
	 * @return If the e-mail exists in the User table, return true. Otherwise false.
	 * 
	 * Check if the id is in User DB.
	 */
	public boolean isUser(String email) throws SQLException {
		// TODO
		String sql = "SELECT COUNT(*) FROM User WHERE email='" + email + "'";
		int count = sqlMethodBucket.executeQueryNum(sql);
		if(count == 0) {
			return false;
		}
		return true;
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
}
