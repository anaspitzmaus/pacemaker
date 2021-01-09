package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
	
	
}
