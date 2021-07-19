package com.rose.administration.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.rose.administration.AccountingType;
import com.rose.administration.ui.CtrlDlgAccountSimple.AccountListener;
import com.rose.pm.examination.E_ExamKinds;

import net.miginfocom.swing.MigLayout;

public class DlgAccountSimple extends JDialog {

	
	private static final long serialVersionUID = -8792764250152903268L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInsurance;
	JComboBox<AccountingType> cbxAccount;
	private JTextField txtExamNr;
	JLabel lblAccount;
	JLabel lblInsurance;
	JLabel lblExamKind;
	JComboBox<E_ExamKinds> cbxExamKind;
	JLabel lblDate;
	JLabel lblExamNr;
	JButton okButton;
	JButton cancelButton;
	
	
	
	protected JTextField getTxtInsurance() {
		return txtInsurance;
	}

	protected JComboBox<AccountingType> getCbxAccount() {
		return cbxAccount;
	}

	protected JTextField getTxtExamNr() {
		return txtExamNr;
	}

	protected JLabel getLblAccount() {
		return lblAccount;
	}

	protected JLabel getLblInsurance() {
		return lblInsurance;
	}

	protected JLabel getLblExamKind() {
		return lblExamKind;
	}

	protected JComboBox<E_ExamKinds> getCbxExamKind() {
		return cbxExamKind;
	}

	protected JLabel getLblDate() {
		return lblDate;
	}

	protected JLabel getLblExamNr() {
		return lblExamNr;
	}

	protected JButton getOkButton() {
		return okButton;
	}

	protected JButton getCancelButton() {
		return cancelButton;
	}

	
	/**
	 * Create the dialog.
	 */
	public DlgAccountSimple() {
		setTitle("Administration");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		{
			lblAccount = new JLabel("Abrechnung:");
			lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblAccount, "cell 0 0,alignx left");
		}
		{
			cbxAccount = new JComboBox<AccountingType>();
			cbxAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(cbxAccount, "cell 1 0,growx");
		}
		{
			lblInsurance = new JLabel("Versicherung:");
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
			lblExamKind = new JLabel("lblExamKind");
			lblExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblExamKind, "cell 0 2,alignx left");
		}
		{
			cbxExamKind = new JComboBox<E_ExamKinds>();
			cbxExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(cbxExamKind, "cell 1 2,growx");
		}
		{
			lblDate = new JLabel("lblDate");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblDate, "cell 0 3,alignx left");
		}
		{
			lblExamNr = new JLabel("lblExamNr");
			lblExamNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblExamNr, "cell 0 4,alignx left");
		}
		{
			txtExamNr = new JTextField();
			txtExamNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(txtExamNr, "cell 1 4,growx");
			txtExamNr.setColumns(10);
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
	
	protected void addOKListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}
	
	protected void addDatePanel(JPanel pnl) {
		contentPanel.add(pnl, "cell 1 3, growx");
	}

	protected void addAccountListener(ActionListener accountListener) {
		cbxAccount.addActionListener(accountListener);		
	}

	protected AccountingType getAccountingType() {
		return (AccountingType) cbxAccount.getSelectedItem();
	}

}
