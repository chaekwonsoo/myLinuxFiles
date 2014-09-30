package com.github.chaekwonsoo.webboard;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAdminManager {

	private AdminManager adminManager = null;
	
	public TestAdminManager() {}

	@Before
	public void setUp() throws Exception {
		adminManager = AdminManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testInstantiationandCheckAdmin() {
		assertTrue(adminManager.checkAdmin("root", "ckscjc62"));
	}
	@Test
	public void testExistsBoardName() throws SQLException {
		assertTrue(adminManager.existBoard("hi"));
	}
	@Test
	public void testAdminExecuteUpdate() throws SQLException {
		String insertSql = "INSERT INTO BoardAdmin VALUES ('nora name', 'nora subject')";
		adminManager.adminExecuteUpdate(insertSql);
		assertTrue(adminManager.existBoard("nora name"));
		
		
		String deleteSql = "DELETE FROM BoardAdmin WHERE boardName='nora name'";
		adminManager.adminExecuteUpdate(deleteSql);
		assertFalse(adminManager.existBoard("nora name"));
		
	}
	
}
