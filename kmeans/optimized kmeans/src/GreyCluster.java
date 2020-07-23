import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class GreyCluster {
	
	double center;
	public double sum=0;
	double fitness;
	int size=0;
	public double[] values;
	int picSize;
	List points;
	
	         
	public GreyCluster(double center,int picSize){
		this.picSize=picSize;
		this.center=center;
		values=new double[picSize];
		points=new LinkedList<Point>();
	}
	
	public void add(double value,Point p)
	{
		values[size]=value;
		size++;
		fitness+=value;
		points.add(p);
	}
	
	public void add(Conflict conflict)
	{
		values[size]=conflict.value;
		size++;
		fitness+=conflict.value;
		Point x=new Point(conflict.x,conflict.y);
		points.add(x);
		
	}
	
	public double getCentre()
	{
		return sum/size;
	}
	
	public double getFitness()
	{
		return fitness;
	}
	
	public void reset()
	{
		center=getCentre();
		System.out.println("center is "+center);
		size=0;
		values=new double[picSize];
		points=new LinkedList<Point>();
		fitness=0;
		sum=0;
		
	}
	
}
