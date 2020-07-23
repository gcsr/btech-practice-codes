package example;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * Login utility. 
 */
public class MetadataLoginUtil {

    public static MetadataConnection login() throws ConnectionException {
        final String USERNAME = "sathiya@schemaproject.com";
        // This is only a sample. Hard coding passwords in source files is a bad practice.
        final String PASSWORD = "n3rdn3rd2"; 
        final String URL = "https://login.salesforce.com/services/Soap/c/29.0";
        final LoginResult loginResult = loginToSalesforce(USERNAME, PASSWORD, URL);
        System.out.println(loginResult.toString());
        return createMetadataConnection(loginResult);
    }
    
    public static void main(String[]args){
    	try{
    		MetadataConnection connection = login();
    		System.out.println(connection.toString());
    	}catch(ConnectionException ex){
    		ex.printStackTrace();
    		System.out.println("Error");
    	}
    }

    private static MetadataConnection createMetadataConnection(final LoginResult loginResult) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setServiceEndpoint(loginResult.getMetadataServerUrl());
        config.setSessionId(loginResult.getSessionId());
        return new MetadataConnection(config);
    }

    private static LoginResult loginToSalesforce(
            final String username,
            final String password,
            final String loginUrl) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setAuthEndpoint(loginUrl);
        config.setServiceEndpoint(loginUrl);
        config.setManualLogin(true);
        return (new EnterpriseConnection(config)).login(username, password);
    }
}