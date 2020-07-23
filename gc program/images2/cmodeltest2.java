import java.awt.*;
import java.applet.Applet;
import java.awt.image.*;
import java.awt.event.*;


public class cmodeltest2 extends Applet{
ColorModel drgb=ColorModel.getRGBdefault();

Image image;


public void init(){
int[] imagebits=
{-11380624
};
MemoryImageSource mis=new MemoryImageSource(1,1,drgb,imagebits,0,1);
image=createImage(mis);

}
public void paint(Graphics gc)
{
gc.drawImage(image,0,0,300,300,this);
}
}
