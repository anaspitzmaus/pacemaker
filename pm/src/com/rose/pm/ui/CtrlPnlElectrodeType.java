package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Kind;
import com.rose.pm.ui.Listener.ManufacturerListener;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Listener.PriceListener;
import com.rose.pm.ui.Renderer.TblDoubleRenderer;


public class CtrlPnlElectrodeType extends CtrlPnlBase{
	
	ComboBoxModel<Manufacturer> manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	Listener listener;
	Renderer renderer;
	ManufacturerListener manufacturerListener;
	FixModeListener fixModeListener;
	MRIListener mriListener;	
	NotationListener notationListener, noticeListener;
	LengthListener lengthListener;
	TblElectrodesModel tblElectrodesModel;
	TblElectrodeModelBooleanRenderer tblElectrodeModelBooleanRenderer;
	TblElectrodeModelEMRenderer tblElectrodeModelEMRenderer;
	TblElectrodeModelIDRenderer tblElectrodeModelIDRenderer;
	TblElectrodeModelStringRenderer tblElectrodeModelStringRenderer;
	TblIntegerRenderer tblLengthRenderer;
	TblDoubleRenderer tblDoubleRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	PriceListener priceListener;
	
	
	protected NotationListener getNotationListener() {
		return this.notationListener;
	}
	
	protected NotationListener getNoticeListener() {
		return this.noticeListener;
	}
	
	protected ComboBoxModel<Manufacturer> getManufacturerModel(){
		return this.manufacturerModel;
	}
	
	protected FixModeListener getFixModeListener() {
		return this.fixModeListener;
	}
	
	protected LengthListener getLengthListener() {
		return this.lengthListener;
	}
	
	protected MRIListener getMRIListener() {
		return this.mriListener;
	}
	
	protected TblElectrodesModel getTblElectrodeModel() {
		return this.tblElectrodesModel;
	}
	
