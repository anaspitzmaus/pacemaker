package com.rose.pm.material;

public class PM extends Material{
	
	


	public PM(AggregateType pmModel) {	
		super(pmModel);		
	}		
	
	

	public String getSerialNr() {
		
		return this.notation;
	}


	public void setSerialNr(String serialNr) {
		this.notation = serialNr;
		
	}


	




//	public Examination getExam() {
//		return exam;
//	}
//	
//	public void setExam(Examination exam) {
//		this.exam = exam;
//	}
	
	

}
