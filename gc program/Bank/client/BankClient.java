import javax.swing.JFrame;

public class BankClient
{
	public static void main(String[] gcs)
	{
		BankClientFrame app=new BankClientFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(250,140);
		app.setVisible(true);
	}	
}