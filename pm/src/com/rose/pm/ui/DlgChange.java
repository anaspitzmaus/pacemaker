package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import com.rose.pm.Pnl_SetDate;

import net.miginfocom.swing.MigLayout;

public class DlgChange extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4431377691587230795L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSerialNr;
	private JTextField txtNotice;
	JButton okButton, cancelButton;
	JLabel lblNotice, lblSerialNr;
	private JLabel lblPrice;
	private JFormattedTextField ftxtPrice;



	/**
	 * Create the dialog.
	 */
	public DlgChange() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		{
			lblSerialNr = new JLabel("lblSerialNr:");
			lblSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblSerialNr, "cell 0 0,alignx left");
		}
		{
			txtSerialNr = new JTextField();
			txtSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtSerialNr.setText("");
			contentPanel.add(txtSerialNr, "cell 1 0,growx");
			txtSerialNr.setColumns(10);
		}
		{
			lblNotice = new JLabel("lblNotice");
			lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNotice, "cell 0 1,alignx left");
		}
		{
			txtNotice = new JTextField();
			txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(txtNotice, "cell 1 1,growx");
			txtNotice.setColumns(10);
		}
		{
			lblPrice = new JLabel("lblPrice");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblPrice, "cell 0 2,alignx left");
		}
		{
			ftxtPrice = new JFormattedTextField();
			ftxtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(ftxtPrice, "cell 1 2,growx");
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
	
	protected void setLblSerialNrText(String txt) {
		lblSerialNr.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setSerialNrText(String txt) {
		txtSerialNr.setText(txt);
	}
	
	protected void setNoticeText(String txt) {
		txtNotice.setText(txt);
	}
	
	
	protected void placePnlDate(Pnl_SetDate pnlSetDate) {
		contentPanel.add(pnlSetDate, "cell 0 3 2 1, alignx left");
	}
	
	protected void addSerialNrListener(DocumentListener l) {
		txtSerialNr.getDocument().addDocumentListener(l);
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);
	}

	protected void addCreateListener(ActionListener createListener) {
		okButton.addActionListener(createListener);		
	}

	protected void setLblPriceText(String txt) {
		lblPrice.setText(txt);		
	}
	
	protected void setPriceFormatter(AbstractFormatterFactory dff) {
		ftxtPrice.setFormatterFactory(dff);
	}

	protected void addPriceListener(PropertyChangeListener listener) {
		ftxtPrice.addPropertyChangeListener(listener);		
	}

	protected void setPriceValue(Double price) {
		ftxtPrice.setValue(price);		
	}

	

}
