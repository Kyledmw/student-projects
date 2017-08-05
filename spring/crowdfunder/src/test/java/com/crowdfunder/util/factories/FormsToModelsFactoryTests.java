package com.crowdfunder.util.factories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.control.forms.PledgeForm;
import com.crowdfunder.control.forms.ProjectForm;
import com.crowdfunder.control.forms.UserForm;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;
import com.crowdfunder.models.User;
import com.crowdfunder.util.factories.interfaces.IFormsToModelsFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class FormsToModelsFactoryTests {

	@Autowired
	private IFormsToModelsFactory _formsToModelsFactory;
	
	@Test
	public void testUserFormToUser() {
		final String userName = "kyle";
		final String password = "password";
		final String pwConfirm = "password";
		final String email = "email";
		final BigDecimal creditLimit = new BigDecimal(2000);
		
		UserForm userForm = new UserForm();
		userForm.setUserName(userName);
		userForm.setEmail(email);
		userForm.setCreditLimit(creditLimit);
		userForm.setPassword(password);
		userForm.setPasswordConfirm(pwConfirm);
		
		User user = _formsToModelsFactory.userFormToUser(userForm);
		
		assertEquals(user.getCreditLimit(), creditLimit);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getPassword(), password);
		assertEquals(user.getPasswordConfirm(), pwConfirm);
		assertEquals(user.getUserName(), userName);
	}
	
	@Test
	public void testProjectFormToProject() {
		final String name = "project";
		final String desc = "Description";
		final String ytLink = "https://www.youtube.com/watch?v=tJKAQggSs9Q";
		final String ytCode = "tJKAQggSs9Q";
		final BigDecimal goal = new BigDecimal(50000);
		final long duration = 8;
		final String imageName = "imageName";
		
		MockMultipartFile firstFile = new MockMultipartFile(imageName, "filename.txt", "text/plain", "some xml".getBytes());
		
		ProjectForm projectForm = new ProjectForm();
		projectForm.setDesc(desc);
		projectForm.setGoalAmount(goal);
		projectForm.setProjName(name);
		projectForm.setDuration(duration);
		projectForm.setYTubeLink(ytLink);
		projectForm.setImage(firstFile);
		
		Project proj = _formsToModelsFactory.projectFormToProject(projectForm);
		
		assertEquals(proj.getName(), name);
		assertEquals(proj.getDescription(), desc);
		assertEquals(proj.getYtVidCode(), ytCode);
		assertEquals(proj.getGoalAmount(), goal);
		assertEquals(proj.getImageName(), imageName);
	}
	
	@Test 
	public void testPledgeFormToPledge() {
		final long projId = 3;
		final BigDecimal amount = new BigDecimal(2000);
		
		PledgeForm pf = new PledgeForm();
		pf.setProjectId(projId);
		pf.setAmount(amount);
		
		Pledge pledge = _formsToModelsFactory.pledgeFormToPledge(pf);
		
		assertEquals(pledge.getActive(), true);
		assertEquals(pledge.getAmount().compareTo(amount), 0);
	}
}
