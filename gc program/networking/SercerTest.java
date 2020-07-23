import javax.swing.JFrame;

public class SercerTest {
        

    public static void main(String[] args) 
    {
    	server app=new server();
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	app.setVisible(true);
    	app.setSize(300,150);
    	app.runServer();
       
    }
}
