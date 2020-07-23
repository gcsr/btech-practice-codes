import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainMenu extends JFrame implements WindowListener{
	String outputFileName="";
    JMenuItem f,b,u,i,r;
    JPanel cards;
    FileWriter fileWriter;
    public MainMenu(List<Book> books, List<User> users,String outputFileName){    	
    	super ( "Using JMenus and ArrayList" );
    	this.outputFileName=outputFileName;
    	try{
    		
    		fileWriter=new FileWriter(outputFileName);
    	}catch(Exception ex){
    		System.out.println("out file is not correct path");
    		System.exit(1);
    	}
    	//setLayout(new GridLayout(1,1));
    	JPanel usersPanel = new UsersTable(users);    	
    	JPanel  booksPanel= new BooksTable(books);
    	JPanel borrow=new BorrowBook(users,books,fileWriter);
    	JPanel returnBook=new ReturnBook(users,books);
       	cards = new JPanel(new CardLayout());
    	cards.add(usersPanel, "users");
    	cards.add(booksPanel, "books");
    	cards.add(borrow, "borrow");
    	cards.add(returnBook, "return");
    	JMenu fileMenu = new JMenu( "File" ); // create file menu    	
    	
    	f = new JMenuItem( "f" );       
    	f.setMnemonic( 'f' );
    	f.addActionListener(
    		new ActionListener(){
    			public void actionPerformed( ActionEvent event ){
    				try{
    					if(fileWriter!=null)
    						fileWriter.close();
    				}catch(Exception ex){
    					
    				}
    				System.exit(1);
                } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener
        
    	
    	b = new JMenuItem( "b" );       
    	b.setMnemonic( 'b' );        
    
    	
    	b.addActionListener(
    		new ActionListener(){
    			public void actionPerformed( ActionEvent event ){
    				 CardLayout cl = (CardLayout)(cards.getLayout());
    		    	 cl.show(cards, "books");
                } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener
    
    	
    	u = new JMenuItem( "u" );       
    	u.setMnemonic( 'u' );        
    
    	
    	u.addActionListener(
    		new ActionListener(){
    			public void actionPerformed( ActionEvent event ){
    				 CardLayout cl = (CardLayout)(cards.getLayout());
    		    	 cl.show(cards, "users");
                } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener
    
    	
    	
    	i = new JMenuItem( "i" );       
    	i.setMnemonic( 'i' );
    	
    	
    	
    	i.addActionListener(
    		new ActionListener(){
    			public void actionPerformed( ActionEvent event ){
    				CardLayout cl = (CardLayout)(cards.getLayout());
   		    	 	cl.show(cards, "borrow");
                } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener
    	
    	
    	
    	r = new JMenuItem( "r" );       
    	r.setMnemonic( 'r' );        
    
    	
    	r.addActionListener(
    		new ActionListener(){
    			public void actionPerformed( ActionEvent event ){
    				CardLayout cl = (CardLayout)(cards.getLayout());
   		    	 	cl.show(cards, "return");
                } // end method actionPerformed
           } // end anonymous inner class
        ); // end call to addActionListener
        
        
    
    
    	fileMenu.add(f);
    	fileMenu.add(b);
    	fileMenu.add(u);
    	fileMenu.add(i);
    	fileMenu.add(r);
    	
    	JMenuBar bar = new JMenuBar(); // create menu bar 
    	setJMenuBar( bar ); // add menu bar to application
    	bar.add( fileMenu ); // add file menu to menu bar
    	
    
    	add(cards);
    }
    
    public static void main(String[] gcs){
    	if(gcs.length!=2){
    		System.out.println("Usage:java MainMenu inputfilename outputfilename");
    		System.exit(1);
    	}
    	
    	GetUsersAndBooks ub=new GetUsersAndBooks(gcs[0]);
    	List<Book> b=ub.books;
    	List<User> u=ub.users;
    	MainMenu app=new MainMenu(b,u,gcs[1]);
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	app.setSize(500,400);
    	app.setVisible(true);
    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try{
			if(fileWriter!=null)
				fileWriter.close();
		}catch(Exception ex){
			
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		try{
			if(fileWriter!=null)
				fileWriter.close();
		}catch(Exception ex){
			
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
