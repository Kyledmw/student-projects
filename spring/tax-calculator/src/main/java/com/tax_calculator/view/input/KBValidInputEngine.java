package com.tax_calculator.view.input;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.tax_calculator.util.general.ExternalisedStrings;

/**
 ********************************************************************
 * Adapter Bean that adds a validator engine on top of a standard System.in Scanner
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class KBValidInputEngine {

	private Scanner _kb;
	
	public KBValidInputEngine() {
		_kb = new Scanner(System.in);
	}
	
	/**
	 * Retrieve a valid integer from the console
	 * @return valid integer input
	 */
	public int getValidInt() {
		int input;
		while(!_kb.hasNextInt()) {
			System.out.println(ExternalisedStrings.INPUT_INVALID_NUMBER);
			_kb.next();
		}
		input = _kb.nextInt();
		_kb.nextLine();
		return input;
	}
	
	/**
	 * Retrieve a valid double from the console
	 * @return valid double input
	 */
	public double getValidDouble() {
		double input;
		while(!_kb.hasNextDouble()) {
			System.out.println(ExternalisedStrings.INPUT_INVALID_NUMBER);
			_kb.next();
		}
		input = _kb.nextDouble();
		_kb.nextLine();
		return input;
	}
	
	/**
	 * Retrieve a non empty String from the console
	 * @return non empty String
	 */
	public String getNonEmptyString() {
		boolean validInput = false;
		String input = "";
		while(!validInput) {
			input = _kb.next();
			if(input != "") {
				validInput = true;
			} else {
				System.out.println(ExternalisedStrings.INPUT_INVALID_STRING);
			}
		}
		return input;
	}
	
	/**
	 * Retrieve a valid char from the console
	 * @return valid char input
	 */
	public char getValidChar() {
		char c = ' ';
		boolean validInput = false;
		
		while(!validInput) {
			String s = getNonEmptyString();
			
			if(s.length() > 1) {
				System.out.println(ExternalisedStrings.INPUT_INVALID_CHAR);
			} else {
				c = s.charAt(0);
				validInput = true;
			}
		}
		return c;
	}
}
