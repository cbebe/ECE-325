package ece325.TestCases;

import ece325.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Assignment 5: Interfaces <br />
 * Part 3: The {@code PersonTest} class
 */
public class PersonTests {
    private java.util.Set<Person> persons;
    private ece325.PersonComparator p;

    @org.junit.Before
    public void setUp() throws Exception {
        p = new PersonComparator();
        persons = new java.util.TreeSet<Person>(p);
        persons.add(new Person(32));
        persons.add(new Person(17));
        persons.add(new Person(13));
        persons.add(new Person(35));
        persons.add(new Person(27));
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void greaterThan() {
        Person a = new Person(420);
        Person b = new Person(69);
        assertTrue(p.compare(a, b) > 0);
    }

    @Test
    public void lessThan() {
        Person a = new Person(420);
        Person b = new Person(69);
        assertTrue(p.compare(b, a) < 0);
    }

    @Test
    public void equalTo() {
        Person a = new Person(80085);
        Person b = new Person(80085);
        assertEquals(0, p.compare(a, b));
    }

    /**
     * @return {@code boolean} whether the TreeSet is sorted or not
     */
    private boolean isSorted() {
        java.util.Iterator<Person> iter = persons.iterator();
        Person a = iter.next();
        while (iter.hasNext()) {
            Person b = iter.next();
            // a should be less than or equal to b
            if (p.compare(a, b) > 0)
                return false;
            a = b;
        }

        return true;
    }

    @Test
    public void testSorted() {
        assertTrue("Person TreeSet should be automatically sorted", isSorted());
    }
}
