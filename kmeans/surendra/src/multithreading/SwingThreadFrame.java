package multithreading;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.EventQueue;



public class SwingThreadFrame extends JFrame
{
	public SwingThreadFrame()
	{
		setTitle("Swing Thread Test");
		
		final JComboBox combo=new JComboBox();
		combo.insertItemAt(new Integer(Integer.MAX_VALUE),0);
		combo.setPrototypeDisplayValue(combo.getItemAt(0));
		combo.setSelectedIndex(0);
		JPanel panel=new JPanel();
		
		JButton goodButton=new JButton("good");
		goodButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new GoodWorkRunnable(combo)).start();
				
			}
		});
		JButton badButton=new JButton("bad");
		badButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new BadWorkRunnable(combo)).start();
				
			}
		});
		panel.add(goodButton);
		panel.add(badButton);
		panel.add(combo);
		add(panel);
		pack();
		
		
	}
	
}

class GoodWorkRunnable implements Runnable{
	JComboBox box;
	Random generator;
	public GoodWorkRunnable(JComboBox box)
	{
		this.box=box;
		generator=new Random();
	}
	public void run()
	{
		try{
			while(true)
			{
				EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						box.showPopup();
						int i=Math.abs(generator.nextInt());
						
						if(i%2==0)
							box.insertItemAt(new Integer(i),0);
							
						else if(box.getItemCount()>0)
							box.removeItemAt(i%box.getItemCount());	
					}
				});
				Thread.sleep(1);
			}
			
		}
		catch(InterruptedException e)
			{
				e.printStackTrace();
			}
	}
}

class BadWorkRunnable implements Runnable{
	JComboBox box;
	Random generator;
	public BadWorkRunnable(JComboBox box)
	{
		this.box=box;
		generator=new Random();
	}
	public void run()
	{
		try{
			while(true)
			{
			
						box.showPopup();
						int i=Math.abs(generator.nextInt());
						
						if(i%2==0)
							box.insertItemAt(new Integer(i),0);
							
						else if(box.getItemCount()>0)
							box.removeItemAt(i%box.getItemCount());	
				
				Thread.sleep(1);
			}
			
		}
		catch(InterruptedException e)
			{
				e.printStackTrace();
			}
	}
}