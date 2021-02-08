package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;


public class FrmBase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7479382775386573822L;
	private JPanel contentPane;
	
	JTabbedPane tabbedPane;
	JPanel pnlStatistics;
	
	
	

	
	
	protected JTabbedPane getTabbedPane() {
		return this.tabbedPane;
	}

	protected JPanel getPnlStatistics() {
		return this.pnlStatistics;
	}
	
	protected void insertToolBar(JToolBar bar) {
		contentPane.add(bar, BorderLayout.NORTH);
	}
	/**
	 * Create the frame.
	 */
	public FrmBase() {
		super("Aggregate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
//		menuBar = new FrmMenuBar();
//		setJMenuBar(menuBar);
		
		
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
	
	protected void addTabChangeListener(ChangeListener l) {
		tabbedPane.addChangeListener(l);
	}

	protected Integer getSelectedTabIndex() {
		return tabbedPane.getSelectedIndex();
	}

}