	public CtrlPnlElectrodeType() {
		panel = new PnlElectrodeType();
		panel.setName("Elektrodenmodel");
		panel.setOpaque(false);
		panel.setBackground(Color.BLUE);
		setListener();
		setModel();
		setRenderer();
		setComponentText();
		((PnlElectrodeType)panel).setManufacturerIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void setListener() {
		listener = new Listener();
		notationListener = listener.new NotationListener();	
		panel.addNotationListener(notationListener);
		noticeListener = listener.new NotationListener();
		panel.addNoticeListener(noticeListener);
		manufacturerListener = listener.new ManufacturerListener();
		((PnlElectrodeType)panel).addManufacturerListener(manufacturerListener);
		fixModeListener = new FixModeListener();
		((PnlElectrodeType)panel).addFixModeListener(fixModeListener);
		lengthListener = new LengthListener();
		((PnlElectrodeType)panel).addLengthListener(lengthListener);
		lengthListener.length = ((PnlElectrodeType)panel).getLength();
		mriListener = new MRIListener();
		((PnlElectrodeType)panel).addMRIListener(mriListener);

		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlElectrodeType)panel).addTblRowSelectionListener(tblRowSelectionListener);
		priceListener = listener.new PriceListener();
		((PnlElectrodeType)panel).addPriceChangeListener(priceListener);
		
	}
	
	private void setModel() {
		ArrayList<Manufacturer> manufacturers = SQL_SELECT.manufacturers();
		Manufacturer[] arr = new Manufacturer[manufacturers.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < manufacturers.size(); i++) 
            arr[i] = manufacturers.get(i);		
		
		manufacturerModel = new DefaultComboBoxModel<Manufacturer>(arr);
	
		((PnlElectrodeType)panel).setManufacturerModel(manufacturerModel);
		
		tblElectrodesModel = new TblElectrodesModel(SQL_SELECT.electrodeModels());
		panel.setTblModel(tblElectrodesModel);
	}
	
	private void setRenderer() {
		manufacturerRenderer = new ManufacturerRenderer();
		((PnlElectrodeType)panel).setManufacturerRenderer(manufacturerRenderer);
		tblElectrodeModelBooleanRenderer = new TblElectrodeModelBooleanRenderer();
		((PnlElectrodeType)panel).setTableBooleanRenderer(Boolean.class, tblElectrodeModelBooleanRenderer);
		tblElectrodeModelEMRenderer = new TblElectrodeModelEMRenderer();
		((PnlElectrodeType)panel).setTableEMTypeRenderer(ElectrodeType.class, tblElectrodeModelEMRenderer);
		tblLengthRenderer = new TblIntegerRenderer();
		((PnlElectrodeType)panel).setTblLengthRenderer(Integer.class, tblLengthRenderer);
		tblElectrodeModelStringRenderer = new TblElectrodeModelStringRenderer();
		((PnlElectrodeType)panel).setTableStringRenderer(String.class, tblElectrodeModelStringRenderer);
		renderer = new Renderer();
		tblDoubleRenderer = renderer.new TblDoubleRenderer();
		((PnlElectrodeType)panel).setTblDoubleRenderer(Double.class, tblDoubleRenderer);
	}

	protected void setComponentText() {
		((PnlElectrodeType)panel).setLblNotationText("Bezeichnung:");
		((PnlElectrodeType)panel).setLblManufacturerText("Hersteller:");
		((PnlElectrodeType)panel).setLblMRIText("MRT:");
		((PnlElectrodeType)panel).setLblNoticeText("Bemerkung:");
		((PnlElectrodeType)panel).setBtnCreateText("Eintragen");
		((PnlElectrodeType)panel).setBtnDeleteText("Löschen");
		((PnlElectrodeType)panel).setLblFixText("Fixierung:");
		((PnlElectrodeType)panel).setLblLengthText("Länge:");
		((PnlElectrodeType)panel).setLblPriceText("Preis:");
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
			//setSelectedItem(null);
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
	
	
	
	class FixModeListener implements ActionListener{
		String fixMode = "Schraube";
		
		protected String getFixMode() {
			return this.fixMode;
		}
		
		public FixModeListener() {
			((PnlElectrodeType)panel).setTglFixText(fixMode);			
		}		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        
	        if(selected) {
				abstractButton.setText("Anker");						
				
			}else {
				abstractButton.setText("Schraube");			
				
			}
		       	        
		}		
	}
	
	class LengthListener implements ChangeListener{
		Integer length;
		
		protected Integer getLength() {
			return this.length;
		}
		
		@Override
		public void stateChanged(ChangeEvent event) {
			JSpinner spinner = (JSpinner) event.getSource();
			length = (Integer) spinner.getValue();
		}
		
	}
	
	
	
	class MRIListener implements ActionListener{
		Boolean mri = false;
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        mri = selected;			
		}
		
		protected Boolean getMRI() {
			return this.mri;
		}
		
	}
	
	class TblElectrodesModel extends AbstractTableModel{		
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -5397061877255389159L;
			protected ArrayList<String> columnNames;
			ArrayList<ElectrodeType> electrodeModels;
			PM_Kind type;
			
			
			
			protected void setElectrodeModels(ArrayList<ElectrodeType> electrodeModels) {
				this.electrodeModels = electrodeModels;
			}
			
			protected ArrayList<ElectrodeType> getElectrodeModels(){
				return this.electrodeModels;
			}
	
			public TblElectrodesModel(ArrayList<ElectrodeType> electrodeModels) {
				this.electrodeModels = electrodeModels;
				columnNames = new ArrayList<String>();
				columnNames.add("Id");
				columnNames.add("Bezeichnung");
				columnNames.add("Hersteller");
				columnNames.add("Länge");
				columnNames.add("Fixierung");
				columnNames.add("MRI");
				columnNames.add("Anmerkung");
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
				return this.electrodeModels.size();
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Class getColumnClass(int col) {
				return getValueAt(0, col).getClass();
			}
	
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				
				
					switch(columnIndex) {
					case 0: return electrodeModels.get(rowIndex);
					
					case 1: return electrodeModels.get(rowIndex).getNotation();
					
					case 2: return electrodeModels.get(rowIndex).getManufacturer().getNotation();
					
					case 3: return electrodeModels.get(rowIndex).getLength();
					
					case 4: return electrodeModels.get(rowIndex).getFixMode();
					
					case 5: return electrodeModels.get(rowIndex).getMri();
	
					case 6: return electrodeModels.get(rowIndex).getNotice();
					
					case 7: return electrodeModels.get(rowIndex).getPrice();
					
					default: return null;
					
					}	
			}
				
	}

	class TblElectrodeModelEMRenderer extends JLabel implements TableCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7893845222434501963L;
	
		public TblElectrodeModelEMRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((ElectrodeType) value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);  
			}
			return this;
		}
	}
	
	class TblElectrodeModelIDRenderer extends JLabel implements TableCellRenderer{
	
			
		/**
		 * 
		 */
		private static final long serialVersionUID = 8941880047794362140L;
			public TblElectrodeModelIDRenderer() {
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
	
	class TblElectrodeModelStringRenderer extends JLabel implements TableCellRenderer{
	
		
		
			/**
		 * 
		 */
		private static final long serialVersionUID = 3150560739954111797L;
	
			public TblElectrodeModelStringRenderer() {
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
	
	class TblElectrodeModelBooleanRenderer extends JCheckBox implements TableCellRenderer{
		
	
		/**
		 * 
		 */
		private static final long serialVersionUID = -7591172567469251176L;
	
		public TblElectrodeModelBooleanRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setSelected((boolean) value);
			setHorizontalAlignment(CENTER);
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);  
			}
			return this;
		}
		
	}
	
	class TblIntegerRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4401669427993767316L;

		public TblIntegerRenderer() {
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
		ElectrodeType elModel;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlElectrodeType)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlElectrodeType)panel).getSelectedTblRow();
	            elModel = (ElectrodeType) ((PnlElectrodeType)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		
		protected ElectrodeType getElectrodeModelSelected() {
			return elModel;
		}		
	}
	
	
	
	
	
	protected void addCreateListener(ActionListener l) {
		((PnlElectrodeType)panel).addCreateListener(l);
	}

	protected TblRowSelectionListener getTblRowSelectionListener() {
		return this.tblRowSelectionListener;		
	}

	public void addDeleteListener(ActionListener deleteTypeListener) {
		((PnlElectrodeType)panel).addDeleteListener(deleteTypeListener);
		
	}

	protected PriceListener getPriceListener() {		
		return this.priceListener;
	}
}
