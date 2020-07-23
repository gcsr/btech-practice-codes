import javax.swing.JFrame;


public class NetworkingGUI implements Runnable{

	public NetworkingGUI()
	{
		
	}
	public void run() {
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println("run both servers again");
			return;
		}

		NetworkingGUIFrame app=new NetworkingGUIFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO Auto-generated method stu;
		

	}

}
