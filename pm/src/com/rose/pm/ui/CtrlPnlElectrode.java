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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.PM;

import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblDateRenderer;
import com.rose.pm.ui.Renderer.TblStringRenderer;



public class CtrlPnlElectrode extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	ComboBoxModel<ElectrodeType> electrodeTypeModel;
	ElectrodeTblModel electrodeTblModel;
	ElectrodeTypeListener electrodeTypeListener;
	ElectrodeTypeRenderer electrodeTypeRenderer;
	Listener listener;
	Renderer renderer;
	NotationListener notationListener;
	TblDateRenderer tblDateRenderer;
	TblStringRenderer tblStringRenderer;
	TblElectrodeIDRenderer electrodeRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	
	
	public CtrlPnlElectrode() {
		createPanel();
		setComponentLabeling();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlElectrode)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
	}
	
	protected void createPanel() {
		panel = new PnlElectrode();
		panel.setName("Elektroden");
		setListener();
		setModel();
		setRenderer();
		((PnlElectrode)panel).setElectrodeTypeSelectionIndex(-1);
		((PnlElectrode)panel).setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	protected void setComponentLabeling() {
		((PnlElectrode)panel).setLblElectrodeTypeText("Elektrodenmodel:");
		((PnlElectrode)panel).setLblSerialNrText("Seriennummer:");
		((PnlElectrode)panel).setLblNoticeText("Bemerkung:");
		((PnlElectrode)panel).setBtnCreateText("Einfügen");
		((PnlElectrode)panel).setBtnDeleteText("Löschen");
		((PnlElectrode)panel).setBtnShowAllText("Alle Modelle");
	}
	
	 void setModel() {
		ArrayList<ElectrodeType> electrodeTypes = SQL_SELECT.electrodeModels();
		ElectrodeType[] arr = new ElectrodeType[electrodeTypes.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < electrodeTypes.size(); i++) 
            arr[i] = electrodeTypes.get(i);		
		
        electrodeTypeModel = new DefaultComboBoxModel<ElectrodeType>(arr);
		//aggregateTypeModel = new AggregateTypeModel();
		((PnlElectrode)panel).setElectrodeTypeModel(electrodeTypeModel);
		electrodeTblModel = new ElectrodeTblModel(SQL_SELECT.electrodes(electrodeTypeListener.model));
		((PnlElectrode)panel).setElectrodeTblModel(electrodeTblModel);
	}
	 
	 private void setListener() {
		 electrodeTypeListener = new ElectrodeTypeListener();
		 ((PnlElectrode)panel).addElectrodeTypeListener(electrodeTypeListener);	
		 listener = new Listener();
		 notationListener = listener.new NotationListener();
		 ((PnlElectrode)panel).addSerialNrListener(notationListener);
		 tblRowSelectionListener = new TblRowSelectionListener();
		 ((PnlElectrode)panel).addTblRowSelectionListener(tblRowSelectionListener);
		 
	 }
	 
	 private void setRenderer() {
		 electrodeTypeRenderer = new ElectrodeTypeRenderer();
		 ((PnlElectrode)panel).setElectrodeTypeRenderer(electrodeTypeRenderer);
		 renderer = new Renderer();
		 tblDateRenderer = renderer.new TblDateRenderer();
		 ((PnlElectrode)panel).setDateRenderer(LocalDate.class, tblDateRenderer);
		 tblStringRenderer = renderer.new TblStringRenderer();
		 ((PnlElectrode)panel).setStringRenderer(String.class, tblStringRenderer);
		 electrodeRenderer = new TblElectrodeIDRenderer();
		 ((PnlElectrode)panel).setElectrodeRenderer(Electrode.class, electrodeRenderer);
	 }

	 class ElectrodeTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends Electrode> electrodes;
		
		
		public ElectrodeTblModel(ArrayList<? extends Electrode> electrodes) {
			this.electrodes = electrodes;
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
			return this.electrodes.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
			case 0: return electrodes.get(rowIndex);
			
			case 1: return electrodes.get(rowIndex).getSerialNr();
			
			case 2: return electrodes.get(rowIndex).getExpireDate();
			
			case 3: return electrodes.get(rowIndex).getNotice();
			
			default: return null;
			
			}	
			
		}
		
		
		
		protected void setElectrodes(ArrayList<? extends Electrode> el) {
			this.electrodes = el;		
		}
			
	}
	 
	 class ElectrodeTypeListener implements ItemListener{

		ElectrodeType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (ElectrodeType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}				
		    }
			updateTblModel();		
		}
		
		protected void updateTblModel() {
			electrodeTblModel.setElectrodes(SQL_SELECT.electrodes(model));			
			electrodeTblModel.fireTableDataChanged();
		}		
	}
	 
	 class ElectrodeTypeRenderer extends JLabel implements ListCellRenderer<ElectrodeType>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7934129282290942751L;
		
		public ElectrodeTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ElectrodeType> list, ElectrodeType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof ElectrodeType) {
				setText(((ElectrodeType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
		
	}
	 
	 class TblElectrodeIDRenderer extends JLabel implements TableCellRenderer{

			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7687161924946698926L;

			public TblElectrodeIDRenderer() {
				super.setOpaque(true);
			}
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				Integer id = ((Electrode)value).getId();
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
			Electrode electrode;
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (((PnlElectrode)panel).getSelectedTblRow() > -1) {			
					int row = ((PnlElectrode)panel).getSelectedTblRow();
		            electrode = (Electrode) ((PnlElectrode)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
		        }			
			}
			
			protected Electrode getElectrodeSelected() {
				return electrode;
			}		
		}
		
		class DeleteListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblRowSelectionListener.getElectrodeSelected() instanceof Electrode) {
					if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
						if(SQL_UPDATE.deleteElectrode(tblRowSelectionListener.getElectrodeSelected())){
							electrodeTblModel.electrodes.remove(tblRowSelectionListener.getElectrodeSelected());
							electrodeTblModel.fireTableDataChanged();
						}
					}
				
				}
			
			}
		}
	
}
