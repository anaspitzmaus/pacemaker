package com.rose.pm.material;

public class ERType extends MaterialType{

	Integer id;
	
	public ERType(String notation) {
		super(notation);		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
