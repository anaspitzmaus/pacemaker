package com.rose.pm.examination.ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

public class PnlExamFindings extends JPanel {

	private static final long serialVersionUID = 7212776445598839234L;
	JTextArea txtIndication;
	JLabel lblIndication;
	
	public PnlExamFindings() {
		setLayout(new MigLayout("", "[][grow]", "[]"));
		
		lblIndication = new JLabel("lblIndication");
		lblIndication.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblIndication, "cell 0 0");
		
		txtIndication = new JTextArea();
		add(txtIndication, "cell 1 0,growx,aligny top");

	}
	
	void addIndicationListener(DocumentListener l) {
		txtIndication.getDocument().addDocumentListener(l);
	}

}
