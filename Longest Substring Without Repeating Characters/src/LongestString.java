import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestString {
	private static Set<Object> h = new HashSet<Object>();
	private static StringBuilder s = new StringBuilder();
	static int max = 0;
	static String sb = "";
	
	public static void SubString (String a) {
		for(int i = 0; i < a.length(); i++) {
			if(h.contains(a.charAt(i))) {
				if(max < s.length()) {
					max = s.length();
					sb = s.toString();
				}
				s.replace(0, s.length(),a.charAt(i)+"");
				
			} else {
				s.append(a.charAt(i));
				h.add(a.charAt(i));
			}
		}
		System.out.println(sb);
		System.out.println(sb.length());
	}
	

	public static void main(String[] args) {
		SubString("pwwkew");

	}

}
