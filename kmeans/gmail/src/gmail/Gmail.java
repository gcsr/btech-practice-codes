package gmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;


public class Gmail {
	public static void main(String[] gcs){
		/* String host = "pop.gmail.com";// change accordingly
	     String mailStoreType = "pop3";
	     String username = "tracing.client@gmail.com";// change accordingly
	     String password = "gcsekharharish";// change accordingly

	     processMail(host, mailStoreType, username, password);*/
		 sendMail("");
		 processIMAPReceive();
	}
	
	public static void processIMAPReceive(){
		/* String host = "smtp.gmail.com";// change accordingly
	     String mailStoreType = "smtp";*/
	     String username = "tracing.client@gmail.com";// change accordingly
	     String password = "gcsekharharish";// change accordingly

	     processMailIMAP(username, password);
	}
	
	public static void sendMail(String body){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session sessions = Session.getInstance(props,
		    new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        			return new PasswordAuthentication("tracing.client@gmail.com","gcsekharharish");
		        }
        	});
       try {
           Message message = new MimeMessage(sessions);
           message.setFrom(new InternetAddress("tracing.client@gmail.com"));
           message.setRecipients(Message.RecipientType.TO,
           InternetAddress.parse("tracing.client@gmail.com"));
           message.setSubject("tracing client");
           MailObject mo=new MailObject();
           mo.setCustomer("customer");
           mo.setDate("date");
           mo.setLocation("location");
           mo.setUser("user");
           mo.setX(0);
           mo.setY(0);
           
           message.setText(mo.toString());
           System.out.println(message.getContent().toString());
           Transport.send(message);
           System.out.println("working"+"Done");
           } catch (MessagingException e) {
        	   System.out.println("exception"+ "error");
            e.printStackTrace();
            //throw new RuntimeException(e);
           }catch(Exception ex){
        	   System.out.println("exception"+"error");
            ex.printStackTrace();
          }
	}
		
	
	public static void processMail(String host,String mailStoreType,String user,String password){
		
		try {

		      //create properties field
		      Properties properties = new Properties();

		      properties.put("mail.pop3.host", host);
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("pop3s");

		      store.connect(host, user, password);

		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_ONLY);

		      // retrieve the messages from the folder in an array and print it
		      Message[] messages = emailFolder.getMessages();
		      System.out.println("messages.length---" + messages.length);

		      for (int i = 0, n = messages.length; i < n; i++) {
		         Message message = messages[i];
		         System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		         System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("Text: " + message.getContent().toString());

		      }

		      //close the store and folder objects
		      emailFolder.close(false);
		      store.close();

		      } //catch (NoSuchProviderException e) {
		         //e.printStackTrace();
		      //}
				catch (MessagingException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }

	}
	
	
	public static void processMailIMAP(String user,String password){
		try {

		      //create properties field
		      Properties properties = new Properties();

		      
		      //properties.put("mail.smtp.auth", "true");
		      //properties.put("mail.smtp.starttls.enable", "true");
		      //properties.put("mail.smtp.host", "smtp.gmail.com");
		      
		      //properties.put("mail.smtp.port", "465");
		        
		      //properties.put("mail.pop3.host", host);
		      //properties.put("mail.pop3.port", "995");
		      //properties.put("mail.pop3.starttls.enable", "true");
		      properties.setProperty("mail.store.protocol", "imaps");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("imaps");
		      store.connect("imap.googlemail.com",user, password);

		     // store.connect(host, user, password);

		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_WRITE);

		      // retrieve the messages from the folder in an array and print it
		      
		      Flags seen = new Flags(Flags.Flag.SEEN);
		      FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		      Message[] messages = emailFolder.search(unseenFlagTerm);
		      //Message[] messages = emailFolder.getMessages();
		      System.out.println("messages.length---" + messages.length);

		      for (int i = 0, n = messages.length; i < n; i++) {
		         Message message = messages[i];
		         System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		         System.out.println("From: " + message.getFrom()[0]);       
		         System.out.println("From: " + message.getContentType());
		         System.out.println("Text: " +new MailObject(getStringFromInputStream((InputStream)message.getContent())));
		      }

		      //close the store and folder objects
		      emailFolder.close(false);
		      store.close();

		      } //catch (NoSuchProviderException e) {
		         //e.printStackTrace();
		      //}
				catch (MessagingException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
	}
	
	public  static void ProcessMessage(Message msg){

	}

	public static MailObject getMapObject(Message msg){

		return null;
	}
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}
