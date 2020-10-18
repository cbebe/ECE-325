import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Stack;

@SuppressWarnings("serial")
class SyntaxError extends Exception {
	public SyntaxError(String errorMessage) {
		super(errorMessage);
	}
}

@SuppressWarnings("serial")
class RuntimeError extends Exception {
	public RuntimeError(String errorMessage) {
		super(errorMessage);
	}
}

public class Calculator {

	// Stack for numbers
	private Stack<Integer> numbers = new Stack<Integer>();
	// Stack for operators
	private Stack<Character> operators = new Stack<Character>();
	// HadhMap for variables
	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	// HashMap for Order of Operators
	@SuppressWarnings("serial")
	private HashMap<Character, Integer> order = new HashMap<Character, Integer>() {
		{
			put('^', 3);
			put('*', 2);
			put('+', 1);
			put('-', 0);
		}
	};

	public void syntaxError(String expression) throws SyntaxError {
		String bracketTest = expression.replaceAll("^()", "");

		while (bracketTest.contains("\\(\\)")) {
			bracketTest.replaceAll("()", "");
		}

		int bracketSets = 0;
		if (bracketTest.length() > 0) {

			for (int i = 0; i < bracketTest.length(); ++i) {
				if (bracketTest.charAt(i) == '(') {
					++bracketSets;
				} else if (bracketTest.charAt(i) == ')') {
					--bracketSets;
				}
			}
			if (bracketSets > 0) {
				throw new SyntaxError("\')\' expected");
			} else if (bracketSets < 0) {
				throw new SyntaxError("\'(\' expected");
			}
		}
		// let without =
		Pattern p = Pattern.compile("let [a-z] [a-z0-9]");
		Matcher m = p.matcher(expression);
		if (m.find()) {
			throw new SyntaxError("\'=\' expected");
		}
		// closing bracket does not exist
		p = Pattern.compile(" let [a-z]");
		m = p.matcher(expression);
		if (m.find()) {
			throw new SyntaxError("\')\' expected");
		}
	}

	// execute calculation
	private void calculation() throws SyntaxError {
		int right = numbers.pop();
		int left = numbers.pop();

		switch (operators.pop()) {
			case '+':
				numbers.push(left + right);
				break;
			case '-':
				numbers.push(left - right);
				break;
			case '*':
				numbers.push(left * right);
				break;
			case '^':
				numbers.push((int) Math.pow(left, right));
				break;
			default:
				throw new SyntaxError("invalid operator");
		}
	}

	private int getVariable(String variable) throws RuntimeError {
		Integer value = variables.get(variable);

		if (value == null)
			throw new RuntimeError(String.format("\'%s\' undefined", variable));

		return value;
	}

	private boolean compareOperators(char op1, char op2) {
		char[] operators = { '-', '+', '*', '^' };
		int op1Value = -1, op2Value = -1;
		for (int i = 0; i < 4; i++) {
			if (operators[i] == op1) {
				op1Value = i;
			}
			if (operators[i] == op2) {
				op2Value = i;
			}
		}

		return op1Value > op2Value;
	}

	private int operation(String expression) throws RuntimeError, SyntaxError {
		if (expression.matches("^\\d+$"))
			return Integer.parseInt(expression);

		if (expression.matches("^[a-z]$"))
			return getVariable(expression);

		Pattern p = Pattern.compile("([a-z]+) *([a-z])? *(=)? *(.*)");
		Matcher m = p.matcher(expression);

		if (m.matches()) {
			if (!expression.startsWith("let")) {
				throw new RuntimeError(String.format("\'%s\' undefined", m.group(1)));
			}

			int value = operation(m.group(4));
			variables.put(m.group(2), value);
			return value;
		}

		operators.clear();
		numbers.clear();

		String[] input = expression.split(" ");

		Pattern p2 = Pattern.compile("[a-z0-9] [a-z0-9]");
		Matcher m2 = p2.matcher(expression);
		if (m2.find()) {
			throw new SyntaxError("operator expected");
		}

		for (String in : input) {
			if (in.matches("[a-z]|\\d+")) {
				numbers.push(in.matches("\\d+") ? Integer.parseInt(in) : getVariable(in));
			} else if (in.matches("[*+^-]")) {
				char operator = in.charAt(0);
				if ((operators.size() > 0) && compareOperators(operators.peek(), operator)) {
					calculation();
				}
				operators.push(operator);
			}
		}

		while (operators.size() > 0) {
			calculation();
		}
		int returnValue = numbers.pop();

		return returnValue;
	}

	/**
	 * Execute the expression, and return the correct value
	 * 
	 * @param exp {@code String} The expression string
	 * @return {@code int} The value of the expression
	 */
	public int execExpression(String exp) throws RuntimeError, SyntaxError {
		// remove ;
		String expression = exp.replace(";", "");

		// check syntax error
		syntaxError(expression);

		Stack<String> Stack = new Stack<String>();
		variables.clear();

		String input = "";

		for (int i = 0; i < expression.length(); ++i) {
			char readIn = expression.charAt(i);
			if (readIn == '(') {
				Stack.push(input);
				input = "";
			} else if (readIn == ')') {
				int value = operation(input);

				if (Stack.size() == 0) {
					return value;
				}
				input = Stack.pop() + value;
			} else {
				input += readIn;
			}
		}

		int returnValue = operation(input);
		return returnValue;
	}

	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		// Part 1
		String[] inputs = { "let x = 1;", // 1, returns 1
				"(let x = 1) + x;", // 2, returns 2
				"(let a = 2) + 3 * a - 5;", // 3, returns 3
				"(let x = (let y = (let z = 1))) + x + y + z;", // 4, returns 4
				"1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;", // 5, returns 5
				"1 + (let a = (let b = 1) + b) + a + 1;", // 6, returns 6
				"(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;", // 7, returns 7
				"(let x = 2) ^ (let y = 3);", // 8, returns 8
				"(let y = 3) ^ (let x = 2);" // 9, returns 9
		};

		for (int i = 0; i < inputs.length; i++) {
			try {
				System.out.println(String.format("%d -- %-90s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
			} catch (Exception exception) {
				System.out.println(exception); // no errors here :)
			}
		}

		// Part 2
		inputs = new String[] { "1 + (2 * 3;", // 1, syntax error: ')' expected
				"(let x 5) + x;", // 2, syntax error: '=' expected
				"(let x = 5) (let y = 6);", // 3, syntax error: operator expected
				"(let x = 5 let y = 6);", // 4, syntax error: ')' expected
				"(ler x = 5) ^ (let y = 6);", // 5, runtime error: 'ler' undefined
				"(let x = 5) + y;" // 6, runtime error: 'y' undefined
		};
		for (int i = 0; i < inputs.length; i++) {
			try {
				System.out.println(String.format("%d -- %-30s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
			} catch (Exception exception) {
				System.out.println(exception);
			}
		}
	}
}
