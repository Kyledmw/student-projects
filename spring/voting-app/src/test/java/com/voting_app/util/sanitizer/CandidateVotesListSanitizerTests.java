package com.voting_app.util.sanitizer;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.voting_app.VotingAppApplication;
import com.voting_app.models.CandidateVote;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class CandidateVotesListSanitizerTests {
	
    @Autowired
    private ApplicationContext _ctx;
    
    private ICandidateVoteListSanitizer _sanitizer;

	@Before
	public void setUp() {
		_sanitizer = _ctx.getBean(CandidateVotesListSanitizer.class);
	}

	@Test
	public void sanitizeList() {
		List<CandidateVote> listToSanitize = new ArrayList<CandidateVote>();
		List<CandidateVote> list = new ArrayList<CandidateVote>();
		
		CandidateVote cVote1 = new CandidateVote();
		cVote1.setRank(1);
		CandidateVote cVote2 = new CandidateVote();
		cVote2.setRank(0);
		CandidateVote cVote3 = new CandidateVote();
		cVote3.setRank(3);
		CandidateVote cVote4 = new CandidateVote();
		cVote4.setRank(0);
		CandidateVote cVote5 = new CandidateVote();
		cVote5.setRank(0);
		CandidateVote cVote6 = new CandidateVote();
		cVote6.setRank(2);
		
		listToSanitize.add(cVote1);
		listToSanitize.add(cVote2);
		listToSanitize.add(cVote3);
		listToSanitize.add(cVote4);
		listToSanitize.add(cVote5);
		listToSanitize.add(cVote6);
		
		list.add(cVote1);
		list.add(cVote3);
		list.add(cVote6);
		
		List<CandidateVote> sanitizedList = _sanitizer.sanitizeList(listToSanitize);
		
		assertEquals(sanitizedList, list);
	}
}
