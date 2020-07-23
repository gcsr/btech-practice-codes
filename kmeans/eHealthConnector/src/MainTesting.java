
import be.ehealth.technicalconnector.config.ConfigFactory;

import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;

public class MainTesting {

	public static void main(String args[]) {

		try {


			ConfigFactory.setConfigLocation(
					"config/be.ehealth.technicalconnector.properties");

			System.out.println(ConfigFactory.getConfigValidator().getProperty("KEYSTORE_DIR"));

			SessionManager sessionmgmt = Session.getInstance();

			if (!sessionmgmt.hasValidSession()) {
				sessionmgmt.createSessionEidOnly() ;
			
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
