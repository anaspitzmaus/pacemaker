package com.rose.person;



public class Physician extends Staff {

	protected String title;
	
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
		
	


	public Physician(String alias){
		super("", "");
		this.alias = alias;
	}

	public Physician(String surname, String firstname) {
		super(surname, firstname);		
	}


	


	

}
