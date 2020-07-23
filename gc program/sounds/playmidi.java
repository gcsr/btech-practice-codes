import javax.sound.midi.*;
import javax.sound.midi.spi.*;

public class playmidi
{
	public static void main(String[] gcs)
	{
		/*if(gcs.length!=1)
		{
			System.out.println("argument 1 ra gcga");
			System.exit(0);
		}
		System.out.println(gcs[0]);
		*/
		new pmidi("pq.MP3");
	}
}

class pmidi implements MetaEventListener
{
	private static final int eot=47;
	private Sequencer sequencer;
	private Synthesizer synthesizer;
	private Sequence seq=null;
	private String fname;

	public pmidi(String fnm)
	{
		System.out.println("const");
		fname=fnm;
		initsequencer();
		loadmidi(fname);
		play();
	}
	public void initsequencer()
	{
		System.out.println("init sequencer");
		try
		{
			sequencer=MidiSystem.getSequencer();
			if(sequencer==null)
			{
				System.out.println("cannot get a sequencer");
				System.exit(0);
			}

		sequencer.open();
		sequencer.addMetaEventListener(this);
		if(!(sequencer instanceof Synthesizer))
		{
			System.out.println("linking the sequencer to the synthesizer");
			synthesizer=MidiSystem.getSynthesizer();
			synthesizer.open();

			Receiver synthreceiver=synthesizer.getReceiver();
			Transmitter seqtransmitter=sequencer.getTransmitter();
			seqtransmitter.setReceiver(synthreceiver);
		}
		else
		synthesizer=(Synthesizer)sequencer;
		}
		catch(MidiUnavailableException e)
		{
			System.out.println("no sequencer available");
			System.exit(0);
		}
	}
	public void loadmidi(String fnm)
	{
		System.out.println("load midi");
		try
		{
			seq=MidiSystem.getSequence(getClass().getResource(fnm));
			//double duration=((double)seq.getMicrosecondLength());
			//System.out.println(duration);
		}
		catch(Exception e)
		{
			System.out.println("exception loading midi");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void play()
	{
		System.out.println("play");
		if(sequencer!=null&&seq!=null)
		{
			try
			{
				sequencer.setSequence(seq);
				System.out.println("before");
				sequencer.start();
				System.out.println("after");
			}
			catch(InvalidMidiDataException e)
			{
				System.out.println("invalid midi file");
				System.exit(0);
			}
		}
	}
	public void meta(MetaMessage e)
	{
		if(e.getType()==eot)
		{
			System.out.println("Exiting");
			sequencer.close();
			System.exit(0);
		}
	}
}