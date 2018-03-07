// https://leetcode.com/problems/shortest-palindrome/description/
public class ShortestPalindrome {
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		// String s = constructAnnoyingString('a','b',300,1);
		// String s = "abcd";
		String s = "aacecaaa";
		
		System.out.println(shortestPalindrome(s));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static String shortestPalindrome(String s) {
        if(s.length() < 2) {
            return s;
        }
        String r = reverseString(s);
        String t = s + '#' + r;
        int[] lookupFailKMP = generateKMPLookupTable(t);
        int v = lookupFailKMP[lookupFailKMP.length-1];
        return r.substring(0,s.length()-v) + s;
    }
    
    private static String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    private static int[] generateKMPLookupTable(String s) {
        int[] t = new int[s.length()];
        int i = 1, len = 0;
        while(i < s.length()) {
            if(s.charAt(i) == s.charAt(len)) {
                t[i++] = ++len;
            } else {
                if(len > 0) {
                    len = t[len-1];
                } else {
                    t[i++] = 0;
                }
            }
        }
        return t;
    }
    
    public static String constructAnnoyingString(char main, char sep, int s, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < s; j++) {
				sb.append(main);
			}
			if(i == n-1) {
				sb.append(main);
			}
			sb.append(sep);
		}
		
		return sb.toString();
	}
}
