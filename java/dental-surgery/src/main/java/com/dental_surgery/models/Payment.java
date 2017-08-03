package com.dental_surgery.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class which models a Payment
 * @author Kyle Williamson R00110793
 *
 */
public class Payment {
	
	private static int numberOfPayments = 0;
	private int paymentNo;
	private double paymentAmt;
	private Date paymentDate;	
	private boolean isPaid;
	
	public Payment() { }

	public Payment(Date paymentDate) {
		numberOfPayments++;
		setPaymentNo(numberOfPayments);
		setPaymentDate(paymentDate);
		setPaid(true);
	}
	
	// Set and Get Methods
	
	public static void setNumberOfPayments(int numberOfPayments) {
		Payment.numberOfPayments = numberOfPayments;
	}

	public static int getNumberOfPayments() {
		return numberOfPayments;
	}
	
	@XmlAttribute
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	
	public int getPaymentNo() {
		return paymentNo;
	}
	
	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
		setPaid(true);
	}

	public double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	
	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String toString() {
		return ("Number: " + this.paymentNo 
				+ " Amount: " + this.paymentAmt 
				+ " Date: " + this.paymentDate 
				+ " Paid: " + this.isPaid);
	}
}
