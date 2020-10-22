package ece325;

/**
 * Assignment 4 Part 2: Unit Testing <br />
 * The calculator to run the test cases
 */
// TODO: Assignment 4 Part 2 -- Create the Calculator here

public class Calculator {

    public Double add(double a, double b) {
        return a + b;
    }

    public Double divide(double a, double b) {
        return a / b;
    }

    public Double multiply(double a, double b) {
        return a * b;
    }

    public Double subtract(double a, double b) {
        return a - b;
    }

    public Double[] getRoots(double a, double b, double c) {
        double disc = Math.sqrt((b * b) - 4 * a * c);
        // double operations already handle NaN
        double x1 = (-b + disc) / (2 * a);
        double x2 = (-b - disc) / (2 * a);
        return (x1 == x2) ? new Double[] { x1 } : new Double[] { x1, x2 };
    }

    public Double squareRoot(double a) {
        return Math.sqrt(a);
    }
}