import java.util.List;
import java.util.ArrayList;
import mergesort.MergeSort;

/**
 * Lab 2: Debugging with an IDE and Prefix Tree) <br />
 * The {@code ResearchGroup} class uses a 2D array to store the names of group members
 */


public class ResearchGroups {

    /**
     * Returns list as a string of comma-separated list
     * 
     * @param list List object to be converted to String
     */

    public static String listNumberString(List<Integer> list) {
        String listStr = list.toString();
        return listStr.substring(1, listStr.length() - 1);
    }

    /**
     * Search a person to check whether he/she is in the groups
     * 
     * @param groups {@code String[]} The 2D array of groups to be searched
     * @param name   {@code String} name of the person
     */
    public static void searchMember(String[][] groups, String name) {
        boolean existsInList = false;
        // use list to keep track of membership/leadership in groups
        List<Integer> groupNumbers = new ArrayList<>();
        List<Integer> leaderInGroup = new ArrayList<>();

        // iterate on every element
        for (int i = 0; i < groups.length; ++i) {
            for (int j = 0; j < groups[i].length; ++j) {
                if (groups[i][j].equals(name)) {
                    existsInList = true;
                    groupNumbers.add(i);
                    if (j == 0)
                        leaderInGroup.add(i);
                }
            }
        }

        if (existsInList) {
            System.out.print(name + " exists in the list: ");
            System.out.print("Part of group(s) " + listNumberString(groupNumbers));
            if (!leaderInGroup.isEmpty()) {
                System.out.print(", Leader of group(s) " + listNumberString(leaderInGroup));
            }
            System.out.println();
        } else {
            System.out.println(name + " does not exist in the list");
        }
    }

    /**
     * Sort groups by number of members <br />
     * 
     * @param groups (<code>String[][]</code>) The 2D array of groups to be sorted
     */
    public static void sortGroups(String[][] groups) {
        // create a hash by mapping each group to an integer
        int[] hash = new int[groups.length];
        for (int i = 0; i < groups.length; ++i)
            hash[i] = groups[i].length * 100 + i;

        MergeSort.mergeSort(hash, 0, hash.length - 1);

        // clone array to use as hash reference
        String[][] groupCopy = groups.clone();

        for (int i = 0; i < groups.length; ++i)
            groups[i] = groupCopy[hash[i] % 100];
    }

    /**
     * Returns a string representation of the group
     * 
     * @param group {@code String[]} group members' names
     * @return {@code String}string representation of group
     */
    public static String groupString(String[] group) {
        String groupStr = "{";
        for (int i = 0; i < group.length - 1; ++i) {
            groupStr += group[i] + ", ";
        }
        return groupStr + group[group.length - 1] + "}";
    }

    /**
     * Main entry
     * 
     * @param args {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[][] groups = {{"Bob", "Carol", "Eric", "Matt"}, // 0
                {"Jim", "Lucy", "Terry", "Brenda", "Ben"}, // 1
                {"Susan", "Brad", "Jim"}, // 2
                {"Sue", "Wendy", "Sam"}, // 3
                {"Kate", "Jack", "James", "Sydney"}, // 4
                {"Mohammad", "Tim", "Kian"}, // 5
                {"Emma", "Carol"}, // 6
                {"Nick", "Osama", "Harry", "Ben"}, // 7
                {"Mary", "John", "Ricky"}}; // 8

        ResearchGroups.searchMember(groups, "Jim");
        ResearchGroups.searchMember(groups, "Lucy");
        ResearchGroups.searchMember(groups, "John Doe");

        System.out.println("--------\nUNSORTED\n--------");
        for (int i = 0; i < groups.length; ++i)
            System.out.println(groupString(groups[i]) + " length: " + groups[i].length);

        ResearchGroups.sortGroups(groups);

        System.out.println("--------\nSORTED\n--------");
        for (int i = 0; i < groups.length; ++i)
            System.out.println(groupString(groups[i]) + " length: " + groups[i].length);
    }

}
