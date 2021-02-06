package com.rose.person;

import java.time.LocalDate;

public abstract class Person {

	protected String surname; 
	protected String firstname;
	protected LocalDate birthday;
	protected Integer sex; // can be 0 for not known, 1 for female and 2 for male or 9 for indifferent
	protected String address;
	
	
	
	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public LocalDate getBirthday() {
		return birthday;
	}



	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}


	

	public Sex getSex() {
		switch (sex) {
		case 0:
			return Sex.NOT_KNOWN;
			
		case 1:
			return Sex.FEMALE;
			
		case 2:
			return Sex.MALE;
			
		case 9:
			return Sex.DIVERS;
		default:
			return null;
			
		}		
	}
	
	
/**
 * can be indifferent, female or male
 * 
 */
	public void setSex(Sex sex) {
		switch (sex){
		case NOT_KNOWN:
			this.sex = 0;
			break;
		case FEMALE:
			this.sex = 1;
			break;
		case MALE:
			this.sex = 2;
			break;
		case DIVERS:
			this.sex = 9;
			break;
		default:
			this.sex = null;
		}
		
	}
	
	public Integer getSexCode(){
		return this.sex;
	}
	
	public void setSexCode(int sex) {
		this.sex = sex;		
	}



	public Person(String surname, String firstname){
		this.surname = surname; 
		this.firstname = firstname;
	}
	
	
}
