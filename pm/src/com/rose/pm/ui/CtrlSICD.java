package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.AggregateType;

import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.SICDType;


public class CtrlSICD {
	CtrlPnlSICDType ctrlPnlSICDType;
	CtrlPnlSICD ctrlPnlSICD;
	CreateSICDTypeListener createSICDTypeListener;
	DeleteSICDTypeListener deleteSICDTypeListener;
	
	
	protected CtrlPnlSICDType getCtrlPnlSICDType() {
		return this.ctrlPnlSICDType;
	}
	
	protected CtrlPnlSICD getCtrlPnlSICD() {
		return this.ctrlPnlSICD;
	}
	
	public CtrlSICD() {
		initialzeControls();
		setListener();
	}
	
	protected void initialzeControls() {
		ctrlPnlSICDType = new CtrlPnlSICDType();
		ctrlPnlSICD = new CtrlPnlSICD();
	}
	
	protected void setListener() {
		createSICDTypeListener = new CreateSICDTypeListener();
		ctrlPnlSICDType.addCreateListener(createSICDTypeListener);
		ctrlPnlSICDType.addDeleteListener(deleteSICDTypeListener);
	}
	
class CreateSICDTypeListener implements ActionListener{
		AggregateType type;
		
		protected void initializeAggregateTypes() {
			type = new SICDType(ctrlPnlSICDType.getNotationListener().getNotation());
		}

		
		protected void actualizeDBAndModels() {
			if(type instanceof SICDType) {
				Integer id = null;
				try {
					id = SQL_INSERT.sicdType((SICDType) type);
				}catch (SQLIntegrityConstraintViolationException e) {
					JOptionPane.showMessageDialog(null, "Dieses SICD-Modell existiert bereits!", "Hinweis", JOptionPane.WARNING_MESSAGE);

				}catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT sicdType(SICDType sicd_Model)", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				
				if(id != null) {
					type.setId(id);
					try {
						ctrlPnlSICDType.getAggregateTypeTableModel().setAggregateTypes(SQL_SELECT.sicdTypes());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ctrlPnlSICDType.getAggregateTypeTableModel().fireTableDataChanged();
					ctrlPnlSICD.sicdTypeModel.addElement((SICDType) type);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ctrlPnlSICDType.getNotationListener().getNotation() != "" && ctrlPnlSICDType.getManufacturerModel().getSelectedItem() instanceof Manufacturer) {
				initializeAggregateTypes();
				type.setManufacturer((Manufacturer)ctrlPnlSICDType.getManufacturerModel().getSelectedItem());
				type.setNotice(ctrlPnlSICDType.getNoticeListener().getNotation());	
				
				actualizeDBAndModels();
			}			
			
		}
		
	}

	class DeleteSICDTypeListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(ctrlPnlSICDType.getTblRowSelectionListener().getRecorderTypeSelected() instanceof SICDType) {
				AggregateType type = ctrlPnlSICDType.getTblRowSelectionListener().getRecorderTypeSelected();
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					try {
						SQL_UPDATE.deleteSICDType(type);
						ctrlPnlSICDType.getAggregateTypeTableModel().aggregateTypes.remove(type);
						ctrlPnlSICDType.getAggregateTypeTableModel().fireTableDataChanged();
					}catch(SQLException ex) {
						if(ex.getErrorCode() == 1451) {
							JOptionPane.showMessageDialog(null, "Dieses SICD-Model kann nicht gelöscht werden, da hierfür bereits mindestens ein SICD eingetragen wurden!", "Hinweis", JOptionPane.WARNING_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(new JFrame(),
							"Message:\n" +  ex.getMessage() + "\n\nClass:\n" + "Fehler beim Löschen eines SICD-Models ", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
						}
					}
						
						//for all types of aggregates
					for(int i = 0; i< ctrlPnlSICD.sicdTypeModel.getSize(); i++) {
						//if notation of deleted aggregate type is same as notation of aggregate type in the ComboBoxModel
						if(ctrlPnlSICD.sicdTypeModel.getElementAt(i).getNotation().equals(type.getNotation())) {
							//remove type of aggregate from the comboBoxModel
							ctrlPnlSICD.sicdTypeModel.removeElementAt(i);
						}
					}
				}
			}
		}			
	}		

	
}
