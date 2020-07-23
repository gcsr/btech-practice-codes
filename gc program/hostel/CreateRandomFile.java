import java.io.RandomAccessFile;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.File;

public class CreateRandomFile
{
	private static final int NUMBER_RECORDS=100;
	public static void main(String[] gc)
	{
		CreateRandomFile app=new CreateRandomFile();
		
		for(int i=0;i<10;i++)
		app.createFile("07701A050"+i+".dat");

	}
	private void createFile(String ss)
	{
		
		
		File fileName=new File(ss)	;
		
		if(fileName==null||fileName.getName().equals(""))
			JOptionPane.showMessageDialog(null,"Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
		
		else
		{
		
			try
			{
				RandomAccessFile file=
				new RandomAccessFile(fileName,"rw");
				
				RandomAccessAccountRecord blankRecord=
				new RandomAccessAccountRecord();
				
				for(int count=0;count<100;count++)
				{
					blankRecord.write(file);
				}
				
				file.close();
				
				JOptionPane.showMessageDialog(null,"Created File "+fileName,"Status",JOptionPane.INFORMATION_MESSAGE);
				
				
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null,"Error Processing File","Error Processing File",JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}	

	}
}