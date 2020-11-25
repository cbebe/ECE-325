/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Animal} interface
 */
interface Animal {
    /**
     * An animal speaks
     * @return              {@code String} animal speaks
     */
    public String speak ();
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Lion} class
 */
class Lion implements Animal {
    /**
     * The lion speaks
     * @return              {@code String} lion speaks
     */
    public String speak() {
        return "ROAR";
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Mouse} class
 */
class Mouse implements Animal {
    /**
     * The mouse speaks
     * @return              {@code String} mouse speaks
    */
    public String speak() {
        return "SQUEAK";
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Bison} class
 */
class Bison implements Animal {
    /**
     * The bison speaks
     * @return              {@code String} bison speaks
     */
    public String speak() {
        return "BELLOW";
    }
}

class Dog implements Animal {
    
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code AnimalType} class
 */
class AnimalType {
    /**
     * Create and return an animal
     * @param criteria      {@code String} how is the animal like
     * @return              {@code Animal} the animal
     */
    public static Animal getAnimal(String criteria) {
        // TODO: Lab 6 Part 2-1 -- Refactor this method
        if (criteria.equals("small"))
            return new Mouse();
        else if (criteria.equals("big"))
            return new Bison();
        else if (criteria.equals("lazy"))
            return new Lion();
        return null;
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code JavaDPExample} class
 */
public class JavaDPExample {
    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        Animal small = AnimalType.getAnimal("small");
        System.out.println(small.getClass().getName() + " speaks: " + small.speak());
        Animal big = AnimalType.getAnimal("big");
        System.out.println(big.getClass().getName() + " speaks: " + big.speak());
        Animal lazy = AnimalType.getAnimal("lazy");
        System.out.println(lazy.getClass().getName() + " speaks: " + lazy.speak());

        // TODO: Lab 6 Part 2-2 -- add an animal "Dog" here: criteria="loyal"; speak="woof"
        
        Animal loyal = AnimalType.getAnimal("loyal");
        System.out.println(loyal.getClass().getName() + " speaks: " + loyal.speak());

        // TODO: Lab 6 Part 2-3 -- remove the "small" animal here: Mouse

        try {
            small = AnimalType.getAnimal("small");
            System.out.println(small.getClass().getName() + " speaks: " + small.speak());
        } catch (Exception e) {
            System.out.println("Unkwon animal...");
        }
    }
}
