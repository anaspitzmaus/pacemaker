package com.rose.pm.ui;

import java.util.ArrayList;


import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;




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
	
	
	class ICDTypeTblModel extends PMTypeTblModel{
		
		private static final long serialVersionUID = -718398649807963087L;

		public ICDTypeTblModel(ArrayList<? extends AggregateType> aggregates) {
			super(aggregates);			
		}		
	}	
}
