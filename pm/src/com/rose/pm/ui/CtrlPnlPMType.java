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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.text.BadLocationException;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregatModel;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Type;



public class CtrlPnlPMType extends CtrlPnlBase{

	PnlPMType pnlPMType;
	PMTypeTblModel tblModel; 
	ComboBoxModel<Manufacturer> manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	ManufacturerListener manufacturerListener;
	NotationListener notationListener, noticeListener;
	RAListener raListener;
	RVListener rvListener;
	LVListener lvListener;
	MRIListener mriListener;
	Boolean ra = false;
	Boolean rv = false;
	Boolean lv = false;
	
	PM_Type[] pmType;
	TypeRenderer typeRenderer;
	TypeListener typeListener;
	DefaultComboBoxModel<PM_Type> pmTypeModel;
	CreateListener createListener;
	TablePMTypeRenderer tablePMTypeRenderer;
	TblStringRenderer tblStringRenderer;
	TblPMIDRenderer tblPMIDRenderer;
	TblBooleanRenderer tblBooleanRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	
	
	public CtrlPnlPMType() {
		createPanel();
		pmType = new PM_Type[]{PM_Type.Single_Chamber, PM_Type.Double_Chamber, PM_Type.CRT};
		setStandardModels();
		
		setModel();
		setRenderer();
		setListener();
		((PnlPMType)panel).setManufacturerIndex(-1);
		((PnlPMType)panel).setRVSelection(true);
		rv = true;
		((PnlPMType)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setComponentText();
	}
	
	protected void createPanel() {
		panel = new PnlPMType();
		panel.setName("Schrittmachertyp");
	}
	
	protected void setModel() {
		tblModel = new PMTypeTblModel(SQL_SELECT.pacemakerKinds());
		((PnlPMType)panel).setTblModel(tblModel);			
	}
	
	protected void setStandardModels() {
		ArrayList<Manufacturer> manufacturers = SQL_SELECT.manufacturers();
		Manufacturer[] arr = new Manufacturer[manufacturers.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < manufacturers.size(); i++) 
            arr[i] = manufacturers.get(i);		
		
		manufacturerModel = new DefaultComboBoxModel<Manufacturer>(arr);
	
		((PnlPMType)panel).setManufacturerModel(manufacturerModel);
		//model of the ComboBox displaying the types of pacemakers
		pmTypeModel = new DefaultComboBoxModel<PM_Type>(pmType);
		((PnlPMType)panel).setTypeModel(pmTypeModel);	
	}
	
	protected void setRenderer() {
		manufacturerRenderer = new ManufacturerRenderer();
		((PnlPMType)panel).setManufacturerRenderer(manufacturerRenderer);
		typeRenderer = new TypeRenderer();
		((PnlPMType)panel).setTypeRenderer(typeRenderer);
		tablePMTypeRenderer = new TablePMTypeRenderer();
		((PnlPMType)panel).setTablePMTypeRenderer(PM_Type.class, tablePMTypeRenderer);
		tblStringRenderer = new TblStringRenderer();
		((PnlPMType)panel).setTableStringRenderer(String.class, tblStringRenderer);
		tblPMIDRenderer = new TblPMIDRenderer();
		((PnlPMType)panel).setTableIDRenderer(AggregatModel.class, tblPMIDRenderer);
		tblBooleanRenderer = new TblBooleanRenderer();
		((PnlPMType)panel).setTableBooleanRenderer(Boolean.class, tblBooleanRenderer);
	}
	
	protected void setListener() {
		manufacturerListener = new ManufacturerListener();
		((PnlPMType)panel).addManufacturerListener(manufacturerListener);
		notationListener = new NotationListener();
		((PnlPMType)panel).addNotationListener(notationListener);
		typeListener = new TypeListener();
		((PnlPMType)panel).addTypeListener(typeListener);
		noticeListener = new NotationListener();
		((PnlPMType)panel).addNoticeListener(noticeListener);
		mriListener = new MRIListener();
		((PnlPMType)panel).addMRIListener(mriListener);
		rvListener = new RVListener();
		((PnlPMType)panel).addRVListener(rvListener);
		lvListener = new LVListener();
		((PnlPMType)panel).addLVListener(lvListener);
		raListener = new RAListener();
		((PnlPMType)panel).addRALIstener(raListener);
		createListener = new CreateListener();
		((PnlPMType)panel).addCreateListener(createListener);
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlPMType)panel).addTblRowSelectionListener(tblRowSelectionListener);
		
	}
	
	protected void setComponentText() {
		((PnlPMType)panel).setLblNotationText("Bezeichnung");
		((PnlPMType)panel).setLblManufacturerText("Hersteller");
		((PnlPMType)panel).setLblMRIText("MRT");
		((PnlPMType)panel).setLblNoticeText("Bemerkung");
		((PnlPMType)panel).setBtnCreateText("Eintragen");
	}
	
	protected void checkChambers() {
		if(ra && rv && lv) {
			//if crt
			pmTypeModel.setSelectedItem(pmType[2]);
			return;
		}else if(ra && rv || ra && lv || rv && lv) {
			//if double chamber
			pmTypeModel.setSelectedItem(pmType[1]);
			return;
		}else if(ra || rv || lv) {
			// if single chamber
			pmTypeModel.setSelectedItem(pmType[0]);
			return;
		}
	}
	
	protected void selectChamber(PM_Type type) {
		if(type instanceof PM_Type) {
			 switch (type) {
				case Single_Chamber:
					((PnlPMType)panel).setRASelection(false);
					((PnlPMType)panel).setRVSelection(true);
					((PnlPMType)panel).setLVSelection(false);
					ra = false; rv = true; lv = false;
					break;
				case Double_Chamber:
					((PnlPMType)panel).setRASelection(true);
					((PnlPMType)panel).setRVSelection(true);
					((PnlPMType)panel).setLVSelection(false);
					ra = true; rv = true; lv = false;
					break;
				case CRT:
					((PnlPMType)panel).setRASelection(true);
					((PnlPMType)panel).setRVSelection(true);
					((PnlPMType)panel).setLVSelection(true);
					ra = true; rv = true; lv = true;
					break;
				default:
					ra = false; rv = false; lv = false;
					break;
			}
		}else {
			((PnlPMType)panel).setRASelection(false);
			((PnlPMType)panel).setRVSelection(false);
			((PnlPMType)panel).setLVSelection(false);
			ra = false; rv = false; lv = false;
		}
	}
	
	class PMTypeTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends AggregatModel> aggregates;
		PM_Type type;
		
		public PMTypeTblModel(ArrayList<? extends AggregatModel> paceMakers) {
			this.aggregates = paceMakers;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Typ");
			columnNames.add("MRI");
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
			setType(aggregates.get(rowIndex));
			
			
				switch(columnIndex) {
				case 0: return aggregates.get(rowIndex);
				
				case 1: return aggregates.get(rowIndex).getNotation();
				
				case 2: return aggregates.get(rowIndex).getManufacturer().getNotation();
				
				case 3: return aggregates.get(rowIndex).getType();
				
				case 4: return aggregates.get(rowIndex).getMri();
				
				case 5: return aggregates.get(rowIndex).getNotice();
				
				default: return null;
				
				}	
			
		}
		
		protected void setType(AggregatModel pm) {
			if(pm.getRa() && pm.getRv() && pm.getLv()) {
				pm.setType(PM_Type.CRT);
			}else if(pm.getRa() && pm.getRv() || pm.getRa() && pm.getLv() || pm.getRv() && pm.getLv()) {
				pm.setType(PM_Type.Double_Chamber);
			}else if(pm.getRa() || pm.getRv() || pm.getLv()) {
				pm.setType(PM_Type.Single_Chamber);
			}else {
				pm.setType(null);
			}
		}
		
		protected void setAggregats(ArrayList<? extends AggregatModel> pm) {
			this.aggregates = pm;
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
	
	/**
	 * listener for the JTextField txtNotation
	 * @author Ekkehard
	 *
	 */
	class NotationListener implements DocumentListener{
		String notation = "";
		@Override
		public void changedUpdate(DocumentEvent e) {			
			setNotation(e);	
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			setNotation(e);	
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setNotation(e);	
		}
		
		private void setNotation(DocumentEvent e) {
			try {
				notation = e.getDocument().getText(0, e.getDocument().getLength());
			} catch (BadLocationException e1) {
				notation = "";
			}
		}
		
		protected String getNotation() {
			return this.notation;
		}
		
	}
	
	class MRIListener implements ActionListener{
		Boolean mri;
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
	
	class TypeModel implements ComboBoxModel<PM_Type>{

		int indexSel;
		PM_Type[] types;		
		public TypeModel(PM_Type[] types) {
			this.types = types;

		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PM_Type getElementAt(int index) {
			// TODO Auto-generated method stub
			return types[index];
		}

		@Override
		public int getSize() {
			return types.length;
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			return types[indexSel];
		}

		@Override
		public void setSelectedItem(Object pmType) {
			
			for(int i=0; i<types.length; i++) {
				if(types[i].equals(pmType)) {
					indexSel = i;
				}
			}
			
		}
		
	}
	
	/**
	 * renderer for the comboBox that displays the types of pacemakers (Single-, double-chamber or CRT)
	 * @author user2
	 *
	 */
	class TypeRenderer extends JLabel implements ListCellRenderer<PM_Type>{


		/**
		 * 
		 */
		private static final long serialVersionUID = 5665803606940459417L;

		@Override
		public Component getListCellRendererComponent(JList<? extends PM_Type> list, PM_Type type, int index,
				boolean isSelected, boolean hasFocus) {
			if(type instanceof PM_Type) {
				switch(type) {
					case Single_Chamber:
						setText("Einkammer");
						break;
					case Double_Chamber:
						setText("Doppelkammer");
						break;
					case CRT:
						setText("CRT");
						break;
					default:
						setText("Einkammer");					
				}
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	
	
	class TypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<PM_Type> cb = (JComboBox<PM_Type>)e.getSource();
	        PM_Type type = (PM_Type)cb.getSelectedItem();
	       selectChamber(type);
			
		}
		
	}
	
	class RAListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			ra = selected;
			checkChambers();
		}
		
	}
	
	class RVListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			rv = selected;
			checkChambers();
		}
		
	}
	
	class LVListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			lv = selected;
			checkChambers();
		}
		
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(manufacturerListener.getManufacturer() != null && notationListener.getNotation() != null && checkElectrodes()) {
//				if(icd) {
//					ICD_Model aggModel = new ICD_Model(notation);
//					aggModel.setManufacturer(manufacturer);
//					aggModel.setRa(ra);
//					aggModel.setRv(rv);
//					aggModel.setLv(lv);
//					aggModel.setMri(mri);
//					SQL_INSERT.icd_Model((ICD_Model) aggModel);
//					tblPMKindModel.setAggregats(SQL_SELECT.ICD_Kinds());
//				}else {
					AggregatModel aggModel = new AggregatModel(notationListener.getNotation());
					aggModel.setManufacturer(manufacturerListener.getManufacturer());
					aggModel.setRa(ra);
					aggModel.setRv(rv);
					aggModel.setLv(lv);
					aggModel.setMri(mriListener.getMRI());
					aggModel.setNotice(noticeListener.getNotation());
					SQL_INSERT.pacemakerModel(aggModel);
					tblModel.setAggregats(SQL_SELECT.pacemakerKinds());
				//}	
				
				tblModel.fireTableDataChanged();
				((PnlPMType)panel).emptyTextFields();
				System.out.println(notationListener.getNotation());
			}
			
		}
		
		/**
		 * check if at least one Electrode was selected
		 * @return
		 */
		Boolean checkElectrodes() {
			if(ra || rv || lv) {
				return true;
			}
			return false;
		}
		
	}
	
	/**
	 * renderer for the table that displays the kinds of pacemakers
	 * @author user2
	 *
	 */
	class TablePMTypeRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4054908565583961074L;
		private String notation;
		
		public TablePMTypeRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if(value instanceof PM_Type) {
				changeChamberNotation((PM_Type) value);
				setText(notation);
			}else {
				setText("");
			}
			
			 
			
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
				
			//}
			return this;
		}
		
		private void changeChamberNotation(PM_Type type) {
			switch(type) {
			case Single_Chamber:
				notation = "Einkammer";
				break;
			case Double_Chamber:
				notation = "Doppelkammer";
				break;
			case CRT:
				notation = "CRT";
				break;
			default:
					notation = "";
			}
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
			Integer id = ((AggregatModel)value).getId();
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
	
	class TblBooleanRenderer extends JCheckBox implements TableCellRenderer{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 6237739721842901289L;
	
		public TblBooleanRenderer() {
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

	class TblRowSelectionListener implements ListSelectionListener{
		AggregatModel pmType;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlPMType)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlPMType)panel).getSelectedTblRow();
	            pmType = (AggregatModel) ((PnlPMType)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}		
	}
	
	
	
	
		
	
}
