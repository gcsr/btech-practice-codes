
public class System1 {
	public static void main(String[] gcs)
	{
		Thread server=new Thread(new Server(gcs));
		Thread client=new Thread(new NetworkingGUI());
		
		server.start();
		client.start();
		
	}

}
