package ece325.TestCases;

import ece325.Person;
import ece325.PersonComparator;
import java.util.TreeSet;

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

    private boolean isSorted() {
        Iterator<Person> iter = persons.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        return true;
    }

    @Test
    public void runTest() {
        assertTrue(isSorted());
    }
}
