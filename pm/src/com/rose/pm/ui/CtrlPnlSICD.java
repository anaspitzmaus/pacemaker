package com.rose.pm.ui;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.table.AbstractTableModel;

import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.PM;
import com.rose.pm.material.SICD;
import com.rose.pm.material.SICDType;
import com.rose.pm.material.Status;

import com.rose.pm.ui.Listener.NotationListener;

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
	TblPMIDRenderer tblPMIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblAggregateTypeRenderer tblAggregateTypeRenderer;
	TblStatusRenderer tblStatusRenderer;
	com.rose.pm.ui.Renderer.TblDateRenderer tblDateRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	DeleteListener deleteListener;
	TblMouseAdaptor tblMouseAdaptor;
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
		((PnlSICD)panel).addSICDTypeListener(sicdTypeListener);	
		showAllListener = new ShowAllListener();
		((PnlSICD)panel).addShowAllListener(showAllListener);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlSICD)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	 void setModel() {
		ArrayList<SICDType> sicdTypes = SQL_SELECT.sicdTypes();
		SICDType[] arr = new SICDType[sicdTypes.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < sicdTypes.size(); i++) 
            arr[i] = sicdTypes.get(i);		
		
		sicdTypeModel = new DefaultComboBoxModel<SICDType>(arr);
		//aggregateTypeModel = new AggregateTypeModel();
		((PnlSICD)panel).setSICDTypeModel(sicdTypeModel);
		sicdTblModel = new SICDTblModel(SQL_SELECT.sicds(sicdTypeListener.model));
		((PnlSICD)panel).setSICDTblModel(sicdTblModel);
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
			
			case 1: return sicds.get(rowIndex).getAggregatModel();
			
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
			sicdTblModel.setSICDs(SQL_SELECT.sicds(model));			
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
			SQL_INSERT.sicd(sicd);				
			sicdTblModel.setSICDs((SQL_SELECT.sicds((SICDType) sicdTypeModel.getSelectedItem()));
			sicdTblModel.fireTableDataChanged();
		}
		
	}
	
}
