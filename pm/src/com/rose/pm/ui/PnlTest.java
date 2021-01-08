package com.rose.pm.ui;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class PnlTest extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PnlTest() {
		setLayout(new MigLayout("", "[grow][grow]", "[]"));
		
		JComboBox comboBox = new JComboBox();
		add(comboBox, "cell 0 0,growx");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);

	}

}
