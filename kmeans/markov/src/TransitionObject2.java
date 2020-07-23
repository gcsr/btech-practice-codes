
public class TransitionObject2 {
	private String currentElement;
	private String nextElement[];
	private int infavour;
	private int inoppose;
	
	public TransitionObject2(String currentElement,String nextElement[]){
		this.currentElement=currentElement;
		this.nextElement=nextElement;
		this.infavour=0;
		this.inoppose=0;
	}
	
	public void addFavour(){
		infavour++;
	}
	
	public void addOppose(){
		inoppose++;
	}
	
	
	public String getCurrentElement(){
		return currentElement;
	}
	
	public String[] getNextElement(){
		return nextElement;
	}
	public int getFavour(){
		return infavour;
	}
	
	public int getOppose(){
		return inoppose;
	}	
	
	
	public boolean check(String next){
		for(String s:nextElement){
			if(s!=null){
				if(s.equals(next))
					return true;
			}
		}
		return false;
	}
	
}
