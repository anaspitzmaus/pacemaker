package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.Isynet;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.Status;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Renderer.TblLocalDateRenderer;
import com.rose.pm.ui.Renderer.TblPatientRenderer;
import com.rose.pm.ui.Renderer.TblCellStatusRenderer;
import com.rose.pm.ui.Renderer.TblStringRenderer;



public class CtrlPnlElectrode extends CtrlPnlBase{
	Ctrl_PnlSetDate ctrlPnlSetDate;
	DefaultComboBoxModel<ElectrodeType> electrodeTypeModel;
	ElectrodeTblModel electrodeTblModel;
	ElectrodeTypeListener electrodeTypeListener;
	ElectrodeTypeRenderer electrodeTypeRenderer;
	TblElectrodeTypeRenderer tblElectrodeTypeRenderer;
	Listener listener;
	Renderer renderer;
	NotationListener serialNrListener, noticeListener;
	TblLocalDateRenderer tblDateRenderer;
	TblStringRenderer tblStringRenderer;
	TblElectrodeIDRenderer electrodeRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	ShowAllListener showAllListener;
	CreateListener createListener;
	TblMouseAdaptor tblMouseAdaptor;
	DeleteListener deleteListener; 
	TblPatientRenderer tblPatientRenderer;
	TblCellStatusRenderer tblStatusRenderer;
	Renderer.TblImplantDateRenderer tblImplantDateRenderer;
	Editor editor;
	Editor.DateCellEditor dateCellEditor;
	
	public CtrlPnlElectrode() {
		createPanel();
		setComponentLabeling();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		((PnlElectrode)panel).integratePnlDate(ctrlPnlSetDate.getPanel());
	}
	
	protected void createPanel() {
		panel = new PnlElectrode();
		panel.setName("Elektroden");
		setListener();
		setModel();	
		setEditor();
		
		setRenderer();
		((PnlElectrode)panel).setElectrodeTypeSelectionIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlElectrode)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	protected void setComponentLabeling() {
		((PnlElectrode)panel).setLblElectrodeTypeText("Elektrodenmodel:");
		((PnlElectrode)panel).setLblSerialNrText("Seriennummer:");
		((PnlElectrode)panel).setLblNoticeText("Bemerkung:");
		((PnlElectrode)panel).setBtnCreateText("Einfügen");
		((PnlElectrode)panel).setBtnDeleteText("Löschen");
		((PnlElectrode)panel).setBtnShowAllText("Alle Modelle");
	}
	
	 void setModel() {
		ArrayList<ElectrodeType> electrodeTypes = SQL_SELECT.electrodeModels();
		ElectrodeType[] arr = new ElectrodeType[electrodeTypes.size()]; 	
		 electrodeTypeModel = new DefaultComboBoxModel<ElectrodeType>();
        // ArrayList to Array Conversion 
        for (int i = 0; i < electrodeTypes.size(); i++) {
            arr[i] = electrodeTypes.get(i);		
            electrodeTypeModel.addElement(electrodeTypes.get(i));
	 	}
		
		((PnlElectrode)panel).setElectrodeTypeModel(electrodeTypeModel);
		electrodeTblModel = new ElectrodeTblModel(SQL_SELECT.electrodes(electrodeTypeListener.model));
		panel.setTblModel(electrodeTblModel);
		
	}
	 
	 private void setListener() {
		 electrodeTypeListener = new ElectrodeTypeListener();
		 ((PnlElectrode)panel).addElectrodeTypeListener(electrodeTypeListener);	
		 listener = new Listener();
		 serialNrListener = listener.new NotationListener();
		 ((PnlElectrode)panel).addSerialNrListener(serialNrListener);
		 noticeListener = listener.new NotationListener();
		 ((PnlElectrode)panel).addNoticeListener(noticeListener);
		 tblRowSelectionListener = new TblRowSelectionListener();
		 ((PnlElectrode)panel).addTblRowSelectionListener(tblRowSelectionListener);
		 showAllListener = new ShowAllListener();
		 ((PnlElectrode)panel).addShowAllListener(showAllListener);
		 createListener = new CreateListener();
		 ((PnlElectrode)panel).addCreateListener(createListener);
		 deleteListener = new DeleteListener();
		 ((PnlElectrode)panel).addDeleteListener(deleteListener);
	 }
	 
