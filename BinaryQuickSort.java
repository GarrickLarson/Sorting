import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Created by glars_v5g84e9 on 2/23/2017.
 */
public class BinaryQuickSort {
    static int n;
    static int k;
    static int counter;
    static ArrayList<String> sorted;

    public static void main(String[] args) {
        // n = number of string elements
        n = 100000;
        // k = length of each string element
        k = 30;
        counter = 0;

        sorted = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        ArrayList<String> unsortedArray1 = new ArrayList<>();
        // create random bitstrings to be sorted
        // n number of strings
        for (int i = 0; i < n; i++) {
            // k number of characters
            for (int j = 0; j < k; j++) {
                sb.append(rand.nextInt(2));
            }
            unsortedArray1.add(sb.toString());
            sb.delete(0, k + 1);
        }

        sort(0, unsortedArray1);

        // print out sorted elements (comment out if not interested
        for (String s: sorted) {
            System.out.println(s);
        }

        System.out.println("N: " + n);
        System.out.println("K: " + k);
        System.out.println("COUNTER: " + counter);
    }

    private static void sort(int charIndex, ArrayList<String> sArray) {
        ArrayList<String> low = new ArrayList<>();
        ArrayList<String> high = new ArrayList<>();

        if (sArray.size() <= 1 || charIndex >= k - 1) {
            for (String s : sArray) {
                sorted.add(s);
                counter++;
            }
        }else{

            for (String s : sArray) {
                if (s.charAt(charIndex) == '1') {
                    high.add(s);
                } else {
                    low.add(s);
                }
                counter++;
            }
            charIndex++;
            if (low.size() > 0) {
                sort(charIndex, low);
            }
            if (high.size() > 0) {
                sort(charIndex, high);
            }
        }
    }
}
