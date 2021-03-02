package com.rose.pm;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class MyImages {
	public Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    System.out.println( Thread.currentThread().getContextClassLoader());
	    return Toolkit.getDefaultToolkit().getImage(url);
	}
}
