import java.applet.Applet;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class ImmediateCanvas3d1test extends JFrame
{
	
public ImmediateCanvas3d1test()
{
	
GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();

add(new ImmediateCanvas3D1(config));
setSize(500,500);
setVisible(true);

}	
public static void main(String[] gcs)
{
new ImmediateCanvas3d1test();
}
}