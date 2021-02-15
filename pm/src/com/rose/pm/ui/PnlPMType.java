package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Kind;
import com.rose.pm.ui.Listener.PriceListener;
import com.rose.pm.ui.Renderer.TblDoubleRenderer;

import net.miginfocom.swing.MigLayout;

public class PnlPMType extends PnlBase{
	
	JComboBox<Manufacturer> cbxManufacturer;
	JComboBox<PM_Kind> cbxType;
	JCheckBox checkRA, checkRV, checkLV;
	JCheckBox checkMRI;
	JLabel lblManufacturer;
	JLabel lblMRI;
	NumberFormat paymentFormat;
	JFormattedTextField ftxtPrice;
	JLabel lblPrice;
	
	private static final long serialVersionUID = -2047308671221451668L;

	/**
	 * Create the panel.
	 */
	public PnlPMType() {
		paymentFormat = DecimalFormat.getInstance();
		paymentFormat.setMinimumFractionDigits(2);
		paymentFormat.setMaximumFractionDigits(2);
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][grow][]", "[][][][]"));
		
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		
		pnlInput.add(txtNotation, "cell 1 0");
		
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblManufacturer, "cell 2 0,alignx trailing");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(cbxManufacturer, "cell 3 0");
		
		lblMRI = new JLabel("lblMRI");
		lblMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblMRI,  "cell 4 0");
		
		checkMRI = new JCheckBox();
		pnlInput.add(checkMRI, "cell 5 0");
		
		cbxType = new JComboBox<PM_Kind>();
		cbxType.setFont(font);
		pnlInput.add(cbxType, "cell 6 0");
		
		
		checkRA = new JCheckBox("Rechter Vorhof");
		checkRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(checkRA, "cell 6 1");
		
		checkRV = new JCheckBox("Rechter Ventrikel");
		checkRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(checkRV, "cell 6 2");
		
		checkLV = new JCheckBox("Linker Ventrikel");
		checkLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(checkLV, "cell 6 3");		
		
		pnlInput.add(lblNotice, "cell 7 0");		
		
		pnlInput.add(txtNotice, "cell 8 0, growx");		
		
		lblPrice = new JLabel("lblPrice");
		lblPrice.setFont(font);
		pnlInput.add(lblPrice, "cell 9 0");
		
		ftxtPrice = new JFormattedTextField(paymentFormat);
		ftxtPrice.setFont(font);
		ftxtPrice.setColumns(10);
		pnlInput.add(ftxtPrice, "cell 10 0");		
		
		lblPrice.setLabelFor(ftxtPrice);
		
		pnlInput.add(btnCreate, "cell 11 0");
	
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);	
	}
	
	
	
	protected void setLblManufacturerText(String txt) {
		lblManufacturer.setText(txt);
	}
	
	protected void setLblMRIText(String txt) {
		lblMRI.setText(txt);
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

	
	protected void setTypeModel(ComboBoxModel<PM_Kind> model) {
		cbxType.setModel(model);		
	}
	
	protected void setRASelection(Boolean sel) {
		checkRA.setSelected(sel);
	}
	
	protected void setRVSelection(Boolean sel) {
		checkRV.setSelected(sel);
	}
	
	protected void setLVSelection(Boolean sel) {
		checkLV.setSelected(sel);
	}
	
	protected ComboBoxModel<PM_Kind> getTypeModel() {
		return cbxType.getModel();
	}
	
	protected void setTypeRenderer(ListCellRenderer<PM_Kind> r) {
		cbxType.setRenderer(r);
	}	
	
	protected void addTypeListener(ActionListener l) {
		cbxType.addActionListener(l);
	}
	
		
	protected void addMRIListener(ActionListener l) {
		checkMRI.addActionListener(l);
	}

	protected void addRVListener(ActionListener rvListener) {
		checkRV.addActionListener(rvListener);
	}
	
	protected void addLVListener(ActionListener lvListener) {
		checkLV.addActionListener(lvListener);
	}
	
	protected void addRALIstener(ActionListener raListener) {
		checkRA.addActionListener(raListener);
	}

	protected void emptyTextFields() {
		txtNotation.setText("");
		txtNotice.setText("");
		checkMRI.setSelected(false);
		checkRV.setSelected(false);
		checkLV.setSelected(false);
		checkRA.setSelected(false);		
	}
	
		
	protected void setTablePMTypeRenderer(Class<PM_Kind> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);		
	}
	
	protected void setTableStringRenderer(Class<String> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
	}

	protected void setTableIDRenderer(Class<AggregateType> idClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(idClass, renderer);
	}
	
	protected void setTableBooleanRenderer(Class<Boolean> bolClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(bolClass, renderer);
	}

	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	protected AggregateType getTableValueAt(int row, int column) {
		return (AggregateType) table.getValueAt(row, column);
	}

	protected void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}

	protected void addPriceChangeListener(PropertyChangeListener priceListener) {
		ftxtPrice.addPropertyChangeListener(priceListener);		
	}

	protected void setTblDoubleRenderer(Class<Double> doubleClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(doubleClass, renderer);		
	}

}
