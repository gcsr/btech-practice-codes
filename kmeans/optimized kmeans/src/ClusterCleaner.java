import java.util.Comparator;
import java.util.*;


public class ClusterCleaner implements Comparator<GreyCluster>{

	@Override
	public int compare(GreyCluster arg0, GreyCluster arg1) {
		// TODO Auto-generated method stub
		if(arg0.center!=arg1.center)
		{
			double x=arg0.center-arg1.center;
			if(x>0.0)
				return 1;
			else return -5;
		}
		else
		{
			return (int)(arg0.fitness - arg1.fitness);
		}
		
		
	}
	
	public void test()
	{
		Map map;
		map=new HashMap<Integer,String>();
	}

}
