package com.crowdfunder.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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
import com.crowdfunder.repositories.interfaces.IPledgeRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
@DataJpaTest
public class PledgeRepositoryTests {

    @Autowired
    private TestEntityManager _entityManager;
    
    @Autowired
    private IPledgeRepository _pledgeRepo;
    
    @Test
    public void findAll() {
    	List<Pledge> pledges = _pledgeRepo.findAll();
    	assertNotNull(pledges);
    	assertTrue(!pledges.isEmpty());
    }
    
    @Test
    public void findById() {
    	Pledge pledge = _pledgeRepo.findOne((long) 1);
    	assertNotNull(pledge);
    	assertTrue(pledge.getPledgeId() == 1);
    	assertEquals(pledge.getAmount().compareTo(new BigDecimal(300)), 0);
    	assertNotNull(pledge.getProject());
    	assertNotNull(pledge.getUser());
    }
    
    @Test
    public void update() {
    	Pledge pledge = new Pledge();
    	pledge.setActive(true);
    	pledge.setAmount(new BigDecimal(300));
    	pledge.setPledgeId((long) 1); 
    	
    	Project dummyProj = new Project();
    	dummyProj.setProjectId((long) 1);
    	User dummyUser = new User();

		_entityManager.persist(dummyUser);

    	pledge.setProject(dummyProj);
    	pledge.setUser(dummyUser);
    	
    	_pledgeRepo.save(pledge);
    	Pledge upledge = _pledgeRepo.findOne((long) 1);
    	
    	assertEquals(upledge.getUser(), dummyUser);
    	assertEquals(upledge.getProject().getProjectId(), dummyProj.getProjectId());
    	assertEquals(upledge.getAmount().compareTo(new BigDecimal(300)), 0);
    }
    
    @Test
    public void create() {
    	Pledge pledge = new Pledge();
    	pledge.setActive(true);
    	pledge.setAmount(new BigDecimal(300));
    	
    	Project dummyProj = new Project();
    	dummyProj.setProjectId((long) 1);
    	User dummyUser = new User();

		_entityManager.persist(dummyUser);
    	
    	pledge.setProject(dummyProj);
    	pledge.setUser(dummyUser);
    	
    	Pledge saved = _pledgeRepo.save(pledge);
    	assertTrue(saved.getPledgeId() == 4);
    	assertEquals(pledge.getUser(), dummyUser);
    	assertEquals(pledge.getProject(), dummyProj);
    }
}
