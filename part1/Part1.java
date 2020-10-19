package part1;

import java.util.Date;

/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Test} class (main)
 */
public class Part1 {
  public static void main(String args[]) {
    HwEngineer will = new HwEngineer("Will", 3000);
    ProjManager john = new ProjManager("John", 6000, "Snake game", new Date());
    System.out.println("Will's salary: " + will.raiseSalary() + ", John's salary: " + john.raiseSalary());
  }
}
