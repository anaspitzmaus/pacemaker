package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMenuBar {
	FrmMenuBar menuBar;
	
	protected FrmMenuBar getMenuBar() {
		return this.menuBar;
	}
	
	public CtrlMenuBar() {
		menuBar = new FrmMenuBar();
	}
	
	class ManufacturerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
