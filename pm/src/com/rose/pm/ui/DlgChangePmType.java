package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class DlgChangePmType extends DlgChangeType {

	private static final long serialVersionUID = 8371364718552432364L;
	private JLabel lblMRI;
	private JCheckBox checkMRI;
	private JCheckBox checkRA, checkRV, checkLV;
	private JLabel lblPmKind;
	/**
	 * Create the dialog.
	 */
	public DlgChangePmType() {
		setTitle("Schrittmachermodel bearbeiten");	
		getContentPanel().setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		{
			
			getContentPanel().add(lblNotation, "cell 0 0,alignx left");
		}
		{
			
			getContentPanel().add(txtNotation, "cell 1 0,growx");
			
		}
		{
			
			getContentPanel().add(lblNotice, "cell 0 3,alignx left");
		}
		{
			
			getContentPanel().add(txtNotice, "cell 1 3,growx");
			
		}
		
		lblPmKind = new JLabel("lblPmKind");
		lblPmKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPanel().add(lblPmKind, "cell 0 1,alignx left");
		
		checkRA = new JCheckBox("checkRA");
		checkRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPanel().add(checkRA, "flowx,cell 1 1,alignx left");
		
		checkRV = new JCheckBox("checkRV");
		checkRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPanel().add(checkRV, "cell 1 1");
		
		checkLV = new JCheckBox("checkLV");
		checkLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPanel().add(checkLV, "cell 1 1");
		
		{
			lblMRI = new JLabel("lblMRI");
			lblMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(lblMRI, "cell 0 2,alignx left");
		}
		{
			checkMRI = new JCheckBox("checkMRI");
			checkMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			getContentPanel().add(checkMRI, "cell 1 2,alignx left");
		}
		{			
			getContentPanel().add(lblPrice, "cell 0 4,alignx left,aligny bottom");
		}
		{
			
			getContentPanel().add(ftxtPrice, "cell 1 4,growx");
		}		

	}
	
	protected void setLblMRIText(String txt) {
		lblMRI.setText(txt);
	}
	
	protected void setCheckMRIValue(Boolean mri) {
		checkMRI.setSelected(mri);
	}
	
	protected void setCheckMRIText(String txt) {
		checkMRI.setText(txt);
	}
	
	protected void setLblPmKindText(String txt) {
		lblPmKind.setText(txt);
	}
	
	protected void setCheckRAValue(Boolean ra) {
		checkRA.setSelected(ra);
	}
	
	protected void setCheckRVValue(Boolean lv) {
		checkRV.setSelected(lv);
	}
	
	protected void setCheckLVValue(Boolean lv) {
		checkLV.setSelected(lv);
	}
	
	protected void setCheckRAText(String txt) {
		checkRA.setText(txt);
	}
	
	protected void setCheckRVText(String txt) {
		checkRV.setText(txt);
	}
	
	protected void setCheckLVText(String txt) {
		checkLV.setText(txt);
	}
	
	protected void addMRIListener(ActionListener l) {
		checkMRI.addActionListener(l);
	}
	
	protected void addRAListener(ActionListener l) {
		checkRA.addActionListener(l);
	}

	protected void addRVListener(ActionListener l) {
		checkRV.addActionListener(l);
	}
	
	protected void addLVListener(ActionListener l) {
		checkLV.addActionListener(l);
	}
}
