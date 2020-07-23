import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;



// class to handle time out case
public class ScreenTimeOutHandler {

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to hold the path to the time out php file
	String screenTimeOutFileName = "C:/wamp/www/ScreenTimeOut.php";

	// variable to hold the URI of the time out php file
	String screenTimeOutURI = "http://localhost/ScreenTimeOut.php";

	// variable to hold the contents of the php file,
	String firstLine;
	String restOfFile;

	// method to display the time out screen
	void display() {

		firstLine = "<?php echo\"Sorry <b>" + BeaconFileHandler.custInfo
				+ "</b>. Time Out!\";" + "?>";
		restOfFile = "<html>" + "<head>" + "<title> Time Out </title>"
				+ "</head>" + "<body>"
				+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
				+ "<center>" + firstLine + "</center>" + "</body>" + "</html>";
		try {
			File f = new File(screenTimeOutFileName);
			f.createNewFile();
			fout = new FileOutputStream(screenTimeOutFileName);
			pout = new PrintStream(fout);
			pout.print(restOfFile);
		} catch (Exception e) {
			System.out.print("ScreenTimeOutHandler.display:"
					+ screenTimeOutFileName + " could not be written");
		}
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.err
					.println("ScreenTimeOutHandler.display:Desktop utility is not supported");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.out
					.println("ScreenTimeOutHandler.display:Desktop utility doesn't support the browse action");
			System.exit(1);
		}
		try {
			java.net.URI uri = new java.net.URI(screenTimeOutURI);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out
					.println("ScreenTimeOutHandler.display:Sorry! Validator screen could not be popped up");
		}

	}

}
