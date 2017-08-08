package com.voting_app.util.cookies.parsers.interfaces;

import javax.servlet.http.Cookie;

import com.voting_app.dao.interfaces.IVotePollDAO;

/**
 ********************************************************************
 * Interface for a voting poll cookie parser
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IVotingPollCookieParser {
	
	/**
	 * Parses cookie and stores its data in the DAO
	 * 
	 * @param dao reference to {@link IVotePollDAO} to add cookie data to
	 * @param cookie {@link Cookie} cookie to parse and store in the DAO
	 */
	void parseCookie(IVotePollDAO dao, Cookie cookie);
}
 