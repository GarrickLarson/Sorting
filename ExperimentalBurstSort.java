import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ExperimentalBurstSort {
    static LinkedList<String> sorted;
     /*
     Counter keeps track of O() time complexity by counting the number of operations
     This algorithm runs in O(n*logn) time complexity on average
     and in O(k*n) time complexity on worst case

     While being faster time-wise than RadixSort, this algorithm uses more memory

     The counter keeps track of the number of times the elements are accessed
     and the number of LinkedLists instantiated
     */
    static int counter;
    // n = number of string elements,
    static int n = 100000;
    // comment out lines 77-80 and try n = 10000000

    // k = length of each string element
    static int k = 10;
    /*
    endCounterX keeps track of how many elements are added to the sorted LinkedList at each
    X level of the algorithm
     */
    static int endCounter1;
    static int endCounter2;
    static int endCounter3;
    static int endCounter4;
    static int endCounter5;
    static int endCounter6;
    static int endCounter7;
    static int endCounter8;
    static int endCounter9;
    static int endCounter10;

    public static void main(String[] args) {

        counter = 0;
        endCounter1 = 0;
        endCounter2 = 0;
        endCounter3 = 0;
        endCounter4 = 0;
        endCounter5 = 0;
        endCounter6 = 0;
        endCounter7 = 0;
        endCounter8 = 0;
        endCounter9 = 0;
        endCounter10 = 0;

        sorted = new LinkedList<>();
        LinkedList<String> unsorted = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        // create random strings of letters to be sorted
        // n number of strings
        System.out.print("Creating " + n + " Random Strings...");
        for (int i = 0; i < n; i++) {
            // k number of characters per string
            for (int j = 0; j < k; j++) {
                sb.append((char)(rand.nextInt(26) + 65));
            }
            unsorted.add(sb.toString());
            sb.delete(0, 11);
        }
        System.out.println("Done");

        System.out.print("Starting Sorting...");
        sort(0, unsorted);
        System.out.println("Done");


        /*
            Comment out the following code segment for n > 100,000
            Warning: if uncommented for n > 100,000 printing may take awhile
         */
//         print out sorted elements
        Object[] sortedToArray = sorted.toArray();
        for (int i = 0; i < sortedToArray.length; i++) {
            System.out.println(sortedToArray[i]);
        }

        System.out.println();
        System.out.println("N: " + n);
        System.out.println("K: " + k);
        System.out.println("Counter: " + counter);
        System.out.println("Percent Improvement over RadixSort: " + (100 - 100*(double)counter / (2 * k * n)) + "%");
        System.out.println("EndCounter1: " + endCounter1 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1)/(double)n);
        System.out.println("EndCounter2: " + endCounter2 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2)/(double)n);
        System.out.println("EndCounter3: " + endCounter3 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3)/(double)n);
        System.out.println("EndCounter4: " + endCounter4 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4)/(double)n);
        System.out.println("EndCounter5: " + endCounter5 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4 - endCounter5)/(double)n);
        System.out.println("EndCounter6: " + endCounter6 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4 - endCounter5 - endCounter6)/(double)n);
        System.out.println("EndCounter7: " + endCounter7 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4 - endCounter5 - endCounter6 - endCounter7)/(double)n);
        System.out.println("EndCounter8: " + endCounter8 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4 - endCounter5 - endCounter6 - endCounter7 - endCounter8)/(double)n);
        System.out.println("EndCounter9: " + endCounter9 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4 - endCounter5 - endCounter6 - endCounter7 - endCounter8 - endCounter9)/(double)n);
        System.out.println("EndCounter10: " + endCounter10 + " | Percentage of elements still being sorted: " + 100*(n-endCounter1 - endCounter2 - endCounter3 - endCounter4 - endCounter5 - endCounter6 - endCounter7 - endCounter8 - endCounter9 - endCounter10)/(double)n);
        System.out.println("Total in Leaves: " + (endCounter1 + endCounter2 + endCounter3 + endCounter4 + endCounter5 + endCounter6 + endCounter7 + endCounter8 + endCounter9 + endCounter10));
    }

    private static void sort(int charIndex, LinkedList<String> ll) {
        // if a leaf node
        if (ll.size() <= 1 || charIndex >= ll.getFirst().length()) {
            for (String s: ll) {
                sorted.addLast(s);
                counter++;
            }

            // add elements to count of elements sorted at that point
            if (charIndex >= ll.getFirst().length()) {
                endCounter10+= ll.size();
            } else if (charIndex == ll.getFirst().length()-1) {
                endCounter9++;
            } else if (charIndex == ll.getFirst().length()-2) {
                endCounter8++;
            } else if (charIndex == ll.getFirst().length()-3) {
                endCounter7++;
            } else if (charIndex == ll.getFirst().length()-4) {
                endCounter6++;
            } else if (charIndex == ll.getFirst().length()-5) {
                endCounter5++;
            } else if (charIndex == ll.getFirst().length()-6) {
                endCounter4++;
            } else if (charIndex == ll.getFirst().length()-7) {
                endCounter3++;
            } else if (charIndex == ll.getFirst().length()-8) {
                endCounter2++;
            } else if (charIndex == ll.getFirst().length()-9) {
                endCounter1++;
            }
        } else {
            @SuppressWarnings("unchecked") LinkedList<String>[] buckets = new LinkedList[26];
            counter++;

            // sort elements into buckets by character at charIndex
            for (String s: ll) {
                int charBucketIndex = ((int) s.charAt(charIndex)) - 65;
                if (buckets[charBucketIndex] == null) {
                    buckets[charBucketIndex] = new LinkedList<String>();
                    counter++;
                }
                buckets[charBucketIndex].addFirst(s);
                counter++;
            }
            charIndex++;

            // recursively sort each bucket
            for (LinkedList<String> ll2: buckets) {
                if (ll2 != null) {
                    sort(charIndex, ll2);
                }
            }
        }
    }
}
