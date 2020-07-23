
public class ResultsList implements Comparable{
	String currentApp;
	String nextApp;
	int positives;
	int negatives;
	
	public ResultsList(String currentApp,String nextApp,int positives,int negatives){
		this.currentApp=currentApp;
		this.nextApp=nextApp;
		this.positives=positives;
		this.negatives=negatives;
	}
	public String getCurrentApp() {
		return currentApp;
	}
	public void setCurrentApp(String currentApp) {
		this.currentApp = currentApp;
	}
	public String getNextApp() {
		return nextApp;
	}
	public void setNextApp(String nextApp) {
		this.nextApp = nextApp;
	}
	public int getPositives() {
		return positives;
	}
	public void setPositives(int positives) {
		this.positives = positives;
	}
	public int getNegatives() {
		return negatives;
	}
	public void setNegatives(int negatives) {
		this.negatives = negatives;
	}
	@Override
	public int compareTo(Object arg0) {
		ResultsList lst=(ResultsList)arg0;
		
		return lst.positives-this.positives;
		// TODO Auto-generated method stub
		
	}
	
}
