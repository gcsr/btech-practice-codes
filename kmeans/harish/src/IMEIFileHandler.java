import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// class to handle the IMEI File received
public class IMEIFileHandler {

	// variable to handle file input
	BufferedReader br;

	// variable to store the filename
	File fileName;

	// variable to store the IMEI retrieved from the IMEI File
	String IMEI;

	// variable to hold the path to the IMEI File
	String imeiFileName = "D:/AndroidWorkspace/SystemSide/IMEIFile.txt";

	// Method to read the contents of the IMEI File
	String read() {

		try {
			br = new BufferedReader(new FileReader(imeiFileName));
			IMEI = br.readLine();
			br.close();
		} catch (Exception e) {
			System.out.println("IMEIFileHandler.read:" + imeiFileName
					+ " could not be read");
		}

		return IMEI;

	}

	// to delete IMEI File after use
	void delete() {

		fileName = new File(imeiFileName);
		fileName.delete();

	}

}
