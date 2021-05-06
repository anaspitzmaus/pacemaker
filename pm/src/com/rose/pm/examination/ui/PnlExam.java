package com.rose.pm.examination.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

public abstract class PnlExam extends JPanel {

	
	private static final long serialVersionUID = -3200285316524474892L;
	PnlExamAdmin pnlExamAdmin;
	PnlExamFindings pnlExamFindings;
	PnlExamResult pnlExamResult;
	/**
	 * Create the panel.
	 */
	public PnlExam() {
		setLayout(new BorderLayout(0, 0));
		
		pnlExamAdmin = new PnlExamAdmin();
		add(pnlExamAdmin, BorderLayout.NORTH);
		
		pnlExamFindings = new PnlExamFindings();
		add(pnlExamFindings, BorderLayout.CENTER);
		
		pnlExamResult = new PnlExamResult();
		add(pnlExamResult, BorderLayout.SOUTH);

	}

}
