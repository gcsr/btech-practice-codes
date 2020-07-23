import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import be.ehealth.businessconnector.genins.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.genins.domain.RequestParameters;
import be.ehealth.businessconnector.genins.session.GenInsService;
import be.ehealth.businessconnector.genins.session.GenInsSessionServiceFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityContactTypeType;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestTypeType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse;


public class JavaServer extends JFrame implements WindowListener{
	ServerSocket serverSocket=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket connection;
	public static boolean sessionCreated=false;
	public static boolean objectCreated=false;
	private JLabel bottomLabel; 
	ImageIcon connectedImage,notconnectedImage;
	static int numOfAttems=0;
	boolean windowClosed=false;
	static JavaServer server;
	public static void main(String[] gcs){
		//getIPAddresses();
		//createSession();
		
		server=new JavaServer();
		      	
        server.createSession();
        server.makeConnection();
       
		
	}
	public JavaServer(){
		super("E-Health Connector");
		setSize(330,240);		
		setLayout(new GridLayout(2,1));
		setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon(getClass().getResource("/images/icon.png"));
		setIconImage(img.getImage());
		ImageIcon image = new ImageIcon(getClass().getResource("/images/home.png"));
        JLabel label = new JLabel(image);
        connectedImage = new ImageIcon(getClass().getResource("/images/connected.png"));
        notconnectedImage=new ImageIcon(getClass().getResource("/images/notconnected.png"));
        bottomLabel=new JLabel(notconnectedImage);
        add(label);
        add(bottomLabel);
		
		setResizable(false);
		addWindowListener(this);
		JavaServer.objectCreated=true;
		setVisible(true);
		//createSession();
		//makeConnection();
		//pack();
	}
	public void run(){
		
		//getIPAddresses();
		createSession();		
		makeConnection();
	}
	
	public void createSession(){
		try {
			
			ConfigFactory.setConfigLocation(
					"/config/be.ehealth.technicalconnector.properties");

			System.out.println(ConfigFactory.getConfigValidator().getProperty("KEYSTORE_DIR"));
	        //SessionInitializer.init("config/be.ehealth.technicalconnector.properties", true);
			
			Session.getInstance().unloadSession();
			

			SessionManager sessionmgmt = Session.getInstance();

			if (!sessionmgmt.hasValidSession()) {
				sessionmgmt.createSession("centredentaire321","centredentaire321");
			}else{
				sessionmgmt.unloadSession();
				sessionmgmt.createSession("centredentaire321","centredentaire321");
			}
			
			bottomLabel.setIcon(connectedImage);
			invalidate();
			repaint();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			JOptionPane.showMessageDialog(null," Error Creating Session, please try again");
			
			numOfAttems++;
			if(numOfAttems>=3)
				System.exit(0);
			createSession();
		}
		
		sessionCreated=true;


	}

