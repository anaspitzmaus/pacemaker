package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ICD_Type;


public class CtrlDlgChangePMType extends CtrlDlgChangeType {
	
	private AggregateType aggType;
	private AbstractTableModel tblModel;
	MRIListener mriListener;	
	CreateListener createListener;
	RAListener raListener;
	RVListener rvListener;
	LVListener lvListener;

	public CtrlDlgChangePMType(AggregateType aggType, AbstractTableModel tblModel) {
		super(aggType);
		this.aggType = aggType;
		this.tblModel = tblModel;
		setDialog(new DlgChangePmType());
		((DlgChangePmType) getDialog()).setCheckMRIValue(aggType.getMri());
		((DlgChangePmType) getDialog()).setCheckRAValue(aggType.getRa());
		((DlgChangePmType) getDialog()).setCheckRVValue(aggType.getRv());
		((DlgChangePmType) getDialog()).setCheckLVValue(aggType.getLv());
				getDialog().setNotation(aggType.getNotation());
		getDialog().setNotice(aggType.getNotice());
		getDialog().setPrice(aggType.getPrice());
		setComponentText();
		setListener();
		if(aggType instanceof ICD_Type) {
			getDialog().setTitle("ICD-Model bearbeiten");
		}
	}
	
	protected void setComponentText() {
		super.setComponentText();		
		((DlgChangePmType) getDialog()).setLblMRIText("MRT-fähig:");
		((DlgChangePmType) getDialog()).setCheckMRIText("MRT");	
		((DlgChangePmType) getDialog()).setLblPmKindText("Modus:");
		((DlgChangePmType) getDialog()).setCheckRAText("RA");
		((DlgChangePmType) getDialog()).setCheckRVText("RV");
		((DlgChangePmType) getDialog()).setCheckLVText("LV");
	}
	
	protected void setListener() {
		super.setListener();
		mriListener = new MRIListener(this.aggType.getMri());
		((DlgChangePmType) getDialog()).addMRIListener(mriListener);
		raListener = new RAListener(aggType.getRa());
		((DlgChangePmType) getDialog()).addRAListener(raListener);
		rvListener = new RVListener(aggType.getRv());
		((DlgChangePmType) getDialog()).addRVListener(rvListener);
		lvListener = new LVListener(aggType.getLv());
		((DlgChangePmType) getDialog()).addLVListener(lvListener);
		createListener = new CreateListener();
		((DlgChangePmType) getDialog()).addCreateListener(createListener);
		
	}
	
	class MRIListener implements ActionListener{
		Boolean mri = false;
		
		protected Boolean getMRI() {
			return this.mri;
		}	

		public MRIListener(Boolean mri) {
			this.mri = mri;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        mri = selected;	
	        
		}			
	}
	
	class RAListener implements ActionListener{
		Boolean ra;
		protected Boolean getValue() {
			return ra;
		}
		
		public RAListener(Boolean ra) {
			this.ra = ra;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			ra = selected;
			
		}
		
	}
	
	class RVListener implements ActionListener{
		Boolean rv;
		
		public RVListener(Boolean rv) {
			this.rv = rv;
		}
		
		protected Boolean getValue() {
			return rv;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			rv = selected;
			
		}
		
	}
	
	class LVListener implements ActionListener{
		Boolean lv;
		
		public LVListener(Boolean lv) {
			this.lv = lv;
		}
		
		protected Boolean getValue() {
			return lv;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			lv = selected;
			
		}
		
	}
	
	class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!notationListener.getNotation().equals("")) {
				aggType.setNotation(notationListener.getNotation());
				aggType.setNotice(noticeListener.getNotation());
				aggType.setMri(mriListener.getMRI());
				aggType.setPrice(priceListener.getPrice());
				aggType.setRa(raListener.getValue());
				aggType.setRv(rvListener.getValue());
				aggType.setLv(lvListener.getValue());
				updateDBAndTblModel();
			}		
		}
		
		
		protected void updateDBAndTblModel() {
			try {
				SQL_UPDATE.AggregateType(aggType);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + CtrlDlgChangePMType.class.getSimpleName() + "\n\nupdateDBAndTblModel", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			
			tblModel.fireTableDataChanged();
			((DlgChangePmType) getDialog()).dispose();
		}
		
	}


}
