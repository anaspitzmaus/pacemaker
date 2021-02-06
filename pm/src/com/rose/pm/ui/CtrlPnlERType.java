package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ERType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Kind;
import com.rose.pm.ui.CtrlPnlPMType.MRIListener;
import com.rose.pm.ui.CtrlPnlPMType.PMTypeTblModel;
import com.rose.pm.ui.CtrlPnlPMType.TblRowSelectionListener;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlPnlERType extends CtrlPnlBase{

	PnlERType pnlERType;
	ERTypeTblModel tblModel; 
	ComboBoxModel<Manufacturer> manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	ManufacturerListener manufacturerListener;
	Listener listener;
	NotationListener notationListener, noticeListener;	
	TableERTypeRenderer tablePMTypeRenderer;
	TblStringRenderer tblStringRenderer;
	TblERIDRenderer tblPMIDRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	
	
	/**
	 * model for the comboBox that displays the manufacturers
	 * @author Ekkehard Rose
	 *
	 */
	class ManufacturerModel implements ComboBoxModel<Manufacturer>{

		
		int indexSel;
		ArrayList<Manufacturer> manufacturers;
		
		public ManufacturerModel() {	
			
			manufacturers = SQL_SELECT.manufacturers();
			
		}
		
		@Override
		public Manufacturer getElementAt(int i) {			
			return manufacturers.get(i);
		}
		
		@Override
		public int getSize() {
			
			return manufacturers.size();
		}
		@Override
		public Object getSelectedItem() {
			
			if (indexSel >= 0) {
				return manufacturers.get(indexSel);
			}else {
				return "";
			}
		}
		@Override
		public void setSelectedItem(Object anItem) {
			if(anItem instanceof Manufacturer) {
				for(int i = 0; i<manufacturers.size(); i++) {
					if(anItem.equals(manufacturers.get(i))) {
						indexSel = i;
						break;
					}
				}
			}
			
		}
		
			
		
		
		public ArrayList<Manufacturer> getManufacturers(){
			return this.manufacturers;
		}

		@Override
		public void addListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	class ManufacturerRenderer extends JLabel implements ListCellRenderer<Manufacturer>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6240666262007840856L;

		public ManufacturerRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}
	
		@Override
		public Component getListCellRendererComponent(JList<? extends Manufacturer> list, Manufacturer value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof Manufacturer) {
				setText(((Manufacturer) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	class ManufacturerListener implements ItemListener{
		Manufacturer manufacturer;
				
		/**
		 * return the selected manufacturer
		 * @return
		 */
		public Manufacturer getManufacturer() {
			return manufacturer;
		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					manufacturer = (Manufacturer) event.getItem();			        
				} catch (ClassCastException e) {
					manufacturer = null;
				}
				
		       }
			
		}
	}
	
	class ERTypeTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends ERType> recordTypes;
		PM_Kind type;
		
		public ERTypeTblModel(ArrayList<? extends ERType> recorders) {
			this.recordTypes = recorders;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
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
			return this.recordTypes.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
				case 0: return recordTypes.get(rowIndex);
				
				case 1: return recordTypes.get(rowIndex).getNotation();
				
				case 2: return recordTypes.get(rowIndex).getManufacturer().getNotation();
				
				case 3: return recordTypes.get(rowIndex).getNotice();
				
				default: return null;
			
			}		
		}	
		
		protected void setRecorders(ArrayList<? extends ERType> erType) {
			this.recordTypes = erType;
//			if(icd) {
//				if(!columnNames.contains("ATP")) {
//					columnNames.add("ATP");//add columns for ICD-Model
//				}				
//			}else {//if pacemaker
//				columnNames.remove("ATP");
//			}
			
		}
	}
	
	/**
	 * renderer for the table that displays the types of eventrecorders
	 * @author user2
	 *
	 */
	class TableERTypeRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4308724346222015548L;
		/**
		 * 
		 */
		
		private String notation;
		
		public TableERTypeRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if(value instanceof ERType) {
				setText(((ERType) value).getNotation());
			}else {
				setText("");
			}
			
			 
			
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
				
			
			return this;
		}				
	}
	
	class TblERIDRenderer extends JLabel implements TableCellRenderer{

		
		
		private static final long serialVersionUID = -8009540639397813052L;
		
		public TblERIDRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((ERType)value).getId();
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
		private static final long serialVersionUID = -3832353250998700434L;


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
	
	class TblRowSelectionListener implements ListSelectionListener{
		ERType erType;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlERType)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlERType)panel).getSelectedTblRow();
	            erType = (ERType) ((PnlERType)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		protected ERType getRecorderTypeSelected() {
			return erType;
		}
			
	}
	

	protected void addCreateListener(ActionListener createTypeListener) {
		((PnlERType)panel).addCreateListener(createTypeListener);		
	}
	
	protected void addDeleteListener(ActionListener deleteTypeListener) {
		((PnlERType)panel).addDeleteListener(deleteTypeListener);		
	}
	
	protected ComboBoxModel<Manufacturer> getManufacturerModel() {
		return this.manufacturerModel;
	}

	protected NotationListener getNoticeListener() {
		return this.noticeListener;
	}
	
	protected NotationListener getNotationListener() {
		return this.notationListener;
	}
	
	protected ERTypeTblModel getERTypeTableModel() {
		return this.tblModel;
	}
	
	protected TblRowSelectionListener getTblRowSelectionListener() {		
		return this.tblRowSelectionListener;
	}
}

