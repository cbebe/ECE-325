package part1;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Customer} class
 */
public class Customer extends Person implements Printable {
    private double projPrice;

    /**
     * Constructor for Customer
     * 
     * @param name      {@code String} name of customer
     * @param projPrice {@code double} price of the project
     */
    public Customer(String name, double projPrice) {
        super(name);
        this.projPrice = projPrice;
    }

    /**
     * @return {@code double} projPrice field
     */
    public double getProjPrice() {
        return projPrice;
    }

    /**
     * @return {@code String} information about the class (i.e. name and projPrice)
     */
    public String printInfo() {
        return String.format("Name: %s, Project price: %s", getName(), getProjPrice());
    }
}
