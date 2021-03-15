package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * abstract JPanel that builds a primitive JPanel with a panel at north and a panel at south
 * and a scrollPane with a JTable at the center. 
 * Also a Label ´notation´ and a Label ´notice´ with the associated JTextFields are created
 * it offers methods to add listeners and models to the components
 * @author Ekki
 *
 */

public abstract class PnlBase extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2190719278722274586L;
	JTable table;
	JPanel pnlInput;
	JPanel pnlSouth;
	JTextField txtNotation;
	JTextField txtNotice;
	JLabel lblNotation;	
	JLabel lblNotice;
	JButton btnCreate, btnDelete;	
	Font font;
	

	/**
	 * Create the panel.
	 */
	public PnlBase() {
		setLayout(new BorderLayout(0, 0));
		
		pnlInput = new JPanel();
		add(pnlInput, BorderLayout.NORTH);		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		pnlSouth = new JPanel();
		pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		add(pnlSouth, BorderLayout.SOUTH);
		
		font = new Font("Tahoma", Font.PLAIN, 14);
		
		lblNotation = new JLabel("lblNotation");
		lblNotation.setFont(font);
		
		txtNotation = new JTextField();
		txtNotation.setFont(font);		
		txtNotation.setColumns(10);
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(font);
		
		txtNotice = new JTextField();
		txtNotice.setFont(font);
		txtNotice.setColumns(10);
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(font);
		
		btnDelete = new JButton("BtnDelete");
		btnDelete.setFont(font);

	}
	
	/**
	 * set the notation to the label 'notation'	
	 * @param txt a String value
	 */
	protected void setLblNotationText(String txt) {
		lblNotation.setText(txt);
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
	
	protected void addNotationListener(DocumentListener notationListener) {
		txtNotation.getDocument().addDocumentListener(notationListener);		
	}

	protected void addNoticeListener(DocumentListener noticeListener) {
		txtNotice.getDocument().addDocumentListener(noticeListener);		
	}
	
	protected void addCreateListener(ActionListener createListener) {
		btnCreate.addActionListener(createListener);		
	}
	
	protected void addDeleteListener(ActionListener listener) {
		btnDelete.addActionListener(listener);		
	}
	
	protected void setTblModel(AbstractTableModel model) {
		table.setModel(model);
	}
	
	protected void setTblSelectionMode(int singleSelection) {
		table.setSelectionMode(singleSelection);
	}
	
	protected int getSelectedTblRow() {		
		return table.getSelectedRow();
	}
	
	protected void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}
	
	protected void addTblMouseAdaptor(MouseListener mouseAdaptor) {
		table.addMouseListener(mouseAdaptor);		
	}
	
	protected void setDoubleRenderer(Class<Double> dblClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(dblClass, renderer);
	}
	
	

}
