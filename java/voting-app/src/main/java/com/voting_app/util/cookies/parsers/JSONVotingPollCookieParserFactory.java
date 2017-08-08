package com.voting_app.util.cookies.parsers;

import java.util.HashMap;
import java.util.Map;

import com.voting_app.util.cookies.constants.IVotingPollCookieConstants;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParser;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParserFactory;

/**
 ********************************************************************
 * Implementation of {@link IVotingPollCookieParserFactory} for JSON Cookie parsers
 * <br>
 * <br>
 * {@implements {@link IVotingPollCookieParserFactory}}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class JSONVotingPollCookieParserFactory implements IVotingPollCookieParserFactory {

	
	private Map<String, IVotingPollCookieParser> _parsers;

	public JSONVotingPollCookieParserFactory() {
		_parsers = new HashMap<String, IVotingPollCookieParser>();
		_parsers.put(IVotingPollCookieConstants.CANDIDATES_KEY, new JSONCandidatesCookieParser());
		_parsers.put(IVotingPollCookieConstants.POLITICAL_PARTIES_KEY, new JSONPoliticalPartiesCookieParser());
		_parsers.put(IVotingPollCookieConstants.CANDIDATE_VOTES_KEY, new JSONCandidateVotesCookieParser());
		_parsers.put(IVotingPollCookieConstants.VOTING_POLL_KEY, new JSONVotingPollCookieParser());
	}
	
	public IVotingPollCookieParser getParser(String key) {
		return _parsers.get(key);
	}

}
