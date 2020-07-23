import java.util.Comparator;


public class ClusterComparator implements Comparator<GreyCluster>{

	@Override
	public int compare(GreyCluster arg0, GreyCluster arg1) {
		// TODO Auto-generated method stub
		return (int)(arg0.center-arg1.center);
		
	}

}
