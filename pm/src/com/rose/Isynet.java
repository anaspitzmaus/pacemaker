package com.rose;

import com.rose.person.Patient;

public class Isynet {

	public static Patient getActualPatient() {
		Patient patient = new Patient("Test", "Fritz");
		return patient;
	}
}
