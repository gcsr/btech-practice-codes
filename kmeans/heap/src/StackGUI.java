import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class StackGUI extends JFrame {

    private JPanel pnl_inputArea;
    private JPanel pnl_outputArea;
    private JPanel pnl_resultArea;
    private JTextField txt_input;
    private JTextArea txt_output;
    private JLabel lbl_input;
    private JLabel lbl_result;
    private JButton btn_start;
    private JButton btn_step;
    private JButton btn_newExpression;
    private EventHandler EH;
    private InfixtoPostfix inToPost;
    private EvalPostfix evalutePotfix;
    private final String outputHeader = String.format("%s\t%s\t\t\t%s\n",
            "Item Read", "Infix", "Postfix")
            + "---------------------------------------------------------------"
            + "----------------------------------------------------------\n";

    public StackGUI() {
        setTitle("Stack Graphic Program");
        setSize(620, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        EH = new EventHandler();
        addComponents();
    }

    private void addComponents() {
        /*
         * input area panel and components
         */
        pnl_inputArea = new JPanel();
        pnl_inputArea.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));// flow layout as element after another
        //create label for input expression
        lbl_input = new JLabel("Enter expression:  ");
        pnl_inputArea.add(lbl_input);
        //add text for expression
        txt_input = new JTextField(20);
        pnl_inputArea.add(txt_input);
        //add start button
        btn_start = new JButton("Start");
        btn_start.addActionListener(EH); // create action for button click
        pnl_inputArea.add(btn_start);
        //add step button
        btn_step = new JButton("Step");
        btn_step.addActionListener(EH); // create action for button click
        btn_step.setEnabled(false); // disable button
        pnl_inputArea.add(btn_step);
        //add new expression button
        btn_newExpression = new JButton("New Expression");
        btn_newExpression.addActionListener(EH); // create action for button click
        btn_newExpression.setEnabled(false); // disable button
        pnl_inputArea.add(btn_newExpression);
        /*
         * output area panel and components
         */
        pnl_outputArea = new JPanel();
        pnl_outputArea.setLayout(new BorderLayout()); // border layout as north-south-east-west-center
        pnl_outputArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // create empty space around txt area
        //create output txt area
        txt_output = new JTextArea(outputHeader);
        txt_output.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnl_outputArea.add(new JScrollPane(txt_output)); // add output text with scroll pane for long expressions
        /**
         * result area panel and components
         */
        pnl_resultArea = new JPanel();
        pnl_resultArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_resultArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //create label
        lbl_result = new JLabel("Result: ");
        lbl_result.setFont(new Font("SansSerif", Font.BOLD, 15));
        pnl_resultArea.add(lbl_result);
        //
        add(pnl_inputArea, BorderLayout.NORTH); // set to frame top
        add(pnl_outputArea, BorderLayout.CENTER); // set to frame center
        add(pnl_resultArea, BorderLayout.SOUTH);
    }

    /**
     * This class is used to handle button clicks actions only
     */
    private class EventHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton btn_pressed = (JButton) e.getSource(); // get pressed button
            if (btn_pressed == btn_start) { // if start button was pressed
                //if no expression entered
                if (txt_input.getText().equals("") || txt_input.getText() == null) {
                    JOptionPane.showMessageDialog(null, "Please Enter Expression");
                } //otherwise
                else {
                    if (checkValid()) {
                        //create object of input to postfix by this expression
                        inToPost = new InfixtoPostfix(txt_input.getText());
                        //disable txt and start button
                        txt_input.setEnabled(false);
                        btn_start.setEnabled(false);
                        //enable step,result and new expression buttons
                        btn_step.setEnabled(true);
                        btn_newExpression.setEnabled(true);
                    }
                }
            }
            if (btn_pressed == btn_step) { // if step button was pressed
                //first check if expression is fully converted
                int i;
                for (i = 0; i < txt_input.getText().length(); i++) {
                    //second we don't count any spaces
                    while (txt_input.getText().charAt(i) == ' ') {
                        i++; // skip all spaces
                    }
                    // do one step for infix to postfix conversion
                    String infix = inToPost.getInfix(i); // current infix
                    String postfix = inToPost.toPostfix(i); // current postfix
                    char readCharacter = infix.charAt(i); // current character read
                    
                    txt_output.append(String.format("%c\t%s\t\t\t%s\n", readCharacter, infix, postfix)); // add output
                    i++;
                }
                btn_step.setEnabled(false);
                evalutePotfix = new EvalPostfix(inToPost.toPostfix(i - 2)); // evaluate expreesion
                lbl_result.setText("Result is   " + evalutePotfix.evaluate());
            }
            if (btn_pressed == btn_newExpression) { // if new expression button was pressed
                //set output text to default (header only)
                txt_output.setText(outputHeader);
                //enable and clear inptu text 
                txt_input.setEnabled(true);
                txt_input.setText("");
                //disable step and new expression buttons
                btn_step.setEnabled(false);
                btn_newExpression.setEnabled(false);
                //enable start button
                btn_start.setEnabled(true);
            }
        }

        /**
         * check if expression is valid
         *
         * @return true if valid, otherwise false
         */
        private boolean checkValid() {
            String exp = txt_input.getText();
            char[] ch = exp.toCharArray();
            Stack s = new Stack();
            //check ( )
            for (int i = 0; i < ch.length; i++) {
                if (ch[i] == '(')
                    s.push(ch[i]);
                if (ch[i] == ')') {
                    if (s.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Not valid exp");
                        return false;
                    }
                    s.pop();
                }
            }
            if (!s.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Not valid exp");
                return false;
            }
            char first = ch[0];
            if (first == '+' || first == '-' || first == '*' || first == '/') {
                JOptionPane.showMessageDialog(null, "Not valid exp");
                return false;
            }
            if (ch[ch.length - 1] == '+' || ch[ch.length - 1] == '-' || ch[ch.length - 1] == '*' || ch[ch.length - 1] == '/') {
                JOptionPane.showMessageDialog(null, "Not valid exp");
                return false;
            }
            for (int i = 1; i < ch.length; i++) {
                if (ch[i] == ' ')
                    continue;
                if (Character.isDigit(ch[i]) && Character.isDigit(first)) {
                    JOptionPane.showMessageDialog(null, "Not valid exp");
                    return false;
                }
                if (Character.isAlphabetic(ch[i]) && Character.isAlphabetic(first)) {
                    JOptionPane.showMessageDialog(null, "Not valid exp");
                    return false;
                }
                if ((ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/')
                        && (first == '+' || first == '-' || first == '*' || first == '/')) {
                    JOptionPane.showMessageDialog(null, "Not valid exp");
                    return false;
                }
                first = ch[i];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        new StackGUI().setVisible(true);
    }
}

/**
 * this class user to evaluate postfix expression of numbers (not symbols)
 */
class EvalPostfix {

    private String postfix;
    private Stack stack;

    public EvalPostfix(String postfix) {
        this.postfix = postfix;
        stack = new Stack();
    }

    public String getPostfix() {
        return postfix;
    }

    public String evaluate() {
        char[] chars = postfix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i]))
                return postfix;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
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

    private static boolean isOperator(char ch) {
        return ch == '*' || ch == '/' || ch == '+' || ch == '-' || ch == '^';
    }
}

/**
 * this class used to convert infix expression to postfix
 */
class InfixtoPostfix {

    private String infix;
    private Stack stack;

    public InfixtoPostfix(String infix) {
        this.infix = infix;
        stack = new Stack();
    }

    public String getInfix(int i) {
        return infix.substring(0, i + 1);
    }

    public int priority(char ch) {
        if (ch == '^')
            return 3;
        if (ch == '/' || ch == '*')
            return 2;
        if (ch == '+' || ch == '-')
            return 1;
        return 0;
    }

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
