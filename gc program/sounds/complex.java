import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import java.security.AccessControlException;

public class complex
{
	public static void main(String[] gcs)
	{
		new cplex();
	}
}

class cplex implements LineListener
{

	Clip clip;
	public void update(LineEvent event)
	{
		if(event.getType()==LineEvent.Type.STOP)
		{
			System.out.println("exiting");
			clip.stop();
			event.getLine().close();
			System.exit(0);
		}
	}
	public cplex()
	{

		loadclip();
		play();


	}
	public void play()
	{
		if(clip!=null)
			clip.start();
	}

	public void loadclip()
	{
		try{


			 AudioInputStream stream=AudioSystem.getAudioInputStream(getClass().getResource("gc.wav"));

			AudioFormat format=stream.getFormat();
			if((format.getEncoding()==AudioFormat.Encoding.ULAW)||(format.getEncoding()==AudioFormat.Encoding.ALAW))
			{
				AudioFormat newformat=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
				format.getSampleRate(),
				format.getSampleSizeInBits()*2,
				format.getChannels(),
				format.getFrameSize()*2,
				format.getFrameRate(),true);
				stream=AudioSystem.getAudioInputStream(newformat,stream);
				System.out.println("converted audioformat"+newformat);
				format=newformat;

			}
			DataLine.Info info=new DataLine.Info(Clip.class,format);
			if(!AudioSystem.isLineSupported(info))
			{
				System.out.println("unsupported clip file pp.MID");
				System.exit(0);
			}
			clip=(Clip)AudioSystem.getLine(info);
			clip.addLineListener(this);
			clip.open(stream);
			stream.close();

		}
		catch(UnsupportedAudioFileException e)
		{
			System.out.println("unsupported audio file");
			e.printStackTrace();
			System.exit(0);
		}
		catch(LineUnavailableException e)
		{
			System.out.println("lineunavailable");
			e.printStackTrace();
			System.exit(0);
		}

		catch(IOException e)
		{
			System.out.println("could not read");
			System.exit(0);

		}
		catch(Exception e)
		{
			System.out.println("problem with pp.MID");
			e.printStackTrace();
			System.exit(0);

		}



	}

}