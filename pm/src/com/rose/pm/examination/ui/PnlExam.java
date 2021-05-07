package com.rose.pm.examination.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;

public abstract class PnlExam extends JPanel {

	
	private static final long serialVersionUID = -3200285316524474892L;
	
	PnlExamFindings pnlExamFindings;
	PnlExamResult pnlExamResult;
	/**
	 * Create the panel.
	 */
	public PnlExam() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		pnlExamFindings = new PnlExamFindings();
		add(pnlExamFindings, BorderLayout.CENTER);
		
		pnlExamResult = new PnlExamResult();
		add(pnlExamResult, BorderLayout.SOUTH);

	}
	
	protected void addIndicationListener(DocumentListener l) {
		pnlExamFindings.addIndicationListener(l);
	}

}
