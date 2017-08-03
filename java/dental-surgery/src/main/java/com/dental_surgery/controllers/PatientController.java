package com.dental_surgery.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;

/**
 * 
 * Class which controls all data flow into the Patient Class
 * Controls PatientJPanel View
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseController}
 */
public class PatientController extends BaseController {
	/**
	 * Constructor for PatientController Class
	 * Sends Patient names into the PatientJPanel drop down
	 * Sets up Event Listeners
	 * 
	 * @param view reference of the JFrame class which controls the GUI
	 * @param patientList reference of ArrayList of Patient objects
	 */
	public PatientController(DentalSurgeryView view, ArrayList<Patient> patientList) {
		super(view, patientList);
		getView().getPatientPanel().setDropDown(getPatientNames());
		setListeners();
		
	}
	
	public void setListeners() {
		getView().getPatientPanel().addAddBtnListener(new AddPatientListener());
		getView().getPatientPanel().addRemoveBtnListener(new RemovePatientListener());
		getView().getPatientPanel().addListBtnListener(new ListPatientListener());
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the AddPatient Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class AddPatientListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 *  
		 * Gets data from Patient form and calls {@link addPatient()}
		 * Updates all Patient dropdowns in the GUI
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				String name = getView().getPatientPanel().getNameFieldText();
				String address = getView().getPatientPanel().getAddressFieldText();
				String number = getView().getPatientPanel().getContactNoFieldText();
				if(name.equals("")|| address.equals("") || number.equals("")) {
					JOptionPane.showMessageDialog(null, "Form fields can't be empty.");
				} else {
					success = addPatient(name, address, number);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Failed adding Patient.");
					} else {
						getView().getPatientPanel().updateDropDown(name);
						getView().getProcedurePanel().updatePatientDropDown(name);
						getView().getPaymentPanel().updatePatientDropDown(name);	
					}
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
			
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the RemovePatient Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class RemovePatientListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Gets selected index from Patient drop down and removes Patient object at that index
		 * Updates all Patient dropdowns in the GUI
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				int index = getView().getPatientPanel().getDropDown().getSelectedIndex();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "No Patient to remove.");
				} else {
					getPatientList().remove(index);
					getView().getPatientPanel().getDropDown().removeItemAt(index);
					getView().getProcedurePanel().getPatientDropDown().removeItemAt(index);
					getView().getPaymentPanel().getPatientDropDown().removeItemAt(index);
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the ListPatients Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 * @Implements {@link ActionListener}
	 */
	class ListPatientListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Method generates a 2D Array which contains Patient names as well as the amount the total Procedure cost.
		 * 
		 * This is passed into the view to display in a text area
		 */
		public void actionPerformed(ActionEvent e) {
			String[][] patientDetailsList = new String[getPatientList().size()][4];
			for(int i = 0; i < getPatientList().size(); i++) {
				patientDetailsList[i][0] = String.valueOf(getPatientList().get(i).getPatientNo());
				patientDetailsList[i][1] = getPatientList().get(i).getPatientName();
				patientDetailsList[i][2] = getPatientList().get(i).getPatientAdd();
				patientDetailsList[i][3] = getPatientList().get(i).getPatientPhone();
			}
			getView().getPatientPanel().setTextArea(patientDetailsList);
		}
	}
	
	/**
	 * Method which adds a new Patient to the ArrayList
	 * 
	 * @param name name of a new Patient from the view
	 * @param address address of a new Patient from the view
	 * @param number number of a new Patient from the view
	 * @return true is successful, false if failed
	 */
	public boolean addPatient(String name, String address, String number) {
		try {
			Patient patient = new Patient(name, address, number);
			patient.setPatientPhone(number);
			getPatientList().add(patient);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
