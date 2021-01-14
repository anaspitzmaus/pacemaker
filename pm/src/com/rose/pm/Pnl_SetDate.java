package com.rose.pm;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;


public class Pnl_SetDate extends JPanel {
	
	private static final long serialVersionUID = -941489489867032889L;
	private JFormattedTextField ftxtCalendar;
	private JLabel lblCalendar;
	private JLabel lblDate;
	protected LocalDate date;

	
	public JFormattedTextField getFtxtCalendar() {
		return ftxtCalendar;
	}

	public void setFtxtCalendar(JFormattedTextField ftxtCalendar) {
		this.ftxtCalendar = ftxtCalendar;
	}

	public JLabel getLblCalendar() {
		return lblCalendar;
	}

	public void setLblCalendar(JLabel lblCalendar) {
		this.lblCalendar = lblCalendar;
	}
	
	public void setLabelDateText(String text){
		lblDate.setText(text);
		
	}

	/**
	 * Create the panel.
		 */
	public Pnl_SetDate(String dateFormat, LocalDate ld, LocalDate minDate) {
		lblDate = new JLabel("Start:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblDate);
	
	
		DateFormat displayFormat = new SimpleDateFormat(dateFormat);
		DateFormatter displayFormatter = new DateFormatter(displayFormat);
		DateFormat editFormat = new SimpleDateFormat(dateFormat.replace('.', '/'));
		DateFormatter editFormatter = new DateFormatter(editFormat);
		DefaultFormatterFactory factory = new DefaultFormatterFactory(displayFormatter, displayFormatter, editFormatter);			
		ftxtCalendar = new JFormattedTextField(factory, DateMethods.ConvertLocalDateToDate(ld));
		ftxtCalendar.setColumns(10);
		ftxtCalendar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtCalendar.setEditable(false);
		add(ftxtCalendar);
	
	
		ImageIcon icon = new ImageIcon(getImage("images/calendar_green_01.png"));
		icon.getImage();
		lblCalendar = new JLabel(icon);
		//lblCalendar = new JLabel("Kalebder");
		lblCalendar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblCalendar);		
		
	}
	
	public Pnl_SetDate(DefaultFormatterFactory factory, LocalDate ld){
		lblDate = new JLabel("Start:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblDate);	
	
					
		ftxtCalendar = new JFormattedTextField(factory, ld);
		ftxtCalendar.setColumns(10);
		ftxtCalendar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(ftxtCalendar);
	
	
		ImageIcon icon = new ImageIcon(getImage("images/calendar_green_01.png"));
		icon.getImage();
		lblCalendar = new JLabel(icon);
		//lblCalendar = new JLabel("Kalebder");
		lblCalendar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblCalendar);
	}
	
	public Pnl_SetDate() {
		lblDate = new JLabel("Start:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblDate);
		ftxtCalendar = new JFormattedTextField();
		ftxtCalendar.setColumns(10);
		ftxtCalendar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(ftxtCalendar);
		
		
		
		//ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/calendar_green_01.png"));
		ImageIcon icon = new ImageIcon(getImage("images/calendar_green_01.png"));
		icon.getImage();
		lblCalendar = new JLabel(icon);
		lblCalendar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblCalendar);
	}
	
	
	
	public void addCalendarListener(MouseListener l){
		lblCalendar.addMouseListener(l);
	}
	
	public void addDateChangeListener(PropertyChangeListener l){
		ftxtCalendar.addPropertyChangeListener(l);
	}
	
	public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}

}
