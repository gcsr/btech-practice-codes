import java.io.Serializable;

public class AccountRecord implements Serializable
{
	private int recordNumber;
	private int idli,pongal,uthappam,dosa,voda,chapathi,lemRice,rice,extraRS;
	String extraItems; 
	String date;
	private String firstName;
	private String lastName;
	private double balance;
	public AccountRecord()
	{
		this(0,"",0,0,0,0,0,0,0,0,0,"");
	}
	public AccountRecord(int rno,String dt,int idli,int pongal,int uthappam,int dosa,int voda,
	int chapathi,int lemRice,int rice,int extraRS,String extraItems)
	{
		setRecordNumber(rno);
		setIdli(idli);
		setDate(dt);
		setPongal(pongal);
		setUthappam(uthappam);
		setDosa(dosa);
		setVoda(voda);
		setChapathi(chapathi);
		setLemRice(lemRice);
		setRice(rice);
		setExtraRS(extraRS);
		setExtraItems(extraItems);
		
	}
	public void setIdli(int num)
	{
		idli=num;
	}
	public int getIdli()
	{
		return idli;
	}
	public void setPongal(int num)
	{
		pongal=num;
	}
	public int getPongal()
	{
		return pongal;
	}
	public void setUthappam(int num)
	{
		uthappam=num;
	}
	public int getUthappam()
	{
		return uthappam;
	}
	public void setDosa(int num)
	{
		dosa=num;
	}
	public int getDosa()
	{
		return dosa;
	}
	public void setVoda(int num)
	{
		voda=num;
	}
	public int getVoda()
	{
		return voda;
	}
	public void setChapathi(int num)
	{
		chapathi=num;
	}
	public int getChapathi()
	{
		return chapathi;
	}
	public void setLemRice(int num)
	{
		lemRice=num;
	}
	public int getLemRice()
	{
		return lemRice;
	}
	
	public void setRice(int num)
	{
		rice=num;
	}
	public int getRice()
	{
		return rice;
	}
	
	public void setRecordNumber(int num)
	{
		recordNumber=num;
	}
	public int getRecordNumber()
	{
		return recordNumber;
	}
	
	public void setExtraRS(int num)
	{
		extraRS=num;
	}
	public int getExtraRS()
	{
		return extraRS;
	}
	public void setExtraItems(String items)
	{
		extraItems=items;
	}
	public String getExtraItems()
	{
		return extraItems;
	}
	public void setDate(String tedi)
	{
		date=tedi;
	}
	public String getDate()
	{
		return date;
	}
	
	
}