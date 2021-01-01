package com.rose.pm.db;

import java.io.InputStream;

/**
 * this is a very small class, created an hour before finishing the master thesis
 * to enable the system to start even if the database isn't created 
 * was implemented at the EKA while being on charge 
 * @author Ekkehard Rose
 * @version 1.0
 *
 */
public class Script {
	
	public InputStream getFilePath(){
		InputStream is = null;
		try{			
			is = this.getClass().getResourceAsStream("kgp.sql");
		}catch(Exception e){
			
		}
		return is;
	}
}
