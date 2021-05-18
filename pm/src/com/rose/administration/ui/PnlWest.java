package com.rose.administration.ui;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.rose.administration.Insurance;
import com.rose.pm.material.Material;

import net.miginfocom.swing.MigLayout;

public class PnlWest extends JPanel {


	private static final long serialVersionUID = -8183815217094082658L;
	JLabel lblInsurance;
	JLabel lblAggregate;
	private JList<Insurance> listInsurance;
	private JList<Material> listAggregate;
	
	public PnlWest() {
		setLayout(new MigLayout("", "[grow]", "[][grow][][grow]"));
		
		lblInsurance = new JLabel("lblInsurance");
		lblInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblInsurance, "cell 0 0");
		
		listInsurance = new JList<Insurance>();
		add(listInsurance, "cell 0 1,grow");
		
		lblAggregate = new JLabel("lblAggregate");
		lblAggregate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAggregate, "cell 0 2");
		
		listAggregate = new JList<Material>();
		add(listAggregate, "cell 0 3,grow");

	}
	
	protected void setLblInsuranceText(String txt) {
		lblInsurance.setText(txt);
	}
	
	protected void setLblAggregateText(String txt) {
		lblAggregate.setText(txt);
	}
	
	protected void setInsuranceListModel(DefaultListModel<Insurance> model) {
		listInsurance.setModel(model);
	}
	
	protected void setAggregateListModel(DefaultListModel<Material> model) {
		listAggregate.setModel(model);
	}
	
	
	
	

}
