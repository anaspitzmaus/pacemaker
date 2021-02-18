package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.AbstractTableModel;

import com.rose.person.Patient;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Material;
import com.rose.pm.material.Status;


/**
 * popupMenu to provide selected material to the active patient
 * @author Ekki
 *
 */
public class PopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -2826141447212609880L;

	JMenuItem itemProvide;
	ProvideListener provideListener;
	Patient patient;
	Material material;
	AbstractTableModel model;
	
	
	
    public PopupMenu(Patient patient, Material material, AbstractTableModel model) {
    	this.patient = patient;
    	this.material = material;
    	this.model = model;
    	if(material.getPatient() instanceof Patient) {
    		itemProvide = new JMenuItem("Bereitstellung lösen");
    	}else {
    		itemProvide = new JMenuItem("bereitstellen");
    	}
        add(itemProvide);
        setListener();
        
    }
    
   
    
    private void setListener() {
    	provideListener = new ProvideListener();
    	itemProvide.addActionListener(provideListener);
    }
    
    class ProvideListener implements ActionListener{
    	   	
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(material instanceof Material) {
				if(material.getPatient() instanceof Patient) {
					//delete the provided status
					deleteProvided();
				}else if(patient instanceof Patient) {
					//set the material provided to the patient
					provideMaterial();
				}
				try {
					SQL_UPDATE.setPatProvided(material);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
		}
		
		private void deleteProvided() {
			material.setPatient(null);
			material.setStatus(Status.Lager);
			model.fireTableDataChanged();			
		}
		
		 private void provideMaterial() {
		    	//insert patient to database if not already exists
				try {
					Integer patId = SQL_INSERT.patient(patient);
					if(patId instanceof Integer) {
						material.setPatient(patient);
						material.setStatus(Status.Bereitgestellt);
						model.fireTableDataChanged();
						
						//update database that material is provided for a patient
					}
				} catch (SQLIntegrityConstraintViolationException e1) {
					
					//Patient always exists at database
					//get the id of the patient and set the id to the patient
					try {
						patient = SQL_SELECT.patient(patient.getNumber());
						material.setPatient(patient);
						material.setStatus(Status.Bereitgestellt);
						model.fireTableDataChanged();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
										
		    }
    	
    }
    
   
}
