package com.rose.pm.ui;

import javax.swing.table.TableCellRenderer;

import com.rose.pm.material.AggregateType;
import com.rose.pm.material.SICDType;

import net.miginfocom.swing.MigLayout;


/**
 * panel for the SICDs
 * @author Ekki
 *
 */
public class PnlSICDType extends PnlAggregateTypeBase {		
	
	
	private static final long serialVersionUID = 5280759806419439713L;

	/**
	 * Create the panel.
	 */
	public PnlSICDType() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][grow][]", "[][][][]"));
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		pnlInput.add(txtNotation, "cell 1 0");
		pnlInput.add(lblManufacturer, "cell 2 0,alignx trailing");
		pnlInput.add(cbxManufacturer, "cell 3 0");
		pnlInput.add(lblNotice, "cell 4 0");		
		pnlInput.add(txtNotice, "cell 5 0, growx");
		pnlInput.add(lblPrice, "cell 6 0");
		pnlInput.add(ftxtPrice, "cell 7 0");
		pnlInput.add(btnCreate, "cell 8 0");	
			
	}
	
	protected void setTableSICDIDRenderer(Class<SICDType> idClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(idClass, renderer);
	}
	
	
}
