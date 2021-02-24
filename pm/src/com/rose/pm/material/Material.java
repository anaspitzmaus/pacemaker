package com.rose.pm.material;

import com.rose.person.Patient;

public abstract class Material {
	String notation;
	String notice;
	Double price;
	Patient patient; //the patient, the material is provided for
	Status status; //the status of the material
	MaterialType materialType;// the type of material, the material belongs to
	
	
	
	public MaterialType getMaterialType() {
		return materialType;
	}


	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
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
	
}
