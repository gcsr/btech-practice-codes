/**
 * @(#)VLCPlayer.java
 *
 *
 * @author 
 * @version 1.00 2011/1/6
 */
 import  java.lang.Runtime;

public class VLCPlayer {
        
    /**
     * Creates a new instance of <code>VLCPlayer</code>.
     */
    public VLCPlayer() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
       Runtime.getRuntime().exec("http://localhost:6060/VideoLAN/VLC/vlc.exe");
       
       //following line executed successfully
       // Runtime.getRuntime().exec("c:/VideoLAN/VLC/vlc.exe");
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    }
}
