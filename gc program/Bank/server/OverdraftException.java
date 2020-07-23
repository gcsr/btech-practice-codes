public class OverdraftException extends Exception
{
	public boolean _withdrawlSucceded;
	
	public OverdraftException(boolean withdrawlSucceded)
	{
		_withdrawlSucceded=withdrawlSucceded;
	}
	public boolean isWithdrawlSucceded()
	{
		return _withdrawlSucceded;
	}
}