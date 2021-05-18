package com.rose.administration.ui;

import javax.swing.DefaultListModel;

import com.rose.administration.Insurance;

public class CtrlPnlWest {
	PnlWest pnlWest;
	InsuranceListModel insuranceListModel;
	
	protected PnlWest getPanel() {
		return pnlWest;
	}

	public CtrlPnlWest() {
		pnlWest = new PnlWest();
		pnlWest.setLblInsuranceText("Kostenträger");
		pnlWest.setLblAggregateText("Aggregat");
		setModels();
	}
	
	private void setModels() {
		insuranceListModel = new InsuranceListModel();
		pnlWest.setInsuranceListModel(insuranceListModel);
	}
	
	class InsuranceListModel extends DefaultListModel<Insurance>{

		private static final long serialVersionUID = 2329604100260762897L;
		public InsuranceListModel() {
			//get insurances from database
			//add insurances to the listModel
		}
	}
	
}
