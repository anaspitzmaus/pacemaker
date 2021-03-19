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
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.Material;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.Status;

public class Listener {

	/**
	 * listener for the JTextField txtNotation
	 * @author Ekkehard
	 *
	 */
	class NotationListener implements DocumentListener{
		String notation = "";
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
		
		protected Double getPrice() {
			return this.price;
		}
	}
	
	class SearchStatusListener implements ItemListener{
		Status status;
		AbstractTableModel tblModel;
		PnlBase panel;
		Class<? extends Material> materialClass;
		
		protected Status getStatus() {
			return this.status;
		}
		
		public SearchStatusListener(AbstractTableModel tblModel, Class<? extends Material> materialClass, PnlBase panel) {
			this.tblModel = tblModel;
			this.materialClass = materialClass;
			this.panel = panel;
		}
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					status = (Status) event.getItem();						
				} catch (ClassCastException e) {
					status = null;				
				}
				
				try {
					tblModel.setValueAt(status, 0, 5);
					((CtrlPnlMonitor.MonitorTblModel)tblModel).setMonitors(SQL_SELECT.monitors(null, (String) tblModel.getValueAt(0, 2), status));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tblModel.fireTableDataChanged();
				panel.setFirstRowHeight(40);
		    }
			
		}
			
	}
	
}
