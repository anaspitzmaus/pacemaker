package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;



public abstract class DlgChangeType extends JDialog {

	
	private static final long serialVersionUID = -196111717122340558L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField txtNotation;
	protected JTextField txtNotice;
	protected JLabel lblNotation;
	protected JLabel lblNotice;
	protected JLabel lblPrice;
	protected JFormattedTextField ftxtPrice;
	NumberFormat paymentFormat;	
	JButton okButton;
	
	

	protected JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Create the dialog.
	 */
	public DlgChangeType() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			lblNotation = new JLabel("lblNotation");
			lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			lblNotation.setLabelFor(txtNotation);
		}
		{
			txtNotation = new JTextField();
			txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			txtNotation.setColumns(10);
		
		}
		{
			lblNotice = new JLabel("lblNotice");
			lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			lblNotice.setLabelFor(txtNotice);
		}
		{
			txtNotice = new JTextField();
			txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			txtNotice.setColumns(10);
		}
		{
			lblPrice = new JLabel("lblPrice");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			lblPrice.setLabelFor(ftxtPrice);
		}
		{
			paymentFormat = DecimalFormat.getInstance();
			paymentFormat.setMinimumFractionDigits(2);
			paymentFormat.setMaximumFractionDigits(2);
			ftxtPrice = new JFormattedTextField(paymentFormat);
			ftxtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
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
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void addNotationListener(DocumentListener l) {
		txtNotation.getDocument().addDocumentListener(l);
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);
	}
	
	protected void addPriceListener(PropertyChangeListener l) {
		ftxtPrice.addPropertyChangeListener(l);
	}
	
	protected void setLblNotationText(String txt) {
		lblNotation.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setLblPriceText(String txt) {
		lblPrice.setText(txt);
	}
	
	protected void setNotation(String notation) {
		txtNotation.setText(notation);
	}
	
	protected void setNotice(String notice) {
		txtNotice.setText(notice);
	}
	
	protected void setPrice(Double price) {
		ftxtPrice.setValue(price);
	}
	
	protected void addCreateListener(ActionListener l) {
		okButton.addActionListener(l);
	}

}
