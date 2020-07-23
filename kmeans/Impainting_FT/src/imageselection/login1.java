package imageselection;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;
import java.sql.*;

//import Main.*;
//import="java.sql.*,databaseconnection.*"



 public class login1 extends JFrame implements ActionListener



{


JLabel enterName;
JTextField name;
JButton login1,newuser;
String storeName;
String uname,pass,path,userid;

 JLabel l1,l2,l3,l4,l5,l6,l7;
  JTextField t1,t2;	 
	 JLabel n1;
	 
	  JPasswordField jp1;
	 JLabel title;
	
JLabel cs;
	 Container c;
		
	 ImageIcon ii;
	 ImageIcon i2;
	 	Socket c1;
	 
	 public login1()
	
	 {
	
		initializeComponent();
		
	 }
		
		
		private void initializeComponent()

	   {
		
	


	
	    title=new JLabel("<html><font color=White size=+2><strong><center><i>Hierarchical Super-Resolution-Based Inpainting</i></center></strong></font>");
	      l1=new JLabel("<html><font color=#FF69B4 size=+1><strong><center><i>User Name</i></center></strong></font>");
	       l2=new JLabel("<html><font color=#FF69B4 size=+1><strong><center><i>Password</i></center></strong></font>");
	       
	       
l3=new JLabel("<html><font color=#FF69B4 size=+1><strong><center><i>Super-Resolution Process</i></center></strong></font>");	        
		 login1=new JButton("login");
		 
		 login1.addActionListener(this);
		 
		 newuser=new JButton("New User?");
		
		 newuser.addActionListener(this);
		 
		  
		 c=getContentPane();
		 
		 c.setLayout(null);
		  t1=new JTextField(30);
		
		 
		 jp1 = new JPasswordField();
	 			
	//	c.add(cs);
		//	cs.setBounds(155,460,500,60);
		
		
		
	 	c.add(jp1);
		 c.add(l3);
	//	 c.add(imageLabel1);
		
		ImageIcon xl = new ImageIcon("inpaint.jpg");
	    l6=new JLabel(xl);
	    l6.setBounds(2, 340, 500, 197);		
		l6.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		l6.setForeground(new Color(64,0,0));
		l6.setBackground(new Color(154,150,200));
	 	c.add(l6);
		ImageIcon x2 = new ImageIcon("tech.jpg");
	    l7=new JLabel(x2);
	    l7.setBounds(528, 30, 100, 500);		
		l7.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		l7.setForeground(new Color(64,0,0));
		l7.setBackground(new Color(154,150,200));
	 	c.add(l7);
		
		
	    
	   
	  	 
	    c.add(title);
        c.add(l1);
	    c.add(l2);	
	    c.add(t1);
	    c.add(login1);
	    c.add(newuser);
		 
		 c.setBackground(new java.awt.Color(0,100,0));
         c.setLocation(300,300);
         setTitle("login");
         setSize(650,650);
         setVisible(true);
		
//	   	imageLabel1.setBounds(475,125,450,350);
	   
	
	
	
	
title.setBounds(65,30,500,60);
	l1.setBounds(150,160,125,25);
	l2.setBounds(150,210,125,25);
	
	l3.setBounds(490,530,155,75);
	
	t1.setBounds(260,160,180,30);
	t1.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		t1.setForeground(new Color(64,0,0));
		t1.setBackground(new Color(26,176,164));
		
   	jp1.setBounds(260,205,180,30);
   	jp1.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		jp1.setForeground(new Color(64,0,0));
		jp1.setBackground(new Color(26,176,164));
		
		
		login1.setBounds(298,250,70,30);
	    }
	    
	  
	   public void actionPerformed(ActionEvent ae)
	
	  	{
	  
	 
	 if(ae.getSource()==login1)
	   {
	   	
	   	if(t1.getText().equals(""))
	    	
	    	{
	        	        
	      	JOptionPane.showMessageDialog(null,"Enter UserName.","Login error",JOptionPane.ERROR_MESSAGE);
	    	
	    	}
	    	else if(jp1.getText().equals(""))
	    	     {
	    		     
	             JOptionPane.showMessageDialog(null,"Enter Password.","Login error",JOptionPane.ERROR_MESSAGE);
	             }
	             
	             
	             else
	             {
	             	
	             	
	             	if((t1.getText().equals("admin"))&&(jp1.getText().equals("admin")))
	             	{
	             	dispose();
	            try
    {
    Main g=new Main();
    g.show();
    }
    catch(Exception e)
    {}

  
	             
	             	}
	             	else
	             	{
	             		JOptionPane.showMessageDialog(null,"Enter Correct name and password.","Login error",JOptionPane.ERROR_MESSAGE);
	             	}
	           
	             	
	             	
	             	}
	 
	 
	   }
	    
	    }
		
	    
	   
	    public static void main(String ar[])
	    {
	    	login1 so=new login1();
	    }
	   
	 
	   	


	   }
	   
	   
	
