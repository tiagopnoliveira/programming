import java.util.Arrays;

public class LongestSubstringWithKDistinctChars {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(longestSubstringWithKDistinctChars("abcbbddddda", 3));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }

	public static String longestSubstringWithKDistinctChars(String s, int k) {
		int charsUsed[] = new int[26];
		Arrays.fill(charsUsed, -1);
		int maxEnd = 0, maxStart = 0, cur = 0, u = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int prevPos = charsUsed[c-'a'];
			charsUsed[c-'a'] = i;
			if(prevPos >= 0) {
				continue;
			}
			if(u < k) {
				u++;
				continue;
			}
			int posLastUsedChar = findPosLastUsedChar(charsUsed);
			charsUsed[s.charAt(posLastUsedChar)-'a'] = -1;
			if((maxEnd - maxStart) < (i-posLastUsedChar)) {
				maxStart = cur;
				maxEnd = i;
			}
			cur = posLastUsedChar+1;
		}
		if((maxEnd - maxStart) < (s.length()-cur)) {
			maxStart = cur;
			maxEnd = s.length();
		}
		return s.substring(maxStart, maxEnd);
	}
	
	public static int findPosLastUsedChar(int[] charsUsed) {
		int min = -1;
		for(int i = 0; i < charsUsed.length; i++) {
			if(min < 0 || (charsUsed[i] >= 0 && charsUsed[i] < min)) {
				min = charsUsed[i];
			}
		}
		return min;
	}
}
