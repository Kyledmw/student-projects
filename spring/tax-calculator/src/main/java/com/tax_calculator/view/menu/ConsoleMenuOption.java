package com.tax_calculator.view.menu;

import com.tax_calculator.view.callbacks.OptionsListener;

/**
 ********************************************************************
 *
 * Class that represents a single option in a console menu.
 * 
 * Contains the display string for the option.
 * 
 * Contains a listener to handle the event that this option was selected
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConsoleMenuOption {

	private String _displayString;
	
	private OptionsListener _listener;
	
	public void setDisplayString(String display) {
		_displayString = display;
	}
	
	public void setListener(OptionsListener listener){
		_listener = listener;
	}
	
	public String getDisplayString() {
		return _displayString;
	}
	
	public OptionsListener getListener() {
		return _listener;
	}
}
