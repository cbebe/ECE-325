import java.util.Comparator;

/**
 * Assignment 5: Interfaces <br />
 * Part 3: The {@code Person} class
 */
public class Person {
    int age; // The age of the person

    public Person(int age) {
        this.age = age;
    }
}

class PersonComparator implements Comparator<Person> {
    public int compare(Person a, Person b) {
        return a.age - b.age;
    }
}