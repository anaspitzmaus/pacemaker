package com.rose.pm.ui;

import com.rose.pm.material.MaterialType;

public abstract class CtrlDlgChangeType {
	DlgChangeType dlgChangeType;
	MaterialType materialType;
	Listener listener;
	Listener.NotationListener notationListener;
	Listener.NotationListener noticeListener;
	Listener.PriceListener priceListener;
	
	protected void setDialog(DlgChangeType dialog) {
		dlgChangeType = dialog;
	}
	
	protected DlgChangeType getDialog() {
		return dlgChangeType;
	}
	
	public CtrlDlgChangeType(MaterialType materialType) {
		this.materialType = materialType;		
	}
	
	protected void setListener() {
		listener = new Listener();
		notationListener = listener.new NotationListener(this.materialType.getNotation());
		dlgChangeType.addNotationListener(notationListener);
		noticeListener = listener.new NotationListener(this.materialType.getNotice());
		dlgChangeType.addNoticeListener(noticeListener);
		priceListener = listener.new PriceListener(this.materialType.getPrice());
		dlgChangeType.addPriceListener(priceListener);
	}
	
	protected void setComponentText() {
		dlgChangeType.setLblNotationText("Bezeichnung:");
		dlgChangeType.setLblNoticeText("Anmerkung:");
		dlgChangeType.setLblPriceText("Preis:");
	}
	
}
