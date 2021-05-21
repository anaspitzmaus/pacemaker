package com.rose.administration.ui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PnlDlgInsuranceWest extends JPanel {
	
	private static final long serialVersionUID = 8259057486244375186L;
	private JTextField txtNotation;
	private JTextField txtPerson;
	private JTextField txtStreet;
	private JTextField txtNr;
	private JTextField txtCity;
	private JTextField txtPostCode;
	private JTextField txtPostBox;
	JLabel lblNotation;
	JLabel lblPerson;
	JLabel lblStreet;
	JLabel lblNr;
	JLabel lblCity;
	JLabel lblPostCode;
	JLabel lblPostBox;
	JButton btnNew, btnChange, btnDelete;	
	private JScrollPane scrollPane;
	private JList listInsurance;

	/**
	 * Create the panel.
	 */
	public PnlDlgInsuranceWest() {
		setLayout(new MigLayout("", "[][grow][][grow][grow]", "[grow][][][][][][][]"));
		
		lblNotation = new JLabel("lblNotation");
		lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNotation, "cell 0 0,alignx left");
		
		txtNotation = new JTextField();
		txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtNotation, "cell 1 0,growx");
		txtNotation.setColumns(10);
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 4 0 1 8,grow");
		
		listInsurance = new JList();
		listInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(listInsurance);
		
		lblPerson = new JLabel("lblPerson");
		lblPerson.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPerson, "cell 0 1,alignx left");
		
		txtPerson = new JTextField();
		txtPerson.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPerson, "cell 1 1,growx");
		txtPerson.setColumns(10);
		
		lblStreet = new JLabel("lblStreet");
		lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblStreet, "cell 0 2,alignx left");
		
		txtStreet = new JTextField();
		txtStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtStreet, "cell 1 2,growx");
		txtStreet.setColumns(10);
		
		lblNr = new JLabel("lblNr");
		lblNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNr, "cell 2 2,alignx trailing");
		
		txtNr = new JTextField();
		txtNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtNr, "cell 3 2,growx");
		txtNr.setColumns(1);
		
		lblCity = new JLabel("lblCity");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblCity, "cell 0 3,alignx left");
		
		txtCity = new JTextField();
		txtCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtCity, "cell 1 3,growx");
		txtCity.setColumns(10);
		
		lblPostCode = new JLabel("lblPostCode");
		lblPostCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPostCode, "cell 0 4,alignx left");
		
		txtPostCode = new JTextField();
		txtPostCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPostCode, "cell 1 4,growx");
		txtPostCode.setColumns(10);
		
		lblPostBox = new JLabel("lblPostBox");
		lblPostBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPostBox, "cell 0 5,alignx left");
		
		txtPostBox = new JTextField();
		txtPostBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPostBox, "cell 1 5,growx");
		txtPostBox.setColumns(10);
		
		btnNew = new JButton("btnNew");
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnNew, "cell 0 7");
		
		btnChange = new JButton("btnChange");
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnChange, "flowx,cell 1 7");
		
		btnDelete = new JButton("btnDelete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnDelete, "cell 1 7");

	}
	
	protected void setLblNotationText(String txt) {
		this.lblNotation.setText(txt);
	}
	
	protected void setLblCityText(String txt) {
		this.lblCity.setText(txt);
	}
	
	protected void setLblPostCodeText(String txt) {
		this.lblPostCode.setText(txt);
	}
	
	protected void setLblStreetText(String txt) {
		this.lblStreet.setText(txt);
	}
	
	protected void setLblStreetNrText(String txt) {
		this.lblNr.setText(txt);
	}
	
	protected void setLblPostBoxText(String txt) {
		this.lblPostBox.setText(txt);
	}
	
	protected void setLblPersonText(String txt) {
		this.lblPerson.setText(txt);
	}
	
	protected void setBtnNewText(String txt) {
		this.btnNew.setText(txt);
	}
	
	protected void setBtnChangeText(String txt) {
		this.btnChange.setText(txt);
	}
	
	protected void setBtnDeleteText(String txt) {
		this.btnDelete.setText(txt);
	}
	
	protected String getStreet() {
		return txtStreet.getText();
	}
	
	protected String getStreetNr() {
		return txtNr.getText();
	}
	
	protected String getCity() {
		return txtCity.getText();
	}
	
	protected String getPostCode() {
		return txtPostCode.getText();
	}
	
	protected String getPostBox() {
		return txtPostBox.getText();
	}
	
	protected String getPerson() {
		return txtPerson.getText();
	}

	protected String getNotation() {
		return txtNotation.getText();
	}
	
	protected void setNotationText(String txt) {
		this.txtNotation.setText(txt);
	}
	
	protected void setCityText(String txt){
		this.txtCity.setText(txt);
	}
	
	protected void setStreetText(String txt) {
		this.txtStreet.setText(txt);
	}
	
	protected void setStreetNrText(String txt) {
		this.txtNr.setText(txt);
	}
	
	protected void setPostCodeText(String txt) {
		this.txtPostCode.setText(txt);
	}
	
	protected void setPostBoxText(String txt) {
		this.txtPostBox.setText(txt);
	}
	
	protected void setPersonText(String txt) {
		this.txtPerson.setText(txt);
	}
	
	protected void addBtnNewListener(ActionListener l) {
		this.btnNew.addActionListener(l);
	}
	
	protected void addBtnChangeListener(ActionListener l) {
		this.btnChange.addActionListener(l);
	}
	
	protected void addBtnDeleteListener(ActionListener l) {
		this.btnDelete.addActionListener(l);
	}
	
	
	
	
	
}
