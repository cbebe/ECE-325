package ece325.TestCases;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ece325.*;

/**
 * JUnit test class for solving square roots
 */
public class SquareRootTests {

    private Calculator calc;
    private double epsilon = 0.0000001;

    // TODO: Assignment 4 Part 2 -- Checkpoint4

    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRandomPositiveSquareRoot() {
        // You cannot use the Math.sqrt() function in the test!
        double a = Math.random() * 200000000;
        double sqrtA = calc.squareRoot(a);
        assertTrue(Math.abs(sqrtA * sqrtA - a) < epsilon);
    }

    @Test
    public void testRandomNegitiveSquareRoot() {
        // The result should be a complex number i.e. Double.isNaN()
        double a = (Math.random() - 1.0) * 200000000;
        double sqrtA = calc.squareRoot(a);
        assertTrue(Double.isNaN(sqrtA));
    }

    @Test
    public void testSquareRootofZero() {
        // You cannot use the Math.sqrt() function in the test!
        assertTrue(Math.abs(calc.squareRoot(0.0) - 0.0) < epsilon);
    }

    @Test
    public void testSquareRootofOne() {
        // You cannot use the Math.sqrt() function in the test!
        assertTrue(Math.abs(calc.squareRoot(1.0) - 1.0) < epsilon);
    }

}
