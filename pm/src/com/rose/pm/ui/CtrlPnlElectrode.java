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
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import com.rose.Isynet;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.CtrlPnlMonitor.ListMonitorTypeRenderer;
import com.rose.pm.ui.CtrlPnlMonitor.MonitorTypeTblCellEditor;
import com.rose.pm.ui.CtrlPnlMonitor.SearchMonitorTypeListener;
import com.rose.pm.ui.CtrlPnlMonitor.SearchNotationListener;
import com.rose.pm.ui.Editor.SearchStatusTblCellEditor;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblCellLocalDateRenderer;
import com.rose.pm.ui.Renderer.TblCellMaterialIDRenderer;


public class CtrlPnlElectrode extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	DefaultComboBoxModel<ElectrodeType> electrodeTypeModel;
	ElectrodeTblModel electrodeTblModel;
	ElectrodeTypeListener electrodeTypeListener;
	ListCellElectrodeTypeRenderer listCellElectrodeTypeRenderer;
	TblElectrodeTypeRenderer tblElectrodeTypeRenderer;
	Listener listener;	
	NotationListener serialNrListener, noticeListener;
	Renderer renderer;
	Renderer.TblCellLocalDateRenderer tblCellLocalDateRenderer;
	Renderer.TblCellStringRenderer notationRenderer;
	Renderer.TblCellMaterialIDRenderer tblCellMaterialIDRenderer;
	Renderer.TblCellStatusRenderer tblStatusRenderer;
	Renderer.TblCellImplantDateRenderer tblImplantDateRenderer;
	Renderer.TblCellPatientRenderer tblCellPatientRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	CreateListener createListener;
	TblMouseAdaptor tblMouseAdaptor;
	DeleteListener deleteListener; 
	ElectrodeTypeTblCellEditor electrodeTypeTblCellEditor;
	Editor editor;
	Editor.SearchStatusTblCellEditor searchStatusTblCellEditor;	
	Editor.DateCellEditor dateCellEditor;
	
	public CtrlPnlElectrode() {
		createPanel();
		setComponentLabeling();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlElectrode)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		electrodeTypeTblCellEditor = new ElectrodeTypeTblCellEditor();
		((PnlElectrode)panel).setElectrodeTypeTblCellEditor(electrodeTypeTblCellEditor);
		editor = new Editor();
		searchStatusTblCellEditor = editor.new SearchStatusTblCellEditor(electrodeTblModel, panel);
		((PnlElectrode)panel).setStatusTblCellEditor(searchStatusTblCellEditor);
		
		JTextField textField = new JTextField("Text");
		SearchNotationListener searchNotationListener = new SearchNotationListener();
		textField.getDocument().addDocumentListener(searchNotationListener);		
		((PnlElectrode)panel).setNotationCellEditor(new DefaultCellEditor(textField));
		//panel.table.setAutoCreateRowSorter(true);
		panel.setFirstRowHeight(40);//the standard height of the first row
	}
	
	protected void createPanel() {
		panel = new PnlElectrode();
		panel.setName("Elektroden");
		setListener();
		setModel();	
		setEditor();		
		setRenderer();
		((PnlElectrode)panel).setElectrodeTypeSelectionIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlElectrode)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	protected void setComponentLabeling() {
		((PnlElectrode)panel).setLblElectrodeTypeText("Elektrodenmodel:");
		((PnlElectrode)panel).setLblSerialNrText("Seriennummer:");
		((PnlElectrode)panel).setLblNoticeText("Bemerkung:");
		((PnlElectrode)panel).setBtnCreateText("Einfügen");
		((PnlElectrode)panel).setBtnDeleteText("Löschen");
		((PnlElectrode)panel).setBtnShowAllText("Alle Modelle");
	}
	
	void setModel() {
		try {
			ArrayList<ElectrodeType> electrodeTypes = SQL_SELECT.electrodeTypes(null, "");
			ElectrodeType[] arr = new ElectrodeType[electrodeTypes.size()]; 	
			 electrodeTypeModel = new DefaultComboBoxModel<ElectrodeType>();
	        // ArrayList to Array Conversion 
	        for (int i = 0; i < electrodeTypes.size(); i++) {
	            arr[i] = electrodeTypes.get(i);		
	            electrodeTypeModel.addElement(electrodeTypes.get(i));
		 	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		((PnlElectrode)panel).setElectrodeTypeModel(electrodeTypeModel);
		
		try {
			electrodeTblModel = new ElectrodeTblModel(SQL_SELECT.electrodes(electrodeTypeListener.model, "", Status.Lager));//initial search values
			panel.setTblModel(electrodeTblModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	 
	 private void setListener() {
		 electrodeTypeListener = new ElectrodeTypeListener();//listener for the comboBox, that shows the types of electrodes
		 ((PnlElectrode)panel).addElectrodeTypeListener(electrodeTypeListener);	
		 listener = new Listener();
		 serialNrListener = listener.new NotationListener();
		 ((PnlElectrode)panel).addSerialNrListener(serialNrListener);
		 noticeListener = listener.new NotationListener();
		 ((PnlElectrode)panel).addNoticeListener(noticeListener);
		 tblRowSelectionListener = new TblRowSelectionListener();
		 ((PnlElectrode)panel).addTblRowSelectionListener(tblRowSelectionListener);
		 createListener = new CreateListener();
		 ((PnlElectrode)panel).addCreateListener(createListener);
		 deleteListener = new DeleteListener();
		 ((PnlElectrode)panel).addDeleteListener(deleteListener);
	 }
	 
	 private void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlElectrode)panel).setDateCellEditor(dateCellEditor);
	 }
	 
	 private void setRenderer() {
		 renderer = new Renderer();
		 listCellElectrodeTypeRenderer = new ListCellElectrodeTypeRenderer();
		 ((PnlElectrode)panel).setElectrodeTypeRenderer(listCellElectrodeTypeRenderer);		
		 tblCellMaterialIDRenderer = renderer.new TblCellMaterialIDRenderer();
		((PnlElectrode)panel).setTblElectrodeIDRenderer(Electrode.class, tblCellMaterialIDRenderer);
		tblElectrodeTypeRenderer = new TblElectrodeTypeRenderer();
		 ((PnlElectrode)panel).setTblElectrodeTypeRenderer(ElectrodeType.class, tblElectrodeTypeRenderer);
		 notationRenderer = renderer.new TblCellStringRenderer();
		((PnlElectrode)panel).setTblCellStringRenderer(String.class, notationRenderer);
		  tblCellPatientRenderer = renderer.new TblCellPatientRenderer();
		 ((PnlElectrode)panel).setTblPatientRenderer(Patient.class, tblCellPatientRenderer);
		 tblStatusRenderer = renderer.new TblCellStatusRenderer();
		 ((PnlElectrode)panel).setTblStatusRenderer(Status.class, tblStatusRenderer);
		 tblImplantDateRenderer = renderer.new TblCellImplantDateRenderer();
		 ((PnlElectrode)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer);
		 tblCellLocalDateRenderer = renderer.new TblCellLocalDateRenderer();
		 ((PnlElectrode)panel).setLocalDateRenderer(LocalDate.class, tblCellLocalDateRenderer);
	 }
	 
	 private Boolean isElectrodeProvided(Electrode electrode) {		
		 if(electrode.getPatient() instanceof Patient) {
			 return true;
		 }else {
			 return false;
		 }
	 }

	 class ElectrodeTblModel extends AbstractTableModel{

		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends Electrode> electrodes;
		@SuppressWarnings("rawtypes")
		Class[] classes = {Electrode.class, ElectrodeType.class, String.class, LocalDate.class, String.class, Status.class, Patient.class, Date.class};
		
		protected Electrode searchElectrode = null;
		protected ElectrodeType searchElectrodeType = null;
		protected String searchNotation = "";
		protected LocalDate searchLocalDate = null;
		protected String searchNotice = "";
		protected Status searchStatus = Status.Lager;
		protected Patient searchPatient = null;
		protected Date searchDate = null;
		
		public ElectrodeTblModel(ArrayList<? extends Electrode> electrodes) {
			this.electrodes = electrodes;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Bemerkung");
			columnNames.add("Status");
			columnNames.add("Patient");
			columnNames.add("Implantationsdatum");
		}
		
		
		
		@Override
		public void fireTableDataChanged() {
			super.fireTableDataChanged();
			panel.setFirstRowHeight(40);//set the height of the first row 
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
		public int getRowCount() {
			return this.electrodes.size() + 1;
		}
		
		@Override
		public Class getColumnClass(int col) {
			return classes[col];			
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(rowIndex > 0) {	
				switch(columnIndex) {
					case 0: return electrodes.get(rowIndex - 1);
					case 1: return electrodes.get(rowIndex - 1).getElectrodeType();
					case 2: return electrodes.get(rowIndex - 1).getSerialNr();					
					case 3: return electrodes.get(rowIndex - 1).getExpireDate();					
					case 4: return electrodes.get(rowIndex - 1).getNotice();					
					case 5: return electrodes.get(rowIndex - 1).getStatus();					
					case 6: return electrodes.get(rowIndex - 1).getPatient();					
					case 7: return electrodes.get(rowIndex - 1).getDateOfImplantation();					
					default: return null;
				}			
			}else{
				switch (columnIndex) {
					case 0: return searchElectrode;
					case 1: return searchElectrodeType;
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
					searchElectrodeType = (ElectrodeType)value;
					break;		
				case 2:
					searchNotation = (String)value;
					break;
				case 5:
					searchStatus = (Status)value;					
					break;
				}
			}else {
	            electrodes.get(row).setDateOfImplantation((Date)value);
	            try {
					SQL_UPDATE.dateOfImplant(electrodes.get(row));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            //change value at database;
			}
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

		protected void setElectrodes(ArrayList<? extends Electrode> el) {
			this.electrodes = el;		
		}
			
	}
	 
	 public class ElectrodeTypeTblCellEditor extends AbstractCellEditor implements TableCellEditor {

		private static final long serialVersionUID = -2802019602401796233L;
		
		private TableCellEditor editor;
		private JComboBox<ElectrodeType> cbxElectrodeType;
		SearchElectrodeTypeListener searchElectrodeTypeListener;
		private ComboBoxModel<ElectrodeType> cbxElectrodeTypeModel;	

		protected ComboBoxModel<ElectrodeType> getCbxElectrodeTypeModel() {
			return cbxElectrodeTypeModel;
		}
		
		public ElectrodeTypeTblCellEditor() {
			ArrayList<ElectrodeType> electrodeTypes;
			try {
				electrodeTypes = SQL_SELECT.electrodeTypes(null, "");
				ElectrodeType[] arr = new ElectrodeType[electrodeTypes.size()]; 		  
		        // ArrayList to Array Conversion 
				
		        for (int i = 0; i < electrodeTypes.size(); i++) {
		            arr[i] = electrodeTypes.get(i);		
		        }
		        
				cbxElectrodeTypeModel = new DefaultComboBoxModel<ElectrodeType>(arr);
				cbxElectrodeType = new JComboBox<ElectrodeType>();				
				cbxElectrodeType.setModel(cbxElectrodeTypeModel);
				
				cbxElectrodeType.insertItemAt(null, 0);
				
				cbxElectrodeType.setRenderer(new ListElectrodeTypeRenderer());
				searchElectrodeTypeListener = new SearchElectrodeTypeListener();
				cbxElectrodeType.addItemListener(searchElectrodeTypeListener);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
                editor = new DefaultCellEditor(cbxElectrodeType);
            } 

			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
		 
	 }
	 
	 class SearchElectrodeTypeListener implements ItemListener{
		 ElectrodeType electrodeType;
		 
		 protected ElectrodeType getElectrodeType() {
			return electrodeType;
		}

		@Override
		 public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					electrodeType = (ElectrodeType) event.getItem();	
				} catch (ClassCastException e) {
					electrodeType = null;				
				}
			}else {
				electrodeType = null;
			}
				
			try {
				electrodeTblModel.setValueAt(electrodeType, 0, 1);
				electrodeTblModel.setElectrodes(SQL_SELECT.electrodes((ElectrodeType) electrodeTblModel.getValueAt(0, 1), (String)electrodeTblModel.getValueAt(0, 2), (Status)electrodeTblModel.getValueAt(0, 5)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			electrodeTblModel.fireTableDataChanged();
					
		 }		 
	 }
	 
	 class ListElectrodeTypeRenderer extends JLabel implements ListCellRenderer<ElectrodeType>{

		private static final long serialVersionUID = -5257225134787861745L;

		public ListElectrodeTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}
	
		@Override
		public Component getListCellRendererComponent(JList<? extends ElectrodeType> list, ElectrodeType value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof ElectrodeType) {
				setText(((ElectrodeType) value).getNotation());
				setFont(new Font("Tahoma", Font.ITALIC, 14));
			}else {
				setText("Alle Elektrodenmodelle");
				setFont(new Font("Tahoma", Font.ITALIC, 14));
			}
			return this;
		}				
	}
	 
	 class ElectrodeTypeListener implements ItemListener{

		ElectrodeType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (ElectrodeType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}				
		    }
			//updateTblModel();		
		}
		
		protected void updateTblModel() {
			Status status;
			try {
				status = searchStatusTblCellEditor.getSearchStatusListener().getStatus();
			}catch(NullPointerException e) {
				status = null;
			}
			try {
				electrodeTblModel.setElectrodes(SQL_SELECT.electrodes(model, electrodeTblModel.searchNotation, status));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			electrodeTblModel.fireTableDataChanged();
		}		
	}
	 
	 class ListCellElectrodeTypeRenderer extends JLabel implements ListCellRenderer<ElectrodeType>{

		private static final long serialVersionUID = -7934129282290942751L;
		
		public ListCellElectrodeTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ElectrodeType> list, ElectrodeType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof ElectrodeType) {
				setText(((ElectrodeType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
	 }
	 
	 class TblElectrodeTypeRenderer extends JLabel implements TableCellRenderer{
		 
		 public TblElectrodeTypeRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		 }
		
		 private static final long serialVersionUID = -7162535661899399897L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {//for all but the first row
				if(value instanceof ElectrodeType) {				
					String notation = ((ElectrodeType)value).getNotation();
					setText(notation);
					if(isSelected) {
						setBackground(Color.ORANGE);
					}else {
						setBackground(row%2==0 ? Color.white : Color.lightGray);   
					}
					setBorder(null);
				}					
			}else if(row==0){//for the first 'search' row
				Border border = BorderFactory.createLineBorder(Color.ORANGE, 2);
				setBorder(border);
				setBackground(Color.white);	
				
				if(value instanceof ElectrodeType) {
					setText(((ElectrodeType) value).getNotation());
				}else {
					setText("Alle Elektrodenmodelle");					
				}
			}
			return this;
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
				electrodeTblModel.setValueAt(txt, 0, 2);
				try {
					electrodeTblModel.setElectrodes(SQL_SELECT.electrodes((ElectrodeType)electrodeTblModel.getValueAt(0, 1), (String) electrodeTblModel.getValueAt(0, 2), (Status) electrodeTblModel.getValueAt(0, 5)));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				electrodeTblModel.fireTableDataChanged();
				
			}	
		}
	class TblRowSelectionListener implements ListSelectionListener{
		Electrode electrode;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlElectrode)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlElectrode)panel).getSelectedTblRow();
	            electrode = (Electrode) ((PnlElectrode)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		
		protected Electrode getElectrodeSelected() {
			return electrode;
		}		
	}
		
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tblRowSelectionListener.getElectrodeSelected() instanceof Electrode) {
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					if(SQL_UPDATE.deleteElectrode(tblRowSelectionListener.getElectrodeSelected())){
						electrodeTblModel.electrodes.remove(tblRowSelectionListener.getElectrodeSelected());
						electrodeTblModel.fireTableDataChanged();
					}
				}
			
			}
		
		}
	}
	
	class CreateListener implements ActionListener{
		Electrode electrode;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && electrodeTypeModel.getSelectedItem() instanceof ElectrodeType) {
				initiateElectrode();
				electrode.setExpireDate(ctrlPnlSetDate.getDate());
				electrode.setSerialNr(serialNrListener.getNotation());
				electrode.setNotice(noticeListener.getNotation());
				updateDBAndTblModel();
				
			}
		}
		
		protected void initiateElectrode() {
			electrode = new Electrode((ElectrodeType) electrodeTypeModel.getSelectedItem());			
		}
		
		protected void updateDBAndTblModel() {
			Integer key = null;;
			try {
				key = SQL_INSERT.electrode(electrode);					
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: CreateListener/updateDBAndTblModel()", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			if(key != null) {//if insertion was successful
				electrodeTblModel.setValueAt(Status.Lager, 0, 5);
				electrodeTblModel.setValueAt(electrodeTypeModel.getSelectedItem(), 0, 1);
				for(int i = 0; i < electrodeTypeTblCellEditor.getCbxElectrodeTypeModel().getSize(); i++) {
					try {
						if(electrodeTypeTblCellEditor.getCbxElectrodeTypeModel().getElementAt(i).getNotation().equals(((ElectrodeType)electrodeTypeModel.getSelectedItem()).getNotation())) {
							electrodeTypeTblCellEditor.getCbxElectrodeTypeModel().setSelectedItem(electrodeTypeTblCellEditor.getCbxElectrodeTypeModel().getElementAt(i));
						}
					}catch(NullPointerException e) {//if (monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i) == null
						//do nothing
					}
				}
				try {
					electrodeTblModel.setElectrodes(SQL_SELECT.electrodes((ElectrodeType) electrodeTypeModel.getSelectedItem(), (String)electrodeTblModel.getValueAt(0, 2), (Status)electrodeTblModel.getValueAt(0, 5)));
			
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			electrodeTblModel.fireTableDataChanged();
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
	             if (table.getSelectedRow() != -1 && row > 0) {
	                CtrlDlgChangeElectrode ctrlDlgChangeElectrode = new CtrlDlgChangeElectrode((Electrode) electrodeTblModel.getValueAt(row, 0), electrodeTblModel);
	                ctrlDlgChangeElectrode.getDialog().setVisible(true);
	             }
	        }else if(SwingUtilities.isRightMouseButton(mouseEvent) == true){
	        	table =(JTable) mouseEvent.getSource();
	        	Point point = mouseEvent.getPoint();
	        	int row = table.rowAtPoint(point);
	        	if(table.getSelectedRow() == row) {
	        		Isynet isynet = new Isynet();
	        		Patient patient = isynet.getPatient();
	        		PopupMenu menu = new PopupMenu(patient, (Electrode) electrodeTblModel.getValueAt(row, 0), electrodeTblModel);
	                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	        	}
	        }
	        		
	    }
	}
	
	
	

	
	
	
	
	
}
