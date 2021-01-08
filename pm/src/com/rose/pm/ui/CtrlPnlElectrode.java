package com.rose.pm.ui;

import java.time.LocalDate;

import com.rose.pm.Ctrl_PnlSetDate;

public class CtrlPnlElectrode extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	
	public CtrlPnlElectrode() {
		createPanel();
		setComponentLabeling();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlElectrode)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
	}
	
	protected void createPanel() {
		panel = new PnlElectrode();
		panel.setName("Elektroden");
	}
	
	protected void setComponentLabeling() {
		((PnlElectrode)panel).setLblElectrodeTypeText("Elektrodenmodel:");
		((PnlElectrode)panel).setLblSerialNrText("Seriennummer:");
		((PnlElectrode)panel).setLblNoticeText("Bemerkung:");
		((PnlElectrode)panel).setBtnCreateText("Einfügen");
		((PnlElectrode)panel).setBtnDeleteText("Löschen");
		((PnlElectrode)panel).setBtnShowAllText("Alle Modelle");
	}

	
}
