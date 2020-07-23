
import net.jini.core.entry.Entry;
import net.jini.core.lookup.ServiceID;
import net.jini.lookup.entry.Name;
import net.jini.lookup.ServiceIDListener;
import net.jini.lookup.JoinManager;
import net.jini.lease.LeaseRenewalManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
public class HelloServer extends UnicastRemoteObject 
   implements HelloInterface, ServiceIDListener
   {
   private ServiceID myID;
   
   public HelloServer() throws RemoteException 
      {      }
   public String sayHello () throws RemoteException
      {
      return ("Hello World from Jini Hello server!");
      }
   public void serviceIDNotify (ServiceID uniqueID)
      {
      myID = uniqueID;
      System.out.println("server: ID set: " + myID );
      }


   public static void main (String[] args) throws Exception
      {
      System.setSecurityManager (new RMISecurityManager ());
      HelloServer myServer = new HelloServer ();
      Entry[] identityingAttributes = new Entry[1];
      identityingAttributes[0] = new Name("HelloServer");
      JoinManager myManager = new JoinManager 
            (
            myServer, 
            identityingAttributes, 
            myServer, 
            new LeaseRenewalManager ()
            );
      System.out.println ("Server has been Joined!");
      }
   }