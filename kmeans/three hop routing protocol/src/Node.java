
public class Node {
	private int port;
	private String name;

	private int nodeNo;
	
	private int bandwidth;
	private int limitMessages;
	public int messageQueue;
	private int cleaningTime;
	private int baseStation1;
	private int baseStation2;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	public int getLimitMessages() {
		return limitMessages;
	}
	public void setLimitMessages(int limitMessages) {
		this.limitMessages = limitMessages;
	}
	public int getMessageQueue() {
		return messageQueue;
	}
	public void setMessageQueue(int messageQueue) {
		this.messageQueue = messageQueue;
	}
	public int getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
	}
	public int getCleaningTime() {
		return cleaningTime;
	}
	public void setCleaningTime(int cleaningTime) {
		this.cleaningTime = cleaningTime;
	}
	public int getBaseStation1() {
		return baseStation1;
	}
	public void setBaseStation1(int baseStation1) {
		this.baseStation1 = baseStation1;
	}
	public int getBaseStation2() {
		return baseStation2;
	}
	public void setBaseStation2(int baseStation2) {
		this.baseStation2 = baseStation2;
	}
	
	
	
	
}
