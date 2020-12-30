package com.rose.pm.material;

public class Manufacturer {
	String notation;
	String contact_person;
	String mobile;
	Integer id;
	
	
	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}


	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobil) {
		this.mobile = mobil;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Manufacturer(String notation) {
		this.notation = notation;
	}
}
