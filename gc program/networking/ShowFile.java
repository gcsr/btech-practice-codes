import java.io.FileReader;
import java.io.PrintWriter;

public class ShowFile
{
	public static void main(String[] gcs)throws Exception
	{
		FileReader fir=new FileReader("InputOutputTest.java");
		PrintWriter pr=new PrintWriter(System.out,true);
		char[] c=new char[512];
		int count;
		while((count=fir.read(c))!=-1)
			pr.write(c,0,count);
			
		//pr.flush();
		pr.close();
		fir.close();	
		
	}
}