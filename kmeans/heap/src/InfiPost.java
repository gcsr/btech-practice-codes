import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


/**
 * 
 * @author vamsi
 * this is the main class for user interface
 *
 */
public class InfiPost extends JFrame{
	
	/**
	 * this class user to evaluate postfix expression of numbers (not symbols)
	 */
	class EvalPostfix {

	    private String postfix;
	    private Stack stack;

	    /**
	     * constructor for postfix
	     * @param postfix string input
	     */
	    public EvalPostfix(String postfix) {
	        this.postfix = postfix;
	        stack = new Stack();
	    }

	    /**
	     * metho to return the string
	     * @return input
	     */
	    public String getPostfix() {
	        return postfix;
	    }

	    /**
	     * method to evaluate postfix
	     * @return postfix
	     */
	    public String evaluate() {
	        char[] characters = postfix.toCharArray();
	        for (int i = 0; i < characters.length; i++) {
	            if (Character.isAlphabetic(characters[i]))
	                return postfix;
	        }
	        for (int i = 0; i < characters.length; i++) {
	            char ch = characters[i];
	            if (isOperator(ch)) {
	                double num1 = Double.parseDouble(String.valueOf(stack.pop()));
	                double num2 = Double.parseDouble(String.valueOf(stack.pop()));
	                switch (ch) {
	                    case '+':
	                        stack.push(num2 + num1);
	                        break;
	                    case '*':
	                        stack.push(num2 * num1);
	                        break;
	                    case '-':
	                        stack.push(num2 - num1);
	                        break;
	                    case '/':
	                        stack.push(num2 / num1);
	                        break;
	                }
	            } else if (Character.isDigit(ch)) {
	                stack.push(ch);
	            }
	        }
	        if (!stack.isEmpty())
	            return "" + Double.parseDouble(String.valueOf(stack.pop()));
	        else
	            return "" + 0.0;
	    }
	    private boolean isOperator(char ch) {
	        return ch == '*' || ch == '/' || ch == '+' || ch == '-' || ch == '^';
	    }

	  
	}

	/**
	 * this class used to convert infix expression to postfix
	 */
	class InfixtoPostfix {

	    private String infix;
	    private Stack stack;

	    /**
	     * cnstructor for infifix
	     * @param infix input string for infifix
	     */
	    public InfixtoPostfix(String infix) {
	        this.infix = infix;
	        stack = new Stack();
	    }

	    /**
	     * returns infifix
	     * @param i
	     * @return
	     */
	    public String getInfix(int i) {
	        return infix.substring(0, i + 1);
	    }

	    /**
	     * returns priority of operator
	     * @param ch operator
	     * @return priority
	     */
	    public int priority(char ch) {
	        if (ch == '^')
	            return 3;
	        if (ch == '/' || ch == '*')
	            return 2;
	        if (ch == '+' || ch == '-')
	            return 1;
	        return 0;
	    }

	    /**
	     * return postfix of integer
	     * @param e integer input
	     * @return string
	     */
	    public String toPostfix(int e) {
	        String copy = infix;
	        int i, l = copy.length();
	        char ch;
	        String r = "";
	        for (i = 0; i <= e; i++) {
	            ch = copy.charAt(i);
	            if (ch == ' ')
	                continue;
	            if (Character.isDigit(ch) || Character.isAlphabetic(ch)) {
	                r += ch + " ";
	            } else if (ch == '(')
	                stack.push(ch);
	            else if (ch == ')') {
	                while (String.valueOf(stack.peek()).charAt(0) != '(') {
	                    r += stack.pop() + " ";
	                }
	                stack.pop();
	            } else {
	                while (!stack.isEmpty() && priority(ch) <= priority(String.valueOf(stack.peek()).charAt(0))) {
	                    r += stack.pop() + " ";
	                }
	                stack.push(ch);
	            }
	        }
	        //pop remain operations
	        while (!stack.isEmpty()) {
	            r += stack.pop() + " ";
	        }
	        return r;
	    }
	}
	
	JLabel topLabel=new JLabel(" Expression Please : ");
	JTextField topField=new JTextField("",40);
	JButton findButton=new JButton("       Enter       ");
	JButton nextButton=new JButton("       Steps       ");
	JLabel expressionValue=new JLabel("Expression Value");
	JTextField resultFied=new JTextField(" result is");
	String itemText="<centre><b><u>Item<br/><br/></u></b></centre>";
    private InfixtoPostfix inToPost;
    private EvalPostfix evalutePotfix;
    
    
    JLabel item=new JLabel("<html>"+itemText+"</html>");
	
	
	String infixText="<centre><b><u>Infix<br/><br/></u></b></centre>";
	JLabel infixLabel=new JLabel("<html>"+infixText+"</html>");
	
	
	String postfixText="<centre><b><u>Postfix<br/><br/></u></b></centre>";
	JLabel postfixLabel=new JLabel("<html>"+postfixText+"</html>");
	
	/**
	 * main method for starting execution
	 * @param gcs command line arguments
	 */
	public static void main(String[] gcs){
		InfiPost app=new InfiPost();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(600,400);
		app.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setVisible(true);
	}
	
