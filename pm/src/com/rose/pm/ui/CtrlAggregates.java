package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;

import com.rose.pm.material.Manufacturer;


public class CtrlAggregates {

	CtrlPnlPMType ctrlPnlPMType;
	CtrlPnlPM ctrlPnlPM;
	CreateTypeListener createTypeListener;
	
	
	protected CtrlPnlPMType getCtrlPnlAggregatesType() {
		return this.ctrlPnlPMType;
	}
	
	protected CtrlPnlPM getCtrlPnlAggregates() {
		return this.ctrlPnlPM;
	}
	
	public CtrlAggregates() {
		ctrlPnlPMType = new CtrlPnlPMType();
		ctrlPnlPM = new CtrlPnlPM();
		setListener();
	}
	
	private void setListener() {
		createTypeListener = new CreateTypeListener();
		ctrlPnlPMType.addCreateListener(createTypeListener);
		
	}
	
	/**
	 * listener for adding a new created type of aggregates to the database
	 * adds the new type of aggregate to the ComboBox in the Aggregate panel
	 * @author user2
	 *
	 */
	class CreateTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(ctrlPnlPMType.getNotationListener().getNotation() != "" && ctrlPnlPMType.getManufacturerModel().getSelectedItem() instanceof Manufacturer && checkElectrodes()) {
				AggregateType model = new AggregateType(ctrlPnlPMType.getNotationListener().getNotation());
				model.setManufacturer((Manufacturer)ctrlPnlPMType.getManufacturerModel().getSelectedItem());
				model.setMri(ctrlPnlPMType.getMRIListener().getMRI());
				model.setRa(ctrlPnlPMType.getRAListener().getValue());
				model.setRv(ctrlPnlPMType.getRVListener().getValue());
				model.setLv(ctrlPnlPMType.getLVListener().getValue());
				model.setNotice(ctrlPnlPMType.getNoticeListener().getNotation());
				SQL_INSERT.pacemakerModel(model);	
					
				ctrlPnlPMType.getAggregateTypeTableModel().setAggregats(SQL_SELECT.pacemakerKinds());
				ctrlPnlPMType.getAggregateTypeTableModel().fireTableDataChanged();
				ctrlPnlPM.aggregateTypeModel.addElement(model);
				
			}
			
			
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
}
