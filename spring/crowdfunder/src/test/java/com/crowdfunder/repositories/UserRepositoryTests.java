package com.crowdfunder.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.models.User;
import com.crowdfunder.repositories.interfaces.IUserRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
@DataJpaTest
public class UserRepositoryTests {
    
    @Autowired
    private IUserRepository _userRepo;

    
    @Test
    public void findAll() {
    	List<User> users = _userRepo.findAll();
    	assertNotNull(users);
    	assertTrue(!users.isEmpty());
    }
    
    @Test
    public void findById() {
    	User user = _userRepo.findOne((long) 1);
    	assertNotNull(user);
    	assertTrue(user.getUserId() == 1);
    	assertEquals(user.getUserName(), "kyle");
    	assertEquals(user.getEmail(), "kyle.williamson@mycit.ie");
    	assertTrue(user.getCreditLimit().compareTo(new BigDecimal(1000)) == 0);
    	assertNotNull(user.getPledges());
    	assertNotNull(user.getProjects());
    	assertNotNull(user.getRoles());
    }
    
    @Test
    public void update() {
    	User user = new User();
    	user.setCreditLimit(new BigDecimal(20000));
    	user.setEmail("email@email.com");
    	user.setPassword("password2");
    	user.setPasswordConfirm("password2");
    	user.setUserName("user");
    	user.setUserId((long) 1);
    	user.setPledges(new ArrayList<>());
    	user.setProjects(new ArrayList<>());
    	user.setRoles(new HashSet<>());
    	
    	_userRepo.save(user);
    	User uUser = _userRepo.findOne((long) 1);
    	
    	assertEquals(user.getCreditLimit(), uUser.getCreditLimit());
    	assertEquals(user.getEmail(), uUser.getEmail());
    	assertEquals(user.getPassword(), uUser.getPassword());
    	assertNull(uUser.getPasswordConfirm());
    	assertEquals(user.getPledges(), uUser.getPledges());
    	assertEquals(user.getProjects(), uUser.getProjects());
    	assertEquals(user.getUserId(), uUser.getUserId());
    	assertEquals(user.getUserName(), uUser.getUserName());
    }
    
    @Test
    public void create() {
    	User user = new User();
    	user.setCreditLimit(new BigDecimal(20000));
    	user.setEmail("email2@email.com");
    	user.setPassword("password2");
    	user.setPasswordConfirm("password2");
    	user.setUserName("user2");
    	user.setPledges(new ArrayList<>());
    	user.setProjects(new ArrayList<>());
    	user.setRoles(new HashSet<>());
    	
    	_userRepo.save(user);
    	User uUser = _userRepo.findBy_userName("user2");
    	
    	assertNotNull(uUser);
    	assertEquals(user.getCreditLimit(), uUser.getCreditLimit());
    	assertEquals(user.getEmail(), uUser.getEmail());
    	assertEquals(user.getPassword(), uUser.getPassword());
    	assertEquals(user.getPasswordConfirm(), uUser.getPasswordConfirm());
    	assertEquals(user.getPledges(), uUser.getPledges());
    	assertEquals(user.getProjects(), uUser.getProjects());
    	assertEquals(user.getUserId(), uUser.getUserId());
    	assertEquals(user.getUserName(), uUser.getUserName());
    }
    
    @Test
    public void findByUsername() {
    	User user = _userRepo.findBy_userName("kyle");
    	
    	assertNotNull(user);
    	assertTrue(user.getUserId() == 1);
    	assertEquals(user.getUserName(), "kyle");
    	assertEquals(user.getEmail(), "kyle.williamson@mycit.ie");
    	assertTrue(user.getCreditLimit().compareTo(new BigDecimal(1000)) == 0);
    	assertNotNull(user.getPledges());
    	assertNotNull(user.getProjects());
    	assertNotNull(user.getRoles());
    }
}
