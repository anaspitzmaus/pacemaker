package com.rose.pm.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CtrlFrmBase {
	FrmBase frame;
	CtrlPnlElectrodeType ctrlPnlElectrodeType;
	CtrlPnlElectrode ctrlPnlElectrode;
	CtrlElectrodes ctrlElectrodes;
	CtrlAggregates ctrlAggregates;
	CtrlICD ctrlICD;
	CtrlPnlPMType ctrlPnlPMType;
	CtrlPnlPM ctrlPnlPM;
	CtrlPnlICDType ctrlPnlICDType;
	CtrlPnlICD ctrlPnlICD;
	CtrlPnlSICDType ctrlPnlSICDType;
	CtrlPnlSICD ctrlPnlSICD;
	CtrlPnlERType ctrlPnlERType;
	CtrlPnlER ctrlPnlER;
	CtrlER ctrlER;
	CtrlSICD ctrlSICD;
	CtrlPnlMonitorType ctrlPnlMonitorType;
	CtrlMonitors ctrlMonitors;
	TabChangeListener tabChangeListener;
	//Isynet isynet;
	CtrlTbrIsynet ctrlTbrIsynet;
	
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
		frame.getTabbedPane().add(ctrlICD.getCtrlPnlAggregatesType().getPanel().getName(), ctrlICD.getCtrlPnlAggregatesType().getPanel());
		frame.getTabbedPane().add(ctrlICD.getCtrlPnlAggregates().getPanel().getName(), ctrlICD.getCtrlPnlAggregates().getPanel());
		frame.getTabbedPane().add(ctrlER.getCtrlPnlERType().getPanel().getName(), ctrlER.getCtrlPnlERType().getPanel());
		frame.getTabbedPane().add(ctrlER.getCtrlPnlER().getPanel().getName(), ctrlER.getCtrlPnlER().getPanel());
		frame.getTabbedPane().add(ctrlSICD.getCtrlPnlSICDType().getPanel().getName(), ctrlSICD.getCtrlPnlSICDType().getPanel());
		frame.getTabbedPane().add(ctrlSICD.getCtrlPnlSICD().getPanel().getName(), ctrlSICD.getCtrlPnlSICD().getPanel());
		frame.getTabbedPane().add(ctrlMonitors.getCtrlPnlMonitorType().getPanel().getName(), ctrlMonitors.getCtrlPnlMonitorType().getPanel());
		frame.setJMenuBar(ctrlMenuBar.getMenuBar());
		frame.insertToolBar(ctrlTbrIsynet.getToolBar());
		setListener();
		
	}
	
	private void setListener() {
		tabChangeListener = new TabChangeListener();
		frame.addTabChangeListener(tabChangeListener);
	}
	
	private void createPanels() {

		ctrlElectrodes = new CtrlElectrodes();
		ctrlAggregates = new CtrlAggregates();
		ctrlICD = new CtrlICD();
		ctrlER = new CtrlER();
		ctrlSICD = new CtrlSICD();
		ctrlMonitors = new CtrlMonitors();
		ctrlMenuBar = new CtrlMenuBar();
		ctrlTbrIsynet = new CtrlTbrIsynet();
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
