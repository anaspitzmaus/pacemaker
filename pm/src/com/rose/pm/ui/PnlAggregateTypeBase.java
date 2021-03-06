package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.MaterialType;
/**
 * an abstract panel that inherits the PnlBase and adds components for 
 * aggregate type panels that is:
 * Label and JComboBox for the Manufacturer, 
 * Label and JFormattedTextField for the Price
 * @author Ekki
 *
 */
public abstract class PnlAggregateTypeBase extends PnlBase {

	
	private static final long serialVersionUID = -6474362930880791370L;
	JLabel lblManufacturer;
	JComboBox<Manufacturer> cbxManufacturer;
	NumberFormat paymentFormat;
	JFormattedTextField ftxtPrice;
	JLabel lblPrice;
	
	protected void setLblPriceText(String txt) {
		lblPrice.setText(txt);
	}
	
	protected void setLblManufacturerText(String txt) {
		lblManufacturer.setText(txt);
	}
	/**
	 * Create the panel.
	 */
	public PnlAggregateTypeBase() {
		paymentFormat = DecimalFormat.getInstance();
		paymentFormat.setMinimumFractionDigits(2);
		paymentFormat.setMaximumFractionDigits(2);
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblPrice = new JLabel("lblPrice");
		lblPrice.setFont(font);
		
		ftxtPrice = new JFormattedTextField(paymentFormat);
		ftxtPrice.setFont(font);
		ftxtPrice.setColumns(10);
	}	
	
	
	protected void setManufacturerModel(ComboBoxModel<Manufacturer> l) {
		cbxManufacturer.setModel(l);
	}

	protected void setManufacturerRenderer(ListCellRenderer<Manufacturer> manufacturerRenderer) {
		cbxManufacturer.setRenderer(manufacturerRenderer);		
	}
	
	protected void addManufacturerListener(ItemListener l) {
		cbxManufacturer.addItemListener(l);		
	}
	
	protected void setManufacturerIndex(Integer index) {
		cbxManufacturer.setSelectedIndex(index);
	}
	
	protected void setTableStringRenderer(Class<String> colclass, TableCellRenderer renderer) {
		table.setDefaultRenderer(colclass, renderer);
	}

	protected void setTableIDRenderer(Class<? extends AggregateType> idClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(idClass, renderer);
	}	
	
	protected MaterialType getTableValueAt(int row, int column) {
		return (MaterialType) table.getValueAt(row, column);
	}

	protected void addTblRowSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);		
	}

	protected void addPriceChangeListener(PropertyChangeListener priceListener) {
		ftxtPrice.addPropertyChangeListener(priceListener);		
	}

	protected void setTblDoubleRenderer(Class<Double> doubleClass, TableCellRenderer renderer) {
		table.setDefaultRenderer(doubleClass, renderer);		
	}

}
