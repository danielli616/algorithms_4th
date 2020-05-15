import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    private QuickSort() {
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;

        while (true) {
            while (less(a[i], a[lo])) {
                if (i == hi) break;
                i++;
            }

            while (less(a[lo], a[j])) {
                if (j == lo) break;
                j--;
            }
            if (i >= j) break;  //这里用 i > j 可以吗？
            exch(a, i, j);
        }
        exch(a, lo, j);

        return j;
    }


    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int p = partition(a, lo, hi);
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    public static void sort(Comparable[] a) {

        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);

    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        QuickSort.sort(a);
        show(a);
    }
}
