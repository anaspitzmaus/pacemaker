package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.rose.person.Patient;

/**
 * popupMenu to provide selected material to the active patient
 * @author Ekki
 *
 */
public class PopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -2826141447212609880L;

	JMenuItem itemProvide;
	ProvideListener provideListener;
	
    public PopupMenu(Patient patient) {
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
			
			
		}
    	
    }
}
