package com.rose.person;

public class Nurse extends Staff{

	
	protected String alias;
	
	protected Integer id;
	
	
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Nurse(String surname, String firstname) {
		super(surname, firstname);
		
	}
	
	public Nurse(String alias){
		super("", "");
	}

}
