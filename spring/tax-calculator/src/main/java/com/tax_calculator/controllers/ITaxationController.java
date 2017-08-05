package com.tax_calculator.controllers;

import com.tax_calculator.models.Tax;
import com.tax_calculator.view.callbacks.IViewCallback;
import com.tax_calculator.view.console.IConsoleView;

/**
 ********************************************************************
 * This controller handles tax related views and events
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ITaxationController {

	/**
	 * Prepares a ConsoleMenu for tax related actions
	 * 
	 * @param viewCallback a callback used to exit the view.
	 * @return IConsoleView, ConsoleMenu with relevant options and handlers
	 */
	IConsoleView getView(IViewCallback viewCallback);
	
	/**
	 * Prepares TaxForm view for an existing Tax model
	 * 
	 * @param tax Tax object to create form for.
	 * @return TaxForm object
	 */
	IConsoleView getEditView(Tax tax);

}
