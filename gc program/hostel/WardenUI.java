import javax.swing.*;
import java.io.*;



public class WardenUI extends JFrame
 {

        	private RandomAccessFile input;
	
	private JButton read;
	private JTextField idField;
	private JTextArea recordDisplayArea;
	File f;
    
    int i=0;	
		
	
	public WardenUI()
	{		
		recordDisplayArea=new JTextArea();
		JScrollPane scroller=new JScrollPane(recordDisplayArea);
		add(scroller);
	
		recordDisplayArea.setText("rno \t od no\tidli\tpongal\tuthappam\tdosa\tvoda\tlemrice\trice\textraRs\textraItems\n");
		readRecord();
		setSize(1200,550);
		setVisible(true);
		
		
	}
	

	
	public void readRecord()
	  {
	  	File f;
	  	System.out.println("reading records");
	  	
	  	try
	  	{
	  		for(int i=0;i<61;i++)
	  		{
	  			if(i<10)
	  			{
	  				f=new File("07701A050"+i+".dat");
	  			}
	  			else
	  				f=new File("07701A05"+i+".dat");
	  		  			
	  			FileEditor fe=new FileEditor(f);
	  			System.out.println("displaying records");			
	  	  		RandomAccessAccountRecord record=fe.sum();	  					
	  	
	  		  	recordDisplayArea.append(i+"\t"+
	  			"07701A05"+i+"\t"+record.getIdli()+"\t"+record.getPongal()+"\t"+record.getUthappam()+"\t"+record.getDosa()+
	  			"\t"+record.getVoda()+"\t"+record.getChapathi()+"\t"+record.getLemRice()+"\t"+record.getExtraRS()+
	  			"\t"+record.getExtraItems()+"\n");
	  		
	  		
  		  		
	  		
	  			fe.closeFile();
	  		
	  		}	 		
	  		
	  	}
	  	catch(EOFException e)
	  	{
	  		
	  		JOptionPane.showMessageDialog(this,"No More Records in File","End of File",JOptionPane.ERROR_MESSAGE);
	  	    
	  	}
	  
	  	catch(IOException e)
	  	{
	  		JOptionPane.showMessageDialog(this,"Error during reading File","Read Error",JOptionPane.ERROR_MESSAGE);
	  		System.exit(1);
	  	}
	  }
	
  
  
    
    
}