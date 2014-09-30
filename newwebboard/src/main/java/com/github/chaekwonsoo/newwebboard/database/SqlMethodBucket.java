package com.github.chaekwonsoo.newwebboard.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.tomcat.jdbc.pool.DataSource;
import com.github.chaekwonsoo.newwebboard.BoardData;

// TODO: singleton pattern here too? (y / n) 일단 yes
// TODO: singleton pattern 올바로 적용
public class SqlMethodBucket {
	
	private DataSource dataSource = null;
	private static SqlMethodBucket sqlMethodBucket = null;
	
	public static SqlMethodBucket getInstance(DataSource dataSource) {
		if(sqlMethodBucket == null) {
			sqlMethodBucket = new SqlMethodBucket(dataSource);
		}
		return sqlMethodBucket;
	}
	
	private SqlMethodBucket(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void executeUpdate(String sql) throws SQLException {
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
	
	public int executeQueryNum(String sql) throws SQLException {
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
	
	public String executeQueryString(String sql) throws SQLException {
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
	
	public Vector<BoardData> executeQuery(String sql) throws SQLException {
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
}
