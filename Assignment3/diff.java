1,2d0
< import java.util.HashMap;
< import java.util.Stack;
4a3,4
> import java.util.HashMap;
> import java.util.Stack;
7,10c7,10
< class RuntimeError extends Exception {
<   public RuntimeError(String message) {
<     super(message);
<   }
---
> class SyntaxError extends Exception {
> 	public SyntaxError(String errorMessage) {
> 		super(errorMessage);
> 	}
14,17c14,17
< class SyntaxError extends Exception {
<   public SyntaxError(String message) {
<     super(message);
<   }
---
> class RuntimeError extends Exception {
> 	public RuntimeError(String errorMessage) {
> 		super(errorMessage);
> 	}
20,27d19
< /**
<  * ECE 325 - Fall 2020 <br/>
<  * Assignment 3: Exception handling <br/>
<  * Calculator using BNF
<  * <p>
<  * 
<  * @author Charles Ancheta
<  */
30,268c22,255
<   /**
<    * HashMap to store variables
<    */
<   private HashMap<String, Integer> varMap = new HashMap<String, Integer>();
< 
<   /**
<    * HashMap for order of operations
<    */
<   @SuppressWarnings("serial")
<   private HashMap<Character, Integer> operationOrder = new HashMap<Character, Integer>() {
<     {
<       put('-', 0);
<       put('+', 1);
<       put('*', 2);
<       put('^', 3);
<     }
<   };
< 
<   private Stack<Integer> valueStack = new Stack<Integer>();
<   private Stack<Character> operatorStack = new Stack<Character>();
< 
<   /**
<    * Checks for bracket balance in the expression
<    * 
<    * @param exp {@code String} expression to be checked
<    * @throws SyntaxError
<    */
<   private void checkBrackets(String exp) throws SyntaxError {
<     int bCount = 0; // number of open parentheses
<     for (int i = 0; i < exp.length(); ++i) {
<       char c = exp.charAt(i);
<       if (c == '(') {
<         ++bCount;
<       } else if (c == ')') {
<         if (bCount == 0)
<           throw new SyntaxError("\"(\" expected");
<         --bCount;
<       }
<     }
<     if (bCount > 0)
<       throw new SyntaxError("\")\" expected");
<   }
< 
<   /**
<    * Does binary operation on the stack
<    * 
<    * @throws SyntaxError
<    */
<   private void doOperation() throws SyntaxError {
<     int val2 = valueStack.pop();
<     int val1 = valueStack.pop();
<     int result = 0;
< 
<     switch (operatorStack.pop()) {
<       case '+':
<         result = val1 + val2;
<         break;
<       case '-':
<         result = val1 - val2;
<         break;
<       case '*':
<         result = val1 * val2;
<         break;
<       case '^':
<         result = (int) Math.pow(val1, val2);
<         break;
<       default:
<         throw new SyntaxError("invalid operator");
<     }
< 
<     valueStack.push(result);
<   }
< 
<   /**
<    * Gets value of variable from varMap
<    * 
<    * @param var {@code String} name of variable
<    * @return {@code int} value of variable
<    * @throws RuntimeError
<    */
<   private int getVariable(String var) throws RuntimeError {
<     try {
<       return varMap.get(var);
<     } catch (NullPointerException e) {
<       throw new RuntimeError(String.format("\"%s\" undefined", var));
<     }
<   }
< 
<   /**
<    * Evaluates math expression using stacks
<    * 
<    * @param exp {@code String} expressionto be evaluated
<    * @return {@code int} final value of the expression
<    * @throws SyntaxError
<    * @throws RuntimeError
<    */
<   private int evaluateMath(String exp) throws SyntaxError, RuntimeError {
<     operatorStack.clear();
<     valueStack.clear();
<     // assumes that all operands and operations are split by spaces
<     String[] tokens = exp.split(" ");
<     boolean noOperator = false;
<     for (String t : tokens) {
<       if (t.matches("\\d+|[a-z]")) {
<         if (noOperator)
<           throw new SyntaxError("operator expected");
< 
<         valueStack.push(t.matches("\\d+") ? Integer.parseInt(t) : getVariable(t));
<         noOperator = true;
<       } else if (t.matches("[*+^-]")) {
<         char operator = t.charAt(0);
<         if (!operatorStack.empty() && operationOrder.get(operator) <= operationOrder.get(operatorStack.peek()))
<           doOperation();
< 
<         operatorStack.push(operator);
<         noOperator = false;
<       }
<     }
< 
<     while (!operatorStack.empty())
<       doOperation();
< 
<     return valueStack.pop();
<   }
< 
<   /**
<    * Evaluates an expression (without brackets)
<    * 
<    * @param exp {@code String} expression to be evaluated
<    * @return {@code int} final value of the expression
<    * @throws RuntimeError
<    * @throws SyntaxError
<    */
<   private int calculate(String exp) throws RuntimeError, SyntaxError {
<     if (exp.matches("^\\d+$"))
<       return Integer.parseInt(exp);
< 
<     if (exp.matches("^[a-z]$"))
<       return getVariable(exp);
< 
<     // the pinnacle of test-driven development
<     Pattern p = Pattern.compile("([a-z]+) *([a-z])? *(=)? *(.*)");
<     Matcher m = p.matcher(exp);
<     if (m.matches()) {
<       // invalid assignment command
<       if (!exp.startsWith("let"))
<         throw new RuntimeError(String.format("\"%s\" undefined", m.group(1)));
< 
<       // assignment expression not closed
<       if (exp.substring(3).contains("let"))
<         throw new SyntaxError("\")\" expected");
< 
<       if (m.group(3) == null)
<         throw new SyntaxError("\"=\" expected");
< 
<       int val = calculate(m.group(4));
<       varMap.put(m.group(2), val);
<       return val;
<     }
< 
<     return evaluateMath(exp);
<   }
< 
<   /**
<    * Execute the expression, and return the correct value
<    * 
<    * @param exp {@code String} The expression string
<    * @return {@code int} The value of the expression
<    */
<   public int execExpression(String exp) throws RuntimeError, SyntaxError {
<     exp = exp.replace(";", ""); // remove ;
<     checkBrackets(exp);
< 
<     Stack<String> expStack = new Stack<String>();
<     varMap.clear();
< 
<     String token = "";
<     // split the expression between parentheses and push into stack
<     for (int i = 0; i < exp.length(); ++i) {
<       char c = exp.charAt(i);
<       if (c == '(') {
<         expStack.push(token);
<         token = "";
<       } else if (c == ')') {
<         int value = calculate(token);
< 
<         if (expStack.isEmpty())
<           return value;
< 
<         token = expStack.pop() + value;
<       } else {
<         token += c;
<       }
<     }
< 
<     return calculate(token);
<   }
< 
<   /**
<    * Main entry
<    * 
<    * @param args {@code String[]} Command line arguments
<    */
<   public static void main(String[] args) {
<     Calculator calc = new Calculator();
<     // Part 1
<     String[] inputs = { "let x = 1;", // 1, returns 1
<         "(let x = 1) + x;", // 2, returns 2
<         "(let a = 2) + 3 * a - 5;", // 3, returns 3
<         "(let x = (let y = (let z = 1))) + x + y + z;", // 4, returns 4
<         "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;", // 5, returns 5
<         "1 + (let a = (let b = 1) + b) + a + 1;", // 6, returns 6
<         "(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;", // 7, returns 7
<         "(let x = 2) ^ (let y = 3);", // 8, returns 8
<         "(let y = 3) ^ (let x = 2);" // 9, returns 9
<     };
< 
<     for (int i = 0; i < inputs.length; i++)
<       try {
<         System.out.println(String.format("%d -- %-90s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
<       } catch (Exception e) {
<         System.out.println(e); // no errors here :)
<       }
< 
<     // Part 2
<     inputs = new String[] { "1 + (2 * 3;", // 1, syntax error: ')' expected
<         "(let x 5) + x;", // 2, syntax error: '=' expected
<         "(let x = 5) (let y = 6);", // 3, syntax error: operator expected
<         "(let x = 5 let y = 6);", // 4, syntax error: ')' expected
<         "(ler x = 5) ^ (let y = 6);", // 5, runtime error: 'ler' undefined
<         "(let x = 5) + y;" // 6, runtime error: 'y' undefined
<     };
<     for (int i = 0; i < inputs.length; i++)
<       try {
<         System.out.println(String.format("%d -- %-30s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
<       } catch (Exception e) {
<         System.out.println(e);
<       }
<   }
---
> 	// Stack for numbers
> 	private Stack<Integer> numbers = new Stack<Integer>();
> 	// Stack for operators
> 	private Stack<Character> operators = new Stack<Character>();
> 	// HadhMap for variables
> 	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
> 	// HashMap for Order of Operators
> 	@SuppressWarnings("serial")
> 	private HashMap<Character, Integer> order = new HashMap<Character, Integer>() {
> 		{
> 			put('^', 3);
> 			put('*', 2);
> 			put('+', 1);
> 			put('-', 0);
> 		}
> 	};
> 
> 	public void syntaxError(String expression) throws SyntaxError {
> 		String bracketTest = expression.replaceAll("^()", "");
> 
> 		while (bracketTest.contains("\\(\\)")) {
> 			bracketTest.replaceAll("()", "");
> 		}
> 
> 		int bracketSets = 0;
> 		if (bracketTest.length() > 0) {
> 
> 			for (int i = 0; i < bracketTest.length(); ++i) {
> 				if (bracketTest.charAt(i) == '(') {
> 					++bracketSets;
> 				} else if (bracketTest.charAt(i) == ')') {
> 					--bracketSets;
> 				}
> 			}
> 			if (bracketSets > 0) {
> 				throw new SyntaxError("\')\' expected");
> 			} else if (bracketSets < 0) {
> 				throw new SyntaxError("\'(\' expected");
> 			}
> 		}
> 		// let without =
> 		Pattern p = Pattern.compile("let [a-z] [a-z0-9]");
> 		Matcher m = p.matcher(expression);
> 		if (m.find()) {
> 			throw new SyntaxError("\'=\' expected");
> 		}
> 		// closing bracket does not exist
> 		p = Pattern.compile(" let [a-z]");
> 		m = p.matcher(expression);
> 		if (m.find()) {
> 			throw new SyntaxError("\')\' expected");
> 		}
> 	}
> 
> 	// execute calculation
> 	private void calculation() throws SyntaxError {
> 		int right = numbers.pop();
> 		int left = numbers.pop();
> 
> 		switch (operators.pop()) {
> 			case '+':
> 				numbers.push(left + right);
> 				break;
> 			case '-':
> 				numbers.push(left - right);
> 				break;
> 			case '*':
> 				numbers.push(left * right);
> 				break;
> 			case '^':
> 				numbers.push((int) Math.pow(left, right));
> 				break;
> 			default:
> 				throw new SyntaxError("invalid operator");
> 		}
> 	}
> 
> 	private int getVariable(String variable) throws RuntimeError {
> 		Integer value = variables.get(variable);
> 
> 		if (value == null)
> 			throw new RuntimeError(String.format("\'%s\' undefined", variable));
> 
> 		return value;
> 	}
> 
> 	private boolean compareOperators(char op1, char op2) {
> 		char[] operators = { '-', '+', '*', '^' };
> 		int op1Value = -1, op2Value = -1;
> 		for (int i = 0; i < 4; i++) {
> 			if (operators[i] == op1) {
> 				op1Value = i;
> 			}
> 			if (operators[i] == op2) {
> 				op2Value = i;
> 			}
> 		}
> 
> 		return op1Value > op2Value;
> 	}
> 
> 	private int operation(String expression) throws RuntimeError, SyntaxError {
> 		if (expression.matches("^\\d+$"))
> 			return Integer.parseInt(expression);
> 
> 		if (expression.matches("^[a-z]$"))
> 			return getVariable(expression);
> 
> 		Pattern p = Pattern.compile("([a-z]+) *([a-z])? *(=)? *(.*)");
> 		Matcher m = p.matcher(expression);
> 
> 		if (m.matches()) {
> 			if (!expression.startsWith("let")) {
> 				throw new RuntimeError(String.format("\'%s\' undefined", m.group(1)));
> 			}
> 
> 			int value = operation(m.group(4));
> 			variables.put(m.group(2), value);
> 			return value;
> 		}
> 
> 		operators.clear();
> 		numbers.clear();
> 
> 		String[] input = expression.split(" ");
> 
> 		Pattern p2 = Pattern.compile("[a-z0-9] [a-z0-9]");
> 		Matcher m2 = p2.matcher(expression);
> 		if (m2.find()) {
> 			throw new SyntaxError("operator expected");
> 		}
> 
> 		for (String in : input) {
> 			if (in.matches("[a-z]|\\d+")) {
> 				numbers.push(in.matches("\\d+") ? Integer.parseInt(in) : getVariable(in));
> 			} else if (in.matches("[*+^-]")) {
> 				char operator = in.charAt(0);
> 				if ((operators.size() > 0) && compareOperators(operators.peek(), operator)) {
> 					calculation();
> 				}
> 				operators.push(operator);
> 			}
> 		}
> 
> 		while (operators.size() > 0) {
> 			calculation();
> 		}
> 		int returnValue = numbers.pop();
> 
> 		return returnValue;
> 	}
> 
> 	/**
> 	 * Execute the expression, and return the correct value
> 	 * 
> 	 * @param exp {@code String} The expression string
> 	 * @return {@code int} The value of the expression
> 	 */
> 	public int execExpression(String exp) throws RuntimeError, SyntaxError {
> 		// remove ;
> 		String expression = exp.replace(";", "");
> 
> 		// check syntax error
> 		syntaxError(expression);
> 
> 		Stack<String> Stack = new Stack<String>();
> 		variables.clear();
> 
> 		String input = "";
> 
> 		for (int i = 0; i < expression.length(); ++i) {
> 			char readIn = expression.charAt(i);
> 			if (readIn == '(') {
> 				Stack.push(input);
> 				input = "";
> 			} else if (readIn == ')') {
> 				int value = operation(input);
> 
> 				if (Stack.size() == 0) {
> 					return value;
> 				}
> 				input = Stack.pop() + value;
> 			} else {
> 				input += readIn;
> 			}
> 		}
> 
> 		int returnValue = operation(input);
> 		return returnValue;
> 	}
> 
> 	/**
> 	 * Main entry
> 	 * 
> 	 * @param args {@code String[]} Command line arguments
> 	 */
> 	public static void main(String[] args) {
> 		Calculator calc = new Calculator();
> 		// Part 1
> 		String[] inputs = { "let x = 1;", // 1, returns 1
> 				"(let x = 1) + x;", // 2, returns 2
> 				"(let a = 2) + 3 * a - 5;", // 3, returns 3
> 				"(let x = (let y = (let z = 1))) + x + y + z;", // 4, returns 4
> 				"1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;", // 5, returns 5
> 				"1 + (let a = (let b = 1) + b) + a + 1;", // 6, returns 6
> 				"(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;", // 7, returns 7
> 				"(let x = 2) ^ (let y = 3);", // 8, returns 8
> 				"(let y = 3) ^ (let x = 2);" // 9, returns 9
> 		};
> 
> 		for (int i = 0; i < inputs.length; i++) {
> 			try {
> 				System.out.println(String.format("%d -- %-90s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
> 			} catch (Exception exception) {
> 				System.out.println(exception); // no errors here :)
> 			}
> 		}
> 
> 		// Part 2
> 		inputs = new String[] { "1 + (2 * 3;", // 1, syntax error: ')' expected
> 				"(let x 5) + x;", // 2, syntax error: '=' expected
> 				"(let x = 5) (let y = 6);", // 3, syntax error: operator expected
> 				"(let x = 5 let y = 6);", // 4, syntax error: ')' expected
> 				"(ler x = 5) ^ (let y = 6);", // 5, runtime error: 'ler' undefined
> 				"(let x = 5) + y;" // 6, runtime error: 'y' undefined
> 		};
> 		for (int i = 0; i < inputs.length; i++) {
> 			try {
> 				System.out.println(String.format("%d -- %-30s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
> 			} catch (Exception exception) {
> 				System.out.println(exception);
> 			}
> 		}
> 	}
