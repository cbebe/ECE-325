package part1;

import java.util.Date;

public class Part2 {
    public static void main(String args[]) {
        SwEngineer steph = new SwEngineer("Steph", 3000, "AI");
        SwEngineer steph2 = new SwEngineer("Steph", 3000, "AI");

        Date now = new Date();
        ProjManager john = new ProjManager("John", 6000, "Snake game", now);
        ProjManager john2 = new ProjManager("John", 6000, "Snake game", now);

        System.out.println("steph.equals(steph2): " + steph.equals(steph2));
        System.out.println("john.equals(john2): " + john.equals(john2));

        System.out.println("steph.hashCode() == steph2.hashCode(): " + (steph.hashCode() == steph2.hashCode()));
        System.out.println("john.hashCode() == john2.hashCode(): " + (john.hashCode() == john2.hashCode()));
    }
}
