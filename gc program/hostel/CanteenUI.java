
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class CanteenUI extends JFrame
{
	int first=0;                       
	JLabel labels[];
	JTextField textFields[];
	JTextField made[];
	JButton insert,delete,clear,update;
	JLabel id;
	JTextField idField;
	int size;
	
		
	String ss[]={"rno","date","idli","pongal","uthappam","dosa","voda","chapathi","lemrice","rice","extra rs","items"};

	public CanteenUI()
	{
		
		super("Canteen");
		setVisible(true);
		setSize(350,450);
		

		setLayout(new BorderLayout());
		size=ss.length;

		bottom ss=new bottom();
		add(ss,BorderLayout.SOUTH);


		up pp=new up();
		add(pp,BorderLayout.NORTH);

		center cr=new center();
		add(cr,BorderLayout.CENTER);
	}


	class center extends JPanel
	{
		center()
		{
		
		setLayout(new GridLayout(size,3));
		
		labels=new JLabel[size];
		textFields=new JTextField[size];
		made=new JTextField[size];
	
		for(int i=0;i<size;i++)
		labels[i]=new JLabel(ss[i]);
		
		for(int i=0;i<size;i++)
		{
			textFields[i]=new JTextField();
			made[i]=new JTextField();
		}	
		
		for(int count=0;count<size;count++)
  		{				
			add(labels[count]);
			add(textFields[count]);
			add(made[count]);
		}
		
		made[0].setText("0");
		made[1].setText("");
		
		for(int i=0;i<(made.length-1);i++)
			made[i].setText("0");
		
		
		textFields[1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			  fill();	
			}
		});
		}
	
	}
	class up extends JPanel
	{
		up()
		{
			setLayout(new FlowLayout());
		
			id=new JLabel("id No");
			add(id);
			idField=new JTextField(10);
			add(idField);
		}	
	}





	class bottom extends JPanel
	{
		bottom()
		{
			
			insert=new JButton("insert");
			update=new JButton("update");
			delete=new JButton("delete");
			clear=new JButton("clear");
			
			
			add(insert);
			add(update);
			add(delete);
			add(clear);
			
			
			insert.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					insertRecord();
					updates();
					
				}
			});
			clear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String[] values={"1","00-00-2010","0","0","0","0","0","0","0","0","0",""
					};
					setFieldValues(values);
					idField.setText("07701A05");
				}
			});
			update.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
					updateRecord();
					
				}
			});
			delete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
					deleteRecord();
					
				}
			});
			
			
		
		}
	}
	public void  setFieldValues(String strings[])
			throws IllegalArgumentException
	{
		if(strings.length!=size)
			throw new IllegalArgumentException("there must be "+size+" strings in the array");
		for(int count=0;count<size;count++)
		{
			textFields[count].setText(strings[count]);
		}				
	}
	public String[] getFieldValues()
	{
		String values[]=new String[size];
		for(int count=0;count<size;count++)
		{
			values[count]=textFields[count].getText();
		}
		return values;
	}
	
	private void insertRecord()
	{
		String idText=idField.getText();
		
		File f=new File(idText+".dat");
		try
		{	
			FileEditor fe=new FileEditor(f);
		
			String[] values=getFieldValues();
		
			fe.newRecord(Integer.parseInt(values[0]),values[1],Integer.parseInt(values[2]),
			Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),
			Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),
			Integer.parseInt(values[9]),Integer.parseInt(values[10]),values[11]);
			
			fe.closeFile();
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
		catch(AccountCreated e)
		{
			JOptionPane.showMessageDialog(this,"record created","success",JOptionPane.INFORMATION_MESSAGE);
			
		}
			
	}
	private void updates()
	{
		if(first==1)
		{
			for(int i=0;i<size;i++)
			made[i].setEditable(false);
			first++;
		}
		for(int i=2;i<(size-1);i++)
		{
			made[i].setText(String.valueOf(Integer.parseInt(made[i].getText())-Integer.parseInt(textFields[i].getText())));
		}	
	}
	
	private void fill()
	{
		
		File f=new File(idField.getText()+".dat");
		
		try
		{
			FileEditor fe=new FileEditor(f);
		
			RandomAccessAccountRecord record;
			
			System.out.println(textFields[1].getText());
		
			record=fe.getRecord(textFields[1].getText());
			
			System.out.println("record undi");
		
			String values[]={String.valueOf(record.getRecordNumber()),record.getDate(),String.valueOf(record.getIdli()),String.valueOf(record.getPongal()),
			String.valueOf(record.getUthappam()),String.valueOf(record.getDosa()),String.valueOf(record.getVoda()),
			String.valueOf(record.getChapathi()),String.valueOf(record.getLemRice()),String.valueOf(record.getRice()),
			String.valueOf(record.getExtraRS()),record.getExtraItems()};
		
			setFieldValues(values);
			fe.closeFile();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(this,"Erro Reading to the File","IO Error",JOptionPane.ERROR_MESSAGE);
		}
			catch(IllegalArgumentException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage(),"Bad record Number",JOptionPane.ERROR_MESSAGE);
		}
			
	}
	private void updateRecord()
	{
			String idText=idField.getText();
		
		File f=new File(idText+".dat");
		try
		{	
			FileEditor fe=new FileEditor(f);
		
			String[] values=getFieldValues();
		
			fe.updateRecord(Integer.parseInt(values[0]),values[1],Integer.parseInt(values[2]),
			Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),
			Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),
			Integer.parseInt(values[9]),Integer.parseInt(values[10]),values[11]);
			
			fe.closeFile();
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
		catch(AccountCreated e)
		{
			JOptionPane.showMessageDialog(this,"record updated","success",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	private void deleteRecord()
	{
		String idText=idField.getText();
		
		File f=new File(idText+".dat");
		try
		{	
			FileEditor fe=new FileEditor(f);
		
			fe.deleteRecord(Integer.parseInt(textFields[0].getText()));
			
			fe.closeFile();
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
		catch(AccountCreated e)
		{
			JOptionPane.showMessageDialog(this,"record deleted","success",JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
}
