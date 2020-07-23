import java.util.Scanner;
import java.util.Vector;

public class Heap {
	
	private Vector<Integer> elements=new Vector<Integer>();
	
	public static void main(String[] gcs){
		Heap hip=new Heap();
		Scanner scanner=new Scanner(System.in);
		
		
		while(true){
			System.out.println();
    		System.out.println("--------------------------------------------------------------------------------");
    		System.out.println("i: insert into heap.");
    		System.out.println("r: remove from heap.");
    		System.out.println("d: display display.");
    		System.out.println("e: exit.");    		    		
    		System.out.println("--------------------------------------------------------------------------------");
    		System.out.println("Enter Command");
    		
    		String cr=scanner.next();
    		if(cr.length()!=1){
    			System.out.println("Invalid Command");
    			System.out.println("I'm leaving");
    			System.exit(1);
    		}
    		else{
    			char c=cr.charAt(0);
    			switch(c){
    			case 'i':
    				System.out.println("You have selected to insert");
    				System.out.println("Enter Number of inputs");
    				int numberofInputs=scanner.nextInt();
    				int input;
    				int kp=0;
    				while(kp<numberofInputs){
    					System.out.println("Enter value");
    					input=scanner.nextInt();
    					hip.insert(input);
    					kp++;
    				}
    				
    				/*int value=scanner.nextInt();
    				hip.insert(value);*/
    				break;
    				
    			case 'r':
    				System.out.println("You have selected to remove");
    				int removedValue=hip.extractMin();    
    				System.out.println("removed element is "+removedValue);
    				break;
    				
    			case 'd':
    				System.out.println("You have selected display");
    				hip.print();
    				break;
    				
    			case 'e':
    				System.out.println("You have selected to exit");
    				System.exit(0);
    				
    			default:
    				System.out.println("Invalid selection");
    				break;
    			}
    			
    		}
    	
		}
		
	}
	
	public Heap(){
		
	}
	
	void insert(int key){
		
		elements.add(key);
		clearInsertion(elements.size());
		//print();
		
	}
	
	void clearInsertion(int key){		

			
			int i = elements.size()-1;
			
			while (i > 0 && (elements.get(i) > elements.get(parent(i)))){				
				int temp=elements.get(i);
				
				elements.setElementAt(elements.get(parent(i)),i);
				elements.setElementAt(temp,parent(i));
				
				i = parent(i);

				
			}
		
	}
	
	private void print(){
		
		System.out.println();
		for (int j = 0; j < elements.size(); j++){
			System.out.print(elements.get(j)+"\t");
		}
		//System.out.println("elements size is "+elements.size());
		
	}

	
	
	private int parent(int i){
		if (i ==0)
			return -1;
		else
			return (i - 1) / 2;
	}
	
	
	
	int left(int i){
		int result = 2 * i + 1;
		//if (result > size)
			//return size;
		return result;
	}

	int right(int i){
		int result = 2 * i + 2;
		//if (result > size)
			//return size;
		return result;
	}
	
	int extractMin(){

		
		int result=-100;
		int last=-100;
		int currentSize=elements.size();
		if (currentSize <1){
			
			return -100;
			//return "No Element There";
		}
		else{
			
			result = elements.get(0);
			last=elements.get(elements.size()-1);
			elements.removeElementAt(elements.size()-1);
			
			if(result!=last){
				elements.setElementAt(last,0);
				heapify(0);
				//elements.insertElementAt(0,last);
				
			}
			return result;
		
		}

		
	}
	
	
	private void heapify(int i){
		int l = left(i);
		int r = right(i);
		int minimum;
		int currentSize=elements.size();
		if (l < currentSize && elements.get(l) > elements.get(i)){
			minimum = l;
		}
		else minimum = i;
		if (r < currentSize && elements.get(r) > elements.get(minimum)){
			minimum = r;
		}

		

		if (minimum != i){
			int temp;
		
			temp=elements.get(i);
			elements.setElementAt(elements.get(minimum),i );
			elements.setElementAt(temp,minimum);
			heapify(minimum);

		}
	}
	

	
}
