package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
				System.out.println(ctrlPnlElectrodeType.getPriceListener().getPrice());
				elType.setPrice(ctrlPnlElectrodeType.getPriceListener().getPrice());
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
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					if(SQL_UPDATE.deleteElectrodeModel(elType)){
						ctrlPnlElectrodeType.getTblElectrodeModel().electrodeModels.remove(elType);
						ctrlPnlElectrodeType.getTblElectrodeModel().fireTableDataChanged();
						//for all types of electrodes
						for(int i = 0; i< ctrlPnlElectrode.electrodeTypeModel.getSize(); i++) {
							//if notation of deleted electrode type is same as notation of electrode type in the ComboBoxModel
							if(ctrlPnlElectrode.electrodeTypeModel.getElementAt(i).getNotation().equals(elType.getNotation())) {
								//remove type of electrode from the comboBoxModel
								ctrlPnlElectrode.electrodeTypeModel.removeElementAt(i);
							}
						}
					}
				}
			}
			
		}
		
	}
}
