import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.image.RenderedImage;



public class JAIMedianFilter{
	
	public static void main(String[] gcs){
		//MedianFilterDescriptor.c;
		//JAI.cre
	}
	
}

/*

File file = new File("grainy-image.jpg");
InputStream in = null;
long filelength = 0L;
ByteArrayInputStream bais = null;
try {
in = new FileInputStream(file); 
filelength = file.length();
byte[] bytes = new byte[(int)filelength];
int offset = 0;
int numRead = 0;
while (offset < bytes.length && (numRead=in.read(bytes, offset, bytes.length-offset)) >= 0)
{
offset += numRead;
}
if (offset < bytes.length)
{
throw new IOException("Could not completely read file "+file.getName());
} bais = new ByteArrayInputStream(bytes);

RenderedImage src = null;
src = JAI.create("stream", SeekableStream.wrapInputStream(bais, true));
RenderedImage renderedImage = PlanarImage.wrapRenderedImage(src);
RenderedOp renderedOp = MedianFilterDescriptor.create(renderedImage,
MedianFilterDescriptor.MEDIAN_MASK_SQUARE , 5, null);
BufferedImage image = renderedOp.getAsBufferedImage();
ImageIO.write(image, "jpeg", new File("smooth-image.jpg"));
} catch (IOException x) {
System.err.println(x);
}
*/