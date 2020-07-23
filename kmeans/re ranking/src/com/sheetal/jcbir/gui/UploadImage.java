package com.sheetal.jcbir.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UploadImage extends JPanel{
	
	GridBagLayout layout;
	GridBagConstraints constraints;

	JLabel label1,label2,label3,label4,label5,label6,label7,label8;
	JTextField tField1,tField2,tField3,tField4,tField5,tField6;
	JFileChooser fileChooser=null;
	private String path="";
	public UploadImage(){
		
		layout = new GridBagLayout();			
		setLayout( layout ); // set frame layout                          
		constraints = new GridBagConstraints(); // instantiate constraints
		
		Insets insets=new Insets(5,5,5,5);
		constraints.insets=insets;
		
		//Border border=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.cyan,Color.GRAY,Color.black,Color.GRAY );		
        	
		
		label1=new JLabel("Image Name");
		
		constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        layout.setConstraints(label1, constraints ); // set constraints	        
        add(label1);	   
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
		tField1=new JTextField("Enter Name");
		layout.setConstraints(tField1, constraints ); // set constraints	        
        add(tField1);	
        
        
    
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 1; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label2=new JLabel("Image Color");
		layout.setConstraints(label2, constraints ); // set constraints	        
        add(label2);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 1; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        tField2=new JTextField("Enter Color");
		layout.setConstraints(tField2, constraints ); // set constraints	        
        add(tField2);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 2; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label3=new JLabel("Image Behaviour");
		layout.setConstraints(label3, constraints ); // set constraints	        
        add(label3);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 2; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        tField3=new JTextField("Enter Behaviour");
		layout.setConstraints(tField3, constraints ); // set constraints	        
        add(tField3);	
    
    
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 3; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label4=new JLabel("Image Type");
		layout.setConstraints(label4, constraints ); // set constraints	        
        add(label4);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 3; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        tField4=new JTextField("Enter Type");
		layout.setConstraints(tField4, constraints ); // set constraints	        
        add(tField4);	
    
    
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 4; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label5=new JLabel("Category");
		layout.setConstraints(label5, constraints ); // set constraints	        
        add(label5);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 4; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    

        tField5=new JTextField("Category");
		layout.setConstraints(tField5, constraints ); // set constraints	        
        add(tField5);	
    
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 5; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label6=new JLabel("Upload Image");
		layout.setConstraints(label6, constraints ); // set constraints	        
        add(label6);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 5; // set gridy                              
        constraints.gridwidth = 1; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	
        JButton chooseButton=new JButton("Choose");
        layout.setConstraints(chooseButton, constraints);
        add(chooseButton);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 3; // set gridx                           
        constraints.gridy = 5; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label7=new JLabel("hi");
		layout.setConstraints(label7, constraints ); // set constraints	        
        add(label7);	
        
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 6; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    
        
	
        label8=new JLabel("Image Id");
		layout.setConstraints(label8, constraints ); // set constraints	        
        add(label8);	
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 6; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	        
    

        tField6=new JTextField("Image Id");
		layout.setConstraints(tField6, constraints ); // set constraints	        
        add(tField6);	

        
        
    
    
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0;	        
        constraints.weighty=0;
        
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 7; // set gridy                              
        constraints.gridwidth = 4; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight	
        
        JButton uploadButton=new JButton("Upload");
        layout.setConstraints(uploadButton, constraints);
        add(uploadButton);
        
        
            
        
        
        chooseButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		updatePath();
        	}
        });
        
        uploadButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		uploadImage();
        	}
        });
		
	}
	
	private void updatePath(){
		JFileChooser fileChooser=new JFileChooser();
  		
  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  		int result=fileChooser.showOpenDialog(null);
  		if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  		path=(fileChooser.getSelectedFile().getAbsolutePath());
  		label7.setText(path);
  		
	}
	
	private void uploadImage(){
		try{
			
			int imageid=Integer.parseInt(tField6.getText());
			
			String tags="";
			tags+=tField1.getText()+" "+tField2.getText()+" "+tField3.getText()+" "+tField4.getText()+" "+tField5.getText();
			Connection connection=DatabaseConnection.getConnection("karna","root","");
			String insertQuery="insert into Images values(?,?,?)";
			PreparedStatement pStmt=connection.prepareStatement(insertQuery);
			pStmt.setInt(1, imageid);
			pStmt.setString(2, tags);
			pStmt.setString(3, path);
			
			pStmt.executeUpdate();
			DatabaseConnection.closeConnection(connection);
			
			JOptionPane.showMessageDialog(this,"Image updated to database");
		
			//pStmt.setInt(1,);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
