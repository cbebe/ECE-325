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

    public int compareTo(Coffee c) {
        if (c.strength > strength)
            return -1;
        else if (c.strength < strength)
            return 1;
        else
            return 0;
    }
}
