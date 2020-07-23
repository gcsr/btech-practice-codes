
public class Results {
	long times[];
	long instructions[];
	
	int timesCount=0;
	int instCount=0;
	
	public Results(int length){
		times=new long[length];
		instructions=new long[length];
	}
	
	public void setTimes(long[] times){
		this.times=times;
	}
	
	public void setInstructions(long[] instructions){
		this.instructions=instructions;
	}
	
	public long[] getTimes(){
		return times;
	}
	
	public long[] getInstructions(){
		return instructions;
	}
	
	public void addTime(long time){
		times[timesCount++]=time;
	}
	
	public void addInst(long inst){
		instructions[instCount++]=inst;
	}
	
	@Override
	public String toString(){
		String Times="";
		
		Times+="Time taken for each frequent set\n";
		for(long x:times){
			Times+="\t frequntset "+x;
		}
		
		String insts="Instructions executed for\n ";
		
		for(long x:instructions){
			insts+="\t frequentset "+x;
		}
		
		return Times+"\n"+insts;
	}
	
}