	/**
	 * main class constructor
	 */
	public InfiPost(){
		super("Infix/Postfix");
		setLayout(new BorderLayout());
		add(new TopPanel(),BorderLayout.NORTH);
		add(new RightPanel(),BorderLayout.EAST);
		add(new MiddlePanel(),BorderLayout.CENTER);
	}
	
	/**
	 * display at top panel
	 * @author vamsi
	 *
	 */
	class TopPanel extends JPanel{
		public TopPanel(){
			Box box=Box.createHorizontalBox();
			box.add(topLabel);
			box.add(topField);
			add(box);
		}
	}
	
	/**
	 * display at the right panel
	 * @author vamsi
	 *
	 */
	class RightPanel extends JPanel{
		public RightPanel(){
			Box box=Box.createVerticalBox();
			box.add(findButton);
			findButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if (topField.getText().equals("") || topField.getText() == null) {
	                    JOptionPane.showMessageDialog(null, "Please Enter Expression");
	                } //otherwise
	                else {
	                    if (checkValid()) {
	                        //create object of input to postfix by this expression
	                        inToPost = new InfixtoPostfix(topField.getText());
	                        //disable txt and start button
	                        topField.setEnabled(false);
	                        findButton.setEnabled(false);
	                        //enable step,result and new expression buttons
	                        nextButton.setEnabled(true);	                        
	                    }
	                }
				}
			});
			box.add(Box.createVerticalStrut(10));
			box.add(nextButton);
			nextButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					int i;
	                for (i = 0; i < topField.getText().length(); i++) {
	                    //second we don't count any spaces
	                    while (topField.getText().charAt(i) == ' ') {
	                        i++; // skip all spaces
	                    }
	                    // do one step for infix to postfix conversion
	                    String infix = inToPost.getInfix(i); // current infix
	                    String postfix = inToPost.toPostfix(i); // current postfix
	                    char readCharacter = infix.charAt(i); // current character read
	                    itemText+=readCharacter+"<br/>";
	                    infixText+=infix+"<br/>";
	                    postfixText+=postfix+"<br/>";
	                    item.setText("<html>"+itemText+"</html>");
	                	infixLabel.setText("<html>"+infixText+"</html>");
	                	postfixLabel.setText("<html>"+postfixText+"</html>");
	                    
	                   
	                    i++;
	                }
	                nextButton.setEnabled(false);
	                evalutePotfix = new EvalPostfix(inToPost.toPostfix(i - 2)); // evaluate expreesion
	                resultFied.setText("Result is   " + evalutePotfix.evaluate());
	                topField.setEnabled(true);
	                findButton.setEnabled(true);
				}
			});
			box.add(Box.createVerticalStrut(20));
			box.add(expressionValue);
			box.add(Box.createVerticalStrut(10));
			box.add(resultFied);
			add(box);
		}
	}
	
	/**
	 * display in the middle
	 * @author vamsi
	 *
	 */
	class MiddlePanel extends JPanel{
		public MiddlePanel(){
			setLayout(new GridLayout(1,3));
			JScrollPane itemPane=new JScrollPane(item);
			itemPane.getVerticalScrollBar().setUnitIncrement(15);
			
			
			JScrollPane infixPane=new JScrollPane(infixLabel);
			infixPane.getVerticalScrollBar().setUnitIncrement(15);
			
			
			JScrollPane postfixPane=new JScrollPane(postfixLabel);
			postfixPane.getVerticalScrollBar().setUnitIncrement(15);
			
			add(itemPane);
			add(infixPane);
			add(postfixPane);
			
		}
	}
	
	 /**
     * check if expression is valid
     *
     * @return true if valid, otherwise false
     */
    private boolean checkValid() {
        String exp = topField.getText();
        char[] characters = exp.toCharArray();
        Stack s = new Stack();
        //check ( )
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '(')
                s.push(characters[i]);
            if (characters[i] == ')') {
                if (s.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Expressin is not valid");
                    return false;
                }
                s.pop();
            }
        }
        if (!s.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Expression is not valid");
            return false;
        }
        char first = characters[0];
        if (first == '+' || first == '-' || first == '*' || first == '/') {
            JOptionPane.showMessageDialog(null, "Expression is not valid");
            return false;
        }
        if (characters[characters.length - 1] == '+' || characters[characters.length - 1] == '-' || characters[characters.length - 1] == '*' || characters[characters.length - 1] == '/') {
            JOptionPane.showMessageDialog(null, "Expression is not valid");
            return false;
        }
        for (int i = 1; i < characters.length; i++) {
            if (characters[i] == ' ')
                continue;
            if (Character.isDigit(characters[i]) && Character.isDigit(first)) {
                JOptionPane.showMessageDialog(null, "Expression is not valid");
                return false;
            }
            if (Character.isAlphabetic(characters[i]) && Character.isAlphabetic(first)) {
                JOptionPane.showMessageDialog(null, "Expression is not valid");
                return false;
            }
            if ((characters[i] == '+' || characters[i] == '-' || characters[i] == '*' || characters[i] == '/')
                    && (first == '+' || first == '-' || first == '*' || first == '/')) {
                JOptionPane.showMessageDialog(null, "Expression is not valid");
                return false;
            }
            first = characters[i];
        }
        return true;
    }
}


