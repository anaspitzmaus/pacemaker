package com.rose.pm.ui.settings;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
/**
 * a primitive abstract class to set a path 
 * @author Administrator
 *
 */
public abstract class PnlSetPath extends JPanel {
	
	private static final long serialVersionUID = -3236609115107776133L;
	private JTextField txtPath;
	private JLabel lblPath;
	private JButton btnFileChooser;
	
	
	
	public JTextField getTxtPath() {
		return txtPath;
	}

	public JLabel getLblPath() {
		return lblPath;
	}

	public JButton getBtnFileChooser() {
		return btnFileChooser;
	}

	/**
	 * Create the panel.
	 */
	public PnlSetPath() {
		setLayout(new MigLayout("", "[][grow][]", "[]"));
		
		lblPath = new JLabel("Pfad:");
		lblPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPath, "cell 0 0,alignx trailing");
		
		txtPath = new JTextField();
		txtPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPath, "cell 1 0,growx");
		txtPath.setColumns(40);
		
		btnFileChooser = new JButton("Browse");
		btnFileChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnFileChooser, "cell 2 0");

	}
	
	public void addFileChooserListener(ActionListener l){
		btnFileChooser.addActionListener(l);
	}
	
	public void addPathDocumentListener(DocumentListener l){
		txtPath.getDocument().addDocumentListener(l);
	}
	
	

}
