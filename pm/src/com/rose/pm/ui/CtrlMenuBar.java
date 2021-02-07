package com.rose.pm.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.Isynet;
import com.rose.pm.ui.settings.DlgPath;


public class CtrlMenuBar {
	FrmMenuBar menuBar;
	ManufacturerListener manufacturerListener;
	SettingsListener settingsListener;
	UpdateListener updateListener;
	
	protected FrmMenuBar getMenuBar() {
		return this.menuBar;
	}
	
	public CtrlMenuBar() {
		menuBar = new FrmMenuBar();
		menuBar.setBtnManufacturerText("Hersteller");
		ImageIcon icon = new ImageIcon(getImage("images/settings.png"));
		icon.getImage();
		menuBar.setBtnSettingsIcon(icon);
		menuBar.setBtnSettingsText("");
		
		ImageIcon iconRefresh = new ImageIcon(getImage("images/refresh_16.png"));
		iconRefresh.getImage();
		menuBar.setBtnUpdateIcon(iconRefresh);
		menuBar.setBtnUpdateText("");
		setListener();
	}
	
	
	
	private void setListener() {
		manufacturerListener = new ManufacturerListener();
		menuBar.addManufacturerListener(manufacturerListener);
		settingsListener = new SettingsListener();
		menuBar.addSettingsListener(settingsListener);
		updateListener = new UpdateListener();
		menuBar.addUpdateListener(updateListener);
	}
	
	/**
	 * listener for refreshing the active patient as set in isynet
	 * @author Ekki
	 *
	 */
	class UpdateListener implements ActionListener{
		Isynet isynet;
		@Override
		public void actionPerformed(ActionEvent e) {
			isynet = new Isynet();
			
			try {
				HashMap<Integer, String> data = isynet.readPatInfo();
				//create a new Patient out of the data 
				//put the patients name to the menuBar
			} catch (FileNotFoundException e1) {					
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Dateipfad" , JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {				
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Dateipfad" , JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}



	class ManufacturerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CtrlDlgManufacturer ctrlDlgManufacturer = new CtrlDlgManufacturer();
			
		}
		
	}
	
	class SettingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DlgPath dlgPath = new DlgPath();
			dlgPath.setVisible(true);
			
		}
		
	}


	public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}
}
