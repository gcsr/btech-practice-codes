import java.util.Iterator;
import java.util.Scanner;

public class Test {

	public static int num=4;
	public static Scanner reader;
	public static char c;
	
	 public static void main(String[] args) {
        
		 boolean  flag=true;
          
		 
		 int N=5;
		 reader = new Scanner(System.in);
		 //int N=Integer.parseInt(args[0]);
		
		 StdDraw.setXscale(0,N);
		 StdDraw.setYscale(0,N);
		 
		StdDraw.text(2.5, 4.5,"Color Game", 0);
		 
		 
		 drawRectangle(num);	       
		 
		 char check;
		 System.out.println("Enter number of players less or equal to 4 and greater than 1");
		 check=reader.next().charAt(0);
		 
		 
		 if(check =='2'){
		 oddPlayer("First");
		 evenPlayer("Second");
		 }else if(check =='3'){
			 
			 oddPlayer("First");
			 evenPlayer("Second");
			 oddPlayer("Third");
			 }
	     else if(check =='4'){
	    	 oddPlayer("First");
			 evenPlayer("Second");
			 oddPlayer("Third");
			 evenPlayer("fourth");
				 
							 
			 }else{
				 
				 System.out.println("invalid input....");
			 }
			 
			 
		 }		 
			 
	       
	        
	       
             
		   
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		 
		 
		 
		 
		 
   
	 
	 
	 
	
	
	public static void setColor(char c,int N,int indexX,int indexY){
		
		
				
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				
				   		   
				       if(c =='r'){
				
					    StdDraw.setPenColor(StdDraw.RED);
				        
				       }
				       else if(c == 'o'){
				    	   
				    	   StdDraw.setPenColor(StdDraw.ORANGE);  
				       }
				       else if(c == 'j'){
				    	   StdDraw.setPenColor(StdDraw.YELLOW);
				       }
				       else if(c == 'v'){
				    	   StdDraw.setPenColor(StdDraw.GREEN);
				       }
				       else if(c == 'b'){
				    	   StdDraw.setPenColor(StdDraw.BLUE);
				       }else if(c == 'i'){
				    	   StdDraw.setPenColor(StdDraw.PINK);
				       }         
					      
					    else if(c == 'O'){
					    	   
					    	   StdDraw.setPenColor(StdDraw.WHITE);  
					       }
					       else if(c == 'J'){
					    	   StdDraw.setPenColor(StdDraw.WHITE);
					       }
					       else if(c == 'V'){
					    	   StdDraw.setPenColor(StdDraw.WHITE);
					       }
					       else if(c == 'B'){
					    	   StdDraw.setPenColor(StdDraw.WHITE);
					       }else if(c == 'I'){
					    	   StdDraw.setPenColor(StdDraw.WHITE);
					       }else if(c == 'R'){
					    	   StdDraw.setPenColor(StdDraw.WHITE);
					       }
					
					   
				     if(i==indexX && j==indexY)				 
				   StdDraw.filledSquare(i+0.5,j+0.5,0.5);
				    
			}
		 }
  }
    public static void drawRectangle(int N){
    	   
    	 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				
				     
				  /* if((i+j)%2 == 0){
					   StdDraw.setPenColor(StdDraw.BLACK);
					     
				         
				   }   
				    else{	    	
				         
				        	 
					    StdDraw.setPenColor(StdDraw.RED);
				    }*/
					   
				 StdDraw.setPenColor(StdDraw.BLACK);
				   
				   if(i==3 && j==3 ){
				 
					   
					   StdDraw.square(i+0.5,j+0.5,0.5);
				  
				   }else if(i!=3 && j!=4){
					   
					   StdDraw.square(i+0.5,j+0.5,0.5);   
				   }
			}   
    	
    	  	
    	
    	
    	
    	
    }


    }
    public static void oddPlayer(String playerNo){
    	
    	for (int i = 0; i < 3; i++) {
			  
		      for(int j=0;j<num;j++) {
		   
	       
	       
	    	   System.out.println(playerNo + "player enter color  box ");

	        char c = reader.next().charAt(0);
	        setColor(c,num,i,j);
	       
	        	       
            
		   }
		 }
 		 
	  
		 System.out.println(playerNo +"player enter color  box ");

	        char c = reader.next().charAt(0);
	        setColor(c,num,3,3); // 3,3 is for 13 block
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    public static void evenPlayer(String playerNo){
    	
    	System.out.println(playerNo + "player enter color  box ");

        c = reader.next().charAt(0);
       setColor(c,num,3,3); //3 ,3 is for 13 block

   
    
   


for (int i = 0; i < 3; i++) {
	  
     for(int j=0;j<4;j++) {

  
  
  
	   System.out.println(playerNo + "player enter color  box ");

    c = reader.next().charAt(0);
   setColor(c,4,num-i-2,num-j-1);
  
   
  
    
  }
}


    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
		
		
		
	

