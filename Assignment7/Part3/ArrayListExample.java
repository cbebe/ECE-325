import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assignment 7: Type Compatibility and Generics <br />
 * An generic array
 */
public class ArrayListExample {

    /**
     * total_area -- takes a list of 2d shapes and calculates the total area of
     * those shapes
     */
    static double total_area(List<? extends TwoDShape<?>> list) {
        return list.stream().mapToDouble(TwoDShape::area).sum();
    }

    /**
     * total_perimeter -- takes a list of rectangles and calculates the total
     * perimeter
     */
    static double total_perimeter(List<Rectangle> list) {
        return list.stream().mapToDouble(Rectangle::perimeter).sum();
    }

    /**
     * describe_all -- takes a list of geometric shapes and invokes the
     * "{@code describe}" method on each of them, then prints out the total number
     * of shapes
     */
    static void describe_all(List<? extends GeometricShape<?>> list) {
        list.forEach(GeometricShape::describe);
        System.out.println("total number of shapes: " + list.size());
    }

    /**
     * add_empties -- takes a list of geometric shapes and adds the following
     * objects to it: <br/>
     * {@code new Circle(0.0);          } <br/>
     * {@code new Cone(0.0, 0.0);       } <br/>
     * {@code new Rectangle(0.0, 0.0);  } <br/>
     * {@code new Sphere(0.0);          }
     */
    static void add_empties(List<GeometricShape<?>> list) {
        list.add(new Circle(0.0));
        list.add(new Cone(0.0, 0.0));
        list.add(new Rectangle(0.0, 0.0));
        list.add(new Sphere(0.0));
    }

    /**
     * Difficult Question: <br/>
     * supersize_list -- takes an array list of some kind of geometric shapes and
     * returns an array list of the same type, with the shapes super sized
     */

    static <T extends GeometricShape<T>> ArrayList<T> supersize_list(ArrayList<T> list) {
        return new ArrayList<T>(list.stream().map(T::supersize).collect(Collectors.toList()));
    }

    // leave this main method as is:
    /**
     * Main entry
     * 
     * @param args {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        // Make a list of rectangles and add some rectangles. ArrayList<Rectangle>
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
        rects.add(new Rectangle(2.0, 3.0));
        rects.add(new Rectangle(5.0, 5.0)); // Make a list of spheres
        ArrayList<Sphere> spheres = new ArrayList<Sphere>();
        spheres.add(new Sphere(10.0));
        spheres.add(new Sphere(50.0));
        spheres.add(new Sphere(0.0));
        // Super-size them
        System.out.println();
        System.out.println("super-sizing a list of rectangles");
        ArrayList<Rectangle> double_rects = supersize_list(rects);
        describe_all(double_rects);
        System.out.println();
        System.out.println("super-sizing a list of spheres");
        ArrayList<Sphere> double_spheres = supersize_list(spheres);
        describe_all(double_spheres);
    }
}
