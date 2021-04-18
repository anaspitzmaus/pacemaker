package com.rose.pm.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.pm.material.Electrode;



public class PM_Implant extends PM_Intervention {
	
	ArrayList<Electrode> electrodesImplant;
	
	public PM_Implant(HashMap<String, HashMap<String, ArrayList<String>>> studyValues) {
		super(studyValues);
		electrodesImplant = new ArrayList<Electrode>();
	}
	
	public PM_Implant() {
		super();
		electrodesImplant = new ArrayList<Electrode>();
	}

	@Override
	public void setStaff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean storeExamToDB(Integer treatmentCaseId) {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	

}
