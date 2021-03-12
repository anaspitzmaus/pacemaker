package com.rose.pm.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.CtrlPnlElectrode.ElectrodeTblModel;
import com.rose.pm.ui.CtrlPnlElectrode.ElectrodeTypeListener;
import com.rose.pm.ui.CtrlPnlElectrode.TblMouseAdaptor;


public class CtrlPnlMonitor extends CtrlPnlBase {
	Ctrl_PnlSetDate ctrlPnlSetDate;
	DefaultComboBoxModel<MonitorType> monitorTypeModel;
	MonitorTblModel monitorTblModel;
	MonitorTypeListener monitorTypeListener;
	Listener listener;
	
	public CtrlPnlMonitor() {
		createPanel();
		setComponentLabeling();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlMonitor)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
	}
	
	protected void createPanel() {
		panel = new PnlMonitor();
		panel.setName("Monitore");
		setListener();
		setModel();	
		
		//setEditor();
		
//		setRenderer();
//		((PnlElectrode)panel).setElectrodeTypeSelectionIndex(-1);
//		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tblMouseAdaptor = new TblMouseAdaptor();
//		((PnlElectrode)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	protected void setComponentLabeling() {
		((PnlMonitor)panel).setLblMonitorTypeText("Monitormodel:");
		((PnlMonitor)panel).setLblNotationText("Seriennummer:");
		((PnlMonitor)panel).setLblNoticeText("Bemerkung:");
		((PnlMonitor)panel).setBtnCreateText("Einfügen");
		((PnlMonitor)panel).setBtnDeleteText("Löschen");
		((PnlMonitor)panel).setBtnShowAllText("Alle Modelle");
	}
	
	
	void setModel() {
			ArrayList<MonitorType> monitorTypes;
			try {
				monitorTypes = SQL_SELECT.monitorTypes(null, "");
				MonitorType[] arr = new MonitorType[monitorTypes.size()]; 	
				monitorTypeModel = new DefaultComboBoxModel<MonitorType>();
				
		        // ArrayList to Array Conversion 
		        for (int i = 0; i < monitorTypes.size(); i++) {
		            arr[i] = monitorTypes.get(i);		
		            monitorTypeModel.addElement(monitorTypes.get(i));
			 	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			((PnlMonitor)panel).setMonitorTypeModel(monitorTypeModel);
			
			try {
				monitorTblModel = new MonitorTblModel(SQL_SELECT.monitors(monitorTypeListener.monitorType));
				panel.setTblModel(monitorTblModel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	void setListener() {
		 monitorTypeListener = new MonitorTypeListener();
		 ((PnlMonitor)panel).addMonitorTypeListener(monitorTypeListener);	
		 listener = new Listener();
	}
	
	class MonitorTblModel extends AbstractTableModel{

		private static final long serialVersionUID = -1833403504107519409L;
		
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
