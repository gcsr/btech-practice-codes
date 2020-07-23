import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// class to handle the PIN File received
class PinFileHandler {

	// variable to handle fiel input
	BufferedReader br;

	// variable to store the filename
	File fileName;

	// variable to store the contents of the PIN File
	String line;

	// variable to store the PIN retrieved form the PIN File
	String PIN;

	// variable to hold customer account number
	String accountNo;

	// variable to store the no of characters in the CardFile
	int noOfChar;

	// CardFile location
	String cardFileName = "D:/AndroidWorkspace/SystemSide/CardFile.txt";

	// PIN File location
	String pinFileName = "D:/AndroidWorkspace/SystemSide/PinFile.txt";

	// method to check if the PIN File exists
	boolean fileExists() {

		fileName = new File(pinFileName);

		if (!fileName.exists())
			return false;
		return true;

	}

	// Method to read the PIN from the PIN File
	void read() {

		/*
		 * try { br = new BufferedReader(new FileReader(cardFileName));
		 * 
		 * while ((line = br.readLine()) != null) { noOfChar = line.length();
		 * accountNo = line.substring(noOfChar - 15, noOfChar); }
		 * 
		 * br.close(); } catch (Exception e) {
		 * System.out.println("PINFileHandler:" + cardFileName +
		 * " could not be read"); }
		 */
		accountNo = CardFileHandler.accountNo;
		try {
			br = new BufferedReader(new FileReader(pinFileName));
			PIN = br.readLine();

			br.close();
		} catch (Exception e) {
			System.out.println("PINFileHandler.read:" + pinFileName
					+ " could not be read");
		}
		PinFileValidater pin = new PinFileValidater();
		pin.validate(accountNo, PIN);

	}

	// method to delete the PIN file after use
	void delete() {

		fileName = new File(pinFileName);
		fileName.delete();

	}

}
