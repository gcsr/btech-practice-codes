import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;

// class to validate the CardFile
class CardFileValidater {

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to hold the path to the CardFile Validater php file
	String cardValidaterFileName = "C:/wamp/www/CardFileValidater.php";

	// variable to store the address of the php file to be executed
	String phpFileURI = "http://localhost/CardFileValidater.php";

	// variable to hold the contents of the php file,
	String firstLine;
	String restOfFile;

	// method to validate the CardFile
	void validate(String accountNo, String IMEI) {

		firstLine = "<?php $IMEI=\""
				+ IMEI
				+ "\";"
				+ "$mysql=mysql_connect(\"localhost\",\"root\",\"\") or die(\"LOGIN FAILED\");"
				+ "mysql_select_db(\"bankdatabase\") or die(\"DATABASE ACCESS FAILED\");"
				+ "$result=mysql_query(\"select IMEI from customerinfo where AccountNo=\'"
				+ accountNo
				+ "\'\""
				+ ") or die(\"SQL QUERY FAILED\");"
				+ "$filehandler=fopen(\"IMEIResult.txt\",\"w\") or die (\"FILE OPENING FAILED\");"
				+ "while($array=mysql_fetch_array($result)){"
				+ "$fetchedIMEI=$array[\"IMEI\"];" + "if($IMEI==$fetchedIMEI){"
				+ "$auth=\"OK\";" + "fwrite($filehandler,$auth );" + "}"
				+ "else {" + "$auth=\"NOT OK\";"
				+ "fwrite($filehandler,$auth);}}" + "echo\"Welcome <b>"
				+ BeaconFileHandler.custInfo
				+ "</b>.<br> Please send your PIN File\";" + "?>";
		restOfFile = "<html>" + "<head>" + "<title> CardValidater </title>"
				+ "</head>" + "<body>"
				+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
				+ "<center>" + firstLine + "</center>" + "</body>" + "</html>";

		try {
			File f = new File(cardValidaterFileName);
			f.createNewFile();
			fout = new FileOutputStream(cardValidaterFileName);
			pout = new PrintStream(fout);
			pout.print(restOfFile);
		} catch (Exception e) {
			System.out.print("CardFileValidater.validate:"
					+ cardValidaterFileName + " could not be written");
		}
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.out
					.println("CardFileValidater.validate:Desktop utility is not supported");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.out
					.println("CardFileValidater.validate:Desktop utility doesn't support the browse action");
		}
		try {
			java.net.URI uri = new java.net.URI(phpFileURI);
			desktop.browse(uri);

		} catch (Exception e) {
			System.out
					.println("CardFileValidater.validate:Cannot execute CardFileValidater.php");
		}

	}
}

// class to validate the PIN
class PinFileValidater {

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to hold the path to the PIN File validater php file
	String pinValidaterFileName = "C:/wamp/www/PinFileValidater.php";

	// variable to hold the URI of the PIN File validater php file
	String phpFileURI = "http://localhost/PinFileValidater.php";

	// variable to hold the contents of the php file,
	String firstLine;
	String restOfFile;

