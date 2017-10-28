import java.util.ArrayList;
import java.util.List;

public class solution {
	public static List<Integer> findSubstring(String s, String[] words) {
		int count = 0;
		int n = 0;
		int i = words.length;
		int k = 0;
		List<Integer> a = new ArrayList<Integer>();
		if(s.length() == 0 || words.length == 0) {
			System.out.println("hi");
			return a;
		}
		while (n < s.length()) {
			StringBuilder sb  = new StringBuilder();
			int j = words[0].length();
			for (k = n; k <= s.length();k++) {
				if (sb.length() < j ) {
					sb.append(s.charAt(k));
				} else {
					n = k;
					break;
				}
			}
			for(int l = 0; l < i; l++) {
				if(words[l].equals(sb.toString())) {
					count++;
				}
			}
			if (count == i) {
				a.add(k - ((i*j)));
				count = 0;
			}
			if (s.length() - n  < j) {
				return a;
			}
			
		}
		return a;
    }

	public static void main(String[] args) {
		String[] s = {"foo", "bar"};
		System.out.println(findSubstring("foobarbarfoo", s));
		// TODO Auto-generated method stub

	}

}
