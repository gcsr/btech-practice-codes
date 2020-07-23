import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Vector;
import java.util.*;
public class Puzzle8Frame extends JFrame{
	JTabbedPane tabPane;
	int[] pieces=new int[9];
	JPanel panel1,panel2,panel3;
    JTextField[] tFields=new JTextField[9];
    JButton[] buttons=new JButton[9];
    Vector solution=new Vector();
    int stepNo=1;
    JPanel panel11=new JPanel();
    JPanel panel21=new JPanel();
    
    boolean exception=true;
    
	public Puzzle8Frame()
	{
	    tabPane=new JTabbedPane();
	    
		//JLabel label1=new JLabel("panel1",SwingConstants.CENTER);
		
		createNewPanel();
		createOldPanel();
		/*
		//JPanel panel1=new JPanel();
		//panel1.add(label1);

		//JLabel label2=new JLabel("panel2",SwingConstants.CENTER);
		//JPanel panel2=new JPanel();
		//panel2.setBackground(Color.YELLOW);
		//panel2.add(label2);*/

		JLabel label3=new JLabel("if anything goes wrong,it's programmers fault and don't blame me");
		JPanel panel3=new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.add(new JButton("north"),BorderLayout.NORTH);
		panel3.add(new JButton("south"),BorderLayout.SOUTH);
		panel3.add(new JButton("east"),BorderLayout.EAST);
		panel3.add(new JButton("west"),BorderLayout.WEST);
		panel3.add(label3,BorderLayout.CENTER);



		tabPane.addTab("new",null,panel1,"first panel");
		tabPane.addTab("steps",null,panel2,"second panel");
		tabPane.addTab("help",null,panel3,"third panel");
		add(tabPane);

	}
	public void createNewPanel()
	{
		panel1=new JPanel();
		panel1.setLayout(new BorderLayout());
		
		
		panel11.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++)
		{
			tFields[i]=new JTextField(i);
			panel11.add(tFields[i]);
		}
		
		panel1.add(panel11,BorderLayout.CENTER);
		JButton startButton=new JButton("start");
		startButton.addActionListener(new ActionListener()
		{
			public boolean repeated(int i)
			{
				int count=0;
				for(int x:pieces)
				{
					if(i==x)
						count++;
				}
				if(count>1)
					return false;
				else 
					return true;
			}
			public void actionPerformed(ActionEvent ev)
			{
				try
				{
					for(int i=0;i<9;i++)
					{
						pieces[i]=Integer.parseInt(tFields[i].getText());
					}
				
					for(int i:pieces)
					{
						if(i<0 || i>8)
							throw new NumberOutOfRangeException();
						if(repeated(i)==false)
							throw new NumberRepeatedException();
					}
					
					Solver puzzle=new Solver(pieces);
					puzzle.solve();
					puzzle.print();
					solution=puzzle.getVector();
					JOptionPane.showMessageDialog(Puzzle8Frame.this,"execution completed");
					exception=false;
					
					 
				}
				catch(IllegalArgumentException ex)
				{
					exception=true;
					JOptionPane.showMessageDialog(Puzzle8Frame.this,"enter only integers");
					//System.out.println("enter only integers");
					//JOptionPane.showMessageDialog(this, arg1, arg2, arg3)
				}
				
				catch(NumberOutOfRangeException ex)
				{
					exception=true;
					JOptionPane.showMessageDialog(Puzzle8Frame.this,"numbers should be between 0 and 9");
					//System.out.println("numbers should be between 0 and 9");
				}
				catch(NumberRepeatedException ex)
				{
					exception=true;
					JOptionPane.showMessageDialog(Puzzle8Frame.this,"numbers should not be repeated");
					//System.out.println("numbers should not be repeated");
				}
				
				
				
			}
		});
		panel1.add(startButton,BorderLayout.SOUTH);
	}
	
	public void updateButtons()
	{
		if(stepNo>solution.size())
		{
JOptionPane.showMessageDialog(Puzzle8Frame.this,"all steps are over");
			return;
		}
		Puzzle puzzle=(Puzzle)solution.get(solution.size()-stepNo);
		
		pieces=puzzle.getPieces();
		
		for(int i=0;i<9;i++)
		{
				buttons[i].setVisible(true);
				buttons[i].setText(""+pieces[i]);//=new JButton(""+pieces[i]);
				if(pieces[i]==0)
					buttons[i].setVisible(false);
				panel21.add(buttons[i]);
			
		}
		stepNo++;
		
	}
	
	public void createOldPanel()
	{
		panel2=new JPanel();
		panel2.setLayout(new BorderLayout());
		
		
		panel21.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++)
		{
			buttons[i]=new JButton("");
			/*if(i==0)
			buttons[i].setVisible(false);*/
			panel21.add(buttons[i]);
		}
		
		panel2.add(panel21,BorderLayout.CENTER);
		JButton nextButton=new JButton("next");
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev)
			{
				if(exception==true)
{
					JOptionPane.showMessageDialog(Puzzle8Frame.this,"fill the details correctly and press start");
return;
}
				updateButtons();
				System.out.println("in action performed");
			}
		});
		panel2.add(nextButton,BorderLayout.SOUTH);
	}
	
	
	

}
