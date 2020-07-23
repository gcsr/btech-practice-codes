import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



public class ImprovedApprioryAlgorithm {
	
	Transaction[] transactions;
	int size;
	int shopItemNumber;
	int basketItemNumber;
	int minimumSupport;
	int numberOfProducts;
	long first,last;
	static int countss=0;
	List<ImprovedApprioryTransaction> filtered=null;
   
	Results rs;
	public ImprovedApprioryAlgorithm(Transaction[] transactions,int minimumSupport,int size, int shopItemNumber,int basketItemNumber,int numberOfProducts){
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
		filtered=new LinkedList<ImprovedApprioryTransaction>();
		first=System.currentTimeMillis();
		
		for(int i=0;i<shopItemNumber;i++){
			trr=new Transaction(1);
			trr.add(i+1);
			filtered.add(new ImprovedApprioryTransaction(trr));
		}
		
		for(int i=0;i<size;i++){
			int[] itmee=transactions[i].itemIds;
			
			for(int x:itmee){
				filtered.get(x-1).addCount();
				filtered.get(x-1).addId(i);
			}
		}
		last=System.currentTimeMillis();
		
		rs.addInst(size);
		rs.addTime(last-first);
		
		displayFiltered();
	
		for(int i=1;(i<numberOfProducts && filtered.size()>0);i++){
			findCandidateSets();
			first=System.currentTimeMillis();				
			findFrequentItemSets();
			last=System.currentTimeMillis();						
			//System.out.println(" time gap is "+(last-first) +" milllis seconds ");
			rs.addTime(last-first);
			displayFiltered();
		}
		
		//displayFiltered();
		
		System.out.println(rs);
		
		return rs;
		
	}
	
	private void findCandidateSets(){
		
		System.out.println("in find candidate sets");
		
		findJoin();				
	}
	
	private void findJoin(){
		List leftSet=filtered;
		List rightSet=filtered;
		HashSet newSet=new HashSet<ImprovedApprioryTransaction>();		
		int nextLength=0;		
		nextLength=filtered.get(0).tr.itemIds.length+1;
		
		//int[] newIds=new int[nextLength];
		Transaction rightTransaction,leftTransaction;
		
		
		int[] leftIds;
				
		int sizzz=leftSet.size();
		ImprovedApprioryTransaction atr=null,latr=null;
		int spsppsps=0;
		int currentLength;
		int rightIds[];
		boolean existAl=false;
		int brandNewId;
		Transaction newTransaction;
		ImprovedApprioryTransaction newAppr;	
			
		for(int spsp=0;spsp<(sizzz/2+1);spsp++){		
			
			atr=(ImprovedApprioryTransaction)leftSet.get(spsp);			
			leftTransaction=atr.tr;
			leftIds=leftTransaction.getIds();
			currentLength=leftIds.length;			
			
			spsppsps=0;
			for(spsppsps=0;spsppsps<sizzz;spsppsps++){
				latr=(ImprovedApprioryTransaction)rightSet.get(spsppsps);
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
								
						
					newAppr=new ImprovedApprioryTransaction(newTransaction);					
					newAppr.setTransactionIds(atr.getTransactionIds());
					newSet.add(newAppr);
					
					//System.out.println("size after adding "+newSet.size());
					
				}
				
			}
		}
		
		filtered=new LinkedList<ImprovedApprioryTransaction>();
		
		Iterator itttt=newSet.iterator();
		
		while(itttt.hasNext()){
			filtered.add((ImprovedApprioryTransaction)itttt.next());
		}
		
		//displayFiltered();
	}	
	
	public void findFrequentItemSets(){
		
		ImprovedApprioryTransaction temp; 
		
		List fltre=new LinkedList<ImprovedApprioryTransaction>();
		Iterator iterator=filtered.iterator();
		ImprovedApprioryAlgorithm.countss=0;
		
		while(iterator.hasNext()){
			temp=(ImprovedApprioryTransaction)iterator.next();
			temp.checkTransactions(transactions);
			if(temp.count>=minimumSupport)
				fltre.add(temp);
		}
		
		rs.addInst(ImprovedApprioryAlgorithm.countss);
		
		filtered=fltre;
	}
	
	private void displayFiltered(){
		
		DisplayImprovedAppriory dt=new DisplayImprovedAppriory(filtered);
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
