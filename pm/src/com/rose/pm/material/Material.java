package com.rose.pm.material;

import java.time.LocalDate;

import com.rose.person.Patient;

public abstract class Material {
	String notation;
	String notice;
	Double price;
	Patient patient; //the patient, the material is provided or implanted
	Status status; //the status of the material
	MaterialType materialType;// the type of material, the material belongs to
	Integer id;
	LocalDate expireDate;
	
	
	
	public MaterialType getMaterialType() {
		return materialType;
	}


	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	
	public LocalDate getExpireDate() {
		return this.expireDate;
	}


	public void setExpireDate(LocalDate date) {
		this.expireDate = date;		
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public String getNotation() {
		return notation;
	}


	protected void setNotation(String notation) {
		this.notation = notation;
	}


	public Material(String notation) {
		this.notation = notation;
	}
	
	public Material(MaterialType type) {
		this.materialType = type;
	}


	public String getNotice() {
		return notice;
	}


	public void setNotice(String notice) {
		this.notice = notice;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
