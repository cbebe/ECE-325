import java.util.TreeSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Assignment 5: Interfaces <br />
 * Part 3: The {@code PersonTest} class
 */
public class PersonTest {
    public static void runTest() {
        // TODO: Assignment 5 Part 3 -- rewrite this using JUnit
        Set<Person> persons = new TreeSet<Person>(new PersonComparator());
        persons.add(new Person(32));
        persons.add(new Person(17));
        persons.add(new Person(13));
        persons.add(new Person(35));
        persons.add(new Person(27));

        Iterator<Person> iter = persons.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
