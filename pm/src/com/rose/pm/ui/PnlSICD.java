package com.rose.pm.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.rose.pm.material.AggregateType;
import com.rose.pm.material.SICDType;

import net.miginfocom.swing.MigLayout;

;

public class PnlSICD extends PnlBase {

	private static final long serialVersionUID = 3214318116528507442L;
	JLabel lblAggregateType;	
	JComboBox<SICDType> cbxSICDType;
	JButton btnShowAll;
	
	protected void setLblSerialNrText(String txt) {
		lblNotation.setText(txt);
	}
	
		
	protected void setLblAggregatTypeText(String txt) {
		lblAggregateType.setText(txt);
	}
	
		
	protected void setBtnShowAllText(String txt) {
		btnShowAll.setText(txt);
	}
	/**
	 * Create the panel.
	 */
	public PnlSICD() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[][]"));
		
		btnShowAll = new JButton("btnShowAll");
		btnShowAll.setFont(font);
		pnlInput.add(btnShowAll, "cell 1 0");
		
		lblAggregateType = new JLabel("lblPM_Type");
		lblAggregateType.setFont(font);
		pnlInput.add(lblAggregateType, "cell 0 1");
		
		cbxSICDType = new JComboBox<SICDType>();
		cbxSICDType.setFont(font);
		pnlInput.add(cbxSICDType, "cell 1 1, growx");
		
		pnlInput.add(lblNotation, "cell 2 1");		
		
		pnlInput.add(txtNotation, "cell 3 1");
		
		pnlInput.add(lblNotice, "cell 5 1");
		
		pnlInput.add(txtNotice, "cell 6 1, growx");
		
		pnlInput.add(btnCreate, "cell 7 1");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}

}
