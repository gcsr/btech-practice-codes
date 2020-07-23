import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// class to handle the CardFile received
public class CardFileHandler {

	// variable to handle file input
	BufferedReader br;

	// variable to store the filename
	File fileName;

	// variable to store the contents of the CardFile
	String line;

	// variable to store the IMEI retrieved from the IMEI File
	String IMEI;

	// variable to hold customer account number
	static String accountNo;

	// variable to store the no of characters in the CardFile
	int noOfChar;

	// variable to hold the path to the CardFile
	String cardFileName = "D:/AndroidWorkspace/SystemSide/CardFile.txt";

	// method to check if the CardFile exists
	boolean fileExists() {

		fileName = new File(cardFileName);
		if (!fileName.exists())
			return false;
		return true;

	}

	// Method to read the contents of the CardFile
	void read() {

		try {
			br = new BufferedReader(new FileReader(cardFileName));
			line = br.readLine();
			noOfChar = line.length();
			accountNo = line.substring(noOfChar - 15, noOfChar);
			br.close();
		} catch (Exception e) {
			System.out.println("CardFileHandler.read:" + cardFileName
					+ " could not be read");
		}
		IMEIFileHandler imeiFile = new IMEIFileHandler();
		IMEI = imeiFile.read();
		CardFileValidater card = new CardFileValidater();
		card.validate(accountNo, IMEI);

	}

	// to delete CardFile after use
	void delete() {

		fileName = new File(cardFileName);
		fileName.delete();

	}

}
