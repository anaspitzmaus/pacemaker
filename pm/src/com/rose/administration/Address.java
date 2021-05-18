package com.rose.administration;

public class Address {
	String street, nr, city, postcode, contactperson, postbox;
	
	
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNr() {
		return nr;
	}


	public void setNr(String nr) {
		this.nr = nr;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getContactperson() {
		return contactperson;
	}


	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}	


	public String getPostbox() {
		return postbox;
	}


	public void setPostbox(String postbox) {
		this.postbox = postbox;
	}
	
	public Address(String postbox, String city, String postcode) {
		this.postbox = postbox;
		this.city = city;
		this.postcode = postcode;
	}


	public Address(String street, String nr, String city, String postCode, String contactPerson) {
		this.street = street;
		this.nr = nr;
		this.city = city;
		this.postcode = postCode;
		this.contactperson = contactPerson;
	}
	
}
