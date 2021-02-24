package com.rose.pm.material;

import java.util.ArrayList;

public abstract class MaterialType {
	String notation, notice;
	Manufacturer manufacturer;
	Double price;
	ArrayList<Material> material;
	
	
	public String getNotation() {
		return notation;
	}


	public void setNotation(String notation) {
		this.notation = notation;
	}


	public String getNotice() {
		return notice;
	}


	public void setNotice(String notice) {
		this.notice = notice;
	}


	public Manufacturer getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public ArrayList<Material> getMaterial() {
		return material;
	}


	public void setMaterial(ArrayList<Material> material) {
		this.material = material;
	}


	public MaterialType(String notation) {
		material = new ArrayList<Material>();
		this.notation = notation;
	}
}
