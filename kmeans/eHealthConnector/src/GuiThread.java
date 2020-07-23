import javax.swing.JFrame;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;


public class GuiThread implements Runnable{
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Temp tmp=new Temp();
		tmp.setSize(400, 400);
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmp.setVisible(true);
	}
	
}
