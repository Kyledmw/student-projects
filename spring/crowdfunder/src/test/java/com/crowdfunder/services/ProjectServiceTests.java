package com.crowdfunder.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.models.Project;
import com.crowdfunder.services.interfaces.IProjectService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class ProjectServiceTests {
	
	@Mock
	private IProjectService _mockProjServ;
	
	@Autowired
	private IProjectService _projServ;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllProjectsTest() {
		final int projectListSize = 3;
		List<Project> projects = getMockList(projectListSize);
		
		when(_mockProjServ.getAllProjects()).thenReturn(projects);
		assertEquals(projectListSize, _mockProjServ.getAllProjects().size());
	}

	@Test
	public void getProjectTest() {
		Project proj = getMockList(1).get(0);
		
		when(_mockProjServ.getProject((long) 0)).thenReturn(proj);
		
		Project rProj = _mockProjServ.getProject((long) 0);
		
		assertEquals(rProj.getCancelled(), false);
		assertEquals(rProj.getCompleted(), false);
		assertEquals(rProj.getDescription(), "desc0");
		assertEquals(rProj.getFinishTime().getTime(), new Timestamp(1).getTime());
		assertTrue(rProj.getGoalAmount().compareTo(new BigDecimal(2000)) == 0);
		assertEquals(rProj.getName(), "name0");
		assertTrue(rProj.getProjectId() == 0);
	}

	@Test
	public void getDaysLeftTest() {
		final int expectedDays = 7;
		Project projComp = new Project();
		projComp.setCompleted(true);
		
		Project projCanc = new Project();
		projCanc.setCancelled(true);
		
		Project proj = new Project();
		proj.setFinishTime(new Timestamp(System.currentTimeMillis() + (86400000 * expectedDays)));
		
		assertTrue(_projServ.getDaysLeft(projComp) == 0);
		assertTrue(_projServ.getDaysLeft(projCanc) == 0);
		assertTrue(_projServ.getDaysLeft(proj) == expectedDays -1);
	}
	
	@Test
	public void cancel() {
		Project projComp = new Project();
		projComp.setCompleted(true);
		
		_projServ.cancelProject(projComp);
		assertFalse(projComp.getCancelled());
	}
	
	private List<Project> getMockList(int listSize) {
		List<Project> projects = new ArrayList<Project>();
		for(int i=0; i < listSize; i++) {
			Project proj = new Project();
			proj.setCancelled(false);
			proj.setCompleted(false);
			proj.setDescription("desc" + i);
			proj.setFinishTime(new Timestamp(1));
			proj.setGoalAmount(new BigDecimal(2000));
			proj.setName("name" + i);
			proj.setProjectId((long) i);
			projects.add(proj);
		}
		return projects;
	}
	
}
