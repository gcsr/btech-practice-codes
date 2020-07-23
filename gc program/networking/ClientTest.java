import javax.swing.JFrame;

public class ClientTest {
        
   
    public static void main(String[] gcs)
    {
    	client app;
    	if(gcs.length==0)
    	app=new client("127.0.0.1");	
    	else
    		app=new client(gcs[0]);	
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	app.setVisible(true);
    	app.setSize(300,150);
    	app.runClient();
       
    }
}
