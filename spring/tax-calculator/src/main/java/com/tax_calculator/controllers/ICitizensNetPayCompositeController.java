package com.tax_calculator.controllers;

import com.tax_calculator.view.console.IConsoleView;

/**
 ********************************************************************
 * This controller prepares the ConsoleCitizenDisplayer view with data
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICitizensNetPayCompositeController {
	
	/**
	 * Prepare the ConsoleCitizenDisplayer view object and return it.
	 * 
	 * @return IConsoleView, ConsoleCitizenDisplayer view object
	 */
	IConsoleView getView();
}
