/**
 * @(#)BeatBox.java
 *
 *
 * @author 
 * @version 1.00 2010/7/20
 */

import javax.swing.JFrame;
 import java.awt.BorderLayout;
 import javax.swing.JPanel;
 import javax.swing.BorderFactory;
 import java.util.ArrayList;
 import javax.swing.JCheckBox;
 import javax.swing.Box;
 import javax.swing.BoxLayout;
 import javax.swing.JButton;
 import javax.swing.JLabel;
 import java.awt.GridLayout;
 import java.awt.Dimension;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.sound.midi.Sequence;
 import javax.sound.midi.Sequencer;
 import javax.sound.midi.MidiSystem;
 import javax.sound.midi.Track;
 import javax.sound.midi.MidiEvent;
 import javax.sound.midi.ShortMessage;
 
 
public class BeatBox extends JFrame {
	JPanel mainPanel;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	ArrayList<JCheckBox> checkboxList;
	String[] instrumentNames={"Bass Drum","Closed Hi-Hat",
	"Open Hi_Hat","Accoustic Snare","Crash Symbol",
		"Hand Clap","High Tom","Hi Bongo","Maracas","Whistle",
		"Low Conga","Cowbell","Vibraslap","Low_Mid Tom","High Agogo",
		"Open Hi Conga"
	};
	int[] instruments={35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63
	};
	
	public static void main(String[] gcs)
	{
		new BeatBox();
	}
	
	public BeatBox() {
		theFrame=new JFrame("Cyber Beat Box");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout=new BorderLayout();
		JPanel background=new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		checkboxList=new ArrayList<JCheckBox>();
		Box buttonBox=Box.createVerticalBox();
		
		
		
		JButton start=new JButton("start");
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				buildTrackAndStart();
			}
		});
		
		buttonBox.add(start);
		
		JButton stop=new JButton("stop");
		buttonBox.add(stop);
		
		stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sequencer.stop();
			}
		});
		
		
		
		JButton upTempo=new JButton("Tempo Up");
		buttonBox.add(upTempo);
		
		upTempo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				float tempoFactor=sequencer.getTempoFactor();
					sequencer.setTempoFactor((float)(tempoFactor*1.03));
			}
		});
		
	
		JButton downTempo=new JButton("Tempo Down");
		buttonBox.add(downTempo);
		
		
		
		downTempo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				float tempoFactor=sequencer.getTempoFactor();
					sequencer.setTempoFactor((float)(tempoFactor*0.97));
			}
		});
		
		JPanel namePanel=new JPanel();
		namePanel.setLayout(new GridLayout(16,1));
		
			
		for(int i=0;i<16;i++)
		{
			
			namePanel.add(new JLabel(instrumentNames[i]));
		}
		
		
		mainPanel=new JPanel(new GridLayout(16,16));
		
		for(int i=0;i<256;i++)
		{
			JCheckBox c=new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
			
		}
		
		background.add(BorderLayout.CENTER,mainPanel);
		background.add(BorderLayout.EAST,buttonBox);
		background.add(BorderLayout.WEST,namePanel);
		
		theFrame.getContentPane().add(background);
		setUpMidi();
		
		theFrame.pack();
		theFrame.setBounds(50,50,550,420);
		theFrame.setVisible(true);
	}
	
	private void buildTrackAndStart()
	{
		int[] trackList=null;
		
		sequence.deleteTrack(track);
		
		track=sequence.createTrack();
		
		for(int i=0;i<16;i++)
		{
			trackList=new int[16];
			int key=instruments[i];
			
			for(int j=0;j<16;j++)
			{
				JCheckBox jc=(JCheckBox)checkboxList.get(j+16*i);
				if(jc.isSelected())
					trackList[j]=key;
					else
					trackList[j]=0;	
			}
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
			
		}
		track.add(makeEvent(192,9,1,0,15));
		try{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	private void setUpMidi()
	{
		try
		{
			sequencer=MidiSystem.getSequencer();
			sequencer.open();
			sequence=new Sequence(Sequence.PPQ,4);
			track=sequence.createTrack();
			sequencer.setTempoInBPM(120);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
		
	}
	private void makeTracks(int[] list)
	{
		for(int i=0;i<16;i++)
		{
			int key=list[i];
			if(key!=0)
			{
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
				
		}
	}
	
	private MidiEvent makeEvent(int comd,int chan,int one,int two,int tick)
	{
		MidiEvent event=null;
		try{
			ShortMessage a=new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event=new MidiEvent(a,tick);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return event;
		
	}

	
}