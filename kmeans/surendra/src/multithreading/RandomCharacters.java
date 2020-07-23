package multithreading;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class RandomCharacters extends JApplet implements ActionListener
{
	private final static int SIZE=3;
	private JLabel outputs[];
	private JCheckBox checkBoxes[];
	private Thread threads[];
	private boolean suspended[];
	private String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public void init()
	{
		outputs=new JLabel[SIZE];
		checkBoxes=new JCheckBox[SIZE];
		threads=new Thread[SIZE];
		suspended=new boolean[SIZE];
		
		
		Container container=getContentPane();
		
		container.setLayout(new GridLayout(SIZE,2,5,5));
		
		for(int count=0;count<SIZE;count++)
		{
			outputs[count]=new JLabel();
			outputs[count].setBackground(Color.green);
			outputs[count].setOpaque(true);
			container.add(outputs[count]);
			
			checkBoxes[count]=new JCheckBox("suspended");
			checkBoxes[count].addActionListener(this);
			
			container.add(checkBoxes[count]);
		}
	}
	public void start()
	{
		
		for(int count=0;count<threads.length;count++)
		{
			threads[count]=new Thread(new RunnableObject(),"Thread"+(count+1));
			threads[count].start();
		}
	}
	public synchronized void actionPerformed(ActionEvent e)
	{
		for(int count=0;count<checkBoxes.length;count++)
		{
			if(e.getSource()==checkBoxes[count])
			{
				suspended[count]=!suspended[count];
				outputs[count].setBackground(suspended[count]?Color.red:Color.green);
				
				if(!suspended[count])
				notifyAll();
				
				return;
			}
		}
		
	}
	public synchronized void stop()
	{
		for(int count=0;count<threads.length;count++)
		threads[count]=null;
		
		return;
	}
	private class RunnableObject implements Runnable
	{
		public void run()
		{
			
			final Thread currentThread=Thread.currentThread();
			
			final int index=getIndex(currentThread);
						
			while(threads[index]==currentThread)
			{
				try
				{
					System.out.println(threads[index]);
			
					Thread.sleep((int)(Math.random()*1000));
				
				
					synchronized(RandomCharacters.this)
					{
						while(suspended[index]&&threads[index]==currentThread)
						{
							RandomCharacters.this.wait();
						}
					}
				
				}
			
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			
			
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					
				
					char displayChar=alphabet.charAt((int)(Math.random()*26));
					
					outputs[index].setText(currentThread.getName()+":"+displayChar);
				}
			});
			}
			System.err.println(currentThread.getName()+" terminating");
		}
		
		
	}
	private int getIndex(Thread current)
	{
		for(int count=0;count<threads.length;count++)
		if(current==threads[count])
		return count;
		
		return -1;
		
	}
	
	
}