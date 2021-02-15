package com.rose.pm.material;

import java.time.LocalDate;

import com.rose.person.Patient;

public class ER extends Material{
	String serialNr;
	LocalDate expireDate;
//	Examination exam;
	ERType erType;
	Integer id;
	String notice;
	
	
	
	


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
		super("");
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



	
	

}
