package com.rose.pm.material;

import com.rose.person.Patient;

public abstract class Material {
	String notation;
	Manufacturer manufacturer;
	String notice;
	Double price;
	Patient patient;

	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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
