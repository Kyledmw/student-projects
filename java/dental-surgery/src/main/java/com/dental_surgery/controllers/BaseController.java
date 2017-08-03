package com.dental_surgery.controllers;

import java.util.ArrayList;

import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;
import com.dental_surgery.models.Payment;
import com.dental_surgery.models.Procedure;

/**
 * Class which provides common functionality for all controllers
 * @author Kyle Williamson R00110793
 *
 */
public abstract class BaseController {
	
	private ArrayList<Patient> patientList;
	private ArrayList<Procedure> procedureList;
	private DentalSurgeryView view;
	
	/**
	 * Constructor for the Abstract BaseController class
	 * 
	 * @param view reference of the JFrame class which controls the GUI
	 * @param patientList reference of ArrayList of Patient objects
	 */
	public BaseController(DentalSurgeryView view, ArrayList<Patient> patientList) {
		setPatientList(patientList);
		setView(view);
	}
	
	public BaseController(DentalSurgeryView view, ArrayList<Patient> patientList, ArrayList<Procedure> procedureList) {
		setPatientList(patientList);
		setProcedureList(procedureList);
		setView(view);
	}
	
	// Get and Set Methods
	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}
	
	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	/**
	 * Method that clears patientList and adds a Patient ArrayList 
	 * @param patientList ArrayList containing Patient objects
	 */
	public void updatePatientList(ArrayList<Patient> patientList) {
		this.patientList.clear();
		this.patientList.addAll(patientList);
	}
	
	public void setProcedureList(ArrayList<Procedure> procedureList) {
		this.procedureList = procedureList;
	}
	
	public ArrayList<Procedure> getProcedureList() {
		return this.procedureList;
	}
	
	/**
	 * Method that clears procedureList and adds a Procedure ArrayList 
	 * @param procedureList ArrayList containing Procedure objects
	 */
	public void updateProcedureList(ArrayList<Procedure> procedureList) {
		this.procedureList.clear();
		this.procedureList.addAll(procedureList);
	}
	
	public void setView(DentalSurgeryView view) {
		this.view = view;
	}
	
	public DentalSurgeryView getView() {
		return view;
	}
	
	/**
	 * A method which generates an array of name properties from each Patient object in the ArrayList
	 * 
	 * @return string array of patient names
	 */
	public String[] getPatientNames() {
		String[] patients = new String[patientList.size()];
		for(int i = 0; i< patients.length; i++) {
			patients[i] = patientList.get(i).getPatientName();
		}
		return patients;
	}
	
	/**
	 * Method which generates a string array of procedure names
	 * 
	 * @return string array if successful, null if fails
	 */
	public String[] getProcedureNames() {
		String[] procedures = new String[procedureList.size()];
		for(int i = 0; i < procedures.length; i++) {
			procedures[i] = procedureList.get(i).getProcName();
		}
		return procedures;

	}
	
	/**
	 * A method that sets the static total fields in the Patient, Payment and Procedure Classes
	 * This is done based on the current size of the ArrayList containing a collection on each class.
	 */
	public void setModelTotals() {
		Patient.setNumberOfPatients(patientList.size());
		int paymentCount = 0;
		for(Patient current : patientList) {
			paymentCount += current.getP_paymentList().size();
		}
		
		Procedure.setNumberOfProc(procedureList.size());
		Payment.setNumberOfPayments(paymentCount);
	}
	
	/**
	 * Abstract Method for implementing setting up listener classes
	 */
	public abstract void setListeners();
	
}
