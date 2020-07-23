import java.sql.Timestamp;


public class Message  implements Comparable{
	private int from;
	private int to;
	private String message;
	Timestamp time;
	String type;
	String status;
	String fileName;
	
	byte[] data;
	private int uniqueId;
	private int counter;
	
	private int fullSize;
	
	private int[] route;
	
	
	private String hash;
	
	
	
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int[] getRoute() {
		return route;
	}
	public void setRoute(int[] route) {
		this.route = route;
	}
	public int getFullSize() {
		return fullSize;
	}
	public void setFullSize(int fullSize) {
		this.fullSize = fullSize;
	}
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Message to=(Message)arg0;
		
		if(this.counter>to.counter)
			return 1;
		else return -3;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
	
	
	
}
