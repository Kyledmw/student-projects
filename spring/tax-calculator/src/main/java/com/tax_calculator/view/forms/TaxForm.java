package com.tax_calculator.view.forms;

import com.tax_calculator.util.general.ExternalisedStrings;
import com.tax_calculator.view.callbacks.ITaxCallback;
import com.tax_calculator.view.console.IConsoleView;
import com.tax_calculator.view.input.KBValidInputEngine;

/**
 ********************************************************************
 * @Implements IConsoleView
 * 
 * View representing a form for Tax objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class TaxForm implements IConsoleView {

	private int _curIndex;
	private double _taxFrom;
	private double _eligableAmount;
	private double _percent;
	private boolean _taxLimited;

	private ITaxCallback _callback;

	public TaxForm() {
		_curIndex = 0;
		System.out.println(ExternalisedStrings.APP_FORM_EDIT_TAX + ":");
	}

	@Override
	public void display() {
		switch(_curIndex) {
			case 0:
				System.out.println(ExternalisedStrings.DATA_TAX_TAX_FROM + ":");
				break;
			case 1:
				System.out.println(ExternalisedStrings.DATA_TAX_AMOUNT_ELIGIBLE + ":");
				break;
			case 2:
				System.out.println(ExternalisedStrings.DATA_TAX_TAX_PERCENT + ":");
				break;
			case 3:
				System.out.println(ExternalisedStrings.DATA_TAX_TAX_LIMITED + ": 1=true, 2=false");
				break;
		}
	}

	@Override
	public void handleInput(KBValidInputEngine kb) {
		switch (_curIndex) {
			case 0:
				_taxFrom = kb.getValidDouble();
				_curIndex++;
				break;
			case 1:
				_eligableAmount = kb.getValidDouble();
				_curIndex++;
				break;
			case 2:
				_percent = kb.getValidDouble();
				_curIndex++;
				 break;
			case 3:
				int input = 0;
				while(input != 1 && input != 2) {
					input = kb.getValidInt();
				}
				_taxLimited = (input == 1);
				_callback.callback(_taxFrom, _eligableAmount, _percent, _taxLimited);
				break;
		}
	}

	public void onFinishHandler(ITaxCallback callback) {
		_callback = callback;
	}

}
