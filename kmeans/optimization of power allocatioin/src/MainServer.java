import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainServer{
	static List<Node> nodesInNetwork=new ArrayList<Node>();
	static List<Message> messages=new ArrayList<Message>();
	static int[] sensingLosses;
	static int[] fusionLosses;
	
	public MainServer(){
		
	}
	
	public static void createNetwork(int no){
		sensingLosses=new int[no];
		fusionLosses=new int[no];
		for(int i=0;i<no;i++){
			Node node=new Node();
			node.setName("Node "+(i+1));
			node.setType("Participatory");
			sensingLosses[i]=getMainRandomDouble();
			fusionLosses[i]=getMainRandomDouble();
			node.setSensingLoss(sensingLosses[i]);
			node.setFusionLoss(fusionLosses[i]);
			node.setPort(19000+i);
			nodesInNetwork.add(node);
			
		}
		
		Node node=new Node();
		node.setName("Sensor Node");
		node.setType("sensor");
		node.setPort(20000);
		node.setFusionLoss(getMainRandomDouble());
		nodesInNetwork.add(node);
		//new NetworkNode(node);
		node=new Node();
		node.setName("Fusion Node");
		node.setType("fusion");
		node.setPort(20001);
		nodesInNetwork.add(node);
		//new NetworkNode(node);
		
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
			if(message.getTo().equals("Sensor Node")){
				Message mg=new Message();
				mg.setFrom("Sensor Node");
				mg.setTo("Fusion Node");
				mg.setMessage(message.getMessage()+" "+"fusion loss "+nodesInNetwork.get(nodesInNetwork.size()-2).getFusionLoss());
				messages.add(mg);
			}
			
			messages.add(message);
		}
	}
	
	public static List<Message> getFrom(String from){
		
		if(from==null || from.equals(""))
			return messages;
		List<Message> temp=new ArrayList<Message>();
		Iterator<Message> itr=messages.iterator();
		while(itr.hasNext()){
			Message t=itr.next();
			if(from.equals(t.getFrom()))
				temp.add(t);
		}
		
		return temp;
	}
	
	public static List<Message> getTo(String to){
		
		synchronized(messages){
		
			List<Message> temp=new ArrayList<Message>();
			Iterator<Message> itr=messages.iterator();
			while(itr.hasNext()){
				Message t=itr.next();
				if(to.equals(t.getTo()))
					temp.add(t);
			}
			
			return temp;
		}
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
	
	public static int getMainRandomDouble(){
		 Random generator = new Random();
		 int maximum=20;
		 int minimum=1;
		 int range = maximum - minimum + 1;
		 int randomNum =  generator.nextInt(range) + minimum;
		 return randomNum;
	}
	
	
	public static void main(String[] gcs){
		createNetwork(4);
		displayDivisons();
	}
	public static void displayDivisons(){
		
		int[] outputPowerRanges=new int[10];
		for(int i=0;i<10;i++){
			outputPowerRanges[i]=10000*(i+1);
		}
		
		int length=(nodesInNetwork.size()-2);
		
		int[] equals=new int[10];
		for(int i=0;i<equals.length;i++){
			equals[i]=outputPowerRanges[i]/length;
		}
		
		int[][] outputValues=new int[10][length];
		int[][] equalOutputValues=new int[10][length];
		
		for(int i=0;i<10;i++){			
			for(int j=0;j<length;j++){
				equalOutputValues[i][j]=equals[i]-(sensingLosses[j]*equals[i])/100-(fusionLosses[j]*equals[i])/100;
			}
		}
		
		for(int i=0;i<10;i++){
			
			for(int j=0;j<length;j++){
				outputValues[i][j]=equals[i]+(sensingLosses[j]*equals[i])/100+(fusionLosses[j]*equals[i])/100;
			}
		}
		
		int j=0;
		/*for(int x:outputValues[0]){
			System.out.print(x+"\t");
		}*/
		
		//System.out.println(outputPowerRanges[0]);
		
		for(int i=0;i<10;i++){	
			
			while( !sumEquals(outputValues[i],outputPowerRanges[i])){
				if(j==length)
					j=0;
				outputValues[i][j]--;
			}
			//System.out.println("hrer");
		}
		
		int[] minimumequals=new int[10];
		int[] minimumoutputs=new int[10];
		//System.out.println("hrer");
		for(int i=0;i<10;i++){
			minimumequals[i]=minimum(equalOutputValues[i]);
			minimumoutputs[i]=minimum(outputValues[i]);
		}
		//System.out.println("hrer");
		
		new DrawChart(minimumequals,minimumoutputs,outputPowerRanges).setVisible(true);;
		
		
	}
	
	public static boolean sumEquals(int[] input,int check){
		int length=input.length;
		double sum=0;
		for(double x:input)
			sum+=x;
		if(Math.abs(sum)==check)
			return  true;
		else return false;
	}
	
	public static int minimum(int[] input){
		int min=input[0];
		
		for(int i=0;i<input.length;i++){
			if(min >input[i])
				min=input[i];
		}
		
		//System.out.println(min);
		
		return min;
	}
	

	public static void doManipulations(){
		
	}
	
	public int findSum(){
		return 0;
	}
}
