package com.tax_calculator.view.menu;

import java.util.List;

import com.tax_calculator.util.general.ExternalisedStrings;
import com.tax_calculator.view.console.IConsoleView;
import com.tax_calculator.view.input.KBValidInputEngine;

/**
 ********************************************************************
 * @implements IConsoleView
 *
 * A generic console menu. Uses a List<ConsoleMenuOption> for displaying options and handling events
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConsoleMenu implements IConsoleView {

	private List<ConsoleMenuOption> _options;

	public ConsoleMenu(List<ConsoleMenuOption> options) {
		_options = options;
	}

	@Override
	public void display() {
		for (int i = 0; i < _options.size(); i++) {
			StringBuilder builder = new StringBuilder();
			builder.append(i + 1);
			builder.append(") ");
			builder.append(_options.get(i).getDisplayString());
			System.out.println(builder.toString());
		}
	}

	@Override
	public void handleInput(KBValidInputEngine kb) {
		int input = kb.getValidInt();
		input--;
		if(input >= 0 && input < _options.size()) {
			_options.get(input).getListener().action(input);
		} else {
			System.out.println(ExternalisedStrings.INPUT_INVALID_NUMBER);
		}
		
	}
	
	/**
	 * Update a string for an option at the given index
	 * 
	 * @param index index of the menu option to edit
	 * @param option new option String to replace existing one
	 */
	public void updateOptionString(int index, String option) {
		_options.get(index).setDisplayString(option);
	}
}
