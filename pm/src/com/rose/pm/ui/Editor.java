package com.rose.pm.ui;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.text.DateFormatter;

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
}
