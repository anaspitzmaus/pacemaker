package com.rose.pm.examination.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

public class PnlExamResult extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlExamResult() {
		setLayout(new BorderLayout(0, 0));
		
		JTextArea txtAreaResult = new JTextArea();
		add(txtAreaResult, BorderLayout.NORTH);
		
		JTextArea txtAreaSummary = new JTextArea();
		add(txtAreaSummary, BorderLayout.CENTER);
		
		JTextArea txtAreaRecommendation = new JTextArea();
		add(txtAreaRecommendation, BorderLayout.SOUTH);

	}

}