	 private void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlElectrode)panel).setDateCellEditor(dateCellEditor);
	 }
	 
	 private void setRenderer() {
		 electrodeTypeRenderer = new ElectrodeTypeRenderer();
		 ((PnlElectrode)panel).setElectrodeTypeRenderer(electrodeTypeRenderer);
		 renderer = new Renderer();
		 tblDateRenderer = renderer.new TblLocalDateRenderer();
		 ((PnlElectrode)panel).setDateRenderer(LocalDate.class, tblDateRenderer);
		 tblStringRenderer = renderer.new TblStringRenderer();
		 ((PnlElectrode)panel).setStringRenderer(String.class, tblStringRenderer);
		 electrodeRenderer = new TblElectrodeIDRenderer();
		 ((PnlElectrode)panel).setElectrodeRenderer(Electrode.class, electrodeRenderer);
		 tblElectrodeTypeRenderer = new TblElectrodeTypeRenderer();
		 ((PnlElectrode)panel).setTblElectrodeTypeRenderer(ElectrodeType.class, tblElectrodeTypeRenderer);
		 tblPatientRenderer = renderer.new TblPatientRenderer();
		 ((PnlElectrode)panel).setTblPatientRenderer(Patient.class, tblPatientRenderer);
		 tblStatusRenderer = renderer.new TblCellStatusRenderer();
		 ((PnlElectrode)panel).setTblStatusRenderer(Status.class, tblStatusRenderer);
		 tblImplantDateRenderer = renderer.new TblImplantDateRenderer();
		 ((PnlElectrode)panel).setTblImplantDateRenderer(Date.class, tblImplantDateRenderer);
	 }
	 
	 private Boolean isElectrodeProvided(Electrode electrode) {		
		 if(electrode.getPatient() instanceof Patient) {
			 return true;
		 }else {
			 return false;
		 }
	 }

	 class ElectrodeTblModel extends AbstractTableModel{

		private static final long serialVersionUID = -8444808544442905721L;

		protected ArrayList<String> columnNames;
		ArrayList<? extends Electrode> electrodes;
		@SuppressWarnings("rawtypes")
		Class[] classes = {Electrode.class, ElectrodeType.class, String.class, LocalDate.class, String.class, Status.class, Patient.class, Date.class};
		
		public ElectrodeTblModel(ArrayList<? extends Electrode> electrodes) {
			this.electrodes = electrodes;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Ablaufdatum");
			columnNames.add("Bemerkung");
			columnNames.add("Status");
			columnNames.add("Patient");
			columnNames.add("Implantationsdatum");
		}
		
		@Override
		public int getColumnCount() {
			return columnNames.size();
		}
		
		@Override
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }

		@Override
		public int getRowCount() {
			return this.electrodes.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return classes[col];			
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
						
			switch(columnIndex) {
			case 0: return electrodes.get(rowIndex);
			case 1: return electrodes.get(rowIndex).getElectrodeType();
			case 2: return electrodes.get(rowIndex).getSerialNr();
			
			case 3: return electrodes.get(rowIndex).getExpireDate();
			
			case 4: return electrodes.get(rowIndex).getNotice();
			
			case 5: return electrodes.get(rowIndex).getStatus();
			
			case 6: return electrodes.get(rowIndex).getPatient();
			
			case 7: return electrodes.get(rowIndex).getDateOfImplantation();
			
			default: return null;
			
			}			
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
            electrodes.get(row).setDateOfImplantation((Date)value);
            try {
				SQL_UPDATE.dateOfImplant(electrodes.get(row));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //change value at database;
		}
		
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex == 7) {
				return true;
			}else {
				return false;
			}
		}

		protected void setElectrodes(ArrayList<? extends Electrode> el) {
			this.electrodes = el;		
		}
			
	}
	 
	 class ElectrodeTypeListener implements ItemListener{

		ElectrodeType model;
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					model = (ElectrodeType) event.getItem();			        
				} catch (ClassCastException e) {
					model = null;
				}				
		    }
			updateTblModel();		
		}
		
		protected void updateTblModel() {
			electrodeTblModel.setElectrodes(SQL_SELECT.electrodes(model));			
			electrodeTblModel.fireTableDataChanged();
		}		
	}
	 
	 class ElectrodeTypeRenderer extends JLabel implements ListCellRenderer<ElectrodeType>{

		private static final long serialVersionUID = -7934129282290942751L;
		
		public ElectrodeTypeRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ElectrodeType> list, ElectrodeType value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value instanceof ElectrodeType) {
				setText(((ElectrodeType) value).getNotation());
				
			}else {
				setText("");
			}
			return this;
		}
	 }
	 
	 class TblElectrodeTypeRenderer extends JLabel implements TableCellRenderer{
		 
		 public TblElectrodeTypeRenderer() {
			 super.setOpaque(true);
		 }
		
		private static final long serialVersionUID = -7162535661899399897L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String notation = ((ElectrodeType)value).getNotation();
			setText(notation);
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
		}
		 
	 }
	 
	 class TblElectrodeIDRenderer extends JLabel implements TableCellRenderer{
		 
			
		
		private static final long serialVersionUID = -7687161924946698926L;

		public TblElectrodeIDRenderer() {
			super.setOpaque(true);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Integer id = ((Electrode)value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(row%2==0 ? Color.white : Color.lightGray);   
			}
			return this;
		}
		
	}
	
 
	
	class TblRowSelectionListener implements ListSelectionListener{
		Electrode electrode;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((PnlElectrode)panel).getSelectedTblRow() > -1) {			
				int row = ((PnlElectrode)panel).getSelectedTblRow();
	            electrode = (Electrode) ((PnlElectrode)panel).getTableValueAt(row, 0); //get the aggregate from the first column		            
	        }			
		}
		
		protected Electrode getElectrodeSelected() {
			return electrode;
		}		
	}
		
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tblRowSelectionListener.getElectrodeSelected() instanceof Electrode) {
				if(JOptionPane.showConfirmDialog(null, "Möchten sie den Datensatz wirklich löschen?") == 0) {
					if(SQL_UPDATE.deleteElectrode(tblRowSelectionListener.getElectrodeSelected())){
						electrodeTblModel.electrodes.remove(tblRowSelectionListener.getElectrodeSelected());
						electrodeTblModel.fireTableDataChanged();
					}
				}
			
			}
		
		}
	}
	
	class ShowAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((PnlElectrode)panel).setElectrodeTypeSelectionIndex(-1);
			update();
		}
		
		protected void update() {
			electrodeTblModel.setElectrodes(SQL_SELECT.electrodes(null));			
			electrodeTblModel.fireTableDataChanged();
		}
		
	}
	
	class CreateListener implements ActionListener{
		Electrode electrode;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "" && electrodeTypeModel.getSelectedItem() instanceof ElectrodeType) {
				initiateElectrode();
				electrode.setExpireDate(ctrlPnlSetDate.getDate());
				electrode.setSerialNr(serialNrListener.getNotation());
				electrode.setNotice(noticeListener.getNotation());
				updateDBAndTblModel();
				
			}
		}
		
		protected void initiateElectrode() {
			electrode = new Electrode((ElectrodeType) electrodeTypeModel.getSelectedItem());			
		}
		
		protected void updateDBAndTblModel() {
			SQL_INSERT.electrode(electrode);				
			electrodeTblModel.setElectrodes(SQL_SELECT.electrodes((ElectrodeType) electrodeTypeModel.getSelectedItem()));
			electrodeTblModel.fireTableDataChanged();
		}
		
	}
	
	class TblMouseAdaptor extends MouseAdapter{
		 JTable table;
		 @Override
	    public void mouseClicked(MouseEvent mouseEvent){
	        if(mouseEvent.getClickCount()==2){
	        	 table =(JTable) mouseEvent.getSource();
	             Point point = mouseEvent.getPoint();
	             int row = table.rowAtPoint(point);
	             if (table.getSelectedRow() != -1 && row >= 0) {
	                CtrlDlgChangeElectrode ctrlDlgChangeElectrode = new CtrlDlgChangeElectrode((Electrode) electrodeTblModel.getValueAt(row, 0), electrodeTblModel);
	                ctrlDlgChangeElectrode.getDialog().setVisible(true);
	             }
	        }else if(SwingUtilities.isRightMouseButton(mouseEvent) == true){
	        	table =(JTable) mouseEvent.getSource();
	        	Point point = mouseEvent.getPoint();
	        	int row = table.rowAtPoint(point);
	        	if(table.getSelectedRow() == row) {
	        		Isynet isynet = new Isynet();
	        		Patient patient = isynet.getPatient();
	        		PopupMenu menu = new PopupMenu(patient, (Electrode) electrodeTblModel.getValueAt(row, 0), electrodeTblModel);
	                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	        	}
	        }
	        		
	    }
	}
	
	
	

	
	
	
	
	
}
