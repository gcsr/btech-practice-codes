import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.applet.AudioClip;
import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class soundplayer extends JFrame implements ActionListener
{
	JList list;
	JButton button1,button2,button3;
	HashMap soundsmap;
	ArrayList playingclips;
	JPanel panel;
	JLabel statuslabel;
	
	String pp[]=new String[30];
	public soundplayer()
	{
		
				
		setLayout(new BorderLayout());
		
		loadsongs();
		playingclips=new ArrayList<AudioClip>();
		panel=new JPanel();
		
		list=new JList(pp);
		JScrollPane spane=new JScrollPane(list);
		button1=new JButton("play");
		button2=new JButton("loop");
		button3=new JButton("stop");
		panel.add(spane);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		add(panel,BorderLayout.NORTH);
		
		statuslabel=new JLabel();
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		add(statuslabel,BorderLayout.SOUTH);
		
	}
	
	public void loadsongs()
	{
		soundsmap=new HashMap();
		for(int i=1;i<24;i++)
		{
			try
			{
			pp[i]=("ss ("+i+").MID");
			AudioClip clip=JApplet.newAudioClip(getClass().
			getResource(pp[i].toString()));
			if(clip==null)
			System.out.println("error loading file");
			else
			soundsmap.put(pp[i].toString(),clip);
			}
			catch(Exception e)
			{
				System.out.println("mga");
			}
		}
	}
	
	public void playmusic(boolean toloop)
	{
		
		System.out.println("playmusic");
		String chosenfile=(String)list.getSelectedValue();
		AudioClip aclip=(AudioClip)soundsmap.get(chosenfile);
		if(aclip==null)
	    {
		statuslabel.setText("sound "+chosenfile+" not loaded");
		return;
		}
		if(toloop)
		aclip.loop();
		else
		aclip.play();
		
		playingclips.add(aclip);
		
		String times=(toloop)?"repeatedly":"once";
		
		statuslabel.setText("playing sound "+chosenfile+" "+times);
		
	}
	
	public void stopmusic()
	{
		System.out.println("stopmusic");
		if(playingclips.isEmpty())
		statuslabel.setText("nothing to stop");
		else
		{
			AudioClip aclip;
			for(int i=0;i<playingclips.size();i++)
			{
				aclip=(AudioClip)playingclips.get(i);
				aclip.stop();
				
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("actionperformed");
		String str=(String)e.getActionCommand();
		
		
		if(str=="play")
		{
			System.out.println("button1 is selected");
		    playmusic(false);
		}
		
		else
		if(str=="loop")
		playmusic(true);
		
		else
		if(str=="stop")
		stopmusic();
		
		
	}
}