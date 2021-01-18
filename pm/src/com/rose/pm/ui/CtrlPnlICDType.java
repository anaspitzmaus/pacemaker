package com.rose.pm.ui;

import java.util.ArrayList;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ICD_Type;



public class CtrlPnlICDType extends CtrlPnlPMType{
	
	public CtrlPnlICDType() {
		super();	
	}
	
	@Override
	protected void createPanel() {
		panel = new PnlICDType();
		panel.setName("ICD-Model");
	}
	
	@Override
	protected void setModel() {
		tblModel = new ICDTypeTblModel(SQL_SELECT.ICD_Kinds());
		((PnlICDType)panel).setTblModel(tblModel);
	}
	
//	@Override
//	protected void setListener() {
//		createListener = new ICDTypeCreateListener();
//		((PnlPMType)panel).addCreateListener(createListener);
//	}
	
	class ICDTypeTblModel extends PMTypeTblModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8444808544442905721L;	
		
		
		
		public ICDTypeTblModel(ArrayList<? extends AggregateType> paceMakers) {
			super(paceMakers);
			
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
	}
	

	class ICDTypeCreateListener extends CreateListener{	
		
		@Override
		protected void initiate() {
			aggModel = new ICD_Type(notationListener.getNotation());
		}
	
		@Override
		protected void updateDBAndModel(AggregateType aggModel) {
			
			SQL_INSERT.icd_Model((ICD_Type) aggModel);
			tblModel.setAggregats(SQL_SELECT.ICD_Kinds());
		}
		
		
	}
	
	
}
