package part1;

import java.util.Date;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Test} class (main)
 */
public class Test {
  public static void main(String args[]) {
    HwEngineer linda = new HwEngineer("Linda", 3000);
    Date d = new Date();
    ProjManager john = new ProjManager("John", 6000, "Snake game", d);
    System.out.println(
        "Linda's salary: " + linda.raiseSalary() + ", John's salary: " + john.raiseSalary());

    SwEngineer steph = new SwEngineer("Steph", 3000, "AI");
    SwEngineer steph2 = new SwEngineer("Steph", 1000, "AI");

    ProjManager john2 = new ProjManager("John", 6000, "Snake game", d);

    System.out.println("steph.equals(steph2): " + steph.equals(steph2));
    System.out.println("john.equals(john2): " + john.equals(john2));

    System.out.println(
        "steph.hashCode() == steph2.hashCode(): " + (steph.hashCode() == steph2.hashCode()));
    System.out
        .println("john.hashCode() == john2.hashCode(): " + (john.hashCode() == john2.hashCode()));
  }
}
