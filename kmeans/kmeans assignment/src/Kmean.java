
import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

public class Kmean {
	
	Point[] points;
	
	GreyCluster[] greyClusters;
	GreyCluster[] newClusters;
	
	int size=0;
	
	public Kmean(int count, int uLimit,int size){
		points=GenerateRandomPoints.randomPints(count, uLimit);
		this.size=size;
		
		greyClusters=new GreyCluster[size];
		int px;
		
		for(int i=0;i<size;i++){			
			px=(int)(Math.random()*count);		
			greyClusters[i]=new GreyCluster(points[px]);
		}
		
		int less=0;
		
		for(int j=0;j<(greyClusters.length-1);j++){
			if(greyClusters[j].center==greyClusters[j+1].center){
				less++;
				greyClusters[j]=null;
			}
		}
		
		newClusters=new GreyCluster[size-less];
		
		int j=0,k=0;
		while(j<size){
			if(greyClusters[j]!=null){
				newClusters[k]=greyClusters[j];
				j++;
				k++;
			}
			
			else
				j++;
		}
		
		
		size=size-less;
		
		greyClusters=newClusters;

		
		while(true){	
			for(j=0;j<count;j++){
							
				double diff=(points[j].x-greyClusters[0].center.x)*(points[j].x-greyClusters[0].center.x)+
						(points[j].y-greyClusters[0].center.y)*(points[j].y-greyClusters[0].center.y);
				
				int min=0;
				
				for(int l=1;(l<size);l++){
					
					
					double curDiff=(points[j].x-greyClusters[l].center.x)*(points[j].x-greyClusters[l].center.x)+
							(points[j].y-greyClusters[l].center.y)*(points[j].y-greyClusters[l].center.y);
					
					if(curDiff<diff){
						diff=curDiff;
						min=l;
					}
					
				}				
			
				greyClusters[min].add(points[j]);
					
			
			}
			
			less=0;
			
			for(j=0;j<(greyClusters.length-1);j++){
				if(greyClusters[j].center==greyClusters[j+1].center){
					less++;
					greyClusters[j]=null;
				}
			}
			
			newClusters=new GreyCluster[size-less];
			
			j=0;k=0;
			while(j<size){
				if(greyClusters[j]!=null){
					newClusters[k]=greyClusters[j];
					j++;
					k++;
				}
				
				else
					j++;
			}
			
			
			size=size-less;
			
			greyClusters=newClusters;
			
			
		}
	


		
	}	
	
	
	public static void main(String[] gcs){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter no of points to take randomly");
		int size=scanner.nextInt();
		System.out.println("enter upper limit for x and y values of points");
		int uLimit=scanner.nextInt();
		System.out.println("enter no of cluster");
		
		
				
	}
}
