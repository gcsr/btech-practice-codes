package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;

import biz.source_code.dsp.filter.FilterPassType;
import biz.source_code.dsp.sound.IirFilterAudioInputStreamExstrom;

public class Logics {
	 private static final int EXTERNAL_BUFFER_SIZE = 1280000;
	 
	public static void playSong(){
		new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try {
			    	   File f = new File("output2.au");
			    	   if(!f.exists()){
			    		 f = new File("output.au");
			    	   }
			    	   AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
			    	   Clip clip = AudioSystem.getClip();
			    	   clip.open(audioIn);
			    	   clip.start();
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();
			  				
		
	}
	
	public static String getFilePath(){
		JFileChooser fileChooser;
		fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result=fileChooser.showOpenDialog(null);
			
			if(result==JFileChooser.CANCEL_OPTION)
				return"";
			
		return fileChooser.getSelectedFile().getAbsolutePath();
			 
	}
	
	public static void filterWavFile (String inputFileName, FilterPassType filterPassType, int filterOrder, double fcf1, double fcf2, String outputFileName) throws Exception {
		   AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(inputFileName));
		   AudioInputStream filterStream = IirFilterAudioInputStreamExstrom.getAudioInputStream(inputStream, filterPassType, filterOrder, fcf1, fcf2);
		   AudioSystem.write(filterStream, AudioFileFormat.Type.AU, new File(outputFileName)); 
	}
	
