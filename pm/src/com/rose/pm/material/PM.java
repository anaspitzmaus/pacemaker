package com.rose.pm.material;

import java.time.LocalDate;

import com.rose.person.Patient;




public class PM {
	
	String serialNr;
	LocalDate expireDate;
//	Examination exam;
	AggregateType aggregatModel;
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



	public PM(AggregateType pmModel) {		
		this.aggregatModel = pmModel;
	}		
	
	

	public AggregateType getAggregatModel() {
		return aggregatModel;
	}
	
	public void setAggregateModel(AggregateType model) {
		this.aggregatModel = model;
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



//	public Examination getExam() {
//		return exam;
//	}
//	
//	public void setExam(Examination exam) {
//		this.exam = exam;
//	}
	
	

}
