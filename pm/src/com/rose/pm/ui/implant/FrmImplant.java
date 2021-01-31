package com.rose.pm.ui.implant;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

public class FrmImplant extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6833901748613967299L;
	private JPanel contentPane;
	

	
	/**
	 * Create the frame.
	 */
	public FrmImplant() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	protected void insertPnlMain(JPanel panel) {
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
