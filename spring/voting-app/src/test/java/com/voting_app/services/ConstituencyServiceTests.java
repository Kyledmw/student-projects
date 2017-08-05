package com.voting_app.services;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.voting_app.VotingAppApplication;
import com.voting_app.models.Constituency;
import com.voting_app.services.interfaces.IConstituencyService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class ConstituencyServiceTests {
	
	@Mock
	private IConstituencyService _mockConstitService;
	
	@Mock
	private Constituency _mockConstit;
	
	@Autowired
	private IConstituencyService _constService;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void save() {
		final int defaultId = 0;
		final int changedId = 1;
		when(_mockConstit.getId()).thenReturn(defaultId);
		_constService.save(_mockConstit);
		when(_mockConstit.getId()).thenReturn(changedId);
		_constService.save(_mockConstit);
	}
	
	@Test
	public void get() {
		final int id = 1;
		final String name = "Dummy Name";
		
		Constituency constit = new Constituency();
		constit.setId(id);
		constit.setName(name);
		
		when(_mockConstitService.get(id)).thenReturn(constit);
		assertEquals(constit, _mockConstitService.get(id));
	}
	
	@Test
	public void getAll() {
		final int listSize = 30;
		List<Constituency> list = new ArrayList<Constituency>();
		for(int i=0; i<listSize; i++) {
			Constituency constit = mock(Constituency.class);
			when(constit.getId()).thenReturn(i);
			when(constit.getName()).thenReturn("Constituency " + 1);
			list.add(constit);
		}
		when(_mockConstitService.getAll()).thenReturn(list);
		assertEquals(list, _mockConstitService.getAll());
	}
}
