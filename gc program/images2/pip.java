//import java.awt.image.SystemColors;
import java.awt.Color;
//import java.awt.SystemColors;
public class pip
{
public static void main(String gcs[])
{

int rp=0xff<<24|0xff<<16;
Color pp=Color.getColor("Color.red",-65536);
System.out.println(pp);
Color po=SystemColors.activeCaption;
System.out.println(po);
}
}