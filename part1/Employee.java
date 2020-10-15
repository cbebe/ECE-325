package part1;
/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Employee} class
 */
public class Employee extends Person {
    private double baseSalary;

    public Employee(String name, double baseSalary) {
        super(name);
        this.baseSalary = baseSalary;
    }

    /**
     * @return {@code double} base salary of employee
     */
    public double getBaseSalary() {
        return baseSalary;
    }
}


