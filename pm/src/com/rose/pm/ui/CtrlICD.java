package com.rose.pm.ui;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ICD_Type;

public class CtrlICD extends CtrlAggregates{

		
	@Override
	protected void initialzeControls() {
		ctrlPnlPMType = new CtrlPnlICDType();
		ctrlPnlPM = new CtrlPnlICD();
	}

	@Override
	protected void setListener() {
		createTypeListener = new CreateICDTypeListener();
		deleteTypeListener = new DeleteICDTypeListener();
		ctrlPnlPMType.addCreateListener(createTypeListener);
		ctrlPnlPMType.addDeleteListener(deleteTypeListener);
	}
	
	class CreateICDTypeListener extends CreateTypeListener{
		
		@Override
		protected void initializeAggregate() {
			model = new ICD_Type(ctrlPnlPMType.getNotationListener().getNotation());
		}

		@Override
		protected void actualizeDBAndModels() {
			if(model instanceof ICD_Type) {
				Integer id = SQL_INSERT.icd_Model((ICD_Type) model);	
				if(id != null) {
					model.setId(id);
					ctrlPnlPMType.getAggregateTypeTableModel().setAggregats(SQL_SELECT.ICD_Kinds());
					ctrlPnlPMType.getAggregateTypeTableModel().fireTableDataChanged();
					ctrlPnlPM.aggregateTypeModel.addElement(model);
				}
			}
		}
		
	}
	
	class DeleteICDTypeListener extends DeleteTypeListener{
		
	}
}
