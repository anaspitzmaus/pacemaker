package com.rose.pm.ui;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
				Integer id = null;
				try {
					id = SQL_INSERT.icd_Model((ICD_Type) model);
				}catch (SQLIntegrityConstraintViolationException e) {
					JOptionPane.showMessageDialog(null, "Dieses ICD-Modell existiert bereits!", "Hinweis", JOptionPane.WARNING_MESSAGE);

				}catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT icd_Model(ICD_Type icd_Model)", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				
				if(id != null) {
					model.setId(id);
					ctrlPnlPMType.getAggregateTypeTableModel().setAggregats(SQL_SELECT.ICD_Kinds());
					ctrlPnlPMType.getAggregateTypeTableModel().fireTableDataChanged();
					ctrlPnlPM.aggregateTypeModel.addElement(model);
				}
			}
		}
		
	}
	
	
}
