package com.rose.pm.ui.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;


public class CtrlSetPathPatInfo {
	PnlSetPathPatInfo pnlSetPathPatInfo;
	Preferences prefs;
	OpenFileChooserListener openFileChooserListener;
	String fileName;
	
	
	public PnlSetPathPatInfo getPnlSetPathPatInfo() {
		return pnlSetPathPatInfo;
	}

	public CtrlSetPathPatInfo() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		pnlSetPathPatInfo = new PnlSetPathPatInfo();
		pnlSetPathPatInfo.getLblPath().setText("Pfad-PatInfo:");
		setListener();
	}
	
	private void setListener(){		
		
		openFileChooserListener = new OpenFileChooserListener();
		pnlSetPathPatInfo.addFileChooserListener(openFileChooserListener);
	}
	
	
	
	class OpenFileChooserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			int returnValue = chooser.showDialog(null, "Ausw�hlen");
			if(returnValue == JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getParent();
				pnlSetPathPatInfo.getTxtPath().setText(fileName);
				prefs.put("Path_PatInfo", fileName);
			}
			
		}
		
	}
}
