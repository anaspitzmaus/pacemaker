package com.rose.pm.material;

public class SICD extends Material{

	SICDType type;
	
	public SICD(SICDType sicdModel) {
		super(sicdModel);		
	}
	
	public void setSerialNr(String serialNr) {
		this.notation = serialNr;
	}
	
	public String getSerialNr() {
		return this.notation;
	}

}
