package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ERType;
import com.rose.pm.material.Manufacturer;


public class CtrlER {

	CtrlPnlERType ctrlPnlERType;
	CtrlPnlER ctrlPnlER;
	CreateTypeListener createTypeListener;
	DeleteTypeListener deleteTypeListener;
	
	protected CtrlPnlERType getCtrlPnlERType() {
		return this.ctrlPnlERType;
	}
	
	protected CtrlPnlER getCtrlPnlER() {
		return this.ctrlPnlER;
	}
	
	public CtrlER() {
		initializeControls();
		setListener();
	}
	
	protected void initializeControls() {
		ctrlPnlERType = new CtrlPnlERType();
		ctrlPnlER = new CtrlPnlER();
	}
	
	protected void setListener() {
		createTypeListener = new CreateTypeListener();
		ctrlPnlERType.addCreateListener(createTypeListener);
		deleteTypeListener = new DeleteTypeListener();
		ctrlPnlERType.addDeleteListener(deleteTypeListener);
	}
	
	/**
	 * listener for adding a new created type of event recorder to the database
	 * adds the new type of event recorder to the ComboBox in the ER panel
	 * @author user2
	 *
	 */
	class CreateTypeListener implements ActionListener{
		ERType model;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(ctrlPnlERType.getNotationListener().getNotation() != "" && ctrlPnlERType.getManufacturerModel().getSelectedItem() instanceof Manufacturer) {
				initializeRecorders();
				model.setManufacturer((Manufacturer)ctrlPnlERType.getManufacturerModel().getSelectedItem());
				model.setNotice(ctrlPnlERType.getNoticeListener().getNotation());	
				
				actualizeDBAndModels();
			}			
		}
		
		protected void actualizeDBAndModels() {
			if(model instanceof ERType) {
				Integer id = null;
				try {
					id = SQL_INSERT.eventRecorderType(model);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(),
				    e1.getErrorCode() + ": "+ e1.getMessage()+ "/n/n Class: Das Eventrecordermodel konnten nicht in die Datenbank eingefügt werden!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
				}	
				if(id != null) {
					model.setId(id);		
					try {
						ctrlPnlERType.getERTypeTableModel().setRecorders(SQL_SELECT.eventRecorderTypes());
						ctrlPnlERType.getERTypeTableModel().fireTableDataChanged();
						ctrlPnlER.erTypeModel.addElement(model);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(new JFrame(),
							    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: Modelle der Eventrecorder konnten nicht abgefragt werden!", "SQL Exception warning",
							    JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
		}
		
		protected void initializeRecorders() {
			model = new ERType(ctrlPnlERType.getNotationListener().getNotation());
		}
		
		
	}
	
	class DeleteTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ctrlPnlERType.getTblRowSelectionListener().getRecorderTypeSelected() instanceof ERType) {
				ERType type = ctrlPnlERType.getTblRowSelectionListener().getRecorderTypeSelected();
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					try {
						SQL_UPDATE.deleteEventRecorderType(type);
						ctrlPnlERType.getERTypeTableModel().recordTypes.remove(type);
						ctrlPnlERType.getERTypeTableModel().fireTableDataChanged();
					}catch(SQLException ex) {
						if(ex.getErrorCode() == 1451) {
							JOptionPane.showMessageDialog(null, "Dieses Eventrekordermodel kann nicht gelöscht werden, da hierfür bereits Eventrekorder eingetragen wurden!", "Hinweis", JOptionPane.WARNING_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(new JFrame(),
							"Message:\n" +  ex.getMessage() + "\n\nClass:\n" + "Fehler beim Löschen eines Eventrekordermodels ", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
						}
					}
						
						//for all types of aggregates
						for(int i = 0; i< ctrlPnlER.erTypeModel.getSize(); i++) {
							//if notation of deleted aggregate type is same as notation of aggregate type in the ComboBoxModel
							if(ctrlPnlER.erTypeModel.getElementAt(i).getNotation().equals(type.getNotation())) {
								//remove type of aggregate from the comboBoxModel
								ctrlPnlER.erTypeModel.removeElementAt(i);
							}
						}
					}
				}
			}			
		}		
	
}
