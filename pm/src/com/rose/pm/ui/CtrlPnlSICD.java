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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

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
import com.rose.pm.ui.Renderer.TblPatientRenderer;
import com.rose.pm.ui.Renderer.TblStatusRenderer;
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
	ShowAllListener showAllListener;
	TblSICDIDRenderer tblSICDIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblAggregateTypeRenderer tblAggregateTypeRenderer;
	TblStatusRenderer tblStatusRenderer;
	com.rose.pm.ui.Renderer.TblDateRenderer tblDateRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	DeleteListener deleteListener;
	TblMouseAdaptor tblMouseAdaptor;
	TblPatientRenderer tblPatientRenderer;
	
	public CtrlPnlSICD() {
		createPanel();
		setComponentLabeling();
		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlSICD)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		setStandardListener();
		setListener();
		setModel();
		setRenderer();
		((PnlSICD)panel).setAggregateTypeSelectionIndex(-1);
		((PnlSICD)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	protected void createPanel() {
		panel = new PnlSICD();
		panel.setName("SICD");
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
		showAllListener = new ShowAllListener();
		((PnlSICD)panel).addShowAllListener(showAllListener);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlSICD)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	 protected void setModel() {
		 ArrayList<? extends MaterialType> aggregateTypes;
		try {
			aggregateTypes = SQL_SELECT.sicdTypes();
			SICDType[] arr = new SICDType[aggregateTypes.size()]; 		  
	        // ArrayList to Array Conversion 
	        for (int i = 0; i < aggregateTypes.size(); i++) 
	            arr[i] = (SICDType) aggregateTypes.get(i);		
			
			sicdTypeModel = new DefaultComboBoxModel<SICDType>(arr);
			
			((PnlSICD)panel).setAggregateTypeModel(sicdTypeModel);
			sicdTblModel = new SICDTblModel(SQL_SELECT.sicds((SICDType) sicdTypeListener.model));
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
			tblSICDIDRenderer = new TblSICDIDRenderer();
			((PnlSICD)panel).setAggregateIDRenderer(SICD.class, tblSICDIDRenderer);
			tblStringRenderer = renderer.new TblStringRenderer();
			((PnlSICD)panel).setStringRenderer(String.class, tblStringRenderer);
			tblDateRenderer = renderer.new TblDateRenderer();
			((PnlSICD)panel).setDateRenderer(LocalDate.class, tblDateRenderer);
			tblAggregateTypeRenderer = new TblAggregateTypeRenderer();
			((PnlSICD)panel).setTblAggregateTypeRenderer(SICDType.class, tblAggregateTypeRenderer);
			tblStatusRenderer = renderer.new TblStatusRenderer();
			((PnlSICD)panel).setStatusRenderer(Status.class, tblStatusRenderer);
			tblPatientRenderer = renderer.new TblPatientRenderer();
			((PnlSICD)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
			 
		}
	
	class SICDTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends SICD> sicds;
		private final Class[] columnClass = new Class[] {
			 SICD.class, SICDType.class, String.class, LocalDate.class, String.class, Status.class, Patient.class
		};
		
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
			return this.sicds.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return columnClass[col];
		}

		
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
			case 0: return sicds.get(rowIndex);
			
			case 1: return sicds.get(rowIndex).getMaterialType();
			
			case 2: return sicds.get(rowIndex).getSerialNr();
			
			case 3: return sicds.get(rowIndex).getExpireDate();
			
			case 4: return sicds.get(rowIndex).getNotice();
			
			case 5: return sicds.get(rowIndex).getStatus();
			
			case 6: return sicds.get(rowIndex).getPatient();
			
			default: return null;
			
			}	
			
		}		
		
		protected void setSICDs(ArrayList<? extends SICD> sicds) {
			this.sicds = sicds;		
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
			updateTblModel();		
		}
		
		protected void updateTblModel() {
			try {
				sicdTblModel.setSICDs(SQL_SELECT.sicds(model));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			sicdTblModel.fireTableDataChanged();
		}		
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
				((PnlPM)panel).clearComponents();
				serialNrListener.notation = "";
			}
		}
		
		protected void initiateAggregate() {
			sicd = new SICD((SICDType) sicdTypeModel.getSelectedItem());
		}
		
		protected void updateDBAndTblModel() {
			try {
				SQL_INSERT.sicd(sicd);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT sicd(SICD sicd)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}				
			try {
				sicdTblModel.setSICDs(SQL_SELECT.sicds((SICDType) sicdTypeModel.getSelectedItem()));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_SELECT sicds(SICDType sicdType)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			sicdTblModel.fireTableDataChanged();
		}
		
	}
	
	class ShowAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((PnlSICD)panel).setAggregateTypeSelectionIndex(-1);
			update();
		}
		
		protected void update() {
			try {
				sicdTblModel.setSICDs(SQL_SELECT.sicds(null));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			sicdTblModel.fireTableDataChanged();
		}
		
	}
	
	/**
	 * shoul be set to renderer class and overrided
	 * @author user2
	 *
	 */
	class TblSICDIDRenderer extends JLabel implements TableCellRenderer{		
		
		private static final long serialVersionUID = -7832440906483552566L;
		
		public TblSICDIDRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((SICD)value).getId();
			setText(id.toString());
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
			SICDType type = (SICDType) value;
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
					if(SQL_UPDATE.deleteSICD(tblRowSelectionListener.getAggregatSelected())){
						sicdTblModel.sicds.remove(tblRowSelectionListener.getAggregatSelected());
						sicdTblModel.fireTableDataChanged();
					}
				}
			
			}
		
		}
	}
	
	class TblMouseAdaptor extends MouseAdapter{
		int row;
		 @Override
	    public void mouseClicked(MouseEvent mouseEvent){
	        if(mouseEvent.getClickCount()==2){
	        	 JTable table =(JTable) mouseEvent.getSource();
	             Point point = mouseEvent.getPoint();
	             row = table.rowAtPoint(point);
	             if (table.getSelectedRow() != -1 && row >= 0) {
	                initiateDialog();
	             }
	        }
	    }
		 
		 protected void initiateDialog() {
			 CtrlDlgChangeSICD ctrlDlgChangeSICD = new CtrlDlgChangeSICD((SICD) sicdTblModel.getValueAt(row, 0), sicdTblModel);
             ctrlDlgChangeSICD.getDialog().setVisible(true);

		 }
	}
	
	
}
