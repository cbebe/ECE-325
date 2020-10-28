package ece325;

/**
 * Assignment 5: Interfaces <br />
 * Part 1: The {@code Coffee} class
 */
public class Coffee implements Comparable<Coffee> {
    private int strength; // The strength of the coffee

    public Coffee(int strength) {
        this.strength = strength;
    }

    /**
     * Compares this to another Coffee object
     * 
     * @param c {@ocode Cofee} Coffee to be compared to this
     * @return {@code int} the result of the comparison
     */
    public int compareTo(Coffee c) {
        // ternary operators muahaha
        return (c.strength > strength) ? -1 : (c.strength < strength) ? 1 : 0;
    }
}
