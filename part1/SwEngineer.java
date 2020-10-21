package part1;

import java.util.Objects;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code SwEngineer } class
 */
public class SwEngineer extends Employee {
    private String projName;

    /**
     * Constructor for SwEngineer
     * 
     * @param name       {@code String} name of Sw Engineer
     * @param baseSalary {@code double} base salary of Sw Engineer
     * @param projName   {@code String} name of project
     */
    public SwEngineer(String name, double baseSalary, String projName) {
        super(name, baseSalary);
        this.projName = projName;
    }

    /**
     * @return {@code String} projName string field
     */
    public String getProjName() {
        return projName;
    }

    @Override
    /**
     * @return {@code int} hashCode created with name, baseSalary, and projName
     */
    public int hashCode() {
        return Objects.hash(getName(), getBaseSalary(), projName);
    }

    @Override
    /**
     * Compare an Object to this
     * 
     * @param obj {@code Object} Object to compare to this
     * 
     * @return {@code boolean} true if the Object obj is equal to this
     */
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof SwEngineer))
            return false;

        SwEngineer swe = (SwEngineer) obj;
        return projName.equals(swe.projName) && getName().equals(swe.getName())
                && getBaseSalary() == swe.getBaseSalary();
    }
}
