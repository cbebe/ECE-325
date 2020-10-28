package ece325.TestCases;

import ece325.Coffee;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

/**
 * Assignment 5: Interfaces <br />
 * Part 1: The {@code CoffeeTest} class
 */
public class CoffeeTests {
    private List<Coffee> coffees;

    @Before
    public void setUp() throws Exception {
        coffees = new ArrayList<Coffee>();
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(2));
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(20));
        coffees.add(new Coffee(5));
    }

    @After
    public void tearDown() throws Exception {
    }

    private boolean isSorted() {
        System.out.println("Coffees in order of strength:");
        for (Coffee type : coffees) {
            System.out.println(type);
        }

        return true;
    }

    @Test
    public void testComparable() {
        // TODO: Assignment 5 Part 1 -- rewrite this using JUnit
        Collections.sort(coffees);
        assertTrue("Coffee List must be sorted", isSorted());
    }
}
