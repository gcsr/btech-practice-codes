package inheritance;

class Calculation{ 
	   int z;
	   int a=40;
		
	   public void addition(int x, int y){
	      z = x+y;
	      System.out.println("The sum of the given numbers:"+z);
	   }
		
	   public void Subtraction(int x,int y){
	      z = x-y;
	      System.out.println("The difference between the given numbers:"+z);
	   }
	   
	   
	   
}

public class My_Calculation extends Calculation{    
	  
	  int a=20;
	   public void multiplication(int x, int y){
	      z = x*y;
	      System.out.println("The product of the given numbers:"+z);
	   }
	   
	   public void dispaly(){
		   System.out.println(a);
	   }
		
	   public static void main(String args[]){
	      int a = 20, b = 10;
	      My_Calculation demo = new My_Calculation();
	      demo.dispaly();     
	   }

}