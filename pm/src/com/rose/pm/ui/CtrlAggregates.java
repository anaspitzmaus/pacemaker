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
import com.rose.pm.ui.Listener.PriceListener;


public class CtrlAggregates {

	CtrlPnlPMType ctrlPnlPMType;
	CtrlPnlPM ctrlPnlPM;
	CreateTypeListener createTypeListener;
	DeleteTypeListener deleteTypeListener;
	
	protected CtrlPnlPMType getCtrlPnlAggregatesType() {
		return this.ctrlPnlPMType;
	}
	
	protected CtrlPnlPM getCtrlPnlAggregates() {
		return this.ctrlPnlPM;
	}
	
	public CtrlAggregates() {
		initialzeControls();
		setListener();
	}
	
	protected void initialzeControls() {
		ctrlPnlPMType = new CtrlPnlPMType();
		ctrlPnlPM = new CtrlPnlPM();
	}
	
	protected void setListener() {
		createTypeListener = new CreateTypeListener();
		ctrlPnlPMType.addCreateListener(createTypeListener);
		deleteTypeListener = new DeleteTypeListener();
		ctrlPnlPMType.addDeleteListener(deleteTypeListener);
	}
	
	/**
	 * listener for adding a new created type of aggregates to the database
	 * adds the new type of aggregate to the ComboBox in the Aggregate panel
	 * @author user2
	 *
	 */
	class CreateTypeListener implements ActionListener{
		AggregateType model;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(ctrlPnlPMType.getNotationListener().getNotation() != "" && ctrlPnlPMType.getManufacturerModel().getSelectedItem() instanceof Manufacturer && checkElectrodes()) {
				initializeAggregate();
				model.setManufacturer((Manufacturer)ctrlPnlPMType.getManufacturerModel().getSelectedItem());
				model.setMri(ctrlPnlPMType.getMRIListener().getMRI());
				model.setRa(ctrlPnlPMType.getRAListener().getValue());
				model.setRv(ctrlPnlPMType.getRVListener().getValue());
				model.setLv(ctrlPnlPMType.getLVListener().getValue());
				model.setNotice(ctrlPnlPMType.getNoticeListener().getNotation());	
				model.setPrice(((PriceListener)ctrlPnlPMType.getPriceListener()).getPrice());
				actualizeDBAndModels();
			}			
		}
		
		protected void actualizeDBAndModels() {
			if(model instanceof AggregateType) {				
				Integer id = null;
				try {
					id = SQL_INSERT.pacemakerModel(model);
				}catch (SQLIntegrityConstraintViolationException e) {
					JOptionPane.showMessageDialog(null, "Dieses Schrittmachmodell existiert bereits!", "Hinweis", JOptionPane.WARNING_MESSAGE);

				}catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT PacemakerModel(PM_Model pmModel)", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				
				if(id != null) {
					model.setId(id);
					ctrlPnlPMType.getAggregateTypeTableModel().setAggregats(SQL_SELECT.pacemakerKinds());
					ctrlPnlPMType.getAggregateTypeTableModel().fireTableDataChanged();
					ctrlPnlPM.aggregateTypeModel.addElement(model);
				}
			}
		}
		
		protected void initializeAggregate() {
			model = new AggregateType(ctrlPnlPMType.getNotationListener().getNotation());
		}
		
		/**
		 * check if at least one Electrode was selected
		 * @return
		 */
		protected Boolean checkElectrodes() {
			if(ctrlPnlPMType.getRAListener().getValue() || ctrlPnlPMType.getRVListener().getValue() || ctrlPnlPMType.getLVListener().getValue()) {
				return true;
			}
			return false;
		}
	}
	
	class DeleteTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ctrlPnlPMType.getTblRowSelectionListener().getAggregatSelected() instanceof AggregateType) {
				AggregateType type = ctrlPnlPMType.getTblRowSelectionListener().getAggregatSelected();
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					if(SQL_UPDATE.deleteAggregatModel(type)){
						ctrlPnlPMType.getAggregateTypeTableModel().aggregates.remove(type);
						ctrlPnlPMType.getAggregateTypeTableModel().fireTableDataChanged();
						//for all types of aggregates
						for(int i = 0; i< ctrlPnlPM.aggregateTypeModel.getSize(); i++) {
							//if notation of deleted aggregate type is same as notation of aggregate type in the ComboBoxModel
							if(ctrlPnlPM.aggregateTypeModel.getElementAt(i).getNotation().equals(type.getNotation())) {
								//remove type of aggregate from the comboBoxModel
								ctrlPnlPM.aggregateTypeModel.removeElementAt(i);
							}
						}
					}
				}
			}
			
		}
		
	}
}
