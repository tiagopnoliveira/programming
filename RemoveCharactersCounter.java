import java.util.*;

public class RemoveCharactersCounter {

//	Constraints
//	-	A will contain between 1 and 1,000 elements, inclusive.
//	-	B will contain between 1 and 1,000 elements, inclusive.
//	-	Each character in A will be a lowercase English letter (i.e. 'a'-'z').
//	-	Each character in B will be a lowercase English letter (i.e. 'a'-'z').
//	 
//	Examples
//	0)	
//	    	
//	"acabc"
//	"accabcc"
//	Returns: 1
//	One optimal solution here is to remove characters at 0-based indices 1 and 6 in the string B. Both removed characters are 'c's. This changes the string B from "accabcc" to "acabc" and makes the two strings equal. As we only removed one distinct character, the correct return value is 1.
//	1)	
//	    	
//	"aabbcc"
//	"ccbbaa"
//	Returns: 2
//	One optimal solution is to remove the first four characters of A and the last four characters of B. After this change, both strings become equal to "cc". We removed two distinct characters: some 'a's and some 'b's.
//	2)	
//	    	
//	"aaaabc"
//	"bcaaaaa"
//	Returns: 1
//	The optimal solution here is to remove all nine 'a's. Note that we are only interested in minimizing the number of distinct characters among the characters we remove. The total number of removed characters may be arbitrary.
//	3)	
//	    	
//	"abcde"
//	"abcde"
//	Returns: 0
//	4)	
//	    	
//	"abcdefghijklm"
//	"nopqrstuvwxyz"
//	Returns: 26
//	We need to remove all of them.
//	

	private static final String a = "abacadaeafagahaiajakalamanaoapaqarasatauavawaxayazbcbdbebfbgbhbibjbkblbmbnbobpbqbrbsbtbubvbwbxbybzcdcecfcgchcicjckclcmcncocpcqcrcsctcucvcwcxcyczdedfdgdhdidjdkdldmdndodpdqdrdsdtdudvdwdxdydzefegeheiejekelemeneoepeqereseteuevewexeyezfgfhfifjfkflfmfnfofpfqfrfsftfufvfwfxfyfzghgigjgkglgmgngogpgqgrgsgtgugvgwgxgygzhihjhkhlhmhnhohphqhrhshthuhvhwhxhyhzijikiliminioipiqirisitiuiviwixiyizjkjljmjnjojpjqjrjsjtjujvjwjxjyjzklkmknkokpkqkrksktkukvkwkxkykzlmlnlolplqlrlsltlulvlwlxlylzmnmompmqmrmsmtmumvmwmxmymznonpnqnrnsntnunvnwnxnynzopoqorosotouovowoxoyozpqprpsptpupvpwpxpypzqrqsqtquqvqwqxqyqzrsrtrurvrwrxryrzstsusvswsxsysztutvtwtxtytzuvuwuxuyuzvwvxvyvzwxwywzxyxzyz";
	private static final String b = "abcaadaeafagahaiajakalamanaopaaqarasatauavawaxayazbcbdebbfbgbhbibjbkblbmbnbobpbqbrbstbbubvbwbxbybzcdcefccgchiccjkcclmccncocpcqcrcstcuccvcwcxcyczdedfdgdhdidjdkdldmdnoddpdqdrdsdtduvddwdxdydzefegeheiejekelemneoeepeqereseteuveewxeeyezfghffifjfkflfmfnfofpfqfrfsftfuvffwfxfyfzghgigjgkglgmgngogpgqrggsgtguvggwgxgygzhihjkhhlhmhnohhpqhhrhshthuhvhwhxhyhzijikiliminioipiqriisitiuiviwixiyizjkljjmjnjopjjqjrjsjtjuvjjwjxjyjzlkkmknokpkkqkrksktkukvkwkxkykzlmlnollplqlrlstllulvwllxlylzmnommpmqmrmsmtmumvmwmxmymznonpnqrnnsntnunvnwnxnynzpooqorsootouovowxooyzopqprpsptpuvppwxppypzqrqstqquqvqwqxqyqzrstrurrvrwxrryrztssusvswsxsysztutvwttxtytzuvuwuxuyuzvwxvvyvzwxwywzxyxzyz";
	
	// Should be 9.. :(

    public static void main(String[] args) {
		System.out.println("String A: " + a);
		System.out.println("String B: " + b);
		System.out.println("result: " + removeCharactersCounter(a,b));
    }
	
	private static int removeCharactersCounter(String a, String b) {
		String lettersTouched = "";
		Set charsUsedA = returnCharsUsed(a);
		Set charsUsedB = returnCharsUsed(b);
		Set<Character> chars = new LinkedHashSet<Character>(charsUsedA);
		Set<Character> temp = new LinkedHashSet<Character>(charsUsedB);

		
		chars.removeAll(charsUsedB);
		temp.removeAll(charsUsedA);
		chars.addAll(temp);
		
		int i = 0;
		for (Character c : chars) {
			a = a.replaceAll("" + c, "");
			b = b.replaceAll("" + c, "");
			i++;
			lettersTouched += c;
		}
		
		chars.clear();
		chars.addAll(charsUsedA);
		chars.retainAll(charsUsedB);
		
		return removeCharactersCounter(a,b,chars,i, lettersTouched);
	}
	
	private static int removeCharactersCounter(String a, String b, Set<Character> chars, int i, String lettersTouched) {
		if(chars.size() == 0 || a.equals(b)) {
			System.out.println(lettersTouched);
			return i;
		}
		
		Map<Character, String[]> cache = new HashMap<Character , String[]>();
		
		for (Character c : chars) {
			String[] replacedStrings = new String[2];
			replacedStrings[0] = a.replaceAll("" + c, "");
			replacedStrings[1] = b.replaceAll("" + c, "");
			if(replacedStrings[0].equals(replacedStrings[1])) {
				lettersTouched += "" + c;
				System.out.println(lettersTouched + c);
				return i+1;
			}
			cache.put(c, replacedStrings);
		}

		for (Character c : chars) {
			Set<Character> newChars = new LinkedHashSet<Character>(chars);
			newChars.remove(c);
			return removeCharactersCounter(cache.get(c)[0], cache.get(c)[1], newChars, i+1, lettersTouched + c);
		}
		return -1;
	}
	
	private static Set<Character> returnCharsUsed(String s) {
		Set<Character> chars = new HashSet<Character>();
		while(s.length() > 0) {
			char c = s.charAt(0);
			chars.add(c);
			s = s.replaceAll("" + c, "");
		}
		return chars;
	}
	
}
