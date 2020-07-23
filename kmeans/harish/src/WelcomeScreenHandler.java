import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

// class to handle the welcome screen after receiving the Beacon File
class WelcomeScreenHandler {

	// variable to handle file output
	FileOutputStream fout;

	// variable to handle file write
	PrintStream pout;

	// variable to hold the path to the welcome screen php file
	String welcomeScreenFileName = "C:/wamp/www/WelcomeScreen.php";

	// variable to hold the URI of the welcome screen
	String welcomeScreenURI = "http://localhost/WelcomeScreen.php";

	// variable to hold the contents of the php file,
	String firstLine;
	String restOfFile;

	// method to display the welcome screen
	void display(String customer) {

		firstLine = "<?php echo\"Welcome <b>" + customer
				+ "</b>. You are now paired with this ATM!"
				+ "<br> Please send your CardFile\";?>";
		restOfFile = "<html>" + "<head>" + "<title> Welcome Screen </title>"
				+ "</head>" + "<body>"
				+ "<br><br><br><br><br><br><br><br><br><br><br><br>"
				+ "<center>" + firstLine + "</center>" + "</body>" + "</html>";
		try {
			File f = new File(welcomeScreenFileName);
			f.createNewFile();
			fout = new FileOutputStream(welcomeScreenFileName);
			pout = new PrintStream(fout);
			pout.print(restOfFile);
		} catch (Exception e) {
			System.out.print("WelcomeScreenHandler.display:"
					+ welcomeScreenFileName + " could not be written");
		}
		if (!java.awt.Desktop.isDesktopSupported()) {
			System.err
					.println("WelcomeScreenHandler.display:Desktop utility is not supported");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			System.out
					.println("WelcomeScreenHandler.display:Desktop utility doesn't support the browse action");
			System.exit(1);
		}
		try {
			java.net.URI uri = new java.net.URI(welcomeScreenURI);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out
					.println("WelcomeScreenHandler.display:Sorry! Welcome screen could not be popped up");
		}

	}
}
