package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;


import net.miginfocom.swing.MigLayout;

public class DlgChangeElectrodeType extends JDialog {

	
	private static final long serialVersionUID = 3356472811092247681L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNotation;
	private JTextField txtNotice;
	JSpinner spinLength;
	JLabel lblNotation;
	JLabel lblLength;
	JLabel lblFix;
	JRadioButton rdbtnAnker, rdbtnScrew;
	JLabel lblPrice;
	JFormattedTextField ftxtPrice;
	JLabel lblNotice;
	private JLabel lblMRI;
	private JCheckBox checkMRI;
	JButton okButton, cancelButton;
	
	
	
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		{
			lblNotation = new JLabel("lblNotation");
			lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNotation, "cell 0 0,alignx left");
		}
		{
			txtNotation = new JTextField();
			txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(txtNotation, "cell 1 0,growx");
			txtNotation.setColumns(10);
		}
		{
			lblLength = new JLabel("lblLength");
			lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblLength, "cell 0 1,alignx left");
		}
		{
			spinLength = new JSpinner();
			spinLength.setModel(new SpinnerNumberModel(54, 40, 70, 1));
			spinLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(spinLength, "cell 1 1");
		}
		{
			lblFix = new JLabel("lblFix");
			lblFix.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblFix, "cell 0 2,alignx left,growy");
		}
		{
			
			rdbtnAnker = new JRadioButton("rdbtnAnker");
			rdbtnAnker.setActionCommand("anchor");
			rdbtnAnker.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(rdbtnAnker, "flowx,cell 1 2");
		}
		{
			rdbtnScrew = new JRadioButton("rdbtnScrew");
			rdbtnScrew.setActionCommand("screw");
			rdbtnScrew.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(rdbtnScrew, "cell 1 2");		
			
			
		}
		{
			lblNotice = new JLabel("lblNotice\r\n");
			lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNotice, "cell 0 3,alignx left");
		}
		{
			txtNotice = new JTextField();
			txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(txtNotice, "cell 1 3,growx");
			txtNotice.setColumns(10);
		}
		{
			lblMRI = new JLabel("lblMRI");
			lblMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblMRI, "cell 0 4,alignx left");
		}
		{
			checkMRI = new JCheckBox("checkMRI");
			checkMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(checkMRI, "cell 1 4,alignx left");
		}
		{
			lblPrice = new JLabel("lblPrice");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblPrice, "cell 0 5,alignx left,aligny bottom");
		}
		{
			ftxtPrice = new JFormattedTextField();
			ftxtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(ftxtPrice, "cell 1 5,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void setLblLengthText(String txt) {
		lblLength.setText(txt);
	}
	
	protected void setLblMRIText(String txt) {
		lblMRI.setText(txt);
	}
	
	protected void setLblNotationText(String txt) {
		lblNotation.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setLblFixModeText(String txt) {
		lblFix.setText(txt);
	}
	
	protected void setLblPriceText(String txt) {
		lblPrice.setText(txt);
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
	
	protected void setPrice(Double price) {
		ftxtPrice.setValue(price);
	}
	
	protected void setMRI(Boolean mri) {
		checkMRI.setSelected(mri);
	}
	
	protected void setNotation(String notation) {
		txtNotation.setText(notation);
	}
	
	protected void setNotice(String notice) {
		txtNotice.setText(notice);
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

	protected void addPriceListener(PropertyChangeListener l) {
		ftxtPrice.addPropertyChangeListener(l);		
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

	protected void addNotationListener(DocumentListener l) {
		txtNotation.getDocument().addDocumentListener(l);		
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);		
	}

	protected void addCreateListener(ActionListener l) {
		okButton.addActionListener(l);		
	}
	
	
	

}
