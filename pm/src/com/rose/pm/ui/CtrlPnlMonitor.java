package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.Isynet;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.Editor.DateCellEditor;
import com.rose.pm.ui.Listener.NotationListener;



public class CtrlPnlMonitor extends CtrlPnlBase {
	Ctrl_PnlSetDate ctrlPnlSetDate;
	DefaultComboBoxModel<MonitorType> monitorTypeModel;
	MonitorTblModel monitorTblModel;
	MonitorTypeListener monitorTypeListener;
	Listener listener;
	Renderer renderer;
	MonitorTypeListCellRenderer monitorTypeListCellRenderer;
	NotationListener serialNrListener, noticeListener;
	TblRowSelectionListener tblRowSelectionListener;
	ShowAllListener showAllListener;
	CreateListener createListener;
	TblMouseAdaptor tblMouseAdaptor;
	DeleteListener deleteListener; 
	TblMonitorIDRenderer tblMonitorIDRenderer;
	TblMonitorTypeRenderer tblMonitorTypeRenderer;
	Renderer.TblStringRenderer tblStringRenderer;
	Renderer.TblPatientRenderer tblPatientRenderer;
	Renderer.TblStatusRenderer tblStatusRenderer;
	Renderer.TblImplantDateRenderer tblImplantDateRenderer;
	Renderer.TblLocalDateRenderer tblLocalDateRenderer;
	Editor editor;
	Editor.DateCellEditor dateCellEditor;
	
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
		setEditor();		
		setRenderer();
		((PnlMonitor)panel).setMonitorTypeSelectionIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlMonitor)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	protected void setComponentLabeling() {
		((PnlMonitor)panel).setLblMonitorTypeText("Monitormodel:");
		((PnlMonitor)panel).setLblNotationText("Seriennummer:");
		((PnlMonitor)panel).setLblNoticeText("Bemerkung:");
		((PnlMonitor)panel).setBtnCreateText("Einfügen");
		((PnlMonitor)panel).setBtnDeleteText("Löschen");
		((PnlMonitor)panel).setBtnShowAllText("Alle Modelle");
	}
	
	private void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlMonitor)panel).setDateCellEditor(dateCellEditor);
	}
	
	void setModel() {
		//ComboBoxModel for the types of Monitors
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
		
		//table model for the monitors
		try {
			monitorTblModel = new MonitorTblModel(SQL_SELECT.monitors(monitorTypeListener.monitorType));

			panel.setTblModel(monitorTblModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void setListener() {
		 monitorTypeListener = new MonitorTypeListener();
		 ((PnlMonitor)panel).addMonitorTypeListener(monitorTypeListener);	
		 listener = new Listener();
		 serialNrListener = listener.new NotationListener();
		 ((PnlMonitor)panel).addSerialNrListener(serialNrListener);
		 noticeListener = listener.new NotationListener();
		 ((PnlMonitor)panel).addNoticeListener(noticeListener);
		 tblRowSelectionListener = new TblRowSelectionListener();
		 ((PnlMonitor)panel).addTblRowSelectionListener(tblRowSelectionListener);
		 showAllListener = new ShowAllListener();
		 ((PnlMonitor)panel).addShowAllListener(showAllListener);
		 createListener = new CreateListener();
		 ((PnlMonitor)panel).addCreateListener(createListener);
		 deleteListener = new DeleteListener();
		 ((PnlMonitor)panel).addDeleteListener(deleteListener);
	}
	
	private void setRenderer() {
		renderer = new Renderer();
		monitorTypeListCellRenderer = new MonitorTypeListCellRenderer();
		((PnlMonitor)panel).setMonitorTypeRenderer(monitorTypeListCellRenderer);
		tblMonitorIDRenderer = new TblMonitorIDRenderer();
		((PnlMonitor)panel).setTblMonitorIDRenderer(Monitor.class, tblMonitorIDRenderer);
		tblMonitorTypeRenderer = new TblMonitorTypeRenderer();
		((PnlMonitor)panel).setTblMonitorTypeRenderer(MonitorType.class, tblMonitorTypeRenderer);
		tblStringRenderer = renderer.new TblStringRenderer();
		((PnlMonitor)panel).setTblStringRenderer(String.class, tblStringRenderer);
		 tblPatientRenderer = renderer.new TblPatientRenderer();
		 ((PnlMonitor)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
		 tblStatusRenderer = renderer.new TblStatusRenderer();
		 ((PnlMonitor)panel).setTblStatusRenderer(Status.class, tblStatusRenderer);
		 tblImplantDateRenderer = renderer.new TblImplantDateRenderer();
		 ((PnlMonitor)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer);
		 tblLocalDateRenderer = renderer.new TblLocalDateRenderer();
		 ((PnlMonitor)panel).setLocalDateRenderer(LocalDate.class, tblLocalDateRenderer);
	}
	
	class MonitorTblModel extends AbstractTableModel{

		private static final long serialVersionUID = -1833403504107519409L;
		
		protected ArrayList<String> columnNames;
		ArrayList<Monitor> monitors;
		@SuppressWarnings("rawtypes")
		Class[] classes = {Monitor.class, MonitorType.class, String.class, LocalDate.class, String.class, Status.class, Patient.class, Date.class};
		
		protected Monitor searchMonitor = null;
		protected MonitorType searchMonitorType = null;
		protected String searchNotation = "";
		protected LocalDate searchLocalDate = null;
		protected String searchNotice = "";
		protected Status searchStatus = Status.Lager;
		protected Patient searchPatient = null;
		protected Date searchDate = null;
		
		
		
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
			return monitors.size() + 1;
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
			if(rowIndex > 0) {
				switch(columnIndex) {
					case 0: return monitors.get(rowIndex - 1);
					case 1: return monitors.get(rowIndex - 1).getMaterialType();
					case 2: return monitors.get(rowIndex - 1).getSerialNr();
					
					case 3: return monitors.get(rowIndex - 1).getExpireDate();
					
					case 4: return monitors.get(rowIndex - 1).getNotice();
					
					case 5: return monitors.get(rowIndex - 1).getStatus();
					
					case 6: return monitors.get(rowIndex - 1).getPatient();
					
					case 7: return monitors.get(rowIndex - 1).getDateOfImplantation();
					
					default: return null;
					
				}	
			}else {
				switch (columnIndex) {
				case 0: return searchMonitor;
				case 1: return searchMonitorType;
				case 2: return searchNotation;
				case 3: return searchLocalDate;
				case 4: return searchNotice;
				case 5: return searchStatus;
				case 6: return searchPatient;
				case 7: return searchDate;
				default: return null;
			}
			}
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
            monitors.get(row).setDateOfImplantation((Date)value);
            try {
				SQL_UPDATE.dateOfImplant(monitors.get(row));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //change value at database;
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
	 
	 class MonitorTypeListCellRenderer extends JLabel implements ListCellRenderer<MonitorType>{

		private static final long serialVersionUID = -1511554657041982786L;

		public MonitorTypeListCellRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);		
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends MonitorType> list, MonitorType value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof MonitorType) {
				setText(((MonitorType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
			
	} 
	 
	 class TblMonitorIDRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 8727963414643594470L;

		public TblMonitorIDRenderer() {
			setOpaque(true);
		 }
		 
		 @Override
		 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			 	if(value instanceof Monitor) {
					Integer id = ((Monitor)value).getId();
					setText(id.toString());			 	
					if(isSelected) {
						setBackground(Color.ORANGE);
					}else {
						setBackground(row%2==0 ? Color.white : Color.lightGray);   
					}
			 	}
					return this;
		 }
		 
	 }
	 
	 class TblMonitorTypeRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 324810444790377934L;
		
		public TblMonitorTypeRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value instanceof MonitorType) {
				String notation = ((MonitorType)value).getNotation();
				setText(notation);
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}
			}
			return this;
		}
		 
	 }
	 
	
	 
	class TblRowSelectionListener implements ListSelectionListener{
		Monitor monitor;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlMonitor)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlMonitor)panel).getSelectedTblRow();
	            monitor = (Monitor) ((PnlMonitor)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		
		protected Monitor getMonitorSelected() {
			return this.monitor;
		}		
	}
			
		class DeleteListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblRowSelectionListener.getMonitorSelected() instanceof Monitor) {
					if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
						try {
							SQL_UPDATE.deleteMonitor(tblRowSelectionListener.getMonitorSelected());
							monitorTblModel.monitors.remove(tblRowSelectionListener.getMonitorSelected());
							monitorTblModel.fireTableDataChanged();
						} catch (SQLException e1) {
							if(e1.getErrorCode() == 1451) {
								JOptionPane.showMessageDialog(null, "Dieser Monitor kann nicht gelöscht werden, da er bereits für eine Untersuchung verwendet wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(new JFrame(),
								"Message:\n" +  e1.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean deleteMonitor(Monitor monitor)", "SQL Exception warning",
							    JOptionPane.WARNING_MESSAGE);
							}
						}
							
						
					}
				
				}
			
			}
		}
		
		class ShowAllListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				((PnlMonitor)panel).setMonitorTypeSelectionIndex(-1);
				update();
			}
			
			protected void update() {
				try {
					monitorTblModel.setMonitors(SQL_SELECT.monitors(null));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				monitorTblModel.fireTableDataChanged();
			}
			
		}
		
		class CreateListener implements ActionListener{
			Monitor monitor;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(serialNrListener.getNotation() != "" && monitorTypeModel.getSelectedItem() instanceof MonitorType) {
					initiateMonitor();
					monitor.setExpireDate(ctrlPnlSetDate.getDate());
					monitor.setSerialNr(serialNrListener.getNotation());
					monitor.setNotice(noticeListener.getNotation());
					updateDBAndTblModel();
					
				}
			}
			
			protected void initiateMonitor() {
				monitor = new Monitor((MonitorType) monitorTypeModel.getSelectedItem());			
			}
			
			protected void updateDBAndTblModel() {
				Integer key = null;;
				try {
					key = SQL_INSERT.monitor(monitor);					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				if(key != null) {//if insertion was successful
					try {
						monitorTblModel.setMonitors(SQL_SELECT.monitors((MonitorType) monitorTypeModel.getSelectedItem()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				monitorTblModel.fireTableDataChanged();
			}
			
		}
		
		class TblMouseAdaptor extends MouseAdapter{
			 JTable table;
			 @Override
		    public void mouseClicked(MouseEvent mouseEvent){
		        if(mouseEvent.getClickCount()==2){
		        	 table =(JTable) mouseEvent.getSource();
		             Point point = mouseEvent.getPoint();
		             int row = table.rowAtPoint(point);
		             if (table.getSelectedRow() != -1 && row >= 0) {
		                CtrlDlgChangeMonitor ctrlDlgChangeMonitor = new CtrlDlgChangeMonitor((Monitor) monitorTblModel.getValueAt(row, 0), monitorTblModel);
		                ctrlDlgChangeMonitor.getDialog().setVisible(true);
		             }
		        }else if(SwingUtilities.isRightMouseButton(mouseEvent) == true){
		        	table =(JTable) mouseEvent.getSource();
		        	Point point = mouseEvent.getPoint();
		        	int row = table.rowAtPoint(point);
		        	if(table.getSelectedRow() == row) {
		        		Isynet isynet = new Isynet();
		        		Patient patient = isynet.getPatient();
		        		PopupMenu menu = new PopupMenu(patient, (Monitor) monitorTblModel.getValueAt(row, 0), monitorTblModel);
		                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
		        	}
		        }
		        		
		    }
		}

}
