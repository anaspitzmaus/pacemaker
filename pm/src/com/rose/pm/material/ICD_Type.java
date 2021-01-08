package com.rose.pm.material;

public class ICD_Type extends AggregateType{

	Boolean atp = false;
	
	public ICD_Type(String notation) {
		super(notation);
		// TODO Auto-generated constructor stub
	}

	public Boolean getAtp() {
		return atp;
	}

	protected void setAtp(Boolean atp) {
		this.atp = atp;
	}
	
	
	
	
}
