package com.rose.pm.ui.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;


public class CtrlSetPathPatInfo {
	PnlSetPathPatInfo pnlSetPathPatInfo;
	Preferences prefs;
	OpenFileChooserListener openFileChooserListener;
	String filePath;
	File file;
	
	public PnlSetPathPatInfo getPnlSetPathPatInfo() {
		return pnlSetPathPatInfo;
	}

	public CtrlSetPathPatInfo() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		pnlSetPathPatInfo = new PnlSetPathPatInfo();
		pnlSetPathPatInfo.getLblPath().setText("Pfad-PatInfo:");
		pnlSetPathPatInfo.setPathText(prefs.get("Path_PatInfo", ""));
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
			int returnValue = chooser.showDialog(null, "Auswählen");
			if(returnValue == JFileChooser.APPROVE_OPTION){
				filePath = chooser.getSelectedFile().getParent();
				file = chooser.getSelectedFile();
				pnlSetPathPatInfo.getTxtPath().setText(filePath);
				prefs.put("Path_PatInfo", file.getPath());
			}
			
		}
		
	}
}
