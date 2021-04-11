package com.rose.pm.ui.implant;

public enum StudyType {
	LeftHeartCatheter, PM_Implantation, Koronar_Diagnostisch, Peripher_Diagnostisch;
	
	
	public static StudyType switchStudyTypeByProtocol(String studyType) {
		switch (studyType) {
		case "Koronar^Diagnostisch":
			return StudyType.Koronar_Diagnostisch;
		case "Peripher^Diagnostisch":
			return StudyType.Peripher_Diagnostisch;

		default:
			return null;
		}
		
	}
}
