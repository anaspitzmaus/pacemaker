package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

import com.rose.person.Patient;

import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.Material;
import com.rose.pm.material.MaterialType;

import com.rose.pm.material.Status;

public class Renderer {

	class TblCellLocalDateRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 5861989547138095236L;

		public TblCellLocalDateRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row > 0) {
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
			}else {
				setBackground(Color.white);
				return null;
			}		
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
			String euro = "\u20ac";
			if(value instanceof Number) {
				setText(df2.format(value) + " " + euro);
			}else {//e.g. for null value
				setText("");
			}
			setHorizontalAlignment(JLabel.RIGHT);
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);  
			}
			return this;
		}
		
	}
	
	class TblCellPatientRenderer extends JLabel implements TableCellRenderer{
		
		private static final long serialVersionUID = -9124714500922535749L;

		public TblCellPatientRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {
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
			}else {
				setBackground(Color.white);	
				setText("");
			}
			return this;
		}
		
	}
	
	class TblCellStatusRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 6751749887945145998L;

		public TblCellStatusRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
		    setVerticalAlignment(CENTER);	
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row==0) {	
				if(value instanceof Status) {
					setText(((Status)value).name());
				}else {
					setText("Alle");
				}
				setBackground(Color.white);	
				Border border = BorderFactory.createLineBorder(Color.ORANGE, 2);
				setBorder(border);
			}else if(row>0) {
				if(value instanceof Status) {
					setText(((Status)value).name());
				}else {
					setText(" ");
				}
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);  
				}	
				setBorder(null);
			}
			return this;
		}		
		
	}
	
	class TblCellImplantDateRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = 2063936186512998098L;
		SimpleDateFormat f;
		
		public TblCellImplantDateRenderer() {
			setOpaque(true);
			f = new SimpleDateFormat("dd.MM.yyyy");
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {
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
			}else {
				setBackground(Color.white);	
				setText("");
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
			setFont(new Font("Tahoma", Font.ITALIC, 14));
			if(value instanceof Status) {
				setText(((Status) value).name());				
			}else {
				setText("Alle");
			}			
			return this;
		}
	 }
	
	class TblCellMaterialIDRenderer extends JLabel implements TableCellRenderer{

		private static final long serialVersionUID = -5875251106838788209L;

		public TblCellMaterialIDRenderer() {
			setOpaque(true);
		 }
		 
		 @Override
		 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			 if(row>0) {
				if(value instanceof Material) {
					setText(String.valueOf(((Material) value).getId()));			
				}else {
					setText("");
				}
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}
			}else {
				setBackground(Color.white);
				setText("");
			}
			return this;				
		 }		 
	 }
	
	class TblCellMaterialTypeRenderer extends JLabel implements TableCellRenderer{
		 
		private static final long serialVersionUID = -135370397809098322L;

		public TblCellMaterialTypeRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		 }		
		

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(row>0) {//for all but the first row
				if(value instanceof MaterialType) {				
					String notation = ((MaterialType)value).getNotation();
					setText(notation);
					if(isSelected) {
						setBackground(Color.ORANGE);
					}else {
						setBackground(row%2==0 ? Color.white : Color.lightGray);   
					}
					setBorder(null);
				}					
			}else if(row==0){//for the first 'search' row
				Border border = BorderFactory.createLineBorder(Color.ORANGE, 2);
				setBorder(border);
				setBackground(Color.white);	
				setText(getFirstRowText((MaterialType)value));			
			}
			return this;
		}
		
		protected String getFirstRowText(MaterialType value) {
			if(value instanceof MaterialType) {
				return (((MaterialType) value).getNotation());
			}else {
				return "Alle Modelle";				
			}			
		}
		 
	 }
	
	class TblCellStringRenderer extends JLabel implements TableCellRenderer{
		
		private static final long serialVersionUID = 2671808743527070182L;

		public TblCellStringRenderer() {
			super.setOpaque(true);
		}		
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value.toString());
			if(row>0) {				
				if(isSelected) {
					setBackground(Color.ORANGE);
				}else {
					setBackground(row%2==0 ? Color.white : Color.lightGray);   
				}	
				setBorder(null);
			}else{
				if(column == 2) {//column for serial number not for notice column
					Border border = BorderFactory.createLineBorder(Color.ORANGE, 2);
					setBorder(border);			
				}else {
					setBorder(null);
				}
				setBackground(Color.white);
			}
			return this;				
		}
		
	}
}
