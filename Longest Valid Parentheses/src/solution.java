import java.util.Stack;

public class solution {
	static Stack<Character> b = new Stack<Character>();
	static StringBuilder sb = new StringBuilder();
	public static int longestValidParentheses(String s) {
		char[] a = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (a[i] == '(') {
				b.push(a[i]);
			}
			if (a[i] == ')') {
				if (b.isEmpty()) {
					continue;
				}
				if (b.peek().equals('(')) {
					sb.append(b.pop());
					sb.append(a[i]);
				} else {
					continue;
				}
			}
		}
		
		
		return sb.length();
    
        
    }
	
	
	public static void main(String[] args) {
		
		System.out.println(longestValidParentheses("))("));

	}

}
