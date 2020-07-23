     import java.awt.Frame;
     import java.awt.image.renderable.ParameterBlock; 
     import java.awt.image.renderable.ParameterBlock;
     import java.io.IOException;
     
     
    // import javax.media.jai.Interpolation;
     //import javax.media.jai.JAI;
     //import javax.media.jai.RenderedOp;
   //  import com.sun.media.jai.codec.FileSeekableStream;
     //import javax.media.jai.widget.ScrollingImagePanel;
    
     public class sim {
          public static void main(String[] args) {
         
             if (args.length != 1) {
                 System.out.println("Usage: java JAISampleProgram " +
                                    "input_image_filename");
                 System.exit(-1);
             }
         
             FileSeekableStream stream = null;
             try {
                 stream = new FileSeekableStream(args[0]);
             } catch (IOException e) {
                 e.printStackTrace();
                 System.exit(0);
             }
              RenderedOp image1 = JAI.create("stream", stream);
         
             Interpolation interp = Interpolation.getInstance(
                                        Interpolation.INTERP_BILINEAR);
         
             ParameterBlock params = new ParameterBlock();
             params.addSource(image1);
             params.add(2.0F);         // x scale factor
             params.add(2.0F);         // y scale factor
             params.add(0.0F);         // x translate
             params.add(0.0F);         // y translate
             params.add(interp);       // interpolation method
                      RenderedOp image2 = JAI.create("scale", params);
         
             int width = image2.getWidth();
             int height = image2.getHeight();
         
             ScrollingImagePanel panel = new ScrollingImagePanel(
                                             image2, width, height);
         
             Frame window = new Frame("JAI Sample Program");
             window.add(panel);
             window.pack();
             window.show();
             
         }
     }
