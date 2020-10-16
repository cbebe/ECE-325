import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class RuntimeError extends Exception {
  private static final long serialVersionUID = 1L;

  public RuntimeError(String message) {
    super(message);
  }
}

class SyntaxError extends Exception {
  private static final long serialVersionUID = 1L;

  public SyntaxError(String message) {
    super(message);
  }
}

/**
 * ECE 326 - Fall 2020 <br/>
 * Assignment 4: Exception handling <br/>
 * Calculator using BNF
 * <p>
 * 
 * @author Charles Ancheta
 */
public class Calculator {

  private HashMap<String, Integer> variableMap = new HashMap<String, Integer>();
  private Stack<String> operationStack = new Stack<String>();

  /**
   * Evaluates a string with purely binary operations Using PEMAS as the order of
   * operations (no division)
   * 
   * @param operations {@code String} string to be evaluated
   * @return {@code int} value of the expression
   */
  int evalOperations(String operations) {
    int returnValue = 0;

    return returnValue;
  }

  /**
   * Execute the expression, and return the correct value
   * 
   * @param exp {@code String} The expression string
   * @return {@code int} The value of the expression
   */
  public int execExpression(String exp) throws RuntimeError, SyntaxError {
    int returnValue = 0;
    variableMap.clear();
    operationStack.clear();
    // TODO: Assignment 4 Part 1 -- parse, calculate the expression, and return the
    // correct value

    String token = "";
    for (int i = 0, n = exp.length(); i < n; ++i) {
      token += exp.charAt(i);

      token = "";
    }

    // TODO: Assignment 4 Part 2-1 -- when come to illegal expressions, raise proper
    // exceptions

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

    for (int i = 0; i < inputs.length; i++)
      try {
        System.out.println(String.format("%d -- %-90s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
      } catch (Exception e) {
        System.out.println(e);
      }

    // Part 2
    inputs = new String[] { "1 + (2 * 3;", // 1, syntax error: ')' expected
        "(let x 5) + x;", // 2, syntax error: '=' expected
        "(let x = 5) (let y = 6);", // 3, syntax error: operator expected
        "(let x = 5 let y = 6);", // 4, syntax error: ')' expected
        "(ler x = 5) ^ (let y = 6);", // 5, runtime error: 'ler' undefined
        "(let x = 5) + y;" // 6, runtime error: 'y' undefined
    };
    // TODO: Assignment 3 Part 2-2 -- catch and deal with your exceptions here
    for (int i = 0; i < inputs.length; i++)
      try {
        System.out.println(String.format("%d -- %-30s %d", i + 1, inputs[i], calc.execExpression(inputs[i])));
      } catch (Exception e) {
        System.out.println(e);
      }

  }

}
