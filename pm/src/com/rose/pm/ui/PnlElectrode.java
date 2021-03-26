package com.rose.pm.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rose.person.Patient;
import com.rose.pm.Pnl_SetDate;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Status;

import net.miginfocom.swing.MigLayout;

public class PnlElectrode extends PnlBase {

	
	private static final long serialVersionUID = 656734936297482035L;
	JLabel lblElectrodeType;
	JComboBox<ElectrodeType> cbxElectrodeType;
	JButton btnShowAll;
	
	
	
	protected void setLblSerialNrText(String txt) {
		lblNotation.setText(txt);
	}	
	
	protected void setLblElectrodeTypeText(String txt) {
		lblElectrodeType.setText(txt);
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
		
		pnlInput.add(lblNotation, "cell 2 1");
		
		pnlInput.add(txtNotation, "cell 3 1");
				
		pnlInput.add(lblNotice, "cell 5 1");
		
		pnlInput.add(txtNotice, "cell 6 1, growx");
		
		pnlInput.add(btnCreate, "cell 7 1");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);

		
	}
	
	protected void integratePnlDate(Pnl_SetDate pnlDate) {
		pnlInput.add(pnlDate, "cell 4 1");
	}

	protected void addSerialNrListener(DocumentListener listener) {
		super.addNotationListener(listener);				
	}
	
	protected void addElectrodeTypeListener(ItemListener l) {
		cbxElectrodeType.addItemListener(l);
	}

	protected void setElectrodeTypeModel(ComboBoxModel<ElectrodeType> model) {
		cbxElectrodeType.setModel(model);
		
	}

	/**
	 * set the renderer for the ComboBox that displays the types of electrodes
	 * @param renderer
	 */
	protected void setElectrodeTypeRenderer(ListCellRenderer<ElectrodeType> renderer) {
		cbxElectrodeType.setRenderer(renderer);		
	}
	
	/**
	 * set the electrode type renderer for the table 
	 * @param elclass
	 * @param r
	 */
	protected void setTblElectrodeTypeRenderer(Class<ElectrodeType> elclass, TableCellRenderer r) {
		table.setDefaultRenderer(elclass, r);
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
	
	protected void setTblCellStringRenderer(Class<String> stringClass, TableCellRenderer r) {
		table.setDefaultRenderer(stringClass, r);
	}
	
	protected void setDateRenderer(Class<LocalDate> dateClass, TableCellRenderer r) {
		table.setDefaultRenderer(dateClass, r);
	}

	protected Electrode getTableValueAt(int row, int column) {
		return (Electrode) table.getValueAt(row, column);
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

	protected void setDateCellEditor(DefaultCellEditor editor) {
		 table.getColumnModel().getColumn(7).setCellEditor(editor);		
	}
	
	protected void setElectrodeTypeTblCellEditor(TableCellEditor editor) {
		table.getColumnModel().getColumn(1).setCellEditor(editor);		
	}

	


}
