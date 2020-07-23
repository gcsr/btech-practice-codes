package hostel;



import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class daily extends JFrame
{
public daily()
{
super("box layout frame");
setSize(1200,400);

setLayout(new BorderLayout());
left lt=new left();
add(lt,BorderLayout.WEST);

right rt=new right();
add(rt,BorderLayout.EAST);
bottom ss=new bottom();
add(ss,BorderLayout.SOUTH);


up pp=new up();
add(pp,BorderLayout.NORTH);

center cr=new center();
add(cr,BorderLayout.CENTER);
}

}
class center extends JPanel
{
	ImageIcon picture;
	public void paintComponent(Graphics gc)
	{
		super.paintComponent(gc);
	    String images[]={"s1.JPG","s2.JPG","s3.JPG"};


		
		picture=new ImageIcon(images[0]);

		picture.paintIcon(this,gc,0,0);
	
	}
	
}
class up extends JPanel
{
	up()
	{
		setLayout(new FlowLayout());
		add(new JButton("id no"));
		add(new JTextField(10));
	}	
}

class left extends JPanel
{
	left()
	{
		
		setLayout(new GridLayout(5,2));
		String ss[]={"idli","pongal","uthappam","dosa","voda"};
		
		for(int count=0;count<5;count++)
  		{
			
			
			add(new JButton(ss[count]));
			add(new JTextField(3));
		}
		
		
	}
}



class right extends JPanel
{
	right()
	{
		setLayout(new GridLayout(4,2));
		String ss[]={"chapathi","lemrice","rice","extra rs"};
		
		for(int count=0;count<4;count++)
  		{
			
			
			add(new JButton(ss[count]));
			add(new JTextField(3));
		}
		
		
	}
}


class bottom extends JPanel
{
	bottom()
	{
		add(new JButton("photo"));
		add(new JButton("insert"));
		add(new JButton("delete"));
		add(new JButton("clear"));
		
		
		
	}
}

