package com.voting_app.util.cookies.parsers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;

import org.json.JSONArray;
import org.json.JSONObject;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.util.cookies.constants.IVotingPollCookieConstants;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParser;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParserConstants;
/**
 ********************************************************************
 * Class that parses a Cookie containing political parties data in JSON format
 * <br>
 * <br>
 * {@implements {@link IVotingPollCookieParser}}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class JSONPoliticalPartiesCookieParser implements IVotingPollCookieParser {

	public void parseCookie(IVotePollDAO dao, Cookie cookie) {
		JSONArray politicalPartyArr = null;
		try {
			politicalPartyArr = new JSONArray(URLDecoder.decode(cookie.getValue(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8));
		} catch (UnsupportedEncodingException e) {
			// Should never occur
			e.printStackTrace();
		}
		for(int i = 0; i < politicalPartyArr.length(); i++) {
			JSONObject obj = (JSONObject) politicalPartyArr.get(i);
			dao.getPoliticalPartyDAO().addPoliticalParty(obj.getInt(IVotingPollCookieParserConstants.ID_FIELD_KEY), obj.getString(IVotingPollCookieParserConstants.NAME_FIELD_KEY));
		}
	}

}
