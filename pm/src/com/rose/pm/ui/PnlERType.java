package com.rose.pm.ui;


import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.rose.pm.material.ERType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.ui.CtrlPnlERType.TblERIDRenderer;
import net.miginfocom.swing.MigLayout;

public class PnlERType extends PnlBase {

	
	private static final long serialVersionUID = 8958719570479767135L;

	
	JFormattedTextField ftxtPrice;
	JComboBox<Manufacturer> cbxManufacturer;	
	JLabel lblManufacturer;
	JLabel lblPrice;	
	NumberFormat paymentFormat;
	/**
	 * Create the panel.
	 */
	public PnlERType() {
		
		paymentFormat = DecimalFormat.getInstance();
		paymentFormat.setMinimumFractionDigits(2);
		paymentFormat.setMaximumFractionDigits(2);
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][grow][]", "[][][][]"));
		
		
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
		
		
		lblPrice = new JLabel("lblPrice");
		lblPrice.setFont(font);
		pnlInput.add(lblPrice, "cell 6 0");
		
		ftxtPrice = new JFormattedTextField(paymentFormat);
		ftxtPrice.setFont(font);
		pnlInput.add(ftxtPrice, "cell 7 0");
		ftxtPrice.setColumns(10);
		
		lblPrice.setLabelFor(ftxtPrice);		
		
		pnlInput.add(btnCreate, "cell 8 0");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}
	
	
	protected void setLblManufacturerText(String txt) {
		lblManufacturer.setText(txt);
	}
		
	protected void setLblPriceText(String txt) {
		lblPrice.setText(txt);
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
	
	protected void setTableStringRenderer(Class<String> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
	}

	protected void setTableIDRenderer(Class<ERType> idClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(idClass, renderer);
	}
	
	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	protected ERType getTableValueAt(int row, int column) {
		return (ERType) table.getValueAt(row, column);
	}

	public void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}

	protected void addPriceChangeListener(PropertyChangeListener priceListener) {
		ftxtPrice.addPropertyChangeListener(priceListener);		
	}

	protected void setTblDoubleRenderer(Class<Double> doubleClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(doubleClass, renderer);		
	}

	protected void setTblERIDRenderer(Class<ERType> erTypeClass, TblERIDRenderer renderer) {
		table.setDefaultRenderer(erTypeClass, renderer);		
	}

}
