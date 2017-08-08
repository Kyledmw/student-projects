package com.voting_app.util.cookies;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.json.JSONArray;
import org.json.JSONObject;

import com.voting_app.dao.VotePollListDAO;
import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.util.cookies.constants.IVotingPollCookieConstants;
import com.voting_app.util.cookies.parsers.JSONVotingPollCookieParserFactory;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParser;
import com.voting_app.util.cookies.parsers.interfaces.IVotingPollCookieParserFactory;
/**
 ********************************************************************
 * Class that handles conversion between cookies {@link Cookie} and the DAO {@link IVotePollDAO}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VotingPollCookieHandler {

	/**
	 * Create cookies from the given {@link IVotePollDAO}
	 * 
	 * @param dao {@link IVotePollDAO} DAO to create cookies off
	 * @return {@link List} List of cookies created from DAO data
	 */
	public synchronized List<Cookie> getCookies(IVotePollDAO dao) {
		List<Cookie> cookieList = new ArrayList<Cookie>();
		JSONArray candidateList = new JSONArray(dao.getCandidateDAO().getCandidates());
		JSONArray politicalPartiesList = new JSONArray(dao.getPoliticalPartyDAO().getPoliticalParties());
		JSONArray candidateVotes = new JSONArray(dao.getCandidateVotesDAO().getCandidateVotes());
		JSONObject votingpoll = new JSONObject(dao.getVotingPoll());
		try {
			cookieList.add(new Cookie(IVotingPollCookieConstants.CANDIDATES_KEY, URLEncoder.encode(candidateList.toString(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8)));
			cookieList.add(new Cookie(IVotingPollCookieConstants.POLITICAL_PARTIES_KEY, URLEncoder.encode(politicalPartiesList.toString(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8)));
			cookieList.add(new Cookie(IVotingPollCookieConstants.CANDIDATE_VOTES_KEY, URLEncoder.encode(candidateVotes.toString(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8)));
			cookieList.add(new Cookie(IVotingPollCookieConstants.VOTING_POLL_KEY, URLEncoder.encode(votingpoll.toString(), IVotingPollCookieConstants.COOKIE_ENCODING_UTF8)));
			return cookieList;
		} catch (UnsupportedEncodingException e) {
			//Should never be thrown
			return null;
		}
	}
	
	/**
	 * Create and seed a {@link IVotePollDAO} object from the given cookies
	 * 
	 * @param cookies {@link Cookie}[]
	 * @return {@link IVotePollDAO} seeded DAO
	 */
	public synchronized IVotePollDAO getDAO(Cookie[] cookies) {
		IVotePollDAO dao = new VotePollListDAO();
		if(cookies != null) {
			IVotingPollCookieParserFactory cookieParserFactory = new JSONVotingPollCookieParserFactory();
			for(int i = 0; i < cookies.length; i++) {
				IVotingPollCookieParser parser = cookieParserFactory.getParser(cookies[i].getName());
				if(parser != null) {
					parser.parseCookie(dao, cookies[i]);
				}
			}
		}
		return dao;
	}
}
