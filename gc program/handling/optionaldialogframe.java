package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;




class buttonpanel extends JPanel
{
		ButtonGroup group;   
		
		public buttonpanel(String title,String... options)
		{
			System.out.println("constructor");
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			group=new ButtonGroup();
			
			for(String option:options)
			{
				JRadioButton b=new JRadioButton(option);
				b.setActionCommand(option);
				add(b);
				group.add(b);
				b.setSelected(option==options[0]);
			}
		}
		
		
		public String Selection()
		{
			return group.getSelection().getActionCommand();
		}
		
		public void pinni()
		{
			System.out.println("kkk");
		}
}	


public class optionaldialogframe extends JFrame
{

	JPanel typepanel,messagetypepanel,optiontypepanel,optionspanel,inputpanel,messagepanel;

	public optionaldialogframe()
	{
		setTitle("optional dialogs");
		
		JPanel gridpanel=new JPanel();
		gridpanel.setLayout(new GridLayout(2,3));
		
		typepanel=new buttonpanel("type","messege","confirm","option","input");
		messagetypepanel=new buttonpanel("message type","ERROR_MESSAGE","INFORMATION_MESSAGE","WARNING_MESSAGE","QUESTION_MESSAGE","INFORMATION_MESSAGE");
		messagepanel=new buttonpanel("message","string","icon","component","other","object[]");
		optiontypepanel=new buttonpanel("confirm","DEFAULT_OPTION","YES_NO_OPTION","YES_NO_CANCEL_OPTION","OK_CANCEL_OPTION");
				optionspanel=new buttonpanel("option","string[]","icon[]","object[]");
		inputpanel=new buttonpanel("input","textfield","combobox");
		inputpanel.pinni();
		
		gridpanel.add(typepanel);		
		gridpanel.add(messagetypepanel);
		gridpanel.add(messagepanel);
		gridpanel.add(optiontypepanel);
		gridpanel.add(optionspanel);
		gridpanel.add(inputpanel);										
		
		JPanel showpanel=new JPanel();
		JButton button=new JButton("show");
		
		showpanel.add(button);
		button.addActionListener(new showaction());
		add(gridpanel,BorderLayout.CENTER);
		add(showpanel,BorderLayout.SOUTH);
		inputpanel.pinni();
	}



	
	class showaction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		//	optiontypepanel.pinni();
		}
	}

}


