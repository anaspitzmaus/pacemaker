package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;

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
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
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
			rdbtnAnker.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(rdbtnAnker, "flowx,cell 1 2");
		}
		{
			rdbtnScrew = new JRadioButton("rdbtnScrew");
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
			lblPrice = new JLabel("lblPrice");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblPrice, "cell 0 4,alignx left,aligny bottom");
		}
		{
			ftxtPrice = new JFormattedTextField();
			ftxtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(ftxtPrice, "cell 1 4,growx");
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
	
	protected void setLength(Integer l) {
		spinLength.setValue(l);
	}

}
