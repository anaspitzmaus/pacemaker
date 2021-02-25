package com.rose.pm.material;

public class AggregateType extends MaterialType
{	
	PM_Kind pmKind;	
	
	Boolean ra, rv, lv;
	Boolean mri;
	
	

	public Boolean getMri() {
		return mri;
	}

	public void setMri(Boolean mri) {
		this.mri = mri;
	}

	

	

	public PM_Kind getPM_Kind() {
		return pmKind;
	}


	public void setPM_Kind(PM_Kind pmKind) {
		this.pmKind = pmKind;
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

	public AggregateType(String notation) {
		super(notation);		
	}

	

	
	
	

	

}
