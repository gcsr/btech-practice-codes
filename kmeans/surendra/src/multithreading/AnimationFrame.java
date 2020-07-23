package multithreading;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AnimationFrame extends JFrame{
	private final int VALUES_LENGTH=30;
	
	public AnimationFrame()
	{
		ArrayPanel panel=new ArrayPanel();
		add(panel,BorderLayout.CENTER);
		
		Double[] values=new Double[VALUES_LENGTH];
		
		final Sorter sorter=new Sorter(values,panel);
		JButton runButton=new JButton("Run");
		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sorter.setRun();
			}
		});
		
		JButton stepButton=new JButton("step");
		stepButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sorter.setStep();
			}
		});
		
		JPanel buttons=new JPanel();
		buttons.add(runButton);
		buttons.add(stepButton);
		
		add(buttons,BorderLayout.NORTH);
		
		setSize(300,300);
		
		
		for(int i=0;i<values.length;i++)
			values[i]=new Double(Math.random());
			
			
		Thread t=new Thread(sorter);
		t.start();	
		
	}
}

class Sorter implements Runnable{
	private ArrayPanel panel;
	private Double[] values;
	Semaphore gate;
	boolean run;
	
	public Sorter(Double[] values,ArrayPanel panel)
	{
		this.values=values;
		this.panel=panel;
		this.gate=new Semaphore(1);
		run=true;
		
	}
	public void setRun()
	{
		run=true;
		gate.release();
	}
	public void setStep()
	{
		run=false;
		gate.release();
	}
	
	public void run()
	{
		Comparator<Double> comp=new Comparator<Double>()
		{
			public int compare(Double i1,Double i2)
			{
				panel.setValues(values,i1,i2);
				try
				{
					if(run)
						Thread.sleep(100);						
					else
					gate.acquire();	
							
				}
				catch(InterruptedException e)
				{
					Thread.currentThread().interrupt();
				}
				return i1.compareTo(i2);
			}
		};
		
		Arrays.sort(values,comp);
		panel.setValues(values,null,null);
		
	}
}

class ArrayPanel extends JPanel
{
	private Double[] values;
	private Double marked1;
	private Double marked2;
	public void paintComponent(Graphics gc)
	{
		if(values==null)return;
		
		super.paintComponent(gc);
		
		Graphics2D gcd=(Graphics2D)gc;
		int width=getWidth()/values.length;
		
		for(int i=0;i<values.length;i++)
		{
			double height=values[i]*getHeight();
			
			Rectangle2D bar=new Rectangle2D.Double(width*i,0,width,height);
			
			if(values[i]==marked1||values[i]==marked2)
				gcd.fill(bar);
				else
					gcd.draw(bar);
		}
	}
	
	public void setValues(Double[] values,Double marked1,Double marked2)
	{
		this.values=values;
		this.marked1=marked1;
		this.marked2=marked2;
		repaint();
	}
	
}