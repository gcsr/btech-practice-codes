import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.io.File;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.EOFException;

public class CreditEnquiry extends JFrame
{
	private JButton open,credit,debit,zero,clear;
	JPanel bpanel;
	JTextArea recordDisplayArea;
	ObjectInputStream input;
	FileInputStream fileInput;
	private File fileName;
	private String accountType;
    private DecimalFormat twoDigits=new DecimalFormat("0.00");
    
	public static void main(String[] gcs)
	{
		new CreditEnquiry();
	}
	public CreditEnquiry()
	{
		super("Credit Enquiry Program");
		Container container=getContentPane();
		bpanel=new JPanel();
		open=new JButton("Open File");
		bpanel.add(open);
		open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		credit=new JButton("Credit Balances");
		bpanel.add(credit);
		debit=new JButton("Debit Balances");
		bpanel.add(debit);
		zero=new JButton("Zero Balances");
		bpanel.add(zero);

        clear=new JButton("clear");
        bpanel.add(clear);
        clear.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		try
        		{
        		fileInput.close();
        		input.close();
        		fileInput=new FileInputStream(fileName);
				input=new ObjectInputStream(fileInput);
        		}
        		catch(Exception ex)
        		{
        			System.out.println("sorry");
        		}
        	}
        });
		recordDisplayArea=new JTextArea();
		JScrollPane scroller=new JScrollPane(recordDisplayArea);
		container.add(scroller,BorderLayout.CENTER);
		container.add(bpanel,BorderLayout.SOUTH);
		credit.setEnabled(false);
		debit.setEnabled(false);
		zero.setEnabled(false);
		credit.addActionListener(new ButtonHandler());
		debit.addActionListener(new ButtonHandler());
		zero.addActionListener(new ButtonHandler());
		addWindowListener(new WindowAdapter()
	   {
		public void windowClosing(WindowEvent e)
		{

			closeFile();
				System.exit(0);
		}
	   });
	   pack();
	   setSize(600,250);
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

				try
				{
					if(input!=null)
					{
					  input.close();
					 } 
						
					fileInput=new FileInputStream(fileName);
					input=new ObjectInputStream(fileInput);
					
					open.setEnabled(false);
					credit.setEnabled(true);
					debit.setEnabled(true);
					zero.setEnabled(true);
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(this,"Error Opening File","Error",JOptionPane.ERROR_MESSAGE);
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
				System.exit(1);
		}
	}
	 public void readRecord()
	  {
	  	AccountRecord record;
	  	try
	  	{
	  		if(input!=null)
					{
					  input.close();
					 } 
						
					fileInput=new FileInputStream(fileName);
					input=new ObjectInputStream(fileInput);
			recordDisplayArea.setText("the records are:");
			
			while(true)
			{
	  			record=(AccountRecord)input.readObject();
	  			if(shouldDisplay(record.getBalance()))
	  			recordDisplayArea.append(record.getAccount()+"\t"+
	  			record.getFirstName()+"\t"+record.getLastName()+"\t"+twoDigits.format(record.getBalance())+"\n");

			}
			
             
          }   
	  	catch(EOFException e)
	  	{
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
	  private boolean shouldDisplay(double balance)
	  {
	  	if(accountType.equals("Credit Balances")&&balance<0)
	  		return true;
	  	if(accountType.equals("Debit Balances")&&balance>0)
	  		return true;
	  	if(accountType.equals("Zero Balances")&&balance==0)
	  		return true;
	  		return false;
	  }

	  private class ButtonHandler implements ActionListener
	  {
	  	public void actionPerformed(ActionEvent e)
	  	{
	  		accountType=e.getActionCommand();
	  		readRecord();
	  	}
	  }


}