package com.sheetal.jcbir.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class MainClassFrame extends JFrame{
	UploadImage panel1;
	ImagesDisplay panel2;
	
        
	JTextField tf=new JTextField();
	public MainClassFrame(){
		super("Images");		 
		JTabbedPane tabpane=new JTabbedPane();		
			
		panel1=new UploadImage();
		panel1.setBackground(Color.CYAN);
		
		panel2=new ImagesDisplay(tf);
		panel2.setBackground(Color.CYAN);
		
		
		tabpane.addTab("Upload",null,panel1,"first panel");
		tabpane.addTab("Search",null,panel2,"second panel");
		
		add(tabpane);
		pack();

	}
}
