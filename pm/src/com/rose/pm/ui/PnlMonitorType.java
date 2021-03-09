package com.rose.pm.ui;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import net.miginfocom.swing.MigLayout;

public class PnlMonitorType extends PnlAggregateTypeBase {

	private static final long serialVersionUID = -174462447511482833L;
	
	public PnlMonitorType(){
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][][][]", "[]"));
		
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		pnlInput.add(txtNotation, "cell 1 0");	
		
		JSeparator jSep = new JSeparator(SwingConstants.VERTICAL);
		jSep.setFont(font);
		jSep.setForeground(Color.BLACK);
		pnlInput.add(jSep, "cell 2 0");
		
		pnlInput.add(lblManufacturer, "cell 3 0,alignx trailing");
		
		pnlInput.add(cbxManufacturer, "cell 4 0");
		
		pnlInput.add(lblNotice, "cell 5 0");
		
		pnlInput.add(txtNotice, "cell 6 0, growx");	
		
		pnlInput.add(lblPrice, "cell 7 0");
		
		pnlInput.add(ftxtPrice, "cell 8 0");		
		
		lblPrice.setLabelFor(ftxtPrice);
		
		pnlInput.add(btnCreate, "cell 9 0");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
		
		
	}

	public void setManufacturerCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(2).setCellEditor(editor);		
	}

	protected void setFirstRowHeight(int height) {
		table.setRowHeight(0, height);
		
	}
}
