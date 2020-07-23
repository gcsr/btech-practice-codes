
public class ApprioryTransaction{
		
		Transaction tr;
		int count;
		
		ApprioryTransaction(Transaction tr,int count){
			this.tr=tr;
			this.count=count;
		}
		
		ApprioryTransaction(Transaction tr){
			this.tr=tr;
			this.count=0;
		}
		
		public void addCount(){
			count++;
		}
		
		@Override
		public int hashCode(){
			return 0;
		}
		
		
		@Override
		public boolean equals(Object obj){
			ApprioryTransaction adssd=(ApprioryTransaction)obj;			
		//	System.out.println("appriory equals" +tr);
			//System.out.println("appriory equals" +adssd.tr);
			
			
			
			boolean result=this.tr.equals(adssd.tr);
			//System.out.println();
			return result;
		}
		
		
	}
	
   