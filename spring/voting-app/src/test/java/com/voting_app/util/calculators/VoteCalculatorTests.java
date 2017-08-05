package com.voting_app.util.calculators;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

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

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class VoteCalculatorTests {

    @Autowired
    private ApplicationContext _ctx;
	
	private static IVoteCalculator _voteCalc;

	@Before
	public void setUp() {
		_voteCalc = _ctx.getBean(VoteCalculator.class);
	}
	
	@Test
	public void getTotalVotes() {
		final int totalCandidates = 5;
		final int totalVotes = 155;
		
		Map<Integer, Integer> votesByRank = new HashMap<Integer, Integer>();
		votesByRank.put(1, 15);
		votesByRank.put(2, 12);
		votesByRank.put(3, 7);
		votesByRank.put(4, 5);
		votesByRank.put(5, 1);
		
		int totalVotesRes = _voteCalc.getTotalVotes(votesByRank, totalCandidates);
		assertEquals(new Integer(totalVotes), new Integer(totalVotesRes));
	}
	
	@Test
	public void calculateRankonePercentage() {
		final int rankOneVotes = 15;
		final int validVotes = 60;
		
		double percentage = _voteCalc.calculateRankOnePercentage(rankOneVotes, validVotes);
		assertEquals(new Double(25), new Double(percentage));
	}

}
