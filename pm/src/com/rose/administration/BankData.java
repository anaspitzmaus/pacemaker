package com.rose.administration;

import com.rose.person.Staff;

public class BankData {
	String iban, bic, institute;
	Staff staff;
	
	
	
	public String getIban() {
		return iban;
	}



	public void setIban(String iban) {
		this.iban = iban;
	}



	public String getBic() {
		return bic;
	}



	public void setBic(String bic) {
		this.bic = bic;
	}



	public String getInstitute() {
		return institute;
	}



	public void setInstitute(String institute) {
		this.institute = institute;
	}



	public Staff getStaff() {
		return staff;
	}



	public BankData(Staff staff) {
		this.staff = staff;
	}
	
	
	
}
