
public class sort {
    static int count =0;
    
    public sort() {
        
    }
    public static void sortMethod (Comparable[] a) {
        int n = a.length;
        for (int i =0; i < n ; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    Object temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = (Comparable) temp;
                    count ++;
                }
                if (isSorted(a) == true) {
                    break;
                }
            }
            isSorted(a);
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println(count);
    }
    private static boolean isSorted(Comparable[] a) {
        for (int x =0; x < a.length-1 ; x++) {
            if(a[x].compareTo(a[x+1]) > 0) {
                return false;
                }
            }
        return true;
        
    }
    public static void main(String[] args) {
        Comparable[] a = new Comparable [args.length];
        for (int i= 0; i< args.length ; i ++) {
            a[i] = (args[i]);
        }
        sort.sortMethod(a);
    }
}
