package com.rose.pm.material;

public class ElectrodeModel extends Material{

	Integer id;
	Integer length;
	Boolean mri;
	String fixMode;
	String notice;
	
	
	
	public String getNotice() {
		return notice;
	}



	public void setNotice(String notice) {
		this.notice = notice;
	}



	public String getFixMode() {
		return fixMode;
	}



	public void setFixMode(String fixMode) {
		this.fixMode = fixMode;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public ElectrodeModel(String notation) {
		super(notation);
		// TODO Auto-generated constructor stub
	}

}
