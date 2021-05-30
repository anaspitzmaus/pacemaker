package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ElectrodeType;


public class CtrlDlgChangeElectrodeModel extends CtrlDlgChangeType{
	DlgChangeElectrodeType dlgChangeElectrodeType;
	LengthListener lengthListener;
	ElectrodeType electrodeType;
	MRIListener mriListener;
	FixModeListener fixModeAnchorListener, fixModeScrewListener;
	ButtonGroup g;
	AbstractTableModel tblModel;
	CreateListener createListener;
	
	
	protected DlgChangeElectrodeType getDialog() {
		return dlgChangeElectrodeType;
	}



	public CtrlDlgChangeElectrodeModel(ElectrodeType eType,  AbstractTableModel model) {
		super(eType);
		this.electrodeType = eType;
		this.tblModel = model;
		dlgChangeElectrodeType = new DlgChangeElectrodeType();
		setDialog(dlgChangeElectrodeType);
		g = new ButtonGroup();
		g.add(dlgChangeElectrodeType.getRdbtnAnker());
		g.add(dlgChangeElectrodeType.getRdbtnScrew());
		dlgChangeElectrodeType.setLength(electrodeType.getLength());
		dlgChangeElectrodeType.setNotation(electrodeType.getNotation());
		dlgChangeElectrodeType.setNotice(electrodeType.getNotice());
		dlgChangeElectrodeType.setMRI(electrodeType.getMri());
		dlgChangeElectrodeType.setFixMode(electrodeType.getFixMode());
		dlgChangeElectrodeType.setPrice(electrodeType.getPrice());
		setComponentText();
		setListener();
	}
	
	protected void setComponentText() {
		super.setComponentText();
		dlgChangeElectrodeType.setLblFixModeText("Fixierung:");
		dlgChangeElectrodeType.setLblLengthText("Länge:");
		dlgChangeElectrodeType.setLblMRIText("MRT-fähig:");
		dlgChangeElectrodeType.setCheckMRIText("MRT");		
		dlgChangeElectrodeType.setRadioAnchorText("Anker");
		dlgChangeElectrodeType.setRadioScrewText("Schraube");
	}
	
	protected void setListener() {
		super.setListener();
		lengthListener = new LengthListener(this.electrodeType.getLength());
		dlgChangeElectrodeType.addLengthListener(lengthListener);
		fixModeAnchorListener = new FixModeListener(this.electrodeType.getFixMode());
		dlgChangeElectrodeType.addFixModeAnchorListener(fixModeAnchorListener);
		fixModeScrewListener = new FixModeListener(this.electrodeType.getFixMode());
		dlgChangeElectrodeType.addFixModeScrewListener(fixModeAnchorListener);
		mriListener = new MRIListener(this.electrodeType.getMri());
		dlgChangeElectrodeType.addMRIListener(mriListener);
		createListener = new CreateListener();
		dlgChangeElectrodeType.addCreateListener(createListener);
	}
	
	class LengthListener implements ChangeListener{
		Integer length;
		
		public LengthListener(Integer length) {
			this.length = length;
		}
		
		protected Integer getLength() {
			return this.length;
		}
		
		@Override
		public void stateChanged(ChangeEvent event) {
			JSpinner spinner = (JSpinner) event.getSource();
			length = (Integer) spinner.getValue();
			
		}
	}	

	
	class FixModeListener implements ActionListener{
		String fixMode = "Schraube";
		
		protected String getFixMode() {
			return this.fixMode;
		}
		
		public FixModeListener(String fixMode) {
			this.fixMode = fixMode;			
		}		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			ButtonModel model = g.getSelection();
			if(model.getActionCommand() == "screw") {
				fixMode = "Schraube";
				
			}else {
				fixMode = "Anker";
			}       	        
		}		
	}
	
	class MRIListener implements ActionListener{
		Boolean mri = false;
		
		protected Boolean getMRI() {
			return this.mri;
		}	

		public MRIListener(Boolean mri) {
			this.mri = mri;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        mri = selected;	
	        
		}			
	}
	
	class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!notationListener.getNotation().equals("")) {
				electrodeType.setNotation(notationListener.getNotation());
				electrodeType.setNotice(noticeListener.getNotation());
				electrodeType.setFixMode(fixModeAnchorListener.getFixMode());
				electrodeType.setMri(mriListener.getMRI());
				electrodeType.setLength(lengthListener.getLength());
				electrodeType.setPrice(priceListener.getPrice());
				updateDBAndTblModel();
			}		
		}
		
		
		protected void updateDBAndTblModel() {
			SQL_UPDATE.electrodeModel(electrodeType);
			
			//((ElectrodeTblModel)model).setElectrodes(SQL_SELECT.electrodes(electrode.getElectrodeType()));
			tblModel.fireTableDataChanged();
			dlgChangeElectrodeType.dispose();
		}
		
	}
}
