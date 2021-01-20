package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.ui.Listener.ManufacturerListener;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlElectrodes {
	CtrlPnlElectrodeType ctrlPnlElectrodeType;
	CtrlPnlElectrode ctrlPnlElectrode;
	CreateTypeListener createTypeListener;
	DeleteTypeListener deleteTypeListener;
	
	
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
		deleteTypeListener = new DeleteTypeListener();
		ctrlPnlElectrodeType.addDeleteListener(deleteTypeListener);
	}
	
	/**
	 * listener for adding a new created type of electrodes to the database
	 * adds the new type of electrode to the ComboBoxModel in the Electrode panel
	 * @author user2
	 *
	 */
	class CreateTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(ctrlPnlElectrodeType.getNotationListener().getNotation() != "" && ctrlPnlElectrodeType.getManufacturerModel().getSelectedItem() instanceof Manufacturer) {
				ElectrodeType elType = new ElectrodeType(ctrlPnlElectrodeType.getNotationListener().getNotation());
				elType.setManufacturer((Manufacturer)ctrlPnlElectrodeType.getManufacturerModel().getSelectedItem());
				elType.setFixMode(ctrlPnlElectrodeType.getFixModeListener().getFixMode());
				elType.setLength(ctrlPnlElectrodeType.getLengthListener().getLength());
				elType.setMri(ctrlPnlElectrodeType.getMRIListener().getMRI());
				elType.setNotice(ctrlPnlElectrodeType.getNoticeListener().getNotation());
				SQL_INSERT.electrodeModel(elType);	
					
				ctrlPnlElectrodeType.getTblElectrodeModel().setElectrodeModels(SQL_SELECT.electrodeModels());
				ctrlPnlElectrodeType.getTblElectrodeModel().fireTableDataChanged();
				ctrlPnlElectrode.electrodeTypeModel.addElement(elType);
				
			}
		}
		
	}
	
	/**
	 * listener for deleting a type of electrode
	 * deletes the electrode type at the database and in the ComboBoxModel of the electrode panel
	 * @author Ekki
	 *
	 */
	class DeleteTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ctrlPnlElectrodeType.getTblRowSelectionListener().getElectrodeModelSelected() instanceof ElectrodeType) {
				ElectrodeType elType = ctrlPnlElectrodeType.getTblRowSelectionListener().getElectrodeModelSelected();
				if(SQL_UPDATE.deleteElectrodeModel(elType)){
					ctrlPnlElectrodeType.getTblElectrodeModel().electrodeModels.remove(elType);
					ctrlPnlElectrodeType.getTblElectrodeModel().fireTableDataChanged();
					ctrlPnlElectrode.electrodeTypeModel.removeElement(elType);
				}
			}
			
		}
		
	}
}
