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
public class ProjectControllerTests {
	
	private static final String BASE_ROUTE = "/projects";

	@Autowired
	private WebApplicationContext _wac;
	
	private static MockMvc _mockMvc;
	
	@Before
	public void setUp() throws Exception {
		_mockMvc = MockMvcBuilders.webAppContextSetup(_wac).build();
	}
	
	@Test
	public void projectView() throws Exception {
		final int projectId = 1;
		final String projectRoute = BASE_ROUTE + "/" + projectId;
		
		_mockMvc.perform(get(projectRoute))
		.andExpect(status().isOk())
		.andExpect(view().name("layout"))
		.andExpect(content().string(containsString("Appliance Maintence Scheduling")))
		.andExpect(content().string(containsString("kyle")))
		.andExpect(content().string(containsString("Description")));
	}

	@Test
	public void pageableProjectsView() throws Exception {
		final String route = BASE_ROUTE;
		
		_mockMvc.perform(get(route))
		.andExpect(status().isOk())
		.andExpect(view().name("layout"))
		.andExpect(content().string(containsString("View project page")))
		.andExpect(content().string(containsString("Appliance Maintence Scheduling")))
		.andExpect(content().string(containsString("Receipts and stuff")))
		.andExpect(content().string(containsString("Sensors and all that")))
		.andExpect(content().string(containsString("Sort")));
	}
}
