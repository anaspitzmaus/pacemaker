package com.rose.pm.ui;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.SICDType;


public class CtrlSICD extends CtrlICD{
	CtrlPnlSICDType ctrlPnlSICDType;
	CtrlPnlSICD ctrlPnlSICD;
	CreateSICDTypeListener createTypeListener;
	DeleteTypeListener deleteTypeListener;
	
	
//	protected CtrlPnlSICDType getCtrlPnlAggregatesType() {
//		return this.ctrlPnlSICDType;
//	}
//	
//	protected CtrlPnlSICD getCtrlPnlSICD() {
//		return this.ctrlPnlSICD;
//	}
	
	public CtrlSICD() {
		initialzeControls();
		setListener();
	}
	
	protected void initialzeControls() {
		ctrlPnlPMType = new CtrlPnlSICDType();
		ctrlPnlPM = new CtrlPnlSICD();
	}
	
	protected void setListener() {
		createTypeListener = new CreateSICDTypeListener();
		ctrlPnlPMType.addCreateListener(createTypeListener);
		ctrlPnlPMType.addDeleteListener(deleteTypeListener);
	}
	
class CreateSICDTypeListener extends CreateTypeListener{
		
		@Override
		protected void initializeAggregate() {
			model = new SICDType(ctrlPnlSICDType.getNotationListener().getNotation());
		}

		@Override
		protected void actualizeDBAndModels() {
			if(model instanceof SICDType) {
				Integer id = null;
				try {
					id = SQL_INSERT.sicdType((SICDType) model);
				}catch (SQLIntegrityConstraintViolationException e) {
					JOptionPane.showMessageDialog(null, "Dieses SICD-Modell existiert bereits!", "Hinweis", JOptionPane.WARNING_MESSAGE);

				}catch(SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT sicdType(SICDType sicd_Model)", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				
				if(id != null) {
					model.setId(id);
					try {
						ctrlPnlSICDType.getAggregateTypeTableModel().setAggregats(SQL_SELECT.sicdTypes());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ctrlPnlSICDType.getAggregateTypeTableModel().fireTableDataChanged();
					ctrlPnlSICD.sicdTypeModel.addElement((SICDType) model);
				}
			}
		}
		
	}
	
}
