package com.rose.administration;

public class Insurance {
	String notation;
	Address address;
	
	
	
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}



	public Insurance(String notation) {
		this.notation = notation;
	}


	public String getNotation() {
		return this.notation;
	}
	
	
}
