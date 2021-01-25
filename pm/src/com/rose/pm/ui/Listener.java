package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import com.rose.pm.material.Manufacturer;

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
			}
		}	
		
		protected Double getPrice() {
			return this.price;
		}
	}
	
	
}
