package ece325.TestCases;

import ece325.Person;
import ece325.PersonComparator;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Assignment 5: Interfaces <br />
 * Part 3: The {@code PersonTest} class
 */
public class PersonTests {
    private Set<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = new TreeSet<Person>(new PersonComparator());
        persons.add(new Person(32));
        persons.add(new Person(17));
        persons.add(new Person(13));
        persons.add(new Person(35));
        persons.add(new Person(27));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void greaterThan() {
        PersonComparator p = new PersonComparator();
        Person a = new Person(420);
        Person b = new Person(69);
        assertTrue(p.compare(a, b) > 0);
    }

    @Test
    public void lessThan() {
        PersonComparator p = new PersonComparator();
        Person a = new Person(420);
        Person b = new Person(69);
        assertTrue(p.compare(b, a) < 0);
    }

    @Test
    public void equalTo() {
        PersonComparator p = new PersonComparator();
        Person a = new Person(80085);
        Person b = new Person(80085);
        assertEquals(0, p.compare(a, b));
    }

    private boolean isSorted() {
        Iterator<Person> iter = persons.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

        return true;
    }

    @Test
    public void testSorted() {
        assertTrue("Person TreeSet should be automatically sorted", isSorted());
    }
}
