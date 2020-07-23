import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.io.EOFException;


public class ReadRandomFile extends JFrame
{
	private RandomAccessFile input;
	private BankUI userInterface;
	private JButton next,open;
    
    int i=0;	
		
	public static void main(String[] gcs)
	{
		new ReadRandomFile();
	}
	public ReadRandomFile()
	{
		super("write to random access file");
		
		userInterface=new BankUI(4);
		getContentPane().add(userInterface);
		open=userInterface.getDoTask1Button();
		open.setText("Open File for Reading");
		
		open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				
					closeFile();
			}
		});
		next=userInterface.getDoTask2Button();
		next.setText("Next");
		next.setEnabled(false);
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				readRecord();
			}
		});
		
		setSize(300,150);
		setVisible(true);
		
	}
	
	private void openFile()
	{
			JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result=fileChooser.showOpenDialog(this);

		if(result==JFileChooser.CANCEL_OPTION)
			return;

		File fileName=fileChooser.getSelectedFile()	;
		if(fileName==null||fileName.getName().equals(""))
			JOptionPane.showMessageDialog(this,"Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
		else
			try
			{
			    input=new RandomAccessFile(fileName,"r");
				next.setEnabled(true);
				open.setEnabled(false);
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(this,"File does not exist","Invalid  FileName",JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
	}
	private void closeFile()
	{
		try{
					if(input!=null);
					input.close();
					System.exit(0);
				}
	
			catch(IOException e)
				{
					JOptionPane.showMessageDialog(this,"Error Closing File","Error",JOptionPane.ERROR_MESSAGE);
				}
	}
	public void readRecord()
	  {
	  	RandomAccessAccountRecord record=new RandomAccessAccountRecord();
	  	try
	  	{
	  		do
	  		{
	  			System.out.println(record.getAccount());
	  			System.out.println(i);
	  			i++;
	  			record.read(input);
	  		}while(record.getAccount()==0);	
	  		System.out.println("writing "+i);
	  	
	  		String values[]={String.valueOf(record.getAccount()),
	  		record.getFirstName(),record.getLastName(),
	  		String.valueOf(record.getBalance())};
	  		
	  		userInterface.setFieldValues(values);
	  	}
	  	catch(EOFException e)
	  	{
	  		
	  		JOptionPane.showMessageDialog(this,"No More Records in File","End of File",JOptionPane.ERROR_MESSAGE);
	  		closeFile();
	  	}
	  
	  	catch(IOException e)
	  	{
	  		JOptionPane.showMessageDialog(this,"Error during reading File","Read Error",JOptionPane.ERROR_MESSAGE);
	  		System.exit(1);
	  	}
	  }
	
}