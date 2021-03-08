package com.rose.pm.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;

import com.rose.person.Patient;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.Status;


public class CtrlPnlMonitor extends CtrlPnlBase {
	DefaultComboBoxModel<MonitorType> monitorTypeModel;
	MonitorTblModel monitorTblModel;
	MonitorTypeListener monitorTypeListener;
	 void setModel() {
			ArrayList<MonitorType> monitorTypes;
			try {
				monitorTypes = SQL_SELECT.monitorTypes();
				MonitorType[] arr = new MonitorType[monitorTypes.size()]; 	
				monitorTypeModel = new DefaultComboBoxModel<MonitorType>();
				((PnlMonitor)panel).setMonitorTypeModel(monitorTypeModel);
		        // ArrayList to Array Conversion 
		        for (int i = 0; i < monitorTypes.size(); i++) {
		            arr[i] = monitorTypes.get(i);		
		            monitorTypeModel.addElement(monitorTypes.get(i));
			 	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			try {
				monitorTblModel = new MonitorTblModel(SQL_SELECT.monitors(monitorTypeListener.monitorType));
				panel.setTblModel(monitorTblModel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	class MonitorTblModel extends AbstractTableModel{

		protected ArrayList<String> columnNames;
		ArrayList<Monitor> monitors;
		@SuppressWarnings("rawtypes")
		Class[] classes = {Monitor.class, MonitorType.class, String.class, LocalDate.class, String.class, Status.class, Patient.class, Date.class};
		
		public MonitorTblModel(ArrayList<Monitor> monitors) {
			this.monitors = monitors;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Bemerkung");
			columnNames.add("Status");
			columnNames.add("Patient");
			columnNames.add("Einsatz ab");
		}
		@Override
		public int getRowCount() {
			return columnNames.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.size();
		}
		
		@Override
		public String getColumnName(int column) {
			return columnNames.get(column);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex) {
			case 0: return monitors.get(rowIndex);
			case 1: return monitors.get(rowIndex).getId();
			case 2: return monitors.get(rowIndex).getSerialNr();
			
			case 3: return monitors.get(rowIndex).getExpireDate();
			
			case 4: return monitors.get(rowIndex).getNotice();
			
			case 5: return monitors.get(rowIndex).getStatus();
			
			case 6: return monitors.get(rowIndex).getPatient();
			
			case 7: return monitors.get(rowIndex).getDateOfImplantation();
			
			default: return null;
			
			}			
		}
		
		@Override
		public Class getColumnClass(int col) {
			return classes[col];			
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex == 7) {
				return true;
			}else {
				return false;
			}
		}

		protected void setMonitors(ArrayList<Monitor> monitors) {
			this.monitors = monitors;		
		}
		
	}
	
	 class MonitorTypeListener implements ItemListener{

			MonitorType monitorType;
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					try {
						monitorType = (MonitorType) event.getItem();			        
					} catch (ClassCastException e) {
						monitorType = null;
					}				
			    }
				updateTblModel();		
			}
			
			protected void updateTblModel() {
				try {
					monitorTblModel.setMonitors(SQL_SELECT.monitors(monitorType));
					monitorTblModel.fireTableDataChanged();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			}		
		}

}
