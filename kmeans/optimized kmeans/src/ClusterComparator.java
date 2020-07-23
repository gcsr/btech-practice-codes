import java.util.Comparator;


public class ClusterComparator implements Comparator<GreyCluster>{

	@Override
	public int compare(GreyCluster arg0, GreyCluster arg1) {
		// TODO Auto-generated method stub
		if(arg0.fitness!=arg1.fitness)
		{
			double x=arg0.fitness-arg1.fitness;
			if(x>0.0)
				return 1;
			else return -5;
		}
		else
		{
			return arg0.size-arg1.size;
		}
		
		
	}

}
