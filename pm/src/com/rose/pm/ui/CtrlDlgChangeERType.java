package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ERType;




public class CtrlDlgChangeERType extends CtrlDlgChangeType{
	
	private ERType erType;
	private AbstractTableModel tblModel;
	CreateListener createListener;
	

	public CtrlDlgChangeERType(ERType erType, AbstractTableModel tblModel) {
		super(erType);
		this.erType = erType;
		this.tblModel = tblModel;
		setDialog(new DlgChangeERType());
				
		((DlgChangeERType) getDialog()).setNotation(erType.getNotation());
		((DlgChangeERType) getDialog()).setNotice(erType.getNotice());		
		((DlgChangeERType) getDialog()).setPrice(erType.getPrice());
		setComponentText();
		setListener();
	}
	
	protected void setListener() {
		super.setListener();
		createListener = new CreateListener();
		((DlgChangeERType) getDialog()).addCreateListener(createListener);
		
	}
	
class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!notationListener.getNotation().equals("")) {
				erType.setNotation(notationListener.getNotation());
				erType.setNotice(noticeListener.getNotation());
				erType.setPrice(priceListener.getPrice());
				updateDBAndTblModel();
			}		
		}
		
		
		protected void updateDBAndTblModel() {
			try {
				SQL_UPDATE.ERType(erType);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + CtrlDlgChangeERType.class.getSimpleName() + "\n\nupdateDBAndTblModel", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			
			tblModel.fireTableDataChanged();
			((DlgChangeERType) getDialog()).dispose();
		}
		
	}
	

}
