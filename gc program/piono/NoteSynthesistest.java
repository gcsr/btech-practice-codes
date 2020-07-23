import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.IOException;
import javax.sound.sampled.*;




public class NoteSynthesistest
{
	private static int SAMPLE_RATE=22050;
	private static  AudioFormat format=null;
	private static SourceDataLine line=null;
	private static double MAX_AMPLITUDE=32760;
	private static int MIN_FREQ=250;
	private static int MAX_FREQ=2000;
	
	public static void main(String[] gcs)
	{
		
		createOutput();
		play();
		System.exit(0);
	}
	private static void createOutput()
	{


			format=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,SAMPLE_RATE,16,2,4,SAMPLE_RATE,false);
			
			System.out.println("Audio Format :"+format);
			try
			{
			DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);
			if(!AudioSystem.isLineSupported(info))
			{
				System.out.println("unsupported line "+format);
				System.exit(0);
			}
			line=(SourceDataLine)AudioSystem.getLine(info);
			
			line.open(format);
			}
			catch(Exception e)
			{
			e.printStackTrace();
			System.exit(0);
			}


	}
	private static void play()
	{
		int maxSize=(int)Math.round((SAMPLE_RATE*format.getFrameSize())/MIN_FREQ);
		byte[] samples=new byte[maxSize];
		System.out.println("mas size is "+maxSize);
		line.start();
		double volume;
		for(int step=1;step<3;step++)
		{
			for(int freq=MIN_FREQ;freq<MAX_FREQ;freq+=step)
			{
				volume=1.0-(step/10.0);
				sendNote(freq,volume,samples);
			}
		}
		line.drain();
		line.stop();
		line.close();
	}
	
	private static void sendNote(int freq,double vollevel,byte[] samples)
	{
		if((vollevel<0.0)||(vollevel>1.0))
		{
			System.out.println("vol level should be between o and 1,using 0.9");
			vollevel=0.9;
		}
		double amplitude=vollevel*MAX_AMPLITUDE;
		int numSamplesInWave=(int)Math.round(((double)SAMPLE_RATE)/freq);
		int dx=0;
		System.out.println("numofsamplesinwave is : "+numSamplesInWave);
		for(int i=0;i<20/*numSamplesInWave*/;i++)
		{
			double sine=i/2;
			//3*(Math.sin((double)i/numSamplesInWave)*2.0*Math.PI)+2*(Math.cos((double)i/numSamplesInWave)*2.0*Math.PI);
		    //System.out.println("sine is : "+sine);
			int sample=(int)(sine*amplitude);
			samples[dx+0]=(byte)(sample&0xFF);
			samples[dx+1]=(byte)((sample>>8)&0xFF);
			samples[dx+2]=(byte)(sample&0xFF);
			samples[dx+3]=(byte)((sample>>8)&0xFF);
			dx+=4;
			
		}
		int offset=0;
		while(offset<dx)
		offset+=line.write(samples,offset,dx-offset);
	}
}