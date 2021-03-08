package com.rose.pm.material;

public class Monitor extends Material {

	public Monitor(MaterialType type) {
		super(type);		
	}
	
	public String getSerialNr() {
		return this.notation;
	}
	
	public void setSerialNr(String s) {
		this.notation = s;
	}

}
