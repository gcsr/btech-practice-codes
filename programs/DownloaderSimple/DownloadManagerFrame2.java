import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DownloadManagerFrame2 extends JFrame {
	
	GridBagConstraints constraints;
	GridBagLayout layout;
	JFileChooser fileChooser;
	JTextField toField;
	JTextField urlField ;
	JTextField threadField;
	String saveTo;
	MainThread2 mThread;
	//int noOfThreads;
	
	
	public DownloadManagerFrame2()
	{
		super( "DownLoader" );
		Insets insets=new Insets(1,1,1,1);
		layout = new GridBagLayout();                                     
		setLayout( layout ); // set frame layout                          
		constraints = new GridBagConstraints(); // instantiate constraints
		constraints.insets=insets;
		
		JLabel urlLabel=new JLabel("enter URL");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        
        //constraints.weighty=0.5;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(urlLabel, constraints ); // set constraints
        add(urlLabel); // add component
             
		
        // weightx and weighty for button1 are both 0: the default
        urlField = new JTextField( "url" );
        urlField.selectAll();
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth = 6; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight                 
        layout.setConstraints(urlField, constraints ); // set constraints
        add(urlField); // add component
                     
		
        
        JLabel threadsLabel=new JLabel("enter no OF Threads");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 2; // set gridy                              
        constraints.gridwidth =5; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight                 
        layout.setConstraints(threadsLabel, constraints ); // set constraints
        add(threadsLabel); // add component
        
        
        threadField = new JTextField( "threads" );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 5; // set gridx                           
        constraints.gridy = 2; // set gridy                              
        constraints.gridwidth = 3; // set gridwidth                    
        constraints.gridheight = 1; // set gridheight                 
        layout.setConstraints(threadField, constraints ); // set constraints
        add(threadField); // add component
        
        
        JLabel toLabel=new JLabel("To");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 3; // set gridy                              
        constraints.gridwidth = 1; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(toLabel, constraints ); // set constraints
        add(toLabel); // add component
        
        toField = new JTextField( "select" );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 3; // set gridy                              
        constraints.gridwidth = 5; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(toField, constraints ); // set constraints
        add(toField); // add component
        
        JButton chooseButton = new JButton( "choose" );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 6; // set gridx                           
        constraints.gridy = 3; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight     
        chooseButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event)
    		{
    				
    					System.out.println("in choose");
    				 
    			        fileChooser=new JFileChooser();
    			        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    			        int result=fileChooser.showSaveDialog(DownloadManagerFrame2.this);
    			        if(result==JFileChooser.CANCEL_OPTION)
    			        	return;
    			        
    			        toField.setText(fileChooser.getSelectedFile().getAbsolutePath());
    			        System.out.println("ending choose");
    			
    		}
    	
        	}
        );
        
        layout.setConstraints(chooseButton, constraints ); // set constraints
        add(chooseButton); // add component
        
       
        
        
        JLabel empLabel1=new JLabel(" 									");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 5; // set gridy                              
        constraints.gridwidth = 6; // set gridwidth                    
        constraints.gridheight = 4; // set gridheight                 
        layout.setConstraints(empLabel1, constraints ); // set constraints
        add(empLabel1); // add component
        
        
        
        
        JButton startButton = new JButton( "start" );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 10; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        startButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event)
    		{
    			
    				try
    				{
    					System.out.println("in start");
    					mThread=new MainThread2(urlField.getText(),toField.getText(),threadField.getText());
    					mThread.createMainFile();
    					mThread.createThreadFile();
    					mThread.runThreads();
    				}
    				catch(Exception ex)
    				{
    					ex.printStackTrace();
    					
    					return;
    				}
    			
    		}
    	
        	}
        );
       
       
        layout.setConstraints(startButton, constraints ); // set constraints
        add(startButton); // add component
        
        JButton pauseButton = new JButton( "pause" );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 2; // set gridx                           
        constraints.gridy = 10; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(pauseButton, constraints ); // set constraints
        add(pauseButton); // add component
        
        
        JButton stopButton = new JButton( "stop" );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx=0.5;
        //constraints.weighty=0.5;
        constraints.gridx = 4; // set gridx                           
        constraints.gridy = 10; // set gridy                              
        constraints.gridwidth = 2; // set gridwidth                    
        constraints.gridheight = 2; // set gridheight                 
        layout.setConstraints(stopButton, constraints ); // set constraints
        add(stopButton); // add component
        
        
                  
   }
}	
