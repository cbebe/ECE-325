package ece325.TestCases;

import ece325.Coffee;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testComparable() {
        // TODO: Assignment 5 Part 1 -- rewrite this using JUnit
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(2));
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(20));
        coffees.add(new Coffee(5));
        Collections.sort(coffees);

        System.out.println("Coffees in order of strength:");
        for (Coffee type : coffees) {
            System.out.println(type);
        }
    }
}
