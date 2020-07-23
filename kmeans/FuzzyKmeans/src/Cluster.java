
public class Cluster {
	
	double center[];
	double sum;
	double mse;
	int size=0;
	public double[] values;
	
	         
	public Cluster(double[] center,int picSize){
		
		this.center=center;
		values=new double[picSize];
	}
	
	public void add(double value)
	{
		values[size]=value;
		size++;
		sum+=value;
		
	}
	
	public double getMse()
	{
		
		
		return mse;
			
	}
	
	
	

}
