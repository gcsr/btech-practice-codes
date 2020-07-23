import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import javax.swing.JOptionPane;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;

public class ReadSequentialFile extends JFrame
{
	BankUI userInterface;
	JButton open,next;
	ObjectInputStream input;
	public static void main(String[] gcs)
	{
		new ReadSequentialFile();
	}
	public ReadSequentialFile()
	{
		super("Reading a Sequential File of Object");
		userInterface=new BankUI();
		getContentPane().add(userInterface,BorderLayout.CENTER);
		
		open=userInterface.getDoTask1Button();
		next=userInterface.getDoTask2Button();
		open.setText("Open File");
		
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
				if(input!=null)
					closeFile();
			}
		});
		next.setText("Next");
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				readRecord();
			}
		});
		pack();
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
					input=new ObjectInputStream(new FileInputStream(fileName));
					open.setEnabled(false);
					next.setEnabled(true);
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(this,"Error Opening File","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		

	}
		public void closeFile()
	   {
			try{
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
	  	AccountRecord record;
	  	try
	  	{
	  		record=(AccountRecord)input.readObject();
	  		String values[]={String.valueOf(record.getAccount()),record.getFirstName(),record.getLastName(),String.valueOf(record.getBalance())};
	  		userInterface.setFieldValues(values);
	  	}
	  	catch(EOFException e)
	  	{
	  		next.setEnabled(false);
	  		JOptionPane.showMessageDialog(this,"No More Records in File","End of File",JOptionPane.ERROR_MESSAGE);
	  	}
	  	catch(ClassNotFoundException e)
	  	{
	  		JOptionPane.showMessageDialog(this,"Unable to Create Object","Class Not Found",JOptionPane.ERROR_MESSAGE);
	  	}
	  	catch(IOException e)
	  	{
	  		JOptionPane.showMessageDialog(this,"Error during reading File","Read Error",JOptionPane.ERROR_MESSAGE);
	  	}
	  }
}