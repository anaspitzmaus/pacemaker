package com.rose.pm.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

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
}
