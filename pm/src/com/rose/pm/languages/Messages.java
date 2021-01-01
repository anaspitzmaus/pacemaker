package com.rose.pm.languages;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * @author Ekkehard Rose
 * changes Text with regard to the language
 * caution: when calling this method, be sure to have imported the right (this) class!
 */
public class Messages {
	
	private static String baseName = "languages.messages";
	private static ResourceBundle bundle;
	
	public static String getString(String key){
		try{
			bundle = ResourceBundle.getBundle(baseName);
			return bundle.getString(key);
		}catch(MissingResourceException e){
			return key;		
		}
	}	
}
