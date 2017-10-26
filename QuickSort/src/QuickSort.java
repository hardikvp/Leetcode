
public class QuickSort {
    private static int[] a;
    private static int number;
    
    public static void sort(int[] values) {
        a = values;
        number = values.length;
        shuffle(a);
        sort(a,0, number-1);
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            }
        }
   
    private static void sort(int[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
   
    private static void exch(int[] a2, int i, int j) {
        int swap = a2[i];
        a2[i] = a2[j];
        a2[j] = swap;
    }

    private static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }
        
    private static boolean less(int v, int a) {
        return v- a < 0;
    }
    public static void main(String[] args) {
        int[] a = new int [args.length];
        for (int i= 0; i< args.length ; i ++) {
            a[i] = Integer.parseInt(args[i]);
        }
        QuickSort.sort(a);
    }
        // TODO Auto-generated method stub

}
