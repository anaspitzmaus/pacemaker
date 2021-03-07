package com.rose.pm.ui;

import java.awt.Color;
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
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Manufacturer;
import net.miginfocom.swing.MigLayout;

public class PnlElectrodeType extends PnlAggregateTypeBase {

	
	
	JSpinner spinLength;
	JLabel lblMRI, lblLength, lblFix;
	JCheckBox checkMRI;
	JToggleButton tglFix;
	
	private static final long serialVersionUID = 113685124454489212L;

	/**
	 * Create the panel.
	 */
	public PnlElectrodeType() {
		
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][][][][][grow][][][]", "[]"));
		
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		pnlInput.add(txtNotation, "cell 1 0");
				
		JSeparator jSep = new JSeparator(SwingConstants.VERTICAL);
		jSep.setFont(font);
		jSep.setForeground(Color.BLACK);
		pnlInput.add(jSep, "cell 2 0");
		
		pnlInput.add(lblManufacturer, "cell 3 0,alignx trailing");
		
		pnlInput.add(cbxManufacturer, "cell 4 0");
		
		lblMRI = new JLabel("lblMRI");
		lblMRI.setFont(font);
		pnlInput.add(lblMRI,  "cell 5 0");
		
		checkMRI = new JCheckBox();
		checkMRI.setFont(font);
		pnlInput.add(checkMRI, "cell 6 0");
		
		lblLength = new JLabel("lblLength");
		lblLength.setFont(font);
		pnlInput.add(lblLength,  "cell 7 0");
		
		spinLength = new JSpinner();
		spinLength.setFont(font);
		spinLength.setModel(new SpinnerNumberModel(50, 40, 60, 1));
		pnlInput.add(spinLength, "cell 8 0");
		
		lblFix = new JLabel("lblFix");
		lblFix.setFont(font);
		pnlInput.add(lblFix, "cell 9 0");
		
		tglFix = new JToggleButton("tglFix");
		tglFix.setFont(font);
		pnlInput.add(tglFix, "cell 10 0");		
		
		pnlInput.add(lblNotice, "cell 11 0");
		
		pnlInput.add(txtNotice, "cell 12 0, growx");		
		
		pnlInput.add(lblPrice, "cell 13 0");
		
		pnlInput.add(ftxtPrice, "cell 14 0");		
		
		lblPrice.setLabelFor(ftxtPrice);
		
		pnlInput.add(btnCreate, "cell 15 0");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
		
	}
	
	protected void setLblMRIText(String txt) {
		lblMRI.setText(txt);
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
	

	protected void addFixModeListener(ActionListener fixModeListener) {
		tglFix.addActionListener(fixModeListener);		
	}

	protected void addLengthListener(ChangeListener lengthListener) {
		spinLength.addChangeListener(lengthListener);		
	}
	
	protected Integer getLength() {
		return (Integer) spinLength.getModel().getValue();
	}
	
	protected void setTableEMTypeRenderer(Class<ElectrodeType> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);		
	}
	
	protected void setTableStringRenderer(Class<String> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
	}

	protected void setTableBooleanRenderer(Class<Boolean> bolClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(bolClass, renderer);
	}	

	protected void addMRIListener(ActionListener mriListener) {
		checkMRI.addActionListener(mriListener);		
	}

	protected void setTblLengthRenderer(Class<Integer> intClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(intClass, renderer);		
	}
	
	protected void setTblDoubleRenderer(Class<Double> dblClass, TableCellRenderer r) {
		table.setDefaultRenderer(dblClass, r);
	}
	
	protected void addPriceChangeListener(PropertyChangeListener priceListener) {
		ftxtPrice.addPropertyChangeListener(priceListener);		
	}

}
