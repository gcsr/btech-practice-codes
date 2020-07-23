import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class MainClientGui extends JFrame implements WindowListener{
	JTabbedPane tabpane=null;
	JPanel createPanel;
	JPanel changesPanel;
	JPanel displayPanel;
	List<Record> tableRecords=null;
	ClientConnection connection;
	
	boolean editClicked=false;
	boolean tableClicked=false;
	Record record=null;
	public MainClientGui(){
			//addWindowListener(this);
		super("Network Client");
		String ip="";
		try {
			
			InetAddress IP=InetAddress.getLocalHost();
			
			ip=IP.getHostAddress();
			
			System.out.println("IP of my system is := "+ip);
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
		
		connection=new ClientConnection(ip,30000);
		
		refreshPage();
		addWindowListener(this);
	}
	private void refreshPage(){
		getContentPane().removeAll();
		tabpane=new JTabbedPane();	
		
		createPanel=new CreatePanel();
		createPanel.setBackground(Color.blue);
		if(!editClicked){
			changesPanel=new ChangesPanel();
		}else{
			changesPanel=new EditPanel(record);
		}
		changesPanel.setBackground(Color.blue);
		
		if(!tableClicked)
			displayPanel=new DisplayPanel();
		else
			displayPanel=new TableDisplay();
		displayPanel.setBackground(Color.blue);
		
		
		tabpane.addTab("Create",null,createPanel,"first panel");
		tabpane.addTab("Change",null,changesPanel,"second panel");
		tabpane.addTab("Display",null,displayPanel,"third panel");
		//tabpane.addTab("WrapUp",null,panel3,"third panel");
		add(tabpane);
		show();
	
	}
	public static void main(String[] gcs){
		MainClientGui gui=new MainClientGui();
	}
	
	
	class CreatePanel extends JPanel{
		JLabel idLabel,nodesLabel,hubsLabel,switchesLabel,topologyLabel,countryLabel,statusLabel;
		JTextField idField,nodesField,hubsField,switchesField,topologyField,countryField,statusField;
		
		JComboBox<String> topologyList=null;
		
		JButton submitButton;
		
		public CreatePanel(){
			setLayout(new GridLayout(9,2,20,4));
			
			idLabel=new JLabel();
			idLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Network Id</html>");
			
			nodesLabel=new JLabel();
			nodesLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numberof Nodes</html>");
			
			hubsLabel=new JLabel();
			hubsLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numberof Hubs</html>");
			
			switchesLabel=new JLabel();
			switchesLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Numberof Switches</html>");
			
			topologyLabel=new JLabel();
			topologyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Topology</html>");
			
			countryLabel=new JLabel();
			countryLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Country</html>");
			
			statusLabel=new JLabel();
			statusLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</html>");
			
			idField=new JTextField("",15);
			nodesField=new JTextField("",15);
			hubsField=new JTextField("",15);
			switchesField=new JTextField("",15);
			topologyField=new JTextField("",15);
			countryField=new JTextField("",15);
			statusField=new JTextField("",15);
			
			add(idLabel);
			add(idField);
			
			add(nodesLabel);
			add(nodesField);
			
			add(hubsLabel);
			add(hubsField);
			
			add(switchesLabel);
			add(switchesField);
			
			add(topologyLabel);
			String[] toplologies = new String[] {"Star", "Ring",
                    "Mesh", "Bus","Tree","Line"};

			topologyList = new JComboBox<>(toplologies);

			//add(topologyField);
			add(topologyList);
			
			
			add(countryLabel);
			add(countryField);
			
			
			add(statusLabel);
			add(statusField);
			submitButton=new JButton("Create");
			JLabel emptyLabel=new JLabel();
			emptyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel1=new JLabel();
			emptyLabel1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel2=new JLabel();
			emptyLabel2.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			
			add(emptyLabel);
			add(emptyLabel1);
			add(emptyLabel2);			
			add(submitButton);
			submitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					createRecord();
				}
			});
			
			
		}
		
		private void createRecord(){
			String error=checkError();
			if(error.equals("")){
				Record record=new Record();
				record.setId(Integer.parseInt(idField.getText()));
				record.setNodes(Integer.parseInt(nodesField.getText()));
				record.setHubs(Integer.parseInt(hubsField.getText()));
				record.setSwitches(Integer.parseInt(switchesField.getText()));
				record.setTopology((String)topologyList.getSelectedItem());
				record.setCountry(countryField.getText());
				record.setStatus(statusField.getText());
				String s=connection.insertRow(record);
				if(s.equals("success")){
					JOptionPane.showMessageDialog(new JFrame()," Record Created" , "Dialog",
					        JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Error Creating Record" , "Dialog",
					        JOptionPane.ERROR_MESSAGE);

				}
			}else{
				JOptionPane.showMessageDialog(new JFrame(),error , "Dialog",
				        JOptionPane.ERROR_MESSAGE);

			}
		}
		
		private String checkError(){
			String error="";
			error+=check(idField.getText(),"Id Field");
			error+=check(nodesField.getText(),"Nodes Field");
			error+=check(hubsField.getText(),"Hubs Field");
			error+=check(switchesField.getText(),"Switches Field");
			return error;
		}
		
		
		
		private String check(String iText,String temp){
			//String iText=idField.getText();
			if(iText==null || iText.equals("")){
				return "please fill "+temp+"\n";
			}else{
				try{
					int id=Integer.parseInt(iText);
					return "";
				}catch(Exception ex){
					return "please enter valid value for "+temp+"\n";
				}
			}
		}
	}
	
	class ChangesPanel extends JPanel{
		
		JLabel idLabel,nodesLabel,hubsLabel,switchesLabel,topologyLabel,countryLabel,statusLabel;
		JTextField idField,nodesField,hubsField,switchesField,topologyField,countryField,statusField;
		
		JComboBox<String> topologyList=null;
		
		
		JLabel idsLabel;
		JComboBox<String> idsList=null;
		JButton submitButton;
		List<Record> records=null;
		
		
		public ChangesPanel(){
			/*if(!editClicked){
				loder1();
				//getContentPane().removeAll();
				//add(new )
			}else{
				loder2();
			}*/
			loder1();
		}
		
		
		private void updatePanel(){
			//System.out.println("d"+idsList.getSelectedItem()+"d");
			int recordId=Integer.parseInt((String)(idsList.getSelectedItem()));
			Iterator<Record> itr=records.iterator();
			
			while(itr.hasNext()){
				Record temp=itr.next();
				if(temp.getId()==recordId)
					record= temp;
			}
			//changesPanel=new EditPanel(temp);
			editClicked=!editClicked;
			refreshPage();
			tabpane.setSelectedIndex(1);
			
		}
		
		private void loder1(){
			
			getContentPane().removeAll();
			setLayout(new GridLayout(9,2,20,4));
			idsLabel=new JLabel();
			idsLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select Id</html>");
			records=connection.getAllRecords();
		
		
			String[] ids = new String[records.size()];
			for(int i=0;i<records.size();i++){
				ids[i]=""+records.get(i).getId();
			}
			
			idsList = new JComboBox<>(ids);

			//	add(topologyField);
			add(idsList);
			submitButton=new JButton("Edit");
			JLabel emptyLabel=new JLabel();
			emptyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel1=new JLabel();
			emptyLabel1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel2=new JLabel();
			emptyLabel2.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			
			add(emptyLabel);
			add(emptyLabel1);
			add(emptyLabel2);			
			add(submitButton);
			
			submitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					updatePanel();
					
				}
			});
			show();

		}
		
		

	}
	
	class DisplayPanel extends JPanel{
		
		JLabel nodesLabel,topologyLabel;
		JTextField nodesField,topologyField;
		JComboBox<String> topologyList=null;
		JButton submitButton;
		public DisplayPanel(){
			setLayout(new GridLayout(9,2,20,4));
			nodesLabel=new JLabel();
			nodesLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numberof Nodes</html>");
		
			topologyLabel=new JLabel();
			topologyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Topology</html>");
			nodesField=new JTextField("",15);
			
			add(nodesLabel);
			add(nodesField);
			add(topologyLabel);
			
			String[] toplologies = new String[] {"Not Selected","Star", "Ring",
                    "Mesh", "Bus","Tree","Line"};

			topologyList = new JComboBox<>(toplologies);

			//add(topologyField);
			add(topologyList);
			submitButton=new JButton("Display");
			JLabel emptyLabel=new JLabel();
			emptyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel1=new JLabel();
			emptyLabel1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel2=new JLabel();
			emptyLabel2.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			
			add(emptyLabel);
			add(emptyLabel1);
			add(emptyLabel2);			
			add(submitButton);
			
			submitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					getRecords();
				}
			});
			
		}
		
		private void getRecords(){
			String noOfNodes=nodesField.getText();
			if(noOfNodes!=null && !noOfNodes.equals("")){
				try{
					int pp=Integer.parseInt(noOfNodes);
					tableRecords=connection.getRecordsByNode(pp);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(new JFrame(),"Please enter valid input for NO of Nodes" , "Dialog",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
			}else if(!topologyList.getSelectedItem().equals("Not Selected")){
				tableRecords=connection.getRecordsByTopology((String)topologyList.getSelectedItem());
			}else{
				tableRecords=connection.getAllRecords();
			}
			
			tableClicked=!tableClicked;
			refreshPage();
			tabpane.setSelectedIndex(2);
		}
	}
	
	
	class EditPanel extends JPanel{
		JLabel idLabel,nodesLabel,hubsLabel,switchesLabel,topologyLabel,countryLabel,statusLabel;
		JTextField idField,nodesField,hubsField,switchesField,topologyField,countryField,statusField;
		
		JComboBox<String> topologyList=null;
		
		JButton submitButton;
		
		public EditPanel(Record record){
			setLayout(new GridLayout(10,2,20,4));
			
			idLabel=new JLabel();
			idLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Network Id</html>");
			
			nodesLabel=new JLabel();
			nodesLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numberof Nodes</html>");
			
			hubsLabel=new JLabel();
			hubsLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numberof Hubs</html>");
			
			switchesLabel=new JLabel();
			switchesLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Numberof Switches</html>");
			
			topologyLabel=new JLabel();
			topologyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Topology</html>");
			
			countryLabel=new JLabel();
			countryLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Country</html>");
			
			statusLabel=new JLabel();
			statusLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</html>");
			
			idField=new JTextField("",15);
			nodesField=new JTextField("",15);
			hubsField=new JTextField("",15);
			switchesField=new JTextField("",15);
			topologyField=new JTextField("",15);
			countryField=new JTextField("",15);
			statusField=new JTextField("",15);
			
			add(idLabel);
			add(idField);
			idField.setText(""+record.getId());
			idField.setEditable(false);
			
			add(nodesLabel);
			add(nodesField);
			nodesField.setText(""+record.getNodes());
			
			add(hubsLabel);
			add(hubsField);
			
			hubsField.setText(""+record.getHubs());
			
			
			add(switchesLabel);
			add(switchesField);
			
			switchesField.setText(""+record.getSwitches());
			
			add(topologyLabel);
			String[] toplologies = new String[] {"Star", "Ring",
                    "Mesh", "Bus","Tree","Line"};

			topologyList = new JComboBox<>(toplologies);

			//add(topologyField);
			add(topologyList);
			topologyList.setSelectedItem(record.getTopology());
			
			add(countryLabel);
			add(countryField);
			countryField.setText(""+record.getCountry());
			
			
			add(statusLabel);
			add(statusField);
			statusField.setText(""+record.getStatus());
			
			submitButton=new JButton("Update");
			JLabel emptyLabel=new JLabel();
			emptyLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel1=new JLabel();
			emptyLabel1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel2=new JLabel();
			emptyLabel2.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JLabel emptyLabel3=new JLabel();
			emptyLabel3.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
			
			JButton deleteButton=new JButton("Delete");
			add(emptyLabel);
			add(emptyLabel1);
			add(deleteButton);			
			add(submitButton);
			add(emptyLabel3);
			JButton backButton=new JButton("back");
			add(backButton);
			submitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					updateRecord();
				}
			});
			
			deleteButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					deleteRecord();
				}
			});
			
			backButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					goBack();
				}
			});
		}
		
		private void goBack(){
			editClicked=!editClicked;
			refreshPage();
			tabpane.setSelectedIndex(1);
			
			
		}
		
		private void updateRecord(){
			String error=checkError();
			if(error.equals("")){
				Record record=new Record();
				record.setId(Integer.parseInt(idField.getText()));
				record.setNodes(Integer.parseInt(nodesField.getText()));
				record.setHubs(Integer.parseInt(hubsField.getText()));
				record.setSwitches(Integer.parseInt(switchesField.getText()));
				record.setTopology((String)topologyList.getSelectedItem());
				record.setCountry(countryField.getText());
				record.setStatus(statusField.getText());
				String s=connection.updateRow(record.getId(), record.getNodes(),record.getHubs(), record.getSwitches(), record.getTopology(), record.getCountry(),record.getStatus());
				if(s.equals("success")){
					JOptionPane.showMessageDialog(new JFrame()," Record Updated" , "Dialog",
					        JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Error Updating Record" , "Dialog",
					        JOptionPane.ERROR_MESSAGE);

				}
			}else{
				JOptionPane.showMessageDialog(new JFrame(),error , "Dialog",
				        JOptionPane.ERROR_MESSAGE);

			}
			editClicked=!editClicked;
			refreshPage();
			tabpane.setSelectedIndex(1);
			
		}
		
		private void deleteRecord(){
			Record record=new Record();
			record.setId(Integer.parseInt(idField.getText()));
			String s=connection.deleteRow(record);
			if(s.equals("success")){
				JOptionPane.showMessageDialog(new JFrame()," Record Deleted" , "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"Error Deleting Record" , "Dialog",
				        JOptionPane.ERROR_MESSAGE);

			}
			editClicked=!editClicked;
			refreshPage();
			tabpane.setSelectedIndex(1);
		}
		
		private String checkError(){
			String error="";
			error+=check(idField.getText(),"Id Field");
			error+=check(nodesField.getText(),"Nodes Field");
			error+=check(hubsField.getText(),"Hubs Field");
			error+=check(switchesField.getText(),"Switches Field");
			return error;
		}
		
		
		
		private String check(String iText,String temp){
			//String iText=idField.getText();
			if(iText==null || iText.equals("")){
				return "please fill "+temp+"\n";
			}else{
				try{
					int id=Integer.parseInt(iText);
					return "";
				}catch(Exception ex){
					return "please enter valid value for "+temp+"\n";
				}
			}
		}

			

	}
	
	class TableDisplay extends JPanel{
		public TableDisplay(){
			
			setLayout(new BorderLayout());
			String[] columns = new String[] {
		            "Id", "Nodes", "Hubs", "Switches","Topology","Country","Status"
		        };
			
			Object[][] data=new Object[tableRecords.size()][7];
			Iterator<Record> itr=tableRecords.iterator();
			int counter=0;
			
			JButton printButton=new JButton("print");
			while(itr.hasNext()){
				Record record=itr.next();
				data[counter][0]=record.getId();
				data[counter][1]=record.getNodes();
				data[counter][2]=record.getHubs();
				data[counter][3]=record.getSwitches();				
				data[counter][4]=record.getTopology();
				data[counter][5]=record.getCountry();
				data[counter][6]=record.getStatus();
				counter++;
			}
			
			
		         
		       /* //actual data for the table in a 2d array
		        Object[][] data = new Object[][] {
		            {1, "John", 40.0, false },
		            {2, "Rambo", 70.0, false },
		            {3, "Zorro", 60.0, true },
		        };*/
		 
		        //create table with data
		       final JTable table = new JTable(data, columns);
		        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		        JScrollPane scrollPane=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		       /* scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		        //add the table to the frame
		        scrollPane.add(table);
		        //this.add(new JScrollPane(table));
		         */
		        
		        this.add(scrollPane,BorderLayout.CENTER);
		        JButton submitButton=new JButton("back");
		        submitButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						goBack();
					}
				});
		        add(submitButton,BorderLayout.SOUTH);
		        add(printButton,BorderLayout.NORTH);
		        printButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						try{
							boolean pt=table.print();
							if(pt){
								JOptionPane.showMessageDialog(new JFrame()," Printing" , "Dialog",
							        JOptionPane.INFORMATION_MESSAGE);
							}else{	
								JOptionPane.showMessageDialog(new JFrame(),"Error Printing" , "Dialog",
							        JOptionPane.INFORMATION_MESSAGE);
							}
						}catch(Exception ex){
							
						}
					}
				});
		        show();
		}
		private void goBack(){
			tableClicked=!tableClicked;
			refreshPage();
			tabpane.setSelectedIndex(2);
			
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		connection.close();
		
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		connection.close();
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
