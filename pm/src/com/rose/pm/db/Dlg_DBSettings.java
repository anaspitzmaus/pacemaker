package com.rose.pm.db;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.rose.pm.Start;
import com.rose.pm.languages.Messages;

import net.miginfocom.swing.MigLayout;

/**
 * Dialog to set the settings of the database-connection
 * @author Ekkehard Rose
 * @version 1.0
 */
public class Dlg_DBSettings extends JDialog {

	
	private static final long serialVersionUID = -4690411748422172912L;
	private final JPanel contentPanel = new JPanel();
	private final JTextField txtHost;
	private final JTextField txtUser;
	private final JPasswordField txtPW;
	private final Preferences pref;
	private String host;
	private String user;
	private String port;
	private String schema;
	private char[] password = {};
	private final JLabel lblTestResultText, lblTestResult;
	private Dlg_DBSettings myDialog;
	private JTextField txtSchema;
	private JTextField txtPort;
	
	
	/**
	 * Create the dialog.
	 */
	public Dlg_DBSettings() {
		
		myDialog = this;
		myDialog.setTitle(Messages.getString("Database_Settings"));
		pref = Preferences.userNodeForPackage(this.getClass()); //set Preferences
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
			
			JLabel lblPort = new JLabel("Port:");
			lblPort.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblPort, "cell 0 0,alignx left");
			
			txtPort = new JTextField();
			txtPort.setFont(new Font("Tahoma", Font.PLAIN, 16));			
			contentPanel.add(txtPort, "cell 1 0,growx");
			txtPort.setColumns(10);			
			txtPort.setText(pref.get("DB_kgp_Port", "3306"));	
		
		
			JLabel lblHost = new JLabel("Host:");
			lblHost.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblHost, "cell 0 1,alignx left");
		
		
			txtHost = new JTextField();
			txtHost.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(txtHost, "cell 1 1,grow");
			txtHost.setColumns(10);
			txtHost.setText(pref.get("DB_kgp_Host", "localhost"));
			
		
			JLabel lblUser = new JLabel("User:");
			lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblUser, "cell 0 2,alignx left");
		
		
			txtUser = new JTextField();
			txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(txtUser, "cell 1 2,growx");
			txtUser.setColumns(10);
			txtUser.setText(pref.get("DB_kgp_User", "root"));
		
		
			JLabel lblPW = new JLabel("Password:");
			lblPW.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblPW, "cell 0 3,alignx left");
		
		
			txtPW = new JPasswordField();
			txtPW.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(txtPW, "cell 1 3,growx");
				
			JLabel lblSchema = new JLabel("Schema:");
			lblSchema.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblSchema, "cell 0 4,alignx left");
			
			txtSchema = new JTextField();
			txtSchema.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(txtSchema, "cell 1 4,growx");
			txtSchema.setColumns(10);			
			txtSchema.setText(pref.get("DB_kgp__Schema", "kgp"));
		
			lblTestResult = new JLabel("");
			lblTestResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblTestResult, "cell 0 5");
		
		
			lblTestResultText = new JLabel("");
			lblTestResultText.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblTestResultText, "cell 1 5");		
			
		
			/*
			 * button pane
			 */
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				/*
				 * button to test the connection
				 */
				JButton btnTest = new JButton("Test Connection");
				btnTest.setFont(new Font("Tahoma", Font.PLAIN, 11));
				buttonPane.add(btnTest);
				btnTest.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						lblTestResult.setText("Test Result:");
						Connection con = null;
						port = txtPort.getText();
						host = txtHost.getText();
						user= txtUser.getText();
						schema = txtSchema.getText();
						password = txtPW.getPassword(); 
						try {
							Class.forName("com.mysql.jdbc.Driver").newInstance();							
							String connectionCommand = "jdbc:mysql://"+host+"/?user="+user+"&password="+String.valueOf(password)+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";	
							try {
								con = DriverManager.getConnection(connectionCommand);
								lblTestResultText.setText("Success!");
								lblTestResultText.setBackground(Color.GREEN);
								lblTestResultText.setOpaque(true);
								lblTestResultText.setBorder(BorderFactory.createEtchedBorder(2, new Color(255, 255, 255), Color.BLUE));
								con.close();
							} catch (SQLException e1) {
								lblTestResultText.setText("Failure while connecting to database!");	
								lblTestResultText.setBackground(Color.RED);
								lblTestResultText.setOpaque(true);
								lblTestResultText.setBorder(BorderFactory.createEtchedBorder(2, new Color(255, 255, 255), Color.BLUE));
							}
						} catch (InstantiationException|IllegalAccessException|ClassNotFoundException e1) {							
							lblTestResultText.setText("Failure while instantiating the driver!");	
							lblTestResultText.setBackground(Color.RED);
							lblTestResultText.setOpaque(true);
							lblTestResultText.setBorder(BorderFactory.createEtchedBorder(2, new Color(255, 255, 255), Color.BLUE));
						}						
					}
				});
			
				/*
				 * OK button pressed
				 */
			
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						port = txtPort.getText();
						host = txtHost.getText();
						user= txtUser.getText();
						schema = txtSchema.getText();
						password = txtPW.getPassword(); 
						pref.put("DB_Port", port);
						pref.put("DB_Host", host);
						pref.put("DB_User", user);
						pref.put("DB_PW", String.valueOf(password));
						pref.put("DB_Schema", schema);
						myDialog.dispose();
						Start.checkDBConnection();
					}
				});
			
			
				/*
				 * cancel button that closes the dialog and terminates the application
				 */
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						myDialog.dispose();
						System.exit(0);
					}
				});
			
		
	}

}
