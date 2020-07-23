
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessAccountRecord extends AccountRecord
{
	public static final int SIZE=(36+4+20+60);

	public RandomAccessAccountRecord()
	{
		this(0,"",0,0,0,0,0,0,0,0,0,"");	
	}
	
	public RandomAccessAccountRecord(int rno,String dt,int idli,int pongal,int uthappam,int dosa,int voda,
	int chapathi,int lemRice,int rice,int extraRS,String extraItems)
	{
		super(rno,dt,idli,pongal,uthappam,dosa,voda,chapathi,lemRice,rice,extraRS,extraItems);
	}
	
	public void read(RandomAccessFile file)throws IOException
	{
		
		setRecordNumber(file.readInt());
		
		setDate(readDate(file));
		
		setIdli(file.readInt());
		setPongal(file.readInt());
		setUthappam(file.readInt());
		setDosa(file.readInt());
		setVoda(file.readInt());
		setChapathi(file.readInt());
		setLemRice(file.readInt());
		setRice(file.readInt());
		setExtraRS(file.readInt());
		
		
		setExtraItems(readExtraItems(file));
		
		
	}
	private String readDate(RandomAccessFile file)throws  IOException
	{
		char name[]=new char[10],temp;
		for(int count=0;count<name.length;count++)
		{
			temp=file.readChar();
			name[count]=temp;
		}
		return new String(name).replace('\0',' ');

	}
	private void writeDate(RandomAccessFile file,String name)throws IOException
	{
		StringBuffer buffer=null;
		if(name!=null)
			buffer=new StringBuffer(name);
		else
			buffer=new StringBuffer(10);
		buffer.setLength(10);
		file.writeChars(buffer.toString());

	}
	private String readExtraItems(RandomAccessFile file)throws  IOException
	{
		char name[]=new char[30],temp;
		for(int count=0;count<name.length;count++)
		{
			temp=file.readChar();
			name[count]=temp;
		}
		return new String(name).replace('\0',' ');

	}
	private void writeExtraItems(RandomAccessFile file,String name)throws IOException
	{
		StringBuffer buffer=null;
		if(name!=null)
			buffer=new StringBuffer(name);
		else
			buffer=new StringBuffer(30);
		buffer.setLength(30);
		file.writeChars(buffer.toString());

	}
	
	public void write(RandomAccessFile file)throws IOException
	{
		file.writeInt(getRecordNumber());
		
		writeDate(file,getDate());
		file.writeInt(getIdli());
		file.writeInt(getPongal());
		file.writeInt(getUthappam());
		file.writeInt(getDosa());
		file.writeInt(getVoda());
		file.writeInt(getChapathi());
		file.writeInt(getLemRice());
		file.writeInt(getRice());
		file.writeInt(getExtraRS());
			
		writeExtraItems(file,getExtraItems());
		
	}
}
