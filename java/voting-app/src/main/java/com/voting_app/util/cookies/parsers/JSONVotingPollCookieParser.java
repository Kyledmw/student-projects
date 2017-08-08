package com.voting_app.util.cookies.parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;

import org.json.JSONObject;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.util.cookies.constants.IVotingPollCookieConstants;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParser;

/**
 ********************************************************************
 * Class that parses a Cookie containing voting poll data in JSON format
 * <br>
 * <br>
 * {@implements {@link IVotingPollCookieParser}}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class JSONVotingPollCookieParser implements IVotingPollCookieParser {

	private static final String VALID_VOTES_FIELD  = "validVotes";
	private static final String INVALID_VOTES_FIELD  = "invalidVotes";

	public void parseCookie(IVotePollDAO dao, Cookie cookie) {
		JSONObject votePollObj = null;
		try {
			votePollObj = new JSONObject(URLDecoder.decode(cookie.getValue(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8));
		}  catch (UnsupportedEncodingException e) {
			// Should never occur
			e.printStackTrace();
		}
		dao.initVotingPoll(votePollObj.getInt(VALID_VOTES_FIELD), votePollObj.getInt(INVALID_VOTES_FIELD));

	}

}
