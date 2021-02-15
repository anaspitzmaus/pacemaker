package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Kind;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Listener.PriceListener;
import com.rose.pm.ui.Renderer.TblDoubleRenderer;



public class CtrlPnlPMType extends CtrlPnlBase{

	PnlPMType pnlPMType;
	PMTypeTblModel tblModel; 
	ComboBoxModel<Manufacturer> manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	ManufacturerListener manufacturerListener;
	Listener listener;
	NotationListener notationListener, noticeListener;
	RAListener raListener;
	RVListener rvListener;
	LVListener lvListener;
	MRIListener mriListener;
	Boolean ra = false;
	Boolean rv = false;
	Boolean lv = false;	
	PM_Kind[] pmType;
	TypeRenderer typeRenderer;
	TypeListener typeListener;
	DefaultComboBoxModel<PM_Kind> pmTypeModel;
	TablePMTypeRenderer tablePMTypeRenderer;
	TblStringRenderer tblStringRenderer;
	TblPMIDRenderer tblPMIDRenderer;
	TblBooleanRenderer tblBooleanRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	PriceListener priceListener;
	Renderer renderer;
	TblDoubleRenderer tblDoubleRenderer;
	
	
	
	public CtrlPnlPMType() {
		createPanel();
		pmType = new PM_Kind[]{PM_Kind.Single_Chamber, PM_Kind.Double_Chamber, PM_Kind.CRT};
		setStandardModels();		
		setModel();
		setRenderer();
		setStandardListener();
		//setListener();
		((PnlPMType)panel).setManufacturerIndex(-1);
		((PnlPMType)panel).setRVSelection(true);
		rv = true;
		((PnlPMType)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setComponentText();
	}
	
	protected void createPanel() {
		panel = new PnlPMType();
		panel.setName("Schrittmachermodel");
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
		pmTypeModel = new DefaultComboBoxModel<PM_Kind>(pmType);
		((PnlPMType)panel).setTypeModel(pmTypeModel);	
	}
	
	protected void setRenderer() {
		manufacturerRenderer = new ManufacturerRenderer();
		((PnlPMType)panel).setManufacturerRenderer(manufacturerRenderer);
		typeRenderer = new TypeRenderer();
		((PnlPMType)panel).setTypeRenderer(typeRenderer);
		tablePMTypeRenderer = new TablePMTypeRenderer();
		((PnlPMType)panel).setTablePMTypeRenderer(PM_Kind.class, tablePMTypeRenderer);
		tblStringRenderer = new TblStringRenderer();
		((PnlPMType)panel).setTableStringRenderer(String.class, tblStringRenderer);
		tblPMIDRenderer = new TblPMIDRenderer();
		((PnlPMType)panel).setTableIDRenderer(AggregateType.class, tblPMIDRenderer);
		tblBooleanRenderer = new TblBooleanRenderer();
		((PnlPMType)panel).setTableBooleanRenderer(Boolean.class, tblBooleanRenderer);
		renderer = new Renderer();
		tblDoubleRenderer = renderer.new TblDoubleRenderer();
		((PnlPMType)panel).setTblDoubleRenderer(Double.class, tblDoubleRenderer);
	}
	
//	protected void setListener() {
//		createListener = new CreateListener();
//		((PnlPMType)panel).addCreateListener(createListener);
//	}
	
	protected void setStandardListener() {
		listener = new Listener();
		manufacturerListener = new ManufacturerListener();
		((PnlPMType)panel).addManufacturerListener(manufacturerListener);
		notationListener = listener.new NotationListener();
		((PnlPMType)panel).addNotationListener(notationListener);
		typeListener = new TypeListener();
		((PnlPMType)panel).addTypeListener(typeListener);
		noticeListener = listener.new NotationListener();
		((PnlPMType)panel).addNoticeListener(noticeListener);
		mriListener = new MRIListener();
		((PnlPMType)panel).addMRIListener(mriListener);
		rvListener = new RVListener();
		((PnlPMType)panel).addRVListener(rvListener);
		lvListener = new LVListener();
		((PnlPMType)panel).addLVListener(lvListener);
		raListener = new RAListener();
		((PnlPMType)panel).addRALIstener(raListener);
		priceListener = listener.new PriceListener();
		((PnlPMType)panel).addPriceChangeListener(priceListener);
		tblRowSelectionListener = new TblRowSelectionListener();
		((PnlPMType)panel).addTblRowSelectionListener(tblRowSelectionListener);
		
	}
	
	protected void setComponentText() {
		((PnlPMType)panel).setLblNotationText("Bezeichnung");
		((PnlPMType)panel).setLblManufacturerText("Hersteller");
		((PnlPMType)panel).setLblMRIText("MRT");
		((PnlPMType)panel).setLblNoticeText("Bemerkung");
		((PnlPMType)panel).setBtnCreateText("Eintragen");
		((PnlPMType)panel).setBtnDeleteText("Löschen");
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
	
	protected void selectChamber(PM_Kind type) {
		if(type instanceof PM_Kind) {
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

		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends AggregateType> aggregates;
		PM_Kind type;
		
		public PMTypeTblModel(ArrayList<? extends AggregateType> aggregates) {
			this.aggregates = aggregates;
			setColumns();		
		}
		
		protected void setColumns() {
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Typ");
			columnNames.add("MRI");			
			columnNames.add("Bemerkung");	
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
				
				case 6: return aggregates.get(rowIndex).getPrice();
				
				default: return null;
				
				}	
			
		}
		
		protected void setType(AggregateType pm) {
			if(pm.getRa() && pm.getRv() && pm.getLv()) {
				pm.setType(PM_Kind.CRT);
			}else if(pm.getRa() && pm.getRv() || pm.getRa() && pm.getLv() || pm.getRv() && pm.getLv()) {
				pm.setType(PM_Kind.Double_Chamber);
			}else if(pm.getRa() || pm.getRv() || pm.getLv()) {
				pm.setType(PM_Kind.Single_Chamber);
			}else {
				pm.setType(null);
			}
		}
		
		protected void setAggregats(ArrayList<? extends AggregateType> pm) {
			this.aggregates = pm;			
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
	
	class TypeModel implements ComboBoxModel<PM_Kind>{

		int indexSel;
		PM_Kind[] types;		
		public TypeModel(PM_Kind[] types) {
			this.types = types;

		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PM_Kind getElementAt(int index) {
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
	class TypeRenderer extends JLabel implements ListCellRenderer<PM_Kind>{


		/**
		 * 
		 */
		private static final long serialVersionUID = 5665803606940459417L;

		@Override
		public Component getListCellRendererComponent(JList<? extends PM_Kind> list, PM_Kind type, int index,
				boolean isSelected, boolean hasFocus) {
			if(type instanceof PM_Kind) {
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
			JComboBox<PM_Kind> cb = (JComboBox<PM_Kind>)e.getSource();
	        PM_Kind type = (PM_Kind)cb.getSelectedItem();
	       selectChamber(type);
			
		}
		
	}
	
	class RAListener implements ActionListener{

		protected Boolean getValue() {
			return ra;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			ra = selected;
			checkChambers();
		}
		
	}
	
	class RVListener implements ActionListener{

		protected Boolean getValue() {
			return rv;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			rv = selected;
			checkChambers();
		}
		
	}
	
	class LVListener implements ActionListener{

		protected Boolean getValue() {
			return lv;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			lv = selected;
			checkChambers();
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
			
			if(value instanceof PM_Kind) {
				changeChamberNotation((PM_Kind) value);
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
		
		private void changeChamberNotation(PM_Kind type) {
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
			Integer id = ((AggregateType)value).getId();
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
		AggregateType pmType;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlPMType)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlPMType)panel).getSelectedTblRow();
	            pmType = (AggregateType) ((PnlPMType)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		protected AggregateType getAggregatSelected() {
			return pmType;
		}		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tblRowSelectionListener.getAggregatSelected() instanceof AggregateType) {
				if(SQL_UPDATE.deleteAggregatModel(tblRowSelectionListener.getAggregatSelected())){
					tblModel.aggregates.remove(tblRowSelectionListener.getAggregatSelected());
					tblModel.fireTableDataChanged();
				}
			}
			
		}
		
	}

	protected NotationListener getNotationListener() {
		return this.notationListener;
	}

	protected ComboBoxModel<Manufacturer> getManufacturerModel() {
		return this.manufacturerModel;
	}

	protected MRIListener getMRIListener() {
		return this.mriListener;
	}

	protected NotationListener getNoticeListener() {
		return this.noticeListener;
	}
	
	protected PMTypeTblModel getAggregateTypeTableModel() {
		return this.tblModel;
	}

	protected void addCreateListener(ActionListener createTypeListener) {
		((PnlPMType)panel).addCreateListener(createTypeListener);
		
	}

	protected  RAListener getRAListener() {
		return this.raListener;
	}

	protected RVListener getRVListener() {
		return this.rvListener;
	}
	
	protected LVListener getLVListener() {
		return this.lvListener;
	}

	protected TblRowSelectionListener getTblRowSelectionListener() {		
		return this.tblRowSelectionListener;
	}

	protected void addDeleteListener(ActionListener deleteTypeListener) {
		((PnlPMType)panel).addDeleteListener(deleteTypeListener);		
	}

	protected PropertyChangeListener getPriceListener() {
		return this.priceListener;
	}

	
	
	
	
	
		
	
}
