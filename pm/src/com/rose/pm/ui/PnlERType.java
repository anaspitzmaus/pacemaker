package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


import com.rose.pm.material.ERType;
import com.rose.pm.material.Manufacturer;


import net.miginfocom.swing.MigLayout;

public class PnlERType extends PnlBase {

	
	private static final long serialVersionUID = 8958719570479767135L;

	JTextField txtNotation;
	JTextField txtNotice;
	JComboBox<Manufacturer> cbxManufacturer;
	JButton btnCreate, btnDelete;
	JLabel lblNotation;
	JLabel lblManufacturer;
	JLabel lblNotice;
	Font font;
	/**
	 * Create the panel.
	 */
	public PnlERType() {
		font = new Font("Tahoma", Font.PLAIN, 14);
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][grow][]", "[][][][]"));
		
		lblNotation = new JLabel("lblNotation");
		lblNotation.setFont(font);
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		txtNotation = new JTextField();
		txtNotation.setFont(font);
		pnlInput.add(txtNotation, "cell 1 0");
		txtNotation.setColumns(10);
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(font);
		pnlInput.add(lblManufacturer, "cell 2 0,alignx trailing");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(font);
		pnlInput.add(cbxManufacturer, "cell 3 0");
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(font);
		pnlInput.add(lblNotice, "cell 4 0");
		
		txtNotice = new JTextField();
		txtNotice.setFont(font);
		pnlInput.add(txtNotice, "cell 5 0, growx");
		txtNotice.setColumns(10);
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(font);
		pnlInput.add(btnCreate, "cell 6 0");
		
		btnDelete = new JButton("BtnDelete");
		btnDelete.setFont(font);
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}
	
	protected void setLblNotationText(String txt) {
		lblNotation.setText(txt);
	}
	
	protected void setLblManufacturerText(String txt) {
		lblManufacturer.setText(txt);
	}
		
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setBtnCreateText(String txt) {
		btnCreate.setText(txt);
	}
	
	protected void setBtnDeleteTest(String txt) {
		btnDelete.setText(txt);
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

	protected void addNotationListener(DocumentListener notationListener) {
		txtNotation.getDocument().addDocumentListener(notationListener);
		
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);
	}
	
	protected void emptyTextFields() {
		txtNotation.setText("");
		txtNotice.setText("");
	}
	
	protected void addCreateListener(ActionListener l) {
		btnCreate.addActionListener(l);
		
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

	protected void addDeleteListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
		
	}

}
