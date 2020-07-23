import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class ReturnBook extends JPanel{	
	
	JList uusers;
	JList bbooks;
	private GridBagLayout layout; // layout of this frame
	private GridBagConstraints constraints; // constraint
	
	public ReturnBook(List<User> users,List<Book> books){
		layout = new GridBagLayout();                                     
		setLayout( layout ); // set frame layout                          
		constraints = new GridBagConstraints(); // instantiate constraints
		System.out.println("Borrowing Books");
		
		
		constraints.fill = GridBagConstraints.NONE;
        constraints.weightx=0;        
        constraints.weighty=0.5;
        constraints.gridx = 0; // set gridx                           
        constraints.gridy = 0; // set gridy                              
        constraints.gridwidth =1; // set gridwidth                    
        constraints.gridheight =1; // set gridheight                 
        add(new JLabel("Select User"),constraints);
	
		bbooks = new JList(new BookListModel(books)); // create with colorNames
		bbooks.setToolTipText("Select User");
		bbooks.setVisibleRowCount( 2 ); // display five rows at once
		bbooks.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		uusers = new JList(new UserListModel(users)); // create with colorNames
		uusers.setToolTipText("Select Book");
		uusers.setVisibleRowCount( 2 ); // display five rows at once
		uusers.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		
		constraints.gridx = 1; // set gridx                           
	    constraints.gridy = 0; // set gridy                              
	    constraints.gridwidth =1; // set gridwidth                    
	    constraints.gridheight =1; // set gridheight
	    add( new JScrollPane(uusers) ,constraints);
	    constraints.gridx = 0; // set gridx                           
	    constraints.gridy = 1; // set gridy                              
	    constraints.gridwidth =1; // set gridwidth                    
	    constraints.gridheight =1; // set gridheight	    
	    add(new JLabel("Select Book"),constraints);
	    constraints.gridx = 1; // set gridx                           
	    constraints.gridy = 1; // set gridy                              
	    constraints.gridwidth =1; // set gridwidth                    
	    constraints.gridheight =1; // set gridheight
		
		add( new JScrollPane(bbooks),constraints);
		
		constraints.gridx = 0; // set gridx                           
	    constraints.gridy = 2; // set gridy                              
	    constraints.gridwidth =2; // set gridwidth                    
	    constraints.gridheight =1; // set gridheight
		
		JButton button=new JButton("return");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Book bk=(Book)(bbooks.getSelectedValue());
				User ur=(User)(uusers.getSelectedValue());
				returnBook(bk,ur);
			}
		});
		
		add(button,constraints);
	}
	
	private void returnBook(Book bk,User ur){
		if(bk.getBorrower()==ur){
			try{
				ur.removeBook(bk);
				bk.setBorrower(null);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		else{
			System.out.println(ur.toString()+" has not borrowed " +bk.toString()+ " book");
		}
	}
	
	

}
