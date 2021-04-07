package com.rose.pm.ui;



import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ICD;
import com.rose.pm.material.ICD_Type;
import com.rose.pm.material.Status;
import com.rose.pm.ui.CtrlPnlPM.AggregateTblModel;
import com.rose.pm.ui.CtrlPnlPM.PMTypeTblCellEditor;
import com.rose.pm.ui.CtrlPnlPM.TblMouseAdaptor;

public class CtrlPnlICD extends CtrlPnlPM {
	
	
	@Override
	protected void createPanel() {
		panel = new PnlICD();
		panel.setName("ICD");	
		setListener();
		setModel();
		setEditor();
		setRenderer();
		((PnlICD)panel).setAggregateTypeSelectionIndex(-1);
		panel.setTblSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlICD)panel).addTblMouseAdaptor(tblMouseAdaptor);
	}
	
	
	
	@Override
	protected void setComponentLabeling() {
		((PnlICD)panel).setLblAggregatTypeText("ICD-Model:");
		((PnlICD)panel).setLblSerialNrText("Seriennummer:");
		((PnlICD)panel).setLblNoticeText("Bemerkung:");
		((PnlICD)panel).setBtnCreateText("Einfügen");
		((PnlICD)panel).setBtnDeleteText("Löschen");
		((PnlICD)panel).setBtnShowAllText("Alle Modelle");
	}
	
	@Override
	protected void setModel() {
		ArrayList<? extends AggregateType> aggregateTypes;
		try {
			aggregateTypes = SQL_SELECT.ICD_Kinds(null, "");
			aggregateTypeModel = new DefaultComboBoxModel<AggregateType>();
			 for (int i = 0; i < aggregateTypes.size(); i++) 
				 aggregateTypeModel.addElement(aggregateTypes.get(i));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		((PnlICD)panel).setAggregatTypeModel(aggregateTypeModel);
		
		try {
			aggregateTblModel = new AggregateTblModel(SQL_SELECT.icd((ICD_Type) aggregateTypeListener.pmType, "", Status.Lager));
			panel.setTblModel(aggregateTblModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ICD-Aggregate konnten nicht abgefragt werden" + e.getMessage(), "Hinweis", JOptionPane.WARNING_MESSAGE);	
		}
		
	}
	
	@Override
	protected void setRenderer() {
		super.setRenderer();
	}
	
	@Override
	protected void setListener() {
		createListener = new CreateListener();
		((PnlICD)panel).addCreateListener(createListener);
		//Listener for the JComboBox that displays the types of aggregates
		aggregateTypeListener = new AggregateTypeListener();
		((PnlICD)panel).addAggregateTypeListener(aggregateTypeListener);
		tblMouseAdaptor = new TblMouseAdaptor();
		((PnlICD)panel).addTblMouseAdaptor(tblMouseAdaptor);		
	}
	
	@Override
	protected void setEditor() {
		 editor = new Editor();
		 dateCellEditor = editor.new DateCellEditor();
		 ((PnlICD)panel).setDateCellEditor(dateCellEditor);
		 
		 pmTypeTblCellEditor = new ICDTypeTblCellEditor(aggregateTblModel);
		((PnlICD)panel).setPMTypeTblCellEditor(pmTypeTblCellEditor);
	 }
	
	
//	class AggregateTypeListener extends CtrlPnlPM.AggregateTypeListener{
//		
//		@Override
//		protected void updateTblModel() {
//			Status status;
//			try {
//				status = statusTblCellEditor.getSearchStatusListener().getStatus();
//			}catch(NullPointerException e) {
//				status = null;
//			}
//			try {
//				aggregateTblModel.setAggregats(SQL_SELECT.icd((ICD_Type)pmType, aggregateTblModel.searchNotation, status));			
//				aggregateTblModel.fireTableDataChanged();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		}		
//	}
	
		
	class CreateListener extends CtrlPnlPM.CreateListener{
		
		@Override
		protected void initiateAggregate() {
			pm = new ICD((ICD_Type) aggregateTypeModel.getSelectedItem());
		}
		
		@Override
		protected void updateDBAndTblModel() {
			Integer key = null;
			try {
				key = SQL_INSERT.icd((ICD)pm);	
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: CreateListener at CtrlPnlICD", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			if(key != null) {//if insertion was successful
				aggregateTblModel.setValueAt(Status.Lager, 0, 5);
				aggregateTblModel.setValueAt(aggregateTypeModel.getSelectedItem(), 0, 1);
				for(int i = 0; i < pmTypeTblCellEditor.getCbxPMTypeModel().getSize(); i++) {
					try {
						if(pmTypeTblCellEditor.getCbxPMTypeModel().getElementAt(i).getNotation().equals(((AggregateType)aggregateTypeModel.getSelectedItem()).getNotation())) {
							pmTypeTblCellEditor.getCbxPMTypeModel().setSelectedItem(pmTypeTblCellEditor.getCbxPMTypeModel().getElementAt(i));
						}
					}catch(NullPointerException e) {//if (monitorTypeTblCellEditor.getCbxMonitorTypeModel().getElementAt(i) == null
						//do nothing
					}
				}
				
				try {
					aggregateTblModel.setAggregats(SQL_SELECT.icd((ICD_Type) aggregateTypeModel.getSelectedItem(), (String)aggregateTblModel.getValueAt(0, 2), (Status)aggregateTblModel.getValueAt(0, 5)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			aggregateTblModel.fireTableDataChanged();
		}	
	}
	
	protected class ICDTypeTblCellEditor extends PMTypeTblCellEditor{

		private static final long serialVersionUID = -6752666468071864655L;

		public ICDTypeTblCellEditor(AbstractTableModel tblModel) {
			super(tblModel);			
		}		
		
		@Override
		protected void setAggregateTypeRenderer() {
			listPMTypeRenderer = new ListICDTypeRenderer();
		}

		@Override
		protected void setAggregateTypeListener() {
			searchPMTypeListener = new SearchICDTypeListener(this.tblModel);			
		}

		@Override
		protected void selectAggregateTypes() {
			try {
				pmTypes = SQL_SELECT.ICD_Kinds(null, "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	protected class SearchICDTypeListener extends SearchPMTypeListener{

		public SearchICDTypeListener(AbstractTableModel tblModel) {
			super(tblModel);			
		}	

		@Override
		protected void setAggregates() {
			try {
				((AggregateTblModel) tblModel).setAggregats(SQL_SELECT.icd((ICD_Type) tblModel.getValueAt(0, 1), (String)tblModel.getValueAt(0, 2), (Status)tblModel.getValueAt(0, 5)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}
	
	protected class ListICDTypeRenderer extends ListPMTypeRenderer{

		private static final long serialVersionUID = -6873350278090871909L;

		@Override
		protected void setAllAggregateText() {
			setText("Alle ICD-Modelle");
		}	
	}
	
	class TblMouseAdaptor extends CtrlPnlPM.TblMouseAdaptor{
		
		@Override
		 protected void initiateDialog() {
			 CtrlDlgChangeICD ctrlDlgChangeICD = new CtrlDlgChangeICD((ICD) aggregateTblModel.getValueAt(row, 0), aggregateTblModel);
             ctrlDlgChangeICD.getDialog().setVisible(true);
		 }
	}
	
}
