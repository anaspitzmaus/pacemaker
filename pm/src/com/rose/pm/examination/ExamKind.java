package com.rose.pm.examination;

import java.util.EnumMap;

public class ExamKind {
	
	
	EnumMap<E_ExamKinds, Class<?>> enumMapExamKinds;
	
	
	public EnumMap<E_ExamKinds, Class<?>> getEnumMapExamKinds() {
		return enumMapExamKinds;
	}


	public ExamKind() {
		enumMapExamKinds = new EnumMap<>(E_ExamKinds.class);
		enumMapExamKinds.put(E_ExamKinds.PM_Implantation, PM_Implant.class);
		enumMapExamKinds.put(E_ExamKinds.PM_Change, PM_Change.class);
	}

}
