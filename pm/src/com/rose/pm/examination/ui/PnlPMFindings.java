package com.rose.pm.examination.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;



public class PnlPMFindings extends PnlExamFindings {

	
	private static final long serialVersionUID = -3673024648839085202L;
	private JTable tblElectrodes;
	private JTable tblAggregate;

	public PnlPMFindings() {
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 2 1,grow");
		
		tblElectrodes = new JTable();
		scrollPane.setViewportView(tblElectrodes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 2 2 1,grow");
		
		tblAggregate = new JTable();
		scrollPane_1.setViewportView(tblAggregate);
		

	}

}
