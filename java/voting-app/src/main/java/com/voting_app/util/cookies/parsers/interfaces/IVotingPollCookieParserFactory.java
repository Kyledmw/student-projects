package com.voting_app.util.cookies.parsers.interfaces;

/**
 ********************************************************************
 * Interface for a {@link IVotingPollCookieParser} Factory
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IVotingPollCookieParserFactory {
	
	/**
	 * Retrieve a {@link IVotingPollCookieParser} for the given key
	 * 
	 * @param key key of a cookie to find parser of
	 * 
	 * @return {@link IVotingPollCookieParser} parser for the given key
	 */
	IVotingPollCookieParser getParser(String key);
}
