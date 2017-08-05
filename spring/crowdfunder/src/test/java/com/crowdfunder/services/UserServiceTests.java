package com.crowdfunder.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.models.User;
import com.crowdfunder.services.interfaces.IUserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private IUserService _userServ;

	
	@Test
	public void findByUsernameTest() {
		User user = _userServ.findByUsername("kyle");
		assertTrue(user.getUserId() == (long) 1);
		assertEquals(user.getEmail(), "kyle.williamson@mycit.ie");
	}
}