	public void getIPAddresses(){
		
		
		Enumeration e;
		
		try {
			
			InetAddress IP=InetAddress.getLocalHost();
			System.out.println("IP of my system is := "+IP.getHostAddress());
			/*e = NetworkInterface.getNetworkInterfaces();
		
			while(e.hasMoreElements())
			{
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements())
				{
					InetAddress i = (InetAddress) ee.nextElement();
					System.out.println(i.getHostAddress());
				}
			}*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void makeConnection(){
		try // set up server to receive connections; process connections
		{
		    serverSocket = new ServerSocket( 20000); // create ServerSocket
		    while ( !windowClosed )
		    {
		        try
		        {
		            waitForConnection(); // wait for a connection
		            getStreams(); // get input & output streams
		            processConnection(); // process connection
		            //closeConnection();
		         } // end try
		         catch ( EOFException eofException )
		         {
		             System.out.println("Exception while making connections");
		             eofException.printStackTrace();
		         } // end catch
		         finally
		         {
		            //closeConnection(); // close connection
		         } // end finally
		     } // end while
		 } // end try
		 catch ( IOException ioException )
		 {
		     ioException.printStackTrace();
		 } // end catch		
	}
	
	private void waitForConnection() throws IOException
	{
	     System.out.println("Server Waiting on Port "+20000);
	     connection = serverSocket.accept(); 
	     System.out.println(" connected "+20000);
	     // allow server to accept connection
	} // end method waitForConnection
	
	private void getStreams() throws IOException
	{
	     // set up output stream for objects
	     //output = new ObjectOutputStream( connection.getOutputStream() ); 
	     //output.flush(); // flush output buffer to send header information
	
	     input = new ObjectInputStream( connection.getInputStream() );
	     
	} 
	
	private void processConnection() throws IOException{
		String message;
		try // read message and display it
	        {
	            message = ( String ) input.readObject(); // read new message
	            System.out.println(message);
	            parseString(message);
	           // WriteToFile.writeToFile(message);
	            closeConnection();
	    
	        } // end try
	        catch ( ClassNotFoundException classNotFoundException )
	        {
	      
	        } // end catch
	    
	 } // end method processConnection
	private void closeConnection()
	{
	      try
	      {
	         input.close(); // close input stream  
	         connection.close(); // close socket   
	      }  // end try
	      catch ( IOException ioException )
	      {
	         ioException.printStackTrace();
	      } // end catch
	} // end method closeConnection
	
	
	private static void parseString(String fileContentsText){

		String[] dates2=new String[2];
		String first="";
		String displayText="";
		
		try{

			System.out.println(fileContentsText);
			String[] split=fileContentsText.split("\\*");
			
			dates2[0]=split[1];
			dates2[1]=split[2];
			
		   first=split[0];//fileContentsText.substring(0,fileContentsText.indexOf(dates2[0]));

		}catch(Exception ex){
			ex.printStackTrace();
			//JOptionPane.showMessageDialog(null," line no 227");
			//JOptionPane.showMessageDialog(null, "Invalid input or response");
			return;
		}	
	   
		//String first=fileContentsText.substring(0,fileContentsText.indexOf(dates2[0])); 
	   try{
			
			
			RequestParameters requestParameters = new RequestParameters();
					
			if(!first.contains("-")){
				
				requestParameters.setInss(first);
				displayText+="INSS : "+first+"\n";
				
			}
			else{
				requestParameters.setInss(null);
				
				
				String[] split=first.split("-");
				//System.out.println(split[0]);
				
				requestParameters.setMutuality(split[1]);
				displayText+="Mutuality : "+split[1]+"\n";
				
				requestParameters.setRegNrWithMut(split[0]);
				displayText+="Registration with Mutuality : "+split[0]+"\n";
				
			}
		  		
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dateInString1 =dates2[0];//period;
			String dateInString2 =dates2[1];
			try {

				Date date = formatter.parse(dateInString1);
				DateTime dateTime = new DateTime(date);
				requestParameters.setPeriodStart(dateTime);//dateTime);
				date = formatter.parse(dateInString2);
				dateTime = new DateTime(date);
				requestParameters.setPeriodEnd(dateTime);//dateTime);
		
			} catch (ParseException e) {
				//JOptionPane.showMessageDialog(null," line no 274");
				//JOptionPane.showMessageDialog(null, "Invalid dates");
				return;
			}
			
			displayText+="Start Date : "+dates2[0]+"\n";
			displayText+="End Date : "+dates2[1]+"\n";
			
       

			requestParameters.setInsurabilityContactType(InsurabilityContactTypeType.OTHER);
			requestParameters.setInsurabilityRequestType(InsurabilityRequestTypeType.INFORMATION);
			requestParameters.setInsurabilityReference(IdGeneratorFactory.getIdGenerator().generateId());
			
			// Logger logger = LoggerFactory.getLogger(be.ehealth.technicalconnector.utils.ConnectorXmlUtils.class);
			GetInsurabilityAsXmlOrFlatRequestType getInsurabilityAsXmlOrFlatRequestType = RequestObjectBuilderFactory.getRequestObjectBuilder().createGetInsurabilityRequest(requestParameters,false);
			GenInsService service = GenInsSessionServiceFactory.getGenInsService();
			
			GetInsurabilityResponse response = service.getInsurability(getInsurabilityAsXmlOrFlatRequestType); 

			String fileContent=ConnectorXmlUtils.toString(response);

		    save(fileContent);  
			 
		}catch(Exception ex){
			ex.printStackTrace();
			//ex.
			//JOptionPane.showMessageDialog(null," line no 298 "+ex.getMessage());
			//JOptionPane.showMessageDialog(null, "Invalid input or response");
			
		}	
			 
		

			
	}
	public static void save(String sb){
		
		String decodedPath="";
		try{
			String path = JavaServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			decodedPath = URLDecoder.decode(path, "UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		/*System.out.println(decodedPath);
		decodedPath=decodedPath+""+"MyCareNet/";
		System.out.println(decodedPath);*/
		File f=new File(decodedPath);
		String fullPath=(f.getAbsolutePath());
		fullPath=fullPath.substring(0,fullPath.lastIndexOf("."));//+"MyCareNet\\";
		System.out.println(fullPath);
		/*File ff=new File(fullPath);
		
		if(!ff.exists()){
			ff.mkdirs();
			System.out.println("created directory");
		}*/
	
	try{
		fullPath=fullPath+"response.xml";
		System.out.println(fullPath);
		File file =new File(fullPath);
		
		//if file doesnt exists, then create it
		if(!file.exists()){
			file.createNewFile();
			System.out.println("created file");
		}
		
		//true = append file
		
		  FileWriter fileWritter = new FileWriter(file.getAbsolutePath());
	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	      bufferWritter.write(sb);
	      bufferWritter.close();	           	    	    
	      //System.out.println("fullData is written");    	      
		
        
	}catch(IOException e){
		e.printStackTrace();
	}
	
}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		try{
			windowClosed=true;
			if(serverSocket!=null){
				if(!serverSocket.isClosed())
					serverSocket.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.exit(0);
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		try{
			windowClosed=true;
			if(serverSocket!=null){
				if(!serverSocket.isClosed())
					serverSocket.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.exit(0);
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
