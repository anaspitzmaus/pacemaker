package com.rose.pm.ui;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import java.awt.Font;


public class FrmBase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7479382775386573822L;
	private JPanel contentPane;
	JMenuBar menuBar;
	JTabbedPane tabbedPane;
	JPanel pnlStatistics;

	
	
	protected JTabbedPane getTabbedPane() {
		return this.tabbedPane;
	}

	protected JPanel getPnlStatistics() {
		return this.pnlStatistics;
	}
	/**
	 * Create the frame.
	 */
	public FrmBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		pnlStatistics = new JPanel();
		contentPane.add(pnlStatistics, BorderLayout.SOUTH);
	}

}
