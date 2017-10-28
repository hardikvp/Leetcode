
public class solution {
	 public static int firstMissingPositive(int[] A) {
		 if (A.length == 0 || A == null) {
			 return 1;
		 }
		 int i = 0;
	        while(i < A.length){
	            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
	            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
	            else i++;
	        }
	        i = 0;
	        while(i < A.length && A[i] == i+1) i++;
	        return i+1;
	    }
	    
	    private static void swap(int[] A, int i, int j){
	        int temp = A[i];
	        A[i] = A[j];
	        A[j] = temp;
	    }
	 
	public static void main(String[] args) {
		int [] a = {5,4,2,1};
		System.out.println(firstMissingPositive(a));

	}

}
