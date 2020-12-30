package com.rose.pm;

import java.awt.EventQueue;

import com.rose.pm.ui.CtrlFrmBase;


public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CtrlFrmBase ctrlFrmBase = new CtrlFrmBase();
					ctrlFrmBase.showFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
