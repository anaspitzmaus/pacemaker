package com.rose.pm.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CtrlFrmBase {
	FrmBase frame;
	CtrlPnlElectrodeType ctrlPnlElectrodeType;
	CtrlPnlElectrode ctrlPnlElectrode;
	CtrlElectrodes ctrlElectrodes;
	CtrlAggregates ctrlAggregates;
	CtrlPnlPMType ctrlPnlPMType;
	CtrlPnlPM ctrlPnlPM;
	CtrlPnlICDType ctrlPnlICDType;
	CtrlPnlICD ctrlPnlICD;
	TabChangeListener tabChangeListener;
	
	CtrlMenuBar ctrlMenuBar;
	
	protected FrmBase getFrame() {
		return this.frame;
	}
	
	public CtrlFrmBase() {
		frame = new FrmBase();
		createPanels();
		frame.getTabbedPane().add(ctrlElectrodes.getCtrlPnlElectrodeType().getPanel().getName(), ctrlElectrodes.getCtrlPnlElectrodeType().getPanel());
		frame.getTabbedPane().add(ctrlElectrodes.getCtrlPnlElectrode().getPanel().getName(), ctrlElectrodes.getCtrlPnlElectrode().getPanel());
		frame.getTabbedPane().add(ctrlAggregates.getCtrlPnlAggregatesType().getPanel().getName(), ctrlAggregates.getCtrlPnlAggregatesType().getPanel());
		frame.getTabbedPane().add(ctrlAggregates.getCtrlPnlAggregates().getPanel().getName(), ctrlAggregates.getCtrlPnlAggregates().getPanel());
		frame.getTabbedPane().add(ctrlPnlICDType.getPanel().getName(), ctrlPnlICDType.getPanel());
		frame.getTabbedPane().add(ctrlPnlICD.getPanel().getName(), ctrlPnlICD.getPanel());
		frame.setJMenuBar(ctrlMenuBar.getMenuBar());
		setListener();
		
	}
	
	private void setListener() {
		tabChangeListener = new TabChangeListener();
		frame.addTabChangeListener(tabChangeListener);
	}
	
	private void createPanels() {

		ctrlElectrodes = new CtrlElectrodes();
		ctrlAggregates = new CtrlAggregates();
//		ctrlPnlPMType = new CtrlPnlPMType();
//		ctrlPnlPM = new CtrlPnlPM();
		ctrlPnlICDType = new CtrlPnlICDType();
		ctrlPnlICD = new CtrlPnlICD();
		ctrlMenuBar = new CtrlMenuBar();
	}
	
	public void showFrame() {
		if(frame instanceof FrmBase) {
			frame.setVisible(true);
		}
	}
	
	class TabChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			
			switch (frame.getSelectedTabIndex()) {
			//Elektroden
			case 1:
				
				break;
			//Schrittmacher
			case 3:
				
				break;
			default:
				break;
			}
		}
		
	}
}
