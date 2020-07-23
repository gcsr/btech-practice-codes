import java.io.Serializable;


public class Record implements Serializable{
	private int id;
	private int nodes;
	private int switches;
	private int hubs;
	private String topology;
	private String country;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNodes() {
		return nodes;
	}
	public void setNodes(int nodes) {
		this.nodes = nodes;
	}
	public int getSwitches() {
		return switches;
	}
	public void setSwitches(int switches) {
		this.switches = switches;
	}
	public int getHubs() {
		return hubs;
	}
	public void setHubs(int hubs) {
		this.hubs = hubs;
	}
	public String getTopology() {
		return topology;
	}
	public void setTopology(String topology) {
		this.topology = topology;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
