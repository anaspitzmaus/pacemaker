package com.rose.pm.material;

public class ICD_Model extends AggregatModel{

	Boolean atp = false;
	
	public ICD_Model(String notation) {
		super(notation);
		// TODO Auto-generated constructor stub
	}

	protected Boolean getAtp() {
		return atp;
	}

	protected void setAtp(Boolean atp) {
		this.atp = atp;
	}
	
	
	
	
}