	public static String processAudioFile(String fileName){
		File soundFile = new File(fileName);

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

					
					 File outFile=null;
					  try {
					   
					    outFile = new File("output.au");     
					  } catch (NullPointerException ex) {
					    System.out.println("Error: one of the ConvertFileToAIFF" +" parameters is null!");
					    return "";
					  }
					  
					AudioSystem.write(stream,
							AudioSystem.getAudioFileFormat(soundFile).getType(), outFile);
					
					return ""+format.getFrameRate();
							

				} catch (Exception ex) {
					ex.printStackTrace();
					currentSound = null;
					
					return "";
				}
				
				

	}
	
	public static void changeSamplingRate(String text){
		  
		
		  AudioFileFormat inFileFormat;
		  File inFile;
		  File outFile;
		  try {
		    inFile = new File("output.au");
		    outFile = new File("output2.au");     
		  } catch (NullPointerException ex) {
		    System.out.println("Error: one of the ConvertFileToAIFF" +" parameters is null!");
		    return;
		  }
		  try {
		    // query file type
		    inFileFormat = AudioSystem.getAudioFileFormat(inFile);
		    AudioFormat outDataFormat = new AudioFormat(Float.parseFloat(text),
		    		8, (int) 1, true, false);
		    
		    AudioInputStream lowResAIS;         
		    if (AudioSystem.isConversionSupported(outDataFormat,   
		      inFileFormat.getFormat())) {
		    	
		     AudioInputStream inFileAIS = 
		 		        AudioSystem.getAudioInputStream(inFile);
		 		      inFileAIS.reset(); // rewind
		 		      
		      lowResAIS = AudioSystem.getAudioInputStream
		        (outDataFormat, inFileAIS);
		      inFileAIS.reset();
		      lowResAIS.reset(); 
		      System.out.println(inFileAIS.available());
		      System.out.println(lowResAIS.available());
		      AudioSystem.write(lowResAIS,
			           inFileFormat.getType(), outFile);
		      System.out.println(lowResAIS.available());
		    }else{
		    	System.out.println("formatting convertion not supported in java");
		    }
		   
		  } catch (UnsupportedAudioFileException e) {
		    System.out.println("Error: " + inFile.getPath()
		        + " is not a supported audio file type!");
		    return;
		  } catch (IOException e) {
		    System.out.println("Error: failure attempting to read " 
		      + inFile.getPath() + "!");
		    return;
		  }
		
	}
	
		
	  public static AudioFormat getAudioFormat() {
	        float sampleRate = 16000;
	        int sampleSizeInBits = 8;
	        int channels = 2;
	        boolean signed = true;
	        boolean bigEndian = true;
	        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
	                                             channels, signed, bigEndian);
	        return format;
	 }
	
	  
	 public static  void recordAudio(){
		  final long RECORD_TIME = 10000;  // 1 minute
			 
		    // path of the wav file
		    File wavFile = new File("sample.au");
		 
		    // format of audio file
		    AudioFileFormat.Type fileType = AudioFileFormat.Type.AU;
		 
		    // the line from which audio data is captured
		   
			try {
	            AudioFormat format =Logics. getAudioFormat();
	            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	 
	            // checks if system supports the data line
	            if (!AudioSystem.isLineSupported(info)) {
	                System.out.println("Line not supported");
	                System.exit(0);
	            }
	            final TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
	            line.open(format);
	            line.start();   // start capturing
	 
	            Thread stopper = new Thread(new Runnable() {
	                public void run() {
	                    try {
	                        Thread.sleep(RECORD_TIME);
	                    } catch (InterruptedException ex) {
	                        ex.printStackTrace();
	                    }
	                    line.stop();
	                    line.close();
	                    System.out.println("Finished");
	                }
	            });
	     
	            stopper.start();
	     
	            System.out.println("Start capturing...");
	 
	            AudioInputStream ais = new AudioInputStream(line);
	 
	            System.out.println("Start recording...");
	 
	            // start recording
	            AudioSystem.write(ais, fileType, wavFile);
	 
	        } catch (LineUnavailableException ex) {
	            ex.printStackTrace();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	
	  }
	 
	 public static double getMaxFrequency(String fileName){
		 /* This code is based on the example found at:
		 http://www.jsresources.org/examples/SimpleAudioPlayer.java.html */
		 //Create a global buffer size


		 //Get the location of the sound file
		 File soundFile = new File(fileName);

		 //Load the Audio Input Stream from the file        
		 AudioInputStream audioInputStream = null;
		 try {
		 	audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		 } catch (Exception e) {
		 	e.printStackTrace();
		 	System.exit(1);
		 }

		 //Get Audio Format information
		 AudioFormat audioFormat = audioInputStream.getFormat();

		 //Handle opening the line
		 SourceDataLine	line = null;
		 DataLine.Info	info = new DataLine.Info(SourceDataLine.class, audioFormat);
		 try {
		 	line = (SourceDataLine) AudioSystem.getLine(info);
		 	line.open(audioFormat);
		 } catch (LineUnavailableException e) {
		 	e.printStackTrace();
		 	System.exit(1);
		 } catch (Exception e) {
		 	e.printStackTrace();
		 	System.exit(1);
		 }

		 //Start playing the sound
		// line.start();

		 //Write the sound to an array of bytes
		 int nBytesRead = 0;
		 byte[]	abData = new byte[EXTERNAL_BUFFER_SIZE];
		 while (nBytesRead != -1) {
		 	try {
		      		nBytesRead = audioInputStream.read(abData, 0, abData.length);

		 	} catch (IOException e) {
		      		e.printStackTrace();
		 	}
		 	if (nBytesRead >= 0) {
		      		int nBytesWritten = line.write(abData, 0, nBytesRead);
		 	}

		 }

		 //close the line      
		 line.drain();
		 line.close();
		 
		 
		 
		 
		//Calculate the sample rate
		 float sample_rate = audioFormat.getSampleRate();
		 System.out.println("sample rate = "+sample_rate);

		 //Calculate the length in seconds of the sample
		 float T = audioInputStream.getFrameLength() / audioFormat.getFrameRate();
		 System.out.println("T = "+T+ " (length of sampled sound in seconds)");

		 //Calculate the number of equidistant points in time
		 int n = (int) (T * sample_rate) / 2;
		 System.out.println("n = "+n+" (number of equidistant points)");

		 //Calculate the time interval at each equidistant point
		 float h = (T / n);
		 System.out.println("h = "+h+" (length of each time interval in seconds)");
		 
		 
		 
		 //Determine the original Endian encoding format
		 boolean isBigEndian = audioFormat.isBigEndian();

		 //this array is the value of the signal at time i*h
		 int x[] = new int[n];

		 //convert each pair of byte values from the byte array to an Endian value
		 for (int i = 0; i < n*2; i+=2) {
		 	int b1 = abData[i];
		 	int b2 = abData[i + 1];
		 	if (b1 < 0) b1 += 0x100;
		 	if (b2 < 0) b2 += 0x100;

		 	int value;

		 	//Store the data based on the original Endian encoding format
		 	if (!isBigEndian) value = (b1 << 8) + b2;
		 	else value = b1 + (b2 << 8);
		 	x[i/2] = value;
		 }
		 
		 double amplitude=0;
		 double frequency=0;
		 double maxFrequency=0;
		//do the DFT for each value of x sub j and store as f sub j
		 double f[] = new double[n/2];
		 double twoPInjk;
		 int j=0;
		 
		 
		 System.out.println("n is "+n);
		 for (j = 0; j < n; j++) {
			 /*
		 	firstSummation = 0;
		 	secondSummation = 0;

		 	for (k = 0; k < n; k++) {
		      		twoPInjk = (middle) * (j * k);
		      		firstSummation +=  x[k] * Math.cos(twoPInjk);
		      		secondSummation += x[k] * Math.sin(twoPInjk);
		 	}

		    f[j] = Math.abs( Math.sqrt(Math.pow(firstSummation,2) + 
		    Math.pow(secondSummation,2)) );

		 	amplitude = 2 * f[j]/n;*/
		 	frequency = j * h / T * sample_rate;
		 	if(frequency>maxFrequency)
		 		maxFrequency=frequency;
		 	//System.out.println(j);
		 	//System.out.println(maxFrequency);
		 }
		 System.out.println(" max frequency is "+maxFrequency);
		 return maxFrequency;
	 }


}
