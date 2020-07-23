import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.sound.midi.*;




public class playsequence1 implements MetaEventListener 
{
    Receiver receiver;
    Synthesizer synthesizer;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
   
    private int tickPos=0;
   
    private static final int CHANNEL=0;
    private static final int VOLUME=90;
    private static final int BANK_CONTROLLER=0;
    private static final int[] coffsets={9,11,0,2,4,5,7
    };
    private static final int C4_KEY=60;
    private static final int OCTAVE=12;
    private static final int END_OF_TRACK=47;
   
    

    public playsequence1()
    {
    	try
        {
        	
        	System.out.println("cons");
       	    synthesizer=MidiSystem.getSynthesizer();
       	    synthesizer.open();
		    receiver=synthesizer.getReceiver();
			sequencer=MidiSystem.getSequencer();
			if(sequencer==null)
			{
				System.out.println("cannot get a sequencer");
				System.exit(0);
			}

		sequencer.open();
	
        }
        catch(Exception e)
        {
        	System.out.println("error");
        }
        //listInstruments();
        createTrack(4);
        makeSong();
        startSequencer(100);
       /* try
        {
        	Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
        	System.out.println("sleep interrupted");
        	System.exit(0);
        }*/
    
    }
    public void meta(MetaMessage e)
	{
		if(e.getType()==END_OF_TRACK)
		{
			System.out.println("Exiting");
			sequencer.close();
						
		}
	}
    private void startSequencer(int tempo)
	{
		try
		{
			sequencer.setSequence(sequence);
		}
		catch(InvalidMidiDataException e)
		{
			e.printStackTrace();
		}
		sequencer.addMetaEventListener(this);
		sequencer.start();
		sequencer.setTempoInBPM(tempo);
	}
    
    public void createTrack(int resolution)
    {
    	try
        {
	        sequence=new Sequence(Sequence.PPQ,resolution);
        }
        catch(InvalidMidiDataException e)
        {
	       e.printStackTrace();
        }
        track=sequence.createTrack();
    	
    }

	public void playNote(int note,int duration,int channel)
	{
		
		
		try
		{
		
		ShortMessage msg=new ShortMessage();
		
			msg.setMessage(ShortMessage.NOTE_ON,channel,note,70);
			receiver.send(msg,-1);
			try
			{
				Thread.sleep(duration*1000);
			}
			catch(InterruptedException e)
			{
				System.out.println("error in sleeping");
			}
			msg.setMessage(ShortMessage.NOTE_OFF,channel,note,70);
			
		}
		catch(InvalidMidiDataException e)
		{
			System.out.println("invalid midi");
		}
		catch(Exception e)
		{
			System.out.println("MidiUnaivalable");
		}
	}
	
	
	public void listInstruments()
	{
		Instrument[] instrument=synthesizer.getAvailableInstruments();
		System.out.println("no of instruments"+instrument.length);
		for(int i=0;i<instrument.length;i++)
		{
			Patch p=instrument[i].getPatch();
			System.out.print("("+instrument[i].getName()+"<"+p.getBank()+","+p.getProgram()+">)");
			if(i%3==0)
				System.out.println();
		} 
			System.out.println();
	}
	
	private void makeSong()
	{
		
	
	}
	private void changeInstrument(int bank,int program)
	{
		Instrument[] instrument=synthesizer.getAvailableInstruments();
		for(int i=0;i<instrument.length;i++)
		{
			Patch p=instrument[i].getPatch();
			if(bank==p.getBank()&&program==p.getProgram())
			{
				programChange(program);
				bankChange(bank);
				return;
			}
		} 
			System.out.println("no instrument");
		
	}
	private void programChange(int program)
	{

		ShortMessage message=new ShortMessage();
        try
        {
			message.setMessage(ShortMessage.PROGRAM_CHANGE,CHANNEL,program,0);
			MidiEvent event=new MidiEvent(message,tickPos);
			track.add(event);

		}
		catch(InvalidMidiDataException e)
		{
				e.printStackTrace();
		}
	}
	private void bankChange(int bank)
	{
		ShortMessage message=new ShortMessage();
        try
        {
			message.setMessage(ShortMessage.CONTROL_CHANGE,CHANNEL,BANK_CONTROLLER,bank);
			MidiEvent event=new MidiEvent(message,tickPos);
			track.add(event);

		}
		catch(InvalidMidiDataException e)
		{
				e.printStackTrace();
		}
		
	}
	private void addRest(int period)
	{
		tickPos+=period;
	}
	private void add(int note,int period)
	{
		setMessage(ShortMessage.NOTE_ON,note,tickPos);
		tickPos+=period;
		setMessage(ShortMessage.NOTE_OFF,note,tickPos);
		
	}
	public void add(String noteStr,int period)
	{
		int note=getKey(noteStr);
		add(note,period);
	}
	private void add(int note)
	{
		add(note,1);
	}
	private void add(String noteStr)
	{
		add(noteStr,1);
	}
	public void setMessage(int onoroff,int note,int tickPos)
	{
		if(note<0||note>127)
		{
			System.out.println("note outside midirange "+note);
			return;
		}
		ShortMessage message=new ShortMessage();
		try
		{
			message.setMessage(onoroff,CHANNEL,note,VOLUME);
			MidiEvent event=new MidiEvent(message,tickPos);
			track.add(event);
			
		}
		catch(InvalidMidiDataException e)
		{
			e.printStackTrace();
		}
	}
	private int getKey(String noteStr)
	{
		char[] letters=noteStr.toCharArray();
		if(letters.length<2)
		{
			System.out.println("incorrect note syntax:using C4");
			return C4_KEY;
		}
		int c_offset=0;
		if(letters[0]>='A'&&letters[0]<='G')
		c_offset=coffsets[letters[0]-'A'];
		else
		System.out.println("incorrect "+letters[0]+"  using C");
		int range=C4_KEY;
		if(letters[1]>='0'&&letters[1]<='9')
		range=OCTAVE*(letters[1]-'0'+1);
		else
		System.out.println("incorrect "+letters[1]+"  using 4");
		int sharp=0;
		if(letters.length>2&&letters[2]=='#')
		sharp=1;
		int key=range+c_offset+sharp;
		return key;
		
	}
	
	
}