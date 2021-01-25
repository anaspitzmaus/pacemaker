package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.rose.pm.db.DB;
import com.rose.pm.db.Dlg_DBSettings;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Manufacturer;


public class CtrlDlgManufacturer {
	DlgManufacturer dlgManufacturer;
	Manufacturer manufacturer;
	String notation, contactPerson, mobile;
	NotationListener notationListener;
	ContactPersonListener contactPersonListener;
	CreateListener createListener;
	ManufacturerTblModel manufacturerTblModel;
	ArrayList<Manufacturer> manufacturers;
	TblRowSelectionListener tblRowSelectionListener;
	Boolean modusChange;
	ModeListener modeListener;
	MobileListener mobileListener;
	MobileMaskFormatter mobileMaskFormatter;
	MobileMaskFormatter displayFormatter;
	MobileMaskFormatter defaultFormatter;
	MobileFormatterFactory mobileFormatterFactory;
	DlgCloseListener dlgCloseListener;
	
	
	public CtrlDlgManufacturer() {
		dlgManufacturer = new DlgManufacturer();
		//connectDB();
		//if (DB.getConnection() != null) {
			setListener();
			
			mobileMaskFormatter = null;
			try {
				mobileMaskFormatter = new MobileMaskFormatter("####/########");
				displayFormatter = new MobileMaskFormatter("####/########");
				defaultFormatter = new MobileMaskFormatter("####/########");
			}catch(ParseException e) {
				e.printStackTrace();
			}
			
			mobileFormatterFactory = new MobileFormatterFactory(defaultFormatter, displayFormatter, mobileMaskFormatter);
			dlgManufacturer.getFtxtMobile().setFormatterFactory(mobileFormatterFactory);
			manufacturers = SQL_SELECT.manufacturers();
			manufacturerTblModel = new ManufacturerTblModel();
			dlgManufacturer.setTableModel(manufacturerTblModel);
			tblRowSelectionListener = new TblRowSelectionListener();
			dlgManufacturer.addTblRowSelectionListener(tblRowSelectionListener);
			modusChange = false;
			dlgManufacturer.pack();
			showDialog();
		//}
		
	}
	
	private void setListener() {
		notationListener = new NotationListener();
		contactPersonListener = new ContactPersonListener();
		createListener = new CreateListener();
		mobileListener = new MobileListener();
		dlgManufacturer.addNotationListener(notationListener);
		dlgManufacturer.addContactPersonListener(contactPersonListener);
		dlgManufacturer.addCreateListener(createListener);
		dlgManufacturer.addMobilListener(mobileListener);
		modeListener = new ModeListener();
		dlgManufacturer.addModeListener(modeListener);
		dlgCloseListener = new DlgCloseListener();
		dlgManufacturer.addOKListener(dlgCloseListener);
		
	}
	
	protected void showDialog() {
		dlgManufacturer.setVisible(true);
	}
	
