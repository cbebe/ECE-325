import java.util.Arrays;
import java.util.function.BiFunction;

public class Lambdas {
    interface TwoStringPredicate extends BiFunction<String, String, Boolean> {
        Boolean apply(String s1, String s2);
    }

    static class StringUtils {

        static String betterString(String s1, String s2, TwoStringPredicate p) {
            return p.apply(s1, s2) ? s1 : s2;
        }
    }

    public static int comparE(String s1, String s2) {
        if (s1.charAt(0) == 'e' && s2.charAt(0) != 'e') {
            return -1;
        } else {
            return 0;
        }
    }

    public static void main(String args[]) {
        // Part 1
        String[] strings = new String[] { "so i was chillin at home playing", "eve online", "when suddenly fedora.",
                "oh no its gone again!", "*sob*" };

        System.out.println("Original:");
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nSort by length:");
        Arrays.sort(strings, (s1, s2) -> s1.length() - s2.length());
        Arrays.asList(strings).forEach(System.out::println);

        System.out.println("\nSort by length reversed:");
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
        System.out.println("\nUsing lambda interfaces:");
        String string1 = "ShorterFirst";
        String string2 = "LongerSeconddd";

        String longer = StringUtils.betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
        System.out.println("longer: " + longer);
        String first = StringUtils.betterString(string1, string2, (s1, s2) -> true);
        System.out.println("first: " + first);
    }
}
