import java.awt.Graphics;

import javax.swing.JApplet;
import java.applet.AudioClip;


public class audioclip extends JApplet
{
	private AudioClip clip;
	
	public void init()
	{
		clip=JApplet.newAudioClip(getClass().getResource("gc.Wma"));
		
				
		clip.play();
		System.out.println(clip);
		
	}
	
	public void paint(Graphics gc)
	{
	}
}