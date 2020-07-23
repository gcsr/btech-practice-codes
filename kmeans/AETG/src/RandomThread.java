import java.util.Random;
import java.util.concurrent.Callable;


public class RandomThread implements Callable<int[][]>{
	
	int first;
	int last;
	int firstLow,firstHigh,secondLow,secondHigh;
	
	public RandomThread(int first, int last,int levels, int factors){
		this.first=first;
		this.last=last;
		firstLow=0; firstHigh=levels-1;
		secondLow=levels;secondHigh=(levels+factors-1);
	
	}
	
	public int[][] call(){
		Random r=new Random();
		int[][] results=new int[last-first][2];
		
		for(int j=0;j<results.length;j++){
			results[j][0]= r.nextInt(firstHigh-firstLow) + firstLow;
			results[j][1]= r.nextInt(secondHigh-secondLow) + secondLow;
		}
		
		return results;
	}
}
