package com.dental_surgery.models;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class which models a Procedure
 * 
 * @author Kyle Williamson R00110793
 *
 */
public class Procedure {
	
	private static int numberOfProc = 0;
	private int procNo;
	private String procName;
	private double procCost;
	
	public Procedure() { }
	
	public Procedure(String procName, double procCost) {
		numberOfProc++;
		setProcNo(numberOfProc);
		setProcName(procName);
		setProcCost(procCost);
	}
	
	// Set and Get Methods
	
	@XmlAttribute
	public void setProcNo(int procNo) {
		this.procNo = procNo;
	}
	
	public int getProcNo() {
		return procNo;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	public String getProcName() {
		return procName;
	}
	
	public static void setNumberOfProc(int numberOfProc) {
		Procedure.numberOfProc = numberOfProc;
	}

	public static int getNumberOfProc() {
		return numberOfProc;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public double getProcCost() {
		return procCost;
	}
	
	public String toString() {
		return ("Number: " + this.procNo 
				+ " Name: " + this.procName
				+ " Cost: " + this.procCost);
	}
}
