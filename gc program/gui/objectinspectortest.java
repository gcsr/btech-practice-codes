import gui.objectinspector;



import javax.swing.JFrame;


public class objectinspectortest
{
	public static void main(String[] gcs)
	{
		objectinspector app=new objectinspector();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,400);
		app.setVisible(true);
	}
}