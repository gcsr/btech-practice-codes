import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.Action;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.IOException;
public class CompressFileFrame extends ExitingFrame
{
	private FileTextField starting;
	private FileTextField ending;
	protected void buildGui()
	{
		starting=new FileTextField("File to Compress");
		ending=new FileTextField(new CompressFileAction(),"Destination File");
		JPanel newContentPane=new JPanel(new GridLayout(2,1));
		newContentPane.add(starting);
		newContentPane.add(ending);
		setContentPane(newContentPane);
	}
	private class CompressFileAction extends AbstractAction
	{
		public CompressFileAction()
		{
			putValue(Action.NAME,"Compress");
			putValue(Action.SHORT_DESCRIPTION,"Copy and compress the file");
		}
		public void actionPerformed(ActionEvent e)
		{
			InputStream source=starting.getFileInputStream();
			OutputStream dest=ending.getFileOutputStream();
			if((source!=null)&&(dest!=null))
			{
				try{
					BufferedInputStream bufferedSource=new BufferedInputStream(source);
					BufferedOutputStream bufferedDest=new BufferedOutputStream(dest);
					GZIPOutputStream zippedDest=new GZIPOutputStream(bufferedDest);
					
					copy(bufferedSource,zippedDest);
					bufferedSource.close();
					zippedDest.close();
				}
				catch(IOException ev)
				{
					ev.printStackTrace();
				}
			}
			
		}
		private int copy(InputStream source,OutputStream dest)throws IOException
		{
			int nextByte;
			int numOfBytesCopied=0;
			while((nextByte=source.read())!=-1)
			{
				dest.write(nextByte);
				numOfBytesCopied++;
				
			}
			dest.flush();
			return numOfBytesCopied;
			
		}
		
	}
}