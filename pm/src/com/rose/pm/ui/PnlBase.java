package com.rose.pm.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PnlBase extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2190719278722274586L;
	JTable table;
	JPanel pnlInput;
	

	/**
	 * Create the panel.
	 */
	public PnlBase() {
		setLayout(new BorderLayout(0, 0));
		
		pnlInput = new JPanel();
		add(pnlInput, BorderLayout.NORTH);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel pnlSouth = new JPanel();
		add(pnlSouth, BorderLayout.SOUTH);

	}

}
