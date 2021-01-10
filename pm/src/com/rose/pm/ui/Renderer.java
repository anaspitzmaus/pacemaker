package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Renderer {

	class TblDateRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5861989547138095236L;

		public TblDateRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			LocalDate date = (LocalDate) value;			
			setText(date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
			
			
		}
		
	}
	
	class TblStringRenderer extends JLabel implements TableCellRenderer{
		
		
		public TblStringRenderer() {
			super.setOpaque(true);
		}
		private static final long serialVersionUID = -523838024295879261L;
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
		}
		
	}
}
