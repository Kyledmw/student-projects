package com.tax_calculator.view.console;

import com.tax_calculator.view.input.KBValidInputEngine;

/**
 ********************************************************************
 *
 * Generic interface representing a view object for console displays
 * 
 * Each of these methods should be called every loop
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IConsoleView {

	/**
	 * This method handles the display of the view
	 */
	void display();
	
	/**
	 * This method handles any user input required from the keyboard
	 * 
	 * @param kb Keyboard valid input engine
	 */
	void handleInput(KBValidInputEngine kb);
}
