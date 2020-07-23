/*
 * 
 * 
 * 
 * 
 * This class is implemented to print chars H,G and F
 * The way they are printed are
 * -->Number of H's printed are always greater than Number of F's
 * -->F and G are always printed alternatively with F first   
 * 
 */
public class BinarySemaphore extends Semaphore{
	
	
	//semaphore conditions for 3 characters
	static boolean canWriteH;
	static boolean canWriteG;
	static boolean canWriteF;	
	
	
	//count values of all prints
	static int hCount=0;
	static int gCount=0;
	static int fCount=0;
	
	//default constructor
	public BinarySemaphore(){
		
	}
	

	/*
	 * 
	 * printing h should be continuous and
	 * there are no conditions on how many should be printed	 * 
	 * 
	 */
	public void writeH(){
		int random;
		
		//continuous printing
		while(true){
			hCount++;			
			System.out.println("H");
			//to activate printing of other characters after printing first H
			if(hCount==1)
				canWriteF=true;
			// sleep randomly some time to allow other characters printing
			try{
				random=(int)(Math.random()*1000);
				Thread.currentThread().sleep(random);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		
	}
	
	
	/*
	 * this method is designed to print F characters
	 * The condition to be checked is number of H's printed should
	 * always be greater or equals to number of F's printd 
	 */
	public void writeF(){
		int random;
		//continuous printing
		while(true){
			while(canWriteF && hCount>fCount){
				if(!canWriteG){
					System.out.println("F");
					canWriteF=false;
					// to allow G to print
					canWriteG=true;
					fCount++;
					
					//cleep to allow other to print
					try{
						random=(int)(Math.random()*300);
						Thread.currentThread().sleep(random);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	
	/*
	 * this method is designed to print G characters
	 * The condition to be checked is G is always followed
	 * by F and viceversa with F starting first.
	 */	
	public void writeG(){
		int random;
		
		// continuos loop
		while(true){
			while(canWriteG && gCount<fCount){
				if(!canWriteF){
					System.out.println("G");
					canWriteG=false;
					canWriteF=true;
					gCount++;
					
					//to allow other thread to print
					try{
						random=(int)(Math.random()*300);
						Thread.currentThread().sleep(random);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
	}

}
