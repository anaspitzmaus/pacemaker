package com.rose.pm.ui;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;

import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ElectrodeModel;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM;
import com.rose.pm.ui.Listener.NotationListener;

public class CtrlPnlPM extends CtrlPnlBase{

	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	NotationListener serialNrListener, noticeListener;
	AggregateTypeModel aggregateTypeModel;
	AggregateTypeRenderer aggregateTypeRenderer;
	AggregateTypeListener aggregateTypeListener;
	
	
	
	
	public CtrlPnlPM() {
		panel = new PnlPM();
		panel.setName("Schrittmacher");
		((PnlPM)panel).setLblAggregatTypeText("Schrittmachermodel:");
		((PnlPM)panel).setLblSerialNrText("Seriennummer:");
		((PnlPM)panel).setLblNoticeText("Bemerkung:");
		((PnlPM)panel).setBtnCreateText("Einfügen");
		((PnlPM)panel).setBtnDeleteText("Löschen");
		
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlPM)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
		setListener();
		setModel();
		setRenderer();
	}
	
	private void setListener() {
		listener = new Listener();
		serialNrListener = listener.new NotationListener();
		((PnlPM)panel).addSerialNrListener(serialNrListener);
		noticeListener = listener.new NotationListener();
		((PnlPM)panel).addNoticeListener(noticeListener);
		aggregateTypeListener = new AggregateTypeListener();
		((PnlPM)panel).addAggregateTypeListener(aggregateTypeListener);		
	}
	
	private void setModel() {
		aggregateTypeModel = new AggregateTypeModel();
		((PnlPM)panel).setAggregatTypeModel(aggregateTypeModel);
	}
	
	private void setRenderer() {
		aggregateTypeRenderer = new AggregateTypeRenderer();
		((PnlPM)panel).setAggregatTypeRenderer(aggregateTypeRenderer);
	}
	
	class AggregateTypeListener implements ItemListener{

		AggregateType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (AggregateType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}
				
		       }
			
		}
		
	}
	
	class AggregateTypeRenderer extends JLabel implements ListCellRenderer<AggregateType>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7934129282290942751L;
		
		public AggregateTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends AggregateType> list, AggregateType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof AggregateType) {
				setText(((AggregateType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	/**
	 * model for the comboBox that displays the manufacturers
	 * @author Ekkehard Rose
	 *
	 */
	class AggregateTypeModel implements ComboBoxModel<AggregateType>{

		
		int indexSel;
		ArrayList<AggregateType> aggTypes;
		
		public AggregateTypeModel() {	
			
			aggTypes = SQL_SELECT.pacemakerKinds();
			
		}
		
		@Override
		public AggregateType getElementAt(int i) {			
			return aggTypes.get(i);
		}
		
		@Override
		public int getSize() {
			
			return aggTypes.size();
		}
		@Override
		public Object getSelectedItem() {
			
			if (indexSel >= 0) {
				return aggTypes.get(indexSel);
			}else {
				return "";
			}
		}
		@Override
		public void setSelectedItem(Object anItem) {
			if(anItem instanceof AggregateType) {
				for(int i = 0; i<aggTypes.size(); i++) {
					if(anItem.equals(aggTypes.get(i))) {
						indexSel = i;
						break;
					}
				}
			}
			
		}
		
			
		
		
		public ArrayList<AggregateType> getAggregatTypes(){
			return this.aggTypes;
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
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && aggregateTypeModel.getSelectedItem() instanceof AggregateType) {
				PM pm = new PM((AggregateType) aggregateTypeModel.getSelectedItem());
				pm.setExpireDate(ctrlPnlSetDate.getDate());
				pm.setSerialNr(serialNrListener.getNotation());
				pm.setNotice(noticeListener.getNotation());
				SQL_INSERT.pacemaker(pm);	
					
//				tblElectrodesModel.setElectrodeModels(SQL_SELECT.electrodeModels());
//				tblElectrodesModel.fireTableDataChanged();
				
			}
		}
		
	}
}
