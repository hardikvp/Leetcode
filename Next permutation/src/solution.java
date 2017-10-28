import java.util.Arrays;

public class solution {
	public static int [] nextPermutation(int[] nums) {
        int n = nums.length - 1;
        int m = 0;
        outerloop:
        for (; n > 0; n --) {
            for (int j = n-1; j >= 0; j --) {
                if (nums[n] > nums [j]) {
                    m = nums[n];
                    nums[n] = nums[j];
                    nums[j] = m;
                    m = j;
                    break outerloop;
                }
            }
        }
            
        Arrays.sort(nums, m+1 , nums.length - 1);
        return nums;
    }        

	public static void main(String[] args) {
		int [] n = {4,3,1,2};
		System.out.println(nextPermutation(n)[n.length-1]);
		// TODO Auto-generated method stub

	}

}
