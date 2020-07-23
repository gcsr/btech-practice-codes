import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IndianRootFrame extends JFrame{
	JLabel entryLabel,mainLabel,auxillaryLabel,encoStartLabel,textLabel,punctuationLabel;
	JTextField passField,encoStartField;
	JTextArea mainWords,auxillaryWords,textArea,punctuationLetters;
	JButton mainWordsTest,auxillaryWordsTest,punctuationLettersTest,textAreaTest,decodeButton;
	public IndianRootFrame(){
		
		super("Indian Root");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,800);		
		setLayout(null);
		entryLabel=new JLabel("enter password");
		entryLabel.setBounds(20, 20,200,30);
		passField=new JTextField("enter password");
		passField.setBounds(230, 20, 280,30);
		add(entryLabel);
		
		passField.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ev){
				
				IndianRoot root=new IndianRoot(passField.getText().trim());
				encoStartField.setText(root.listChars());
				mainWords.setEditable(true);
			}
		});
		add(passField);
		
		encoStartLabel=new JLabel("start letters of main words");
		encoStartField=new JTextField("main words must start with these letters");
		encoStartLabel.setBounds(20,70,200,30);
		encoStartField.setBounds(230,70,500,30);
		encoStartField.setEditable(false);
		add(encoStartLabel);
		add(encoStartField);
		
		mainLabel=new JLabel("main words seperated with ,");
		auxillaryLabel=new JLabel("auxillary words seperated by ,");
		mainLabel.setBounds(20,120, 450,30);
		mainWordsTest=new JButton("test");
		mainWordsTest.setBounds(500, 120,100,25);
		add(mainLabel);
		
		
		add(mainWordsTest);
		mainWords=new JTextArea("enter words here",10,750);
		mainWords.setBounds(20,160, 750,100);
		mainWords.setEditable(false);
		
		add(mainWords);
		
		mainWordsTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String start=encoStartField.getText();
				
				String[] startLetters=start.split(",");
				String s=mainWords.getText();
				
				String[] group=s.split(",");
				
				
				if(group.length!=startLetters.length){
					JOptionPane.showMessageDialog(IndianRootFrame.this,"no of words and no of start letters should be equal");					
				}
				
				for(int i=0;i<group.length;i++){
					if(!group[i].startsWith(startLetters[i])){
						JOptionPane.showMessageDialog(IndianRootFrame.this,"word no "+(i+1)+"is not starting with letter "+(i+1));
					}
				}
				
				
				if(!WordDealer.isAuxillary(group))
					auxillaryWords.setEditable(true);
				else{
					JOptionPane.showMessageDialog(IndianRootFrame.this,"there are auxillary words in main words");					
					auxillaryWords.setEditable(false);
				}
			}
		});
		auxillaryLabel.setBounds(20,270, 750,30);
		add(auxillaryLabel);
		auxillaryWordsTest=new JButton("test");
		auxillaryWordsTest.setBounds(500, 270,100,25);
		add(auxillaryLabel);
		add(auxillaryWordsTest);
		auxillaryWordsTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){								
				
				String s=auxillaryWords.getText();				
				
				String[] group=s.split(",");							
				
				if(WordDealer.isAuxillary(group)){
					JOptionPane.showMessageDialog(IndianRootFrame.this,"auxillary words are correct");
					textArea.setEditable(true);
				}
				else{
					JOptionPane.showMessageDialog(IndianRootFrame.this,"some words are not auxillary");
					textArea.setEditable(false);
				}
			}
		});
		
		auxillaryWords=new JTextArea("enter auxillary words here",10,750);
		auxillaryWords.setBounds(20,320, 750,100);
		//auxillaryWords.setEditable(false);
		add(auxillaryWords);
		
		
		punctuationLabel=new JLabel("enter punctuation letters separated by space");
		punctuationLabel.setBounds(20,440,750,30);
		add(punctuationLabel);
		
		punctuationLetters=new JTextArea("enter punctuation letters here",10,750);
		punctuationLetters.setBounds(20,480, 750,100);
		//punctuationLetters.setEditable(false);
		add(punctuationLetters);
		
		punctuationLettersTest=new JButton("test");
		punctuationLettersTest.setBounds(500, 440,100,25);
		add(punctuationLettersTest);
		
		punctuationLettersTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String punc=punctuationLetters.getText();
				if(PunctuationDealer.checkNotPunctuationLetters(punc)){
					JOptionPane.showMessageDialog(IndianRootFrame.this,"enter correct punctuation marks");
					textArea.setEditable(false);
				}
				else{
					JOptionPane.showMessageDialog(IndianRootFrame.this,"correct punctuation marks");
					textArea.setEditable(true);
				}
			}
		});
		
		textLabel=new JLabel("enter text with main and auxillary words,punctuation letters");
		textLabel.setBounds(20,620,750,30);
		
		textArea=new JTextArea("enter text to be sent here ",10,750);
		textArea.setBounds(20,660,750,100);
		
		textAreaTest=new JButton("test");
		textAreaTest.setBounds(500, 620,100,25);
		decodeButton=new JButton("Decode");
		decodeButton.setBounds(700, 620,100,25);
		add(decodeButton);
		add(textAreaTest);
		
		
		textArea.setEditable(false);
		add(textLabel);
		add(textArea);
		setVisible(true);
		
		decodeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Decode decode=new Decode(textArea.getText());
				System.out.println(decode.process());
			}
		});
		textAreaTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String mainText=textArea.getText();
				System.out.println(mainText);
				String[] mainWordsArray=mainText.split(",");
				String[] auxillaryWordsArray=auxillaryWords.getText().split(",");
				String[] punctuationLettersArray=punctuationLetters.getText().split(" ");
				String[] sortedauxillaryWords=sort(auxillaryWordsArray);
				
				for(String s:sortedauxillaryWords){
					//System.out.println(s);
					//System.out.println(mainText.indexOf(s));
					mainText=mainText.replace(s,"");
				}
				
				for(String s:punctuationLettersArray){
					//System.out.println(s);
					//System.out.println(mainText.indexOf(s));
					mainText=mainText.replace(s," ");
				}
				
				//mainText.replaceAll(regex, replacement);
				System.out.println(mainText);
				mainText=mainText.replaceAll("\\s+"," ");
				System.out.println(mainText);
				
				textArea.setText(mainText);
				System.out.println(mainText);
			}
			
			private String[] sort(String[] input){
				
				int length=input.length;
				
				for(int i=0;i<length-1;i++){
					for(int j=i+1;j<length;j++){
						if(noOfSpaces(input[i])<noOfSpaces(input[j])){
							String temp=input[i];
							input[i]=input[j];
							input[j]=temp;
						}
					}
				}
				
				return input;
			}
			
			int noOfSpaces(String s){
				String[] sp=s.split(" ");
				return sp.length;
			}
			
			
		});
		
		
		//pack();
		
	}
	
	
	public static void main(String[] gcs){
		new IndianRootFrame();
	}

}
