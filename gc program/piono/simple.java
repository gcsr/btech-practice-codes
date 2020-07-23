import javax.sound.sampled.*;
import javax.sound.midi.*;

public class simple
{
		static Synthesizer synthesizer;
		
		static MidiChannel drumChannel;
	public static void main(String[] gcs)throws MidiUnavailableException
	{
		synthesizer=MidiSystem.getSynthesizer();
		synthesizer.open();
		drumChannel=synthesizer.getChannels()[15];
		//int note=39;
		for(int note=0;note<127;note++)
		playNote(note);
	}
	private static void playNote(int note)
	{
		drumChannel.noteOn(note,90);
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		drumChannel.noteOff(60);
		
	}
}