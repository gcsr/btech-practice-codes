import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;


public class PgmCharReader {
	
	public void readPGM(String filename){
		try {                        		    
		    DataInputStream din = new DataInputStream(new FileInputStream(filename));
		    byte b;
		    // process the top 4 header lines
		    while(true){
		    	b=din.readByte();
		    	System.out.print((char)b);
		    	if(b=='f')
		    		break;
		    }
		    /*if (!filetype.equalsIgnoreCase("p2")) {
		    	System.out.println("[readPGM]Cannot load the image type of "+filetype);
		    	return;
		    }*/
	   	   	din.close();
	    } catch(FileNotFoundException fe) {
	    	System.out.println("Had a problem opening a file.");
	    } catch (Exception e) {
	    	System.out.println(e.toString() + " caught in readPPM.");
	    	e.printStackTrace();
	    }
	}
	// overrides the paint method of Component class
	
	public static void main(String[] args) {
		// instantiate the PgmImage object according to the 
		//  command line argument
		PgmCharReader img;
		String filename ="default";
		String operation = "none";
		//if (args.length>0){
			filename = "D:/gc personal/books/face recog/orl_faces/s1/4.PGM";//args[0];
			img = new PgmCharReader(filename);
		//} else { 
			//img = new PgmImage();
			//filename = "default";
		//}
		/***************************************************
		 * Apply preferred image processing here:
		 **************************************************/
		if (operation.equalsIgnoreCase("fliph"))
			img.flipH();
			
		// set up the GUI for display the PgmImage object 
		JFrame f = new JFrame("PGM Image: "+filename+" Image Operation: " + operation);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.add(img);
		f.pack();
		f.setVisible(true);
	}


}
