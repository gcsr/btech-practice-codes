import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

public class Picture extends JPanel {
	PictureObject object;
	int lengthOfThreads;
	
	public Picture(PictureObject object,int lengthOfThreads)
	{
		this.object=object;
		this.lengthOfThreads=lengthOfThreads;
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Picture pic=new Picture();
		
		frame.add(this);
		frame.show();
		frame.setSize(400,400);
		frame.setVisible(true);
	
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for(int i=0;i<(object.noOfThreads);i++)
		g.fillRect(i*50,10,20,100);
		g.setColor(Color.WHITE);
		for(int i=0;i<(object.noOfThreads);i++)
		{
			//System.out.println("i "+i+"cmpl is "+object.cmpl[i]);
			g.fillRect(i*50,10, 20, ((object.cmpl[i])*100)/lengthOfThreads);
		}
		
	}
	
}
