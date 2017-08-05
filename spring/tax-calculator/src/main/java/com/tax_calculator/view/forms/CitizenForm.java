package com.tax_calculator.view.forms;

import com.tax_calculator.util.general.ExternalisedStrings;
import com.tax_calculator.view.callbacks.ICitizenCallback;
import com.tax_calculator.view.console.IConsoleView;
import com.tax_calculator.view.input.KBValidInputEngine;

/**
 ********************************************************************
 * @Implements IConsoleView
 * 
 * View representing a form for adding Citizens
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CitizenForm implements IConsoleView {

	private int _curIndex;
	private String _citizenName;
	private double _citizenSalary;
	
	private ICitizenCallback _callback;

	public CitizenForm() {
		_curIndex = 0;
		System.out.println(ExternalisedStrings.APP_FORM_ADD_CITIZEN + ":");
	}

	@Override
	public void display() {
		if (_curIndex == 0) {
			System.out.println(ExternalisedStrings.DATA_CITIZEN_NAME + ":");
		} else if (_curIndex == 1) {
			System.out.println(ExternalisedStrings.DATA_CITIZEN_SALARY + ":");
		}
	}

	@Override
	public void handleInput(KBValidInputEngine kb) {
		if (_curIndex == 0) {
			_citizenName = kb.getNonEmptyString();
			_curIndex++;
		} else if (_curIndex == 1) {
			_citizenSalary = kb.getValidDouble();
			_callback.callback(_citizenName, _citizenSalary);
		}
	}
	
	/**
	 * Add a callback for when the view is finished
	 * 
	 * @param callback ICitizenCallback
	 */
	public void onFinishHandler(ICitizenCallback callback) {
		_callback = callback;
	}

}
