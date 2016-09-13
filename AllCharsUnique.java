public class AllCharsUnique {

	private static String s = "qwertyuioasdf576ghjkzxcvbnm-/.>";

    public static void main(String[] args) {
		System.out.println("String: " + s);
		boolean res = allcharsUnique(s);
		System.out.println("result: " + res);
    }
	
	// Only works for a-z lowercase input
	private static boolean allCharsUniqueBitVector(String s) {
		int bv = 0;
		for(int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			if(((1 << c) & bv) > 0) {
				return false;
			}
			bv |= 1 << c;
		}
		return true;
	}

	private static boolean allcharsUnique(String s) {
		boolean usedChar[] = new boolean[256];
		for(int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			if(c > 255) {
				// uh oh
				return false;
			}
			if(usedChar[c]) {
				return false;
			}
			usedChar[c] = true;
		}
		return true;
	}
	
}
