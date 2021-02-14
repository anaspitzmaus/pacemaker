package com.rose.pm.ui;

import java.util.ArrayList;

import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.ui.CtrlPnlPMType.PMTypeTblModel;


public class CtrlPnlSICDType extends CtrlPnlICDType{

	@Override
	protected void createPanel() {
		panel = new PnlSICDType();
		panel.setName("SICD-Model");
	}
	
	@Override
	protected void setModel() {
		tblModel = new SICDTypeTblModel(SQL_SELECT.SICD_Kinds());
		((PnlSICDType)panel).setTblModel(tblModel);
	}
	
	class SICDTypeTblModel extends ICDTypeTblModel{

		
		private static final long serialVersionUID = -8444808544442905721L;			
		
		
		public SICDTypeTblModel(ArrayList<? extends AggregateType> aggregates) {
			super(aggregates);			
		}	
		
		@Override
		protected void setColumns() {
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("MRI");			
			columnNames.add("Bemerkung");				
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			setType(aggregates.get(rowIndex));
			
			
				switch(columnIndex) {
				case 0: return aggregates.get(rowIndex);
				
				case 1: return aggregates.get(rowIndex).getNotation();
				
				case 2: return aggregates.get(rowIndex).getManufacturer().getNotation();				
				
				case 3: return aggregates.get(rowIndex).getMri();
				
				case 4: return aggregates.get(rowIndex).getNotice();	
				
				case 5: return aggregates.get(rowIndex).getPrice();
				
				default: return null;
				
				}	
			
		}		
	}
}
