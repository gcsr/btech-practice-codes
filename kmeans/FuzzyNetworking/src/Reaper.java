import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;


public class Reaper implements ActionListener
{
	private static final long pathTime=50000;
	Map pathBandwidths;
	int positiveBandwidth,negativeBandwidth;
	int universalBandwidth;
	boolean posWidth;
	List removableKeys;
	//JTextArea textArea;
	public Reaper(Map map)//,JTextArea textArea)
	{
		this.pathBandwidths=map;
		//this.textArea=textArea;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		removableKeys=new LinkedList<Integer>();
		//System.out.println("reaper executing");
		synchronized(pathBandwidths)
		{
			positiveBandwidth=universalBandwidth;
			negativeBandwidth=0;
			for(Object entry:pathBandwidths.entrySet())
			{
				Map.Entry en=(Map.Entry)entry;
				
				Allocation all=(Allocation)en.getValue();
				if(all.bandwidth<0)
				all.bandwidth=all.bandwidth-negativeBandwidth;
				System.out.println("allocated band width for paths "+en.getKey()+" is "+all.bandwidth);
				if(all.getElapsedTime()>pathTime){
					//posWidth=true;
					System.out.println("removing path "+en.getKey());
					if(all.bandwidth<0){
						Calculator.bufferCapacity+=all.reqRate;
						System.out.println("buffer capacity is "+Calculator.bufferCapacity);
						negativeBandwidth-=all.reqRate;
					}
					else{
						if(all.bandwidth<Calculator.DEFAULT && 
								Calculator.minimumQueue<Calculator.queueConst){
							Calculator.minimumQueue+=all.bandwidth;
							
						}				
					
						else{
							
						positiveBandwidth+=all.bandwidth;
						System.out.println("positive bandwidth is "+positiveBandwidth);
						
						}
					}
					
					//System.out.println("line before removing");
					removableKeys.add(en.getKey());
					//pathBandwidths.remove(en.getKey());
					//System.out.println("line after removing");
				
				}
				else{
					if(all.bandwidth<=0 ){
						if(positiveBandwidth>=Math.abs(all.bandwidth)){
							
							negativeBandwidth-=all.reqRate;
							positiveBandwidth-=(all.reqRate);
							//System.out.println("buffer capacity before assigning is "+Calculator.bufferCapacity);
							Calculator.bufferCapacity+=all.reqRate;
							System.out.println("buffer capacity is "+Calculator.bufferCapacity);
							all.bandwidth=(all.reqRate);
						}
						
						else if(positiveBandwidth>=Math.abs(all.bandwidth)){
							
							negativeBandwidth-=all.reqRate;
							positiveBandwidth-=(all.reqRate);
							//System.out.println("buffer capacity before assigning is "+Calculator.bufferCapacity);
							Calculator.bufferCapacity+=all.reqRate;
							System.out.println("buffer capacity is "+Calculator.bufferCapacity);
							all.bandwidth=(all.reqRate);
						}
						
						
					}
					
					
				}
				
			}
			//updateBandwidths(positiveBandwidth);
			Iterator iterator=removableKeys.iterator();
			while(iterator.hasNext()){
				pathBandwidths.remove((Integer)iterator.next());
			}
			
			
			
			if(Calculator.bufferCapacity>=0){
				Calculator.bufferCapacity+=positiveBandwidth;
				universalBandwidth=0;
			}
			else
			universalBandwidth=positiveBandwidth;
			
			routerPrint();
		}
		System.out.println("cleaning completed after cleaning");
		System.out.println("buffer capacity "+Calculator.bufferCapacity);
		System.out.println("minimum queue "+Calculator.minimumQueue);
		
	}
	
	private void routerPrint(){
		synchronized(StartServer.textArea){
		StartServer.textArea.setText("Buffer Capacity "+Calculator.bufferCapacity+" \n minimum Queue "+Calculator.minimumQueue);
		}
	}
	
	private void updateBandwidths(int positiveBandwidth){
		
		
		
	}

}
