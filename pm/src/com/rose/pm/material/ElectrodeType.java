package com.rose.pm.material;

public class ElectrodeType extends MaterialType{

	
	Integer length;
	Boolean mri;
	String fixMode;	
	
	
	public String getFixMode() {
		return fixMode;
	}



	public void setFixMode(String fixMode) {
		this.fixMode = fixMode;
	}



	public Integer getLength() {
		return length;
	}



	public void setLength(Integer length) {
		this.length = length;
	}



	public Boolean getMri() {
		return mri;
	}



	public void setMri(Boolean mri) {
		this.mri = mri;
	}



	public ElectrodeType(String notation) {
		super(notation);
		// TODO Auto-generated constructor stub
	}



	

}
