package algorithms;

public class Productor {
	
	int firstElement,secondElement;
	
	public Productor(int firstElement,int secondElement){
		this.firstElement=firstElement;
		this.secondElement=secondElement;
	}
	
	public void multiply(){
		
	}
	
	private int[] fristHalf(int a,int b){
		int ab=0;
		int bc=a;
		int count=0;
		while(bc>0){
			count++;
			bc=bc/10;
		}
		
		count++;
		
		int half=count/2;
		
		int firstHalf=a/(10*half);
		
		//return firstHalf;
	}

}
