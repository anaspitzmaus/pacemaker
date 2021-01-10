package com.rose.pm.ui;


import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ICD;
import com.rose.pm.material.ICD_Type;
import com.rose.pm.material.PM;





public class CtrlPnlICD extends CtrlPnlPM {
	
	
	public CtrlPnlICD() {
		
	}
	
	@Override
	protected void createPanel() {
		panel = new PnlICD();
		panel.setName("ICD");		
	}
	
	
	
	@Override
	protected void setComponentLabeling() {
		((PnlICD)panel).setLblAggregatTypeText("ICD-Model:");
		((PnlICD)panel).setLblSerialNrText("Seriennummer:");
		((PnlICD)panel).setLblNoticeText("Bemerkung:");
		((PnlICD)panel).setBtnCreateText("Einfügen");
		((PnlICD)panel).setBtnDeleteText("Löschen");
		((PnlICD)panel).setBtnShowAllText("Alle Modelle");
	}
	
	@Override
	protected void setModel() {
		ArrayList<? extends AggregateType> aggregateTypes = SQL_SELECT.ICD_Kinds();
		AggregateType[] arr = new AggregateType[aggregateTypes.size()]; 		  
        // ArrayList to Array Conversion 
        for (int i = 0; i < aggregateTypes.size(); i++) 
            arr[i] = aggregateTypes.get(i);		
		
		aggregateTypeModel = new DefaultComboBoxModel<AggregateType>(arr);
		
		((PnlICD)panel).setAggregatTypeModel(aggregateTypeModel);
		aggregateTblModel = new AggregateTblModel(SQL_SELECT.icd((ICD_Type) aggregateTypeListener.model));
		((PnlICD)panel).setAggregateTblModel(aggregateTblModel);
	}
	
	@Override
	protected void setListener() {
		createListener = new CreateListener();
		((PnlICD)panel).addCreateListener(createListener);
		aggregateTypeListener = new AggregateTypeListener();
		((PnlICD)panel).addAggregateTypeListener(aggregateTypeListener);
		showAllListener = new ShowAllListener();
		((PnlICD)panel).addShowAllListener(showAllListener);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlICD)panel).addTblMouseAdaptor(tblMouseAdaptor);
		
	}
	
	class AggregateTypeListener extends CtrlPnlPM.AggregateTypeListener{
		
		@Override
		protected void updateTblModel() {
			aggregateTblModel.setAggregats(SQL_SELECT.icd((ICD_Type) model));			
			aggregateTblModel.fireTableDataChanged();
		}		
	}
	
	class ShowAllListener extends CtrlPnlPM.ShowAllListener{

				
		@Override
		protected void update() {
			aggregateTblModel.setAggregats(SQL_SELECT.icd(null));			
			aggregateTblModel.fireTableDataChanged();
		}
		
	}
	
	
	class CreateListener extends CtrlPnlPM.CreateListener{
		
		@Override
		protected void initiateAggregate() {
			pm = new ICD((ICD_Type) aggregateTypeModel.getSelectedItem());
		}
		
		@Override
		protected void updateDBAndTblModel() {
			SQL_INSERT.icd((ICD)pm);				
			aggregateTblModel.setAggregats(SQL_SELECT.icd((ICD_Type) aggregateTypeModel.getSelectedItem()));
			aggregateTblModel.fireTableDataChanged();
		}	
	}
	
	class TblMouseAdaptor extends CtrlPnlPM.TblMouseAdaptor{
		
		@Override
		 protected void initiateDialog() {
			 CtrlDlgChangeICD ctrlDlgChangeICD = new CtrlDlgChangeICD((ICD) aggregateTblModel.getValueAt(row, 0), aggregateTblModel);
             ctrlDlgChangeICD.getDialog().setVisible(true);
		 }
	}
	
}
