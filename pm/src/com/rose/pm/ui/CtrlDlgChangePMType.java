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


public class CtrlDlgChangePMType extends CtrlDlgChangeType {
	
	private AggregateType aggType;
	private AbstractTableModel tblModel;
	private DlgChangePmType dlgChangePMType;
	MRIListener mriListener;	
	CreateListener createListener;
	RAListener raListener;
	RVListener rvListener;
	LVListener lvListener;

	public CtrlDlgChangePMType(AggregateType aggType, AbstractTableModel tblModel) {
		super(aggType);
		this.aggType = aggType;
		this.tblModel = tblModel;
		dlgChangePMType = new DlgChangePmType();
		setDialog(dlgChangePMType);
		dlgChangePMType.setCheckMRIValue(aggType.getMri());
		dlgChangePMType.setCheckRAValue(aggType.getRa());
		dlgChangePMType.setCheckRVValue(aggType.getRv());
		dlgChangePMType.setCheckLVValue(aggType.getLv());
	}
	
	protected void setComponentText() {
		super.setComponentText();		
		dlgChangePMType.setLblMRIText("MRT-fähig:");
		dlgChangePMType.setCheckMRIText("MRT");	
		dlgChangePMType.setLblPmKindText("Modus:");
		dlgChangePMType.setCheckRAText("RA");
		dlgChangePMType.setCheckRVText("RV");
		dlgChangePMType.setCheckLVText("LV");
	}
	
	protected void setListener() {
		super.setListener();
		mriListener = new MRIListener(this.aggType.getMri());
		dlgChangePMType.addMRIListener(mriListener);
		raListener = new RAListener(aggType.getRa());
		dlgChangePMType.addRAListener(raListener);
		rvListener = new RVListener(aggType.getRv());
		dlgChangePMType.addRVListener(rvListener);
		lvListener = new LVListener(aggType.getLv());
		dlgChangePMType.addLVListener(lvListener);
		createListener = new CreateListener();
		dlgChangePMType.addCreateListener(createListener);
		
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
			dlgChangePMType.dispose();
		}
		
	}


}
