package com.tax_calculator.view.callbacks;

/**
 ********************************************************************
 *
 * Listener used to handle events from ConsoleMenuOption selections.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface OptionsListener {

	/**
	 * 
	 * Listener function to handle the event
	 * 
	 * @param index index of the option in its menu
	 */
	void action(int index);
}
