public class SubstringKMP {
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		String s = constructAnnoyingString('a','b',300,1);
		String t = constructAnnoyingString('a','b',300,100000);
		
//		System.out.println(s);
//		System.out.println(Arrays.toString(buildKMPLookupTable(s)));
//		System.out.println(subNaive(s,t));

		System.out.println(subKMP(s,t));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
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
    
    public static int subNaive(String s, String t) {
		if(s.length() == 0) {
			return -1;
		}
		for(int ti = 0; ti <= t.length() - s.length(); ti++) {
			boolean match = true;
			for(int si = 0; si < s.length(); si++) {
				if(s.charAt(si) != t.charAt(ti+si)) {
					match = false;
					break;
				}
			}
			if(match) {
				return ti;
			}
		}
		return -1;
	}
	
	public static int subKMP(String s, String t) {
		if(s.length() == 0) {
			return -1;
		}
		int[] lookupFail = buildKMPLookupTable(s);
		for(int ti = 0; ti <= t.length() - s.length(); ti++) {
			boolean match = true;
			for(int si = 0; si < s.length(); si++) {
				if(s.charAt(si) != t.charAt(ti+si)) {
					match = false;
					ti += lookupFail[si];
					break;
				}
			}
			if(match) {
				return ti;
			}
		}
		return -1;
	}
	
	public static int[] buildKMPLookupTable(String s) {
		int[] r = new int[s.length()];
		r[0] = 0;
		int i = 1, len = 0;
		while(i < s.length()) { //we want to check if a substring of s matches the start of s
			char c = s.charAt(i);
			if(c == s.charAt(len)) { // if char matches, we increase lenght of the longest common substring
				r[i++] = ++len;
			} else { // if char does not match, we want to backtrace the longest common substring to the previous longest common substring and retest the characters match.
				if(len > 0) {
					len = r[len-1];
				} else {
					r[i++] = len;
				}
			}
		}
		return r;
	}

}
