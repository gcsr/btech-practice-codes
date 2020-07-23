

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSize extends JFrame{
        JButton ss;
        int width=300;int height=300;
   
    public ButtonSize() {
    	super("changing window by button");
    	ss=new JButton("increment");
    	ss.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			width+=50;
    			setSize(width,height);
    		}
    	});
    	add(ss);
    	setSize(300,300);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    }
    
    
    public static void main(String[] args) {
    	new ButtonSize();
       
    }
}
