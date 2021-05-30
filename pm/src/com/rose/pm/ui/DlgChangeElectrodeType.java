package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;

import javax.swing.JLabel;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import javax.swing.SpinnerNumberModel;

import javax.swing.event.ChangeListener;



import net.miginfocom.swing.MigLayout;

public class DlgChangeElectrodeType extends DlgChangeType {

	
	private static final long serialVersionUID = 3356472811092247681L;
		
	JSpinner spinLength;
	JLabel lblLength;
	JLabel lblFix;
	JRadioButton rdbtnAnker, rdbtnScrew;
	private JLabel lblMRI;
	private JCheckBox checkMRI;
	
	protected JRadioButton getRdbtnAnker() {
		return rdbtnAnker;
	}

	protected JRadioButton getRdbtnScrew() {
		return rdbtnScrew;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgChangeElectrodeType dialog = new DlgChangeElectrodeType();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgChangeElectrodeType() {
		setTitle("Elektrodenmodel bearbeiten");		
		
		
		getContentPanel().setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		{
			
			getContentPanel().add(lblNotation, "cell 0 0,alignx left");
		}
		{
			
			getContentPanel().add(txtNotation, "cell 1 0,growx");
			
		}
		{
			lblLength = new JLabel("lblLength");
			lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(lblLength, "cell 0 1,alignx left");
		}
		{
			spinLength = new JSpinner();
			spinLength.setModel(new SpinnerNumberModel(54, 40, 70, 1));
			spinLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(spinLength, "cell 1 1");
		}
		{
			lblFix = new JLabel("lblFix");
			lblFix.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(lblFix, "cell 0 2,alignx left,growy");
		}
		{
			
			rdbtnAnker = new JRadioButton("rdbtnAnker");
			rdbtnAnker.setActionCommand("anchor");
			rdbtnAnker.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(rdbtnAnker, "flowx,cell 1 2");
		}
		{
			rdbtnScrew = new JRadioButton("rdbtnScrew");
			rdbtnScrew.setActionCommand("screw");
			rdbtnScrew.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(rdbtnScrew, "cell 1 2");		
			
			
		}
		{
			
			getContentPanel().add(lblNotice, "cell 0 3,alignx left");
		}
		{
			
			getContentPanel().add(txtNotice, "cell 1 3,growx");
			
		}
		{
			lblMRI = new JLabel("lblMRI");
			lblMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(lblMRI, "cell 0 4,alignx left");
		}
		{
			checkMRI = new JCheckBox("checkMRI");
			checkMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(checkMRI, "cell 1 4,alignx left");
		}
		{
			
			getContentPanel().add(lblPrice, "cell 0 5,alignx left,aligny bottom");
		}
		{
			
			getContentPanel().add(ftxtPrice, "cell 1 5,growx");
		}
		
	}
	
	protected void setLblLengthText(String txt) {
		lblLength.setText(txt);
	}
	
	protected void setLblMRIText(String txt) {
		lblMRI.setText(txt);
	}
	
	protected void setCheckMRIText(String txt) {
		checkMRI.setText(txt);
	}	
	
	protected void setLblFixModeText(String txt) {
		lblFix.setText(txt);
	}	
	
	
	protected void setRadioAnchorText(String txt) {
		rdbtnAnker.setText(txt);
	}
	
	protected void setRadioScrewText(String txt) {
		rdbtnScrew.setText(txt);
	}
	
	protected void setLength(Integer l) {
		spinLength.setValue(l);
	}
	
	protected void setMRI(Boolean mri) {
		checkMRI.setSelected(mri);
	}	
	
	protected void setFixMode(String fixMode) {
		if(fixMode.equals("Anker")) {
			rdbtnAnker.setSelected(true);
		}else {
			rdbtnScrew.setSelected(true);
		}
	}

	protected void addLengthListener(ChangeListener l) {
		spinLength.addChangeListener(l);		
	}

	protected void addFixModeAnchorListener(ActionListener l) {
		rdbtnAnker.addActionListener(l);		
	}
	
	protected void addFixModeScrewListener(ActionListener l) {
		rdbtnScrew.addActionListener(l);
	}

	protected void addMRIListener(ActionListener l) {
		checkMRI.addActionListener(l);		
	}	

	
	
	

}
