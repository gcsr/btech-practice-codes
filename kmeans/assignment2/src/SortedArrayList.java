import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList<E>{
	
	 public boolean add(E u) {
		 
		 int xxx=this.size();
		 
		 if(this.size()==0)
			 add(0,u);
		 
		 
		 else{
			 int i=0;
			 while(i<xxx && u.toString().compareToIgnoreCase(this.get(i).toString())>0){
				 i++;
			 }
			 add(i,u);
		 }
		 
		 return true;
	 }
	
}
