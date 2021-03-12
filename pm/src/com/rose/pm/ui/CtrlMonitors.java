package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;

import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.MonitorType;


public class CtrlMonitors {
	CtrlPnlMonitorType ctrlPnlMonitorType;
	CtrlPnlMonitor ctrlPnlMonitor;
	CreateTypeListener createTypeListener;
	DeleteTypeListener deleteTypeListener;
	
	
	
	protected CtrlPnlMonitorType getCtrlPnlMonitorType() {
		return ctrlPnlMonitorType;
	}



	public CtrlMonitors() {
		ctrlPnlMonitorType = new CtrlPnlMonitorType();
		ctrlPnlMonitor = new CtrlPnlMonitor();
		setListener();
	}
	
	private void setListener() {
		createTypeListener = new CreateTypeListener();
		ctrlPnlMonitorType.addCreateListener(createTypeListener);
		deleteTypeListener = new DeleteTypeListener();
		ctrlPnlMonitorType.addDeleteListener(deleteTypeListener);
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
			if(ctrlPnlMonitorType.getNotationListener().getNotation() != "" && ctrlPnlMonitorType.getManufacturerModel().getSelectedItem() instanceof Manufacturer) {
				MonitorType monitorType = new MonitorType(ctrlPnlMonitorType.getNotationListener().getNotation());
				monitorType.setManufacturer((Manufacturer)ctrlPnlMonitorType.getManufacturerModel().getSelectedItem());
				monitorType.setNotice(ctrlPnlMonitorType.getNoticeListener().getNotation());
				monitorType.setPrice(ctrlPnlMonitorType.getPriceListener().getPrice());
				try {
					Integer id = SQL_INSERT.monitorType(monitorType);
					if(id != null) {
						monitorType.setId(id);
						ctrlPnlMonitorType.getTblModel().setMonitorTypes(SQL_SELECT.monitorTypes(null, ""));
						ctrlPnlMonitorType.getTblModel().fireTableDataChanged();
						ctrlPnlMonitor.monitorTypeModel.addElement(monitorType);
						ctrlPnlMonitorType.setFirstRowHeight(40);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						e.getMessage() + ", " + e.getErrorCode() + "Class: SQL_INSERT monitorType(monitorType) - das Monitormodel konnte nicht erstellt werden!", "SQL Exception warning",
						JOptionPane.WARNING_MESSAGE);
				}				
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
			if(ctrlPnlMonitorType.getTblRowSelectionListener().getMonitorTypeSelected() instanceof MonitorType) {
				MonitorType monitorType = ctrlPnlMonitorType.getTblRowSelectionListener().getMonitorTypeSelected();
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					try {
						if(SQL_UPDATE.deleteMonitorType(monitorType) == 1){
							ctrlPnlMonitorType.getTblModel().monitorTypes.remove(monitorType);
							ctrlPnlMonitorType.getTblModel().fireTableDataChanged();
							//for all types of electrodes
							for(int i = 0; i< ctrlPnlMonitor.monitorTypeModel.getSize(); i++) {
								//if notation of deleted electrode type is same as notation of electrode type in the ComboBoxModel
								if(ctrlPnlMonitor.monitorTypeModel.getElementAt(i).getNotation().equals(monitorType.getNotation())) {
									//remove type of electrode from the comboBoxModel
									ctrlPnlMonitor.monitorTypeModel.removeElementAt(i);
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}			
		}		
	}
}
