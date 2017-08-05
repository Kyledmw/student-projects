package com.tax_calculator.controllers;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.services.ICitizensService;
import com.tax_calculator.view.callbacks.IViewCallback;
import com.tax_calculator.view.console.IConsoleView;
import com.tax_calculator.view.forms.CitizenForm;

/**
 ********************************************************************
 * @implements ICitizenController
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class CitizenController implements ICitizenController {
	
	@Autowired
	private ICitizensService _service;

	@Override
	public IConsoleView getView(IViewCallback viewCallback) {
		CitizenForm form = new CitizenForm();
		form.onFinishHandler((name, salary) -> {
			Citizen citizen = new Citizen();
			citizen.setName(name.replace('_', ' '));
			citizen.setSalary(new BigDecimal(salary,  MathContext.DECIMAL64));
			_service.save(citizen);
			viewCallback.callback();
		});
		return form;
	}

}
