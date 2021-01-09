package com.rose.pm.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.time.LocalDate;

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

import com.rose.pm.Pnl_SetDate;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.PM;

import net.miginfocom.swing.MigLayout;

public class PnlElectrode extends PnlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 656734936297482035L;
	JLabel lblSerialNr, lblNotice, lblElectrodeType;
	JTextField txtSerialNr, txtNotice;
	JComboBox<ElectrodeType> cbxElectrodeType;
	JButton btnCreate, btnDelete, btnShowAll;
	
	protected void setLblSerialNrText(String txt) {
		lblSerialNr.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setLblElectrodeTypeText(String txt) {
		lblElectrodeType.setText(txt);
	}
	
	protected void setBtnCreateText(String txt) {
		btnCreate.setText(txt);
	}
	
	protected void setBtnDeleteText(String txt) {
		btnDelete.setText(txt);
	}
	
	protected void setBtnShowAllText(String txt) {
		btnShowAll.setText(txt);
	}
	/**
	 * Create the panel.
	 */
	public PnlElectrode() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[][]"));
		
		btnShowAll = new JButton("btnShowAll");
		btnShowAll.setFont(font);
		pnlInput.add(btnShowAll, "cell 1 0");
		
		lblElectrodeType = new JLabel("lblPM_Type");
		lblElectrodeType.setFont(font);
		pnlInput.add(lblElectrodeType, "cell 0 1");
		
		cbxElectrodeType = new JComboBox<ElectrodeType>();
		cbxElectrodeType.setFont(font);
		pnlInput.add(cbxElectrodeType, "cell 1 1, growx");
		
		lblSerialNr = new JLabel("lblNotation");
		lblSerialNr.setFont(font);
		pnlInput.add(lblSerialNr, "cell 2 1");
		
		txtSerialNr = new JTextField();
		txtSerialNr.setFont(font);
		pnlInput.add(txtSerialNr, "cell 3 1");
		txtSerialNr.setColumns(10);
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(font);
		pnlInput.add(lblNotice, "cell 5 1");
		
		txtNotice = new JTextField();
		txtNotice.setFont(font);
		txtNotice.setColumns(10);
		pnlInput.add(txtNotice, "cell 6 1, growx");
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(font);
		pnlInput.add(btnCreate, "cell 7 1");
		
		btnDelete = new JButton("btnDelete");
		btnDelete.setFont(font);
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);

	}
	
	protected void integratePnlDate(Pnl_SetDate pnlDate) {
		pnlInput.add(pnlDate, "cell 4 1");
	}

	protected void addSerialNrListener(DocumentListener listener) {
		txtSerialNr.getDocument().addDocumentListener(listener);		
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);
	}
	
	protected void addElectrodeTypeListener(ItemListener l) {
		cbxElectrodeType.addItemListener(l);
	}

	protected void setElectrodeTypeModel(ComboBoxModel<ElectrodeType> model) {
		cbxElectrodeType.setModel(model);
		
	}

	protected void setElectrodeTypeRenderer(ListCellRenderer<ElectrodeType> renderer) {
		cbxElectrodeType.setRenderer(renderer);
		
	}
	
	protected void addCreateListener(ActionListener l) {
		btnCreate.addActionListener(l);
	}
	
	protected void addDeleteListener(ActionListener l) {
		btnDelete.addActionListener(l);
	}

	protected void setElectrodeTblModel(AbstractTableModel aggregateTblModel) {
		table.setModel(aggregateTblModel);
		
	}
	
	protected void setElectrodeTypeSelectionIndex(Integer index) {
		cbxElectrodeType.setSelectedIndex(index);		
	}
	
	protected void addShowAllListener(ActionListener l) {
		btnShowAll.addActionListener(l);
	}

	protected void setElectrodeRenderer(Class<Electrode> elclass, TableCellRenderer r) {
		table.setDefaultRenderer(elclass, r);
	}
	
	protected void setStringRenderer(Class<String> stringClass, TableCellRenderer r) {
		table.setDefaultRenderer(stringClass, r);
	}
	
	protected void setDateRenderer(Class<LocalDate> dateClass, TableCellRenderer r) {
		table.setDefaultRenderer(dateClass, r);
}

	protected void setTblSelectionMode(int selectionMode) {
		table.setSelectionMode(selectionMode);		
	}
	
	protected void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}
	
	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	protected Electrode getTableValueAt(int row, int column) {
		return (Electrode) table.getValueAt(row, column);
	}


}
