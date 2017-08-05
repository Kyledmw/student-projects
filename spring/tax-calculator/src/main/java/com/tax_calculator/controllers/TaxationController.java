package com.tax_calculator.controllers;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tax_calculator.models.Tax;
import com.tax_calculator.services.ITaxationService;
import com.tax_calculator.util.general.ExternalisedStrings;
import com.tax_calculator.view.callbacks.IViewCallback;
import com.tax_calculator.view.console.IConsoleView;
import com.tax_calculator.view.forms.TaxForm;
import com.tax_calculator.view.input.KBValidInputEngine;
import com.tax_calculator.view.menu.ConsoleMenu;
import com.tax_calculator.view.menu.ConsoleMenuOption;
import com.tax_calculator.view.util.OptionStringFactory;

/**
 ********************************************************************
 * @implements ITaxationController
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class TaxationController implements ITaxationController {
	
	@Autowired
	private ITaxationService _service;
	
	@Autowired
	private OptionStringFactory _optionStringFactory;
	
	@Autowired
	private KBValidInputEngine _kb;
	
	private boolean _inFormView;
	
	public TaxationController() {
		_inFormView = false;
	}

	@Override
	public IConsoleView getView(IViewCallback viewCallback) {
		List<Tax> taxRates = _service.getAll();
		List<ConsoleMenuOption> menuOptions = new ArrayList<>();
		ConsoleMenu view = new ConsoleMenu(menuOptions);
		for(Tax tax: taxRates) {
			ConsoleMenuOption option = new ConsoleMenuOption();
			option.setDisplayString(_optionStringFactory.createTaxOptionString(tax));
			option.setListener(index -> {
				_inFormView = true;
				IConsoleView form = getEditView(taxRates.get(index));
				while(_inFormView) {
					form.display();
					form.handleInput(_kb);
				}
				view.updateOptionString(index, _optionStringFactory.createTaxOptionString(taxRates.get(index)));
			});
			menuOptions.add(option);
		}
		ConsoleMenuOption callbackOption = new ConsoleMenuOption();
		callbackOption.setDisplayString(ExternalisedStrings.APP_OPTIONS_BACK);
		callbackOption.setListener(index -> {
			viewCallback.callback();
		});
		menuOptions.add(callbackOption);
		return view;
	}

	@Override
	public IConsoleView getEditView(Tax tax) {
		TaxForm taxForm = new TaxForm();
		taxForm.onFinishHandler((taxFrom, eligableAmount, percent, taxLimited)-> {
			tax.setTaxFrom(new BigDecimal(taxFrom));
			tax.setAmountEligableForTax(new BigDecimal(eligableAmount));
			tax.setTaxPercent(percent);
			tax.setTaxLimited(taxLimited);
			_service.save(tax);
			_inFormView = false;
			
		});
		return taxForm;
	}

}
