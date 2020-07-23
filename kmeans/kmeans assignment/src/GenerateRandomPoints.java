
import java.awt.Point;

public class GenerateRandomPoints {
	public static Point[] randomPints(int count, int maxLimit){
		Point[] p=new Point[count];
		
		int x=0;
		int y=0;
		for(int i=0;i<count;i++){
			
			x=(int)(Math.random()*maxLimit);
			y=(int)(Math.random()*maxLimit);
			Point px=new Point(x,y);
			p[i]=px;
		}
		
		return p;
	}
}
