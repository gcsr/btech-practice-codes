import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class NullPrinter extends JFrame
{
	public NullPrinter()
	{
		try{
			
			JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result=fileChooser.showOpenDialog(this);
	
		File fileName=fileChooser.getSelectedFile()	;
		String ss=fileName.getAbsolutePath();
		
		PrintWriter writer=new PrintWriter(new File(ss,"r"));
		if(writer==null)
			throw new Exception("no printwriter object");
			
		writer.flush();	
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] gcs)
	{
		new NullPrinter();
		
	}
}