	// method to validate the PIN
	void validate(String accountNo, String PIN) {

		firstLine = "<?php $PIN=\""
				+ PIN
				+ "\";"
				+ "$mysql=mysql_connect(\"localhost\",\"root\",\"\") or die(\"LOGIN FAILED\");"
				+ "mysql_select_db(\"bankdatabase\") or die(\"DATABASE ACCESS FAILED\");"
				+ "$result=mysql_query(\"select PIN from customerinfo where AccountNo=\'"
				+ accountNo
				+ "\'\""
				+ ") or die(\"SQL QUERY FAILED\");"
				+ "$filehandler=fopen(\"PINResult.txt\",\"w\") or die (\"FILE OPENING FAILED\");"
				+ "while($array=mysql_fetch_array($result)){"
				+ "$fetchedPIN=$array[\"PIN\"];" + "if($PIN==$fetchedPIN){"
				+ "$auth=\"OK\";" + "fwrite($filehandler,$auth);" + "}"
				+ "else {" + "$auth=\"NOT OK\";"
				+ "fwrite($filehandler,$auth);}}" + "echo\"Welcome <b>"
				+ BeaconFileHandler.custInfo + "</b>. <br> PIN Received\";"
				+ "?>";
		restOfFile = "<html>" + "<head>" + "<title> PINValidater </title>"
				+ "</head>" + "<body>"
				+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
				+ "<center>" + firstLine + "</center>" + "</body>" + "</html>";

		try {
			File f = new File(pinValidaterFileName);
			f.createNewFile();
			fout = new FileOutputStream(pinValidaterFileName);
			pout = new PrintStream(fout);
			pout.print(restOfFile);
		} catch (Exception e) {
			System.out.print("PinFileValidater.validate:"
					+ pinValidaterFileName + " could not be written");
		}
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.out
					.println("PinFileValidater.validate:Desktop utility is not supported");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.out
					.println("PinFileValidater.validate:Desktop utility doesn't support the browse action");
		}
		try {
			java.net.URI uri = new java.net.URI(phpFileURI);
			desktop.browse(uri);

		} catch (Exception e) {
			System.out
					.println("PinFileValidater.validate:Cannot execute PinFileValidater.php");
		}

	}

}

// class to validate the customer's account
class AccountValidater {

	// variable to handle file input
	BufferedReader br;

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to hold the path to the IMEIResult file
	String imeiResultFileName = "C:/wamp/www/IMEIResult.txt";

	// / variable to hold the path to the PINResult file
	String pinResultFileName = "C:/wamp/www/PINResult.txt";

	// variable to hold the path to the account validation php file
	String accountValidatorFileName = "C:/wamp/www/AccountValidation.php";

	// variable to hold the URI of the account validation php file
	String accountValidatorURI = "http://localhost/AccountValidation.php";

	// variable to store the contents of IMEIResult File
	String result1;

	// variable to store the contents of PINResult File
	String result2;

	// variable to hold the contents of the php file,
	String firstLine;
	String restOfFile;

	// method to validate the account
	void validate() {

		try {

			br = new BufferedReader(new FileReader(imeiResultFileName));
			result1 = br.readLine();
			br.close();
			br = new BufferedReader(new FileReader(pinResultFileName));
			result2 = br.readLine();
			br.close();

		} catch (Exception e) {
			System.out.println("AccountValidater.validate:"
					+ imeiResultFileName + " and " + pinResultFileName
					+ " could not be read");
		}

		if (result1.equals("OK") && result2.equals("OK")) {
			firstLine = "<?php echo\"Welcome <b>" + BeaconFileHandler.custInfo
					+ "</b>. <br> Your account has now been activated!\";"
					+ "?>";
			restOfFile = "<html>" + "<head>" + "<title> Account Info </title>"
					+ "</head>" + "<body>"
					+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
					+ "<center>" + firstLine + "</center>" + "</body>"
					+ "</html>";
		} else {
			firstLine = "<?php echo\"Welcome <b>" + BeaconFileHandler.custInfo
					+ "</b>. Authentication failed!\";" + "?>";
			restOfFile = "<html>" + "<head>" + "<title> Account Info </title>"
					+ "</head>" + "<body>"
					+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
					+ "<center>" + firstLine + "<center>" + "</body>"
					+ "</html>";
		}
		try {
			File f = new File(accountValidatorFileName);
			f.createNewFile();
			fout = new FileOutputStream(accountValidatorFileName);
			pout = new PrintStream(fout);
			pout.print(restOfFile);
		} catch (Exception e) {
			System.out.print("AccountValidater.validate:"
					+ accountValidatorFileName + " could not be written");
		}
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.err
					.println("AccountValidater.validate:Desktop utility is not supported");
			System.exit(1);
		}

		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.out
					.println("AccountValidater.validate:Desktop utility doesn't support the browse action");
			System.exit(1);
		}
		try {
			java.net.URI uri = new java.net.URI(accountValidatorURI);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out
					.println("AccountValidater.validate:Sorry! Validator screen could not be popped up");
		}	
	}
}
