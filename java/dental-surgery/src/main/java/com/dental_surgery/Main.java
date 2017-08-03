package com.dental_surgery;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.dental_surgery.controllers.PatientController;
import com.dental_surgery.controllers.PaymentController;
import com.dental_surgery.controllers.ProcedureController;
import com.dental_surgery.controllers.ReportController;
import com.dental_surgery.controllers.XMLController;
import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;
import com.dental_surgery.models.Payment;
import com.dental_surgery.models.Procedure;

public class Main {
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		boolean loadSuccess = false;
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		ArrayList<Procedure> procedureList = new ArrayList<Procedure>();

		DentalSurgeryView dentalView = new DentalSurgeryView();
		XMLController xmlController = new XMLController(dentalView, patientList, procedureList);
		loadSuccess = xmlController.load();
		if(!loadSuccess) {
			JOptionPane.showMessageDialog(null, "Error loading data.");
		} else {
			PaymentController paymentController = new PaymentController(dentalView, patientList);
			ProcedureController procedureController = new ProcedureController(dentalView, patientList, procedureList);
			PatientController patientController = new PatientController(dentalView, patientList);

			ReportController reportController = new ReportController(dentalView, patientList, procedureList);
			
			dentalView.setVisible(true);
		}

		
	}
	
	/**
	 * Method that creates sample data for testing the application
	 * @param patientList ArrayList reference containing all patients
	 * @param procedures ArrayList reference containing all procedures
	 */
	public static void addTestData(ArrayList<Patient> patientList, ArrayList<Procedure> procedures) {
		Procedure basicDenture = new Procedure("Basic Denture", 100);
		Procedure crackRepair = new Procedure("Crack Repair", 50);
		Procedure examination = new Procedure("Examination", 50);
		Procedure bleach = new Procedure("Bleach", 250);
		Procedure xray = new Procedure("Full Mouth Xray", 50);
		Procedure toothRepair = new Procedure("Tooth Repair", 20);
		Procedure restoreCanal = new Procedure("Restore Root Canal", 600);
		Procedure cleaning = new Procedure("Cleaning", 70);
		
		
		procedures.add(basicDenture);
		procedures.add(crackRepair);
		procedures.add(examination);
		procedures.add(bleach);
		procedures.add(xray);
		procedures.add(toothRepair);
		procedures.add(restoreCanal);
		procedures.add(cleaning);
		
		Patient patient1 = new Patient("Kyle", "Cobh", "123456");
		Patient patient2 = new Patient("Jerome", "Blackrock", "234567");
		Patient patient3 = new Patient("Danny", "Kerry", "345678");
		Patient patient4 = new Patient("Frank", "Donegal", "456789");
		Patient patient5 = new Patient("Ron", "Underground", "567890");
		Patient patient6 = new Patient("Pat", "Cork", "678901");
		
		patient1.getP_procList().add(crackRepair);
		patient1.getP_procList().add(cleaning);
		
		patient2.getP_procList().add(basicDenture);
		patient2.getP_procList().add(xray);
		
		patient3.getP_procList().add(bleach);
		
		patient4.getP_procList().add(restoreCanal);
		
		patient5.getP_procList().add(examination);
		
		patient6.getP_procList().add(toothRepair);
		
		Payment one = new Payment(new Date());
		one.setPaymentAmt(120);
		one.setPaid(true);
		
		Payment two = new Payment(new Date());
		two.setPaymentAmt(150);
		
		Payment three = new Payment(new Date());
		three.setPaymentAmt(300);
		three.setPaid(true);
		
		Payment four = new Payment(new Date());
		four.setPaymentAmt(40);
		
		Payment five = new Payment(new Date());
		five.setPaymentAmt(19.99);
		
		patient1.getP_paymentList().add(one);
		patient2.getP_paymentList().add(two);
		patient4.getP_paymentList().add(three);
		patient5.getP_paymentList().add(four);
		patient6.getP_paymentList().add(five);
		
		patientList.add(patient1);
		patientList.add(patient2);
		patientList.add(patient3);
		patientList.add(patient4);
		patientList.add(patient5);
		patientList.add(patient6);
	}
}