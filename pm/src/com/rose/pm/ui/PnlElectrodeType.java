package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Manufacturer;

import net.miginfocom.swing.MigLayout;

public class PnlElectrodeType extends PnlBase {

	JTextField txtNotation;
	JTextField txtNotice;
	JFormattedTextField ftxtPrice;
	JSpinner spinLength;
	JComboBox<Manufacturer> cbxManufacturer;
	JLabel lblNotation, lblManufacturer, lblMRI, lblLength, lblFix, lblNotice, lblPrice;
	JCheckBox checkMRI;
	JToggleButton tglFix;
	JButton btnCreate, btnDelete;
	Font baseFont;
	 private NumberFormat paymentFormat;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 113685124454489212L;

	/**
	 * Create the panel.
	 */
	public PnlElectrodeType() {
		baseFont = new Font("Tahoma", Font.PLAIN, 14);
		paymentFormat = NumberFormat.getCurrencyInstance();
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][][][][][grow][][][]", "[]"));
		
		lblNotation = new JLabel("lblNotation");
		lblNotation.setFont(baseFont);
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		txtNotation = new JTextField();
		txtNotation.setFont(baseFont);
		pnlInput.add(txtNotation, "cell 1 0");
		txtNotation.setColumns(10);
		
		JSeparator jSep = new JSeparator(SwingConstants.VERTICAL);
		jSep.setFont(baseFont);
		jSep.setForeground(Color.BLACK);
		pnlInput.add(jSep, "cell 2 0");
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(baseFont);
		pnlInput.add(lblManufacturer, "cell 3 0,alignx trailing");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(baseFont);
		pnlInput.add(cbxManufacturer, "cell 4 0");
		
		lblMRI = new JLabel("lblMRI");
		lblMRI.setFont(baseFont);
		pnlInput.add(lblMRI,  "cell 5 0");
		
		checkMRI = new JCheckBox();
		checkMRI.setFont(baseFont);
		pnlInput.add(checkMRI, "cell 6 0");
		
		lblLength = new JLabel("lblLength");
		lblLength.setFont(baseFont);
		pnlInput.add(lblLength,  "cell 7 0");
		
		spinLength = new JSpinner();
		spinLength.setFont(baseFont);
		spinLength.setModel(new SpinnerNumberModel(50, 40, 60, 1));
		pnlInput.add(spinLength, "cell 8 0");
		
		lblFix = new JLabel("lblFix");
		lblFix.setFont(baseFont);
		pnlInput.add(lblFix, "cell 9 0");
		
		tglFix = new JToggleButton("tglFix");
		tglFix.setFont(baseFont);
		pnlInput.add(tglFix, "cell 10 0");		
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(baseFont);
		pnlInput.add(lblNotice, "cell 11 0");
		
		txtNotice = new JTextField();
		txtNotice.setFont(baseFont);
		pnlInput.add(txtNotice, "cell 12 0, growx");
		txtNotice.setColumns(10);
		
		lblPrice = new JLabel("lblPrice");
		lblPrice.setFont(baseFont);
		pnlInput.add(lblPrice, "cell 13 0");
		
		ftxtPrice = new JFormattedTextField(paymentFormat);
		ftxtPrice.setFont(baseFont);
		pnlInput.add(ftxtPrice, "cell 14 0");
		ftxtPrice.setColumns(10);
		
		lblPrice.setLabelFor(ftxtPrice);
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(baseFont);
		pnlInput.add(btnCreate, "cell 15 0");
		
		btnDelete = new JButton("BtnDelete");
		btnDelete.setFont(baseFont);
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
		
	}
	
	protected void setLblNotationText(String txt) {
		lblNotation.setText(txt);
	}
	
	protected void setLblManufacturerText(String txt) {
		lblManufacturer.setText(txt);
	}
	
	protected void setLblMRIText(String txt) {
		lblMRI.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setBtnCreateText(String txt) {
		btnCreate.setText(txt);
	}
	
	protected void setBtnDeleteText(String txt) {
		btnDelete.setText(txt);
	}
	
	protected void setLblFixText(String txt) {
		lblFix.setText(txt);
	}
	
	protected void setLblLengthText(String txt) {
		lblLength.setText(txt);
	}
	
	protected void setTglFixText(String txt) {
		tglFix.setText(txt);
	}
	
	protected void setLblPriceText(String txt) {
		lblPrice.setText(txt);
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

	protected void addfixModeListener(ActionListener fixModeListener) {
		tglFix.addActionListener(fixModeListener);
		
	}

	protected void addNotationListener(DocumentListener notationListener) {
		txtNotation.getDocument().addDocumentListener(notationListener);
		
	}

	protected void addNoticeListener(DocumentListener noticeListener) {
		txtNotice.getDocument().addDocumentListener(noticeListener);
		
	}

	protected void addLengthListener(ChangeListener lengthListener) {
		spinLength.addChangeListener(lengthListener);
		
	}
	
	protected Integer getLength() {
		return (Integer) spinLength.getModel().getValue();
	}

	protected void setTblElectrodesModel(AbstractTableModel tblElectrodesModel) {
		table.setModel(tblElectrodesModel);
		
	}
	
	protected void setTableEMTypeRenderer(Class<ElectrodeType> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
		
	}
	
	protected void setTableStringRenderer(Class<String> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
	}

	protected void setTableIDRenderer(Class<ElectrodeType> idClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(idClass, renderer);
	}
	
	protected void setTableBooleanRenderer(Class<Boolean> bolClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(bolClass, renderer);
	}

	protected void addCreateListener(ActionListener createListener) {
		btnCreate.addActionListener(createListener);
		
	}

	protected void addMRIListener(ActionListener mriListener) {
		checkMRI.addActionListener(mriListener);
		
	}

	protected void setTblLengthRenderer(Class<Integer> intClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(intClass, renderer);
		
	}
	
	protected void setTblSelectionMode(int selection) {
		table.setSelectionMode(selection);
	}

	protected int getSelectedTblRow() {
		return table.getSelectedRow();
	}

	protected ElectrodeType getTableValueAt(int row, int column) {
		return (ElectrodeType) table.getValueAt(row, column);
	}

	protected void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);
		
	}

	protected void addDeleteListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
		
	}

}
