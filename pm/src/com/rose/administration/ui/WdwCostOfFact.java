package com.rose.administration.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.Button;
import javax.swing.JMenuItem;

public class WdwCostOfFact {

	private JFrame frame;
	private JTable tblCases;
	CtrlPnlWest ctrlPnlWest;
	private JMenuBar menuBar;
	private JMenuItem mitem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WdwCostOfFact window = new WdwCostOfFact();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WdwCostOfFact() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel pnlSouth = new JPanel();
		frame.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		
		ctrlPnlWest = new CtrlPnlWest();
		frame.getContentPane().add(ctrlPnlWest.getPanel(), BorderLayout.WEST);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tblCases = new JTable();
		scrollPane.setViewportView(tblCases);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mitem = new JMenuItem("Edit");
		menuBar.add(mitem);
	}

}
