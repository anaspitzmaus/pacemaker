package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class PnlBase extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2190719278722274586L;
	JTable table;
	JPanel pnlInput;
	JPanel pnlSouth;
	Font font;
	

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
		
		pnlSouth = new JPanel();
		pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		add(pnlSouth, BorderLayout.SOUTH);
		
		font = new Font("Tahoma", Font.PLAIN, 14);

	}
	
	protected void addManufacturerListener(ItemListener listener) {
		
	}

}
