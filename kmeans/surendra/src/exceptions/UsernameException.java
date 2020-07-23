package exceptions;

public class UsernameException extends Exception{
	
	String msg;
	public UsernameException(String msg){
		//System.out.println(" this is bu");
		this.msg=msg;
	}
	
	public String getMessage(){
		return msg;
	}
}
