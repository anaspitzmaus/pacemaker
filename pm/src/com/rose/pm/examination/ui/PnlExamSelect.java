package com.rose.pm.examination.ui;

import javax.swing.JList;
import javax.swing.JPanel;

import com.rose.pm.examination.Examination;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class PnlExamSelect extends JPanel {

	
	private static final long serialVersionUID = -5040572893312738019L;
	JList<? extends Examination> listExamType;
	/**
	 * Create the panel.
	 */
	public PnlExamSelect() {
		setLayout(new MigLayout("", "[][grow]", "[grow]"));
		
		JLabel lblExamList = new JLabel("lblExamList");
		lblExamList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblExamList, "cell 0 0");
		
		listExamType = new JList<Examination>();
		add(listExamType, "cell 1 0,grow");

	}

}
