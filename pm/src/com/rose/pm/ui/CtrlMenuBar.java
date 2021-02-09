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
		
		
		setListener();
	}
	
	
	
	private void setListener() {
		manufacturerListener = new ManufacturerListener();
		menuBar.addManufacturerListener(manufacturerListener);
		settingsListener = new SettingsListener();
		menuBar.addSettingsListener(settingsListener);
		
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
