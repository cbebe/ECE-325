package part1;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Person} class
 */
public class Person {
    private String name;

    /**
     * Constructor for Person
     * 
     * @param name {@code String} name of person
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * @return {@code String} name of person
     */
    public String getName() {
        return name;
    }
}
