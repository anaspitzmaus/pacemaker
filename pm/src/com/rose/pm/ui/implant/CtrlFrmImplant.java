package com.rose.pm.ui.implant;

import java.awt.EventQueue;

public class CtrlFrmImplant {
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CtrlPnlMain ctrlPnlMain = new CtrlPnlMain();
					FrmImplant frmImplant = new FrmImplant();
					frmImplant.insertPnlMain(ctrlPnlMain.getPanel());
					frmImplant.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	

}
