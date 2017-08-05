package com.tax_calculator.view.callbacks;

/**
 ********************************************************************
 * 
 * Interface for passing Taxation data from the view layer to the control layer
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ITaxCallback {

	void callback(double taxFrom, double eligableAmount, double percent, boolean taxLimited);
}
