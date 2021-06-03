package com.rose.pm.ui;


import net.miginfocom.swing.MigLayout;

public class DlgChangeMonitorType extends DlgChangeType {

	
	private static final long serialVersionUID = 7711459387311239536L;

	public DlgChangeMonitorType() {
		setTitle("Monitormodel bearbeiten");	
		getContentPanel().setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		{
			
			getContentPanel().add(lblNotation, "cell 0 0,alignx left");
		}
		{
			
			getContentPanel().add(txtNotation, "cell 1 0,growx");
			
		}
		{
			
			getContentPanel().add(lblNotice, "cell 0 1,alignx left");
		}
		{
			
			getContentPanel().add(txtNotice, "cell 1 1,growx");
			
		}
		
		{			
			getContentPanel().add(lblPrice, "cell 0 2,alignx left,aligny bottom");
		}
		{
			
			getContentPanel().add(ftxtPrice, "cell 1 2,growx");
		}		

	}

	

}
