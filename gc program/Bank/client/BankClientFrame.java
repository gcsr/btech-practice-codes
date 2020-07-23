import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class BankClientFrame extends JFrame
{
	private JTextField accountField;
	private JTextField depositField;
	private JTextField withdrawField;
	private JTextField balanceField;
	private JButton balanceButton;
	private JButton depositButton;
	private JButton withdrawButton;
	private JLabel accountLabel;
	private JLabel depositLabel;
	private JLabel withdrawLabel;
	private JLabel currentBalanceLabel;
	Account account;
	
	
	public BankClientFrame()
	{
		setLayout(new BorderLayout());
		add(topPanel(),BorderLayout.NORTH);
		add(belowPanel(),BorderLayout.SOUTH);
	}
	
	private JPanel belowPanel()
	{
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2,0,0));
		JLabel currentBalanceLabel=new JLabel("currentBalance");
		
		balanceField=new JTextField();
		panel.add(currentBalanceLabel);
		panel.add(balanceField);
		
		return panel;
		
	}
	
	
	private JPanel topPanel()
	{
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(3,3,0,0));
		
		
		accountLabel=new JLabel("account Nae");
		accountField=new JTextField();
		balanceButton=new JButton("getBalance");
		
		panel.add(accountLabel);
		panel.add(accountField);
		panel.add(balanceButton);
		balanceButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try{
				getAccount();
				resetBalanceField();
				releaseAccount();
				}
				catch(Exception ex)
				{
					System.out.println("ikkade");
				}
			}
		});
		
		depositLabel=new JLabel("deposit");
		depositField=new JTextField();
		depositButton=new JButton("do it");
		
		balanceButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		panel.add(depositLabel);
		panel.add(depositField);
		panel.add(depositButton);
		
		
		depositButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		withdrawLabel=new JLabel("withdraw");
		withdrawField=new JTextField();
		withdrawButton=new JButton("do it");
		
		withdrawButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		
		panel.add(withdrawLabel);
		panel.add(withdrawField);
		panel.add(withdrawButton);
		
		return panel;
		
	}
	
	private void getAccount()
	{
		try
		{
			System.out.println(accountField.getText());
			account=(Account)Naming.lookup(accountField.getText());
			System.out.println(accountField.getText());
		}
		catch(Exception ex)
		{
			System.out.println("Couldn't find account");
			ex.printStackTrace();
		}
		return;
	}
	
	private void resetBalanceField()
	{
		try{
			Money balance=account.getBalance();
			
			balanceField.setText("Balance: "+balance.getCents());
			
		}
		catch(Exception e)
		{
			System.out.println("error while getting balance");
			e.printStackTrace();
		}
	}
	
	private void releaseAccount()
	{
		account=null;
	}
}