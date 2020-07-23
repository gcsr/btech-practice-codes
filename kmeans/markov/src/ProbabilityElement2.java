
public class ProbabilityElement2 {
	String currentApp;
	String[] nextApp;
	double probability;
	
	public ProbabilityElement2(String currentApp,String[] nextApp,double probability){
		this.currentApp=currentApp;
		this.nextApp=nextApp;
		this.probability=probability;
	}
	
	public ProbabilityElement2(String currentApp,String[] nextApp){
		this.currentApp=currentApp;
		this.nextApp=nextApp;
		this.probability=0;
	}
	
	public String getCurrentApp() {
		return currentApp;
	}
	public void setCurrentApp(String currentApp) {
		this.currentApp = currentApp;
	}
	public String[] getNextApp() {
		return nextApp;
	}
	public void setNextApp(String[] nextApp) {
		this.nextApp = nextApp;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	
	
}
