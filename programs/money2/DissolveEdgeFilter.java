import java.awt.image.*;
import java.awt.*;

public class DissolveEdgeFilter extends RGBImageFilter
{
private int alpha,width,height;
private Insets insets;


public DissolveEdgeFilter(int ap,Insets ins)
{

insets=ins;

if(ap<0||ap>255)
throw new IllegalArgumentException("bad alpha");
alpha=ap;
}

public void setDimensions(int wid,int heig)
{

width=wid;
height=heig;
super.setDimensions(width,height);
}
public int filterRGB(int x,int y,int rgb)
{
int modifyrgb=rgb;
//System.out.println(" "+insets.left+" "+" "+insets.right+"  "+insets.top+"  "+insets.bottom);

//System.out.println(x+"   "+y);
if((x<insets.left)||(x>width-insets.right)||(y<insets.top)||(y>height-insets.bottom))
modifyrgb=modifyAlpha(rgb);
return modifyrgb;
}
public int modifyAlpha(int rgb)
{
DirectColorModel cm=(DirectColorModel)ColorModel.getRGBdefault();
int alpha=cm.getAlpha(rgb);
int red=cm.getRed(rgb);
int blue=cm.getBlue(rgb);
int green=cm.getGreen(rgb);
//System.out.println("nee gudha");
alpha=alpha==0?0:this.alpha;
return alpha<<24|red<<16|green<<8|blue;
}
}
