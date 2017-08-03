package com.dental_surgery.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/** 
 * Class which models a Patient
 * 
 * @author Kyle Williamson R00110793
 *
 */
public class Patient {
	
	private static int numberOfPatients = 0;
	private int patientNo;
	private String patientName;
	private String patientAdd;
	private String patientPhone;
	private ArrayList<Payment> p_paymentList;
	private ArrayList<Procedure> p_procList;
	
	public Patient() { }

	public Patient(String patientName, String patientAdd, String patientPhone) {
		numberOfPatients++;
		setPatientNo(numberOfPatients);
		setPatientName(patientName);
		setPatientAdd(patientAdd);
		setPatientPhone(patientPhone);
		setP_procList(new ArrayList<Procedure>());
		setP_paymentList(new ArrayList<Payment>());
	}
	
	// Get and Set Methods
	
	public static void setNumberOfPatients(int numberOfPatients) {
		Patient.numberOfPatients = numberOfPatients;
	}
	
	public static int getNumberOfPatients() {
		return numberOfPatients;
	}
	
	@XmlAttribute
	public void setPatientNo(int patientNo) {
		this.patientNo = patientNo;
	}
	
	public int getPatientNo() {
		return patientNo;
	}
	
	@XmlAttribute
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	
	@XmlAttribute
	public void setPatientAdd(String patientAdd) {
		this.patientAdd = patientAdd;
	}
	
	public String getPatientAdd() {
		return patientAdd;
	}
	
	@XmlAttribute
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	
	public String getPatientPhone() {
		return patientPhone;
	}
	
	@XmlElementWrapper(name="Payments")

	@XmlElement(name="Payment")
	public void setP_paymentList(ArrayList<Payment> p_paymentList) {
		this.p_paymentList = p_paymentList;
	}

	public ArrayList<Payment> getP_paymentList() {
		return p_paymentList;
	}
	
	@XmlElementWrapper(name="Procedures")

	@XmlElement(name="Procedure")
	public void setP_procList(ArrayList<Procedure> p_procList) {
		this.p_procList = p_procList;
	}
	
	public ArrayList<Procedure> getP_procList() {
		return p_procList;
	}
	
	/**
	 * Method which loops through p_procList and totals the cost
	 * @return totalCost Total Cost of Procedures for the Patient
	 */
	public double getTotalCost() {
		double totalCost = 0;
		for(Procedure current : p_procList) {
			totalCost+= current.getProcCost();
		}
		return totalCost;
	}
	
	/**
	 * Method which loops through p_paymentList and totals the amount paid
	 * @return amountedPaid Total paid by the Patient
	 */
	public double getAmountPaid() {
		double amountPaid = 0;
		for(Payment current: p_paymentList) {
			amountPaid+= current.getPaymentAmt();
		}
		return amountPaid;
	}
	
	/**
	 * Method which returns the current amount owed by the Patient
	 * @return amountOwed current amount owed by the Patient
	 */
	public double getAmountOwed() {
		double amountOwed = 0;
		amountOwed = getTotalCost() - getAmountPaid() ;
		return amountOwed;
	}

	
	public String toString() {
		return ("Number: " + this.patientNo  
				+ " Name: " + this.patientName 
				+ " Address: " + this.patientAdd 
				+ " Phone: " + this.patientPhone
				+ " Procedures: " + this.p_procList
				+ " Payments: " + this.p_paymentList);
	}
}
