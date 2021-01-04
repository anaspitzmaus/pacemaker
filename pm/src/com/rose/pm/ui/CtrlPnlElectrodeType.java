package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.ElectrodeModel;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Kind;
import com.rose.pm.ui.Listener.NotationListener;


public class CtrlPnlElectrodeType extends CtrlPnlBase{
	
	ComboBoxModel<Manufacturer> manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	ManufacturerListener manufacturerListener;
	FixModeListener fixModeListener;
	MRIListener mriListener;
	Listener listener;
	NotationListener notationListener, noticeListener;
	LengthListener lengthListener;
	TblElectrodesModel tblElectrodesModel;
	TblElectrodeModelBooleanRenderer tblElectrodeModelBooleanRenderer;
	TblElectrodeModelEMRenderer tblElectrodeModelEMRenderer;
	TblElectrodeModelIDRenderer tblElectrodeModelIDRenderer;
	TblElectrodeModelStringRenderer tblElectrodeModelStringRenderer;
	
	
	
	public CtrlPnlElectrodeType() {
		panel = new PnlElectrodeType();
		panel.setName("Elektrodentyp");
		setListener();
		setModel();
		setRenderer();
		setComponentText();
		((PnlElectrodeType)panel).setManufacturerIndex(-1);
	}
	
	private void setListener() {
		listener = new Listener();
		notationListener = listener.new NotationListener();	
		((PnlElectrodeType)panel).addNotationListener(notationListener);
		noticeListener = listener.new NotationListener();
		((PnlElectrodeType)panel).addNoticeListener(noticeListener);
		manufacturerListener = new ManufacturerListener();
		((PnlElectrodeType)panel).addManufacturerListener(manufacturerListener);
		fixModeListener = new FixModeListener();
		((PnlElectrodeType)panel).addfixModeListener(fixModeListener);
		lengthListener = new LengthListener();
		((PnlElectrodeType)panel).addLengthListener(lengthListener);
		lengthListener.length = ((PnlElectrodeType)panel).getLength();
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
		((PnlElectrodeType)panel).setTblElectrodesModel(tblElectrodesModel);
	}
	
	private void setRenderer() {
		manufacturerRenderer = new ManufacturerRenderer();
		((PnlElectrodeType)panel).setManufacturerRenderer(manufacturerRenderer);
		tblElectrodeModelBooleanRenderer = new TblElectrodeModelBooleanRenderer();
		((PnlElectrodeType)panel).setTableBooleanRenderer(Boolean.class, tblElectrodeModelBooleanRenderer);
		tblElectrodeModelEMRenderer = new TblElectrodeModelEMRenderer();
//		((PnlElectrodeType)panel).setTableEMTypeRenderer(ElectrodeModel.class, tblElectrodeModelEMRenderer);
//		tblElectrodeModelIDRenderer = new TblElectrodeModelIDRenderer();
		((PnlElectrodeType)panel).setTableIDRenderer(ElectrodeModel.class, tblElectrodeModelIDRenderer);
		tblElectrodeModelStringRenderer = new TblElectrodeModelStringRenderer();
		((PnlElectrodeType)panel).setTableStringRenderer(String.class, tblElectrodeModelStringRenderer);
	}

	protected void setComponentText() {
		((PnlElectrodeType)panel).setLblNotationText("Bezeichnung");
		((PnlElectrodeType)panel).setLblManufacturerText("Hersteller");
		((PnlElectrodeType)panel).setLblMRIText("MRT");
		((PnlElectrodeType)panel).setLblNoticeText("Bemerkung");
		((PnlElectrodeType)panel).setBtnCreateText("Eintragen");
		((PnlElectrodeType)panel).setBtnDeleteTest("Löschen");
		((PnlElectrodeType)panel).setLblFixText("Fixierung");
		((PnlElectrodeType)panel).setLblLengthText("Länge");
		
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
			ArrayList<ElectrodeModel> electrodeModels;
			PM_Kind type;
			
			
			
			protected void setElectrodeModels(ArrayList<ElectrodeModel> electrodeModels) {
				this.electrodeModels = electrodeModels;
			}
			
			protected ArrayList<ElectrodeModel> getElectrodeModels(){
				return this.electrodeModels;
			}
	
			public TblElectrodesModel(ArrayList<ElectrodeModel> electrodeModels) {
				this.electrodeModels = electrodeModels;
				columnNames = new ArrayList<String>();
				columnNames.add("Id");
				columnNames.add("Bezeichnung");
				columnNames.add("Hersteller");
				columnNames.add("Länge");
				columnNames.add("Fixierung");
				columnNames.add("MRI");
				columnNames.add("Anmerkung");
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
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
				int row) {
			Integer id = ((ElectrodeModel) value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
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
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
					int row) {
				
				setText(value.toString());
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(Color.WHITE);
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
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
					int row) {
				
					setText(value.toString());
				
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(Color.WHITE);
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
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
				int row) {
			setSelected((boolean) value);
			setHorizontalAlignment(CENTER);
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(notationListener.getNotation() != "" && manufacturerModel.getSelectedItem() instanceof Manufacturer) {
				ElectrodeModel model = new ElectrodeModel(notationListener.getNotation());
				model.setManufacturer((Manufacturer) manufacturerModel.getSelectedItem());
				model.setFixMode(fixModeListener.getFixMode());
				model.setLength(lengthListener.getLength());
				model.setMri(mriListener.getMRI());
				model.setNotice(noticeListener.getNotation());
				SQL_INSERT.electrodeModel(model);	
					
				tblElectrodesModel.setElectrodeModels(SQL_SELECT.electrodeModels());
				tblElectrodesModel.fireTableDataChanged();
				
			}
		}
		
	}
}
