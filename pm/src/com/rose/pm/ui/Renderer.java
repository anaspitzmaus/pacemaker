package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;
import com.rose.person.Patient;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.MaterialType;
import com.rose.pm.material.Status;

public class Renderer {

	class TblLocalDateRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 5861989547138095236L;

		public TblLocalDateRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value instanceof LocalDate) {
				LocalDate date = (LocalDate) value;			
				setText(date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}
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
	
	/**
	 * a tablecellrenderer for double values
	 * 
	 * @author Ekki
	 *
	 */
	class TblDoubleRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = -9100269051584681144L;

		public TblDoubleRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			DecimalFormat df2 = new DecimalFormat("#.##");
			
			setText(df2.format(value) + " €");
			setHorizontalAlignment(JLabel.RIGHT);
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);  
			}
			return this;
		}
		
	}
	
	class TblPatientRenderer extends JLabel implements TableCellRenderer{
		
		private static final long serialVersionUID = -9124714500922535749L;

		public TblPatientRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value instanceof Patient) {
				setText(String.valueOf(((Patient)value).getNumber()));
			}else {
				setText("");
			}
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);  
			}
			return this;
		}
		
	}
	
	class TblStatusRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 6751749887945145998L;

		public TblStatusRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(((Status)value).name());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);  
			}
			return this;
		}
		
	}
	
	class TblImplantDateRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 2063936186512998098L;
		SimpleDateFormat f;
		public TblImplantDateRenderer() {
			setOpaque(true);
			f = new SimpleDateFormat("dd.MM.yyyy");
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			 if( value instanceof Date) {
		            value = f.format(value);
		            setText((String) value);
		        }else {
		        	setText("");
		        }
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			 return this;
		}
		
	}
	
	class TblCellManufacturerRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 7681759060984296813L;

		public TblCellManufacturerRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {
				if(value instanceof Manufacturer) {
					setText(((Manufacturer) value).getNotation());				
				}else {
					setText("");
				}
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}
				
			}else {//if first row				
				setBackground(Color.white);	
				if(value instanceof Manufacturer) {
					setText(((Manufacturer) value).getNotation());
				}else {
					setText("");					
				}
			}
			return this;
			
		}
		
	}
	
	class MaterialTypeListRenderer extends JLabel implements ListCellRenderer<MaterialType>{

		private static final long serialVersionUID = 2174496440997500464L;

		public MaterialTypeListRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends MaterialType> list, MaterialType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof MaterialType) {
				setText(((MaterialType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}		
	} 
	
	class SearchStatusListCellRenderer extends JLabel implements ListCellRenderer<Status>{

		private static final long serialVersionUID = -3682525429996426370L;

		public SearchStatusListCellRenderer() {
			setOpaque(true);
		    setHorizontalAlignment(CENTER);
		    setVerticalAlignment(CENTER);	
		}
		 
		@Override
		public Component getListCellRendererComponent(JList<? extends Status> list, Status value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof Status) {
				setText(((Status) value).name());
				setFont(new Font("Tahoma", Font.ITALIC, 14));
			}else {
				setText("");
			}
			return this;
		}
	 }
}
