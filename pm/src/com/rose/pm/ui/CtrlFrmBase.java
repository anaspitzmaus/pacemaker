package com.rose.pm.ui;

public class CtrlFrmBase {
	FrmBase frame;
	CtrlPnlElectrodeType ctrlPnlElectrodeType;
	CtrlPnlElectrode ctrlPnlElectrode;
	CtrlPnlPMType ctrlPnlPMType;
	CtrlPnlPM ctrlPnlPM;
	
	protected FrmBase getFrame() {
		return this.frame;
	}
	
	public CtrlFrmBase() {
		frame = new FrmBase();
		createPanels();
		frame.getTabbedPane().add(ctrlPnlElectrodeType.getPanel().getName(), ctrlPnlElectrodeType.getPanel());
		frame.getTabbedPane().add(ctrlPnlElectrode.getPanel().getName(), ctrlPnlElectrode.getPanel());
		frame.getTabbedPane().add(ctrlPnlPMType.getPanel().getName(), ctrlPnlPMType.getPanel());
		frame.getTabbedPane().add(ctrlPnlPM.getPanel().getName(), ctrlPnlPM.getPanel());
	}
	
	private void createPanels() {
		ctrlPnlElectrodeType = new CtrlPnlElectrodeType();
		ctrlPnlElectrode = new CtrlPnlElectrode();
		ctrlPnlPMType = new CtrlPnlPMType();
		ctrlPnlPM = new CtrlPnlPM();
	}
	
	public void showFrame() {
		if(frame instanceof FrmBase) {
			frame.setVisible(true);
		}
	}
}
