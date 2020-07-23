
public class TransitionObject {
	private String currentElement;
	private String nextElement;
	private int infavour;
	private int inoppose;
	
	public TransitionObject(String currentElement,String nextElement){
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
	
	public String getNextElement(){
		return nextElement;
	}
	public int getFavour(){
		return infavour;
	}
	
	public int getOppose(){
		return inoppose;
	}
	
	public boolean equalsTo(TransitionObject obj){
		if(this.currentElement.equals(obj.currentElement) && this.nextElement.equals(obj.nextElement))
			return true;
		return false;
	}
	
}
