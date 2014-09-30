package com.github.chaekwonsoo.newwebboard;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.chaekwonsoo.newwebboard.database.SqlMethodBucket;

public class TestAdminManager {

	private AdminManager adminManager;
	private SqlMethodBucket sqlMethodBucket;
	
	public TestAdminManager() {}

	@Before
	public void setUp() throws Exception {
		adminManager = AdminManager.getInstance();
		sqlMethodBucket = SqlMethodBucket.getInstance(AdminManager.getDataSource());
	}
	@After
	public void tearDown() throws Exception {}
	
	@Test
	public void testInstantiationandCheckAdmin() {
		assertTrue(adminManager.checkAdmin("root", "ckscjc62"));
	}
	/*
	@Test
	public void testExistsBoardName() throws SQLException {
		assertTrue(adminManager.existBoard("hi"));
	}
	*/
	@Test
	public void testAdminExecuteUpdate() throws SQLException {
		String insertSql = "INSERT INTO BoardAdmin VALUES ('nora name', 'nora subject')";
		sqlMethodBucket.executeUpdate(insertSql);
		assertTrue(adminManager.existBoard("nora name"));
		
		
		String deleteSql = "DELETE FROM BoardAdmin WHERE boardName='nora name'";
		sqlMethodBucket.executeUpdate(deleteSql);
		assertFalse(adminManager.existBoard("nora name"));
		
	}
	@Test
	public void testMakeAndDeleteBoard() throws SQLException {
		String boardName = "MakeBoardTest";
		String boardSubject = "This is the adminManager.makeBoard() test!";
		
		// Make a board.
		adminManager.makeBoard(boardName, boardSubject);
		assertTrue(adminManager.existBoard("MakeBoardTest"));	// Does it exist?
		assertTrue(adminManager.getBoardSubject(boardName).equals(boardSubject));	// Is the subject correct?
		
		// Delete the board.
		adminManager.deleteBoard("MakeBoardTest");
		assertFalse(adminManager.existBoard(boardName));
	}
	@Test
	public void testUpdateBoardSubject() throws SQLException {
		String boardName = "updateSubTest";
		String originalBoardSubject = "original board subject!";
		String newBoardSubject = "new board subject!";
		
		adminManager.makeBoard(boardName, originalBoardSubject);
		assertTrue(adminManager.getBoardSubject(boardName).equals(originalBoardSubject));
		
		adminManager.updateBoardSubject(boardName, newBoardSubject);
		assertTrue(adminManager.getBoardSubject(boardName).equals(newBoardSubject));
		
		adminManager.deleteBoard(boardName);
	}
	
	/*
	@Test
	public void testGetBoardList() throws SQLException {
		Hashtable<String, String> table = adminManager.getBoardList();
		Enumeration<String> enumeration = table.keys();
		
		if(enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = table.get(key);
			assertTrue(key.equals("updateSubTest") && value.equals("new board subject!"));
		}
		if(enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = table.get(key);
			assertTrue(key.equals("test") && value.equals("This is a board for test."));
		}
		if(enumeration.hasMoreElements())
			assertFalse(false);
	}
	*/
}
