package com.rose.administration.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rose.administration.AccountingType;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class DlgAccountSimple extends JDialog {

	
	private static final long serialVersionUID = -8792764250152903268L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInsurance;
	JComboBox<AccountingType> cbxAccount;
	
	
	public static void main(String[] args) {
		try {
			DlgAccountSimple dialog = new DlgAccountSimple();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAccountSimple() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		{
			JLabel lblAccount = new JLabel("Abrechnung:");
			lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblAccount, "cell 0 0,alignx left");
		}
		{
			cbxAccount = new JComboBox<AccountingType>();
			cbxAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(cbxAccount, "cell 1 0,growx");
		}
		{
			JLabel lblInsurance = new JLabel("Versicherung:");
			lblInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblInsurance, "cell 0 1,alignx left");
		}
		{
			txtInsurance = new JTextField();
			txtInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(txtInsurance, "cell 1 1,growx");
			txtInsurance.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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

}
