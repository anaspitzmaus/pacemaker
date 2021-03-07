package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.MonitorType;
import com.rose.pm.ui.CtrlPnlElectrodeType.TblElectrodeModelIDRenderer;
import com.rose.pm.ui.CtrlPnlElectrodeType.TblRowSelectionListener;
import com.rose.pm.ui.Listener.ManufacturerListener;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlPnlMonitorType extends CtrlPnlBase{


	ComboBoxModel<Manufacturer> manufacturerModel;
	ListManufacturerRenderer manufacturerRenderer;
	Listener listener;
	Renderer renderer;
	ManufacturerListener manufacturerListener;
	NotationListener notationListener, noticeListener;
	TblMonitorTypeModel tblMonitorTypeModel;
	CustomTableCellEditor customTableCellEditor;
	TblRowSelectionListener tblRowSelectionListener;
	
	
	protected NotationListener getNotationListener() {
		return this.notationListener;
	}
	
	protected NotationListener getNoticeListener() {
		return this.noticeListener;
	}
	
	protected ComboBoxModel<Manufacturer> getManufacturerModel(){
		return this.manufacturerModel;
	}
	
	public CtrlPnlMonitorType() {
		panel = new PnlMonitorType();
		panel.setName("Monitormodel");
		panel.setOpaque(false);
		panel.setBackground(Color.BLUE);
		setModel();
		setRenderer();
		customTableCellEditor = new CustomTableCellEditor();
		((PnlMonitorType)panel).setManufacturerCellEditor(customTableCellEditor);
		
	}
	
	private void setModel() {
		tblMonitorTypeModel = new TblMonitorTypeModel();
		panel.setTblModel(tblMonitorTypeModel);
	}
	
	private void setRenderer() {
		manufacturerRenderer = new ListManufacturerRenderer();
		((PnlMonitorType)panel).setManufacturerRenderer(manufacturerRenderer);
		
	}
	
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
	
	class ListManufacturerRenderer extends JLabel implements ListCellRenderer<Manufacturer>{

		private static final long serialVersionUID = 6240666262007840856L;

		public ListManufacturerRenderer() {
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
	
	class TblMonitorTypeModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 5589871039662094045L;
		
		protected ArrayList<String> columnNames;		
		protected ArrayList<MonitorType> monitorTypes;
		private Class[] classes = {Integer.class, String.class, Manufacturer.class, String.class, Double.class};
		
		public TblMonitorTypeModel() {
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Bemerkung");
			columnNames.add("Preis");
			
			monitorTypes = new ArrayList<MonitorType>();
		}
		
		@Override
		public int getRowCount() {
			return monitorTypes.size() + 1;
		}

		@Override
		public int getColumnCount() {
			return columnNames.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(rowIndex > 0) {
				switch(columnIndex) {
					case 0: return monitorTypes.get(rowIndex - 1).getId();
					case 1: return monitorTypes.get(rowIndex - 1).getNotation();
					case 2: return monitorTypes.get(rowIndex - 1).getManufacturer();
					case 3: return monitorTypes.get(rowIndex - 1).getNotice();
					case 4: return monitorTypes.get(rowIndex - 1).getPrice();
					default: return null;
				}
			}else{
				return null;
			}
		}
		
		@Override
		public Class getColumnClass(int col) {
			return classes[col];
		}
		
		@Override
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }
		
		public void setMonitorTypes(ArrayList<MonitorType> monitorTypes) {
			this.monitorTypes = monitorTypes;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(rowIndex == 0) {
				return true;
			}else {
				return false;
			}
		}		
	}
	
	 public class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	      
		private static final long serialVersionUID = 3283574841392539652L;
		private TableCellEditor editor;
		protected JComboBox<Manufacturer> cbxManufacturer;
		
		public CustomTableCellEditor() {
			cbxManufacturer = new JComboBox<Manufacturer>();
			 for(Manufacturer m: SQL_SELECT.manufacturers()) {
				 cbxManufacturer.addItem(m);
			 }	
			 cbxManufacturer.setRenderer(new ListManufacturerRenderer());
		}
		
        @Override
        public Object getCellEditorValue() {
            if (editor != null) {
                return editor.getCellEditorValue();
            }

            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (column == 2) {
                editor = new DefaultCellEditor(cbxManufacturer);
            } 

            return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        }
    }
	 
	 
	class TblRowSelectionListener implements ListSelectionListener{
		MonitorType monitorType;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlMonitorType)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlMonitorType)panel).getSelectedTblRow() - 1;
		        monitorType = (MonitorType) ((PnlMonitorType)panel).getTableValueAt(row, 0); //get the monitor from the first column		            
		    }			
		}
		
		protected MonitorType getMonitorTypeSelected() {
			return monitorType;
		}		
	}

	protected TblMonitorTypeModel getTblModel() {
		return this.tblMonitorTypeModel;
	}

	protected TblRowSelectionListener getTblRowSelectionListener() {
		return this.tblRowSelectionListener;		
	}
}
