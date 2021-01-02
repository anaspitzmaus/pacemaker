package com.rose.pm.ui;

import java.util.ArrayList;



import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregatModel;
import com.rose.pm.material.PM_Type;


public class CtrlPnlICDType extends CtrlPnlPMType{
	
	public CtrlPnlICDType() {
		super();	
	}
	
	@Override
	protected void createPanel() {
		panel = new PnlICDType();
		panel.setName("ICD-Typ");
	}
	
	@Override
	protected void setModel() {
		tblModel = new ICDTypeTblModel(SQL_SELECT.ICD_Kinds());
		((PnlICDType)panel).setTblModel(tblModel);
	}
	
	class ICDTypeTblModel extends PMTypeTblModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;	
		
		
		
		public ICDTypeTblModel(ArrayList<? extends AggregatModel> paceMakers) {
			super(paceMakers);
			columnNames.add("ATP");
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
				
				default: return null;
				
				}	
			
		}
		
		
		
//		protected void setAggregats(ArrayList<? extends AggregatModel> pm) {
//			this.aggregates = pm;
//			if(icd) {
//				if(!columnNames.contains("ATP")) {
//					columnNames.add("ATP");//add columns for ICD-Model
//				}				
//			}else {//if pacemaker
//				columnNames.remove("ATP");
//			}
			
//		}
		
	}
	
}
