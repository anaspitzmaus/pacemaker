package com.rose.pm.material;

public abstract class Material {
	String notation;
	Manufacturer manufacturer;
	String notice;

	
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
	
	
}