	private void connectDB() {
		if (DB.getConnection() == null) {		
			if(DB.createConnection() != null){			
				 //go further on only if a connection to the database could be established
				if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
					//start the login dialog
					try {
//						Dlg_LogIn dialog = new Dlg_LogIn();
//						dialog.setLocationRelativeTo(null); //show in the center of the screen
//						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						//dialog.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(),
							    "The Application couldn't be started!", "Fatal Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(),
						    "The Application couldn't be started!", "Fatal Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}else{ //if there couldn't be created a database connection
				//open the Dialog with the database settings
				Dlg_DBSettings dlgDBSettings = new Dlg_DBSettings();
				dlgDBSettings.setLocationRelativeTo(null);
				dlgDBSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dlgDBSettings.setModal(true);
				dlgDBSettings.setVisible(true);	
			}
		}
	}
	
	
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(modusChange) {
				// update the selected manufacturer with the new data; id must not be updated as always stored in manufacturer object
				manufacturer.setNotation(notation);
				manufacturer.setContact_person(contactPerson);
				manufacturer.setMobile(mobile);
				SQL_UPDATE.Manufacturer(manufacturer);
				
			}else {
			
				manufacturer = new Manufacturer(notation);
				manufacturer.setContact_person(contactPerson);
				manufacturer.setMobile(mobile);
				
				if(manufacturer.getNotation() != null &&  manufacturer.getNotation() != "") {					
					
						SQL_INSERT.Manufacturer(manufacturer);				
				}
			}
			//after update and insert pass a new select statement and refresh the table data
			manufacturers = SQL_SELECT.manufacturers();
			manufacturerTblModel.fireTableDataChanged();
		}		
	}
	
	class NotationListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			notation = dlgManufacturer.getTxtNotation().getText();	
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			notation = dlgManufacturer.getTxtNotation().getText();
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			notation = dlgManufacturer.getTxtNotation().getText();
			
		}
		
	}
	
	class ContactPersonListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			contactPerson = dlgManufacturer.getTxtContact().getText();
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			contactPerson = dlgManufacturer.getTxtContact().getText();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			contactPerson = dlgManufacturer.getTxtContact().getText();
			
		}
		
	}
	
	class MobileListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
				if(dlgManufacturer.getFtxtMobile().getValue() != null) {
					try {
						mobile = (String) dlgManufacturer.getFtxtMobile().getValue();
					}catch(ClassCastException e) {
						mobile = Long.toString((long) dlgManufacturer.getFtxtMobile().getValue());
					}
				}			
		}	
		
	}
	
	class MobileFormatterFactory extends DefaultFormatterFactory{

		/**
		 * 
		 */
		private static final long serialVersionUID = -87753811238277386L;

		public MobileFormatterFactory(AbstractFormatter df, AbstractFormatter dif, AbstractFormatter edf) {
			super(df, dif, edf);
		}
		
	}
	

	class MobileMaskFormatter extends MaskFormatter{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 585074466954075038L;

		public MobileMaskFormatter(String format) throws ParseException {
			super(format);
		}
		
		@Override
		public Object stringToValue(String text) {//return the text as value if text matches the format, otherwise throws a ParseException
			
			try{
				return super.stringToValue(text);
			}catch(ParseException|NullPointerException e) {
				if(text.trim().matches("\\d{4}+/+\\d{5,7}")) {
					return text;
				}else {
					return null;
				}
			}
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if(value!= null) {
				return value.toString();
			}else {
				return super.valueToString(value);//return the mask if value is Null otherwise the string representation of the value
			}
		}
	}
	
	
	
	
	class ManufacturerTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5630441403441681314L;
		protected ArrayList<String> columnNames;
		
		
		public ManufacturerTblModel() {
			
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Firma");
			columnNames.add("Vertreter");
			columnNames.add("Mobil");
			
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.size();
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return manufacturers.size();
		}

		
		
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			switch(columnIndex) {
			case 0: return manufacturers.get(rowIndex).getId();
			
			case 1: return manufacturers.get(rowIndex).getNotation();
			
			case 2: return manufacturers.get(rowIndex).getContact_person();
			
			case 3: return manufacturers.get(rowIndex).getMobile();
			
			default: return null;
			
			}			
		}			
	}
	
	class TblRowSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (dlgManufacturer.getTblManufacturer().getSelectedRow() > -1) {
				if(modusChange) {
					int row = dlgManufacturer.getTblManufacturer().getSelectedRow();
		            manufacturer = new Manufacturer(dlgManufacturer.getTblManufacturer().getValueAt(row, 1).toString());
		            manufacturer.setId((Integer) dlgManufacturer.getTblManufacturer().getValueAt(row, 0));
		            manufacturer.setContact_person(dlgManufacturer.getTblManufacturer().getValueAt(row, 2).toString());
		            manufacturer.setMobile(dlgManufacturer.getTblManufacturer().getValueAt(row, 3).toString());
		            //fill text fields with data of selected manufacturer
		            dlgManufacturer.getTxtNotation().setText(manufacturer.getNotation());
		            dlgManufacturer.getTxtContact().setText(manufacturer.getContact_person());
		            dlgManufacturer.getFtxtMobile().setValue(manufacturer.getMobile());
		        }    	           
	        }
			
		}
		
	}
	
	class ModeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (dlgManufacturer.getTglMode().isSelected()) {
				//Mode change
				modusChange = true;
				dlgManufacturer.getTglMode().setText("Modus: Ändern");
				dlgManufacturer.getBtnCreate().setText("Änderungen übernehmen");			
			}else {
				//Mode new
				modusChange = false;
				dlgManufacturer.getTglMode().setText("Modus: Neu anlegen");
				dlgManufacturer.getBtnCreate().setText("Hinzufügen");
			}
			dlgManufacturer.getTblManufacturer().getSelectionModel().clearSelection();
			dlgManufacturer.getTxtNotation().setText("");
	        dlgManufacturer.getTxtContact().setText("");
	        dlgManufacturer.getFtxtMobile().setText("");	
			
		}
		
	}
	
	class DlgCloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dlgManufacturer.dispose();
			
		}
		
	}
}
