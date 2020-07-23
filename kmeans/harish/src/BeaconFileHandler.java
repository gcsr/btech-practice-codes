import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BeaconFileHandler {

	// variable to handle file input
	BufferedReader br;

	// variable to store the filename
	File fileName;

	// variable to store the customer name retrieved from Beacon File
	static String custInfo = "";

	// variable to hold the path to the Beacon File
	String beaconFileName = "D:/AndroidWorkspace/SystemSide/BeaconFile.txt";

	// method to check if the Beacon File exists
	boolean fileExists() {

		fileName = new File(beaconFileName);
		if (!fileName.exists())
			return false;
		return true;

	}

	// method to delete the Beacon File after use
	void delete() {

		fileName = new File(beaconFileName);
		fileName.delete();

	}

	// method to read the contents of Beacon File
	void read() {

		try {
			br = new BufferedReader(new FileReader(beaconFileName));
			custInfo = br.readLine();
			br.close();
		} catch (Exception e) {
			System.out.println("BeaconFileHandler.read:" + beaconFileName
					+ " could not be read");
		}
		WelcomeScreenHandler screen = new WelcomeScreenHandler();
		screen.display(custInfo);

	}

}
