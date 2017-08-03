package com.dental_surgery.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class which wraps the Patient and Procedure ArrayList to effectively save in XML
 * 
 * @author Kyle Williamson R00110793
 *
 */
@XmlRootElement(namespace="https://github.com/Kyledmw")
public class ProcAndPatientListWrapper {
	
	private ArrayList<Patient> patientList;
	private ArrayList<Procedure> procedureList;

	public ProcAndPatientListWrapper() { }
	
	public ProcAndPatientListWrapper(ArrayList<Patient> patientList, ArrayList<Procedure> procedureList) {
		this.patientList = patientList;
		this.procedureList = procedureList;
	}
	
	// Set and Get Methods
	@XmlElementWrapper(name="Procedures")

	@XmlElement(name="Procedure")
	public void setProcedureList(ArrayList<Procedure> procedureList) {
		this.procedureList = procedureList;
	}
	
	public ArrayList<Procedure> getProcedureList() {
		return procedureList;
	}
	
	@XmlElementWrapper(name="Patients")

	@XmlElement(name="Patient")
	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}
	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

}
