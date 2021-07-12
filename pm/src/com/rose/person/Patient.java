package com.rose.person;

import com.rose.administration.Insurance;

public class Patient extends Person{
	
	public Patient(String surname, String firstname) {
		super(surname, firstname);
		//diagnoses = new ArrayList<Diagnosis>();		
	}

	protected String midname;
	protected Integer number;	//number independent on in or out patients treatment
	protected Integer height;
	protected Double weight, bsa;
	protected Integer age;
	protected Integer dbID, outID, inID;	
	protected Insurance insurance;
//	ArrayList<Diagnosis> diagnoses;
//	protected Heart heart;
	
	
//	public Heart getHeart() {
//		return heart;
//	}
//
//	public void setHeart(Heart heart) {
//		this.heart = heart;
//	}

	public Integer getOutID() {
		return outID;
	}

	public void setOutID(Integer out_ID) {
		this.outID = out_ID;
	}

	public Integer getInID() {
		return inID;
	}

	public void setInID(Integer in_ID) {
		this.inID = in_ID;
	}

	public Double getBsa() {
		return bsa;
	}

	public void setBsa(Double bsa) {
		this.bsa = bsa;
	}

	/**
	 * id as stored in database
	 * @return
	 */
	public Integer getId() {
		return dbID;
	}
	
	/**
	 * id as stored in database
	 * @param id
	 */
	public void setId(Integer id) {
		this.dbID = id;
	}

	public String getMidname() {
		return midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	/**
	 * id of the patient - independent if the patient is treated as in or out patient
	 * @return
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * 
	 * id of the patient - independent if the patient is treated as in or out patient
	 * @return
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setHeight(Integer height) {
		this.height = height;		
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getBSA() {
		return bsa;
	}

	public void setBSA(Double bsa) {
		this.bsa = bsa;
	}

	public Insurance getInsurance() {
		return this.insurance;
	}
	
//	/**
//	 * calls the method to store the patient to the database
//	 * @return
//	 */
//	public Integer storePatientToDB() throws SQLException{
//		
//			return  SQL_INSERT.Patient(this);			
//		
//		
//	}
	
	
	
	
	
	
	
	
	
	
}
