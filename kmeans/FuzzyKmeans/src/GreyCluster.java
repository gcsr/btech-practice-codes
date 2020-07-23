import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class GreyCluster {
	
	double center;
	public double sum=0;
	int size=0;
	double[] values;
	double[] mmbs;
	double[] belongs;
	int picSize;
	List points;
	double total=0;
	
	         
	public GreyCluster(double center,int picSize){
		this.picSize=picSize;
		this.center=center;
		values=new double[picSize];
		mmbs=new double[picSize];
		belongs=new double[picSize];
		points=new LinkedList<Point>();
	}
	
	public void add(double value,double mb,double belong,Point p)
	{
		//total+=value;
		values[size]=value;
		mmbs[size]=mb;
		belongs[size]=belong;
		size++;
		points.add(p);
	}
	
	
	public double getCentre(){
		double normal=0;
		for(int k=0;k<size;k++)
		{
			normal+=belongs[k];
		}
		
		normal=normal/size;
		
		double nume=0,denum=0;
		
		for(int k=0;k<size;k++)
		{
			mmbs[k]=mmbs[k]+(0.1*center*(belongs[k]-normal));
			nume+=mmbs[k]*values[k];
			denum+=mmbs[k];
		}
		
		return nume/denum;
		
	}
	
	public void calcCenter()
	{
		center=getCentre();
	}
	
	public void reset()
	{
		//center=getCentre();
		size=0;
		values=new double[picSize];
		mmbs=new double[picSize];
		belongs=new double[picSize];
		points=new LinkedList<Point>();
		
		//sum=0;
		//total=0;
		
	}
	
}
