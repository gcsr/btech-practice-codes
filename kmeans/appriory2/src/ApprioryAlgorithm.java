import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ApprioryAlgorithm {
	
	
	
	Transaction[] transactions;
	int size;
	int shopItemNumber;
	int basketItemNumber;
	int minimumSupport;
	int numberOfProducts;
	long first,last;
	List<ApprioryTransaction> filtered=null;
	
	Results rs;
   
	
	public ApprioryAlgorithm(Transaction[] transactions,int minimumSupport,int size, int shopItemNumber,int basketItemNumber,int numberOfProducts){
		this.minimumSupport=minimumSupport;
		this.size=size;
		this.shopItemNumber=shopItemNumber;
		this.basketItemNumber=basketItemNumber;
		this.numberOfProducts=numberOfProducts;
		this.transactions=transactions;
		rs=new Results(numberOfProducts);
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
	
	
	
	public Results runAppriory(){
		
		Transaction trr;
		
		filtered=new LinkedList<ApprioryTransaction>();
		
		first=System.currentTimeMillis();			
		
								
		
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
		
		last=System.currentTimeMillis();
		
		rs.addInst(size);
		rs.addTime(last-first);
		
		for(int i=1;(i<numberOfProducts && filtered.size()>0);i++){
			findCandidateSets();
			first=System.currentTimeMillis();			
			findFrequentItemSets();
			last=System.currentTimeMillis();						
			
			rs.addTime(last-first);
			System.out.println(" time gap is "+(last-first) +" milllis seconds ");
			
			displayFiltered();
		}
		
		//displayFiltered();
		System.out.println(rs);
		return rs;
		
	}
	
	private void findCandidateSets(){
		
		System.out.println("in find candidate sets");
		Transaction trr;
		
			findJoin();
			
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
		int sizzz=leftSet.size();
		ApprioryTransaction atr=null,latr=null;
		int spsppsps=0;
		int currentLength;
		int rightIds[];
		boolean existAl=false;
		int brandNewId;
		Transaction newTransaction;
		ApprioryTransaction newAppr;
		
		for(int spsp=0;spsp<(sizzz/2+1);spsp++){		
			
			atr=(ApprioryTransaction)leftSet.get(spsp);
			leftTransaction=atr.tr;
			leftIds=leftTransaction.getIds();
			currentLength=leftIds.length;
			spsppsps=0;
			for(spsppsps=0;spsppsps<sizzz;spsppsps++){
				latr=(ApprioryTransaction)rightSet.get(spsppsps);
				rightIds=latr.tr.getIds();
				for(int i=0;i<currentLength;i++){
					newTransaction=new Transaction(nextLength);					
					existAl=false;					
					brandNewId=rightIds[i];
					
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
									
						
					newAppr=new ApprioryTransaction(newTransaction);
					newSet.add(newAppr);					
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
		
		Iterator it;
		long countss=0;
		ApprioryTransaction apt;
		for(int i=0;i<size;i++){
			it=filtered.iterator();
			while(it.hasNext()){
				countss++;
				apt=(ApprioryTransaction)it.next();
				if(transactions[i].contains(apt.tr))
					apt.count++;
			}			
		}
	
		ApprioryTransaction temp; 
		
		List fltre=new LinkedList<ApprioryTransaction>();
		Iterator iterator=filtered.iterator();
		while(iterator.hasNext()){
			temp=(ApprioryTransaction)iterator.next();
			countss++;
			if(temp.count>=minimumSupport)
				fltre.add(temp);
		}
		
		rs.addInst(countss);
		filtered=fltre;
	}
	
	private void displayFiltered(){
		
		DisplayAppriory dt=new DisplayAppriory(filtered);
		dt.setSize(1200,300);
		dt.setVisible(true);
		
	}	
	
	int ncr(int n, int r){
		if(r==0 || n==r)
			return 0;
		else
			return(ncr(n,r-1)+ncr(n-1,r-1));
	}
	
	
}
