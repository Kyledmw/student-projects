package com.tax_calculator.view.callbacks;

/**
 ********************************************************************
 *
 * Interface for passing citizen data up from the view layer to the control layer
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICitizenCallback {

	void callback(String citizenName, double citizenSalary);
}
