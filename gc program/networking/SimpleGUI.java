import javax.swing.*;
import java.awt.*;



public class SimpleGUI extends JFrame
{
	private JTextField searchString = new JTextField(30);

  private JTextArea names = new JTextArea(15, 80);

  private JButton findButton = new JButton("Find");;

  private ButtonGroup searchIn = new ButtonGroup( );

  private ButtonGroup searchFor = new ButtonGroup( );

  private JCheckBox exactMatch = new JCheckBox("Exact Match", true);

  private JTextField chosenServer = new JTextField( );

  private Whois server;
	public static void main(String[] gcs)
	{
		new SimpleGUI();
	}
	
	public SimpleGUI()
	{
		
		super("Whois");

	      

	    Container pane = this.getContentPane( );

    

    	Font f = new Font("Monospaced", Font.PLAIN, 12);

    	names.setFont(f);

    	names.setEditable(false);
    	JPanel centerPanel = new JPanel( );

    	centerPanel.setLayout(new GridLayout(1, 1, 10, 10));

    	JScrollPane jsp = new JScrollPane(names);

    	centerPanel.add(jsp);

    	pane.add("Center", centerPanel);   

    

		 JPanel northPanel = new JPanel( );

    	JPanel northPanelTop = new JPanel( );

    	northPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));

    	northPanelTop.add(new JLabel("Whois: "));

    	northPanelTop.add("North", searchString);

    	northPanelTop.add(exactMatch);

    	northPanelTop.add(findButton);

    	northPanel.setLayout(new BorderLayout(2,1));

    	northPanel.add("North", northPanelTop);

    	JPanel northPanelBottom = new JPanel( );

    	northPanelBottom.setLayout(new GridLayout(1,3,5,5));
    	pane.add("North", northPanel);

    	northPanelBottom.add(initRecordType( ));

    	northPanelBottom.add(initSearchFields( ));

    	northPanelBottom.add(initServerChoice( ));

    	northPanel.add("Center", northPanelBottom);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setVisible(true);
		
	}
	private JPanel initRecordType( ) {

  

    JPanel p = new JPanel( );

    p.setLayout(new GridLayout(6, 2, 5, 2));

    p.add(new JLabel("Search for:"));

    p.add(new JLabel(""));

    

    JRadioButton any = new JRadioButton("Any", true);

    any.setActionCommand("Any");

    searchFor.add(any);

    p.add(any);



    p.add(this.makeRadioButton("Network"));

    p.add(this.makeRadioButton("Person"));

    p.add(this.makeRadioButton("Host"));

    p.add(this.makeRadioButton("Domain"));

    p.add(this.makeRadioButton("Organization"));

    p.add(this.makeRadioButton("Group"));

    p.add(this.makeRadioButton("Gateway"));

    p.add(this.makeRadioButton("ASN"));



    return p;

  

  }



  private JRadioButton makeRadioButton(String label) {

    

    JRadioButton button = new JRadioButton(label, false);

    button.setActionCommand(label);

    searchFor.add(button);

    return button;

    

  }
  private JRadioButton makeSearchInRadioButton(String label) {

    

    JRadioButton button = new JRadioButton(label, false);

    button.setActionCommand(label);

    searchIn.add(button);

    return button;

    

  }



  private JPanel initSearchFields( ) {

  

    JPanel p = new JPanel( );

    p.setLayout(new GridLayout(6, 1, 5, 2));

    p.add(new JLabel("Search In: "));



    JRadioButton all = new JRadioButton("All", true);

    all.setActionCommand("All");

    searchIn.add(all);

    p.add(all);



    p.add(this.makeSearchInRadioButton("Name"));

    p.add(this.makeSearchInRadioButton("Mailbox"));

    p.add(this.makeSearchInRadioButton("Handle"));



    return p;

  

  }
  private JPanel initServerChoice( ) {

  

    final JPanel p = new JPanel( );

    p.setLayout(new GridLayout(6, 1, 5, 2));

    p.add(new JLabel("Search At: "));



    //chosenServer.setText(server.getHost( ).getHostName( ));

    p.add(chosenServer);

    /*chosenServer.addActionListener( new ActionListener( ) {

      public void actionPerformed(ActionEvent evt) { 

        try {

          InetAddress newHost 

           = InetAddress.getByName(chosenServer.getText( ));

          Whois newServer = new Whois(newHost);

          server = newServer;  

        }

        catch (Exception ex) {

           JOptionPane.showMessageDialog(p, 

             ex.getMessage( ), "Alert", JOptionPane.ERROR_MESSAGE);

        }

      }

    } );*/

    

    return p;

  

  }



}