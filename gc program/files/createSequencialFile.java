
import javax.swing.JFrame;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.IOException;

public class createSequencialFile extends JFrame
{
	private BankUI userinterface;
	private ObjectOutputStream output;
	private JButton enter,open;

	public static void main(String[] gcs)
	{
		new createSequencialFile();

	}
	public createSequencialFile()
	{
		super("creating a sequential file of objects");

		userinterface=new BankUI();
		getContentPane().add(userinterface,BorderLayout.CENTER);
		open=userinterface.getDoTask1Button();
		open.setText("save into file");

		open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		
		enter=userinterface.getDoTask2Button();
		enter.setText("enter");
		enter.setEnabled(false);
		enter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRecord();
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
	setSize(300,200);
	setVisible(true);

   }
   
	
	public void openFile()
	{
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result=fileChooser.showSaveDialog(this);
		
		if(result==JFileChooser.CANCEL_OPTION)
			return;
			
		File fileName=fileChooser.getSelectedFile()	;
		if(fileName==null||fileName.getName().equals(""))
			JOptionPane.showMessageDialog(this,"Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
			else
			{
				try
				{
					output=new ObjectOutputStream(new FileOutputStream(fileName));
					open.setEnabled(false);
					enter.setEnabled(true);
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(this,"Error Opening File","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		

	}
	public void addRecord()
	{
		int accountNumber=0;
		AccountRecord record;
		
		String fieldValues[]=userinterface.getFieldValues();
		if(!fieldValues[0].equals(""))
		{
			try
			{
				accountNumber=Integer.parseInt(fieldValues[0]);
				if(accountNumber>0)
				{
					record=new AccountRecord(accountNumber,fieldValues[1],fieldValues[2],Double.parseDouble(fieldValues[3]));
					output.writeObject(record);
					output.flush();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"AccountNumber must be >0","Bad AccountNumber",JOptionPane.ERROR_MESSAGE);
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
	public void closeFile()
	{
		try{
			output.close();
			System.exit(0);
		}
	
	catch(IOException e)
		{
				JOptionPane.showMessageDialog(this,"Error Closing File","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

