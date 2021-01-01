package com.rose.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultFormatterFactory;

import net.miginfocom.swing.MigLayout;

public class DlgManufacturer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2802805532264066262L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblManufacturer;
	private JTextField txtNotation;
	private JTextField txtContact;
	JFormattedTextField ftxtMobil;
	JButton okButton;
	
	JButton btnCreate;
	JToggleButton tglMode;
	NumberFormat mobileFormat;
	
	
	

	protected JTable getTblManufacturer() {
		return tblManufacturer;
	}

	protected JTextField getTxtNotation() {
		return txtNotation;
	}

	protected JTextField getTxtContact() {
		return txtContact;
	}
	
	protected JFormattedTextField getFtxtMobile() {
		return ftxtMobil;
	}

	protected JButton getBtnCreate() {
		return btnCreate;
	}
	
	

	protected JToggleButton getTglMode() {
		return tglMode;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManufacturer dialog = new DlgManufacturer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgManufacturer() {
		setTitle("Hersteller");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setResizeWeight(0.3);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JPanel pnlManufacturer = new JPanel();
				splitPane.setLeftComponent(pnlManufacturer);
				pnlManufacturer.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
				{
					tglMode = new JToggleButton("Neu anlegen");
					tglMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(tglMode, "cell 0 0 2 1,growx");
				}
				{
					JLabel lblNotation = new JLabel("Name:");
					lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(lblNotation, "cell 0 1,alignx left");
				}
				{
					txtNotation = new JTextField();
					txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(txtNotation, "cell 1 1,alignx left");
					txtNotation.setColumns(20);
				}
				{
					JLabel lblAddress = new JLabel("Kontakt:");
					lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(lblAddress, "cell 0 2,alignx left");
				}
				{
					txtContact = new JTextField();
					txtContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(txtContact, "cell 1 2,alignx left");
					txtContact.setColumns(20);
				}
				{
					JLabel lblMobil = new JLabel("Mobil:");
					lblMobil.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(lblMobil, "cell 0 3,alignx left");
				}
				{
					
					//try {
//						try {
//							ftxtMobil = new JFormattedTextField(new MaskFormatter("###/########"));
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}

							/**
							 * 
							 */
//							private static final long serialVersionUID = 1L;
//							private static final String REGEX = "\\d{4,5}(/)\\d{7,8}";
//
//							@Override
//							public Object stringToValue(String arg0) throws ParseException {
//								System.out.println(arg0);
////								if(arg0 != null) {
////									Pattern p = Pattern.compile(REGEX);
////									Matcher m = p.matcher(arg0);   // get a matcher object
////									System.out.println(m.matches());
////									return arg0;
////								}else {
//									return arg0;
////								}
//								
//							}
//
//							@Override
//							public String valueToString(Object arg0) throws ParseException {
//								System.out.println(arg0);
//								if(arg0 != null) {
//									 
//								return arg0.toString();
//								}else {
//									
//								return null;
//								}
//								
//							}
//							
//						});
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					//mobileFormat = NumberFormat.getNumberInstance();
//					ftxtMobil = new JFormattedTextField(new AbstractFormatter() {
//						
//						@Override
//						public String valueToString(Object value) throws ParseException {
//							System.out.println(value);
//							return null;
//						}
//						
//						@Override
//						public Object stringToValue(String text) throws ParseException {
//							System.out.println(text);
//							return null;
//						}
//					});
					ftxtMobil = new JFormattedTextField();
					ftxtMobil.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(ftxtMobil, "cell 1 3,growx");
				}
				{
					btnCreate = new JButton("Hinzuf\u00FCgen");
					btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(btnCreate, "cell 1 4,alignx right");
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				splitPane.setRightComponent(scrollPane);
				{
					tblManufacturer = new JTable();
					tblManufacturer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(tblManufacturer);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Schlieﬂen");
				okButton.setActionCommand("Schlieﬂen");				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
		}
	}
	
	protected void addOKListener(ActionListener l) {
		okButton.addActionListener(l);
	}	
	
	protected void addNotationListener (DocumentListener l){
		txtNotation.getDocument().addDocumentListener(l);
	}
	
	protected void addContactPersonListener (DocumentListener l) {
		txtContact.getDocument().addDocumentListener(l);
	}
	
	protected void addMobilListener(PropertyChangeListener l) {
		ftxtMobil.addPropertyChangeListener(l);
	}
	
	protected void addCreateListener (ActionListener l) {
		btnCreate.addActionListener(l);
	}
	
	protected void setTableModel(AbstractTableModel model) {
		tblManufacturer.setModel(model);
	}
	
	protected void addTblRowSelectionListener (ListSelectionListener l) {
		tblManufacturer.getSelectionModel().addListSelectionListener(l);
	}
	
	protected void addModeListener(ItemListener l) {
		tglMode.addItemListener(l);
	}
	
	protected void setMobileFormatterFactory(DefaultFormatterFactory ff) {
		ftxtMobil.setFormatterFactory(ff);
	}

}
