package com.rose.pm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import com.toedter.calendar.JCalendar;

/**
 * a dialog with embedded calendar to choose a day and set the selected day to a JFormattedTextField
 * @author Ekkehard Rose
 * @version 1.0 *
 */
public class Dlg_Calendar extends JDialog {

	
	private static final long serialVersionUID = -4664222001108078494L;
	private JPanel contentPanel = new JPanel();
	protected JCalendar calendar;
	final JDialog myDialog;
	

	/**
	 * standard constructor
	 * @param ftxt the JFormattedTextField that receives the selected day-value
	 */
	public Dlg_Calendar(JFormattedTextField ftxt, Date minDate) {
		DefaultFormatterFactory f = (DefaultFormatterFactory) ftxt.getFormatterFactory();
		DateFormatter df = (DateFormatter) f.getDisplayFormatter();
		SimpleDateFormat sdf = (SimpleDateFormat) df.getFormat();
			
		
		myDialog = this;
		setModal(true);		
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setSize(450, 350);
		getContentPane().setLayout(new BorderLayout());
		((JPanel)getContentPane()).setBorder(new LineBorder(new Color(0, 0, 0), 2, true)); //add a border to the contentPane
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			calendar = new JCalendar();
			calendar.setFont(new Font("Dialog", Font.PLAIN, 16));
			if(sdf.toPattern().equals("MM.yyyy")){//if days are not shown in formattedTextField
				calendar.getDayChooser().removeAll();//remove the daypicker in the calendar 
			}
			
			//set the date of calendar only if a date is given in the formatted textField
			if(ftxt.getValue() instanceof Date){
				calendar.setDate((Date)ftxt.getValue());
			}
			calendar.setMinSelectableDate(minDate);
			contentPanel.add(calendar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				//OK-Button returns the selected day to the JFormattedTextField
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int day = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
						int month = calendar.getCalendar().get(Calendar.MONTH);
						int year = calendar.getCalendar().get(Calendar.YEAR);	
						Calendar c = Calendar.getInstance();
						c.set(year, month, day);
						Date seldate = c.getTime();
						ftxt.setValue(seldate); //set the selected date to the formattedTextField
						myDialog.dispose();	//close the dialog
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						myDialog.dispose();
						
					}
				});
			}
		}
		setUndecorated(true);
		pack();
	}

}
