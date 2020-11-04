/**
 * Lab 1: Java Basics, Merge Sort and Maven <br />
 * The {@code MergeSort} class
 * 
 * @author Charles Ancheta
 */
public class MergeSort {
    /**
     * Procedure to merge two sorted arrays
     * 
     * @param arr  {@code int[]} The array to be sorted
     * @param low  {@code int} The lower bound of the low array
     * @param mid  {@code int} The higher bound of the low array (inclusive)/ lower bound of the
     *             high array
     * @param high {@code int} The higher bound of the high array (inclusive)
     */
    static void merge(int[] arr, int low, int mid, int high) {
        // get array sizes for low and high arrays
        int lowSize = mid - low + 1;
        int highSize = high - mid;

        // create copies of low and high arrays
        int lowArray[] = new int[lowSize];
        for (int i = 0; i < lowSize; ++i)
            lowArray[i] = arr[low + i];

        int highArray[] = new int[highSize];
        for (int i = 0; i < highSize; ++i)
            highArray[i] = arr[mid + 1 + i];

        // initialize indices for merging
        int lowIdx = 0, highIdx = 0, arrIdx = low;

        // comparing elements from both arrays and inserting into
        // the original array until one of them runs out
        while (lowIdx < lowSize && highIdx < highSize)
            arr[arrIdx++] = (lowArray[lowIdx] <= highArray[highIdx]) ? lowArray[lowIdx++]
                    : highArray[highIdx++];

        // append the rest of the elements to the array
        while (lowIdx < lowSize)
            arr[arrIdx++] = lowArray[lowIdx++];
        while (highIdx < highSize)
            arr[arrIdx++] = highArray[highIdx++];
    }

    /**
     * Recursive function that implements merge sort
     * 
     * @param arr  {@code int[]} The integer array to be sorted
     * @param low  {@code int} The lower bound of the array to be sorted (0 for first call)
     * @param high {@code int} The higher bound of the array to be sorted (inclusive, arr.length - 1
     *             for first call)
     */
    static void mergeSort(int[] arr, int low, int high) {
        // a single element left, already sorted
        if (high <= low)
            return;

        // split array in half
        int middle = (low + high) / 2;

        // sort the low and high arrays
        mergeSort(arr, low, middle);
        mergeSort(arr, middle + 1, high);
        // merge them both
        merge(arr, low, middle, high);

    }

    /**
     * The merge sort procedure
     * 
     * @param numbers {@code int[]} The integer array to be sorted
     */
    public static int[] sort(int[] numbers) {
        // create a copy of the array and call mergeSort on the whole array
        int[] sorted = numbers.clone();
        mergeSort(sorted, 0, sorted.length - 1);
        return sorted;
    }

    /**
     * Main entry: test the MergeSort
     * 
     * @param args {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 200);
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        numbers = sort(numbers);

        for (int n : numbers)
            System.out.print(n + " ");
        System.out.println();
    }

}
