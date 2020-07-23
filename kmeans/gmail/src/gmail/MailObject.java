package gmail;
/**
 * Created by gc on 2016-04-18.
 */
public class MailObject {

    private String user;
    private String customer;
    private String date;
    private String location;
    private float x;
    private float y;
    
    public MailObject(){
    	
    }
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
    
    
	public String toString(){
		String result="";
		result+="user : "+user+" ffff";
		result+="customer : "+customer+" ffff";
		result+="date : "+date+" ffff";
		result+="location : "+location+" ffff";
		result+="x : "+x+" ffff";
		result+="y : "+y+" ffff";
		
		return result;
	}
	
	public MailObject(String input){
		String[] lines=input.split(" ffff");
		this.user=lines[0].substring(7);
		this.customer=lines[1].substring(11);
		this.date=lines[2].substring(7);
		this.location=lines[3].substring(11);
		this.x=Float.parseFloat(lines[4].substring(4));
		this.y=Float.parseFloat(lines[5].substring(4));
	}


}
