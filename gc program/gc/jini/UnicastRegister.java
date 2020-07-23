
import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import java.rmi.RMISecurityManager;
import java.net.URL;

public class UnicastRegister {
	
	public static void main(String[] argv) {
		System.out.println("i am in main");
		new UnicastRegister();
	}
	
	public UnicastRegister() {
		LookupLocator lookup = null;
		ServiceRegistrar registrar = null;
		System.setSecurityManager(new RMISecurityManager());
		try {
    	lookup = new LookupLocator("jini://localhost");
    	//lookup = new LookupLocator("jini://jan.netcomp.monash.edu.au");
		} catch(java.net.MalformedURLException e) {
		System.err.println("Lookup failed: " + e.toString());
		System.exit(1);
		}
		try {
			registrar = lookup.getRegistrar();
		} catch (java.io.IOException e) {
		System.out.println("error at getRegistrar method IOException");	
		e.printStackTrace();
		System.exit(1);
		} catch (java.lang.ClassNotFoundException e) {
		System.out.println("error at getRegistrar method ClassNotFoundException");		
		System.err.println("Registrar search failed: " + e.toString());
		System.exit(1);
		}
		System.out.println("Registrar found");
		// the code takes separate routes from here for client or service
	}
} //