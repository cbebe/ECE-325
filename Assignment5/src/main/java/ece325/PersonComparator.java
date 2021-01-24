package ece325;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    /**
     * Comparator for Person objects
     * 
     * @param a {@code Person} first Person object
     * @param b {@code Person} second Person object
     * 
     * @return {@code int} result of comparison
     */
    public int compare(Person a, Person b) {
        return a.age - b.age;
    }
}
