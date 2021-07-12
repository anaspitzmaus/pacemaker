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

import com.rose.dataExchange.Isynet;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.PM;
import com.rose.pm.material.Status;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblDoubleRenderer;

public class CtrlPnlPM extends CtrlPnlBase{

	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	Renderer renderer;
	NotationListener serialNrListener, noticeListener;
	DefaultComboBoxModel<AggregateType> aggregateTypeModel;
	ListCellAggregateTypeRenderer listAggregateTypeRenderer;
	AggregateTypeListener aggregateTypeListener;
	AggregateTblModel aggregateTblModel;
	CreateListener createListener;	
	TblCellAggregateTypeRenderer tblAggregateTypeRenderer;
	Renderer.TblCellStringRenderer tblStringRenderer;
	Renderer.TblCellStatusRenderer tblStatusRenderer;
	Renderer.TblCellImplantDateRenderer tblImplantDateRenderer;
	Renderer.TblCellLocalDateRenderer tblDateRenderer;
	Renderer.TblCellMaterialIDRenderer tblCellMaterialIDRenderer;
	Renderer.TblCellPatientRenderer tblPatientRenderer;	
	TblRowSelectionListener tblRowSelectionListener;
	DeleteListener deleteListener;
	TblMouseAdaptor tblMouseAdaptor;
	Editor editor;
	Editor.DateCellEditor dateCellEditor;
	SearchNotationListener searchNotationListener;
	Editor.SearchStatusTblCellEditor statusTblCellEditor;
	PMTypeTblCellEditor pmTypeTblCellEditor;
	TblDoubleRenderer tblDoubleRenderer;
	
	
	public CtrlPnlPM() {
		createPanel();
		setComponentLabeling();		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlPM)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		
		statusTblCellEditor = editor.new SearchStatusTblCellEditor(aggregateTblModel, panel);
		((PnlPM)panel).setStatusTblCellEditor(statusTblCellEditor);
		JTextField textField = new JTextField("Text");		
		textField.getDocument().addDocumentListener(searchNotationListener);		
		((PnlPM)panel).setNotationCellEditor(new DefaultCellEditor(textField));
		//panel.table.setAutoCreateRowSorter(true);
		panel.setFirstRowHeight(40);//the standard height of the first row
		
