package com.crowdfunder.control.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.crowdfunder.control.validation.annotations.ValidYTubeLink;


/**
 ********************************************************************
 * Custom hibernate validator that checks if a youtube video link is valid
 * 
 * In doing so it must check if the list is empty &/ null
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class YTubeLinkValidator implements ConstraintValidator<ValidYTubeLink, String> {

	private final static String YTUBE_LINK_REGEX = "http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?\u200C\u200B[\\w\\?\u200C\u200B=]*)?";
	
	@Override
	public void initialize(ValidYTubeLink constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		return value.matches(YTUBE_LINK_REGEX);
	}

}
