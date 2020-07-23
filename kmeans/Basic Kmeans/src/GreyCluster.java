import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class GreyCluster {
	
	double center;
	public double sum=0;
	int size=0;
	public double[] values;
	int picSize;
	List points;
	double total=0;
	
	         
	public GreyCluster(double center,int picSize){
		this.picSize=picSize;
		this.center=center;
		values=new double[picSize];
		points=new LinkedList<Point>();
	}
	
	public void add(double value,Point p)
	{
		total+=value;
		values[size]=value;
		size++;
		points.add(p);
	}
	
	
	public double getCentre()
	{
		return sum/size;
	}
	
		
	public void reset()
	{
		center=getCentre();
		size=0;
		values=new double[picSize];
		points=new LinkedList<Point>();
		sum=0;
		total=0;
		
	}
	
}
