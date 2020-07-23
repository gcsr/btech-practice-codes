 /**
 * @(#)MainFrame.java
 *
 *
 * @author 
 * @version 1.00 2010/8/5
 */
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.BorderFactory;
import java.awt.event.*;
import java.awt.Font;

public class MainFrame extends JFrame{
        
        JLabel[] label=new JLabel[10];
        JButton[] buttons=new JButton[20];
    /**
     * Creates a new instance of <code>MainFrame</code>.
     */
    public MainFrame() {
    	Dimension ss;
    	setTitle("web page");
    	setLayout(new BorderLayout());
    	JPanel northPanel=new JPanel();
    	JButton button1=new JButton("home");
    	JButton button2=new JButton("start");
    	JButton button3=new JButton("search");
    	JButton button4=new JButton("logout");
    	
    	button4.setRolloverEnabled(true);
    	
    	
    	button4.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e)
    			{
    				System.out.println("this is gc");
    			}
    		});
    	JPanel northBottomPanel=new JPanel();
    	button1.disable();
    	button1.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.red));
    	button2.disable();
    	button2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.red));
    	button4.disable();
    	button4.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.red));
    	northBottomPanel.add(button1);
    	northBottomPanel.add(button2);
    	northBottomPanel.add(button3);
    	northBottomPanel.add(button4);
    	JPanel northTopPanel=new JPanel();
    	
    	northPanel.add(northTopPanel);
    	northPanel.add(northBottomPanel);
    	
    	add(northPanel,BorderLayout.NORTH);
    	
    	
    	
    	
    	JPanel southPanel=new JPanel();
    	
    	JButton button5=new JButton();
    	button3.disable();
    	button3.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.red));
    	
        	
    	southPanel.add(button5);
    	southPanel.add(new JLabel("                                "));
    	
    	for(int i=0;i<10;i++)
    	{
    		label[i]=new JLabel(""+i);
    	
    		
    		southPanel.add(label[i]);
    	
    		
    	}
    	
    	add(southPanel,BorderLayout.SOUTH);
    	add(new CentralPanel(),BorderLayout.CENTER);
    
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(1440,900);
    	setVisible(true);
    	
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	new MainFrame();
    	
    }
    Box verticalBox;
    class CentralPanel extends JPanel
    {
    	CentralPanel()
    	{
    		verticalBox=Box.createVerticalBox();
    		
    		for(int i=0;i<20;i++)
    		{
    			buttons[i]=new JButton("this is going to be really a crazy website"+i);
    			buttons[i].setFont(new Font());
    			buttons[i].disable();
    			buttons[i].setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.red));
    	
    			
    	
    			verticalBox.add(buttons[i]);
    		}
    		add(verticalBox);
    	}
    }
    class pp extends JPanel
    {
    	ImageIcon picture;
    	
    	public pp()
    	{
    		picture=new ImageIcon("gc.bmp");
    			
    			
    		
    	}
    	
    	public void paintComponent(Graphics gc)
    	{
    		picture.paintIcon(this,gc,0,0);
    		
    	}
    }
}
