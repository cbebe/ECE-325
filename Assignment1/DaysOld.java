import java.util.regex.Pattern;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Assignment 1: Using standard libraries <br/>
 * Charles Ancheta, 1581672
 * 
 * Calculate age in days
 */
public class DaysOld {

    /**
     * Creates a string with a given LocalDate into the given format
     * 
     * @param {@code LocalDate} birthday LocalDate object of birthday to be printed
     * @return {@code String} String formatted according to the assignment
     */
    public static String dateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMMM d y"));
    } // public static String dateString(LocalDate date)

    /**
     * Parses date with yyyy-MM-dd format
     * 
     * @param date {@code String} the date to be parsed
     * @return {@code LocalDate} LocalDate object described in the string, null if invalid
     */
    public static LocalDate parseLocalDate(String date) {
        // check if date format is valid
        Pattern p = Pattern.compile("[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}");
        if (!p.matcher(date).matches())
            return null;

        // turn the String[] into a Stream<String> to perform operations on the elements one by one
        // Transform each String into an Integer and turn the Stream<Integer> into an Integer[]
        Integer[] params = Arrays.stream(date.split("-")).map(d -> Integer.parseInt(d))
                .toArray(Integer[]::new);

        // I don't want to handle invalid date ranges and leap years myself
        try {
            return LocalDate.of(params[0], params[1], params[2]);
        } catch (Exception e) {
            return null;
        }
    } // public static LocalDate parseLocalDate(String date)

    /**
     * Calculate how many days between today and the date, and print them out
     * 
     * @param birthday {@code String} The start date
     */
    public static void days(String birthday) {
        // LocalDate.parse(String) cannot parse yyyy-M-d
        LocalDate birthDate = parseLocalDate(birthday);
        if (birthDate == null) {
            System.out.println("Invalid date");
            return;
        }

        LocalDate today = LocalDate.now();

        System.out.printf("Birthday: %s; today: %s -- ", dateString(birthDate), dateString(today));

        if (birthDate.isAfter(today)) {
            System.out.println("Wrong birthday!");
        } else {
            long daysBetween = ChronoUnit.DAYS.between(birthDate, today);
            System.out.printf("You are %d days old.\n", daysBetween);
        }
    } // public static void days(String birthday)

    /**
     * Main entry
     * 
     * @param args {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        days("2000-1-1");
        days("3000-1-1"); // This is a wrong birthday
        // days("yyyy-MM-dd");
        days("2000-9-12"); // my birthday
        days("South America"); // invalid format
        days("4206-94-20"); // invalid range
        days("2001-02-29"); // invalid range - no leap year
    }
}
