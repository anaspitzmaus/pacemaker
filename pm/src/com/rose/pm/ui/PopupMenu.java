package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.rose.person.Patient;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.material.Material;

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
	
    public PopupMenu(Patient patient, Material material) {
    	this.patient = patient;
    	this.material = material;
        itemProvide = new JMenuItem("bereitstellen");
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
			if(patient instanceof Patient && material instanceof Material) {
				
				//insert patient to database if not already exists
				try {
					SQL_INSERT.patient(patient);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				material.setPatient(patient);
				//
			}
			
		}
    	
    }
}
