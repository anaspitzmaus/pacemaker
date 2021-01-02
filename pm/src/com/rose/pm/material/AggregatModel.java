package com.rose.pm.material;

public class AggregatModel extends Material
{	
	PM_Type type;	
	Integer id;
	Boolean ra, rv, lv;
	Boolean mri;
	
	

	public Boolean getMri() {
		return mri;
	}

	public void setMri(Boolean mri) {
		this.mri = mri;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public PM_Type getType() {
		return type;
	}


	public void setType(PM_Type type) {
		this.type = type;
	}	


	public Boolean getRa() {
		return ra;
	}

	public void setRa(Boolean ra) {
		this.ra = ra;
	}

	public Boolean getRv() {
		return rv;
	}

	public void setRv(Boolean rv) {
		this.rv = rv;
	}

	public Boolean getLv() {
		return lv;
	}

	public void setLv(Boolean lv) {
		this.lv = lv;
	}

	public AggregatModel(String notation) {
		super(notation);
		// TODO Auto-generated constructor stub
	}

	
	
	

	

}
