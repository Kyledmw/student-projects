package com.crowdfunder.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;
import com.crowdfunder.services.interfaces.IPledgeService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class PledgeServiceTests {
	
	@Autowired
	private IPledgeService _mockPledgeServ;

	@Test
	public void cancelPledgeTest() {
		Project mockProj = mock(Project.class);
		when(mockProj.getCompleted()).thenReturn(false);
		
		Pledge pledge = new Pledge();
		pledge.setActive(true);
		pledge.setProject(mockProj);
		
		Project mockProjCompl = mock(Project.class);
		when(mockProjCompl.getCompleted()).thenReturn(true);
		
		Pledge pledge2 = new Pledge();
		pledge2.setActive(true);
		pledge2.setProject(mockProjCompl);
		
		assertTrue(_mockPledgeServ.cancelPledge(pledge));
		assertFalse(_mockPledgeServ.cancelPledge(pledge2));
		assertFalse(pledge.getActive());
		assertTrue(pledge2.getActive());
	}
}
