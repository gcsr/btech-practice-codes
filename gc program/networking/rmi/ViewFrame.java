import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;  
import javax.swing.JTextField;  
import javax.swing.JFileChooser;  
import javax.swing.JButton;  
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.IOException;

public class ViewFrame extends JFrame {
	private JTextArea area;
	private DownPanel panel;
		File fileName;

	public ViewFrame(){
		setLayout(new BorderLayout());
		panel=new DownPanel();
		add(panel,BorderLayout.NORTH);
		area=new JTextArea("your file is shown here");
		add(area,BorderLayout.CENTER);
	}
		
		
  class DownPanel extends JPanel{
	private final JTextField field;
	private JButton button;
	JFileChooser fileChooser;
	FileInputStream fileInputStream;

	
	DownPanel()
	{
		setLayout(new GridLayout(1,2));
		field=new JTextField("select a file");
		button=new JButton("open"); 
		add(field,BorderLayout.NORTH);
		add(button,BorderLayout.NORTH);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fileChooser=new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result=fileChooser.showOpenDialog(DownPanel.this);

				if(result==JFileChooser.CANCEL_OPTION)
					return;

				fileName=fileChooser.getSelectedFile()	;
				field.setText(fileName.getAbsolutePath());
				
				showFile();
			}
				
		});
	
	
	}
	private void showFile()
	{
		try{
		fileInputStream=new FileInputStream(fileName);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if(fileInputStream==null)
			area.setText("file not found");
		else{
			try{
				BufferedInputStream bufferedStream=new BufferedInputStream(fileInputStream);
				int nextByte;
				area.setText("");
				StringBuffer localBuffer=new StringBuffer();
				while((nextByte=bufferedStream.read())!=-1)
				{
					char nextChar=(char)nextByte;
					localBuffer.append(nextChar);
				}	
					area.append(localBuffer.toString());
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}	
	}
  }
}
