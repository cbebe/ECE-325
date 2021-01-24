class Point<T extends Number> {
    public T x;
    public T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public boolean samePoint(Point<? extends Number> p) {
        return p.x.doubleValue() == x.doubleValue() && p.y.doubleValue() == y.doubleValue();
    }
}

class Pair<K, V> {
    public K key;
    public V value;

    public Pair(K k, V v) {
        key = k;
        value = v;
    }

    public String toString() {
        return "<" + key + ", " + value + ">";
    }

    public static <K, V> void copyValue(Pair<K, ? super V> p1, Pair<K, ? extends V> p2) {
        p1.value = p2.value;
    }
}

public class MidtermQuestion {
    static void q1() {
        Point<Integer> p1 = new Point<Integer>(3, 7);
        Point<Double> p2 = new Point<Double>(3.0, 7.0);

        System.out.println("p1 == p2? " + p1.samePoint(p2));
        System.out.println("p2 == p1? " + p2.samePoint(p1));
    }

    static void q2() {
        Pair<Integer, Number> p1 = new Pair<>(3, 1002);
        Pair<Integer, Double> p2 = new Pair<>(7, 56.9);

        System.out.println(p1);
        // copies the value of p2 to p1
        Pair.copyValue(p1, p2);
        System.out.println(p1);
    }

    static void q3() {
        // Question: Which design pattern should you use to model the log?

        // Singleton pattern as it provides a single instance from which other
        // modules can create logs
    }

    static void q4() {
        // No, we should first use test-driven development to make sure that all methods
        // are implemented properly and use benchmarking to optimize the calculations

        // THEN we refactor to make the code more maintainable
    }

    public static void main(String[] args) {
        q1();
        q2();
        q3();
        q4();
    }
}