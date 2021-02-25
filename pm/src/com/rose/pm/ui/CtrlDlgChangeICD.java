package com.rose.pm.ui;

import javax.swing.table.AbstractTableModel;


import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ICD;
import com.rose.pm.material.ICD_Type;
import com.rose.pm.ui.CtrlPnlPM.AggregateTblModel;

public class CtrlDlgChangeICD extends CtrlDlgChangePM{

	public CtrlDlgChangeICD(ICD icd, AbstractTableModel model) {
		super(icd, model);
		
	}
	
	@Override
	protected void setCreateListener() {
		createListener = new CreateListener();
		dlgChange.addCreateListener(createListener);
	}
	
	class CreateListener extends CtrlDlgChangePM.CreateListener{			
				
		@Override
		protected void updateDBAndTblModel() {
			SQL_UPDATE.ICD((ICD) pm);				
			((AggregateTblModel)model).setAggregats(SQL_SELECT.icd((ICD_Type) pm.getMaterialType()));			
		}
		
	
	}

}
