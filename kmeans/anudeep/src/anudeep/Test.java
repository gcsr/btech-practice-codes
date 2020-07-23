package anudeep;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// reference stackoverflow
public class Test {

	public String convert(String postfix) throws Exception {
		// gets all the operators to expressions
		String[] expressions = postfix.split("");

		Deque<Expression> stack = new LinkedList<Expression>();

		for (String exp : expressions) {

			// if operator is + or - then no need to evaluate for parenthesis
			// else if its * or / then we need t evaluate for parenthesis reason
			// operator precedence
			if (exp.equals("+") || exp.equals("-")) {
				String right = stack.pop().getExpression();
				String left = stack.pop().getExpression();
				Expression newExp = new Expression(right + exp + left, exp);
				stack.push(newExp);
			} else if (exp.equals("*") || exp.equals("/")) {
				String right = correctExpression(stack.pop());
				String left = correctExpression(stack.pop());
				stack.push(new Expression(right + exp + left, exp));
			} else {
				stack.push(new Expression(exp, ""));
			}
		}
		return stack.pop().getExpression();
	}

	/*
	 * checks if the previous operator is + or -, if any one then adds a
	 * parenthesis
	 */
	private String correctExpression(Expression exp) {
		String result = exp.getExpression();
		if (exp.getOperatorUsed().equals("+")
				|| exp.getOperatorUsed().equals("-")) {
			result = "(" + result + ")";
		}
		return result;
	}

	private static class Expression {
		String expression;
		String operatorUsed;

		public Expression(String exp, String operator) {
			this.expression = exp;
			this.operatorUsed = operator;
		}

		public String getExpression() {
			return expression;
		}

		public String getOperatorUsed() {
			return operatorUsed;
		}
	}
	

	static int counter = 0;

	public static boolean validityCheck(String postfix) {

		if (postfix.length() % 2 == 0)
			return true;
		
		String formatSpecifier = "\\+|-|\\*|/";
		Pattern pattern = Pattern.compile(formatSpecifier);
		Matcher matcher = pattern.matcher(postfix);

		counter = 0;
		// CAN BELOW BE REPLACED WITH A ONE LINER?
		while (matcher.find()) {
		    counter++;
		}
		//String[] expressions = postfix.split("\\+|-|\\*|/");
		//System.out.println(counter);

		if (counter != ((postfix.length() - 1) / 2))
			return true;

		return false;
	}

	public static void main(String[] anudeep) {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		int continueProcess = 0;
		Test converter = new Test();

		while (true) {
			System.out.println("Enter postfix Expression");
			input = scanner.nextLine();
			// input = scanner.next();

			if (validityCheck(input))
				System.out.println("Invalid postfix");
			else {
				try {
					input = converter.convert(input);// converter.convert("1 2 * 3 4 * + 5 *");
					System.out.println("Infix Expression is " + input);
				} catch (Exception ex) {
					// ex.printStackTrace();
					System.out.println("Invalid postfix");
				}
			}
			System.out
					.println("Do you want to continue int values allowed: 0 for no");
			// scanner.reset();
			try {
				continueProcess = scanner.nextInt();
			} catch (Exception ex) {
				System.out.println("invalid input exiting");
				break;
			}
			if (continueProcess == 0)
				break;

			scanner.nextLine();
			// System.out.println(converter.convert("a b + c + 2 *"));
			// System.out.println(converter.convert("a b **c + 2 *"));
		}

	}
}
