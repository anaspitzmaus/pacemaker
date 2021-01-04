package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.material.AggregatModel;
import com.rose.pm.material.ElectrodeModel;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM_Kind;
import com.rose.pm.ui.CtrlPnlElectrodeType.CreateListener;
import com.rose.pm.ui.CtrlPnlElectrodeType.MRIListener;
import com.rose.pm.ui.CtrlPnlElectrodeType.TblElectrodesModel;
import com.rose.pm.ui.CtrlPnlElectrodeType.TblIntegerRenderer;

import net.miginfocom.swing.MigLayout;

public class PnlElectrodeType extends PnlBase {

	JTextField txtNotation;
	JTextField txtNotice;
	JSpinner spinLength;
	JComboBox<Manufacturer> cbxManufacturer;
	JLabel lblNotation, lblManufacturer, lblMRI, lblLength, lblFix, lblNotice;
	JCheckBox checkMRI;
	JToggleButton tglFix;
	JButton btnCreate, btnDelete;
	/**
	 * 
	 */
	private static final long serialVersionUID = 113685124454489212L;

	/**
	 * Create the panel.
	 */
	public PnlElectrodeType() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][][][][][grow][]", "[]"));
		
		lblNotation = new JLabel("lblNotation");
		lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		txtNotation = new JTextField();
		txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(txtNotation, "cell 1 0");
		txtNotation.setColumns(10);
		
		JSeparator jSep = new JSeparator(SwingConstants.VERTICAL);
		jSep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jSep.setForeground(Color.BLACK);
		pnlInput.add(jSep, "cell 2 0");
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblManufacturer, "cell 3 0,alignx trailing");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(cbxManufacturer, "cell 4 0");
		
		lblMRI = new JLabel("lblMRI");
		lblMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblMRI,  "cell 5 0");
		
		checkMRI = new JCheckBox();
		checkMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(checkMRI, "cell 6 0");
		
		lblLength = new JLabel("lblLength");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblLength,  "cell 7 0");
		
		spinLength = new JSpinner();
		spinLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinLength.setModel(new SpinnerNumberModel(50, 40, 60, 1));
		pnlInput.add(spinLength, "cell 8 0");
		
		lblFix = new JLabel("lblFix");
		lblFix.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblFix, "cell 9 0");
		
		tglFix = new JToggleButton("tglFix");
		tglFix.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(tglFix, "cell 10 0");		
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblNotice, "cell 11 0");
		
		txtNotice = new JTextField();
		txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(txtNotice, "cell 12 0, growx");
		txtNotice.setColumns(10);
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(btnCreate, "cell 13 0");
		
		btnDelete = new JButton("BtnDelete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
	
	protected void setBtnDeleteTest(String txt) {
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
	
	protected void setTableEMTypeRenderer(Class<ElectrodeModel> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
		
	}
	
	protected void setTableStringRenderer(Class<String> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
	}

	protected void setTableIDRenderer(Class<ElectrodeModel> idClass, TableCellRenderer renderer) {
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

}
