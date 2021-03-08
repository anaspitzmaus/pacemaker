package com.rose.pm.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import com.rose.pm.material.MonitorType;

public class PnlMonitor extends PnlBase {
	JLabel lblMonitorType;
	JComboBox<MonitorType> cbxMonitorType;
	JButton btnShowAll;

	protected void setMonitorTypeModel(DefaultComboBoxModel<MonitorType> monitorTypeModel) {
		cbxMonitorType.setModel(monitorTypeModel);
		
	}

}
