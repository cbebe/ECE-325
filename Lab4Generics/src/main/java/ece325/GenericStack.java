package ece325;

import java.util.ArrayList;

/**
 * Lab 4: Generics <br />
 * The {@code GenericStack} class
 */
@SuppressWarnings("serial")
public class GenericStack<T> extends ArrayList<T> {
    /**
     * Query the top element
     * 
     * @return {@code T} the top element
     */
    public T peek() {
        return !isEmpty() ? get(size() - 1) : null;
    }

    /**
     * Add a new element as top element
     * 
     * @param value {@code T} the new element
     */
    public void push(T value) {
        add(value);
    }

    /**
     * Remove the top element
     * 
     * @return {@code T} the removed element
     */
    public T pop() {
        if (isEmpty())
            return null;

        T value = peek();
        remove(size() - 1);
        return value;
    }

    /**
     * Check if the stack is empty of not
     * 
     * @return {@code boolean} {@code true} for empty; {@code false} for not
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Do binary operation on two operands
     * 
     * @param b  {@code double} the second operand
     * @param a  {@code double} the first operand
     * @param op {@code char} the binary operator
     * @return {@code Double} the result of the operation
     */
    public static double doOperation(double b, double a, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                return Double.NaN; // invalid operator
        }
    }

    /**
     * Calculate a postfix expression
     * 
     * @param exp {@code String} the postfix expression
     * @return {@code Double} the value of the expression
     */
    public static double calcPostfixExpression(String exp) {
        GenericStack<Double> operands = new GenericStack<Double>();
        // assuming all tokens are split by spaces
        String[] tokens = exp.split(" ");

        for (String t : tokens)
            if (t.matches("\\d+"))
                operands.push(Double.parseDouble(t));
            else if (t.matches("[-^*+/]"))
                operands.push(doOperation(operands.pop(), operands.pop(), t.charAt(0)));
            else
                return Double.NaN; // there was an error

        return operands.pop();
    }
}
