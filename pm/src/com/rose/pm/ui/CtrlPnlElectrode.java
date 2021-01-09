package com.rose.pm.ui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.table.AbstractTableModel;

import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;



public class CtrlPnlElectrode extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	ComboBoxModel<ElectrodeType> electrodeTypeModel;
	ElectrodeTblModel electrodeTblModel;
	ElectrodeTypeListener electrodeTypeListener;
	
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
	
}
