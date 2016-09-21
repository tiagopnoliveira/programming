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

//	private static final String a = "abacadaeafagahaiajakalamanaoapaqarasatauavawaxayazbcbdbebfbgbhbibjbkblbmbnbobpbqbrbsbtbubvbwbxbybzcdcecfcgchcicjckclcmcncocpcqcrcsctcucvcwcxcyczdedfdgdhdidjdkdldmdndodpdqdrdsdtdudvdwdxdydzefegeheiejekelemeneoepeqereseteuevewexeyezfgfhfifjfkflfmfnfofpfqfrfsftfufvfwfxfyfzghgigjgkglgmgngogpgqgrgsgtgugvgwgxgygzhihjhkhlhmhnhohphqhrhshthuhvhwhxhyhzijikiliminioipiqirisitiuiviwixiyizjkjljmjnjojpjqjrjsjtjujvjwjxjyjzklkmknkokpkqkrksktkukvkwkxkykzlmlnlolplqlrlsltlulvlwlxlylzmnmompmqmrmsmtmumvmwmxmymznonpnqnrnsntnunvnwnxnynzopoqorosotouovowoxoyozpqprpsptpupvpwpxpypzqrqsqtquqvqwqxqyqzrsrtrurvrwrxryrzstsusvswsxsysztutvtwtxtytzuvuwuxuyuzvwvxvyvzwxwywzxyxzyz";
//	private static final String b = "abcaadaeafagahaiajakalamanaopaaqarasatauavawaxayazbcbdebbfbgbhbibjbkblbmbnbobpbqbrbstbbubvbwbxbybzcdcefccgchiccjkcclmccncocpcqcrcstcuccvcwcxcyczdedfdgdhdidjdkdldmdnoddpdqdrdsdtduvddwdxdydzefegeheiejekelemneoeepeqereseteuveewxeeyezfghffifjfkflfmfnfofpfqfrfsftfuvffwfxfyfzghgigjgkglgmgngogpgqrggsgtguvggwgxgygzhihjkhhlhmhnohhpqhhrhshthuhvhwhxhyhzijikiliminioipiqriisitiuiviwixiyizjkljjmjnjopjjqjrjsjtjuvjjwjxjyjzlkkmknokpkkqkrksktkukvkwkxkykzlmlnollplqlrlstllulvwllxlylzmnommpmqmrmsmtmumvmwmxmymznonpnqrnnsntnunvnwnxnynzpooqorsootouovowxooyzopqprpsptpuvppwxppypzqrqstqquqvqwqxqyqzrstrurrvrwxrryrztssusvswsxsysztutvwttxtytzuvuwuxuyuzvwxvvyvzwxwywzxyxzyz";

//	private static final String a = "qwertyuiopasdfghjklzxcvbnm";
//	private static final String b = "lkjhgfdsamnbvcxzpoiuytrewq";

	private static final String a = "abcdef";
	private static final String b = "abcfed";
	
	// Should be 9.. :(



    public static void main(String[] args) {
		System.out.println("String A: " + a);
		System.out.println("String B: " + b);
//		System.out.println("result: " + removeCharactersCounter(a,b));
		System.out.println("result: " + minimalDistinct(a,b));

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

		Set<String> workingSequence = new HashSet<String>();
		workingSequence.add("");
		Map<String, String[]> cache = new HashMap<String, String[]>();
		String[] strings = new String[2];
		strings[0] = a;
		strings[1] = b;
		cache.put("", strings);	

		return removeCharactersCounter(a,b,chars,i,cache,workingSequence);
	}
	
	private static int removeCharactersCounter(String a, String b, Set<Character> chars, int i, Map<String, String[]> cache, Set<String> workingSequence) {
		if(chars.size() == 0 || a.equals(b)) {
			return i;
		}
		
		Set<String> nextWorkingSequence = new HashSet<String>(); 

				System.out.println("size of workingSequence: " + workingSequence.size());
		
		for (Character c : chars) {
			for (String s : workingSequence) {
				if(s.indexOf(c) >= 0) {
					continue;
				}
				String currentSequence = s + c;
				String[] currentStrings = cache.get(s);
				String[] replacedStrings = new String[2];
				replacedStrings[0] = currentStrings[0].replaceAll("" + c, "");
				replacedStrings[1] = currentStrings[1].replaceAll("" + c, "");
				if(replacedStrings[0].equals(replacedStrings[1])) {
					System.out.println("Solving chars: " + currentSequence);
					return i+1;
				}
				char[] currentSequenceCharArray = currentSequence.toCharArray();
				Arrays.sort(currentSequenceCharArray);
				currentSequence = new String(currentSequenceCharArray);
				nextWorkingSequence.add(currentSequence);
				cache.put(currentSequence, replacedStrings);

			}			
		}

		return removeCharactersCounter(a, b, chars, i+1, cache, nextWorkingSequence);
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


	private static int minimalDistinct(String A, String B) {
		int[] f = new int[26];
		for (char c = 'a'; c <= 'z'; c++) {
		    for (char d = c; d <= 'z'; d++) {
		        String a = "";
		        String b = "";
		        for (char e : A.toCharArray()) {
		            if (e == c || e == d) {
		                a += e;
		            }
		        }
		        for (char e : B.toCharArray()) {
		            if (e == c || e == d) {
		                b += e;
		            }
		        }
		        if (!a.equals(b)) {
		            f[c - 'a'] |= 1 << (d - 'a');
		            f[d - 'a'] |= 1 << (c - 'a');
		        }
		    }
		}
		for(int i = 0; i < 26; i++) {
			System.out.println("letter " + i + " is \t" + Integer.toBinaryString(f[i]));
		}
		int ans = 0;
		all:
		for (int i = 0; i < 1 << 26; i++) {
		    if (Integer.bitCount(i) <= ans) continue;
		    for (int j = 0; j < 26; j++) {
		        if (((i >> j) & 1) == 0) continue;
		        if ((f[j] & i) != 0) {
		            continue all;
		        }
		    }
		    ans = Integer.bitCount(i);
		}
		return 26 - ans;
    }
	
}
