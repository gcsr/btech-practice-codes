import java.awt.GridLayout;


import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Server{
	static ArrayList<Node> nodesInNetwork=new ArrayList<Node>();
	static ArrayList<Message> messages=new ArrayList<Message>();
	static final int NODESCOUNT=1000;
	
	
	public Server(){
		
	}
	
	public static void configureNetwork(){
		for(int i=0;i<NODESCOUNT;i++){
			Node node=new Node();
			node.setName("Node "+(i+1));
			node.setBandwidth(1000);
			if(i<2500){
				node.setBaseStation1(20000);
				node.setBaseStation2(20001);
			}else if(i<5000){
				node.setBaseStation1(20001);
				node.setBaseStation2(20000);
			}else if(i<7500){
				node.setBaseStation1(20002);
				node.setBaseStation2(20003);
			}else{
				node.setBaseStation1(20003);
				node.setBaseStation2(20002);
			}
			
			node.setLimitMessages(100);
			node.setCleaningTime(2000);
			node.setPort(19000+i);
			
			node.setNodeNo(i);
			nodesInNetwork.add(node);
			
		}
		
		Node node=new Node();
		node.setName("BaseStation1");
		node.setNodeNo(20000);
		node.setPort(20000);
		node.setBandwidth(10000);
		node.setLimitMessages(1000);
		node.setCleaningTime(20000);
		nodesInNetwork.add(node);
		new BaseStation(node);
		node=new Node();
		node.setName("BaseStation2");
		node.setNodeNo(20001);
		node.setPort(20001);
		node.setBandwidth(10000);
		node.setLimitMessages(1000);
		node.setCleaningTime(20000);
		nodesInNetwork.add(node);
		//new NetworkNode(node);
		new BaseStation(node);
		node=new Node();
		node.setName("BaseStation3");
		node.setNodeNo(20002);
		node.setPort(20002);
		node.setBandwidth(10000);
		node.setLimitMessages(1000);
		node.setCleaningTime(20000);
		nodesInNetwork.add(node);
		new BaseStation(node);
		node=new Node();
		node.setName("BaseStation4");
		node.setNodeNo(20003);
		node.setPort(20003);
		node.setBandwidth(10000);
		node.setLimitMessages(1000);
		node.setCleaningTime(20000);
		nodesInNetwork.add(node);
		new BaseStation(node);
	
		
		
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
	
	
	public static String[] getTolist(String input){
		String[] result=new String[nodesInNetwork.size()-1];
		//System.out.println(result.length);
		int counter=0;
		//System.out.println(input);
		for(int i=0;i<nodesInNetwork.size();i++){
			Node node=nodesInNetwork.get(i);
			if(! (input.equals(node.getName())) ){
				result[counter]=(nodesInNetwork.get(i)).getName();
				//System.out.println("adding");
				counter++;
			}
		}
		
		return result;
	}
	
	
	public static void addMessage(Message message){
		
		
		
		synchronized(messages){
			Message temp=message;//(Message)(message.clone());
			//System.out.println(message);
			int[] route=message.getRoute();		
				temp.setTo(route[0]);
				nodesInNetwork.get(route[0]).messageQueue++;
				//System.out.println(nodesInNetwork.get(route[0]).messageQueue);
				if(nodesInNetwork.get(route[0]).messageQueue>=2000){
					nodesInNetwork.get(route[0]).messageQueue=0;
					try{
						//System.out.println("sleepiing");
						Thread.currentThread().sleep(50);
					}catch(Exception ex){
						
					}
				}
					
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
	

	public static void addMessageBaseStation(Message message){
		
		
		
		synchronized(messages){
			Message temp=message;//(Message)(message.clone());
			//System.out.println(message);
			int[] route=message.getRoute();
			
				int no=route[0];
				temp.setTo(no);
				nodesInNetwork.get(no-19000).messageQueue++;
				if(nodesInNetwork.get(no-19000).messageQueue>=50000){
					nodesInNetwork.get(no-19000).messageQueue=0;
					try{
						Thread.currentThread().sleep(50);
					}catch(Exception ex){
						
					}
				}
					
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
					messages.add(temp);					
					//System.out.println(temp.getMessage());
					/*System.out.println(temp.getFrom());
					System.out.println(temp.getTo());
					System.out.println(temp.getMessage());
					System.out.println(temp.getUniqueId());
					System.out.println(temp.getCounter());*/
					
					
					addMessageBaseStation(newMessage);
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
	
	public static int[] getNodes(int current,int finalNode){
		
		int total=getRandom(5,10);
		
		ArrayList<Node> temp=(ArrayList)(nodesInNetwork.clone());
		Collections.shuffle(temp);
		int[] result=new int[total+1];
		int test=0;
		int counter=0;
		for(int i=0;i<temp.size() && counter<total;i++){
			
			test=temp.get(i).getNodeNo();
			if(test!=current && test!=finalNode && test<2000){
				result[counter]=test;
				counter++;
			}
		}
		
		result[total]=finalNode;
		return result;
	}
	
	
	public static void main(String[] gcs){
		configureNetwork();
		
	}
	
	public static int getRandom(int low,int high){
		Random r = new Random();
		//high++;
		int result = r.nextInt(1+high-low) + low;
		return result;
	}
	
	static Message[] sendingMessages;
	
	public static void startMessagingNormal(int messageNo){
		sendingMessages=new Message[messageNo];
		int checker;
		int from;
		int to;
		
		for(int i=0;i<messageNo;i++){
			checker=getRandom(1,2);
			if(checker==1){
				from=getRandom(1,2);
				to=getRandom(3,4);
			}else{
				from=getRandom(3,4);
				to=getRandom(1,2);
			}
		
			if(from==1){
				from=getRandom(1,250);
			}else if(from==2){
				from=getRandom(251,500);
			}else if(from==3){
				from=getRandom(501,750);
			}else{
				from=getRandom(751,1000);
			}
			
			if(to==1){
				to=getRandom(1,250);
			}else if(to==2){
				to=getRandom(251,500);
			}else if(to==3){
				to=getRandom(501,750);
			}else{
				to=getRandom(751,1000);
			}
			Message msg=new Message();
			int route[]=getNodes(from,to);
			//String sss=getPaths(route);
			//System.out.println(" "+from+" "+to);
			msg.setFrom(from);
			msg.setTo(to);
			msg.setRoute(route);
			msg.setMessage("");	
			addMessage(msg);
		}

	}
	
	private static String getPaths(int[] route){
		String result="";	
			for(int i:route)
				result+=i+" ";
		
			return result;
	}
	
	
	public static void startMessagingHybrid(int messageNo){
		sendingMessages=new Message[messageNo];
		int checker;
		int from;
		int to;
		
		for(int i=0;i<messageNo;i++){
			checker=getRandom(1,2);
			if(checker==1){
				from=getRandom(1,2);
				to=getRandom(3,4);
			}else{
				from=getRandom(3,4);
				to=getRandom(1,2);
			}
		
			if(from==1){
				from=getRandom(1,250);
			}else if(from==2){
				from=getRandom(251,500);
			}else if(from==3){
				from=getRandom(501,750);
			}else{
				from=getRandom(751,1000);
			}
			
			if(to==1){
				to=getRandom(1,250);
			}else if(to==2){
				to=getRandom(251,500);
			}else if(to==3){
				to=getRandom(501,750);
			}else{
				to=getRandom(751,1000);
			}
			Message msg=new Message();
		
			msg.setFrom(from);
			int[] route=new int[2];
			if(from<2500){
				route[0]=getRandom(20000,20001);
				route[1]=getRandom(20002,20003);
				//route[2]=to;				
			}else if(i<5000){
				route[0]=getRandom(20000,20001);
				route[1]=getRandom(20002,20003);
				//route[2]=to;			
			}else if(i<7500){
				route[1]=getRandom(20000,20001);
				route[0]=getRandom(20002,20003);
				//route[2]=to;			
			}else{
				route[1]=getRandom(20000,20001);
				route[0]=getRandom(20002,20003);
				//route[2]=to;			
			}
		
			//String sss=getPaths(route);
			//System.out.println(sss);
			
			msg.setRoute(route);
			msg.setMessage("");	
			addMessageBaseStation(msg);
		}
	}
	
}
