package part1;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Employee))
            return false;

        Employee emp = (Employee) obj;
        return super.equals(emp) && baseSalary == emp.baseSalary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseSalary);
    }
}


