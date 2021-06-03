package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.MonitorType;


public class CtrlDlgChangeMonitorType extends CtrlDlgChangeType{
	private MonitorType monitorType;
	private AbstractTableModel tblModel;
	CreateListener createListener;
	
	public CtrlDlgChangeMonitorType(MonitorType monitorType, AbstractTableModel tblModel) {
		super(monitorType);
		this.monitorType = monitorType;
		this.tblModel = tblModel;
		setDialog(new DlgChangeMonitorType());
				
		getDialog().setNotation(monitorType.getNotation());
		getDialog().setNotice(monitorType.getNotice());		
		getDialog().setPrice(monitorType.getPrice());
		setComponentText();
		setListener();
	}
	
	protected void setListener() {
		super.setListener();
		createListener = new CreateListener();
		((DlgChangeMonitorType) getDialog()).addCreateListener(createListener);
		
	}
	
	class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!notationListener.getNotation().equals("")) {
				monitorType.setNotation(notationListener.getNotation());
				monitorType.setNotice(noticeListener.getNotation());
				monitorType.setPrice(priceListener.getPrice());
				updateDBAndTblModel();
			}		
		}
		
		
		protected void updateDBAndTblModel() {
			try {
				SQL_UPDATE.MonitorType(monitorType);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + CtrlDlgChangeMonitorType.class.getSimpleName() + "\n\nupdateDBAndTblModel", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			
			tblModel.fireTableDataChanged();
			((DlgChangeMonitorType) getDialog()).dispose();
		}
		
	}
}
