import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GreyCluster {
	
	Point center;
	public double sum=0;
	List points;
	
	         
	public GreyCluster(Point center){
		points=new LinkedList<Point>();
		this.center=center;
	}
	
	public void add(Point p){
		points.add(p);
	}
	
	public Point getCentre(){
		int size=points.size();
		
		Iterator iterator=points.iterator();
	
		double x=0;
		double y=0;
		
		while(iterator.hasNext()){
			Point p=(Point)iterator.next();
			x+=p.x;
			y+=p.y;
			
		}
		
		return new Point((int)x/size,(int)y/size);
	}
	
	
	public void reset(){
		points=new LinkedList<Point>();
	}
	
}
