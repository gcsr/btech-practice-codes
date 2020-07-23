//order
//static like finally
//main
//cons if instance created in main

public class StaticTest {
        
   static int s,j;
   static{
   	System.out.println("static like finally");
   	s=4;j=5;
   }
    public StaticTest() {
    	System.out.println("this is Static Test constructor");
    	
    }
    
    
    public static void main(String[] args) {
    	System.out.println("this is statictest main class");
    	new StaticTest();
       
    }
}
