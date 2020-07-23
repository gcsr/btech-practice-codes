import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Dijkstra {
	
	
	int[][] vertex;
	int verticesNumber;
	int[][] distances;
	LinkedList<String>[][] paths;
	final static int INFINITY=999999;
	int count=0;
	
	
	
	public void fillGraph(){
		Scanner scanner=new Scanner(System.in);
		System.out.println("enter number of nodes");
		verticesNumber=scanner.nextInt();
		
		if(verticesNumber<2){
			System.out.println("invalid inputs");
			System.out.println("program terminating");
			System.exit(1);
		}
		vertex=new int[verticesNumber][verticesNumber];
		distances=new int[verticesNumber][verticesNumber];
		paths=new LinkedList[verticesNumber][verticesNumber];
		
		
		
		try{			
			
			for(int i=0;i<verticesNumber;i++){
				for(int j=0;j<verticesNumber;j++){
					System.out.println("enter edge weight from node "+(i+1)+" to "+(j+1)+" no vertex  999999");
					vertex[i][j]=scanner.nextInt();
					if(vertex[i][j]<0){
						System.out.println("invalid inputs");
						System.out.println("program terminating");
						System.exit(1);
					}
					
				}
			}
			
			for(int i=0;i<verticesNumber;i++){
				for(int j=0;j<verticesNumber;j++){
					paths[i][j]=new LinkedList<String>();
					paths[i][j].add(0,""+(i+1));
					distances[i][j]=vertex[i][j];
					paths[i][j].add(1,""+(j+1));
					count++;
				}
				
			}	
			
		}catch(Exception ex){
			System.out.println("invalid inputs");
			System.out.println("program terminating");
			System.exit(1);
		}
	}
	
	public void calculatePath(){
		
		for(int i=0;i<verticesNumber;i++){
			for(int j=0;j<verticesNumber;j++){
				for(int k=0;k<verticesNumber;k++){
					if(distances[i][j]>(distances[i][k]+distances[k][j])){
						paths[i][j].add(paths[i][j].size()-1,""+(k+1));
						distances[i][j]=(distances[i][k]+distances[k][j]);
					}
					count++;
				}
			}
		}
		
	}
	
	public void printGraph(){
		Iterator<String> ite;
		
		for(int i=0;i<verticesNumber;i++){
			for(int j=0;j<verticesNumber;j++){
							
				System.out.print(vertex[i][j]+"\t");
				
			}	
			System.out.println();
			
		}
		
		System.out.println("It has taken "+count+" statements to find all the paths ");
	}
	
	
	public void printPaths(){
		Iterator<String> ite;
		
		for(int i=0;i<verticesNumber;i++){
			for(int j=0;j<verticesNumber;j++){
				LinkedList<String> path=paths[i][j];
				ite=path.iterator();
				while(ite.hasNext()){
					System.out.print(ite.next()+"\t");
				}
				
				System.out.print(distances[i][j]);
				System.out.println();
			}			
			
		}
	}
	
	public static void main(String[] gcs){
		Dijkstra algo=new Dijkstra();
		algo.fillGraph();
		algo.calculatePath();
		algo.printGraph();
		algo.printPaths();
	}
}
