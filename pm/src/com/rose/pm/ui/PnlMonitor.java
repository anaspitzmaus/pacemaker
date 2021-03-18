package com.rose.pm.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rose.person.Patient;
import com.rose.pm.Pnl_SetDate;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.Status;


import net.miginfocom.swing.MigLayout;

public class PnlMonitor extends PnlBase {
	
	private static final long serialVersionUID = -3388760039486245297L;
	JLabel lblMonitorType;
	JComboBox<MonitorType> cbxMonitorType;
	JButton btnShowAll;

	protected void setMonitorTypeModel(DefaultComboBoxModel<MonitorType> monitorTypeModel) {
		cbxMonitorType.setModel(monitorTypeModel);		
	}

	protected void setLblMonitorTypeText(String text) {
		lblMonitorType.setText(text);	
	}
	
	protected void setBtnShowAllText(String txt) {
		btnShowAll.setText(txt);
	}

	public PnlMonitor() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[][]"));
		
		btnShowAll = new JButton("btnShowAll");
		btnShowAll.setFont(font);
		pnlInput.add(btnShowAll, "cell 1 0");
		
		lblMonitorType = new JLabel("lblPM_Type");
		lblMonitorType.setFont(font);
		pnlInput.add(lblMonitorType, "cell 0 1");
		
		cbxMonitorType = new JComboBox<MonitorType>();
		cbxMonitorType.setFont(font);
		pnlInput.add(cbxMonitorType, "cell 1 1, growx");
		
		pnlInput.add(lblNotation, "cell 2 1");
		
		pnlInput.add(txtNotation, "cell 3 1");
				
		pnlInput.add(lblNotice, "cell 5 1");
		
		pnlInput.add(txtNotice, "cell 6 1, growx");
		
		pnlInput.add(btnCreate, "cell 7 1");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}

	protected void addMonitorTypeListener(ItemListener l) {
		cbxMonitorType.addItemListener(l);		
	}

	protected void integratePnlDate(Pnl_SetDate pnlDate) {
		pnlInput.add(pnlDate, "cell 4 1");
	}

	protected void setMonitorTypeRenderer(ListCellRenderer<MonitorType> renderer) {
		cbxMonitorType.setRenderer(renderer);
	}
	
	protected void addSerialNrListener(DocumentListener listener) {
		super.addNotationListener(listener);				
	}
	
	protected Monitor getTableValueAt(int row, int column) {
		return (Monitor) table.getValueAt(row, column);
	}
	
	protected void addShowAllListener(ActionListener l) {
		btnShowAll.addActionListener(l);
	}
	
	protected void setMonitorTypeSelectionIndex(Integer index) {
		cbxMonitorType.setSelectedIndex(index);		
	}

	protected void setTblMonitorIDRenderer(Class<Monitor> monitorClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(monitorClass, renderer);		
	}

	protected void setTblMonitorTypeRenderer(Class<MonitorType> monitorTypeClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(monitorTypeClass, renderer);		
	}

	protected void setTblStringRenderer(Class<String> stringClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(stringClass, renderer);
	}

	protected void setTblPatientRenderer(Class<Patient> patientClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(patientClass, renderer);
	}

	protected void setTblStatusRenderer(Class<Status> statusClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(statusClass, renderer);
	}

	protected void setTblImplantDateRenderer(Class<Date> dateClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(dateClass, renderer);
	}

	protected void setLocalDateRenderer(Class<LocalDate> localDateClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(localDateClass, renderer);
	}
	
	protected void setDateCellEditor(DefaultCellEditor editor) {
		 table.getColumnModel().getColumn(7).setCellEditor(editor);		
	}
	
	protected void setNotationCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(2).setCellEditor(editor);
	}
	
	protected void setFirstRowHeight(int height) {
		table.setRowHeight(0, height);		
	}
	
	protected void setMonitorTypeTblCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(1).setCellEditor(editor);		
	}

	public void setStatusTblCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(5).setCellEditor(editor);			
	}

	

}
