package com.rose.pm.ui;

import java.awt.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DateFormatter;

import com.rose.pm.material.MaterialType;

import com.rose.pm.material.Status;




public class Editor {
	 class DateCellEditor extends DefaultCellEditor{
	
		private static final long serialVersionUID = -4704352232757265783L;
		DateFormatter dateFormatter;
		
		public DateCellEditor(){
		    super(new JFormattedTextField());
		}
		
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			JFormattedTextField editor = (JFormattedTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
			Date date;
			LocalDate ldate;
			if (value!=null){
		    	DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		        dateFormatter = new DateFormatter(df);
		       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		        editor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(dateFormatter));
//		        try {
//					ZoneId defaultZoneId = ZoneId.systemDefault();
//					date = Date.from(((LocalDate)value).atStartOfDay(defaultZoneId).toInstant());			    	  
//				}catch(DateTimeParseException e) {
//					date = null;
//				}
		       
		        date = (Date) value;  
		           // String text = DateFormat.format(date);
		        editor.setHorizontalAlignment(SwingConstants.RIGHT);
		        editor.setValue(date);
			}

			return editor;
		}
		 
		@Override
		public Object getCellEditorValue() {
			LocalDate ldate;
			Date date;
			
			// get content of textField
			String str = (String) super.getCellEditorValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			try {
				ldate = LocalDate.parse(str, formatter);
				ZoneId defaultZoneId = ZoneId.systemDefault();
				date = Date.from(ldate.atStartOfDay(defaultZoneId).toInstant());			    	  
			}catch(DateTimeParseException e) {
				date = null;
			}
			return date;
		 
		}
    }
	 
	 /**
	  * table cell editor for the first row and the status column of the table
	  * @author Ekki
	  *
	  */
	 class SearchStatusTblCellEditor extends AbstractCellEditor implements TableCellEditor{
		
		private static final long serialVersionUID = -6298511433943873838L;
		private TableCellEditor editor;
		private JComboBox<Status> cbxStatus;
		private ComboBoxModel<Status> cbxStatusModel;
		private Renderer renderer;
		private Listener listener;
		private Renderer.SearchStatusListCellRenderer searchStatusListCellRenderer;
		private Listener.SearchStatusListener searchStatusListener;
		private AbstractTableModel tblModel;
		
		
		
		protected Listener.SearchStatusListener getSearchStatusListener() {
			return searchStatusListener;
		}
		/**
		 * standard constructor
		 * @param tblModel
		 * @param pnlBase
		 */
		public SearchStatusTblCellEditor(AbstractTableModel tblModel, PnlBase pnlBase) {
			this.tblModel = tblModel;
			Status[] s = Status.class.getEnumConstants();

			cbxStatusModel = new DefaultComboBoxModel<>(s);
			
			cbxStatus = new JComboBox<>(cbxStatusModel);
			cbxStatus.insertItemAt(null, 0);
			renderer = new Renderer();
			searchStatusListCellRenderer = renderer.new SearchStatusListCellRenderer();
			cbxStatus.setRenderer(searchStatusListCellRenderer);
			listener = new Listener();
			searchStatusListener = listener.new SearchStatusListener(this.tblModel, pnlBase);
			cbxStatus.addItemListener(searchStatusListener);
		}
		
		@Override
		public Object getCellEditorValue() {
			if (editor != null) {
				return editor.getCellEditorValue();
			}
            return null;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (column == 5 && row == 0) {
                editor = new DefaultCellEditor(cbxStatus);
            } 
			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
		
	}
	 
//	 public abstract class MaterialTypeTblCellEditor extends AbstractCellEditor implements TableCellEditor {
//
//		private static final long serialVersionUID = -1852119063688536260L;
//		
//		protected TableCellEditor editor;
//		protected JComboBox<? extends MaterialType> cbxMaterialType;
//		protected SearchMaterialTypeListener searchMaterialTypeListener;
//		
//		protected AbstractTableModel tblModel;
//		protected ArrayList<? extends MaterialType> materialTypes;
//		
//		public MaterialTypeTblCellEditor(AbstractTableModel tblModel) {
//			
//			this.tblModel = tblModel;
//			
//		}
//		
//		
//		
//		@Override
//		public Object getCellEditorValue() {
//			if (editor != null) {
//				return editor.getCellEditorValue();
//			}
//            return null;
//		}
//
//		@Override
//		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
//				int column) {
//			if (column == 1 && row == 0) {
//                editor = new DefaultCellEditor(cbxMaterialType);
//            } 
//
//			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
//		}
//		 
//	 }
}
