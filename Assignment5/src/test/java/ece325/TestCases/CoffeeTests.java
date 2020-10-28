package ece325.TestCases;

import ece325.Coffee;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void greaterThan() {
        Coffee a = new Coffee(69);
        Coffee b = new Coffee(420);
        assertEquals(1, b.compareTo(a));
    }

    @Test
    public void lessThan() {
        Coffee a = new Coffee(69);
        Coffee b = new Coffee(420);
        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void equalTo() {
        Coffee a = new Coffee(80085);
        Coffee b = new Coffee(80085);
        assertEquals(0, a.compareTo(b));
    }

    private boolean isSorted() {
        for (int i = 1; i < 0; ++i)
            if (coffees.get(i).compareTo(coffees.get(i - 1)) == -1)
                return false;

        return true;
    }

    @Test
    public void testSorted() {
        Collections.sort(coffees);
        assertTrue("Coffee List must be sorted", isSorted());
    }
}
