package com.rose.pm.examination.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

public abstract class PnlExam extends JPanel {

	
	private static final long serialVersionUID = -3200285316524474892L;
	JTextArea textResult;
	/**
	 * Create the panel.
	 */
	public PnlExam() {
		setLayout(new BorderLayout(0, 0));
		textResult = new JTextArea();
		textResult.setText("txtResult");
		add(textResult, BorderLayout.SOUTH);

	}

}
