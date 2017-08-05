package com.crowdfunder.controllers.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crowdfunder.CrowdfunderApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class AuthControllerTests {

	@Autowired
	private WebApplicationContext _wac;
	
	private static MockMvc _mockMvc;
	
	@Before
	public void setUp() throws Exception {
		_mockMvc = MockMvcBuilders.webAppContextSetup(_wac).build();
	}
	
	@Test
	public void regViewTest() throws Exception {
		final String route = "/registration";
		_mockMvc.perform(get(route))
		.andExpect(status().isOk())
		.andExpect(view().name("layout"))
		.andExpect(content().string(containsString("Registration")))
		.andExpect(content().string(containsString("Login")))
		.andExpect(content().string(containsString("Submit")))
		.andExpect(content().string(containsString("Username:")))
		.andExpect(content().string(containsString("Email:")))
		.andExpect(content().string(containsString("Password:")));
	}
	
	@Test
	public void loginViewTest() throws Exception {
		final String route = "/login";
		_mockMvc.perform(get(route))
		.andExpect(status().isOk())
		.andExpect(view().name("layout"))
		.andExpect(content().string(containsString("Registration")))
		.andExpect(content().string(containsString("Login")))
		.andExpect(content().string(containsString("Submit")))
		.andExpect(content().string(containsString("Username:")))
		.andExpect(content().string(containsString("Password:")));
	}
}
