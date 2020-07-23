import java.io.Serializable;

public class Money implements  Serializable
{
	protected int _cents;
	
	public int getCents()
	{
		return _cents;
	}
	public Money(Integer cents)
	{
		
		this(cents.intValue());
	}
	public Money(int cents)
	{
		_cents=cents;
	}
	public void add(Money otherMoney)
	{
		_cents+=otherMoney.getCents();
	}
	public void substract(Money otherMoney)
	{
		_cents-=otherMoney.getCents();
	}
	
	public boolean greaterThan(Money otherMoney)
	{
		if(_cents>otherMoney.getCents())
			return true;
			return false;
	}	 
	
	public boolean isNegative()
	{
		return _cents<0;
	}	
	public boolean equals(Object object)
	{
		if(object instanceof Money)
		
		{	Money otherMoney=(Money)object;
			
			return (_cents==otherMoney.getCents());
		}
		else
			return false;	
			
	}

}