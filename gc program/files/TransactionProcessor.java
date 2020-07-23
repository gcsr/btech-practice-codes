import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TransactionProcessor extends JFrame
{
	BankUI userInterface;
	private JMenuItem newItem,updateItem,deleteItem,openItem,exitItem;
	private JTextField fields[];
	private JTextField accountField,transactionField;
	private JButton actionButton,cancelButton;
	private FileEditor dataFile;
	private RandomAccessAccountRecord record;
	
	public static void main(String[] gcs)
	{
		new TransactionProcessor();
	}
	
	public TransactionProcessor()
	{
		super("Transaction Processor");
		
		userInterface=new BankUI(5);
		
		getContentPane().add(userInterface);
		userInterface.setVisible(false);
		
		actionButton=userInterface.getDoTask1Button();
		actionButton.setText("Save Changes");
		
		actionButton.setEnabled(false);
		
		actionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String action=e.getActionCommand();
				performAction(action);
			}
		});
		
		cancelButton=userInterface.getDoTask2Button();
		cancelButton.setText("Cancel");
		cancelButton.setEnabled(false);
		
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				userInterface.clearFields();
			}
		});
		
		
		fields=userInterface.getFields();
		
		accountField=fields[0];
		
		accountField.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("accountfield");
					displayRecord("0");
					
				}
			});
			
			transactionField=fields[4];
			
			
			
			transactionField.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					displayRecord(transactionField.getText());
					System.out.println(transactionField.getText());
				}
			});
			
			JMenuBar menuBar=new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu fileMenu=new JMenu("File");
			menuBar.add(fileMenu);
			
			newItem=new JMenuItem("New Record");
			newItem.setEnabled(false);
			
			newItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						fields[0].setEnabled(true);
						fields[1].setEnabled(true);
						fields[2].setEnabled(true);
						fields[3].setEnabled(true);
						fields[4].setEnabled(false);
						
						actionButton.setEnabled(true);
						
						actionButton.setText("Create");
						
						cancelButton.setEnabled(true);
						
						userInterface.clearFields();
					}
				});
				
				fileMenu.add(newItem);
				
				
			updateItem=new JMenuItem("update Record");
			updateItem.setEnabled(false);
			
			updateItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						fields[0].setEnabled(true);
						fields[1].setEnabled(false);
						fields[2].setEnabled(false);
						fields[3].setEnabled(false);
						fields[4].setEnabled(true);
						
						actionButton.setEnabled(true);
						
						actionButton.setText("Update");
						
						cancelButton.setEnabled(true);
						
						userInterface.clearFields();
					}
				});
				
				fileMenu.add(updateItem);	
				
			openItem=new JMenuItem("New/Open File");
			
			
			openItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(!openFile())
						return;
						
						newItem.setEnabled(true);
						updateItem.setEnabled(true);
						deleteItem.setEnabled(true);
						openItem.setEnabled(false);
						
						userInterface.setVisible(true);
						fields[0].setEnabled(false);
						fields[1].setEnabled(false);
						fields[2].setEnabled(false);
						fields[3].setEnabled(false);
						fields[4].setEnabled(false);
						
						
						
						actionButton.setText("Create");
						
						cancelButton.setEnabled(true);
						
						userInterface.clearFields();
					}
				});
				
				
				
			deleteItem=new JMenuItem("delete");
			deleteItem.setEnabled(false);
			
			deleteItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{										
						fields[0].setEnabled(true);
						fields[1].setEnabled(false);
						fields[2].setEnabled(false);
						fields[3].setEnabled(false);
						fields[4].setEnabled(false);
						
						actionButton.setEnabled(true);
						
						actionButton.setText("Delete");
						
						cancelButton.setEnabled(true);
						
						userInterface.clearFields();
					}
				});
				
				fileMenu.add(deleteItem);
				fileMenu.add(openItem);
					
				
				
				
			exitItem=new JMenuItem("Exit");
			
			
			exitItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							dataFile.closeFile();
						}
						catch(IOException ev)
						{
							JOptionPane.showMessageDialog(TransactionProcessor.this,"Error Closing File","IO Error",JOptionPane.ERROR_MESSAGE);
							
						}
						finally{System.exit(0);
						}
					}
				});
				
				fileMenu.addSeparator();
				fileMenu.add(exitItem);	
					
				
				setSize(400,250);
				setVisible(true);
			
			
	}
	
	private boolean openFile()
	{
		
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result=fileChooser.showOpenDialog(this);
		
		if(result==JFileChooser.CANCEL_OPTION)
			return false;
			
		File fileName=fileChooser.getSelectedFile()	;
		if(fileName==null||fileName.getName().equals(""))
		{	
			JOptionPane.showMessageDialog(this,"Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
			return false;
		}
				try
				{
					dataFile=new FileEditor(fileName);
				
				}
				catch(IOException e)
				{
					JOptionPane.showMessageDialog(this,"Error Opening File","Error",JOptionPane.ERROR_MESSAGE);
					return false;
				}
		return true;	
	}
	
	
	private void displayRecord(String transaction)
	{
		try
		{
			System.out.println("displayrecord");
			int accountNumber=Integer.parseInt(userInterface.getFieldValues()[0]);
			
			RandomAccessAccountRecord record=dataFile.getRecord(accountNumber);
			
			if(record.getAccount()==0)
			JOptionPane.showMessageDialog(this,"Account Doesn't Exist","Bad Account Number",JOptionPane.ERROR_MESSAGE);
			
			double change=Double.parseDouble(transaction);
			
			String[] values={String.valueOf(record.getAccount()),
			record.getFirstName(),record.getLastName(),
			String.valueOf(record.getBalance()+change),
			"Charge(+) or payment(-)"};
			
			userInterface.setFieldValues(values);
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this,"Bad Input","Number Format Error",JOptionPane.ERROR_MESSAGE);
			
		}
		catch(IllegalArgumentException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage(),"Bad Account Number",JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(this,"Erro Reading to the File","IO Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void performAction(String action)
	{
		try
		{
		String[] values=userInterface.getFieldValues();
		
		int accountNumber=Integer.parseInt(values[0]);
		String firstName=values[1];
		String lastName=values[2];
		double balance=Double.parseDouble(values[3]);
		
		if(action.equals("Create"))
		dataFile.newRecord(accountNumber,firstName,lastName,balance);
		
		else if(action.equals("Update"))
		dataFile.updateRecord(accountNumber,firstName,lastName,balance);
		
		else if(action.equals("Delete"))
		dataFile.deleteRecord(accountNumber);
		
		else
		JOptionPane.showMessageDialog(this,"Invalid Action","Error Exceuting Action",JOptionPane.ERROR_MESSAGE);
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this,"Bad Input","Number Format Error",JOptionPane.ERROR_MESSAGE);
			
		}
		catch(IllegalArgumentException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage(),"Bad Account Number",JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(this,"Erro Writing to the File","IO Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(AccountCreated e)
		{
			JOptionPane.showMessageDialog(this,"Account Created","Successful",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}	