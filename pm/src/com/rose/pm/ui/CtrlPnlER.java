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
import com.rose.pm.material.ER;
import com.rose.pm.material.ERType;
import com.rose.pm.material.PM;
import com.rose.pm.material.Status;
import com.rose.pm.ui.Editor.DateCellEditor;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblCellImplantDateRenderer;
import com.rose.pm.ui.Renderer.TblCellPatientRenderer;
import com.rose.pm.ui.Renderer.TblDoubleRenderer;

public class CtrlPnlER extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	Renderer renderer;
	NotationListener serialNrListener, noticeListener;
	DefaultComboBoxModel<ERType> erTypeModel;
	ERTypeRenderer erTypeRenderer;
	ERTypeListener erTypeListener;
	ERTblModel erTblModel;
	CreateListener createListener;
	ShowAllListener showAllListener;
	TblERIDRenderer tblERIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblERTypeRenderer tblERTypeRenderer;
	TblStatusRenderer tblStatusRenderer;
	com.rose.pm.ui.Renderer.TblCellLocalDateRenderer tblDateRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	DeleteListener deleteListener;
	TblMouseAdaptor tblMouseAdaptor;
	Editor editor;
	Editor.DateCellEditor dateCellEditor;
	Renderer.TblCellImplantDateRenderer tblImplantDateRenderer;
	TblCellPatientRenderer tblPatientRenderer;
	TblDoubleRenderer tblDoubleRenderer;
	
	public CtrlPnlER() {
		createPanel();
		setComponentLabeling();
		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlER)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		setStandardListener();
		setListener();
		setModel();
		setEditor();
		setRenderer();
		((PnlER)panel).setERTypeSelectionIndex(-1);
		((PnlER)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	protected void createPanel() {
		panel = new PnlER();
		panel.setName("Eventrekorder");
	}
	
	protected void setComponentLabeling() {
		((PnlER)panel).setLblRecorderTypeText("EventRekorder_Model:");
		((PnlER)panel).setLblSerialNrText("Seriennummer:");
		((PnlER)panel).setLblNoticeText("Bemerkung:");
		((PnlER)panel).setBtnCreateText("Einfügen");
		((PnlER)panel).setBtnDeleteText("Löschen");
		((PnlER)panel).setBtnShowAllText("Alle Modelle");
	}
	
	private void setStandardListener() {
		listener = new Listener();
		serialNrListener = listener.new NotationListener();
		((PnlER)panel).addSerialNrListener(serialNrListener);
		noticeListener = listener.new NotationListener();
		((PnlER)panel).addNoticeListener(noticeListener);		
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlER)panel).addTblRowSelectionListener(tblRowSelectionListener);
		deleteListener = new DeleteListener();
		((PnlER)panel).addDeleteListener(deleteListener);
		
	}
	
	/**
	 * overridable Listeners
	 */
	protected void setListener() {
		createListener = new CreateListener();
		((PnlER)panel).addCreateListener(createListener);
		erTypeListener = new ERTypeListener();
		((PnlER)panel).addERTypeListener(erTypeListener);	
		showAllListener = new ShowAllListener();
		((PnlER)panel).addShowAllListener(showAllListener);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlER)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	 protected void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlER)panel).setDateCellEditor(dateCellEditor);
	 }
	
	 void setModel() {
		 //set Model for the comboBox that displays the types of eventRecorders
		ArrayList<ERType> erTypes;
		try {
			erTypes = SQL_SELECT.eventRecorderTypes();
			ERType[] arr = new ERType[erTypes.size()]; 		  
	        // ArrayList to Array Conversion 
	        for (int i = 0; i < erTypes.size(); i++) 
	            arr[i] = erTypes.get(i);	
	        erTypeModel = new DefaultComboBoxModel<ERType>(arr);
			
			((PnlER)panel).setERTypeModel(erTypeModel);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(new JFrame(), "Die Typen der Eventrekorder konnten nicht abgefragt werden", "Hinweis", JOptionPane.WARNING_MESSAGE);
		}
		
		
		//set the model of the table that displays the eventRecorders
		try {
			erTblModel = new ERTblModel(SQL_SELECT.eventRecorder(erTypeListener.model));
			((PnlER)panel).setERTblModel(erTblModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Die Eventrekorder konnten nicht abgefragt werden", "Hinweis", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	private void setRenderer() {
		renderer = new Renderer();
		erTypeRenderer = new ERTypeRenderer();
		((PnlER)panel).setERTypeRenderer(erTypeRenderer);
		tblERIDRenderer = new TblERIDRenderer();
		((PnlER)panel).setERIDRenderer(ER.class, tblERIDRenderer);
		tblStringRenderer = new TblStringRenderer();
		((PnlER)panel).setStringRenderer(String.class, tblStringRenderer);
		tblDateRenderer = renderer.new TblCellLocalDateRenderer();
		((PnlER)panel).setDateRenderer(LocalDate.class, tblDateRenderer);
		tblERTypeRenderer = new TblERTypeRenderer();
		((PnlER)panel).setTblERTypeRenderer(ERType.class, tblERTypeRenderer);
		tblStatusRenderer = new TblStatusRenderer();
		((PnlER)panel).setStatusRenderer(Status.class, tblStatusRenderer);
		 tblPatientRenderer = renderer.new TblCellPatientRenderer();
		 ((PnlER)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
		 tblImplantDateRenderer = renderer.new TblCellImplantDateRenderer();
		 ((PnlER)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer);
		 tblDoubleRenderer = renderer.new TblDoubleRenderer();
		 ((PnlER)panel).setTblPriceRenderer(Double.class, tblDoubleRenderer);
	}
	
	class ERTypeRenderer extends JLabel implements ListCellRenderer<ERType>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7934129282290942751L;
		
		public ERTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ERType> list, ERType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof ERType) {
				setText(((ERType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	class ERTypeListener implements ItemListener{

		ERType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (ERType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}				
		    }
			updateTblModel();		
		}
		
		protected void updateTblModel() {
			try {
				erTblModel.setRecorders(SQL_SELECT.eventRecorder(model));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Das Update der Eventrekorder ist fehlgeschlagen!", "Hinweis", JOptionPane.WARNING_MESSAGE);
			}			
			erTblModel.fireTableDataChanged();
		}		
	}
	
	class ERTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends ER> recorders;
		private final Class[] columnClass = new Class[] {
			 ER.class, ERType.class, String.class, LocalDate.class, Status.class, String.class, Patient.class, Date.class, Double.class
		};
		
		public ERTblModel(ArrayList<? extends ER> recorders) {
			this.recorders = recorders;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Status");
			columnNames.add("Bemerkung");
			columnNames.add("Patient");
			columnNames.add("Implantationsdatum");
			columnNames.add("Preis");
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
			return this.recorders.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return columnClass[col];
		}

		
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
			case 0: return recorders.get(rowIndex);
			
			case 1: return recorders.get(rowIndex).getRecorderType();
			
			case 2: return recorders.get(rowIndex).getSerialNr();
			
			case 3: return recorders.get(rowIndex).getExpireDate();
			
			case 4: return recorders.get(rowIndex).getStatus();
			
			case 5: return recorders.get(rowIndex).getNotice();
			
			case 6: return recorders.get(rowIndex).getPatient();
			
			case 7: return recorders.get(rowIndex).getDateOfImplantation();
			
			case 8: return recorders.get(rowIndex).getPrice();
			
			default: return null;
			
			}	
			
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
            recorders.get(row).setDateOfImplantation((Date)value);
            try {
				SQL_UPDATE.dateOfImplant(recorders.get(row));
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
		
		
		protected void setRecorders(ArrayList<? extends ER> er) {
			this.recorders = er;		
		}
		
	}
	
	class ShowAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((PnlER)panel).setERTypeSelectionIndex(-1);
			update();
		}
		
		protected void update() {
			try {
				erTblModel.setRecorders(SQL_SELECT.eventRecorder(null));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Das Update der Eventrekorder ist fehlgeschlagen!", "Hinweis", JOptionPane.WARNING_MESSAGE);
			}			
			erTblModel.fireTableDataChanged();
		}
		
	}
	
	class CreateListener implements ActionListener{
		ER er;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && erTypeModel.getSelectedItem() instanceof ERType) {
				initiateRecorder();
				er.setExpireDate(ctrlPnlSetDate.getDate());
				er.setSerialNr(serialNrListener.getNotation());
				er.setNotice(noticeListener.getNotation());
				er.setStatus(Status.Lager);
				updateDBAndTblModel();
				((PnlER)panel).clearComponents();
				serialNrListener.notation = "";
			}
		}
		
		protected void initiateRecorder() {
			er = new ER((ERType) erTypeModel.getSelectedItem());
		}
		
		protected void updateDBAndTblModel() {
			try {
				SQL_INSERT.eventRecorder(er);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
			try {
				erTblModel.setRecorders(SQL_SELECT.eventRecorder((ERType) erTypeModel.getSelectedItem()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			erTblModel.fireTableDataChanged();
		}
		
	}
	
	class TblERIDRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = -3959695158737112822L;
		
		public TblERIDRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((ER)value).getId();
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
	
	
		
		private static final long serialVersionUID = -6109076518168325212L;


		public TblStringRenderer() {
			super.setOpaque(true);
		}
		
	
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
		private static final long serialVersionUID = -3527355864115390218L;

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
	
	class TblERTypeRenderer extends JLabel implements TableCellRenderer{

	
		private static final long serialVersionUID = 795885677684192003L;

		public TblERTypeRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			ERType type = (ERType) value;
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
		ER er;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlER)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlER)panel).getSelectedTblRow();
	            er = (ER) ((PnlER)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		
		protected ER getRecorderSelected() {
			return er;
		}		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tblRowSelectionListener.getRecorderSelected() instanceof ER) {
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					try {
					SQL_UPDATE.deleteEventRecorder(tblRowSelectionListener.getRecorderSelected());
						erTblModel.recorders.remove(tblRowSelectionListener.getRecorderSelected());
						erTblModel.fireTableDataChanged();
					}catch(SQLException ex) {
						
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
	        }else if(SwingUtilities.isRightMouseButton(mouseEvent) == true){
	        	table =(JTable) mouseEvent.getSource();
	        	Point point = mouseEvent.getPoint();
	        	int row = table.rowAtPoint(point);
	        	if(table.getSelectedRow() == row) {
	        		Isynet isynet = new Isynet();
	        		Patient patient = isynet.getPatient();
	        		PopupMenu menu = new PopupMenu(patient, (ER) erTblModel.getValueAt(row, 0), erTblModel);
	                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	        	}
	        }
	    }
		 
		 protected void initiateDialog() {
			 CtrlDlgChangeER ctrlDlgChangeER = new CtrlDlgChangeER((ER) erTblModel.getValueAt(row, 0), erTblModel);
             ctrlDlgChangeER.getDialog().setVisible(true);

		 }
	}
}
