import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.sheetal.jcbir.gui.DatabaseConnection;


public class ImagesDisplay extends JPanel{
	
	JPanel bottomPanel;
	JPanel topPanel;
	JTextField tField;
	JPanel otherPanel=new JPanel();
	ArrayList<String> paths=new ArrayList<String>();
	
	public ImagesDisplay(){
		setLayout(new BorderLayout());
		
		topPanel=new JPanel();
		
		tField=new JTextField("Search keyword");
		topPanel.setLayout(new GridLayout(3,3));
		topPanel.add(new JLabel("   "));
		topPanel.add(new JLabel("   "));
		topPanel.add(new JLabel("   "));
		topPanel.add(new JLabel("   "));
		topPanel.add(tField);
		tField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				fillBottomPanel();
				//ImagesDisplay.this.revalidate();
			}
		});
		topPanel.add(new JLabel("   "));
		topPanel.add(new JLabel("   "));
		topPanel.add(new JLabel("   "));
		topPanel.add(new JLabel("   "));
		
		add(topPanel,BorderLayout.NORTH);
		bottomPanel=new JPanel();
		add(bottomPanel,BorderLayout.CENTER);
		
	}
	
	
	public void fillBottomPanel(){		
		
		bottomPanel.removeAll();
		//bottomPanel.setSize(600,400);
		bottomPanel.setBackground(Color.GRAY);
		
		paths=new ArrayList<String>();
		try{
			Connection connection=DatabaseConnection.getConnection("karna","root","");
			//String query="select *from Images where tags like ";
			String query="select *from Images where tags like %"+tField.getText()+"%";
			ResultSet rs=DatabaseConnection.execute(connection, query);
			/*String[] split=tField.getText().split(" ");
			for(int i=0;i<split.length;i++){
				query+="'%"+split[i]+"%' or";
			}
			query=query.substring(0,query.lastIndexOf("or"));
			System.out.println(query);
			ResultSet rs=DatabaseConnection.execute(connection, query);
			*/
			while(rs.next()){
				paths.add(rs.getString("path"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		int columnCount=6;
		
		int number=paths.size();
		int rows=number/columnCount+1;			
		
		JPanel otherPanel=new JPanel();		
		
		otherPanel.setLayout(new GridLayout(rows,columnCount,10,10));	
		
		Iterator iterator=paths.iterator();
		
		while(iterator.hasNext()){
			final String s=(String) iterator.next();
			ImageIcon image = new ImageIcon(s);
			Image newimg = image.getImage().getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH);
			image=new ImageIcon(newimg);
			JLabel thumg=new JLabel();
			thumg.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					copy(s);
					displayImage(s);
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			thumg.setIcon(image);
			otherPanel.add(thumg);
		}
		
		
		int remaining=columnCount-number%columnCount;
		System.out.println(remaining);
		
		while(remaining>0){
			JLabel ll=new JLabel("         ");
			otherPanel.add(ll);
			remaining--;
			//System.out.println("added thing");
		}
		
		JScrollPane scrollPane=new JScrollPane(otherPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setMinimumSize(new Dimension(600, 400));
	    scrollPane.setPreferredSize(new Dimension(600, 400));
		bottomPanel.add(scrollPane);
		bottomPanel.revalidate();
	}
	
	private void displayImage(String url){
		System.out.println(url);
		final DisplayImage s=new DisplayImage(url);
	}
	
	public static void copy(String text){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();

        clipboard.setContents(new StringSelection(text), null);
    }

}
