import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComboBox;
import javax.swing.JFrame;


public class GUIClass extends JFrame implements ActionListener{
	
	PaintPanel panel;
	Timer timer;
	JComboBox cb=null;
	public GUIClass(){
		super("Assignment");
		// Box bv = Box.createVerticalBox();
		 panel=new PaintPanel();
		 setLayout(new BorderLayout());
		 cb=new JComboBox(new String[]{"Not Selected","Constructive Gemoetry","Animation"});
	     cb.setMaximumRowCount(3);
	     cb.setSelectedIndex(0);	
	     add(cb,BorderLayout.NORTH);
	     add(panel,BorderLayout.CENTER);
	  
	    updatePanel();
	    cb.addActionListener(this);
	}
	
	public void updatePanel(){
		try{
			
			Thread.currentThread().sleep(2000);
		}catch(Exception ex){
			
		}
	
		timer = new Timer();
		panel.repaint();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  
			    updatePanel();  
			  }
			}, 2*1000);
		
	}
	
	 public void actionPerformed(ActionEvent e) {
     	//System.out.println("output formed");
         if(cb.getSelectedIndex()==0)
         	panel.setDraw1(true);
         else if(cb.getSelectedIndex()==1)
         	panel.setDraw2(true);
         else
         	panel.setDraw3(true);
         panel.repaint();
     }
	
	
}
