package com.voting_app.util.cookies.parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.json.JSONArray;
import org.json.JSONObject;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.Candidate;
import com.voting_app.util.cookies.constants.IVotingPollCookieConstants;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParser;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParserConstants;

/**
 ********************************************************************
 * Class that parses a Cookie containing candidate votes data in JSON format
 * <br>
 * <br>
 * {@implements {@link IVotingPollCookieParser}}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class JSONCandidateVotesCookieParser implements IVotingPollCookieParser {
	
	private static final String CANDIDATE_KEY = "candidate";
	private static final String VOTES_BY_RANK_KEY = "votesByRank";

	public void parseCookie(IVotePollDAO dao, Cookie cookie) {
		JSONArray candidateVotesArr = null;
		try {
			candidateVotesArr = new JSONArray(URLDecoder.decode(cookie.getValue(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8));
		} catch (UnsupportedEncodingException e) {
			// Should never occur
			e.printStackTrace();
		}
		for(int i = 0; i < candidateVotesArr.length(); i++) {
			JSONObject obj = (JSONObject) candidateVotesArr.get(i);
			JSONObject candObj = (JSONObject) obj.get(CANDIDATE_KEY);
			Candidate candidate = dao.getCandidateDAO().getCandidate(candObj.getInt(IVotingPollCookieParserConstants.ID_FIELD_KEY));
			
			JSONObject votesByRankObj = (JSONObject) obj.get(VOTES_BY_RANK_KEY);
			
			
			String[] fieldNames = JSONObject.getNames(votesByRankObj);
			
			Map<Integer, Integer> votesByRank = new HashMap<Integer, Integer>();
			for(String fieldName: fieldNames) {
				votesByRank.put(Integer.parseInt(fieldName), votesByRankObj.getInt(fieldName));
			}
			dao.getCandidateVotesDAO().addCandidateVotes(candidate, votesByRank);
		}
		
	}

}
