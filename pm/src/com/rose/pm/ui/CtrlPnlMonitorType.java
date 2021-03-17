package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.MonitorType;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Listener.PriceListener;
import com.rose.pm.ui.Renderer.TblCellManufacturerRenderer;


public class CtrlPnlMonitorType extends CtrlPnlBase{


	ComboBoxModel<Manufacturer> manufacturerModel;
	ListManufacturerRenderer listManufacturerRenderer;
	MonitorTypeIdRenderer monitorTypeIdRenderer;
	Listener listener;
	Renderer renderer;
	TblCellManufacturerRenderer tblCellManufacturerRenderer;
	TblStringRenderer notationRenderer;
	Listener.ManufacturerListener manufacturerListener;
	NotationListener notationListener, noticeListener;
	TblMonitorTypeModel tblMonitorTypeModel;
	CustomTableCellEditor customTableCellEditor;
	TblRowSelectionListener tblRowSelectionListener;
	PriceListener priceListener;
	TblSearchPriceRenderer priceRenderer;
	
	
	
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
		setListener();
		setModel();
		setRenderer();
		setComponentText();
		((PnlMonitorType)panel).setManufacturerIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customTableCellEditor = new CustomTableCellEditor();
		((PnlMonitorType)panel).setManufacturerCellEditor(customTableCellEditor);
		JTextField textField = new JTextField("Text");
		SearchNotationListener searchNotationListener = new SearchNotationListener();
		textField.getDocument().addDocumentListener(searchNotationListener);
		((PnlMonitorType)panel).setNotationCellEditor(new DefaultCellEditor(textField));
		((PnlMonitorType)panel).setFirstRowHeight(40);
	}
	
	private void setModel() {
		ArrayList<Manufacturer> manufacturers = SQL_SELECT.manufacturers();
		Manufacturer[] arr = new Manufacturer[manufacturers.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < manufacturers.size(); i++) 
            arr[i] = manufacturers.get(i);		
		
		manufacturerModel = new DefaultComboBoxModel<Manufacturer>(arr);
	
		((PnlMonitorType)panel).setManufacturerModel(manufacturerModel);
		
		try {
			tblMonitorTypeModel = new TblMonitorTypeModel(SQL_SELECT.monitorTypes(null, ""));
			panel.setTblModel(tblMonitorTypeModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void setRenderer() {
		listManufacturerRenderer = new ListManufacturerRenderer();
		((PnlMonitorType)panel).setManufacturerRenderer(listManufacturerRenderer);
		renderer = new Renderer();
		tblCellManufacturerRenderer = renderer.new TblCellManufacturerRenderer();
		((PnlMonitorType)panel).setTblCellManufacturerRenderer(Manufacturer.class, tblCellManufacturerRenderer);
		monitorTypeIdRenderer = new MonitorTypeIdRenderer();
		((PnlMonitorType)panel).setMonitorTypeIdRenderer(MonitorType.class, monitorTypeIdRenderer);
		notationRenderer = new TblStringRenderer();
		((PnlMonitorType)panel).setNotationRenderer(String.class, notationRenderer);
		priceRenderer = new TblSearchPriceRenderer();
		panel.setDoubleRenderer(Double.class, priceRenderer);
		
	}
	
	private void setListener() {
		listener = new Listener();
		notationListener = listener.new NotationListener();	
		panel.addNotationListener(notationListener);
		noticeListener = listener.new NotationListener();
		panel.addNoticeListener(noticeListener);
		manufacturerListener = listener.new ManufacturerListener();
		((PnlMonitorType)panel).addManufacturerListener(manufacturerListener);
		priceListener = listener.new PriceListener();
		((PnlMonitorType)panel).addPriceChangeListener(priceListener);
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlMonitorType)panel).addTblRowSelectionListener(tblRowSelectionListener);
	}
	
	protected void setComponentText() {
		((PnlMonitorType)panel).setLblNotationText("Bezeichnung:");
		((PnlMonitorType)panel).setLblManufacturerText("Hersteller:");
		((PnlMonitorType)panel).setLblNoticeText("Bemerkung:");
		((PnlMonitorType)panel).setBtnCreateText("Eintragen");
		((PnlMonitorType)panel).setBtnDeleteText("Löschen");
		((PnlMonitorType)panel).setLblPriceText("Preis:");
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
				setFont(new Font("Tahoma", Font.ITALIC, 14));
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
		private Class[] classes = {MonitorType.class, String.class, Manufacturer.class, String.class, Double.class};
		
		protected String notice = "";
		protected MonitorType mt = null;
		protected Double price = 0.0;
		protected Manufacturer manuf = null;
		protected String searchNotation = "";
		
		public TblMonitorTypeModel(ArrayList<MonitorType> monitorTypes) {
			this.monitorTypes = monitorTypes;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Bemerkung");
			columnNames.add("Preis");
			
			//monitorTypes = new ArrayList<MonitorType>();
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
					case 0: return monitorTypes.get(rowIndex - 1);
					case 1: return monitorTypes.get(rowIndex - 1).getNotation();
					case 2: return monitorTypes.get(rowIndex - 1).getManufacturer();
					case 3: return monitorTypes.get(rowIndex - 1).getNotice();
					case 4: return monitorTypes.get(rowIndex - 1).getPrice();
					default: return null;
				}
			}else{
				switch (columnIndex) {
					case 0: return mt;
					case 1: return searchNotation;
					case 2: return manuf;
					case 3: return notice;
					case 4: return price;
					default: return null;
				}
			}
		}
		
		
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if(rowIndex == 0 && columnIndex == 2) {
				manuf = (Manufacturer)aValue;
			}else if (rowIndex == 0 && columnIndex == 1){
				searchNotation = (String)aValue;
			}else {
				super.setValueAt(aValue, rowIndex, columnIndex);
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
			if(rowIndex == 0 && (columnIndex == 2 || columnIndex ==1)) {
				return true;
			}else {
				return false;
			}
		}		
	}
	
	 public class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	      
		private static final long serialVersionUID = 3283574841392539652L;
		private TableCellEditor editor;
		private JComboBox<Manufacturer> cbxManufacturer;
		SearchManufacturerListener searchManufacturerListener;
		private ComboBoxModel<Manufacturer> cbxManufacturerModel;
		
		public CustomTableCellEditor() {
			ArrayList<Manufacturer> manufacturers = SQL_SELECT.manufacturers();
			Manufacturer[] arr = new Manufacturer[manufacturers.size() + 1]; 		  
	        // ArrayList to Array Conversion 
			arr[0] = new Manufacturer(" ");
	        for (int i = 1; i < manufacturers.size() + 1; i++) {
	            arr[i] = manufacturers.get(i - 1);		
	        }
			cbxManufacturerModel = new DefaultComboBoxModel<Manufacturer>(arr);
			cbxManufacturer = new JComboBox<Manufacturer>();
			cbxManufacturer.setModel(cbxManufacturerModel);
			cbxManufacturer.setRenderer(new ListManufacturerRenderer());
			searchManufacturerListener = new SearchManufacturerListener();
			cbxManufacturer.addItemListener(searchManufacturerListener);
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
            if (column == 2 && row == 0) {
                editor = new DefaultCellEditor(cbxManufacturer);
            } 

            return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        }      
        
    }
	 
	class MonitorTypeIdRenderer  extends JLabel implements TableCellRenderer{
		
		private static final long serialVersionUID = -3432983718798402753L;

		public MonitorTypeIdRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {
				if(value instanceof MonitorType) {
					setText(String.valueOf(((MonitorType) value).getId()));			
				}else {
					setText("");
				}
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}
			}else {
				setBackground(Color.white);
				setText("");
			}
			return this;
		}
		
	}
	
	class TblStringRenderer extends JLabel implements TableCellRenderer{
		
		
		private static final long serialVersionUID = 6835433470340104756L;


		public TblStringRenderer() {
			super.setOpaque(true);
		}		
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value.toString());
			if(row>0) {				
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}				
			}else {
				setBackground(Color.white);				
				
			}
			return this;			
		}		
	}
	
	class TblSearchPriceRenderer extends Renderer.TblDoubleRenderer implements TableCellRenderer{

		private static final long serialVersionUID = 4282300798910642941L;

		
		public TblSearchPriceRenderer() {
			renderer.super();
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			DecimalFormat df2 = new DecimalFormat("#.##");			
			setText(df2.format(value) + " €");			
			setHorizontalAlignment(JLabel.RIGHT);
			if(row>0) {	
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);  
				}
			}else {
				setBackground(Color.white);	
			}
			return this;
		}		
	}
	
	
	
	
	class SearchManufacturerListener implements ItemListener{
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
					if(manufacturer.getNotation() == " ") {
						manufacturer = null;
					}
				} catch (ClassCastException e) {
					manufacturer = null;				
				}
				
				try {
					tblMonitorTypeModel.setValueAt(manufacturer, 0, 2);
					tblMonitorTypeModel.setMonitorTypes(SQL_SELECT.monitorTypes((Manufacturer) tblMonitorTypeModel.getValueAt(0, 2), (String) tblMonitorTypeModel.getValueAt(0, 1)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tblMonitorTypeModel.fireTableDataChanged();
				((PnlMonitorType)panel).setFirstRowHeight(40);
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
			tblMonitorTypeModel.setValueAt(txt, 0, 1);
			try {
				tblMonitorTypeModel.setMonitorTypes(SQL_SELECT.monitorTypes((Manufacturer) tblMonitorTypeModel.getValueAt(0, 2), (String) tblMonitorTypeModel.getValueAt(0, 1)));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tblMonitorTypeModel.fireTableDataChanged();
			((PnlMonitorType)panel).setFirstRowHeight(40);
		}	
	}
	 
	 
	class TblRowSelectionListener implements ListSelectionListener{
		MonitorType monitorType;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlMonitorType)panel).getSelectedTblRow() > 0) {			
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
	
	protected PriceListener getPriceListener() {		
		return this.priceListener;
	}

	protected void addCreateListener(ActionListener listener) {
		((PnlMonitorType)panel).addCreateListener(listener);		
	}

	protected void addDeleteListener(ActionListener listener) {
		((PnlMonitorType)panel).addDeleteListener(listener);		
	}
	
	protected void setFirstRowHeight(int height) {
		((PnlMonitorType)panel).setFirstRowHeight(height);
	}
}