		setStandardListener();	
	}
	
	protected void createPanel() {
		panel = new PnlPM();
		panel.setName("Schrittmacher");
		setListener();
		setModel();
		setEditor();
		setRenderer();
		((PnlPM)panel).setAggregateTypeSelectionIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlPM)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	protected void setComponentLabeling() {
		((PnlPM)panel).setLblAggregatTypeText("Schrittmachermodel:");
		((PnlPM)panel).setLblSerialNrText("Seriennummer:");
		((PnlPM)panel).setLblNoticeText("Bemerkung:");
		((PnlPM)panel).setBtnCreateText("Einfügen");
		((PnlPM)panel).setBtnDeleteText("Löschen");
		((PnlPM)panel).setBtnShowAllText("Alle Modelle");
	}
	
	private void setStandardListener() {
		listener = new Listener();
		serialNrListener = listener.new NotationListener();
		((PnlPM)panel).addSerialNrListener(serialNrListener);
		noticeListener = listener.new NotationListener();
		((PnlPM)panel).addNoticeListener(noticeListener);		
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlPM)panel).addTblRowSelectionListener(tblRowSelectionListener);
		deleteListener = new DeleteListener();
		((PnlPM)panel).addDeleteListener(deleteListener);
		
	}
	
	/**
	 * overridable Listeners
	 */
	protected void setListener() {
		createListener = new CreateListener();
		((PnlPM)panel).addCreateListener(createListener);
		aggregateTypeListener = new AggregateTypeListener();
		((PnlPM)panel).addAggregateTypeListener(aggregateTypeListener);	
		searchNotationListener = new SearchNotationListener();
	}
	
	 protected void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlPM)panel).setDateCellEditor(dateCellEditor);
		 //set table cell editor for the type of aggregate
		 pmTypeTblCellEditor = new PMTypeTblCellEditor(aggregateTblModel);
		((PnlPM)panel).setPMTypeTblCellEditor(pmTypeTblCellEditor);
	 }
	
	 void setModel() {
		ArrayList<AggregateType> aggregateTypes;
		try {
			aggregateTypes = SQL_SELECT.pacemakerKinds(null, "");
			//AggregateType[] arr = new AggregateType[aggregateTypes.size()]; 
			aggregateTypeModel = new DefaultComboBoxModel<AggregateType>();
			 for (int i = 0; i < aggregateTypes.size(); i++) {
		            //arr[i] = aggregateTypes.get(i);		
		            aggregateTypeModel.addElement(aggregateTypes.get(i));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
				  
       	((PnlPM)panel).setAggregatTypeModel(aggregateTypeModel);
       	
       	
		try {
			aggregateTblModel = new AggregateTblModel(SQL_SELECT.pacemakers(aggregateTypeListener.pmType, "", Status.Lager));
			panel.setTblModel(aggregateTblModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Schrittmacheraggregate konnten nicht abgefragt werden" + e.getMessage(), "Hinweis", JOptionPane.WARNING_MESSAGE);			
		}
		
	}
	
	protected void setRenderer() {
		renderer = new Renderer();
		listAggregateTypeRenderer = new ListCellAggregateTypeRenderer();
		((PnlPM)panel).setAggregatTypeRenderer(listAggregateTypeRenderer);
		 tblCellMaterialIDRenderer = renderer.new TblCellMaterialIDRenderer();
		((PnlPM)panel).setTblPMIDRenderer(PM.class, tblCellMaterialIDRenderer);
		tblAggregateTypeRenderer = new TblCellAggregateTypeRenderer();
		((PnlPM)panel).setTblAggregateTypeRenderer(AggregateType.class, tblAggregateTypeRenderer);
		tblStringRenderer = renderer.new TblCellStringRenderer();
		((PnlPM)panel).setStringRenderer(String.class, tblStringRenderer);
		tblDateRenderer = renderer.new TblCellLocalDateRenderer();
		((PnlPM)panel).setDateRenderer(LocalDate.class, tblDateRenderer);		
		tblStatusRenderer = renderer.new TblCellStatusRenderer();
		((PnlPM)panel).setStatusRenderer(Status.class, tblStatusRenderer);
		 tblPatientRenderer = renderer.new TblCellPatientRenderer();
		 ((PnlPM)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
		 tblImplantDateRenderer = renderer.new TblCellImplantDateRenderer();
		 ((PnlPM)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer);
		 tblDoubleRenderer = renderer.new TblDoubleRenderer();
		 ((PnlPM)panel).setTblPriceRenderer(Double.class, tblDoubleRenderer);
	}
	
	/**
	 * item listener for the comboBox that displays the types of aggregates
	 * @author Ekki
	 *
	 */
	class AggregateTypeListener implements ItemListener{

		AggregateType pmType;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					pmType = (AggregateType) event.getItem();			        
				} catch (ClassCastException e) {
					pmType = null;
				}				
		    }
			//updateTblModel();		
		}
		
//		protected void updateTblModel() {
//			Status status;
//			try {
//				status = statusTblCellEditor.getSearchStatusListener().getStatus();
//			}catch(NullPointerException e) {
//				status = null;
//			}
//			try {
//				aggregateTblModel.setAggregats(SQL_SELECT.pacemakers(pmType, aggregateTblModel.searchNotation, status));
//				aggregateTblModel.fireTableDataChanged();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//			
//		}		
	}
	
	class ListCellAggregateTypeRenderer extends JLabel implements ListCellRenderer<AggregateType>{

		private static final long serialVersionUID = -7934129282290942751L;
		
		public ListCellAggregateTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends AggregateType> list, AggregateType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof AggregateType) {
				setText(((AggregateType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	
	
	class CreateListener implements ActionListener{
		PM pm;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && aggregateTypeModel.getSelectedItem() instanceof AggregateType) {
				initiateAggregate();
				pm.setExpireDate(ctrlPnlSetDate.getDate());
				pm.setSerialNr(serialNrListener.getNotation());
				pm.setNotice(noticeListener.getNotation());
				pm.setStatus(Status.Lager);
				updateDBAndTblModel();
				((PnlPM)panel).clearComponents();
				serialNrListener.notation = "";
			}
		}
		
		protected void initiateAggregate() {
			pm = new PM((AggregateType) aggregateTypeModel.getSelectedItem());
		}
		
		protected void updateDBAndTblModel() {
			Integer key = null;
			try {
				key = SQL_INSERT.pacemaker(pm);	
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: CreateListener at CtrlPnlPM", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			if(key != null) {//if insertion was successful
				aggregateTblModel.setValueAt(Status.Lager, 0, 5);
				aggregateTblModel.setValueAt(aggregateTypeModel.getSelectedItem(), 0, 1);
				for(int i = 0; i < pmTypeTblCellEditor.getCbxPMTypeModel().getSize(); i++) {
					try {
						if(pmTypeTblCellEditor.getCbxPMTypeModel().getElementAt(i).getNotation().equals(((AggregateType)aggregateTypeModel.getSelectedItem()).getNotation())) {
							pmTypeTblCellEditor.getCbxPMTypeModel().setSelectedItem(pmTypeTblCellEditor.getCbxPMTypeModel().getElementAt(i));
						}
					}catch(NullPointerException e) {//if (monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i) == null
						//do nothing
					}
				}
				
				try {
					aggregateTblModel.setAggregats(SQL_SELECT.pacemakers((AggregateType) aggregateTypeModel.getSelectedItem(), (String)aggregateTblModel.getValueAt(0, 2), (Status)aggregateTblModel.getValueAt(0, 5)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			aggregateTblModel.fireTableDataChanged();
		}
		
	}
	
	class AggregateTblModel extends AbstractTableModel{
		
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends PM> aggregates;
		protected Class[] columnClass = new Class[] {
			 PM.class, AggregateType.class, String.class, LocalDate.class, String.class, Status.class,  Patient.class, Date.class, Double.class
		};
		
		protected PM searchPM = null;
		protected AggregateType searchPMType = null;
		protected String searchNotation = "";
		protected LocalDate searchLocalDate = null;
		protected String searchNotice = "";
		protected Status searchStatus = Status.Lager;
		protected Patient searchPatient = null;
		protected Date searchDate = null;
		protected Double searchPrice = null;
		
		
		public AggregateTblModel(ArrayList<? extends PM> paceMakers) {
			this.aggregates = paceMakers;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Bemerkung");
			columnNames.add("Status");			
			columnNames.add("Patient");
			columnNames.add("Implantationsdatum");
			columnNames.add("Preis");
		}
		

		@Override
		public void fireTableDataChanged() {			
			super.fireTableDataChanged();
			panel.setFirstRowHeight(40);//set the height of the first row 
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.size();
		}
		
		@Override
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.aggregates.size() + 1;
		}
		
		@Override
		public Class getColumnClass(int col) {
			return columnClass[col];	
		}

		
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(rowIndex > 0) {		
				switch(columnIndex) {
					case 0: return aggregates.get(rowIndex - 1);					
					case 1: return aggregates.get(rowIndex - 1).getMaterialType();					
					case 2: return aggregates.get(rowIndex - 1).getSerialNr();					
					case 3: return aggregates.get(rowIndex - 1).getExpireDate();					
					case 4: return aggregates.get(rowIndex - 1).getNotice();
					case 5: return aggregates.get(rowIndex - 1).getStatus();
					case 6: return aggregates.get(rowIndex - 1).getPatient();					
					case 7: return aggregates.get(rowIndex - 1).getDateOfImplantation();
					case 8: return aggregates.get(rowIndex - 1).getPrice();
					
					default: return null;
				}
			
			}else {
				switch (columnIndex) {
					case 0: return searchPM;
					case 1: return searchPMType;
					case 2: return searchNotation;
					case 3: return searchLocalDate;					
					case 4: return searchNotice;
					case 5: return searchStatus;
					case 6: return searchPatient;
					case 7: return searchDate;
					case 8: return searchPrice;
					default: return null;
				}
			}
			
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
			if(row == 0) {//for the first 'search' row
				switch(col) {
				case 1:
					searchPMType = (AggregateType)value;
					break;		
				case 2:
					searchNotation = (String)value;
					break;
				case 5:
					searchStatus = (Status)value;
					break;
				}
			}else {
	            aggregates.get(row - 1).setDateOfImplantation((Date)value);
	            try {
					SQL_UPDATE.dateOfImplant(aggregates.get(row - 1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            //change value at database;
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
		
		protected void setAggregats(ArrayList<? extends PM> pm) {
			this.aggregates = pm;		
		}
		
	}
	
	 public class PMTypeTblCellEditor extends AbstractCellEditor implements TableCellEditor {

		private static final long serialVersionUID = 2575940233015655236L;
		
		protected ComboBoxModel<AggregateType> cbxPMTypeModel;	
		protected TableCellEditor editor;
		protected JComboBox<AggregateType> cbxPMType;
		protected SearchPMTypeListener searchPMTypeListener;
		protected ListPMTypeRenderer listPMTypeRenderer;
		protected AbstractTableModel tblModel;
		protected ArrayList<AggregateType> materialTypes;
		protected ArrayList<? extends AggregateType> pmTypes;

		public PMTypeTblCellEditor(AbstractTableModel tblModel) {				
			this.tblModel = tblModel;
			selectAggregateTypes();
				
			AggregateType[] arr = new AggregateType[pmTypes.size()]; 		  
	        // ArrayList to Array Conversion 
			
	        for (int i = 0; i < pmTypes.size(); i++) {
	            arr[i] = pmTypes.get(i);		
	        }
	        
			cbxPMTypeModel = new DefaultComboBoxModel<AggregateType>(arr);
			cbxPMType = new JComboBox<AggregateType>();	
			cbxPMType.setModel(cbxPMTypeModel);
			
			cbxPMType.insertItemAt(null, 0);			
			
			setAggregateTypeRenderer();
			cbxPMType.setRenderer(listPMTypeRenderer);
			setAggregateTypeListener();
			cbxPMType.addItemListener(searchPMTypeListener);
		}
		
		/**
		 * set the renderer 
		 */
		protected void setAggregateTypeRenderer() {
			listPMTypeRenderer = new ListPMTypeRenderer();			
		}
		
		protected void setAggregateTypeListener() {
			searchPMTypeListener = new SearchPMTypeListener(this.tblModel);					
		}
			
		/**
		 * get the types of aggregates of all manufacturers by a SELECT statement 
		 * 
		 */
		protected void selectAggregateTypes() {
			try {
				pmTypes = SQL_SELECT.pacemakerKinds(null, "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public ComboBoxModel<AggregateType> getCbxPMTypeModel() {
			// TODO Auto-generated method stub
			return cbxPMTypeModel;
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
                editor = new DefaultCellEditor(cbxPMType);
            } 

			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
		 
	 }
	 
	 class SearchPMTypeListener implements ItemListener{
		 AggregateType pmType;
		 AbstractTableModel tblModel;
		 
		 protected AggregateType getPMType() {
			return pmType;
		}
		 
		 public SearchPMTypeListener(AbstractTableModel tblModel) {
			this.tblModel = tblModel;
		}

		@Override
		 public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					pmType = (AggregateType) event.getItem();	
				} catch (ClassCastException e) {
					pmType = null;				
				}
			}else {
				pmType = null;
			}
				
			
			tblModel.setValueAt(pmType, 0, 1);
			setAggregates();
			tblModel.fireTableDataChanged();					
		 }
		
		protected void setAggregates() {
			try {
				((AggregateTblModel) tblModel).setAggregats(SQL_SELECT.pacemakers((AggregateType) tblModel.getValueAt(0, 1), (String)tblModel.getValueAt(0, 2), (Status)tblModel.getValueAt(0, 5)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				aggregateTblModel.setValueAt(txt, 0, 2);
				setAggregates();
				aggregateTblModel.fireTableDataChanged();
				
			}
			
			protected void setAggregates() {
				try {
					aggregateTblModel.setAggregats(SQL_SELECT.pacemakers((AggregateType)aggregateTblModel.getValueAt(0, 1), (String) aggregateTblModel.getValueAt(0, 2), (Status) aggregateTblModel.getValueAt(0, 5)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	 
	 class ListPMTypeRenderer extends JLabel implements ListCellRenderer<AggregateType>{

		private static final long serialVersionUID = -3407551918200555529L;

			public ListPMTypeRenderer() {
				setOpaque(true);
		        setHorizontalAlignment(CENTER);
		        setVerticalAlignment(CENTER);
			}
		
			@Override
			public Component getListCellRendererComponent(JList<? extends AggregateType> list, AggregateType value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if(value instanceof AggregateType) {
					setText(((AggregateType) value).getNotation());
					setFont(new Font("Tahoma", Font.ITALIC, 14));
				}else {
					setAllAggregateText();
					setFont(new Font("Tahoma", Font.ITALIC, 14));
				}
				return this;
			}
			
			protected void setAllAggregateText() {
				setText("Alle Schrittmachermodelle");
			}
		}
	
	class TblPMIDRenderer extends JLabel implements TableCellRenderer{

		
		private static final long serialVersionUID = -7687161924946698926L;

		public TblPMIDRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((PM)value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
		}
		
	}
	

	class TblStringRenderer extends JLabel implements TableCellRenderer{
	
		/**
		 * 
		 */
		
		public TblStringRenderer() {
			super.setOpaque(true);
		}
		private static final long serialVersionUID = -523838024295879261L;
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
		}
		
	}
	
	class TblStatusRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 1457346148401818937L;

		public TblStatusRenderer() {
			super.setOpaque(true);
			
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Status status = (Status) value;
			setText(status.name());
			
			setHorizontalAlignment(CENTER);
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}		
			
			return this;
		}	
		
	}
	
	class TblCellAggregateTypeRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 2455144833293671793L;

		public TblCellAggregateTypeRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {//for all but the first row
				if(value instanceof AggregateType) {
					String notation = ((AggregateType)value).getNotation();
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
				
				if(value instanceof AggregateType) {
					setText(((AggregateType) value).getNotation());
				}else {
					setLblText("Alle Aggregatmodelle");					
				}
			}			
				
			return this;
		}
		
		protected void setLblText(String txt) {
			setText(txt);
		}
		
	}
	
	
	
	class TblRowSelectionListener implements ListSelectionListener{
		PM pm;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlPM)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlPM)panel).getSelectedTblRow();
	            pm = (PM) ((PnlPM)panel).getTableValueAt(row, 0); //get the aggregate from the first column	
	            
	        }			
		}
		
		protected PM getAggregatSelected() {
			return pm;
		}		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tblRowSelectionListener.getAggregatSelected() instanceof PM) {
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					if(SQL_UPDATE.deleteAggregate(tblRowSelectionListener.getAggregatSelected())){
						aggregateTblModel.aggregates.remove(tblRowSelectionListener.getAggregatSelected());
						aggregateTblModel.fireTableDataChanged();
					}
				}
			
			}
		
		}
	}
	
	class TblMouseAdaptor extends MouseAdapter{
		int row;
		JTable table;
		 @Override
	    public void mouseClicked(MouseEvent mouseEvent){
	        if(mouseEvent.getClickCount()==2){
	        	 JTable table =(JTable) mouseEvent.getSource();
	             Point point = mouseEvent.getPoint();
	             row = table.rowAtPoint(point);
	             if (table.getSelectedRow() != -1 && row >= 0) {
	                initiateDialog();
	             }
	        } else if(SwingUtilities.isRightMouseButton(mouseEvent) == true){
	        	table =(JTable) mouseEvent.getSource();
	        	Point point = mouseEvent.getPoint();
	        	int row = table.rowAtPoint(point);
	        	if(table.getSelectedRow() == row) {
	        		Isynet isynet = new Isynet();
	        		Patient patient = isynet.getPatient();
	        		PopupMenu menu = new PopupMenu(patient, (PM) aggregateTblModel.getValueAt(row, 0), aggregateTblModel);
	                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	        	}
	        }
	    }
		 
		 protected void initiateDialog() {
			 CtrlDlgChangePM ctrlDlgChangePM = new CtrlDlgChangePM((PM) aggregateTblModel.getValueAt(row, 0), aggregateTblModel);
             ctrlDlgChangePM.getDialog().setVisible(true);

		 }
	}
	
	
}
