package com.rose.pm.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rose.person.Patient;
import com.rose.pm.Pnl_SetDate;
import com.rose.pm.material.SICD;
import com.rose.pm.material.SICDType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.CtrlPnlSICD.SICDTypeTblCellEditor;
import com.rose.pm.ui.Editor.SearchStatusTblCellEditor;
import com.rose.pm.ui.Renderer.TblCellMaterialIDRenderer;
import com.rose.pm.ui.Renderer.TblCellPatientRenderer;

import net.miginfocom.swing.MigLayout;

;

public class PnlSICD extends PnlBase {

	private static final long serialVersionUID = 3214318116528507442L;
	JLabel lblAggregateType;	
	JComboBox<SICDType> cbxSICDType;
	JButton btnShowAll;
	
	protected void setLblSerialNrText(String txt) {
		lblNotation.setText(txt);
	}
	
		
	protected void setLblAggregatTypeText(String txt) {
		lblAggregateType.setText(txt);
	}
	
		
	protected void setBtnShowAllText(String txt) {
		btnShowAll.setText(txt);
	}
	/**
	 * Create the panel.
	 */
	public PnlSICD() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[][]"));
		
		btnShowAll = new JButton("btnShowAll");
		btnShowAll.setFont(font);
		pnlInput.add(btnShowAll, "cell 1 0");
		
		lblAggregateType = new JLabel("lblPM_Type");
		lblAggregateType.setFont(font);
		pnlInput.add(lblAggregateType, "cell 0 1");
		
		cbxSICDType = new JComboBox<SICDType>();
		cbxSICDType.setFont(font);
		pnlInput.add(cbxSICDType, "cell 1 1, growx");
		
		pnlInput.add(lblNotation, "cell 2 1");		
		
		pnlInput.add(txtNotation, "cell 3 1");
		
		pnlInput.add(lblNotice, "cell 5 1");
		
		pnlInput.add(txtNotice, "cell 6 1, growx");
		
		pnlInput.add(btnCreate, "cell 7 1");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}
	
	protected void addSerialNrListener(DocumentListener listener) {
		super.addNotationListener(listener);	
	}
	
	protected void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}
	
	protected void addAggregateTypeListener(ItemListener l) {
		cbxSICDType.addItemListener(l);
	}
	
	protected void addShowAllListener(ActionListener l) {
		btnShowAll.addActionListener(l);
	}
	
	protected void addTblMouseAdaptor(MouseListener tblMouseAdaptor) {
		table.addMouseListener(tblMouseAdaptor);		
	}
	
	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	protected SICD getTableValueAt(int row, int column) {
		return (SICD) table.getValueAt(row, column);
	}
	
	protected void setAggregateTypeSelectionIndex(Integer index) {
		cbxSICDType.setSelectedIndex(index);		
	}	
	
	protected void setAggregateTblModel(AbstractTableModel aggregateTblModel) {
		table.setModel(aggregateTblModel);		
	}
	
	protected void integratePnlDate(Pnl_SetDate pnlDate) {
		pnlInput.add(pnlDate, "cell 4 1");
	}
	
	protected void setAggregateTypeModel(ComboBoxModel<SICDType> model) {
		cbxSICDType.setModel(model);		
	}

	protected void setAggregateTypeRenderer(ListCellRenderer<SICDType> renderer) {
		cbxSICDType.setRenderer(renderer);		
	}

	protected void setAggregateIDRenderer(Class<SICD> sicdClass, TableCellRenderer r) {
		table.setDefaultRenderer(sicdClass, r);
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
	
	protected void setStatusRenderer(Class<Status> statusClass, TableCellRenderer tblStatusRenderer) {
		table.setDefaultRenderer(statusClass, tblStatusRenderer);		
	}
	
	protected void setTblAggregateTypeRenderer(Class<SICDType> typeClass,	TableCellRenderer renderer) {
		table.setDefaultRenderer(typeClass, renderer);		
	}
	
	protected void clearComponents() {
		txtNotation.setText("");
		txtNotice.setText("");
	}

	protected void setTblPatientRenderer(Class<Patient> patientClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(patientClass, renderer);		
	}
	
	protected void setDateCellEditor(DefaultCellEditor editor) {
		 table.getColumnModel().getColumn(7).setCellEditor(editor);		
	}
	
	protected void setTblImplantDateRenderer(Class<Date> dateClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(dateClass, renderer);		
	}

	protected void setSICDTypeTblCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(1).setCellEditor(editor);		
	}


	protected void setTblSICDIDRenderer(Class<SICD> sicdClass, TblCellMaterialIDRenderer renderer) {
		table.setDefaultRenderer(sicdClass, renderer);		
	}

	protected void setStatusTblCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(5).setCellEditor(editor);			
	}
	
	protected void setNotationCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(2).setCellEditor(editor);
	}
	
	protected void setTblCellStringRenderer(Class<String> stringClass, TableCellRenderer r) {
		table.setDefaultRenderer(stringClass, r);
	}
}
