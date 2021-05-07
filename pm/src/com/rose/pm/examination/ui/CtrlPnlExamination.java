package com.rose.pm.examination.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.rose.pm.examination.Examination;

abstract class CtrlPnlExamination{
	
	Examination exam;	//the corresponding kind of examination
	PnlExam pnlExam; //the responding panel
	IndicationListener indicationListener;
	
	
	/**
	 * returns the corresponding examination
	 * @return
	 */
	protected Examination getExamination() {
		return this.exam;
	}
	
	/**
	 * returns the corresponding panel
	 * @return
	 */
	protected PnlExam getPanel() {
		return this.pnlExam;
	}
	
	public CtrlPnlExamination() {
		setListener();
	}
	
	private void setListener() {
		indicationListener = new IndicationListener();
		pnlExam.addIndicationListener(indicationListener);
	}
	
	/**
	 * DocumentListener for the textField displays the indication
	 * @author user2
	 *
	 */
	class IndicationListener implements DocumentListener{
		
		private String indication;
		
		public IndicationListener() {
			this.indication = "";
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			setIndicationText(e);			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setIndicationText(e);			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			setIndicationText(e);			
		}
		
		private void setIndicationText(DocumentEvent e) {
			try {
				this.indication = e.getDocument().getText(0, e.getDocument().getLength());
			} catch (BadLocationException e1) {
				this.indication = "";
			}
		}
		
		protected String getIndication() {
			return this.indication;
		}
		
	}
}
