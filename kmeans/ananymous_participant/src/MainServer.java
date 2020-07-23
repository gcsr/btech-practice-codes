import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainServer{
	static ArrayList<Node> nodesInNetwork=new ArrayList<Node>();
	static ArrayList<Message> messages=new ArrayList<Message>();
	static final int MAINSERVERNODE=100;
	static int uniqueId=1;
	
	static KeyPair keyPair=null;
	
	static{
		try{
		   KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");  
		   keyPairGenerator.initialize(2048); //1024 used for normal securities  
		   keyPair = keyPairGenerator.generateKeyPair();  
		   PublicKey publicKey = keyPair.getPublic();  
		   PrivateKey privateKey = keyPair.getPrivate();  
		  // System.out.println("Public Key - " + publicKey);  
		   //System.out.println("Private Key - " + privateKey);  
		}catch(Exception ex){
			
		}
	}
	
	public MainServer(){
		
	}
	
	public static void createNetwork(int no){
		for(int i=0;i<no;i++){
			Node node=new Node();
			node.setName("Node "+(i+1));
			node.setType("Participatory");
			node.setPort(19000+i);
			node.setNodeNo(i+1);
			node.setHash(""+(i+1));
			nodesInNetwork.add(node);
			
		}
		
		Node node=new Node();
		node.setName("Node "+(100));
		node.setType("Participatory");
		node.setPort(29000);
		node.setNodeNo(100);
		node.setHash("100");
		nodesInNetwork.add(node);
		
	
		for(int i=0;i<nodesInNetwork.size();i++){
			new NetworkNode(nodesInNetwork.get(i));
		}
		
		
	}
	public void startServer(){
		
	}
	
	public void startClient(Node n){
		
	}
	
	private int generatePort(){
		
		
		return 0;
	}
	
	class CreateNodePanel extends JPanel{
		JLabel nodesLabel;
		JTextField nodesField;
		public CreateNodePanel(){
			setLayout(new GridLayout(1,2));
			nodesLabel=new JLabel("No of Nodees");
			nodesField=new JTextField("Nodes No");
			add(nodesLabel);
			add(nodesField);
		}
	}
	
	
	
	public static void addMessage(Message message){
		
		
		
		synchronized(messages){
			Message temp=message;//(Message)(message.clone());
			//System.out.println(message);
			int[] route=message.getRoute();
		
				temp.setTo(route[0]);
				//temps.add(temp);
				int nextRoute[]=findNextRoute(route);
				if(nextRoute!=null){
					Message newMessage=new Message();
					newMessage.setFrom(temp.getTo());
					newMessage.setRoute(nextRoute);
					newMessage.setMessage(temp.getMessage());
					newMessage.setUniqueId(temp.getUniqueId());
					newMessage.setCounter(temp.getCounter());
					newMessage.setHash(temp.getHash());
					newMessage.setType(temp.getType());
					newMessage.setFileName(temp.getFileName());
					newMessage.setData(temp.getData());
					messages.add(temp);					
					//System.out.println(temp.getMessage());
					/*System.out.println(temp.getFrom());
					System.out.println(temp.getTo());
					System.out.println(temp.getMessage());
					System.out.println(temp.getUniqueId());
					System.out.println(temp.getCounter());*/
					
					
					addMessage(newMessage);
				}else{
					/*System.out.println(temp.getFrom());
					System.out.println(temp.getTo());
					System.out.println(temp.getMessage());
					
					System.out.println(temp.getUniqueId());
					System.out.println(temp.getCounter());*/
					messages.add(temp);
				}
		
		}
	}
	
	public static int[] findNextRoute(int[] route){
		if(route.length==1){
			return null;
		}else{
			int length=route.length;
			int[] temp=new int[length-1];
			for(int i=1;i<length;i++){
				temp[i-1]=route[i];
			}
			return temp;
		}
	}
	
	public static ArrayList<Message> getFrom(int from){
		
		if(from==0)
			return messages;
		ArrayList<Message> temp=new ArrayList<Message>();
		Iterator<Message> itr=messages.iterator();
		while(itr.hasNext()){
			Message t=itr.next();
			if(from==(t.getFrom()))
				temp.add(t);
		}
		
		return temp;
	}
	
	public static ArrayList<Message> getTo(int to){
		
		synchronized(messages){
		
			ArrayList<Message> temp=new ArrayList<Message>();
			Iterator<Message> itr=messages.iterator();
			while(itr.hasNext()){
				Message t=itr.next();
				if(to==(t.getTo()))
					temp.add(t);
			}
			
			return temp;
		}
	}
	
	public static String[] getTolist(int input){
		String[] result=new String[nodesInNetwork.size()-1];
		//System.out.println(result.length);
		int counter=0;
		//System.out.println(input);
		for(int i=0;i<nodesInNetwork.size();i++){
			Node node=nodesInNetwork.get(i);
			if(! (input==(node.getNodeNo())) ){
				result[counter]=""+(nodesInNetwork.get(i)).getNodeNo();
				//System.out.println("adding");
				counter++;
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] gcs){
		createNetwork(10);
	
	}
	
	public static int[] getNodes(int current, int total,int finalNode){
		
		ArrayList<Node> temp=(ArrayList)(nodesInNetwork.clone());
		Collections.shuffle(temp);
		int[] result=new int[total+1];
		int test=0;
		int counter=0;
		for(int i=0;i<temp.size() && counter<total;i++){
			
			test=temp.get(i).getNodeNo();
			if(test!=current && test!=MAINSERVERNODE){
				result[counter]=test;
				counter++;
			}
		}
		
		result[total]=finalNode;
		return result;
	}
	
	public static Message[] messageToChunks(Message message,String hash){
		
		
		String input=message.getMessage();		
		int length=input.length();
		Message[] result=new Message[(int)(Math.ceil(length/15.0))];
		
		for(int i=0;i<result.length;i++){
			result[i]=new Message();
			result[i].setFrom(message.getFrom());
			result[i].setUniqueId(message.getUniqueId());
			result[i].setCounter(i+1);
			int end=15*(i+1);
			if(end<length)
				result[i].setMessage(input.substring(15*i, end));
			else
				result[i].setMessage(input.substring(15*i,length));
			
			result[i].setHash(hash);
			result[i].setMessage(getEncryptedData(result[i].getMessage()));
			result[i].setRoute(getNodes(message.getFrom(),3,MAINSERVERNODE));
			result[i].setType(message.getType());
			result[i].setFileName(message.getFileName());
			result[i].setData(result[i].getMessage().getBytes());
			for(int s:result[i].getRoute()){
				System.out.print("\t"+s);
			}
			
			System.out.println("messages "+result.length);
		}		
		return result;
	}
	
	public static Message[] messageToChunksBytes(Message message,String hash){
		
		
		byte[] input=message.getData();	
		
		int length=input.length;
		Message[] result=new Message[(int)(Math.ceil(length/1024.0))];
		
		for(int i=0;i<result.length;i++){
			result[i]=new Message();
			result[i].setFrom(message.getFrom());
			result[i].setUniqueId(message.getUniqueId());
			result[i].setCounter(i+1);
			int end=1024*(i+1);
			if(end<length){
				result[i].setData(getData(input,1024*i, end));
				//result[i].setMessage(input.substring((1024*i)/8, end/8));
			}
			else{
				result[i].setData(getData(input,1024*i,length));
			}
			
			result[i].setHash(hash);
			
			result[i].setRoute(getNodes(message.getFrom(),3,MAINSERVERNODE));
			result[i].setType(message.getType());
			result[i].setFileName(message.getFileName());
			String sssss=new String(message.data);
			if(sssss.length()>240){
				sssss=sssss.substring(0,240);
			}
			//result[i].setMessage();
			result[i].setMessage(getEncryptedData(sssss));
			for(int s:result[i].getRoute()){
				System.out.print("\t"+s);
			}
			
			System.out.println("messages "+result.length);
		}		
		return result;
	}
	
	private static byte[] getData(byte[] data,int start,int end){
		int length=end-start;
		byte[] result=new byte[length];
		int counter=0;
		for(int i=start;i<end;i++){
			result[counter]=data[i];
			counter++;
		}
		return result;
	}
	
	
	public static String getEncryptedData(String input){
		 System.out.println("Data Before Encryption :" + input);  
		  byte[] dataToEncrypt = input.getBytes();  
		  byte[] encryptedData = null;  
		  try {  
		   PublicKey pubKey = keyPair.getPublic();//readPublicKeyFromFile(PUBLIC_KEY_FILE);  
		   Cipher cipher = Cipher.getInstance("RSA");  
		   cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
		   encryptedData = cipher.doFinal(dataToEncrypt);  
		   //System.out.println("Encryted Data: " + encryptedData);  
		     
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }   
		    
		  //System.out.println("----------------ENCRYPTION COMPLETED------------");    
		  return asHex(encryptedData);//new String(encryptedData);  
	}
	
	public static byte[] getEncryptedBytes(byte[] dataToEncrypt){
		 //System.out.println("Data Before Encryption :" + input);  
		 // byte[] dataToEncrypt = input.getBytes();  
		  byte[] encryptedData = null;  
		  try {  
		   PublicKey pubKey = keyPair.getPublic();//readPublicKeyFromFile(PUBLIC_KEY_FILE);  
		   Cipher cipher = Cipher.getInstance("RSA");  
		   cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
		   encryptedData = cipher.doFinal(dataToEncrypt);  
		   //System.out.println("Encryted Data: " + encryptedData);  
		     
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }   
		    
		  //System.out.println("----------------ENCRYPTION COMPLETED------------");
		  return encryptedData;//new String(encryptedData);
		  //return asHex(encryptedData);//new String(encryptedData);  
	}
	
	
	public static String asHex(byte buf[]) {
	    StringBuffer strbuf = new StringBuffer(buf.length * 2);
	    int i;

	    for (i = 0; i < buf.length; i++) {
	        if (((int) buf[i] & 0xff) < 0x10) {
	            strbuf.append("0");
	        }

	        strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
	    }

	    return strbuf.toString();
	}
	
	public static byte[] getDecryptBytes(byte[] data) {  
		  //System.out.println("\n----------------DECRYPTION STARTED------------");  
		  byte[] descryptedData = null;  
		    
		  try {  
		   PrivateKey privateKey = keyPair.getPrivate();//readPrivateKeyFromFile(PRIVATE_KEY_FILE);  
		   Cipher cipher = Cipher.getInstance("RSA");  
		   cipher.init(Cipher.DECRYPT_MODE, privateKey);  
		   descryptedData = cipher.doFinal(data);//getBytes());  
		   //System.out.println("Decrypted Data: " + new String(descryptedData));  
		     
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }   
		    
		  return descryptedData;    
	}  
	
	
	public static String getDecryptData(String data) {  
		  //System.out.println("\n----------------DECRYPTION STARTED------------");  
		  byte[] descryptedData = null;  
		    
		  try {  
		   PrivateKey privateKey = keyPair.getPrivate();//readPrivateKeyFromFile(PRIVATE_KEY_FILE);  
		   Cipher cipher = Cipher.getInstance("RSA");  
		   cipher.init(Cipher.DECRYPT_MODE, privateKey);  
		   descryptedData = cipher.doFinal(fromHexString(data));//getBytes());  
		   //System.out.println("Decrypted Data: " + new String(descryptedData));  
		     
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }   
		    
		  return new String(descryptedData);//new String(descryptedData);    
	}  
	
	public static byte[] fromHexString(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static String getHash(String input,byte[] salt) {
		//byte[] salt=new byte[]{3,3,3,3,3,3,3,3};
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			byte[] bt=digest.digest(input.getBytes("UTF-8"));
			return new String(bt);
		}catch(Exception ex){
			return input;
		}
	 }
	
	public static void processFile(String fileName,String type,byte[] data){
		
		File f=new File(fileName+"."+type);
		if(f.exists())
			return;
		
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(data);
			fos.close();
		}catch(Exception ex){
			
		}
	}
	
	public static ArrayList<Message> processMessages(ArrayList<Message> input){
		
		HashMap<String, ArrayList<Message>> temp=new HashMap<String, ArrayList<Message>>();
		
		Iterator<Message> itr=input.iterator();
		
		Message temp2=null;
		
		while(itr.hasNext()){
			temp2=itr.next();
			String hash=temp2.getHash();
			ArrayList<Message> app=temp.get(hash);
			if(app==null){
				app=new ArrayList<Message>();
				app.add(temp2);
				temp.put(hash, app);
			}else{
				app.add(temp2);
			}
		}
		
		ArrayList<Message> result=new ArrayList<Message>();
		for(Map.Entry<String, ArrayList<Message>> entry:temp.entrySet()){
			result.add(clubMessages(entry.getValue()));
		}
		
		return result;
	}
	
	public static Message clubMessages(ArrayList<Message> inbox){
		
		Collections.sort(inbox);
		Message msg=null;//inbox.get(0);
		byte[] bytes=null;//msg.getData();
		String sss=null;//MainServer.getDecryptData(msg.getMessage());
		
		Iterator<Message> itr=inbox.iterator();

		Message rtttt=new Message();
		
		while(itr.hasNext()){
			if(msg==null){
				msg=((itr.next()));
				bytes=msg.getData();
				if(!msg.getType().equals("chat"))
					sss=msg.getMessage();
				else
					sss=MainServer.getDecryptData(msg.getMessage());
			
			}else{
				Message temp=itr.next();
				bytes=clubBytes(bytes, temp.getData());
				rtttt.setFrom(temp.getFrom());
				rtttt.setTo(temp.getTo());
				if(!msg.getType().equals("chat"))
					sss+=temp.getMessage();
				else
					sss+=MainServer.getDecryptData(temp.getMessage());
				//sss+=MainServer.getDecryptData(temp.getMessage());
			}
		}
		
		//if(!msg.getType().equals("chat")){
		
		rtttt.setFileName(msg.getFileName());
		rtttt.setType(msg.getType());		
		rtttt.setData(bytes);		
		rtttt.setHash(msg.getHash());
		rtttt.setMessage(sss);
			
			/*String fileName=msg.getFileName();
			File f=new File(fileName);
			if(!f.exists()){
				try{
					FileOutputStream fout=new FileOutputStream(fileName);
					fout.write(bytes);
					fout.close();
				}catch(Exception ex){
					
				}
			}
		}*/
		return rtttt;
	}
	
	public static byte[] clubBytes(byte[] input1,byte[] input2){
		int length=input1.length+input2.length;

		byte[] result=new byte[length];
		for(int i=0;i<input1.length;i++){
			result[i]=input1[i];
		}
		
		int counter=0;
		for(int i=input1.length;i<length;i++){
			result[i]=input2[counter];
			counter++;
		}
		
		return result;
	}
	
}
