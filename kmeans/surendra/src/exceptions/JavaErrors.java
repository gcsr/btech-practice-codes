package exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class JavaErrors {
	
	//public abstract void jjj();
	
	public static void main(String[] args){
		
		JavaErrors error=new JavaErrors();
		//File f=new File("filename");
		//{
			//error.call();
		//}catch(ArrayIndexOutOfBoundsException ex){
			
		//}
		try{	
			
			JavaErrors temp2=new JavaErrors(){
				public void call(){
					
				}
			};
			
			
			error.customException("surendra C");
		}catch(UsernameException ex){
			System.out.println(ex.getMessage());
		}catch(Exception ex){
			
		}
		
	}
	
	public void call() throws ArrayIndexOutOfBoundsException{
		int[] a=new int[10];
		
		
		// try catch handling
		/*int i=90;		
		try{
			System.out.println(a[90]);
		}catch(ArrayIndexOutOfBoundsException ex){
			
		}*/
		
		// escalating exception to calee
		System.out.println(a[90]);
		
		
		
	}
	
	public void inheritenceException() {
		try {
			FileInputStream fin=new FileInputStream("aksldjfals");
			int[] a=new int[10];
			System.out.println(a[90]);
			//stm2 this sta
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
		
			//send notification
		}
		finally{
			
		}
		
		
	}
	
	public void customException(String userName) throws UsernameException{
		if(userName.contains(" ")){
			throw new UsernameException("Username has space");
		}
	}
	
	public void  stringExample(){
		String s1="xyz";
		String s2="xyz";
		// upto above 2 lines both refer to same value
		
		s2="hhh";
		// hhh string value ia created in memory
		// and new memory reference is given for s2
		//s2 is linked to another 
		
		String s3="hhh";
		// s3 s2 refere same
		
		String obj1=new String("abc");
		String obj2 =new String("kkk");
		// obj1 and obj2 are different
		
		
	}
}

// Java JavaErrors