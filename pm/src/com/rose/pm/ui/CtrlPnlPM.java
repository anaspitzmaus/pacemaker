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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rose.Isynet;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.PM;
import com.rose.pm.material.Status;
import com.rose.pm.ui.CtrlPnlMonitor.ListMonitorTypeRenderer;
import com.rose.pm.ui.CtrlPnlMonitor.SearchMonitorTypeListener;
import com.rose.pm.ui.Editor.DateCellEditor;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblCellImplantDateRenderer;
import com.rose.pm.ui.Renderer.TblCellPatientRenderer;

public class CtrlPnlPM extends CtrlPnlBase{

	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	Renderer renderer;
	NotationListener serialNrListener, noticeListener;
	DefaultComboBoxModel<AggregateType> aggregateTypeModel;
	AggregateTypeRenderer aggregateTypeRenderer;
	AggregateTypeListener aggregateTypeListener;
	AggregateTblModel aggregateTblModel;
	CreateListener createListener;
	TblPMIDRenderer tblPMIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblAggregateTypeRenderer tblAggregateTypeRenderer;
	TblStatusRenderer tblStatusRenderer;
	TblCellPatientRenderer tblPatientRenderer;
	com.rose.pm.ui.Renderer.TblCellLocalDateRenderer tblDateRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	DeleteListener deleteListener;
	TblMouseAdaptor tblMouseAdaptor;
	Editor editor;
	Editor.DateCellEditor dateCellEditor;
	Renderer.TblCellImplantDateRenderer tblImplantDateRenderer;
	
	
	public CtrlPnlPM() {
		createPanel();
		setComponentLabeling();
		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlPM)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		setStandardListener();
		setListener();
		setModel();
		setEditor();
		setRenderer();
		((PnlPM)panel).setAggregateTypeSelectionIndex(-1);
		((PnlPM)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	protected void createPanel() {
		panel = new PnlPM();
		panel.setName("Schrittmacher");
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
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlPM)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	 protected void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlPM)panel).setDateCellEditor(dateCellEditor);
	 }
	
	 void setModel() {
		ArrayList<AggregateType> aggregateTypes = SQL_SELECT.pacemakerKinds();
		AggregateType[] arr = new AggregateType[aggregateTypes.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < aggregateTypes.size(); i++) 
            arr[i] = aggregateTypes.get(i);		
		
		aggregateTypeModel = new DefaultComboBoxModel<AggregateType>(arr);
		//aggregateTypeModel = new AggregateTypeModel();
		((PnlPM)panel).setAggregatTypeModel(aggregateTypeModel);
		aggregateTblModel = new AggregateTblModel(SQL_SELECT.pacemakers(aggregateTypeListener.model));
		((PnlPM)panel).setAggregateTblModel(aggregateTblModel);
	}
	
	private void setRenderer() {
		renderer = new Renderer();
		aggregateTypeRenderer = new AggregateTypeRenderer();
		((PnlPM)panel).setAggregatTypeRenderer(aggregateTypeRenderer);
		tblPMIDRenderer = new TblPMIDRenderer();
		((PnlPM)panel).setPMIDRenderer(PM.class, tblPMIDRenderer);
		tblStringRenderer = new TblStringRenderer();
		((PnlPM)panel).setStringRenderer(String.class, tblStringRenderer);
		tblDateRenderer = renderer.new TblCellLocalDateRenderer();
		((PnlPM)panel).setDateRenderer(LocalDate.class, tblDateRenderer);
		tblAggregateTypeRenderer = new TblAggregateTypeRenderer();
		((PnlPM)panel).setTblAggregateTypeRenderer(AggregateType.class, tblAggregateTypeRenderer);
		tblStatusRenderer = new TblStatusRenderer();
		((PnlPM)panel).setStatusRenderer(Status.class, tblStatusRenderer);
		 tblPatientRenderer = renderer.new TblCellPatientRenderer();
		 ((PnlPM)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
		 tblImplantDateRenderer = renderer.new TblCellImplantDateRenderer();
		 ((PnlPM)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer);
	}
	
	class AggregateTypeListener implements ItemListener{

		AggregateType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (AggregateType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}				
		    }
			updateTblModel();		
		}
		
		protected void updateTblModel() {
			aggregateTblModel.setAggregats(SQL_SELECT.pacemakers(model));			
			aggregateTblModel.fireTableDataChanged();
		}		
	}
	
	class AggregateTypeRenderer extends JLabel implements ListCellRenderer<AggregateType>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7934129282290942751L;
		
		public AggregateTypeRenderer() {
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
			SQL_INSERT.pacemaker(pm);				
			aggregateTblModel.setAggregats(SQL_SELECT.pacemakers((AggregateType) aggregateTypeModel.getSelectedItem()));
			aggregateTblModel.fireTableDataChanged();
		}
		
	}
	
	class AggregateTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends PM> aggregates;
		private final Class[] columnClass = new Class[] {
			 PM.class, AggregateType.class, String.class, LocalDate.class, Status.class, String.class, Patient.class, Date.class
		};
		
		public AggregateTblModel(ArrayList<? extends PM> paceMakers) {
			this.aggregates = paceMakers;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Status");
			columnNames.add("Bemerkung");
			columnNames.add("Patient");
			columnNames.add("Implantationsdatum");
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
			return this.aggregates.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return columnClass[col];
		}

		
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
			case 0: return aggregates.get(rowIndex);
			
			case 1: return aggregates.get(rowIndex).getMaterialType();
			
			case 2: return aggregates.get(rowIndex).getSerialNr();
			
			case 3: return aggregates.get(rowIndex).getExpireDate();
			
			case 4: return aggregates.get(rowIndex).getStatus();
			
			case 5: return aggregates.get(rowIndex).getNotice();
			
			case 6: return aggregates.get(rowIndex).getPatient();
			
			case 7: return aggregates.get(rowIndex).getDateOfImplantation();
			
			default: return null;
			
			}	
			
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
            aggregates.get(row).setDateOfImplantation((Date)value);
            try {
				SQL_UPDATE.dateOfImplant(aggregates.get(row));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //change value at database;
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex == 7) {
				return true;
			}else {
				return false;
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
			protected AbstractTableModel tblModel;
			protected ArrayList<AggregateType> materialTypes;

			public PMTypeTblCellEditor(AbstractTableModel tblModel) {
				
				
				ArrayList<AggregateType> pmTypes;
				try {
					pmTypes = SQL_SELECT.pacemakerKinds(null, "");
					AggregateType[] arr = new AggregateType[pmTypes.size()]; 		  
			        // ArrayList to Array Conversion 
					
			        for (int i = 0; i < pmTypes.size(); i++) {
			            arr[i] = pmTypes.get(i);		
			        }
			        
					cbxPMTypeModel = new DefaultComboBoxModel<AggregateType>(arr);
					cbxPMType = new JComboBox<AggregateType>(cbxPMTypeModel);				
					
					cbxPMType.insertItemAt(null, 0);
					
					cbxPMType.setRenderer(new ListPMTypeRenderer());
					searchPMTypeListener = new SearchPMTypeListener();
					cbxPMType.addItemListener(searchPMTypeListener);
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
		 
		 protected AggregateType getPMType() {
			return pmType;
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
				
			try {
				aggregateTblModel.setValueAt(pmType, 0, 1);
				aggregateTblModel.setAggregates(SQL_SELECT.pacemakers((AggregateType) aggregateTblModel.getValueAt(0, 1), (String)aggregateTblModel.getValueAt(0, 2), (Status)aggregateTblModel.getValueAt(0, 5)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			aggregateTblModel.fireTableDataChanged();
					
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
					setText("Alle Schrittmachermodelle");
					setFont(new Font("Tahoma", Font.ITALIC, 14));
				}
				return this;
			}				
		}
	
	class TblPMIDRenderer extends JLabel implements TableCellRenderer{

		
		/**
		 * 
		 */
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

		/**
		 * 
		 */
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
	
	class TblAggregateTypeRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2455144833293671793L;

		public TblAggregateTypeRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			AggregateType type = (AggregateType) value;
			setText(type.getNotation());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
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
