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
        Double[] roots;
        double epsilon = 0.0000001;
        // discriminant in the quadratic equation
        double disc = Math.sqrt(b * b - 4 * a * c);
        // denominator in the quadratic equation
        double deno = 2 * a;
        if (disc >= epsilon) {
            roots = new Double[] { (-b + disc) / deno, (-b - disc) / deno };
        } else if (disc >= 0 && disc < epsilon) {
            roots = new Double[] { -b / deno };
        } else {
            roots = new Double[] { Double.NaN, Double.NaN };
        }
        return roots;
    }

    public Double squareRoot(double a) {
        return Math.sqrt(a);
    }
}