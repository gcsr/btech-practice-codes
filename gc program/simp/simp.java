import java.util.*;
import java.lang.reflect.*;


public class simp
{
	String ss;
	public static void main(String[] gcs)
	{
		app d=new app();
		Class c=d.pith();
		ArrayList<Field> fields=new ArrayList<Field>();
		
		Field[] fs=c.getDeclaredFields();
		
		for(Field f:fs)
		System.out.println(f);
		
		//System.out.println(c);
	}
}

class app
{
Class pith()
{
	return getClass();
	
}
}