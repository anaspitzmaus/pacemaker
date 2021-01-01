package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMenuBar {
	FrmMenuBar menuBar;
	ManufacturerListener manufacturerListener;
	
	protected FrmMenuBar getMenuBar() {
		return this.menuBar;
	}
	
	public CtrlMenuBar() {
		menuBar = new FrmMenuBar();
		menuBar.setBtnManufacturerText("Hersteller");
		setListener();
	}
	
	
	
	private void setListener() {
		manufacturerListener = new ManufacturerListener();
		menuBar.addManufacturerListener(manufacturerListener);
	}



	class ManufacturerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CtrlDlgManufacturer ctrlDlgManufacturer = new CtrlDlgManufacturer();
			
		}
		
	}
}
