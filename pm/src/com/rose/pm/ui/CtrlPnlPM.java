package com.rose.pm.ui;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.PM;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlPnlPM extends CtrlPnlBase{

	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	NotationListener serialNrListener, noticeListener;
	ComboBoxModel<AggregateType> aggregateTypeModel;
	AggregateTypeRenderer aggregateTypeRenderer;
	AggregateTypeListener aggregateTypeListener;
	AggregateTblModel aggregateTblModel;
	CreateListener createListener;
	ShowAllListener showAllListener;
	TblPMIDRenderer tblPMIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblDateRenderer tblDateRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	
	
	
	
	public CtrlPnlPM() {
		panel = new PnlPM();
		panel.setName("Schrittmacher");
		((PnlPM)panel).setLblAggregatTypeText("Schrittmachermodel:");
		((PnlPM)panel).setLblSerialNrText("Seriennummer:");
		((PnlPM)panel).setLblNoticeText("Bemerkung:");
		((PnlPM)panel).setBtnCreateText("Einf�gen");
		((PnlPM)panel).setBtnDeleteText("L�schen");
		((PnlPM)panel).setBtnShowAllText("Alle Modelle");
		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlPM)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		setListener();
		setModel();
		setRenderer();
		((PnlPM)panel).setAggregateTypeSelectionIndex(-1);
		((PnlPM)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void setListener() {
		listener = new Listener();
		serialNrListener = listener.new NotationListener();
		((PnlPM)panel).addSerialNrListener(serialNrListener);
		noticeListener = listener.new NotationListener();
		((PnlPM)panel).addNoticeListener(noticeListener);
		aggregateTypeListener = new AggregateTypeListener();
		((PnlPM)panel).addAggregateTypeListener(aggregateTypeListener);	
		createListener = new CreateListener();
		((PnlPM)panel).addCreateListener(createListener);
		showAllListener = new ShowAllListener();
		((PnlPM)panel).addShowAllListener(showAllListener);
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlPM)panel).addTblRowSelectionListener(tblRowSelectionListener);
	}
	
	private void setModel() {
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
		aggregateTypeRenderer = new AggregateTypeRenderer();
		((PnlPM)panel).setAggregatTypeRenderer(aggregateTypeRenderer);
		tblPMIDRenderer = new TblPMIDRenderer();
		((PnlPM)panel).setPMIDRenderer(PM.class, tblPMIDRenderer);
		tblStringRenderer = new TblStringRenderer();
		((PnlPM)panel).setStringRenderer(String.class, tblStringRenderer);
		tblDateRenderer = new TblDateRenderer();
		((PnlPM)panel).setDateRenderer(LocalDate.class, tblDateRenderer);
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
	
	/**
	 * model for the comboBox that displays the types of aggregate
	 * @author Ekkehard Rose
	 *
	 */
//	class AggregateTypeModel implements ComboBoxModel<AggregateType>{
//
//		
//		int indexSel;
//		ArrayList<AggregateType> aggTypes;
//		
//		public AggregateTypeModel() {	
//			
//			aggTypes = SQL_SELECT.pacemakerKinds();
//			
//		}
//		
//		@Override
//		public AggregateType getElementAt(int i) {			
//			return aggTypes.get(i);
//		}
//		
//		@Override
//		public int getSize() {
//			
//			return aggTypes.size();
//		}
//		@Override
//		public Object getSelectedItem() {
//			
//			if (indexSel >= 0) {
//				return aggTypes.get(indexSel);
//			}else {
//				return "";
//			}
//		}
//		@Override
//		public void setSelectedItem(Object anItem) {
//			if(anItem instanceof AggregateType) {
//				for(int i = 0; i<aggTypes.size(); i++) {
//					if(anItem.equals(aggTypes.get(i))) {
//						indexSel = i;
//						break;
//					}
//				}
//			}
//			
//		}
//		
//			
//		
//		
//		public ArrayList<AggregateType> getAggregatTypes(){
//			return this.aggTypes;
//		}
//
//		@Override
//		public void addListDataListener(ListDataListener l) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void removeListDataListener(ListDataListener l) {
//			// TODO Auto-generated method stub
//			
//		}	
//		
//	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && aggregateTypeModel.getSelectedItem() instanceof AggregateType) {
				PM pm = new PM((AggregateType) aggregateTypeModel.getSelectedItem());
				pm.setExpireDate(ctrlPnlSetDate.getDate());
				pm.setSerialNr(serialNrListener.getNotation());
				pm.setNotice(noticeListener.getNotation());
				SQL_INSERT.pacemaker(pm);	
					
				aggregateTblModel.setAggregats(SQL_SELECT.pacemakers((AggregateType) aggregateTypeModel.getSelectedItem()));
				
				aggregateTblModel.fireTableDataChanged();
				
			}
		}
		
	}
	
	class AggregateTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends PM> aggregates;
		
		
		public AggregateTblModel(ArrayList<? extends PM> paceMakers) {
			this.aggregates = paceMakers;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Bemerkung");
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
			return getValueAt(0, col).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
			case 0: return aggregates.get(rowIndex);
			
			case 1: return aggregates.get(rowIndex).getSerialNr();
			
			case 2: return aggregates.get(rowIndex).getExpireDate();
			
			case 3: return aggregates.get(rowIndex).getNotice();
			
			default: return null;
			
			}	
			
		}
		
		
		
		protected void setAggregats(ArrayList<? extends PM> pm) {
			this.aggregates = pm;		
		}
		
	}
	
	class ShowAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((PnlPM)panel).setAggregateTypeSelectionIndex(-1);
			aggregateTblModel.setAggregats(SQL_SELECT.pacemakers(null));			
			aggregateTblModel.fireTableDataChanged();
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
	
	class TblDateRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5861989547138095236L;

		public TblDateRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
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
				if(SQL_UPDATE.deleteAggregate(tblRowSelectionListener.getAggregatSelected())){
					aggregateTblModel.aggregates.remove(tblRowSelectionListener.getAggregatSelected());
					aggregateTblModel.fireTableDataChanged();
				}
			}
			
		}
		
	}
}
