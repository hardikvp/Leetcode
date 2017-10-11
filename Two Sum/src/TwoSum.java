import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TwoSum {
	static HashSet<Integer> h = new HashSet<Integer>();
	static List<Integer> b = new ArrayList<Integer>();
	public static List<Integer> twoSum (int[] a, int target) {
		for (int i: a) {
			int comp = target - i;
			if(h.contains(comp)) {
				b.add(i);
				b.add(comp);
			} else {
				h.add(i);
			}
		}
		return b;
		
	}
	public static void main(String[] args) {
		int [] a = {2,7,11,15};
		int target = 22;
		for (int i :twoSum(a, target)) {
			System.out.println(i);
			System.out.println(5/2);
		};
		

	}

}
