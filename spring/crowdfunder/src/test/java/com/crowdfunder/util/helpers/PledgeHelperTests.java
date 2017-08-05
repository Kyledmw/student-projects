package com.crowdfunder.util.helpers;

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
import com.crowdfunder.models.User;
import com.crowdfunder.util.helpers.interfaces.IPledgeHelper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class PledgeHelperTests {
	
	@Autowired
	private IPledgeHelper _pledgeHelper;
	
	@Test
	public void projectPledgeableTest() {
		Project projFinished = new Project();
		projFinished.setFinishTime(new Timestamp(System.currentTimeMillis() - 86400000));
		
		Project projCancelled = new Project();
		projCancelled.setCancelled(true);
		
		Project validProj = new Project();
		validProj.setCancelled(false);
		validProj.setFinishTime(new Timestamp(System.currentTimeMillis() + 86400000));
		
		assertFalse(_pledgeHelper.projectPledgeable(projFinished));
		assertFalse(_pledgeHelper.projectPledgeable(projCancelled));
		assertTrue(_pledgeHelper.projectPledgeable(validProj));
	}

	@Test
	public void userCanPledgeTest() {
		List<Pledge> pledges = new ArrayList<>();
		Pledge pledge = new Pledge();
		pledge.setActive(true);
		pledge.setAmount(new BigDecimal(4000));
		pledges.add(pledge);
		
		User user = new User();
		user.setCreditLimit(new BigDecimal(5000));
		user.setPledges(pledges);
		
		assertTrue(_pledgeHelper.userCanPledge(user, new BigDecimal(500)));
		assertTrue(_pledgeHelper.userCanPledge(user, new BigDecimal(1000)));
		assertFalse(_pledgeHelper.userCanPledge(user, new BigDecimal(1001)));
	}
	
	@Test
	public void pledgeCancelableForProjectTest() {
		Project projFinished = new Project();
		projFinished.setFinishTime(new Timestamp(System.currentTimeMillis() - 86400000));
		
		Project projNotCancelled = new Project();
		projNotCancelled.setCancelled(true);
		projNotCancelled.setFinishTime(new Timestamp(System.currentTimeMillis() + 86400000));
		
		Project validProj = new Project();
		validProj.setCancelled(true);
		validProj.setFinishTime(new Timestamp(System.currentTimeMillis() + 86400000));
		

		assertFalse(_pledgeHelper.pledgeCancelableForProject(projFinished));
		assertTrue(_pledgeHelper.pledgeCancelableForProject(projNotCancelled));
		assertTrue(_pledgeHelper.pledgeCancelableForProject(validProj));
	}
}
