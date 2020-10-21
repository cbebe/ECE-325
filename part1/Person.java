package part1;

import java.util.Objects;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Person} class
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    /**
     * @return {@code String} name of person
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Person))
            return false;

        Person person = (Person) obj;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

