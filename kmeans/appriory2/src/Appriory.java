import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Appriory {
	
	Transaction[] transactions;
	List<ApprioryTransaction> filtered=null;
	
	int size;
	int shopItemNumber;
	int basketItemNumber;
	int minimumSupport;
	int numberOfProducts;
	
	
	public static void main(String[] gcs){
		System.out.println("Select Any one Option");
		Scanner scanner=new Scanner(System.in);
		Appriory appriory=new Appriory();
		Results[] rss=new Results[2];
		
		int size=0,shopItemNumber=0,basketItemNumber=0;
		int minimumSupport=0;
		int numberOfProducts=0;
		Transaction[] transactions=null;
		long first=0,last=0;
		while(true){
		
			System.out.println("\n option 1: create data \n option 2: display transactions\n option 3: run appriory, improvd appriory\n option 4: display results\n other : exit");
			int option=scanner.nextInt();
			
			
			switch(option){
				case 1: 	
						System.out.println("Enter number of transactions");
						size=scanner.nextInt();
						System.out.println("Enter total number of items in shop");
						shopItemNumber=scanner.nextInt();						
						System.out.println("Enter maximum number of items per basket");
						basketItemNumber=scanner.nextInt();
						
						transactions=appriory.createTransactions(size,shopItemNumber,basketItemNumber);
						break;
				case 2: appriory.display();
						break;
				case 3: 
						System.out.println("Enter candidate support (number of transactions with satisfying condition)");
						minimumSupport=scanner.nextInt();	
					
						System.out.println("number of products in association ");		
						numberOfProducts=scanner.nextInt();		
					
						ApprioryAlgorithm algo=new ApprioryAlgorithm(transactions,minimumSupport,size,shopItemNumber,basketItemNumber,numberOfProducts);
						//first=System.currentTimeMillis();
						rss[0]=algo.runAppriory();
						//last=System.currentTimeMillis();
					
						ImprovedApprioryAlgorithm imprAlgo=new ImprovedApprioryAlgorithm(transactions,minimumSupport,size,shopItemNumber,basketItemNumber,numberOfProducts);
						//first=System.currentTimeMillis();
						rss[1]=imprAlgo.runAppriory();
						//last=System.currentTimeMillis();						
						//System.out.println(" time gap is "+(last-first) +" milllis seconds ");
						
						
						break;
					
				case 4: 
						DrawTable dt=new DrawTable(rss[0],rss[1]);
						dt.setSize(1000,300);
						//dt.setResizable(true);
						dt.setVisible(true);
						break;
						
				default : System.out.println("You have chosen to exit");
					  	  System.out.println("System is exiting");
					  	  System.exit(1);
			}		
		}
		
	}
	
	
	public Transaction[] createTransactions(int size, int shopItemNumber,int basketItemNumber){
		Scanner scanner=new Scanner(System.in);
		
		
		this.size=size;
		this.basketItemNumber=basketItemNumber;
		this.shopItemNumber=shopItemNumber;
		
		transactions=new Transaction[size];
		
		
		if(basketItemNumber>shopItemNumber){
			System.out.println("wrong inputs for data");
			return null;
		}
		
		if(ncr(shopItemNumber,basketItemNumber)>size){
			System.out.println("Unique transaction with the above data is nt possible");
			return null;
		}
		
		
		System.out.println("before for loop");
		
		for(int i=0;i<size;i++){
			int sizeofBasket= (int)Math.floor(Math.random()*54321) ;
			//System.out.println(sizeofBasket);
			sizeofBasket=sizeofBasket% basketItemNumber+1;
			//System.out.println(" size of basket is "+sizeofBasket);
			Transaction trans=new Transaction(sizeofBasket);
			for(int j=0; j<sizeofBasket;j++){
				int itemNumber= (int)Math.floor(Math.random()*54321);
				itemNumber=itemNumber % shopItemNumber +1;
				//System.out.println(" item number is "+itemNumber);
				if(!trans.isExisting(itemNumber))
				trans.add(itemNumber);
				else
					j--;
			}
			//System.out.println(trans);
			
			if(!checkTransactions(trans, i)){
				System.out.println(trans);
				transactions[i]=trans;
			}
			else
				i--;
			//System.out.println(transactions[i]);
		}
		
		return transactions;
	}
	
	private boolean checkTransactions(Transaction tr,int i){
		for(int k=0;k<i;k++){
			if(transactions[k].isSame(tr))
				return true;
		}
		
		return false;
	}
	
	public void display(){
		
		DisplayTransactions dt=new DisplayTransactions(transactions);
		//dt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dt.setSize(300,600);
		dt.setVisible(true);
		
		/*System.out.println("Transaction\tItems");
		
		for(Transaction tra:transactions){
			tra.display();
			System.out.println();		
		}*/
	}
	
	
	
	public void runAppriory(){
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter minium support");
		minimumSupport=scanner.nextInt();
		
		System.out.println("number of products in association ");		
		int numberOfProducts=scanner.nextInt();		
		
		for(int i=0;i<numberOfProducts;i++){
			findCandidateSets();
			findFrequentItemSets();
			displayFiltered();
		}
		
		//displayFiltered();
		
	}
	
	private void findCandidateSets(){
		
		System.out.println("in find candidate sets");
		Transaction trr;
		if(filtered==null){
			
			filtered=new LinkedList<ApprioryTransaction>();
			for(int i=0;i<shopItemNumber;i++){
				trr=new Transaction(1);
				trr.add(i+1);
				filtered.add(new ApprioryTransaction(trr));
			}
			
			for(int i=0;i<size;i++){
				int[] itmee=transactions[i].itemIds;
				
				for(int x:itmee)
					filtered.get(x-1).addCount();
			}
		}else{
			findJoin();
			Iterator it;
			ApprioryTransaction apt;
			for(int i=0;i<size;i++){
				it=filtered.iterator();
				while(it.hasNext()){
					apt=(ApprioryTransaction)it.next();
					if(transactions[i].contains(apt.tr))
						apt.count++;
				}
			}
		}
	}
	
	private void findJoin(){
		List leftSet=filtered;
		List rightSet=filtered;
		HashSet newSet=new HashSet<ApprioryTransaction>();
		
		int nextLength=0;
		
		nextLength=filtered.get(0).tr.itemIds.length+1;
		
		//int[] newIds=new int[nextLength];
		Transaction rightTransaction,leftTransaction;
		
		
		int[] leftIds;
		
		
		Iterator leftIt=leftSet.iterator();
		Iterator rightIt;
		while(leftIt.hasNext()){
			
			ApprioryTransaction atr;
			atr=(ApprioryTransaction)leftIt.next();
			leftTransaction=atr.tr;
			leftIds=leftTransaction.getIds();
			int currentLength=leftIds.length;
			rightIt=rightSet.iterator();
			
			while(rightIt.hasNext()){
				ApprioryTransaction latr=(ApprioryTransaction)rightIt.next();
				int[] rightIds=latr.tr.getIds();
				for(int i=0;i<currentLength;i++){
					Transaction	newTransaction=new Transaction(nextLength);
					
					boolean existAl=false;
					
					int brandNewId=rightIds[i];
					
					for(int sss=0;sss<currentLength;sss++){
						if(brandNewId==leftIds[sss]){
							existAl=true;
							break;
						}
					}
					
					if(existAl)
						break;
					
					int[] newIds=new int[nextLength];
					
					for(int ik=0;ik<(nextLength-1);ik++){
						newIds[ik]=leftIds[ik];
					}
					
					newIds[nextLength-1]=brandNewId;
					newTransaction.setIds(newIds);
					ApprioryTransaction newAppr;				
						
					newAppr=new ApprioryTransaction(newTransaction);
					newSet.add(newAppr);
					System.out.println("size after adding "+newSet.size());
				}
				
			}
		}
		
		filtered=new LinkedList<ApprioryTransaction>();
		
		Iterator itttt=newSet.iterator();
		
		while(itttt.hasNext()){
			filtered.add((ApprioryTransaction)itttt.next());
		}
		
		displayFiltered();
	}	
	
	public void findFrequentItemSets(){
		
		ApprioryTransaction temp; 
		
		List fltre=new LinkedList<ApprioryTransaction>();
		Iterator iterator=filtered.iterator();
		while(iterator.hasNext()){
			temp=(ApprioryTransaction)iterator.next();
			if(temp.count>=minimumSupport)
				fltre.add(temp);
		}
		
		filtered=fltre;
	}
	
	private void displayFiltered(){
		
		DisplayAppriory dt=new DisplayAppriory(filtered);
		dt.setSize(1200,300);
		dt.setVisible(true);
		
	}
	
	public void runImprovedAppriory(){
		
	}
	
	public void createGraphs(){
		
	}
	
	int ncr(int n, int r){
		if(r==0 || n==r)
			return 0;
		else
			return(ncr(n,r-1)+ncr(n-1,r-1));
	}
	
	
}
