package com.crowdfunder.util.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.crowdfunder.util.parsers.interfaces.IYTLinkParser;

/**
 ********************************************************************
 * Standard implementation of IYTLinkParser
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class YTLinkParser implements IYTLinkParser {
	
	private static final String YTUBE_VID_REGEX = "https\\:\\/\\/www\\.youtube\\.com\\/watch\\?v=([\\w-]{11})";

	@Override
	public String parseLinkForVidCode(String ytLink) {
		String vId = null;
		Pattern pattern = Pattern.compile(
				YTUBE_VID_REGEX, 
                Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(ytLink);
	    if (matcher.matches()){
	        vId = matcher.group(1);
	    }
	    return vId;
	}

}
