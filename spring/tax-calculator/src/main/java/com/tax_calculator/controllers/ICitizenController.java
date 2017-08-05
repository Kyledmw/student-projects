package com.tax_calculator.controllers;

import com.tax_calculator.view.callbacks.IViewCallback;
import com.tax_calculator.view.console.IConsoleView;


/**
 ********************************************************************
 * This controller handles events from the CitizenForm view.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICitizenController {

	/**
	 * Prepares the CitizensFormView.
	 * 
	 * @param viewCallback a callback used to exit the view.
	 * @return IConsoleView, CitizenForm view object
	 */
	IConsoleView getView(IViewCallback viewCallback);
}
