package com.github.chaekwonsoo.newwebboard.database;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.chaekwonsoo.newwebboard.UserManager;

public class TestUserManager {

	private UserManager userManager;
	
	public TestUserManager() {}

	@Before
	public void setUp() throws Exception {
		userManager = UserManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testInstantiationandCheckUser() throws SQLException {
		assertTrue(userManager.checkUser("pave88cks@naver.com", "ckscjc62"));
		assertTrue(userManager.checkUser("thththink@gmail.com", "pave88cks"));
		
		// When the password is wrong.
		assertFalse(userManager.checkUser("pave88cks@naver.com", "123456"));
		// When the user does not exist.
		assertFalse(userManager.checkUser("imnotuser@kpop.com", "11111"));
	}
	
	@Test
	public void testIsUser() throws SQLException {
		assertTrue(userManager.isUser("pave88cks@naver.com"));
		assertTrue(userManager.isUser("thththink@gmail.com"));
		
		assertFalse(userManager.isUser("imnotuser@rock.com"));
	}
}
