package com.tax_calculator.view.console;

import java.text.DecimalFormat;
import java.util.List;

import com.tax_calculator.models.CitizenWithNetPay;
import com.tax_calculator.util.general.ExternalisedStrings;
import com.tax_calculator.view.input.KBValidInputEngine;
/**
 ********************************************************************
 * @Implements IConsoleView
 * 
 * View used for displaying CitizenWithNetPay objects to the console
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConsoleCitizenDisplayer implements IConsoleView {
	
	private List<CitizenWithNetPay> _citizens;
	
	public ConsoleCitizenDisplayer(List<CitizenWithNetPay> citizens) {
		_citizens = citizens;
	}

	@Override
	public void display() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		StringBuilder builder = new StringBuilder();
		builder.append(ExternalisedStrings.DATA_CITIZEN_NAME + ":");
		builder.append("\t\t");
		builder.append(ExternalisedStrings.DATA_CITIZEN_SALARY + ":");
		builder.append("\t\t");
		builder.append(ExternalisedStrings.DATA_NET_PAY + ":");
		builder.append("\n");
		builder.append("-----------------------------------------------------------------------------------------");
		System.out.println(builder.toString());
		for(CitizenWithNetPay citizen: _citizens) {
			StringBuilder citizenBuilder = new StringBuilder();
			citizenBuilder.append(citizen.getCitizen().getName());
			citizenBuilder.append("\t\t\t");
			citizenBuilder.append(df.format(citizen.getCitizen().getSalary()));
			citizenBuilder.append("\t\t\t\t");
			citizenBuilder.append(df.format(citizen.getNetPay()));
			System.out.println(citizenBuilder.toString());
		}
		System.out.println("-----------------------------------------------------------------------------------------");
	}

	@Override
	public void handleInput(KBValidInputEngine kb) {
		return;
	}

}
