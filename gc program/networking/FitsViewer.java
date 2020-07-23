import java.net.URL;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.IOException;
import java.awt.image.ImageProducer;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.awt.Graphics;

public class FitsViewer extends JFrame
{
	private Image image;
	private URL url;
	public FitsViewer(URL u)
	{
		super(u.getFile());
		this.url=u;
	}
	public void loadImage()throws IOException
	{
		Object content=this.url.getContent();
		ImageProducer producer;
		
		try{
			producer=(ImageProducer)content;
		}
		catch(ClassCastException e)
		{
			throw new IOException("unexpected type "+content.getClass());
		}
		if(producer==null)
			image=null;
		else
		{
			image=this.createImage(producer);
			int width=image.getWidth(this);
			int height=image.getHeight(this);
			if(width>0&&height>0)
				this.setSize(width,height);
		}
				
		
	}
	public void paint(Graphics gc)
	{
		if(image!=null)
			gc.drawImage(image,0,0,this);
	}
	public static void main(String[] gcs)
	{
		try{
		URLConnection.setContentHandlerFactory(new FitsFactory());
		FitsViewer f=new FitsViewer(new URL(""));
		f.setSize(252,252);
		f.loadImage();
		f.show();
		}
		catch(MalformedURLException e)
		{
			System.err.println("url is not recognized");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
}