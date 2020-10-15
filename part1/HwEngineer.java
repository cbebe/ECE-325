package part1;
/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code HwEngineer} class
 */
public class HwEngineer extends Employee implements SalaryRaisable {
    public HwEngineer(String name, double baseSalary) {
        super(name, baseSalary);
    }

    /**
     * @return {@code double} raised salary after increasing the base salary by the given rate
     */
    public double raiseSalary() {
        return getBaseSalary() * 1.18;
    }
}