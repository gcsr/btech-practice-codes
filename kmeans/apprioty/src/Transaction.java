public class Transaction{
		int[] itemIds;
		int count=0;
		
		public Transaction(int size){
			itemIds=new int[size];
		}
		
		
		public void setIds(int[] itemIds){
			this.itemIds=itemIds;
			count=itemIds.length;
		}
		
		public void add(int i){
			itemIds[count]=i;
			count++;
		}
		
		public void display(){
			String output="";
			
			for(int i : itemIds){
				output+=i+",";
			}
			
			output=output.substring(0,output.lastIndexOf(','));
			System.out.print(output);
		}
		
		boolean isExisting(int number){
			
			//System.out.println("checking for is existing");
			for(int i=0;i<count;i++)
				if(itemIds[i]==number)
					return true;
			return false;
		}
		
		public String toString(){
			String output="";
			
			if(count==0)
				return "";
			
			for(int i : itemIds){
				output+="I"+i+"  ,";
			}
			
			output=output.substring(0,output.length()-1);
			return output;
		}
		
		public int[] getIds(){
			return itemIds;
		}
		
		boolean isSame(Transaction tr){
			
			//System.out.println("in is same");
			//System.out.println(tr);
			//System.out.println(this);
			
			if(tr.count!=count)
				return false;
			else{
				int[] ddd=tr.itemIds;
				int numberofMatchings=0;
				for(int i=0;i<count;i++){
					if(isExisting(ddd[i]))
						numberofMatchings++;
					else return false;
				}
				
				if(numberofMatchings==count)
					return true;
			}
			
			//System.out.println("returning ture");
			return false;
		}
		
		@Override
		public boolean equals(Object obj){
			//System.out.println("equals called");
			
			Transaction tr=(Transaction)obj;
			
			
			display();
			tr.display();
			
			if(tr.count!=count)
				return false;
			else{
				int[] ddd=tr.itemIds;
				int numberofMatchings=0;
				for(int i=0;i<count;i++){
					if(isExisting(ddd[i]))
						numberofMatchings++;
					else return false;
				}
				
				//System.out.println("number of matchings is"+numberofMatchings);
				//System.out.println("count is"+count);
				
				if(numberofMatchings==count)
					return true;
			}
			
			//System.out.println("returning ture");
			return false;
		}
		
	public boolean contains(Transaction tr){
		if(tr.count>count)
			return false;
		else{
			int[] ddd=tr.itemIds;
			int checkLength=ddd.length;
			int numberofMatchings=0;
			for(int i=0;i<checkLength;i++){
				if(isExisting(ddd[i]))
					numberofMatchings++;
				else return false;
			}
			
			if(numberofMatchings==checkLength)
				return true;
		}
		
		//System.out.println("returning ture");
		return false;		
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
		
}
	
