package com.rose.pm.examination.ui;


import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PnlPMImplant extends PnlExam {

	
	private static final long serialVersionUID = 3554468159014945072L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PnlPMImplant() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

}
