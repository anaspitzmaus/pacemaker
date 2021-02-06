package com.rose.pm.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
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
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ER;
import com.rose.pm.material.ERType;
import com.rose.pm.material.PM;
import com.rose.pm.material.Status;

import net.miginfocom.swing.MigLayout;

public class PnlER extends PnlBase {

	private static final long serialVersionUID = 8958719570479767135L;

	JLabel lblSerialNr, lblNotice, lblRecorderType;
	JTextField txtSerialNr, txtNotice;
	JComboBox<ERType> cbxRecorderType;
	JButton btnCreate, btnDelete, btnShowAll;
	
	protected void setLblSerialNrText(String txt) {
		lblSerialNr.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setLblRecorderTypeText(String txt) {
		lblRecorderType.setText(txt);
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
	public PnlER() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[][]"));
		
		btnShowAll = new JButton("btnShowAll");
		btnShowAll.setFont(font);
		pnlInput.add(btnShowAll, "cell 1 0");
		
		lblRecorderType = new JLabel("lblRecorderType");
		lblRecorderType.setFont(font);
		pnlInput.add(lblRecorderType, "cell 0 1");
		
		cbxRecorderType = new JComboBox<ERType>();
		cbxRecorderType.setFont(font);
		pnlInput.add(cbxRecorderType, "cell 1 1, growx");
		
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
	
	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	protected ER getTableValueAt(int row, int column) {
		return (ER) table.getValueAt(row, column);
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
	
	protected void addRecorderTypeListener(ItemListener l) {
		cbxRecorderType.addItemListener(l);
	}

	protected void setRecorderTypeModel(ComboBoxModel<ERType> model) {
		cbxRecorderType.setModel(model);
		
	}

	protected void setRecorderTypeRenderer(ListCellRenderer<ERType> renderer) {
		cbxRecorderType.setRenderer(renderer);
		
	}
	
	protected void addCreateListener(ActionListener l) {
		btnCreate.addActionListener(l);
	}
	
	protected void addDeleteListener(ActionListener l) {
		btnDelete.addActionListener(l);
	}

	protected void setRecorderTblModel(AbstractTableModel recorderTblModel) {
		table.setModel(recorderTblModel);
		
	}
	
	protected void setRecorderTypeSelectionIndex(Integer index) {
		cbxRecorderType.setSelectedIndex(index);		
	}
	
	protected void addShowAllListener(ActionListener l) {
		btnShowAll.addActionListener(l);
	}
	
	protected void setERIDRenderer(Class<ER> erClass, TableCellRenderer r) {
		table.setDefaultRenderer(erClass, r);
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
	
	protected void addERTypeListener(ItemListener l) {
		cbxRecorderType.addItemListener(l);
	}
	
	protected void addTblMouseAdaptor(MouseListener tblMouseAdaptor) {
		table.addMouseListener(tblMouseAdaptor);		
	}
	
	protected void setERTypeModel(ComboBoxModel<ERType> model) {
		cbxRecorderType.setModel(model);		
	}
	
	protected void setERTypeSelectionIndex(Integer index) {
		cbxRecorderType.setSelectedIndex(index);		
	}
	
	protected void setStatusRenderer(Class<Status> statusClass, TableCellRenderer tblStatusRenderer) {
		table.setDefaultRenderer(statusClass, tblStatusRenderer);		
	}
	
	protected void setERTypeRenderer(ListCellRenderer<ERType> renderer) {
		cbxRecorderType.setRenderer(renderer);		
	}
	
	protected void setTblERTypeRenderer(Class<ERType> typeClass,	TableCellRenderer tblERTypeRenderer) {
		table.setDefaultRenderer(typeClass, tblERTypeRenderer);		
	}
	
	protected void setERTblModel(AbstractTableModel erTblModel) {
		table.setModel(erTblModel);		
	}
	
	
	protected void clearComponents() {
		txtSerialNr.setText("");
		txtNotice.setText("");
	}

}
