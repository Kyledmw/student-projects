package com.crowdfunder.util.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
import com.crowdfunder.util.helpers.interfaces.IProjectHelper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class ProjectHelperTests {
	
	private static final int MSECONDS_IN_DAY = 86400000;
	private static final BigDecimal PLEDGE = new BigDecimal(50000);

	@Autowired
	private IProjectHelper _projHelper;
	
	@Test
	public void projectFinishedTest() {
		Project proj = new Project();
		proj.setFinishTime(new Timestamp(System.currentTimeMillis() - MSECONDS_IN_DAY));
		proj.setCancelled(false);
		proj.setCompleted(false);
		
		assertTrue(_projHelper.isProjectFinished(proj));
	}
	
	@Test
	public void projectNotFinishedTest() {
		Project proj = new Project();
		proj.setFinishTime(new Timestamp(System.currentTimeMillis() + MSECONDS_IN_DAY));
		proj.setCancelled(false);
		proj.setCompleted(false);
		
		assertFalse(_projHelper.isProjectFinished(proj));
	}
	
	@Test
	public void updateProjectStatusTest() {
		List<Project> projects = new ArrayList<>();
		
		Project goalUnderTime = new Project();
		goalUnderTime.setFinishTime(new Timestamp(System.currentTimeMillis() + MSECONDS_IN_DAY));
		goalUnderTime.setGoalAmount(PLEDGE);
		goalUnderTime.setPledges(generatePledgeList());
		projects.add(goalUnderTime);
		
		Project goalOverTime = new Project();
		goalOverTime.setFinishTime(new Timestamp(System.currentTimeMillis() - MSECONDS_IN_DAY));
		goalOverTime.setGoalAmount(PLEDGE);
		goalOverTime.setPledges(generatePledgeList());
		projects.add(goalOverTime);
		
		Project noGoalUnderTime = new Project();
		noGoalUnderTime.setFinishTime(new Timestamp(System.currentTimeMillis() + MSECONDS_IN_DAY));
		noGoalUnderTime.setGoalAmount(PLEDGE.add(new BigDecimal(1000)));
		noGoalUnderTime.setPledges(generatePledgeList());
		projects.add(noGoalUnderTime);
		
		Project noGoalOverTime = new Project();
		noGoalOverTime.setFinishTime(new Timestamp(System.currentTimeMillis() - MSECONDS_IN_DAY));
		noGoalOverTime.setGoalAmount(PLEDGE.add(new BigDecimal(1000)));
		noGoalOverTime.setPledges(generatePledgeList());
		projects.add(noGoalOverTime);
		
		List<Pledge> pledgesToUpdate = _projHelper.updateProjectsStatus(projects);
		
		assertFalse(goalUnderTime.getCompleted());
		assertFalse(goalUnderTime.getCancelled());
		
		assertTrue(goalOverTime.getCompleted());
		assertFalse(goalOverTime.getCancelled());
		
		assertFalse(noGoalUnderTime.getCancelled());
		assertFalse(noGoalUnderTime.getCompleted());
		
		assertTrue(noGoalOverTime.getCancelled());
		assertFalse(noGoalOverTime.getCompleted());
		
		assertEquals(pledgesToUpdate.size(), 2);
	}
	
	private static List<Pledge> generatePledgeList() {
		List<Pledge> pledges = new ArrayList<>();
		
		Pledge activePledge = new Pledge();
		activePledge.setActive(true);
		activePledge.setAmount(PLEDGE);
		
		Pledge inactivePledge = new Pledge();
		inactivePledge.setActive(false);
		inactivePledge.setAmount(PLEDGE);
		
		pledges.add(activePledge);
		pledges.add(inactivePledge);
		return pledges;
	}
}
