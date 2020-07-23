import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.IOException;

public class SimpleClientFrame extends JFrame
{
	private JTextArea essageArea;
	private JButton chooseFileButton;
	private JButton printFileButton;
	private JFileChooser fileChooser;
	
	public SimpleClientFrame()
	{
		buildGUI();
	}
	private void buildGUI()
	{
		JPanel ainPanel=new JPanel(new BorderLayout());
		
		essageArea=new JTextArea();
		ainPanel.add(new JScrollPane(essageArea),BorderLayout.CENTER);
		createButtons();
		JPanel buttonHolder=new JPanel();
		buttonHolder.setLayout(new GridLayout(1,2));
		buttonHolder.add(chooseFileButton);
		buttonHolder.add(printFileButton);
		ainPanel.add(buttonHolder,BorderLayout.SOUTH);
		getContentPane().add(ainPanel);
	
		
	}
	private void createButtons()
	{
		chooseFileButton=new JButton("choose");
		chooseFileButton.addActionListener(new FindFile());
		printFileButton=new JButton("print");
		printFileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try{
					ClientNetworkWrapper clientNetworkWrapper=new ClientNetworkWrapper();
					FileInputStream fileInputStream=new FileInputStream(fileChooser.getSelectedFile());
					clientNetworkWrapper.sendDocuentToPrinter(fileInputStream);
					
				}
				catch(IOException ex)
				{
					essageArea.setText("exception printing"+fileChooser.getSelectedFile().getAbsolutePath()+"and the error was"+ex.toString());
				}
			}
		});
	}
	private class FindFile implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(fileChooser==null)
			{
				fileChooser=new JFileChooser();
				if(JFileChooser.APPROVE_OPTION==fileChooser.showOpenDialog(SimpleClientFrame.this))
					essageArea.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}
}