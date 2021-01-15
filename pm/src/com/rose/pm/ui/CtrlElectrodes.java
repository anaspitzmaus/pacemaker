package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.ui.Listener.ManufacturerListener;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlElectrodes {
	CtrlPnlElectrodeType ctrlPnlElectrodeType;
	CtrlPnlElectrode ctrlPnlElectrode;
	CreateTypeListener createTypeListener;
	
	
	protected CtrlPnlElectrodeType getCtrlPnlElectrodeType() {
		return this.ctrlPnlElectrodeType;
	}
	
	protected CtrlPnlElectrode getCtrlPnlElectrode() {
		return this.ctrlPnlElectrode;
	}
	
	public CtrlElectrodes() {
		ctrlPnlElectrodeType = new CtrlPnlElectrodeType();
		ctrlPnlElectrode = new CtrlPnlElectrode();
		setListener();
	}
	
	private void setListener() {
		createTypeListener = new CreateTypeListener();
		ctrlPnlElectrodeType.addCreateListener(createTypeListener);
		
	}
	
	class CreateTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(ctrlPnlElectrodeType.getNotationListener().getNotation() != "" && ctrlPnlElectrodeType.getManufacturerModel().getSelectedItem() instanceof Manufacturer) {
				ElectrodeType model = new ElectrodeType(ctrlPnlElectrodeType.getNotationListener().getNotation());
				model.setManufacturer((Manufacturer)ctrlPnlElectrodeType.getManufacturerModel().getSelectedItem());
				model.setFixMode(ctrlPnlElectrodeType.getFixModeListener().getFixMode());
				model.setLength(ctrlPnlElectrodeType.getLengthListener().getLength());
				model.setMri(ctrlPnlElectrodeType.getMRIListener().getMRI());
				model.setNotice(ctrlPnlElectrodeType.getNoticeListener().getNotation());
				SQL_INSERT.electrodeModel(model);	
					
				ctrlPnlElectrodeType.getTblElectrodeModel().setElectrodeModels(SQL_SELECT.electrodeModels());
				ctrlPnlElectrodeType.getTblElectrodeModel().fireTableDataChanged();
				
			}
		}
		
	}
}
