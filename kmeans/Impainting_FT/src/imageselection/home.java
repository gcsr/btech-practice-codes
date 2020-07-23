package imageselection;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;
import java.sql.*;
 public class home extends JFrame implements ActionListener



{

	 JLabel title;
	JButton click;
JLabel l6;
	 
	 Container c;

	 
	 
	 public home()
	
	 {
	
		initializeComponent();
		
	 }
		
		
		private void initializeComponent()

	   {
		
	


	
	     title=new JLabel("<html><font color=Black size=+2><strong><center><i>Hierarchical Super-Resolution-Based Inpainting</i></center></strong></font>");
	      
		click=new JButton("Login");
		 
	// login.addActionListener(this);
		 
		  
		 c=getContentPane();
		 
		 c.setLayout(null);
	
	
	ImageIcon xl = new ImageIcon("super.jpg");
	    l6=new JLabel(xl);
	    l6.setBounds(110, 95, 400, 300);		
		l6.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		l6.setForeground(new Color(64,0,0));
		l6.setBackground(new Color(154,190,200));
	 	c.add(l6);
	    
	    c.add(click);
	  	 
	    c.add(title);
       
		 
		 c.setBackground(new java.awt.Color(255,231,186));
         c.setLocation(300,300);
         setTitle("home");
         setSize(650,600);
         setVisible(true);
		
//	   	imageLabel1.setBounds(475,125,450,350);
	   
	
		
		click.setBounds(230,450,110,30);
		click.addActionListener(this);
	title.setBounds(105,30,500,60);
	
	
	    }
	    
	  
	   public void actionPerformed(ActionEvent ae)
	
	  	{
	  	
	  
		 if(ae.getSource()==click)
	   {
	   	   dispose();       
		  new login1().show();
     				
       		}					
	   	
	   	
	   }
	    
	    
	    

	    
	   
	    public static void main(String ar[])
	    {
	    	home so=new home();
	    }
	   
	 
	   	


	   }
	   
	   
	
