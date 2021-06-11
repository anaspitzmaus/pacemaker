package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.ER;


public class CtrlDlgChangeER extends CtrlDlgChange {
	ER er;
	AbstractTableModel model;
	CreateListener createListener;
	public CtrlDlgChangeER(ER er, AbstractTableModel model) {
		this.er = er;
		this.model = model;
		
		dlgChange.setSerialNrText(this.er.getSerialNr());
		dlgChange.setNoticeText(this.er.getNotice());
		ctrlPnlSetDate.setDate(er.getExpireDate());
		
		priceFormat = DecimalFormat.getInstance();
		priceFormat.setMinimumFractionDigits(2);
		priceFormat.setMaximumFractionDigits(2);
		DefaultFormatterFactory dff = new DefaultFormatterFactory(new NumberFormatter(priceFormat), new NumberFormatter(priceFormat), new NumberFormatter(NumberFormat.getNumberInstance()));
		dlgChange.setPriceFormatter(dff);
		dlgChange.setPriceValue(this.er.getPrice());
		setCreateListener();
	}
	
	protected void initiateDialog() {
		dlgChange = new DlgChangeER();
		
	}
	
	private void setCreateListener() {
		createListener = new CreateListener();
		dlgChange.addCreateListener(createListener);
	}
	
	class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "") {
				er.setExpireDate(ctrlPnlSetDate.getDate());
				er.setSerialNr(serialNrListener.getNotation());
				er.setNotice(noticeListener.getNotation());
				er.setPrice(priceListener.getPrice());

				updateDBAndTblModel();				
			}
		}
		
		
		protected void updateDBAndTblModel() {
			try {
				SQL_UPDATE.Eventrecorder(er);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + CreateListener.class.getSimpleName() + "\n\nBoolean updateDBAndTblModel()", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
						
			model.fireTableDataChanged();
			dlgChange.dispose();
		}
		
	}
}
