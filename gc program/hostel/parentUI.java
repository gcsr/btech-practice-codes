import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.io.EOFException;


public class parentUI extends JFrame
{
	private RandomAccessFile input;
	
	private JButton read;
	private JTextField idField;
	private JTextArea recordDisplayArea;
	File f;
    
    int i=0;	
		
	
	class up extends JPanel
	{
		up()
		{
			setLayout(new FlowLayout());
		
			read=new JButton("open");
			add(read);
			idField=new JTextField(10);
			add(idField);
		}	
	}
	public parentUI()
	{
		
		setLayout(new BorderLayout());
		
		up pp=new up();
		add(pp,BorderLayout.NORTH);
		
		recordDisplayArea=new JTextArea();
		JScrollPane scroller=new JScrollPane(recordDisplayArea);
		add(scroller,BorderLayout.CENTER);
	
		recordDisplayArea.setText("rno \t date\tidli\tpongal\tuthappam\tdosa\tvoda\tlemrice\trice\textraRs\textraItems\n");
		
		read.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				readRecord();
			}
		});
		
		
		
		setSize(1200,550);
		setVisible(true);
		
		
	}
	

	
	public void readRecord()
	  {
	  	System.out.println("reading records");
	  	
	  	try
	  	{
	  	
	  	File f=new File(idField.getText()+".dat");
	  	FileEditor fe=new FileEditor(f);
	  	int rno=1;
	  	
	  	RandomAccessAccountRecord record=fe.getRecord(1);
	  	
	  		while(record.getRecordNumber()!=0)
	  		{
	  			  			
	  			recordDisplayArea.append(record.getRecordNumber()+"\t"+
	  			record.getDate()+"\t"+record.getIdli()+"\t"+record.getPongal()+"\t"+record.getUthappam()+"\t"+record.getDosa()+
	  			"\t"+record.getVoda()+"\t"+record.getChapathi()+"\t"+record.getLemRice()+"\t"+record.getExtraRS()+
	  			"\t"+record.getExtraItems()+"\n");
	  			rno++;
	  			record=fe.getRecord(rno);	  			
  		  		
	  		}
	  		fe.closeFile();
	  		 		
	  		
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