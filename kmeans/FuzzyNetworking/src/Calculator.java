
public class Calculator {
	public static int DEFAULT=100;
	
	//int bandwidth;
	
	//B in pdf
	public static int bufferCapacity=2000;
	public static int queueConst=300;
	
	//A and D
	int leftEnd;
	int rightEnd;
	public static int minimumQueue=300;
	
	
	//m
	int widthLimit;
	
	//IQSize in pdf
	//public static int avaialableQueue;
	
	//e(t) 1 input to the controller which controls occupancy aim
	int queueError;
	
	
	
	public Calculator(int bufferCapacity,int minimumQueue,int DEFAULT)
	{
		this.bufferCapacity=bufferCapacity;
		this.DEFAULT=DEFAULT;
		this.minimumQueue=minimumQueue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int getAllowedSize(int reqRate){
		System.out.println("in getWidth size");
		System.out.println("buffer capacity is "+bufferCapacity);
		if((reqRate>DEFAULT)){
			if((bufferCapacity-reqRate)>0){
				bufferCapacity-=reqRate;
				return reqRate;
			}
			
			else if(bufferCapacity>0){
				int result=bufferCapacity;
				bufferCapacity=0;
				System.out.println("returning "+result);
				return result;
			}
			
			else{
				bufferCapacity-=reqRate;
				return bufferCapacity;
			}
			
			
			
		}
		else{
			if((bufferCapacity-reqRate)>0){
				bufferCapacity-=reqRate;
				return reqRate;
			}
			
			else if(minimumQueue>=reqRate){
				minimumQueue-=reqRate;
				return reqRate;
			}
		
			else if(bufferCapacity>0){
				int result=bufferCapacity;
				bufferCapacity=0;
				return result;
			}
			
			else{
				bufferCapacity-=reqRate;
				return bufferCapacity;
			}
				
			
		}
		/*
		if((reqRate>DEFAULT)&&(minimumQueue<DEFAULT)){
		
			if((bufferCapacity-reqRate)>0){
				bufferCapacity-=reqRate;
				return reqRate;
			}
			else if(bufferCapacity>0){
				int result=bufferCapacity;
				bufferCapacity=0;
				return result;
			}
			else{
				int result=bufferCapacity;
				bufferCapacity-=reqRate;
				return result;
			}
			
		}
		if((reqRate<DEFAULT)&&(minimumQueue<DEFAULT)){
			if((bufferCapacity-reqRate)>0){
				bufferCapacity-=reqRate;
				return reqRate;
			}
			else if(minimumQueue>=reqRate){
				minimumQueue-=reqRate;
				return reqRate;
			}
			else if(bufferCapacity>0){
				int result=bufferCapacity;
				bufferCapacity=0;
				return result;
			}
			else{
				int result=bufferCapacity;
				bufferCapacity-=reqRate;
				return result;
			}
			
		}*/
		
		
		
		
	}

}
