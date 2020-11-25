import java.util.Arrays;
import java.util.function.BiFunction;

public class Lambdas {
    /**
     * Lambda interface for comparing two strings
     */
    interface TwoStringPredicate extends BiFunction<String, String, Boolean> {
        Boolean apply(String s1, String s2);
    }

    /**
     * Inner class for betterString
     */
    static class StringUtils {

        /**
         * Returns the better string given a {@code TwoStringPredicate}
         * 
         * @param s1 {@code String} the first string
         * @param s2 {@code String} the second string
         * @param p  {@code TwoStringPredicate} the predicate to judge for the better
         *           string
         * @return {@code String} the first if the predicate returns true, the second
         *         otherwise
         */
        static String betterString(String s1, String s2, TwoStringPredicate p) {
            return p.apply(s1, s2) ? s1 : s2;
        }
    }

    /**
     * Helper method to sort Strings that start with 'e' first
     * 
     * @param s1 {@code String} the first string
     * @param s2 {@code String} the second string
     * @return {@code int} the result of the comparison
     */
    public static int comparE(String s1, String s2) {
        if (s1.charAt(0) == 'e' && s2.charAt(0) != 'e') {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Main method of the class
     * 
     * @param args {@code String[]} args
     */
    public static void main(String args[]) {
        // Part 1
        String[] strings = new String[] { "so i was chillin at home playing", "eve online", "when suddenly fedora.",
                "oh no its gone again!", "*sob*" };

        System.out.println("Original:");
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nSort from shortest to longest:");
        Arrays.sort(strings, (s1, s2) -> s1.length() - s2.length());
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nSort from longest to shortest:");
        Arrays.sort(strings, (s1, s2) -> s1.length() - s2.length());
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nSort alphabetically by first character only:");
        Arrays.sort(strings, (s1, s2) -> s1.charAt(0) - s2.charAt(0));
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nStrings that contain 'e' first:");
        Arrays.sort(strings, (s1, s2) -> {
            if (s1.charAt(0) == 'e' && s2.charAt(0) != 'e') {
                return -1;
            } else {
                return 0;
            }
        });
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nStrings that contain 'e' first but with a helper method:");
        Arrays.sort(strings, (s1, s2) -> comparE(s1, s2));
        Arrays.asList(strings).forEach(System.out::println);

        // Part 2
        System.out.println("\nUsing lambda interface:");
        String string1 = "ShorterFirst";
        String string2 = "LongerSecondd";

        String longer = StringUtils.betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
        System.out.println("longer: " + longer);
        String first = StringUtils.betterString(string1, string2, (s1, s2) -> true);
        System.out.println("first:  " + first);
    }
}
