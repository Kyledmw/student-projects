package com.tax_calculator.view.console;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.tax_calculator.controllers.ICitizenController;
import com.tax_calculator.controllers.ICitizensNetPayCompositeController;
import com.tax_calculator.controllers.ITaxationController;
import com.tax_calculator.util.general.ExternalisedStrings;
import com.tax_calculator.view.input.KBValidInputEngine;
import com.tax_calculator.view.menu.ConsoleMenu;
import com.tax_calculator.view.menu.ConsoleMenuOption;

/**
 ********************************************************************
 * @Implements CommandLineRunner
 * 
 * Bean that provides us with command line application functionality. 
 * 
 * This is the starting point to the layered application.
 * 
 * Handles the view loop, and setting up initial view objects and handlers.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
@Profile("!test")
public class CMDRunner implements CommandLineRunner {
	
	@Autowired
	private ICitizensNetPayCompositeController _citizenNetPayCompositeController;
	
	@Autowired
	private ICitizenController _citizenController;
	
	@Autowired
	private ITaxationController _taxController;
	
	@Autowired
	private KBValidInputEngine _kb;
	
	private IConsoleView _initMenu;
	private IConsoleView _currentView;

	@Override
	public void run(String... arg0) throws Exception {
		_currentView = _initMenu = new ConsoleMenu(createInitOptions());
		
		/*
		 * Main loop of the application
		 * Forces the current view object to display data and request input each loop.
		 */
		while(true) {
			_currentView.display();
			_currentView.handleInput(_kb);
		}
	}
	
	/**
	 * Create the main menu console options
	 * 
	 * @return List of ConsoleMenuOptions for the main menu
	 */
	private List<ConsoleMenuOption> createInitOptions() {
		List<ConsoleMenuOption> indexOptions = new ArrayList<>();
		
		//Option1, sets up listener to call CitizenNetPayCompositeController to retrieve the view
		ConsoleMenuOption option1 = new ConsoleMenuOption();
		option1.setDisplayString(ExternalisedStrings.APP_OPTIONS_LIST_CITIZENS);
		option1.setListener(listener -> {
			_citizenNetPayCompositeController.getView().display();
		});
		indexOptions.add(option1);
		
		//Option2, sets up listener to retrieve view from CitizenController, adds callback to change view to MainMenu on completion
		ConsoleMenuOption option2 = new ConsoleMenuOption();
		option2.setDisplayString(ExternalisedStrings.APP_OPTIONS_ADD_CITIZEN);
		option2.setListener(listener -> {
			_currentView = _citizenController.getView(() -> {
				_currentView = _initMenu;
			});
		});
		indexOptions.add(option2);
		
		//Option3, sets up listener to retrieve view from TaxController, adds callback to change view to MainMenu on completion
		ConsoleMenuOption option3 = new ConsoleMenuOption();
		option3.setDisplayString(ExternalisedStrings.APP_OPTIONS_CHANGE_TAXES);
		option3.setListener(listener -> {
			_currentView = _taxController.getView(() -> {
				_currentView = _initMenu;
			});
		});
		indexOptions.add(option3);
		
		//Option4, this is the exit option. The listener simply calls System.exit(0)
		ConsoleMenuOption option4 = new ConsoleMenuOption();
		option4.setDisplayString(ExternalisedStrings.APP_OPTIONS_EXIT);
		option4.setListener(listener -> {
			System.exit(0);
		});
		indexOptions.add(option4);
		
		return indexOptions;
	}

}
