import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class WriteRandomFile extends JFrame
{
	private RandomAccessFile output;
	private BankUI userInterface;
	private JButton enter,open;
	
	private static final int NUMBER_RECORDS=100;
	
	public static void main(String[] gcs)
	{
		new WriteRandomFile();
	}
	public WriteRandomFile()
	{
		super("write to random access file");
		
		userInterface=new BankUI(4);
		getContentPane().add(userInterface,BorderLayout.CENTER);
		open=userInterface.getDoTask1Button();
		open.setText("open");
		
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
				if(output!=null)
				addRecord();
					closeFile();
			}
		});
		enter=userInterface.getDoTask2Button();
		enter.setText("Enter");
		enter.setEnabled(false);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRecord();
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
			JOptionPane.showMessageDialog(null,"Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
		
		else
		{
			try
			{
			    output=new RandomAccessFile(fileName,"rw");
				enter.setEnabled(true);
				open.setEnabled(false);
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(this,"File does not exist","Invalid  FileName",JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}	
	}
	private void closeFile()
	{
		try{
					if(output!=null);
					output.close();
					System.exit(0);
				}
	
			catch(IOException e)
				{
					JOptionPane.showMessageDialog(this,"Error Closing File","Error",JOptionPane.ERROR_MESSAGE);
				}
	}
	private void addRecord()
	{
		int accountNumber=0;
		
		
		String fieldValues[]=userInterface.getFieldValues();
		if(!fieldValues[0].equals(""))
		{
			try
			{
				accountNumber=Integer.parseInt(fieldValues[0]);
				if(accountNumber>0&&accountNumber<=NUMBER_RECORDS)
				{
					System.out.println("writing");
					RandomAccessAccountRecord record=new RandomAccessAccountRecord();
					record.setAccount(accountNumber);
					record.setFirstName(fieldValues[1]);
					record.setLastName(fieldValues[2]);
					record.setBalance(Double.parseDouble(fieldValues[3]));
					
					output.seek((accountNumber-1)*RandomAccessAccountRecord.SIZE);
					
					record.write(output);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"AccountNumber must be >0 and <100","Bad AccountNumber",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(this,"Bad AccountNumber or balance","Number Format Invalid",JOptionPane.ERROR_MESSAGE);
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(this,"Error Writing to File","IO Exception",JOptionPane.ERROR_MESSAGE);
				closeFile();
			}
			
		}
	}
	
}