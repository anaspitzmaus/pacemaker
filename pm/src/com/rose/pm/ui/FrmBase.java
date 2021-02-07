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
	private JToolBar toolBar;
	private JLabel lblIsynet;
	private JTextField txtPatient;
	private JLabel lblPatient;
	private JButton btnRefresh;
	private Component horizontalStrut;
	private JSeparator separator;
	private Component horizontalStrut_1;
	

	
	
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
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		lblIsynet = new JLabel("lblIsynet");
		lblIsynet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblIsynet);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 128, 0));
		separator.setForeground(new Color(0, 0, 0));
		toolBar.add(separator);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		lblPatient = new JLabel("lblPatient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblPatient);
		
		txtPatient = new JTextField();
		txtPatient.setHorizontalAlignment(SwingConstants.LEFT);
		txtPatient.setEditable(false);
		txtPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(txtPatient);
		txtPatient.setColumns(10);
		
		btnRefresh = new JButton("btnRefresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(btnRefresh);
	}
	
	protected void addTabChangeListener(ChangeListener l) {
		tabbedPane.addChangeListener(l);
	}

	protected Integer getSelectedTabIndex() {
		return tabbedPane.getSelectedIndex();
	}

}
