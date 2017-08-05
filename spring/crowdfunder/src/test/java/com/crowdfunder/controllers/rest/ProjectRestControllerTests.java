package com.crowdfunder.controllers.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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
public class ProjectRestControllerTests {
	
	private static final String REQUEST_MAPPING = "/api/projects";

	@Autowired
	private WebApplicationContext _wac;
	
	private static MockMvc _mockMvc;

	@Configuration
	@EnableAutoConfiguration
	public static class Config {
		@Bean
		public ProjectRestController apiController() {
			return new ProjectRestController();
		}
	}

	@Before
	public void setUp() throws Exception {
		_mockMvc = MockMvcBuilders.webAppContextSetup(_wac).build();
	}
	
	@Test
	public void getAll() throws Exception {
		final String route = "/";
		final String FULL_ROUTE = REQUEST_MAPPING + route;
		
		_mockMvc.perform(get(FULL_ROUTE).accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].projectId", is(1)))
		.andExpect(jsonPath("$[1].projectId", is(2)))
		.andExpect(jsonPath("$[2].projectId", is(3)));
	}
	
	@Test
	public void getProject() throws Exception {
		final int id = 1;
		final String name = "Appliance Maintence Scheduling";
		final String desc = "Description";
		final String username = "kyle";
		final double goal = 10000.0;
		final String route = "/";
		final String FULL_ROUTE = REQUEST_MAPPING + route + id;
		
		_mockMvc.perform(get(FULL_ROUTE).accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.projectId", is(id)))
		.andExpect(jsonPath("$.name", is(name)))
		.andExpect(jsonPath("$.description", is(desc)))
		.andExpect(jsonPath("$.user.userName", is(username)))
		.andExpect(jsonPath("$.goalAmount", is(goal)))
		;
	}
	
	@Test
	public void getProjectsPageable() throws Exception {
		final String route = "/page";
		final String FULL_ROUTE = REQUEST_MAPPING + route;
		
		_mockMvc.perform(get(FULL_ROUTE).accept(MediaType.APPLICATION_JSON)).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.content", hasSize(3)))
		.andExpect(jsonPath("$.content[0].projectId", is(1)))
		.andExpect(jsonPath("$.last", is(true)))
		.andExpect(jsonPath("$.first", is(true)))
		.andExpect(jsonPath("$.totalPages", is(1)))
		.andExpect(jsonPath("$.totalElements", is(3)))
		;
	}
}
