import javax.swing.JFrame;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;

public class Temp extends JFrame{
	
	public Temp(){
		super("Created");
		//setSize(400,400);
		//setVisible(false);
		createSession();
	}
	
	private void createSession(){
		try {
			 
			ConfigFactory.setConfigLocation(
					"config/be.ehealth.technicalconnector.properties");

			//System.out.println(ConfigFactory.getConfigValidator().getProperty("KEYSTORE_DIR"));
	        //SessionInitializer.init("config/be.ehealth.technicalconnector.properties", true);
			
			//Session.getInstance().unloadSession();
			

			SessionManager sessionmgmt = Session.getInstance();

			if (!sessionmgmt.hasValidSession()) {
				//sessionmgmt.
				sessionmgmt.createSession("BilelJacob2015","BilelJacob2015");
			}else{
				sessionmgmt.unloadSession();
				sessionmgmt.createSession("BilelJacob2015","BilelJacob2015");
			}

		} catch (Exception e) {
			//System.out.println(e.toString());
			e.printStackTrace();
		}


	}

	

	
}
