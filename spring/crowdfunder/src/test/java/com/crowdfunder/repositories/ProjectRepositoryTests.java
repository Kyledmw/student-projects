package com.crowdfunder.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;
import com.crowdfunder.models.User;
import com.crowdfunder.repositories.interfaces.IProjectRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
@DataJpaTest
public class ProjectRepositoryTests {

    @Autowired
    private TestEntityManager _entityManager;
    
	@Autowired
	private IProjectRepository _projRepo;
	
	@Test
	public void findAll() {
		List<Project> projects = _projRepo.findAll();
		assertNotNull(projects);
		assertTrue(!projects.isEmpty());
	}
	
	@Test
	public void findById() {
		Project proj = _projRepo.findOne((long) 1);
		assertNotNull(proj);
		assertTrue(proj.getProjectId() == 1);
		assertTrue(proj.getName().equals("Appliance Maintence Scheduling"));
		assertEquals(proj.getGoalAmount().compareTo(new BigDecimal(10000)), 0);
	}
	
	@Test
	public void update() {
		final long PROJ_ID = 2;
		Project proj = new Project();
		proj.setProjectId(PROJ_ID );
		proj.setCancelled(false);
		proj.setCompleted(false);
		proj.setDescription("Test");
		proj.setFinishTime(new Timestamp(System.currentTimeMillis()));
		proj.setGoalAmount(new BigDecimal(20000));
		proj.setName("Test");
		proj.setPledges(new ArrayList<Pledge>());
		
		User dummyUser = new User();
		
		proj.setUser(dummyUser);
		
		_entityManager.persist(dummyUser);
		
		_projRepo.save(proj);
		Project uproj = _projRepo.findOne(PROJ_ID);
		
		assertEquals(uproj.getUser(), dummyUser);
		assertTrue(uproj.getDescription().equals("Test"));
		assertTrue(uproj.getName().equals("Test"));
		assertEquals(uproj.getGoalAmount().compareTo(new BigDecimal(20000)), 0);
	}
	
	@Test
	public void create() {
		Project proj = new Project();
		proj.setCancelled(false);
		proj.setCompleted(false);
		proj.setDescription("Test");
		proj.setFinishTime(new Timestamp(System.currentTimeMillis()));
		proj.setGoalAmount(new BigDecimal(20000));
		proj.setName("Test");
		proj.setPledges(new ArrayList<Pledge>());
		
		User dummyUser = new User();

		proj.setUser(dummyUser);
		
		_entityManager.persist(dummyUser);
		
		Project savedProj = _projRepo.save(proj);
		assertTrue(savedProj.getProjectId() == 4);
		assertEquals(savedProj.getUser(), dummyUser);
	}
}

