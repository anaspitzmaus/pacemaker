package com.rose.pm.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;

import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.ICD;
import com.rose.pm.material.ICD_Type;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.Material;
import com.rose.pm.material.MaterialType;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.PM;
import com.rose.pm.material.SICD;
import com.rose.pm.material.SICDType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.CtrlPnlElectrode.ElectrodeTblModel;

public class Listener {

	/**
	 * listener for the JTextField txtNotation
	 * @author Ekkehard
	 *
	 */
	class NotationListener implements DocumentListener{
		String notation = "";
		
		public NotationListener() {
			// TODO Auto-generated constructor stub
		}
		
		public NotationListener(String notation) {
			this.notation = notation;
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {			
			setNotation(e);	
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			setNotation(e);	
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setNotation(e);	
		}
		
		private void setNotation(DocumentEvent e) {
			try {
				notation = e.getDocument().getText(0, e.getDocument().getLength());
			} catch (BadLocationException e1) {
				notation = "";
			}
		}
		
		protected String getNotation() {
			return this.notation;
		}
		
	}
	
	class ManufacturerListener implements ItemListener{
		Manufacturer manufacturer;
				
		/**
		 * return the selected manufacturer
		 * @return
		 */
		public Manufacturer getManufacturer() {
			return manufacturer;
		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					manufacturer = (Manufacturer) event.getItem();			        
				} catch (ClassCastException e) {
					manufacturer = null;
				}
				
		       }
			
		}
	}
	
	class PriceListener implements PropertyChangeListener{
		private Double price = null;
		
		public PriceListener(Double price) {
			this.price = price;
		}
		
		public PriceListener() {
			
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
		
		protected Double getPrice() {
			return this.price;
		}
	}
	
	class SearchStatusListener implements ItemListener{
		Status status;
		AbstractTableModel tblModel;
		PnlBase panel;
		
		
		protected Status getStatus() {
			return this.status;
		}
		
		public SearchStatusListener(AbstractTableModel tblModel, PnlBase panel) {
			this.tblModel = tblModel;
			this.panel = panel;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					status = (Status) event.getItem();						
				} catch (ClassCastException e) {
					status = null;				
				}
			}else {
				status = null;		    		    	
		    }
				
			try {
				tblModel.setValueAt(status, 0, 5);
				Class<? extends Material> matClass;
				matClass = (Class<? extends Material>) tblModel.getColumnClass(0);
				if(matClass == Monitor.class) {
					((CtrlPnlMonitor.MonitorTblModel)tblModel).setMonitors(SQL_SELECT.monitors((MonitorType)tblModel.getValueAt(0,  1), (String) tblModel.getValueAt(0, 2), (Status) tblModel.getValueAt(0, 5)));
				}else if(matClass == Electrode.class) {
					((CtrlPnlElectrode.ElectrodeTblModel)tblModel).setElectrodes(SQL_SELECT.electrodes((ElectrodeType)tblModel.getValueAt(0,  1), (String) tblModel.getValueAt(0, 2), (Status) tblModel.getValueAt(0, 5)));
				}else if(matClass == ICD.class){
					((CtrlPnlICD.AggregateTblModel)tblModel).setAggregats(SQL_SELECT.icd((ICD_Type)tblModel.getValueAt(0, 1), (String)tblModel.getValueAt(0, 2), (Status)tblModel.getValueAt(0, 5)));
				}else if(matClass == PM.class) {				
					((CtrlPnlPM.AggregateTblModel)tblModel).setAggregats(SQL_SELECT.pacemakers((AggregateType)tblModel.getValueAt(0, 1), (String)tblModel.getValueAt(0, 2), (Status)tblModel.getValueAt(0, 5)));
				}else if(matClass == SICD.class) {
					((CtrlPnlSICD.SICDTblModel)tblModel).setSICDs(SQL_SELECT.sicds((SICDType)tblModel.getValueAt(0, 1), (String)tblModel.getValueAt(0, 2), (Status)tblModel.getValueAt(0, 5)));
				}
				//has to be added here for all types of material
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			if(tblModel instanceof ElectrodeTblModel) {
//				if(status == Status.Implantiert) {
//					((ElectrodeTblModel)tblModel).setColumnName(3,"Implantationsdatum");
//					tblModel.fireTableStructureChanged();
//				}
//			}
			tblModel.fireTableDataChanged();
			//panel.setFirstRowHeight(40);
			
		}
			
	}
	
//	class SearchMaterialTypeListener implements ItemListener{
//		MaterialType materialType;
//		AbstractTableModel tblModel;
//		 
//		 
//		protected MaterialType getMaterialType() {
//			return materialType;
//		}
//		 
//		public SearchMaterialTypeListener(AbstractTableModel tblModel) {
//			this.tblModel = tblModel;
//		}
//
//		@Override
//		 public void itemStateChanged(ItemEvent event) {
//			if (event.getStateChange() == ItemEvent.SELECTED) {
//				try {
//					materialType = (MaterialType) event.getItem();	
//				} catch (ClassCastException e) {
//					materialType = null;				
//				}
//			}else {
//				materialType = null;
//			}
				
//			try {
//				tblModel.setValueAt(materialType, 0, 1);
//				tblModel.setMonitors(SQL_SELECT.monitors((MonitorType) tblModel.getValueAt(0, 1), (String) tblModel.getValueAt(0, 2), (Status) tblModel.getValueAt(0, 5)));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			tblModel.fireTableDataChanged();
					
//		 }		 
//	 }
	
}
