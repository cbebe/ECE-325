package part1;
import java.util.Date;
import java.util.Objects;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code ProjManager } class
 */
public class ProjManager extends SwEngineer implements SalaryRaisable, Printable {
    private Date projDeadline;

    public ProjManager(String name, double baseSalary, String projName, Date projDeadline) {
        super(name, baseSalary, projName);
        this.projDeadline = projDeadline;
    }
 
    /**
     * @return {@code Date} projDeadline field
     */
    public Date getProjDeadline() {
        return projDeadline;
    }

    /**
     * @return {@code double} raised salary after multiplying baseSalary by the given rate
     */
    public double raiseSalary() {
        return getBaseSalary() * 1.24;
    }

    /**
     * @return {@code String} information about the class (i.e. name, projName, raiseSalary, projDeadline)
     */
    public String printInfo() {
        return String.format("Name: %s, Project name: %s, Final salary: %s, Project deadline: %s",
                getName(), getProjName(), raiseSalary(), getProjDeadline());
    }
    
    @Override
    /**
     * @return {@code int} hashCode created with SwEngineer hash code and projDeadline
     */
    public int hashCode() {
        return Objects.hash(super.hashCode(), projDeadline);
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
        if (obj == this) return true;

        if (!(obj instanceof ProjManager)) return false;

        ProjManager proj = (ProjManager) obj;
        return super.equals(proj) && projDeadline.equals(proj.projDeadline);
    }
}
