package com.rose.pm.ui;

import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.rose.pm.material.MonitorType;
import com.rose.pm.ui.CtrlPnlMonitor.MonitorTypeListener;

import net.miginfocom.swing.MigLayout;

public class PnlMonitor extends PnlBase {
	
	private static final long serialVersionUID = -3388760039486245297L;
	JLabel lblMonitorType;
	JComboBox<MonitorType> cbxMonitorType;
	JButton btnShowAll;

	protected void setMonitorTypeModel(DefaultComboBoxModel<MonitorType> monitorTypeModel) {
		cbxMonitorType.setModel(monitorTypeModel);
		
	}

	protected void setLblMonitorTypeText(String text) {
		lblMonitorType.setText(text);	
	}
	
	protected void setBtnShowAllText(String txt) {
		btnShowAll.setText(txt);
	}

	public PnlMonitor() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[][]"));
		
		btnShowAll = new JButton("btnShowAll");
		btnShowAll.setFont(font);
		pnlInput.add(btnShowAll, "cell 1 0");
		
		lblMonitorType = new JLabel("lblPM_Type");
		lblMonitorType.setFont(font);
		pnlInput.add(lblMonitorType, "cell 0 1");
		
		cbxMonitorType = new JComboBox<MonitorType>();
		cbxMonitorType.setFont(font);
		pnlInput.add(cbxMonitorType, "cell 1 1, growx");
		
		pnlInput.add(lblNotation, "cell 2 1");
		
		pnlInput.add(txtNotation, "cell 3 1");
				
		pnlInput.add(lblNotice, "cell 5 1");
		
		pnlInput.add(txtNotice, "cell 6 1, growx");
		
		pnlInput.add(btnCreate, "cell 7 1");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}

	protected void addMonitorTypeListener(ItemListener l) {
		cbxMonitorType.addItemListener(l);
		
	}
	

}
