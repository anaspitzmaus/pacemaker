package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlDlgChangeElectrodeModel {
	DlgChangeElectrodeType dlgChangeElectrodeType;
	LengthListener lengthListener;
	PriceListener priceListener;
	ElectrodeType electrodeType;
	NotationListener notationListener, noticeListener;
	MRIListener mriListener;
	FixModeListener fixModeAnchorListener, fixModeScrewListener;
	ButtonGroup g;
	Listener listener;
	AbstractTableModel tblModel;
	CreateListener createListener;
	
	
	protected DlgChangeElectrodeType getDialog() {
		return dlgChangeElectrodeType;
	}



	public CtrlDlgChangeElectrodeModel(ElectrodeType eType,  AbstractTableModel model) {
		this.electrodeType = eType;
		this.tblModel = model;
		dlgChangeElectrodeType = new DlgChangeElectrodeType();
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
	
	private void setComponentText() {
		dlgChangeElectrodeType.setLblFixModeText("Fixierung:");
		dlgChangeElectrodeType.setLblLengthText("Länge:");
		dlgChangeElectrodeType.setLblMRIText("MRT-fähig:");
		dlgChangeElectrodeType.setLblNotationText("Bezeichnung:");
		dlgChangeElectrodeType.setLblNoticeText("Anmerkung:");
		dlgChangeElectrodeType.setLblPriceText("Preis:");
		dlgChangeElectrodeType.setRadioAnchorText("Anker");
		dlgChangeElectrodeType.setRadioScrewText("Schraube");
	}
	
	private void setListener() {
		lengthListener = new LengthListener(this.electrodeType.getLength());
		dlgChangeElectrodeType.addLengthListener(lengthListener);
		priceListener = new PriceListener(this.electrodeType.getPrice());
		dlgChangeElectrodeType.addPriceListener(priceListener);
		fixModeAnchorListener = new FixModeListener(this.electrodeType.getFixMode());
		dlgChangeElectrodeType.addFixModeAnchorListener(fixModeAnchorListener);
		fixModeScrewListener = new FixModeListener(this.electrodeType.getFixMode());
		dlgChangeElectrodeType.addFixModeScrewListener(fixModeAnchorListener);
		mriListener = new MRIListener(this.electrodeType.getMri());
		dlgChangeElectrodeType.addMRIListener(mriListener);
		listener = new Listener();
		notationListener = listener.new NotationListener(this.electrodeType.getNotation());
		noticeListener = listener.new NotationListener(this.electrodeType.getNotice());
		dlgChangeElectrodeType.addNotationListener(notationListener);
		dlgChangeElectrodeType.addNoticeListener(noticeListener);
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
	
	class PriceListener implements PropertyChangeListener{
		private Double price = null;
		
				
		protected Double getPrice() {
			return price;
		}

		public PriceListener(Double price) {
			this.price = price;
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			JFormattedTextField field = (JFormattedTextField)evt.getSource();
			
			if(field.getValue() != null && field.getValue().getClass() == Long.class) {
				Long l = (Long) field.getValue();
				price = l.doubleValue();
			}else if (field.getValue() != null){
				price = (Double) field.getValue();
				
			}			
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
			
//			AbstractButton abstractButton = (AbstractButton) event.getSource();
//	        boolean selected = abstractButton.getModel().isSelected();
//	        
//	        if(selected) {
//				abstractButton.setText("Anker");
//				fixMode = "Anker";
//			}else {
//				abstractButton.setText("Schraube");	
//				fixMode = "Schraube";
//			}		       	        
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
			if(notationListener.getNotation() != "") {
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
