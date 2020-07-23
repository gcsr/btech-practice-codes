import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;


public class TestAudio {
	public static void main(String[] gcs){
		File soundFile = new File("sample.au");

		//Object that will hold the audio input stream for the sound
		Object currentSound=null;

		//Object that will play the audio input stream of the sound
		Clip clip;

		//Object that contains format information about the audio input stream
		AudioFormat format;

		//Load the audio input stream
		try {
			currentSound = AudioSystem.getAudioInputStream(soundFile);
		} catch(Exception e1) {
			System.out.println("Error loading file");            
		}

		try {
			//Get the format information from the Audio Input Stream
			AudioInputStream stream = (AudioInputStream) currentSound;
			format = stream.getFormat();

			
			float newRate = format.getSampleRate() * 2;

			System.out.println(newRate);
			//Create the FloatControl object with the clip object from the code above
			
			
			//Get information about the line
			DataLine.Info info = new DataLine.Info(Clip.class,
			stream.getFormat(),
			((int) stream.getFrameLength() * format.getFrameSize()));

			//Load clip information from the line information
			clip = (Clip) AudioSystem.getLine(info);

			FloatControl rateControl = (FloatControl) clip.getControl(FloatControl.Type.SAMPLE_RATE);

			//change the sampling rate
			rateControl.setValue(newRate);
			//Write the stream onto the clip
			System.out.println(newRate);
			clip.open(stream);

			//make the current sound the clip
			currentSound = clip;

			//start the clip
			clip.start();
			
			//loop the clip continuously
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			ex.printStackTrace();
			currentSound = null;
		}
	}
}
