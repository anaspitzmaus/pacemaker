package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.SICDType;
import com.rose.pm.ui.Listener.NotationListener;




public class CtrlPnlSICDType extends CtrlPnlBase{
	//PnlSICDType pnlSICDType;
	SICDTypeTblModel tblModel; 
	ComboBoxModel<Manufacturer> manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	TblSICDTypeIDRenderer tblSICDTypeIDRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	Listener listener;
	Listener.ManufacturerListener manufacturerListener;
	Listener.PriceListener priceListener;
	Listener.NotationListener notationListener, noticeListener;
	
	Renderer renderer;
	Renderer.TblStringRenderer tblStringRenderer;
	Renderer.TblDoubleRenderer tblDoubleRenderer;
	
	public CtrlPnlSICDType() {
		createPanel();
		
		setModel();
		
		setRenderer();
		setListener();
		setComponentText();
		((PnlSICDType)panel).setManufacturerIndex(-1);
		((PnlSICDType)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	protected void createPanel() {
		panel = new PnlSICDType();
		panel.setName("SICD-Model");		
	}
	
	private void setListener() {
		listener = new Listener();
		notationListener = listener.new NotationListener();	
		((PnlSICDType)panel).addNotationListener(notationListener);
		noticeListener = listener.new NotationListener();
		((PnlSICDType)panel).addNoticeListener(noticeListener);
		manufacturerListener = listener.new ManufacturerListener();
		((PnlSICDType)panel).addManufacturerListener(manufacturerListener);
		
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlSICDType)panel).addTblRowSelectionListener(tblRowSelectionListener);
		priceListener = listener.new PriceListener();
		((PnlSICDType)panel).addPriceChangeListener(priceListener);
		
	}
	
	private void setRenderer() {
		manufacturerRenderer = new ManufacturerRenderer();
		((PnlSICDType)panel).setManufacturerRenderer(manufacturerRenderer);
		
		tblSICDTypeIDRenderer = new TblSICDTypeIDRenderer();
		((PnlSICDType)panel).setTableSICDIDRenderer(SICDType.class, tblSICDTypeIDRenderer);
		
		renderer = new Renderer();
		tblStringRenderer = renderer.new TblStringRenderer();
		((PnlSICDType)panel).setTableStringRenderer(String.class, tblStringRenderer);
		
		tblDoubleRenderer = renderer.new TblDoubleRenderer();
		((PnlSICDType)panel).setTblDoubleRenderer(Double.class, tblDoubleRenderer);
	}
	
	
	protected void setModel() {
		ArrayList<Manufacturer> manufacturers = SQL_SELECT.manufacturers();
		Manufacturer[] arr = new Manufacturer[manufacturers.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < manufacturers.size(); i++) 
            arr[i] = manufacturers.get(i);		
		
		manufacturerModel = new DefaultComboBoxModel<Manufacturer>(arr);
	
		((PnlSICDType)panel).setManufacturerModel(manufacturerModel);
		
		try {
			tblModel = new SICDTypeTblModel(SQL_SELECT.sicdTypes());
			((PnlSICDType)panel).setTblModel(tblModel);
			System.out.println("SICDType" + ((PnlSICDType)panel).table.getColumnClass(0));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getErrorCode() + ", " + e.getMessage() + ", das Tabellenmodel der SICD-Typen konnte nicht erstellt werden");
		}
		
		
	}
	
	protected void setComponentText() {
		((PnlSICDType)panel).setLblNotationText("Bezeichnung:");
		((PnlSICDType)panel).setLblManufacturerText("Hersteller:");
		((PnlSICDType)panel).setLblNoticeText("Bemerkung:");
		((PnlSICDType)panel).setBtnCreateText("Eintragen");
		((PnlSICDType)panel).setBtnDeleteText("Löschen");
		((PnlSICDType)panel).setLblPriceText("Preis:");
	}
	
	class SICDTypeTblModel extends AbstractTableModel{

		
		private static final long serialVersionUID = -8444808544442905721L;			
		protected ArrayList<String> columnNames;
		ArrayList<SICDType> aggregateTypes;
		
		public SICDTypeTblModel(ArrayList<SICDType> aggregateTypes) {
			this.aggregateTypes = aggregateTypes;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Bemerkung");			
			columnNames.add("Preis");	
		}	
		
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
				switch(columnIndex) {
				case 0: return aggregateTypes.get(rowIndex);
				
				case 1: return aggregateTypes.get(rowIndex).getNotation();
				
				case 2: return aggregateTypes.get(rowIndex).getManufacturer().getNotation();					
				
				case 3: return aggregateTypes.get(rowIndex).getNotice();	
				
				case 4: return aggregateTypes.get(rowIndex).getPrice();
				
				default: return null;
				
				}	
			
		}

		@Override
		public int getRowCount() {
			return aggregateTypes.size();
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
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
			
			
		}
		
		protected void setAggregateTypes(ArrayList<SICDType> aggregateTypes) {
			this.aggregateTypes = aggregateTypes;		
		}
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
	
	class TblSICDTypeIDRenderer extends JLabel implements TableCellRenderer{
	
			
			
		private static final long serialVersionUID = -8009540639397813052L;
		
		public TblSICDTypeIDRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((SICDType)value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
		}
		
	}
	
	class TblRowSelectionListener implements ListSelectionListener{
		SICDType aggregateType;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlSICDType)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlSICDType)panel).getSelectedTblRow();
	            aggregateType = (SICDType) ((PnlSICDType)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		protected SICDType getRecorderTypeSelected() {
			return aggregateType;
		}
			
	}
	
	protected void addCreateListener(ActionListener createTypeListener) {
		((PnlSICDType)panel).addCreateListener(createTypeListener);		
	}
	
	protected void addDeleteListener(ActionListener deleteTypeListener) {
		((PnlSICDType)panel).addDeleteListener(deleteTypeListener);		
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
	
	protected SICDTypeTblModel getAggregateTypeTableModel() {
		return this.tblModel;
	}
	
	protected TblRowSelectionListener getTblRowSelectionListener() {		
		return this.tblRowSelectionListener;
	}
}
