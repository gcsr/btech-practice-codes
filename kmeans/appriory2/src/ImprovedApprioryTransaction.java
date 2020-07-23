import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ImprovedApprioryTransaction {
	Transaction tr;
	int count;	
	int countTemp=0;
	
	//ArrayList<Integer> transactionIdsTemp=null;
	HashSet transactionIdsTemp=null;
	Integer[] dumps;
	HashSet<Integer> transactionIds;
	
	ImprovedApprioryTransaction(Transaction tr,int count,HashSet transactionIds){
		this.tr=tr;
		this.count=count;
		this.transactionIds=transactionIds;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count=count;
	}
	
	ImprovedApprioryTransaction(Transaction tr){
		this.tr=tr;
		this.count=0;
		transactionIds=new HashSet<Integer>();
	}
	
	
	Transaction getTransaction(){
		return tr;
	}
	
	public void addId(int idNumber){
		transactionIds.add(idNumber);
	}
	
	public void setTransactionIds(HashSet transactionIds){
		if(transactionIds==null)
			transactionIds=new HashSet<Integer>();
		this.transactionIds=transactionIds;
	}
	
	public HashSet getTransactionIds(){
		return transactionIds;
	}
	
	
	
	public void addCount(){
		count++;
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
	
	public String displaySearch(){
		Iterator iterator=transactionIds.iterator();
		String output="";
		while(iterator.hasNext()){
			
			Integer idd=(Integer)iterator.next();
			output+="T"+idd+"  ,";
		}
		
		output=output.substring(0,output.length()-1);
		return output;
	}
	
	@Override
	public boolean equals(Object obj){
		ImprovedApprioryTransaction adssd=(ImprovedApprioryTransaction)obj;			
		boolean result=this.tr.equals(adssd.tr);			
		return result;
	}
	
	public void checkTransactions(Transaction[] ts){
		
		
		Iterator itr=transactionIds.iterator();
		countTemp=0;
		transactionIdsTemp=new HashSet<Integer>();
				
		while(itr.hasNext()){
			//System.out.println("in frqnt itemset iteratins");
			Integer ip=(Integer)itr.next();
			Transaction trr=ts[ip];
			ImprovedApprioryAlgorithm.countss++;
			if(trr.contains(tr)){
				countTemp++;
				transactionIdsTemp.add(ip);
			}
		}	
		
		count=countTemp;
		transactionIds=transactionIdsTemp;
	}
	
	
	

}
