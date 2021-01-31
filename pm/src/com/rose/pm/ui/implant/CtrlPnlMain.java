package com.rose.pm.ui.implant;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class CtrlPnlMain {
	PnlMain panel;
	
	
	
	protected PnlMain getPanel() {
		return panel;
	}



	public CtrlPnlMain() {
		panel = new PnlMain();
		ImageIcon icon = new ImageIcon(getImage("images/magnifying-glass.png"));
		icon.getImage();
		panel.setBtnAggregateIcon(icon);
		panel.setBtnElectrodeRAIcon(icon);
		panel.setBtnElectrodeRVIcon(icon);
		panel.setBtnElectrodeLVIcon(icon);
	}
	
	public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}
}
