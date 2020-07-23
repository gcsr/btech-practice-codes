import java.util.Random;


public class Graph {
	
	String[] elements;
	int support;
	String input;
	
	public Graph(){
		
	}
	
	public Graph(String input){
		this.input=input;
		elements=input.split(" ");
		
	}
	
	public boolean checkFor(String[] inputs){
		
		int checkLength=inputs.length;
		int numberofMatchings=0;
		for(int i=0;i<checkLength;i++){
			if(isExists(inputs[i]))
				numberofMatchings++;
			else return false;
		}
		
		if(numberofMatchings==checkLength)
			return true;
		else
			return false;
	}
	
	private boolean isExists(String st){
		for(String s:elements){
			if(s.equals(st))
				return true;
		}
		
		return false;
	}

	public String[] getElements() {
		return elements;
	}

	public void setElements(String[] elements) {
		this.elements = elements;
	}

	public int getSupport() {
		return support;
	}

	public void setSupport(int support) {
		this.support = support;
	}
	
	public Graph[] getSubGraphs(){
		Random random=new Random();
		int rs=random.nextInt(elements.length-1)+1;	
		if(rs==elements.length)
			rs--;
		String[][] ss=new String[2][];
		ss[0]=new String[rs];
		ss[1]=new String[elements.length-rs];
		
		for(int i=0;i<rs;i++){
			ss[0][i]=elements[i];
		}
		
		for(int i=rs;i<elements.length;i++){
			ss[1][i-rs]=elements[i];
		}
		
		Graph one=new Graph();
		one.setElements(ss[0]);
		if(one.elements==null){
			System.out.println("null");
			System.exit(1);
		}
		
		Graph two=new Graph();
		two.setElements(ss[1]);
		if(two.elements==null){
			System.out.println("null");
			System.exit(1);
		}
		
		
		//System.out.println(one);
		//System.out.println(two);
		
		return new Graph[]{one,two};
		
		
	}
	
	public String toString(){
		
		String result="";
		
		for(String x: elements){
			result+=" "+x;
		}
		
		return result;
	}
}
