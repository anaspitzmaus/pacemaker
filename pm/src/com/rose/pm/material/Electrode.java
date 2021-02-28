package com.rose.pm.material;

import java.time.LocalDate;
import java.util.Date;


public class Electrode extends Material{
	
	Integer resistance;
	
	ElectrodeType electrodeType;

	


	

//	public Examination getExam() {
//		return exam;
//	}
//
//
//	public void setExam(Examination exam) {
//		this.exam = exam;
//	}


	

	
	public Integer getResistance() {
		return resistance;
	}


	public void setResistance(Integer resistance) {
		this.resistance = resistance;
	}


	public String getSerialNr() {
		return notation;
	}


	public void setSerialNr(String serialNr) {
		this.notation = serialNr;
	}


	public ElectrodeType getElectrodeType() {
		return electrodeType;
	}


	protected void setElectrodeModel(ElectrodeType electrodeModel) {
		this.electrodeType = electrodeModel;
	}


	public Electrode(ElectrodeType electrodeModel) {
		super("");
		this.electrodeType = electrodeModel;
		
	}


	

	
	
	
	
}
