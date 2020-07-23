import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class MainClass {
	public static void main(String[] gcs){
		 try {
	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception fail) {
	        }
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        
		    }
		});		
	}

}
