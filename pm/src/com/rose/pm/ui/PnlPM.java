package com.rose.pm.ui;

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

import com.rose.pm.Pnl_SetDate;
import com.rose.pm.material.AggregateType;

import net.miginfocom.swing.MigLayout;

public class PnlPM extends PnlBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5819944448021949922L;
	JLabel lblSerialNr, lblNotice, lblAggregateType;
	JTextField txtSerialNr, txtNotice;
	JComboBox<AggregateType> cbxPMType;
	JButton btnCreate, btnDelete;
	
	protected void setLblSerialNrText(String txt) {
		lblSerialNr.setText(txt);
	}
	
	protected void setLblNoticeText(String txt) {
		lblNotice.setText(txt);
	}
	
	protected void setLblAggregatTypeText(String txt) {
		lblAggregateType.setText(txt);
	}
	
	protected void setBtnCreateText(String txt) {
		btnCreate.setText(txt);
	}
	
	protected void setBtnDeleteText(String txt) {
		btnDelete.setText(txt);
	}
	/**
	 * Create the panel.
	 */
	public PnlPM() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][grow][]", "[]"));
		
		lblAggregateType = new JLabel("lblPM_Type");
		lblAggregateType.setFont(font);
		pnlInput.add(lblAggregateType, "cell 0 0");
		
		cbxPMType = new JComboBox<AggregateType>();
		cbxPMType.setFont(font);
		pnlInput.add(cbxPMType, "cell 1 0");
		
		lblSerialNr = new JLabel("lblNotation");
		lblSerialNr.setFont(font);
		pnlInput.add(lblSerialNr, "cell 2 0");
		
		txtSerialNr = new JTextField();
		txtSerialNr.setFont(font);
		pnlInput.add(txtSerialNr, "cell 3 0");
		txtSerialNr.setColumns(10);
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(font);
		pnlInput.add(lblNotice, "cell 5 0");
		
		txtNotice = new JTextField();
		txtNotice.setFont(font);
		txtNotice.setColumns(10);
		pnlInput.add(txtNotice, "cell 6 0, growx");
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(font);
		pnlInput.add(btnCreate, "cell 7 0");
		
		btnDelete = new JButton("btnDelete");
		btnDelete.setFont(font);
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
	}
	
	protected void integratePnlDate(Pnl_SetDate pnlDate) {
		pnlInput.add(pnlDate, "cell 4 0");
	}

	protected void addSerialNrListener(DocumentListener listener) {
		txtSerialNr.getDocument().addDocumentListener(listener);		
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);
	}
	
	protected void addAggregateTypeListener(ItemListener l) {
		cbxPMType.addItemListener(l);
	}

	protected void setAggregatTypeModel(ComboBoxModel<AggregateType> model) {
		cbxPMType.setModel(model);
		
	}

	protected void setAggregatTypeRenderer(ListCellRenderer<AggregateType> renderer) {
		cbxPMType.setRenderer(renderer);
		
	}
	
	protected void addCreateListener(ActionListener l) {
		btnCreate.addActionListener(l);
	}
	
	protected void addDeleteListener(ActionListener l) {
		btnDelete.addActionListener(l);
	}

	

}
