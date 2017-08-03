package com.dental_surgery.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;
import com.dental_surgery.models.Payment;

/**
 * 
 * Class which controls all data flow into the Payment Class
 * Controls PaymentJPanel View
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseController}
 */
public class PaymentController extends BaseController{
	
	/**
	 * Constructor for PaymentController Class
	 * Sends Patient names into the PaymentJPanel Patient drop down
	 * Sends Payment amounts of the initial Patient into PaymentJPanel Payment drop down
	 * Sets up Event Listeners
	 * 
	 * @param view reference of the JFrame class which controls the GUI
	 * @param patientList reference of ArrayList of Patient objects
	 */
	public PaymentController(DentalSurgeryView view, ArrayList<Patient> patientList) {
		super(view, patientList);	
		getView().getPaymentPanel().setPatientDropDown(getPatientNames());
		getView().getPaymentPanel().setPaymentDropDown(getPaymentAmounts(0));
		setListeners();
	}
	
	public void setListeners() {
		getView().getPaymentPanel().addAddBtnListener(new AddPaymentListener());
		getView().getPaymentPanel().addRemoveBtnListener(new RemovePaymentListener());
		getView().getPaymentPanel().addListBtnListener(new ListPaymentListener());
		getView().getPaymentPanel().addPatientDropDownListener(new DropDownListener());
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the AddPayment Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class AddPaymentListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 *  
		 * Gets data from Payment form and calls {@link addPayment()}
		 * Updates the Payment drop down in the GUI
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				int patientIndex = getView().getPaymentPanel().getPatientDropDown().getSelectedIndex();
				double amount = getView().getPaymentPanel().getAmount();
				if(amount == 0) {
					JOptionPane.showMessageDialog(null, "Amount field cannot be empty or 0.");
				} else if(patientIndex == -1) {
					JOptionPane.showMessageDialog(null, "No Patient selected.");
				} else {
					success = addPayment(patientIndex, amount);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Adding Payment failed.");
					} else {
						getView().getPaymentPanel().setPaymentDropDown(getPaymentAmounts(patientIndex));	
					}
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the RemovePayment Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class RemovePaymentListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 *  
		 * Gets current selected Patient and Payment and calls {@link deletePayment()}
		 * Updates the Payment drop down in the GUI
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				int patientIndex = getView().getPaymentPanel().getPatientDropDown().getSelectedIndex();
				int index =  getView().getPaymentPanel().getPaymentDropDown().getSelectedIndex();
				if(patientIndex == -1 || index == -1) {
					JOptionPane.showMessageDialog(null, "No Patient or Payment.");
				} else {
					success = deletePayment(patientIndex, index);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Deleting Payment failed.");
					} else {
						getView().getPaymentPanel().getPaymentDropDown().removeItemAt(index);	
					}
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the ListPayments Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class ListPaymentListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Method generates a 2D Array which contains Payment Date as well as the Payment for that Date.
		 * 
		 * This is passed into the view to display in a text area
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				int patientIndex = getView().getPaymentPanel().getPatientDropDown().getSelectedIndex();
				Patient currentPatient = getPatientList().get(patientIndex);
				String patientName = currentPatient.getPatientName();
				String totalPayment = String.valueOf(currentPatient.getAmountPaid());
				String[][] paymentDateAndCostList = new String[currentPatient.getP_paymentList().size()][2];
				for(int i = 0; i < currentPatient.getP_paymentList().size(); i++) {
					paymentDateAndCostList[i][0] = currentPatient.getP_paymentList().get(i).getPaymentDate().toString();
					paymentDateAndCostList[i][1] = String.valueOf(currentPatient.getP_paymentList().get(i).getPaymentAmt());
				}
				getView().getPaymentPanel().setTextArea(patientName, paymentDateAndCostList, totalPayment);
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving item events from the Patient ComboBox Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ItemListener}
	 */
	class DropDownListener implements ItemListener {
		
		/**
		 * Implemented abstract method which is executed when an item event is received
		 * 
		 * Method that changes Payment drop down based on which Patient is selected in the Patient drop down
		 */
		public void itemStateChanged(ItemEvent e) {
			try {
				int patientIndex = getView().getPaymentPanel().getPatientDropDown().getSelectedIndex();
				if(patientIndex == -1) {
					JOptionPane.showMessageDialog(null, "No Patient selected.");
					getView().getPaymentPanel().getPaymentDropDown().removeAllItems();
				} else {
					getView().getPaymentPanel().setPaymentDropDown(getPaymentAmounts(patientIndex));	
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
			
		}
		
	}
	
	/**
	 * Method which adds a Payment to the Patient at index
	 * 
	 * @param patientIndex index of the Patient selected from the Patient drop down
	 * @param amount Payment amount entered in GUI text field
	 * @return true if successful, false if failed
	 */
	public boolean addPayment(int patientIndex, double amount) {
		Patient current = getPatientList().get(patientIndex);
		if(current != null) {
			Payment newPayment =  new Payment(new Date());
			newPayment.setPaymentAmt(amount);
			current.getP_paymentList().add(newPayment);		
			return true;
		}
		return false;
	}
	
	/**
	 * Method which deletes a Payment selected in the Payment drop down for the selected Patient
	 *
	 * @param patientIndex index of the Patient selected in the Patient drop down
	 * @param index index of the Payment selected in the Payment drop down
	 * @return true if successful, false if failed
	 */
	public boolean deletePayment(int patientIndex, int index) {
		boolean success = false;
		try {
			Patient patient = getPatientList().get(patientIndex);
			if(patient != null) {
				patient.getP_paymentList().remove(index);
				success = true;
			}
			return success;	
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which generates a String array of Payment Amounts for the currently selected Patient
	 * 
	 * @param patientIndex index of the currently selected Patient 
	 * @return payments String array if successful, null if fails
	 */
	public String[] getPaymentAmounts(int patientIndex) {
		try {
			String[] payments = new String[getPatientList().get(patientIndex).getP_paymentList().size()];
			for(int i = 0; i< payments.length; i++) {
				payments[i] = String.valueOf(getPatientList().get(patientIndex).getP_paymentList().get(i).getPaymentAmt());
			}
			return payments;
		} catch(Exception e) {
			return null;
		}
	}
	
}
