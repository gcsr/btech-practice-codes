import java.util.ArrayList;


public class AETGImplementor {

	int factors;
	int levels;
	int[][] tuples2;
	int[] values;
	
	ArrayList<int[]> resultTests;
	int m=0;
	
	public AETGImplementor(int factors, int levels,boolean rect){
		this.factors=factors;-
		this.levels=levels;
		values=new int[levels*factors];
		
		m=levels;
		
		for(int i=0;i<factors*levels;i++){
			values[i]=i;
		}
		
		
		generate2Tuples();
		
		while(!allDone()){
			
		}
		
		printResults();
	}
	
	private void printResults(){
		
	}
	
	
	private void generate2Tuples(){
		int n=values.length;
		int tuplesLength=(n*(n-1))/2;
		
		int k=0;
		tuples2=new int[tuplesLength][2];
		for(int i=0;i<n;i++){
			for(int j=(i+1);j<n;j++){
				tuples2[k][0]=values[i];
				tuples2[k][1]=values[j];
				k++;
			}
		}
		System.out.println(" ttal number of ttuples "+k);
	}
	
	private boolean allDone(){
		for(int i=0;i<tuples2.length;i++){
			if(tuples2[i][0]==-9999)
				continue;
			else
				return false;
		}
		
		return true;
	}
}
