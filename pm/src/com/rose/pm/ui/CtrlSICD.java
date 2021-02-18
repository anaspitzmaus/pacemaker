package com.rose.pm.ui;


public class CtrlSICD {
	CtrlPnlSICDType ctrlPnlSICDType;
	CtrlPnlSICD ctrlPnlSICD;
	CreateTypeListener createTypeListener;
	DeleteTypeListener deleteTypeListener;
	
	protected CtrlPnlSICDType getCtrlPnlSICDType() {
		return this.ctrlPnlSICDType;
	}
	
	protected CtrlPnlSICD getCtrlPnlSICD() {
		return this.ctrlPnlSICD;
	}
	
	public CtrlSICD() {
		initialzeControls();
		setListener();
	}
	
	protected void initialzeControls() {
		ctrlPnlSICDType = new CtrlPnlSICDType();
		ctrlPnlSICD = new CtrlPnlSICD();
	}
	
	protected void setListener() {
		createTypeListener = new CreateTypeListener();
		ctrlPnlSICDType.addCreateListener(createTypeListener);
		deleteTypeListener = new DeleteTypeListener();
		ctrlPnlSICDType.addDeleteListener(deleteTypeListener);
	}
	
}
