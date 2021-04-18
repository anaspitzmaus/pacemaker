package com.rose.pm.examination.ui;

import com.rose.pm.examination.Examination;

abstract class CtrlPnlExamination{
	
	Examination exam;	//the corresponding kind of examination
	PnlExam pnlExam; //the responding panel
	
	/**
	 * returns the corresponding examination
	 * @return
	 */
	protected Examination getExamination() {
		return this.exam;
	}
	
	/**
	 * returns the corresponding panel
	 * @return
	 */
	protected PnlExam getPanel() {
		return this.pnlExam;
	}
}
