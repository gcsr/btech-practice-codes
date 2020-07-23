import java.io.File;
import java.io.FileInputStream;

public class Reading {
	public static void main(String[] gcs)
	{
		try
		{
			FileInputStream fin=new FileInputStream("D:/gc personal/books/3.pdf");
			File file=new File("D:/gc personal/books/3.pdf");
			
			
			System.out.println(file.length());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}