import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Account extends Remote 
{
	public Money getBalance()throws RemoteException;
	public void makeDeposit(Money aount)throws RemoteException,NegativeAmountException;
	public void makeWithdrawl(Money money)
			throws RemoteException,OverdraftException,NegativeAmountException;
}