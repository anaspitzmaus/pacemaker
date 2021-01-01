package com.rose.pm.material;

import java.time.LocalDate;


public class Electrode{
	
	Integer resistance;
	String serialNr;
	ElectrodeModel electrodeModel;
	LocalDate expireDate; 
	String notice;
//	Examination exam;
	Integer id;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


//	public Examination getExam() {
//		return exam;
//	}
//
//
//	public void setExam(Examination exam) {
//		this.exam = exam;
//	}


	public String getNotice() {
		return notice;
	}


	public void setNotice(String notice) {
		this.notice = notice;
	}


	public LocalDate getExpireDate() {
		return expireDate;
	}


	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}


	public Integer getResistance() {
		return resistance;
	}


	public void setResistance(Integer resistance) {
		this.resistance = resistance;
	}


	public String getSerialNr() {
		return serialNr;
	}


	public void setSerialNr(String serialNr) {
		this.serialNr = serialNr;
	}


	public ElectrodeModel getElectrodeModel() {
		return electrodeModel;
	}


	protected void setElectrodeModel(ElectrodeModel electrodeModel) {
		this.electrodeModel = electrodeModel;
	}


	public Electrode(ElectrodeModel electrodeModel) {
		this.electrodeModel = electrodeModel;
	}

	
	
	
	
}
