/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Animal} interface
 */
interface Animal {
    /**
     * An animal speaks
     * 
     * @return {@code String} animal speaks
     */
    public String speak();
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Lion} class
 */
class Lion implements Animal {
    /**
     * The lion speaks
     * 
     * @return {@code String} lion speaks
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
     * 
     * @return {@code String} mouse speaks
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
     * 
     * @return {@code String} bison speaks
     */
    public String speak() {
        return "BELLOW";
    }
}

class Dog implements Animal {
    /**
     * The dog speaks
     * 
     * @return {@code String} dog speaks
     */
    public String speak() {
        return "WOOF";
    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code AnimalType} class
 */
class AnimalType {
    public static final java.util.HashMap<String, Class<? extends Animal>> criteriaMap = new java.util.HashMap<>();

    /**
     * Create and return an animal
     * 
     * @param criteria {@code String} how is the animal like
     * @return {@code Animal} the animal, null if not in map
     */
    public static Animal getAnimal(String criteria) {
        try {
            return AnimalType.criteriaMap.get(criteria).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

    }
}

/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code JavaDPExample} class
 */
public class JavaDPExample {
    /**
     * Main entry
     * 
     * @param args {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        // add classes into animal map
        AnimalType.criteriaMap.put("small", Mouse.class);
        AnimalType.criteriaMap.put("big", Bison.class);
        AnimalType.criteriaMap.put("lazy", Lion.class);

        Animal small = AnimalType.getAnimal("small");
        System.out.println(small.getClass().getName() + " speaks: " + small.speak());
        Animal big = AnimalType.getAnimal("big");
        System.out.println(big.getClass().getName() + " speaks: " + big.speak());
        Animal lazy = AnimalType.getAnimal("lazy");
        System.out.println(lazy.getClass().getName() + " speaks: " + lazy.speak());

        // add dog with criterion: loyal and speak: woof
        AnimalType.criteriaMap.put("loyal", Dog.class);

        Animal loyal = AnimalType.getAnimal("loyal");
        System.out.println(loyal.getClass().getName() + " speaks: " + loyal.speak());

        // remove mouse class
        AnimalType.criteriaMap.remove("small");

        try {
            small = AnimalType.getAnimal("small");
            System.out.println(small.getClass().getName() + " speaks: " + small.speak());
        } catch (Exception e) {
            System.out.println("Unknown animal...");
        }
    }
}
