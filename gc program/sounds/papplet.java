import javax.sound.midi.*;

import java.applet.Applet;

public class papplet extends Applet implements MetaEventListener
{
	private static final int eot=47;
	private Sequencer sequencer;
	private Synthesizer synthesizer;
	private Sequence seq=null;
	private String fname;

	public void init()
	{
		System.out.println("const");
		fname="pp.midi";
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