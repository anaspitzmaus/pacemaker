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
import com.rose.pm.material.MaterialType;
import com.rose.pm.material.SICD;
import com.rose.pm.material.SICDType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblCellPatientRenderer;
import com.rose.pm.ui.Renderer.TblStringRenderer;

public class CtrlPnlSICD extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	Renderer renderer;
	NotationListener serialNrListener, noticeListener;
	DefaultComboBoxModel<SICDType> sicdTypeModel;
	SICDTypeRenderer sicdTypeRenderer;
	SICDTypeListener sicdTypeListener;
	SICDTblModel sicdTblModel;
	CreateListener createListener;
	TblStringRenderer tblStringRenderer;
	TblSICDTypeRenderer tblSICDTypeRenderer;
	Renderer.TblCellLocalDateRenderer tblCellLocalDateRenderer;
	Renderer.TblCellStringRenderer notationRenderer;
	Renderer.TblCellMaterialIDRenderer tblCellMaterialIDRenderer;
	Renderer.TblCellStatusRenderer tblStatusRenderer;
	Renderer.TblCellImplantDateRenderer tblImplantDateRenderer;
	Renderer.TblCellPatientRenderer tblCellPatientRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	DeleteListener deleteListener;
	TblMouseAdaptor tblMouseAdaptor;
	TblCellPatientRenderer tblPatientRenderer;
	SICDTypeTblCellEditor sicdTypeTblCellEditor;
	Editor editor;
	Editor.SearchStatusTblCellEditor searchStatusTblCellEditor;	
	Editor.DateCellEditor dateCellEditor;
	
	
	
	public CtrlPnlSICD() {
		createPanel();
		setComponentLabeling();		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlSICD)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		sicdTypeTblCellEditor = new SICDTypeTblCellEditor();
		((PnlSICD)panel).setSICDTypeTblCellEditor(sicdTypeTblCellEditor);
		
		searchStatusTblCellEditor = editor.new SearchStatusTblCellEditor(sicdTblModel, panel);
		((PnlSICD)panel).setStatusTblCellEditor(searchStatusTblCellEditor);
		
		JTextField textField = new JTextField("Text");
		SearchNotationListener searchNotationListener = new SearchNotationListener();
		textField.getDocument().addDocumentListener(searchNotationListener);		
		((PnlSICD)panel).setNotationCellEditor(new DefaultCellEditor(textField));
		
		((PnlSICD)panel).setAggregateTypeSelectionIndex(-1);
		((PnlSICD)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setFirstRowHeight(40);//the standard height of the first row
	}
	
	protected void createPanel() {
		panel = new PnlSICD();
		panel.setName("SICD");
		setStandardListener();
		setListener();
		setModel();
		setEditor();
		setRenderer();
	}
	
	protected void setComponentLabeling() {
		((PnlSICD)panel).setLblAggregatTypeText("SICD-Model:");
		((PnlSICD)panel).setLblSerialNrText("Seriennummer:");
		((PnlSICD)panel).setLblNoticeText("Bemerkung:");
		((PnlSICD)panel).setBtnCreateText("Einfügen");
		((PnlSICD)panel).setBtnDeleteText("Löschen");
		((PnlSICD)panel).setBtnShowAllText("Alle Modelle");
	}
	
	private void setStandardListener() {
		listener = new Listener();
		serialNrListener = listener.new NotationListener();
		((PnlSICD)panel).addSerialNrListener(serialNrListener);
		noticeListener = listener.new NotationListener();
		((PnlSICD)panel).addNoticeListener(noticeListener);		
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlSICD)panel).addTblRowSelectionListener(tblRowSelectionListener);
		deleteListener = new DeleteListener();
		((PnlSICD)panel).addDeleteListener(deleteListener);
		
	}
	
	/**
	 * overridable Listeners
	 */
	protected void setListener() {
		createListener = new CreateListener();
		((PnlSICD)panel).addCreateListener(createListener);
		sicdTypeListener = new SICDTypeListener();
		((PnlSICD)panel).addAggregateTypeListener(sicdTypeListener);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlSICD)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	 protected void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlSICD)panel).setDateCellEditor(dateCellEditor);
	 }
	
	 protected void setModel() {
		 ArrayList<? extends MaterialType> aggregateTypes;
		try {
			aggregateTypes = SQL_SELECT.sicdTypes(null, "");
			
			sicdTypeModel = new DefaultComboBoxModel<SICDType>();
	       
	        for (int i = 0; i < aggregateTypes.size(); i++) 
	          
				sicdTypeModel.addElement((SICDType) aggregateTypes.get(i));
			
			((PnlSICD)panel).setAggregateTypeModel(sicdTypeModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		try {
			sicdTblModel = new SICDTblModel(SQL_SELECT.sicds((SICDType) sicdTypeListener.model, "", Status.Lager));
			((PnlSICD)panel).setAggregateTblModel(sicdTblModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	 
	 private void setRenderer() {
			renderer = new Renderer();
			sicdTypeRenderer = new SICDTypeRenderer();
			((PnlSICD)panel).setAggregateTypeRenderer(sicdTypeRenderer);
			 tblCellMaterialIDRenderer = renderer.new TblCellMaterialIDRenderer();
			((PnlSICD)panel).setTblSICDIDRenderer(SICD.class, tblCellMaterialIDRenderer);
			 notationRenderer = renderer.new TblCellStringRenderer();
			((PnlSICD)panel).setTblCellStringRenderer(String.class, notationRenderer);
			tblCellLocalDateRenderer = renderer.new TblCellLocalDateRenderer();
			((PnlSICD)panel).setDateRenderer(LocalDate.class, tblCellLocalDateRenderer);
			tblSICDTypeRenderer = new TblSICDTypeRenderer();
			((PnlSICD)panel).setTblAggregateTypeRenderer(SICDType.class, tblSICDTypeRenderer);
			tblStatusRenderer = renderer.new TblCellStatusRenderer();
			((PnlSICD)panel).setStatusRenderer(Status.class, tblStatusRenderer);
			tblPatientRenderer = renderer.new TblCellPatientRenderer();
			((PnlSICD)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
			tblImplantDateRenderer = renderer.new TblCellImplantDateRenderer();
			((PnlSICD)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer); 
		}
	
	class SICDTblModel extends AbstractTableModel{

		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends SICD> sicds;
		private final Class[] columnClass = new Class[] {
			 SICD.class, SICDType.class, String.class, LocalDate.class, String.class, Status.class, Patient.class, Date.class
		};
		
		protected SICD searchSICD = null;
		protected SICDType searchSICDType = null;
		protected String searchNotation = "";
		protected LocalDate searchLocalDate = null;
		protected String searchNotice = "";
		protected Status searchStatus = Status.Lager;
		protected Patient searchPatient = null;
		protected Date searchDate = null;
		
		public SICDTblModel(ArrayList<? extends SICD> sicds) {
			this.sicds = sicds;
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
			return this.sicds.size() + 1;
		}
		
		@Override
		public Class getColumnClass(int col) {
			return columnClass[col];
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(rowIndex > 0) {				
				switch(columnIndex) {
					case 0: return sicds.get(rowIndex - 1);			
					case 1: return sicds.get(rowIndex - 1).getMaterialType();			
					case 2: return sicds.get(rowIndex - 1).getSerialNr();			
					case 3: return sicds.get(rowIndex - 1).getExpireDate();			
					case 4: return sicds.get(rowIndex - 1).getNotice();			
					case 5: return sicds.get(rowIndex - 1).getStatus();			
					case 6: return sicds.get(rowIndex - 1).getPatient();			
					case 7: return sicds.get(rowIndex - 1).getDateOfImplantation();			
					default: return null;	
				}
			}else {
				switch (columnIndex) {
					case 0: return searchSICD;
					case 1: return searchSICDType;
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
					searchSICDType = (SICDType)value;
					break;		
				case 2:
					searchNotation = (String)value;
					break;
				case 5:
					searchStatus = (Status)value;					
					break;
				}
			}else {
				sicds.get(row - 1).setDateOfImplantation((Date)value);
	            try {
					SQL_UPDATE.dateOfImplant(sicds.get(row - 1));
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
		
		protected void setSICDs(ArrayList<? extends SICD> sicds) {
			this.sicds = sicds;		
		}
		
	}
	
	public class SICDTypeTblCellEditor extends AbstractCellEditor implements TableCellEditor{

		private static final long serialVersionUID = 8559400690835752900L;

		private TableCellEditor editor;
		private JComboBox<SICDType> cbxSICDType;
		SearchSICDTypeListener searchSICDTypeListener;
		private ComboBoxModel<SICDType> cbxSICDTypeModel;	

		protected ComboBoxModel<SICDType> getCbxSICDTypeModel() {
			return cbxSICDTypeModel;
		}
		
		public SICDTypeTblCellEditor() {
			ArrayList<SICDType> electrodeTypes;
			try {
				electrodeTypes = SQL_SELECT.sicdTypes(null, "");
				SICDType[] arr = new SICDType[electrodeTypes.size()]; 		  
		        // ArrayList to Array Conversion 
				
		        for (int i = 0; i < electrodeTypes.size(); i++) {
		            arr[i] = electrodeTypes.get(i);		
		        }
		        
				cbxSICDTypeModel = new DefaultComboBoxModel<SICDType>(arr);
				cbxSICDType = new JComboBox<SICDType>();				
				cbxSICDType.setModel(cbxSICDTypeModel);
				
				cbxSICDType.insertItemAt(null, 0);
				
				cbxSICDType.setRenderer(new ListSICDTypeRenderer());
				searchSICDTypeListener = new SearchSICDTypeListener();
				cbxSICDType.addItemListener(searchSICDTypeListener);
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
                editor = new DefaultCellEditor(cbxSICDType);
            } 

			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
		
	}
	
	 class SearchSICDTypeListener implements ItemListener{
		 SICDType electrodeType;
		 
		 protected SICDType getSICDType() {
			return electrodeType;
		}

		@Override
		 public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					electrodeType = (SICDType) event.getItem();	
				} catch (ClassCastException e) {
					electrodeType = null;				
				}
			}else {
				electrodeType = null;
			}
				
			try {
				sicdTblModel.setValueAt(electrodeType, 0, 1);
				sicdTblModel.setSICDs(SQL_SELECT.sicds((SICDType) sicdTblModel.getValueAt(0, 1), (String)sicdTblModel.getValueAt(0, 2), (Status)sicdTblModel.getValueAt(0, 5)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sicdTblModel.fireTableDataChanged();
					
		 }		 
	 }
	 
	 class ListSICDTypeRenderer extends JLabel implements ListCellRenderer<SICDType>{

		private static final long serialVersionUID = 5733785645190504175L;

			public ListSICDTypeRenderer() {
				setOpaque(true);
		        setHorizontalAlignment(CENTER);
		        setVerticalAlignment(CENTER);
			}
		
			@Override
			public Component getListCellRendererComponent(JList<? extends SICDType> list, SICDType value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if(value instanceof SICDType) {
					setText(((SICDType) value).getNotation());
					setFont(new Font("Tahoma", Font.ITALIC, 14));
				}else {
					setText("Alle SICD-Modelle");
					setFont(new Font("Tahoma", Font.ITALIC, 14));
				}
				return this;
			}				
		}
	
	class SICDTypeListener implements ItemListener{

		SICDType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (SICDType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}				
		    }
			//updateTblModel();		
		}
		
//		protected void updateTblModel() {
//			try {
//				sicdTblModel.setSICDs(SQL_SELECT.sicds(model));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//			sicdTblModel.fireTableDataChanged();
//		}		
	}
	
	class SICDTypeRenderer extends JLabel implements ListCellRenderer<SICDType>{

		private static final long serialVersionUID = -7934129282290942751L;
		
		public SICDTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends SICDType> list, SICDType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof SICDType) {
				setText(((SICDType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	class CreateListener implements ActionListener{
		SICD sicd;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && sicdTypeModel.getSelectedItem() instanceof SICDType) {
				initiateAggregate();
				sicd.setExpireDate(ctrlPnlSetDate.getDate());
				sicd.setSerialNr(serialNrListener.getNotation());
				sicd.setNotice(noticeListener.getNotation());
				sicd.setStatus(Status.Lager);
				updateDBAndTblModel();
				((PnlSICD)panel).clearComponents();
				serialNrListener.notation = "";
			}
		}
		
		protected void initiateAggregate() {
			sicd = new SICD((SICDType) sicdTypeModel.getSelectedItem());
		}
		
		protected void updateDBAndTblModel() {
			Integer key = null;
			try {
				key = SQL_INSERT.sicd(sicd);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT sicd(SICD sicd)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			if(key != null) {//if insertion was successful
				sicdTblModel.setValueAt(Status.Lager, 0, 5);
				sicdTblModel.setValueAt(sicdTypeModel.getSelectedItem(), 0, 1);
				for(int i = 0; i < sicdTypeTblCellEditor.getCbxSICDTypeModel().getSize(); i++) {
					try {
						if(sicdTypeTblCellEditor.getCbxSICDTypeModel().getElementAt(i).getNotation().equals(((SICDType)sicdTypeModel.getSelectedItem()).getNotation())) {
							sicdTypeTblCellEditor.getCbxSICDTypeModel().setSelectedItem(sicdTypeTblCellEditor.getCbxSICDTypeModel().getElementAt(i));
						}
					}catch(NullPointerException e) {//if (monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i) == null
						//do nothing
					}
				}	
			
			}
			try {
				sicdTblModel.setSICDs(SQL_SELECT.sicds((SICDType) sicdTypeModel.getSelectedItem(), (String)sicdTblModel.getValueAt(0, 2), (Status)sicdTblModel.getValueAt(0, 5)));
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_SELECT sicds(SICDType sicdType)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			sicdTblModel.fireTableDataChanged();
		}
		
	}
	
	
	/**
	 * should be set to renderer class and overrided
	 * @author user2
	 *
	 */
//	class TblSICDIDRenderer extends JLabel implements TableCellRenderer{		
//		
//		private static final long serialVersionUID = -7832440906483552566L;
//		
//		public TblSICDIDRenderer() {
//			super.setOpaque(true);
//		}
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
//				int column) {
//			Integer id = ((SICD)value).getId();
//			setText(id.toString());
//			if(isSelected) {
//				setBackground(Color.ORANGE);
//			}else {
//				setBackground(row%2==0 ? Color.white : Color.lightGray);   
//			}
//			return this;
//		}
//		
//	}
	
	class TblSICDTypeRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 2455144833293671793L;

		public TblSICDTypeRenderer() {
			super.setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {//for all but the first row
				if(value instanceof SICDType) {				
					String notation = ((SICDType)value).getNotation();
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
				
				if(value instanceof SICDType) {
					setText(((SICDType) value).getNotation());
				}else {
					setText("Alle SICD-Modelle");					
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
			sicdTblModel.setValueAt(txt, 0, 2);
			try {
				sicdTblModel.setSICDs(SQL_SELECT.sicds((SICDType)sicdTblModel.getValueAt(0, 1), (String) sicdTblModel.getValueAt(0, 2), (Status) sicdTblModel.getValueAt(0, 5)));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sicdTblModel.fireTableDataChanged();
			
		}	
	}
	
	class TblRowSelectionListener implements ListSelectionListener{
		SICD sicd;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlSICD)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlSICD)panel).getSelectedTblRow();
	            sicd = (SICD) ((PnlSICD)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		
		protected SICD getAggregatSelected() {
			return sicd;
		}		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tblRowSelectionListener.getAggregatSelected() instanceof SICD) {
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					try {
						SQL_UPDATE.deleteSICD(tblRowSelectionListener.getAggregatSelected());
						sicdTblModel.sicds.remove(tblRowSelectionListener.getAggregatSelected());	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					sicdTblModel.fireTableDataChanged();
					
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
	        }else if(SwingUtilities.isRightMouseButton(mouseEvent) == true){
	        	table =(JTable) mouseEvent.getSource();
	        	Point point = mouseEvent.getPoint();
	        	int row = table.rowAtPoint(point);
	        	if(table.getSelectedRow() == row) {
	        		Isynet isynet = new Isynet();
	        		Patient patient = isynet.getPatient();
	        		PopupMenu menu = new PopupMenu(patient, (SICD) sicdTblModel.getValueAt(row, 0), sicdTblModel);
	                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	        	}
	        }
	    }
		 
		 protected void initiateDialog() {
			 CtrlDlgChangeSICD ctrlDlgChangeSICD = new CtrlDlgChangeSICD((SICD) sicdTblModel.getValueAt(row, 0), sicdTblModel);
             ctrlDlgChangeSICD.getDialog().setVisible(true);

		 }
	}
	
	
}
