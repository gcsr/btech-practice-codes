/**
 * @(#)CLessServerTest.java
 *
 *
 * @author 
 * @version 1.00 2010/7/9
 */
 import javax.swing.JFrame;

public class CLessClientTest {
        
     
    public static void main(String[] args) {
       CLessClient app=new CLessClient();
       app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       app.setSize(300,150);
       app.setVisible(true);
       app.waitForPackets();
    }
}
