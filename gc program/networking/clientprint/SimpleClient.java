


import javax.swing.JFrame;

public class SimpleClient {
    public static void main(String[] args) {
       // System.setSecurityManager(new RMISecurityManager());
        //(new SimpleClientFrame()).show();
         
       SimpleClientFrame client=new SimpleClientFrame();
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setVisible(true);
        client.setSize(250, 200);
    }
} 

