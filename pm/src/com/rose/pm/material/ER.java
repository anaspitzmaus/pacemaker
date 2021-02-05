package com.rose.pm.material;

import java.time.LocalDate;

import com.rose.person.Patient;

public class ER {
	String serialNr;
	LocalDate expireDate;
//	Examination exam;
	ERType erType;
	Integer id;
	String notice;
	Status status;
	Patient patient;
	
	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public String getNotice() {
		return notice;
	}



	public void setNotice(String notice) {
		this.notice = notice;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public ER(ERType erType) {		
		this.erType = erType;
	}		
	
	

	public ERType getRecorderType() {
		return erType;
	}
	
	public void setERType(ERType type) {
		this.erType = type;
	}


	public String getSerialNr() {
		
		return this.serialNr;
	}


	public void setSerialNr(String serialNr) {
		this.serialNr = serialNr;
		
	}


	public LocalDate getExpireDate() {
		return this.expireDate;
	}


	public void setExpireDate(LocalDate date) {
		this.expireDate = date;
		
	}



	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

}
