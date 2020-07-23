import javax.sound.sampled.*;
import javax.sound.midi.*;

public class message
{
		static Synthesizer synthesizer;
		
		static MidiChannel drumChannel;
	public message()throws MidiUnavailableException
	{
		synthesizer=MidiSystem.getSynthesizer();
		synthesizer.open();
		drumChannel=synthesizer.getChannels()[15];
	
	}
	public static void playNote(int note)
	{
		System.out.println("playing note");
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