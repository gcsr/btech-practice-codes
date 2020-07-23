
public class ProbabilityElement implements Comparable{
	private String currentApp;
	private String nextApp;
	private double probability;
	
	public ProbabilityElement(String currentApp,String nextApp,double probability){
		this.currentApp=currentApp;
		this.nextApp=nextApp;
		this.probability=probability;
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
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	@Override
	public int compareTo(Object arg0) {
		ProbabilityElement ss=(ProbabilityElement)arg0;
		if(ss.probability>this.probability)
			return 122;
		// TODO Auto-generated method stub
		else
		return -40;
	}
	
}
