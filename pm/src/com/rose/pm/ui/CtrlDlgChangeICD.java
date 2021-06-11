package com.rose.pm.ui;

import javax.swing.table.AbstractTableModel;


import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ICD;


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
			model.fireTableDataChanged();
			dlgChange.dispose();	
		}
		
	
	}

}
