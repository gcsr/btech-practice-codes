import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ProbeServer extends JFrame{

	JLabel pathNoLabel,bWidthLabel;
	JTextField pathField,bWidthField;
	JButton submit;
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		new ProbeServer();
		
	}
	
	public ProbeServer(){
		super("ROUTER");
		//setSize(400,400);
		setLayout(new GridLayout(3,2));
		
		pathNoLabel=new JLabel("Path No");
		bWidthLabel=new JLabel("Bandwidth");
		
		pathField=new JTextField("");
		bWidthField=new JTextField("");
		submit=new JButton("submit");
		add(pathNoLabel);
		add(pathField);
		add(bWidthLabel);
		add(bWidthField);
		add(submit);
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Socket socket;
				try{
					socket=new Socket("127.0.0.1",40606);
					BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					String path="127.0.0.1 127.0.0.1 127.0.0.1 127.0.0.1 127.0.0.1 127.0.0.1 127.0.0.1 end127.0.0.1 size";
					bout.write(pathField.getText()+" "+path+bWidthField.getText()+" ");
					bout.close();
					socket.close();
				}catch(IOException ex)
				{
					ex.printStackTrace();
					System.exit(1);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}

}
