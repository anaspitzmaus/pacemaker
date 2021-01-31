package com.rose.pm.ui.implant;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PnlMain extends JPanel {
	private JTextField txtAggregate;
	private JTextField txtElectrodeRA;
	private JButton btnSearchAggregate;
	private JButton btnSearchElectrodeRA;
	private JLabel lblElectrodeRV;
	private JTextField txtElectrodeRV;
	private JButton btnSearchElectrodeRV;
	private JLabel lblElectrodeLV;
	private JTextField txtElectrodeLV;
	private JButton btnSearchElectrodeLV;
	private JLabel lblAggregateManufacturer;
	private JTextField txtAggregateManufacturer;
	private JLabel lblAggregateSerialNr;
	private JTextField txtAggregateSerialNr;
	private JLabel lblAggLoc;
	private JComboBox cbxAggLoc;
	private JComboBox cbxElRALoc;
	private JComboBox cbxElRVLoc;
	private JComboBox cbxElLVLoc;
	private JLabel lblElRALoc;
	/**
	 * Create the panel.
	 */
	public PnlMain() {
		setLayout(new MigLayout("", "[][grow][][grow][][grow][][grow][][]", "[][][][][]"));
		
		JLabel lblAggregate = new JLabel("lblAggregate");
		lblAggregate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAggregate, "cell 0 0,alignx left");
		
		txtAggregate = new JTextField();
		txtAggregate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtAggregate, "cell 1 0,growx");
		txtAggregate.setColumns(10);
		
		lblAggregateManufacturer = new JLabel("lblAggregateManufacturer");
		lblAggregateManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAggregateManufacturer, "cell 2 0,alignx trailing");
		
		txtAggregateManufacturer = new JTextField();
		txtAggregateManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtAggregateManufacturer, "cell 3 0,growx");
		txtAggregateManufacturer.setColumns(10);
		
		lblAggregateSerialNr = new JLabel("lblAggregateSerialNr");
		lblAggregateSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAggregateSerialNr, "cell 4 0,alignx trailing");
		
		txtAggregateSerialNr = new JTextField();
		txtAggregateSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtAggregateSerialNr, "cell 5 0,growx");
		txtAggregateSerialNr.setColumns(10);
		
		lblAggLoc = new JLabel("lblAggLoc");
		lblAggLoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAggLoc, "cell 6 0,alignx left");
		
		cbxAggLoc = new JComboBox();
		cbxAggLoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxAggLoc, "cell 7 0,growx");
		
		btnSearchAggregate = new JButton();
		
			
		btnSearchAggregate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnSearchAggregate, "cell 8 0");
		
		JLabel lblElectrodeRA = new JLabel("lblElectrodeRA");
		lblElectrodeRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblElectrodeRA, "cell 0 1,alignx center");
		
		txtElectrodeRA = new JTextField();
		txtElectrodeRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtElectrodeRA, "cell 1 1,growx");
		txtElectrodeRA.setColumns(10);
		
		lblElRALoc = new JLabel("lblElRALoc");
		lblElRALoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblElRALoc, "cell 6 1,alignx left");
		
		cbxElRALoc = new JComboBox();
		cbxElRALoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxElRALoc, "cell 7 1,growx");
		
		btnSearchElectrodeRA = new JButton();
		btnSearchElectrodeRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnSearchElectrodeRA, "cell 8 1");
		
		lblElectrodeRV = new JLabel("lblElectrodeRV");
		lblElectrodeRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblElectrodeRV, "cell 0 2,alignx left");
		
		txtElectrodeRV = new JTextField();
		txtElectrodeRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtElectrodeRV, "cell 1 2,growx");
		txtElectrodeRV.setColumns(10);
		
		cbxElRVLoc = new JComboBox();
		cbxElRVLoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxElRVLoc, "cell 7 2,growx");
		
		btnSearchElectrodeRV = new JButton();
		btnSearchElectrodeRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnSearchElectrodeRV, "cell 8 2");
		
		lblElectrodeLV = new JLabel("lblElectrodeLV");
		lblElectrodeLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblElectrodeLV, "cell 0 3,alignx left");
		
		txtElectrodeLV = new JTextField();
		txtElectrodeLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtElectrodeLV, "cell 1 3,growx");
		txtElectrodeLV.setColumns(10);
		
		cbxElLVLoc = new JComboBox();
		cbxElLVLoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxElLVLoc, "cell 7 3,growx");
		
		btnSearchElectrodeLV = new JButton("");
		btnSearchElectrodeLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnSearchElectrodeLV, "cell 8 3");

	}
	
	protected void setBtnElectrodeRAIcon(ImageIcon icon){
		btnSearchElectrodeRA.setIcon(icon);
	}
	
	protected void setBtnAggregateIcon(ImageIcon icon) {
		btnSearchAggregate.setIcon(icon);
	}
	
	protected void setBtnElectrodeRVIcon(ImageIcon icon) {
		btnSearchElectrodeRV.setIcon(icon);
	}
	
	protected void setBtnElectrodeLVIcon(ImageIcon icon) {
		btnSearchElectrodeLV.setIcon(icon);
	}
	
	public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}

}
