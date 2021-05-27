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
	
	
	protected DlgChangeElectrodeType getDialog() {
		return dlgChangeElectrodeType;
	}



	public CtrlDlgChangeElectrodeModel(ElectrodeType eType) {
		this.electrodeType = eType;
		dlgChangeElectrodeType = new DlgChangeElectrodeType();
		g.add(dlgChangeElectrodeType.getRdbtnAnker());
		g.add(dlgChangeElectrodeType.getRdbtnScrew());
		setListener();
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
				System.out.println(price);
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
//			
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
}
