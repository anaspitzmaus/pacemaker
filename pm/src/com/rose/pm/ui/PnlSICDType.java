package com.rose.pm.ui;


import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import com.rose.pm.material.Manufacturer;
import net.miginfocom.swing.MigLayout;


public class PnlSICDType extends PnlBase {

	JLabel lblManufacturer;
	JComboBox<Manufacturer> cbxManufacturer;	
	
	
	private static final long serialVersionUID = 5280759806419439713L;

	/**
	 * Create the panel.
	 */
	public PnlSICDType() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][grow][]", "[][][][]"));
		
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");		
		pnlInput.add(txtNotation, "cell 1 0");
		
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(font);
		pnlInput.add(lblManufacturer, "cell 2 0,alignx trailing");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(font);
		pnlInput.add(cbxManufacturer, "cell 3 0");	
		
		
		pnlInput.add(lblNotice, "cell 4 0");		
		pnlInput.add(txtNotice, "cell 5 0, growx");
		
		pnlInput.add(btnCreate, "cell 6 0");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
			
	}
	
	protected void setTblModel(AbstractTableModel model) {
		table.setModel(model);
	}
	
	protected void setTblSelectionMode(int singleSelection) {
		table.setSelectionMode(singleSelection);
	}
	
	protected void setManufacturerModel(ComboBoxModel<Manufacturer> l) {
		cbxManufacturer.setModel(l);
	}

	protected void setManufacturerRenderer(ListCellRenderer<Manufacturer> manufacturerRenderer) {
		cbxManufacturer.setRenderer(manufacturerRenderer);		
	}
	
	protected void addManufacturerListener(ItemListener l) {
		cbxManufacturer.addItemListener(l);		
	}
	
	protected void setManufacturerIndex(Integer index) {
		cbxManufacturer.setSelectedIndex(index);
	}
	
	protected void emptyTextFields() {
		txtNotation.setText("");
		txtNotice.setText("");		
	}
	
	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	public void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}
}
