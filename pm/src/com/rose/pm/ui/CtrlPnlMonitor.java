package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import java.util.Comparator;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;
import javax.swing.text.BadLocationException;

import com.rose.dataExchange.Isynet;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.MaterialType;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.Status;
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
	CreateListener createListener;
	TblMouseAdaptor tblMouseAdaptor;
	DeleteListener deleteListener; 
	Renderer.TblCellMaterialIDRenderer tblCellMaterialIDRenderer;
	TblCellMonitorTypeRenderer tblCellMonitorTypeRenderer;
	Renderer.TblStringRenderer tblStringRenderer;
	Renderer.TblCellPatientRenderer tblCellPatientRenderer;
	Renderer.TblCellStatusRenderer tblCellStatusRenderer;
	Renderer.TblCellImplantDateRenderer tblCellImplantDateRenderer;
	Renderer.TblCellLocalDateRenderer tblCellLocalDateRenderer;
	Renderer.TblCellStringRenderer notationRenderer;
	Editor editor;
	Editor.DateCellEditor dateCellEditor;
	MonitorTypeTblCellEditor monitorTypeTblCellEditor;
	Editor.SearchStatusTblCellEditor statusTblCellEditor;
	
	
	public CtrlPnlMonitor() {
		createPanel();
		setComponentLabeling();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlMonitor)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		
		monitorTypeTblCellEditor = new MonitorTypeTblCellEditor(monitorTblModel);
		((PnlMonitor)panel).setMonitorTypeTblCellEditor(monitorTypeTblCellEditor);
		
		statusTblCellEditor = editor.new SearchStatusTblCellEditor(monitorTblModel, panel);
		((PnlMonitor)panel).setStatusTblCellEditor(statusTblCellEditor);
		
		JTextField textField = new JTextField("Text");
		SearchNotationListener searchNotationListener = new SearchNotationListener();
		textField.getDocument().addDocumentListener(searchNotationListener);		
		((PnlMonitor)panel).setNotationCellEditor(new DefaultCellEditor(textField));
		//panel.table.setAutoCreateRowSorter(true);
		panel.setFirstRowHeight(40);//the standard height of the first row
		
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
		((PnlMonitor)panel).setBtnCreateText("Einf�gen");
		((PnlMonitor)panel).setBtnDeleteText("L�schen");
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
			//MonitorType[] arr = new MonitorType[monitorTypes.size()]; 	
			monitorTypeModel = new DefaultComboBoxModel<MonitorType>();
			
	        // ArrayList to Array Conversion 
	        for (int i = 0; i < monitorTypes.size(); i++) {
	           // arr[i] = monitorTypes.get(i);		
	            monitorTypeModel.addElement(monitorTypes.get(i));
		 	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		((PnlMonitor)panel).setMonitorTypeModel(monitorTypeModel);
		
		//table model for the monitors
		try {
			monitorTblModel = new MonitorTblModel(SQL_SELECT.monitors(monitorTypeListener.monitorType, "", Status.Lager));//initial values

			panel.setTblModel(monitorTblModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void setListener() {
		 monitorTypeListener = new MonitorTypeListener();//listener for the comboBox that shows the types og monitors
		 ((PnlMonitor)panel).addMonitorTypeListener(monitorTypeListener);	
		 listener = new Listener();
		 serialNrListener = listener.new NotationListener();
		 ((PnlMonitor)panel).addSerialNrListener(serialNrListener);
		 noticeListener = listener.new NotationListener();
		 ((PnlMonitor)panel).addNoticeListener(noticeListener);
		 tblRowSelectionListener = new TblRowSelectionListener();
		 ((PnlMonitor)panel).addTblRowSelectionListener(tblRowSelectionListener);
		 createListener = new CreateListener();
		 ((PnlMonitor)panel).addCreateListener(createListener);
		 deleteListener = new DeleteListener();
		 ((PnlMonitor)panel).addDeleteListener(deleteListener);
	}
	
	private void setRenderer() {
		renderer = new Renderer();
		monitorTypeListCellRenderer = new MonitorTypeListCellRenderer();
		((PnlMonitor)panel).setMonitorTypeRenderer(monitorTypeListCellRenderer);		
		tblCellMaterialIDRenderer = renderer.new TblCellMaterialIDRenderer();
		((PnlMonitor)panel).setTblMonitorIDRenderer(Monitor.class, tblCellMaterialIDRenderer);		
		tblCellMonitorTypeRenderer = new TblCellMonitorTypeRenderer();
		((PnlMonitor)panel).setTblMonitorTypeRenderer(MonitorType.class, tblCellMonitorTypeRenderer);
		notationRenderer = renderer.new TblCellStringRenderer();
		((PnlMonitor)panel).setTblCellStringRenderer(String.class, notationRenderer);		
		 tblCellPatientRenderer = renderer.new TblCellPatientRenderer();
		 ((PnlMonitor)panel).setTblCellPatientRenderer(Patient.class, tblCellPatientRenderer);
		 tblCellStatusRenderer = renderer.new TblCellStatusRenderer();
		 ((PnlMonitor)panel).setTblCellStatusRenderer(Status.class, tblCellStatusRenderer);
		 tblCellImplantDateRenderer = renderer.new TblCellImplantDateRenderer();
		 ((PnlMonitor)panel).setTblCellImplantDateRenderer(Date.class, tblCellImplantDateRenderer);
		 tblCellLocalDateRenderer = renderer.new TblCellLocalDateRenderer();
		 ((PnlMonitor)panel).setLocalDateRenderer(LocalDate.class, tblCellLocalDateRenderer);
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
		public void fireTableDataChanged() {			
			super.fireTableDataChanged();
			panel.setFirstRowHeight(40);//set the height of the first row 
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
			if(row == 0) {//for the first 'search' row
				switch(col) {
				case 1:
					searchMonitorType = (MonitorType)value;
					break;		
				case 2:
					searchNotation = (String)value;
					break;
				case 5:
					searchStatus = (Status)value;
					break;
				}
			}else {
	            monitors.get(row - 1).setDateOfImplantation((Date)value);
	            try {
					SQL_UPDATE.dateOfImplant(monitors.get(row - 1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            //change value at database;
		}
		
		@Override
		public Class getColumnClass(int col) {
			return classes[col];			
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(rowIndex == 0) {
				if(columnIndex == 1 || columnIndex ==2 || columnIndex == 5) {
					return true;
				}else {
					return false;
				}
			}else {
				if(columnIndex == 7) {
					return true;
				}else {
					return false;
				}
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
			//updateTblModel();		
		}
		
		protected void updateTblModel() {
			Status status;
			try {
				status = statusTblCellEditor.getSearchStatusListener().getStatus();
			}catch(NullPointerException e) {
				status = null;
			}
			try {
				monitorTblModel.setMonitors(SQL_SELECT.monitors(monitorType, monitorTblModel.searchNotation, status));
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
	 
		 
	 /**
	  * renderer for the types of monitors shown in the table
	  * @author Ekki
	  *
	  */
	 class TblCellMonitorTypeRenderer extends Renderer.TblCellMaterialTypeRenderer{

		private static final long serialVersionUID = 3751035874683130981L;

		public TblCellMonitorTypeRenderer() {
			renderer.super();
		}
		 
		@Override
		protected String getFirstRowText(MaterialType value) {
			if(value instanceof MonitorType) {
				return (((MonitorType) value).getNotation());
			}else {
				return "Alle Monitormodelle";				
			}	
		}
		
		
		 
	 }
	 
//	 class TblLocalDateRenderer extends JLabel implements TableCellRenderer{
//			
//		private static final long serialVersionUID = 2717295573009886572L;
//
//		public TblLocalDateRenderer() {
//			super.setOpaque(true);
//		}
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//				int row, int column) {
//			if(row > 0) {
//				if(value instanceof LocalDate) {
//					LocalDate date = (LocalDate) value;			
//					setText(date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
//					if(isSelected) {
//						setBackground(Color.ORANGE);
//					}else {
//						setBackground(row%2==0 ? Color.white : Color.lightGray);   
//					}
//				}
//				return this;	
//			}else {
//				setBackground(Color.white);
//				return null;
//			}
//				
//		}		
//		
//	}
	 
	 class SearchStatusRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = -6352537259628112228L;

			public SearchStatusRenderer() {
				setOpaque(true);
		        setHorizontalAlignment(CENTER);
		        setVerticalAlignment(CENTER);	
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				return null;
			}
	 }
	 
	 class SearchStatusListCellRenderer extends JLabel implements ListCellRenderer<Status>{

		private static final long serialVersionUID = -3682525429996426370L;

		public SearchStatusListCellRenderer() {
			setOpaque(true);
		    setHorizontalAlignment(CENTER);
		    setVerticalAlignment(CENTER);	
		}
		 
		@Override
		public Component getListCellRendererComponent(JList<? extends Status> list, Status value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof Status) {
				setText(((Status) value).name());
				setFont(new Font("Tahoma", Font.ITALIC, 14));
			}else {
				setText("");
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
					if(JOptionPane.showConfirmDialog(null, "M�chten sie den Datensatz wirklich l�schen?") == 0) {
						try {
							SQL_UPDATE.deleteMonitor(tblRowSelectionListener.getMonitorSelected());
							monitorTblModel.monitors.remove(tblRowSelectionListener.getMonitorSelected());
							monitorTblModel.fireTableDataChanged();
							
						} catch (SQLException e1) {
							if(e1.getErrorCode() == 1451) {
								JOptionPane.showMessageDialog(null, "Dieser Monitor kann nicht gel�scht werden, da er bereits f�r eine Untersuchung verwendet wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
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
		
//		class ShowAllListener implements ActionListener{
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				((PnlMonitor)panel).setMonitorTypeSelectionIndex(-1);
//				update();
//			}
//			
//			protected void update() {
//				try {
//					monitorTblModel.setMonitors(SQL_SELECT.monitors(null, monitorTblModel.searchNotation, statusTblCellEditor.getSearchStatusListener().getStatus()));
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}			
//				monitorTblModel.fireTableDataChanged();
//			}
//			
//		}
		
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
				Integer key = null;
				try {
					key = SQL_INSERT.monitor(monitor);					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				if(key != null) {//if insertion was successful
					monitorTblModel.setValueAt(Status.Lager, 0, 5);
					monitorTblModel.setValueAt(monitorTypeModel.getSelectedItem(), 0, 1);
					for(int i = 0; i < monitorTypeTblCellEditor.getCbxMonitorTypeModel().getSize(); i++) {
						try {
							if(monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i).getNotation().equals(((MonitorType)monitorTypeModel.getSelectedItem()).getNotation())) {
								monitorTypeTblCellEditor.getCbxMonitorTypeModel().setSelectedItem(monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i));
							}
						}catch(NullPointerException e) {//if (monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i) == null
							//do nothing
						}
					}
					
					try {
						monitorTblModel.setMonitors(SQL_SELECT.monitors((MonitorType) monitorTypeModel.getSelectedItem(), (String)monitorTblModel.getValueAt(0, 2), (Status)monitorTblModel.getValueAt(0, 5)));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				monitorTblModel.fireTableDataChanged();
				
			}
			
		}
		
		class SearchNotationListener implements DocumentListener{
			String txt;
			@Override
			public void insertUpdate(DocumentEvent e) {
				setNotation(e);			
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				setNotation(e);				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				setNotation(e);				
			}
			
			private void setNotation(DocumentEvent e) {
				try {
					txt = e.getDocument().getText(0, e.getDocument().getLength());
					
				} catch (BadLocationException e1) {
					txt = "";
				}
				monitorTblModel.setValueAt(txt, 0, 2);
				try {
					monitorTblModel.setMonitors(SQL_SELECT.monitors((MonitorType)monitorTblModel.getValueAt(0, 1), (String) monitorTblModel.getValueAt(0, 2), (Status) monitorTblModel.getValueAt(0, 5)));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				monitorTblModel.fireTableDataChanged();
				
			}	
		}
		
	class TblStringRenderer extends JLabel implements TableCellRenderer{
		
		private static final long serialVersionUID = 6200115008088445331L;

		public TblStringRenderer() {
			super.setOpaque(true);
		}		
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value.toString());
			if(row>0) {				
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}				
			}else {
				setBackground(Color.white);					
			}
			return this;				
		}
		
	}
	
		
	 public class MonitorTypeTblCellEditor extends AbstractCellEditor implements TableCellEditor {

		
		private static final long serialVersionUID = 6537028958231168566L;
		protected ComboBoxModel<MonitorType> cbxMonitorTypeModel;	
		protected TableCellEditor editor;
		protected JComboBox<MonitorType> cbxMonitorType;
		protected SearchMonitorTypeListener searchMonitorTypeListener;		
		protected AbstractTableModel tblModel;
		protected ArrayList<MonitorType> materialTypes;

		public MonitorTypeTblCellEditor(AbstractTableModel tblModel) {
			
			
			ArrayList<MonitorType> monitorTypes;
			try {
				monitorTypes = SQL_SELECT.monitorTypes(null, "");
				MonitorType[] arr = new MonitorType[monitorTypes.size()]; 		  
		        // ArrayList to Array Conversion 
				
		        for (int i = 0; i < monitorTypes.size(); i++) {
		            arr[i] = monitorTypes.get(i);		
		        }
		        
				cbxMonitorTypeModel = new DefaultComboBoxModel<MonitorType>(arr);
				cbxMonitorType = new JComboBox<MonitorType>(cbxMonitorTypeModel);				
				
				cbxMonitorType.insertItemAt(null, 0);
				
				cbxMonitorType.setRenderer(new ListMonitorTypeRenderer());
				searchMonitorTypeListener = new SearchMonitorTypeListener();
				cbxMonitorType.addItemListener(searchMonitorTypeListener);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


		public ComboBoxModel<MonitorType> getCbxMonitorTypeModel() {
			// TODO Auto-generated method stub
			return cbxMonitorTypeModel;
		}


		@Override
		public Object getCellEditorValue() {
			if (editor != null) {
				return editor.getCellEditorValue();
			}
            return null;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (column == 1 && row == 0) {
                editor = new DefaultCellEditor(cbxMonitorType);
            } 

			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
		 
	 }
		 
	 class ListMonitorTypeRenderer extends JLabel implements ListCellRenderer<MonitorType>{

		private static final long serialVersionUID = -8346710491847958732L;

		public ListMonitorTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}
	
		@Override
		public Component getListCellRendererComponent(JList<? extends MonitorType> list, MonitorType value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof MonitorType) {
				setText(((MonitorType) value).getNotation());
				setFont(new Font("Tahoma", Font.ITALIC, 14));
			}else {
				setText("Alle Monitormodelle");
				setFont(new Font("Tahoma", Font.ITALIC, 14));
			}
			return this;
		}				
	}
		 
	 class SearchMonitorTypeListener implements ItemListener{
		 MonitorType monitorType;
		 
		 protected MonitorType getMonitorType() {
			return monitorType;
		}

		@Override
		 public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					monitorType = (MonitorType) event.getItem();	
				} catch (ClassCastException e) {
					monitorType = null;				
				}
			}else {
				monitorType = null;
			}
				
			try {
				monitorTblModel.setValueAt(monitorType, 0, 1);
				monitorTblModel.setMonitors(SQL_SELECT.monitors((MonitorType) monitorTblModel.getValueAt(0, 1), (String)monitorTblModel.getValueAt(0, 2), (Status)monitorTblModel.getValueAt(0, 5)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	             if (table.getSelectedRow() != -1 && row > 0) {//not for the first row
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
	
	class MyTableRowSorter extends TableRowSorter<MonitorTblModel>{

		@Override
		public void setStringConverter(TableStringConverter stringConverter) {
			// TODO Auto-generated method stub
			super.setStringConverter(stringConverter);
		}

		@Override
		public TableStringConverter getStringConverter() {
			// TODO Auto-generated method stub
			return super.getStringConverter();
		}

		@Override
		public Comparator<?> getComparator(int column) {
			// TODO Auto-generated method stub
			return super.getComparator(column);
		}

		@Override
		protected boolean useToString(int column) {
			// TODO Auto-generated method stub
			return super.useToString(column);
		}

		@Override
		public void toggleSortOrder(int column) {
			// TODO Auto-generated method stub
			super.toggleSortOrder(column);
		}

		@Override
		public int convertRowIndexToView(int index) {
			// TODO Auto-generated method stub
			return super.convertRowIndexToView(index);
		}

		@Override
		public int convertRowIndexToModel(int index) {
			// TODO Auto-generated method stub
			return super.convertRowIndexToModel(index);
		}
		
		
		
	}
}
