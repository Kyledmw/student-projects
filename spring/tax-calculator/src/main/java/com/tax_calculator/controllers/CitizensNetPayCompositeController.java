package com.tax_calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tax_calculator.services.ICitizenNetPayCompositeService;
import com.tax_calculator.view.console.ConsoleCitizenDisplayer;
import com.tax_calculator.view.console.IConsoleView;


/**
 ********************************************************************
 * @implements ICitizensNetPayCompositeController
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class CitizensNetPayCompositeController implements ICitizensNetPayCompositeController {
	
	@Autowired
	private ICitizenNetPayCompositeService _service;

	@Override
	public IConsoleView getView() {
		IConsoleView view = new ConsoleCitizenDisplayer(_service.getCitizensWithNetPay());
		return view;
	}
}
