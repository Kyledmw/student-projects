package com.voting_app.util.cookies.parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;

import org.json.JSONArray;
import org.json.JSONObject;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.PoliticalParty;
import com.voting_app.util.cookies.constants.IVotingPollCookieConstants;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParser;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParserConstants;

/**
 ********************************************************************
 * Class that parses a Cookie containing candidate data in JSON format
 * <br>
 * <br>
 * {@implements {@link IVotingPollCookieParser}}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class JSONCandidatesCookieParser implements IVotingPollCookieParser {

	private static final String POLITICAL_PARTY_FIELD_KEY = "politicalParty";
	
	public void parseCookie(IVotePollDAO dao, Cookie cookie) { 
		JSONArray candidatesArr = null;
		try {
			candidatesArr = new JSONArray(URLDecoder.decode(cookie.getValue(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8));
		} catch (UnsupportedEncodingException e) {
			// Should never occur
			e.printStackTrace();
		}
		for(int i = 0; i < candidatesArr.length(); i++) {
			JSONObject obj = (JSONObject) candidatesArr.get(i);
			JSONObject ppartyObj = obj.getJSONObject(POLITICAL_PARTY_FIELD_KEY);
			PoliticalParty pparty = dao.getPoliticalPartyDAO().addPoliticalParty(ppartyObj.getInt(IVotingPollCookieParserConstants.ID_FIELD_KEY), ppartyObj.getString(IVotingPollCookieParserConstants.NAME_FIELD_KEY));
			dao.getCandidateDAO().addCandidate(obj.getInt(IVotingPollCookieParserConstants.ID_FIELD_KEY), obj.getString(IVotingPollCookieParserConstants.NAME_FIELD_KEY), pparty);
		}
	}

}
