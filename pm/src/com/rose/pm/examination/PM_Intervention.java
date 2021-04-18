package com.rose.pm.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.pm.material.AggregateType;

public abstract class PM_Intervention extends Examination{

	protected AggregateType pm;
	
	
	public PM_Intervention(HashMap<String, HashMap<String, ArrayList<String>>> studyValues) {
		super(studyValues);
		// TODO Auto-generated constructor stub
	}
	
	public PM_Intervention() {
		super();
	}

	
}
