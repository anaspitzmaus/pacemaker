package com.rose.pm;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.rose.pm.db.DB;
import com.rose.pm.db.Dlg_DBSettings;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.ui.CtrlFrmBase;


public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					checkDBConnection();
					CtrlFrmBase ctrlFrmBase = new CtrlFrmBase();
					ctrlFrmBase.showFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	/**
	 * get the connection tom the database, insert the administrator to the appropriate schema of that database and opens the login-dialog
	 */
	public static void checkDBConnection(){
		//Examination exam = null;
		
		//Sensis sensis = new Sensis("I:\\MESO\\Sensis\\Importiert\\");
		
		
		if(DB.createConnection() != null){			
		 //go further on only if a connection to the database could be established
			if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
				//start the login dialog
				try {
//					Dlg_LogIn dialog = new Dlg_LogIn();
//					dialog.setLocationRelativeTo(null); //show in the center of the screen
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					//dialog.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    "The Application couldn't be started!", "Fatal Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
					    "The Application couldn't be started!", "Fatal Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}else{ //if there couldn't be created a database connection
			//open the Dialog with the database settings
			Dlg_DBSettings dlgDBSettings = new Dlg_DBSettings();
			dlgDBSettings.setLocationRelativeTo(null);
			dlgDBSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dlgDBSettings.setModal(true);
			dlgDBSettings.setVisible(true);	
		}
		
	}

}
