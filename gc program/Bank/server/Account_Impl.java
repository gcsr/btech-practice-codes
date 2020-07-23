import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Account_Impl extends UnicastRemoteObject implements Account
{
	private Money _balance;
	
	public Account_Impl(Money startingBalance)throws RemoteException
	{
		System.out.println("cons called");
		_balance=startingBalance;
	}
	
	public Money getBalance() throws RemoteException
	{
		System.out.println("getBalance called");
		return _balance;
	}
	
	public void makeDeposit(Money amount)throws RemoteException,NegativeAmountException
	{
		checkForNegativeAmount(amount);
		_balance.add(amount);
		return;
	}
	
	public void makeWithdrawl(Money amount)throws RemoteException,NegativeAmountException,OverdraftException
	{
		checkForNegativeAmount(amount);
		checkForOverdraft(amount);
		_balance.substract(amount);
	}
	
	public void checkForNegativeAmount(Money amount)throws NegativeAmountException
	{
		int cents=amount.getCents();
		if(cents<0)
			throw new NegativeAmountException();
			
		return;	
	}
	
	public void checkForOverdraft(Money amount)throws OverdraftException
	{
		if(amount.greaterThan(_balance))
			throw new OverdraftException(false);
			
		return;	
	}
	
	
}