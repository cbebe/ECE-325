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
        double disc = (b * b) - 4 * a * c;
        if (disc < 0)
            return new Double[] {Double.NaN, Double.NaN};

        double x1 = (-b + Math.sqrt(disc)) / (2 * a);
        double x2 = (-b - Math.sqrt(disc)) / (2 * a);
        return (x1 == x2) ? new Double[] {x1} : new Double[] {x1, x2};
    }

    public Double squareRoot(double a) {
        return Math.sqrt(a);
    }
}
