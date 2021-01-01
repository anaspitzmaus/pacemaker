package com.rose.pm.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.pm.languages.Messages;




/**
 * class with static methods to build the connection to the database
 * @author Ekkehard Rose
 * @version 1.0
 */
public class DB {

	private static Connection con = null;
	private static Preferences pref;
	
	public static Connection createConnection(){
		
		String database = "kgp";	
		
		if(getDriverInstance()){
			if(getDriverConnection()!=null){
				if(!selectDB(database)){ //if schema not exists
					createDBFromScript(); //create schema from script and try to
					selectDB(database); //connect to schema
				}				
			}		
		}			
		return con;
	}
	
	public static Connection getConnection(){
		return con;
	}	
	
	public static Statement getStatement(){
		Statement stmt = null;
		try{			
			stmt = con.createStatement();			 
		}catch (Exception ex){
			JOptionPane.showMessageDialog(new JFrame(),  ex.getMessage(),  
					"Connect to Database Failure", JOptionPane.WARNING_MESSAGE);		
		}
		return stmt;
	}
	
	/**
	 * creates the database if not exists
	 * the inputStreamReader is necessary, when using the FileReader, the .jar-File doesn't find the path of the sql-file to create the database if not exists
	 * @return true if database could be created, false if not
	 */
	private static boolean createDBFromScript(){
		ScriptRunner runner = new ScriptRunner(con, false, false);	
		Script script = new Script();
		InputStream sqlFile = script.getFilePath();
		
		try {
			runner.runScript(new BufferedReader(new InputStreamReader(sqlFile)));
			return true;
		} catch (IOException | SQLException sre) {
			JOptionPane.showMessageDialog(new JFrame(),  "Please run the 'cor.sql'-File (available on the disk) in your MySQL Workbench!", 
					"Failure while creating the Database from sql-script", JOptionPane.WARNING_MESSAGE);
			return false;			
		} 
	}
	
	
	/**
	 * get the instance of the mysqlDriver
	 * @return true if instance could be created, false if not
	 */
	private static boolean getDriverInstance(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return true;
//		} catch (InstantiationException e1) {
//			JOptionPane.showMessageDialog(new JFrame(),  e1.getMessage(),  
//					"Error: unable to instantiate driver!", JOptionPane.WARNING_MESSAGE);
//			return false;
//		} catch (IllegalAccessException e1) {
//			JOptionPane.showMessageDialog(new JFrame(),  e1.getMessage(),  
//					"Error: access problem while loading!", JOptionPane.WARNING_MESSAGE);
//			return false;
		} catch (ClassNotFoundException e1) {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				return true;
			}catch(ClassNotFoundException e2){
				JOptionPane.showMessageDialog(new JFrame(),  e1.getMessage(),  
						"Error: unable to load driver class!", JOptionPane.WARNING_MESSAGE);	
				return false;				
//			}catch (InstantiationException e2) {
//				return false;
//			}catch (IllegalAccessException e2) {
//				return false;
			}
			
		}
	}
	
	/**
	 * set the settings for the driver connection
	 * @return instance of the connection
	 */
	private static Connection getDriverConnection(){
		pref = Preferences.userNodeForPackage(Dlg_DBSettings.class); //get Preferences
		
		//String host = pref.get("DB_Host", "localhost");
		String port = pref.get("DB_Port", "");
		//String host = "127.0.0.1:3306";
		String host = "192.168.200.1:3306";
		String user = pref.get("DB_User", "root");
		user = "AP02";
		String passwd = pref.get("DB_PW", ""); 
		if(host.equals("localhost")){
			host = host + ":" + port;
		}
		 
		String connectionCommand = "jdbc:mysql://"+host+"/?user="+user+"&password="+passwd+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";	
		try {
			con = DriverManager.getConnection(connectionCommand);		
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(new JFrame(),  e1.getMessage(),  Messages.getString("connection_failure"), 
					JOptionPane.WARNING_MESSAGE);			
		
		}
		return con;
	}
	
	/**
	 * set the default database
	 * @param database
	 * @return
	 */
	public static boolean selectDB(String database){		
		try {
			con.setCatalog(database);
			return true;
		} catch (SQLException e) {
			return false;					
		}
	}
	
	
	
	
}
