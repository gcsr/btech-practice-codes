
public class MyClass implements java.io.Serializable {
	int no;
	byte[] data;
	String s;
	String filename;
	public MyClass(int no,byte[] data,String filename,String s)
	{
		this.no=no;
		this.filename=filename;
		this.data=data;
		this.s=s;
	}

}