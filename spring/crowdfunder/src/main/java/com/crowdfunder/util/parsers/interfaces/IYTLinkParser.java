package com.crowdfunder.util.parsers.interfaces;

/**
 ********************************************************************
 * Interface for a YouTube link parser. 
 * 
 * Contains relevant parsing methods required for ytlinks.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IYTLinkParser {
	
	/**
	 * Parse a YouTube link to get the code/id for the video.
	 * 
	 * E.g. ytLink = "https://www.youtube.com/watch?v=tJKAQggSs9Q" will return tJKAQggSs9Q
	 * 
	 * @param ytLink YouTube link to parse
	 * 
	 * @return video code from parsed link.
	 */
	String parseLinkForVidCode(String ytLink);
}
