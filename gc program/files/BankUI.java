import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class BankUI extends JPanel
{
	protected final static String names[]={"Account Number","First Name","Last Name","Balance","Transaction Amount"
	};
	protected JLabel labels[];
	protected JTextField fields[];
	protected JButton dotask1,dotask2;
	JPanel center,south;
	protected int size;
	
	public BankUI(int mySize)
	{
		size=mySize;
		
		labels=new JLabel[size];
		fields=new JTextField[size];
		
		for(int count=0;count<size;count++)
		labels[count]=new JLabel(names[count]);
		
		for(int count=0;count<size;count++)
		fields[count]=new JTextField();
			
		center=new JPanel();
		center.setLayout(new GridLayout(size,2));
		
		for(int count=0;count<size;count++)
		{
			center.add(labels[count]);
			center.add(fields[count]);
		}	
		dotask1=new JButton();
		dotask2=new JButton();
			
		south=new JPanel();
		south.add(dotask1);
		south.add(dotask2);
		
		setLayout(new BorderLayout());
		    
		add(center,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
		validate();
		    
		
			
	}
	public JButton getDoTask1Button()
	{
		return dotask1;
	}
	public JButton getDoTask2Button()
	{
		return dotask2;
	}
	
	
	public JTextField[] getFields()
	{
		return fields;
	}
	public void clearFields()
	{
		for(int count=0;count<size;count++)
		{
			fields[count].setText("");
		}
	}
	public void  setFieldValues(String strings[])
			throws IllegalArgumentException
	{
		if(strings.length!=size)
			throw new IllegalArgumentException("there must be "+size+" strings in the array");
		for(int count=0;count<size;count++)
		{
			fields[count].setText(strings[count]);
		}				
	}
	public String[] getFieldValues()
	{
		String values[]=new String[size];
		for(int count=0;count<size;count++)
		{
			values[count]=fields[count].getText();
		}
		return values;
	}
	
	
}