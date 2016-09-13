public class PalindromePermutationChecker {

	private static final String s = "Tact coa";
	private static final int MAX_CHAR = 128;

    public static void main(String[] args) {
		System.out.println("String: " + s);
		boolean res = palindromePermutationChecker(s.toCharArray());
		System.out.println("result: " + res);
    }
	
	private static boolean palindromePermutationChecker(char[] s) {
		int charFrequency[] = new int[MAX_CHAR];
		int palindromeSize = s.length;
		for(int i = 0; i < s.length; i++) {
			int c = s[i];
			if(c >= 'A' && c <= 'Z') {
				c += 'a' - 'A';
			}
			if(c == ' ') {
				palindromeSize--;
				continue;
			}
			charFrequency[c]++;
		}
		
		boolean unevenAllowed = (palindromeSize % 2) > 0;
		
		for(int i = 0; i < MAX_CHAR; i++) {
			if (charFrequency[i] % 2 > 0) {
				if (unevenAllowed) {
					unevenAllowed = false;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
