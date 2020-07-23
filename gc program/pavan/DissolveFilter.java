import java.awt.image.*;


public class DissolveFilter extends RGBImageFilter
{
private int alpha;

public DissolveFilter()
{
this(0);
}

public DissolveFilter(int ap)
{
canFilterIndexColorModel=true;


if(ap<0||ap>255)
throw new IllegalArgumentException("bad alpha");
alpha=ap;
}
public int filterRGB(int x,int y,int rgb)
{
DirectColorModel cm=(DirectColorModel)ColorModel.getRGBdefault();
int alpha=cm.getAlpha(rgb);
int red=cm.getRed(rgb);
int blue=cm.getBlue(rgb);
int green=cm.getGreen(rgb);
//System.out.print(alpha+"  ");
if(rgb==-16777216)
alpha=0;
return alpha<<24|red<<16|green<<8|blue;
}
}
