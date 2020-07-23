import java.io.IOException;

public class SystemRunner {

	public static void main(String[] args) {

		// object to handle the main screen
		final MainScreenHandler mainScreen = new MainScreenHandler();

		// object to handle the CardFile
		final CardFileHandler card = new CardFileHandler();

		// object to handle the Beacon File
		final BeaconFileHandler beaconFile = new BeaconFileHandler();

		// object to handle the PIN File
		final PinFileHandler pinFile = new PinFileHandler();

		// object to handle the IMEI File
		final IMEIFileHandler imeiFile = new IMEIFileHandler();

		// object to handle the account validation
		final AccountValidater account = new AccountValidater();

		// object to handle the screen timeout
		final ScreenTimeOutHandler timeoutScreen = new ScreenTimeOutHandler();

		Thread t = new Thread() {

			public void run() {

				while (beaconFile.fileExists()) {

					int count1 = 20;
					int count2 = 20;

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						System.out
								.println("SystemRunner.thread1:Thread error! ");
					}
					beaconFile.read();
					beaconFile.delete();
					while (!card.fileExists() && count1 != 0) {

						try {
							sleep(1000);
							count1--;
						} catch (InterruptedException e) {
							System.out
									.println("SystemRunner.thread2:Thread error! ");
						}

					}
					if (count1 != 0) {

						card.read();
						while (!pinFile.fileExists() && count2 != 0) {

							try {
								sleep(1000);
								count2--;
							} catch (InterruptedException e) {
								System.out
										.println("SystemRunner.thread3:Thread error! ");
							}

						}
						if (count2 != 0) {

							pinFile.read();
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								System.out
										.println("SystemRunner.thread4:Thread error! ");
							}
							account.validate();

						} else
							timeoutScreen.display();

					} else
						timeoutScreen.display();
					card.delete();
					imeiFile.delete();
					pinFile.delete();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.out
								.println("SystemRunner.thread5:Thread error! ");
					}
					try {
						Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
					} catch (IOException e) {
						System.out
								.println("SystemRunner:Thread error! Could not kill the browser");
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						System.out
								.println("SystemRunner.thread6:Thread error! ");
					}
					mainScreen.display();

				}

			}

		};

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("SystemRunner.thread0:Thread error! ");
		}
		mainScreen.display();
		while (true)
			t.run();
	}
}




