package com.rose.pm.examination.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.rose.pm.examination.Examination;
import javax.swing.JPanel;

public class WdwExam {

	private JFrame frame;
	CtrlPnlExamAdmin ctrlPnlExamAdmin;
	JTabbedPane tabExam;
	CtrlPnlExamSelect ctrlPnlExamSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WdwExam window = new WdwExam();
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
	public WdwExam() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 758, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ctrlPnlExamAdmin = new CtrlPnlExamAdmin();
		frame.getContentPane().add(ctrlPnlExamAdmin.getPanel(), BorderLayout.NORTH);
		
		ctrlPnlExamSelect = new CtrlPnlExamSelect();
		frame.getContentPane().add(ctrlPnlExamSelect.getPanel(), BorderLayout.WEST);
		
		
	}
	
	protected void setExamPane(Examination exam) {
		
	}

}
