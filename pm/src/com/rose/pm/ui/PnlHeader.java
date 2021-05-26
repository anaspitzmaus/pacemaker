package com.rose.pm.ui;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class PnlHeader extends JPanel {
		private static final long serialVersionUID = 1195262460750282513L;
	JLabel lblText;
	
	
	protected void setLblText(String txt) {
		this.lblText.setText(txt);
	}


	/**
	 * Create the panel.
	 */
	public PnlHeader() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new MigLayout("", "[grow]", "[][]"));
		
		lblText = new JLabel("Text");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblText, "cell 0 0");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(comboBox, "cell 0 1,growx");

	}

}
