package example;

import java.io.*;

import java.util.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.rmi.RemoteException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sforce.soap.metadata.AsyncResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.metadata.RetrieveMessage;
import com.sforce.soap.metadata.RetrieveRequest;
import com.sforce.soap.metadata.RetrieveResult;
import com.sforce.soap.metadata.RetrieveStatus;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sforce.soap.metadata.PackageTypeMembers;

public class SchemaRetrieve {

    // Binding for the metadata WSDL used for making metadata API calls
    private static MetadataConnection metadataConnection;
    
    static BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));

    // one second in milliseconds
    private static final long ONE_SECOND = 1000;
    // maximum number of attempts to retrieve the results
    private static final int MAX_NUM_POLL_REQUESTS = 50; 

    // manifest file that controls which components get retrieved
    private static final String MANIFEST_FILE = "E:/package123.xml"; 

    private static final double API_VERSION = 31.0; 

    public static synchronized com.sforce.soap.metadata.DescribeMetadataResult main(String USERNAME,String PASSWORD) throws Exception {
        //final String USERNAME = "sathiya@schemaproject.com";
        // This is only a sample. Hard coding passwords in source files is a bad practice.
        //final String PASSWORD = "n3rdn3rd2"; 
        final String URL = "https://login.salesforce.com/services/Soap/c/31.0";
        
        SchemaRetrieve sample = new SchemaRetrieve(USERNAME, PASSWORD, URL);
        double apiVersion = 30.0;
        com.sforce.soap.metadata.DescribeMetadataResult res = 
    	        metadataConnection.describeMetadata(apiVersion);
        return res;
        //describeMetadata();
        //listMetadata();
        //sample.retrieveZip();
    }
    
    
    public static synchronized MetadataConnection getMetadataConnection(String USERNAME,String PASSWORD) throws Exception {
        //final String USERNAME = "sathiya@schemaproject.com";
        // This is only a sample. Hard coding passwords in source files is a bad practice.
        //final String PASSWORD = "n3rdn3rd2"; 
        final String URL = "https://login.salesforce.com/services/Soap/c/31.0";
        
        SchemaRetrieve sample = new SchemaRetrieve(USERNAME, PASSWORD, URL);
        
        return metadataConnection;
        //describeMetadata();
        //listMetadata();
        //sample.retrieveZip();
    }
    
    public SchemaRetrieve(String username, String password, String loginUrl) 
            throws ConnectionException {
        createMetadataConnection(username, password, loginUrl);
    }

    
    private void retrieveZip() throws RemoteException, Exception
    {
        RetrieveRequest retrieveRequest = new RetrieveRequest();
        // The version in package.xml overrides the version in RetrieveRequest
        retrieveRequest.setApiVersion(API_VERSION);
        setUnpackaged(retrieveRequest);

        // Start the retrieve operation
        AsyncResult asyncResult = metadataConnection.retrieve(retrieveRequest);
        String asyncResultId = asyncResult.getId();
        
        // Wait for the retrieve to complete
        int poll = 0;
        long waitTimeMilliSecs = ONE_SECOND;
        RetrieveResult result = null;
        System.out.println("Metadata Retrieve started..!");
        do {
            Thread.sleep(waitTimeMilliSecs);
            // Double the wait time for the next iteration
            waitTimeMilliSecs *= 2;
            if (poll++ > MAX_NUM_POLL_REQUESTS) {
                throw new Exception("Request timed out.  If this is a large set " +
                "of metadata components, check that the time allowed " +
                "by MAX_NUM_POLL_REQUESTS is sufficient.");
            }
            result = metadataConnection.checkRetrieveStatus(
                    asyncResultId);
            System.out.println("Retrieve Status: " + result.getStatus());
        } while (!result.isDone());

        if (result.getStatus() == RetrieveStatus.Failed) {
            throw new Exception(result.getErrorStatusCode() + " msg: " +
                    result.getErrorMessage());
        } else if (result.getStatus() == RetrieveStatus.Succeeded) {      
            // Print out any warning messages
            StringBuilder buf = new StringBuilder();
            if (result.getMessages() != null) {
                for (RetrieveMessage rm : result.getMessages()) {
                    buf.append(rm.getFileName() + " - " + rm.getProblem());
                }
            }
            if (buf.length() > 0) {
                System.out.println("Retrieve warnings:\n" + buf);
            }
    
            // Write the zip to the file system
            System.out.println("Writing results to zip file");
            ByteArrayInputStream bais = new ByteArrayInputStream(result.getZipFile());
            File resultsFile = new File("retrieveResults.zip");
            FileOutputStream os = new FileOutputStream(resultsFile);
            try {
                ReadableByteChannel src = Channels.newChannel(bais);
                FileChannel dest = os.getChannel();
                copy(src, dest);
                
                System.out.println("Results written to " + resultsFile.getAbsolutePath());
                
                //	Start Deploy process
                SchemaDeploy.ZIP_FILE = resultsFile.getAbsolutePath();
                SchemaDeploy schDeploy = new SchemaDeploy("practice@cloud.com", "8880141616h", "https://login.salesforce.com/services/Soap/c/30.0");
                schDeploy.deployZip();
            } finally {
                os.close();
            }
        }
    }
    
    /**
     * Helper method to copy from a readable channel to a writable channel,
     * using an in-memory buffer.
     */
    private void copy(ReadableByteChannel src, WritableByteChannel dest)
        throws IOException
    {
        // Use an in-memory byte buffer
        ByteBuffer buffer = ByteBuffer.allocate(8092);
        while (src.read(buffer) != -1) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear();
        }
    }
    
    private void setUnpackaged(RetrieveRequest request) throws Exception
    {
        // Edit the path, if necessary, if your package.xml file is located elsewhere
        File unpackedManifest = new File(MANIFEST_FILE);
        System.out.println("Manifest file: " + unpackedManifest.getAbsolutePath());
        
        if (!unpackedManifest.exists() || !unpackedManifest.isFile())
            throw new Exception("Should provide a valid retrieve manifest " +
                    "for unpackaged content. " +
                    "Looking for " + unpackedManifest.getAbsolutePath());

        // Note that we populate the _package object by parsing a manifest file here.
        // You could populate the _package based on any source for your
        // particular application.
        com.sforce.soap.metadata.Package p = parsePackage(unpackedManifest);
        request.setUnpackaged(p);
    }

    private  synchronized com.sforce.soap.metadata.Package parsePackage(File file) throws Exception {
        try {
            InputStream is = new FileInputStream(file);
            List<PackageTypeMembers> pd = new ArrayList<PackageTypeMembers>();
            DocumentBuilder db =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Element d = db.parse(is).getDocumentElement();
            for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
                if (c instanceof Element) {
                    Element ce = (Element)c;
                    //
                    NodeList namee = ce.getElementsByTagName("name");
                    if (namee.getLength() == 0) {
                        // not
                        continue;
                    }
                    String name = namee.item(0).getTextContent();
                    NodeList m = ce.getElementsByTagName("members");
                    List<String> members = new ArrayList<String>();
                    for (int i = 0; i < m.getLength(); i++) {
                        Node mm = m.item(i);
                        members.add(mm.getTextContent());
                    }
                    PackageTypeMembers pdi = new PackageTypeMembers();
                    pdi.setName(name);
                    pdi.setMembers(members.toArray(new String[members.size()]));
                    pd.add(pdi);
                }
            }
            com.sforce.soap.metadata.Package r = new com.sforce.soap.metadata.Package();
            r.setTypes(pd.toArray(new PackageTypeMembers[pd.size()]));
            r.setVersion(API_VERSION + "");
            return r;
        } catch (ParserConfigurationException pce) {
            throw new Exception("Cannot create XML parser", pce);
        } catch (IOException ioe) {
            throw new Exception(ioe);
        } catch (SAXException se) {
            throw new Exception(se);
        }
    }
    
 
    private void createMetadataConnection(final String username,
            final String password, final String loginUrl)
            throws ConnectionException {

        final ConnectorConfig loginConfig = new ConnectorConfig();
        loginConfig.setAuthEndpoint(loginUrl);
        loginConfig.setServiceEndpoint(loginUrl);
        loginConfig.setManualLogin(true);
        LoginResult loginResult = (new EnterpriseConnection(loginConfig)).login(
                username, password);

        final ConnectorConfig metadataConfig = new ConnectorConfig();
        metadataConfig.setServiceEndpoint(loginResult.getMetadataServerUrl());
        metadataConfig.setSessionId(loginResult.getSessionId());
        this.metadataConnection = new MetadataConnection(metadataConfig);
    }
    
    //The sample client application retrieves the user's login credentials.
    // Helper function for retrieving user input from the console
    String getUserInput(String prompt) {
        System.out.print(prompt);
        try {
            return rdr.readLine();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static void listMetadata() {
	  try {
		com.sforce.soap.metadata.ListMetadataQuery query = new com.sforce.soap.metadata.ListMetadataQuery();
		String SelectedMetedataType = "CustomObject";
	    query.setType(SelectedMetedataType);
	    //query.setFolder(null);
	    double asOfVersion = 30.0;
	    // Assuming that the SOAP binding has already been established.
	    com.sforce.soap.metadata.FileProperties[] lmr = metadataConnection.listMetadata(
	        new com.sforce.soap.metadata.ListMetadataQuery[] {query}, asOfVersion);
	    if (lmr != null) {
	    	System.out.println(SelectedMetedataType+">>>>>>>>>>>>>>>>");
	    	for (com.sforce.soap.metadata.FileProperties n : lmr) {
	    		System.out.println("Component type: " + n.getFullName());
	      }
	    }            
	  } catch (ConnectionException ce) {
	    ce.printStackTrace();
	  }
	}
    
    public static void describeMetadata() {
	  try {
	    double apiVersion = 30.0;
	    // Assuming that the SOAP binding has already been established.
	    com.sforce.soap.metadata.DescribeMetadataResult res = 
	        metadataConnection.describeMetadata(apiVersion);
	    StringBuffer sb = new StringBuffer();
	    if (res != null && res.getMetadataObjects().length > 0) {
	      for (com.sforce.soap.metadata.DescribeMetadataObject obj : res.getMetadataObjects()) {
	        sb.append("***************************************************\n");
	        sb.append("XMLName: " + obj.getXmlName() + "\n");
	        sb.append("DirName: " + obj.getDirectoryName() + "\n");
	        sb.append("Suffix: " + obj.getSuffix() + "\n");
	        sb.append("***************************************************\n");
	      }
	    } else {
	      sb.append("Failed to obtain metadata types.");
	    }
	    System.out.println(sb.toString());
	  } catch (ConnectionException ce) {
	    ce.printStackTrace();
	  }
	}
}
