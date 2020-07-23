
public class Normal implements Comparable{
	String str;double value;
	public Normal(String str,double value){
		this.str=str;
		this.value=value;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Normal nl=(Normal)arg0;
		if(this.value>nl.value)
			return 10;
		else return -10;
		
	}
	
}
