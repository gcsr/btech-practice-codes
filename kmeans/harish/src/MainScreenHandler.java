import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainScreenHandler {

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to hold the path to the main screen php file
	String mainScreenFileName = "D:/wamp/www/MainScreen.php";

	// variable to hold the URI of the main screen
	String mainScreenURI = "http://localhost/MainScreen.php";

	// variable to hold the contents of the php file,
	String firstLine;
	String restOfFile;

	// method to display the main screen
	void display() {

		firstLine = "<?php echo\"<b>Welcome"
				+ ". <br> Pair with the ATM to use the ATM Card via Cellphone Facility!</b>\";?>";
		restOfFile = "<html>" + "<head>" + "<title> Main Screen </title>"
				+ "</head>" + "<body>"
				+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
				+ "<center>" + firstLine + "</center>" + "</body>" + "</html>";
		try {
			File f = new File(mainScreenFileName);
			f.createNewFile();
			fout = new FileOutputStream(mainScreenFileName);
			pout = new PrintStream(fout);
			pout.print(restOfFile);
		} catch (Exception e) {
			System.out.print("MainScreenHandler.display:" + mainScreenFileName
					+ " could not be written");
		}
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.err
					.println("MainScreenHandler.display:Desktop utility is not supported");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.out
					.println("MainScreenHandler.display:Desktop utility doesn't support the browse action");
			System.exit(1);
		}
		try {
			java.net.URI uri = new java.net.URI(mainScreenURI);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out
					.println("MainScreenHandler.display:Sorry! Welcome screen could not be popped up");
		}

	}
}
