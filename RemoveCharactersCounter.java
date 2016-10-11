import java.util.*;
import java.lang.Math;

public class RemoveCharactersCounter {

//  https://www.topcoder.com/tc?module=ProblemDetail&rd=16798&pm=14356
//
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

//	private static final String a = "qwertyuiopasdfghjklzxcvbnm";
//	private static final String b = "mnbvcxzlkjhgfdsapoiuytrewq";

//	private static final String a = "xxxxxxxvvgabczfedvoocczzxxxxxxx";
//	private static final String b = "xxxxxxxvvaobccczozfedvgzxxxxxxx";

//	private static final String a = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqsssssssssssssssssssssssssssssssssssszzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzoooooooooooooooooooooooooooooooooooojjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaattttttttttttttttttttttttttttttttttttddddddddddddddddddddddddddddddddddddiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiirrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrffffffffffffffffffffffffffffffffffffuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuubbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkppppppppppppppppppppppppppppppppppppyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyccccccccccccccccccccccccccccccccccccxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnngggggggggggggggggggggggggggggggggggglllllllllllllllllllllllllllllllllllleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
//	private static final String b = "lllllllllllllllllllllllllllllllllllloooooooooooooooooooooooooooooooooooojjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjddddddddddddddddddddddddddddddddddddbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzffffffffffffffffffffffffffffffffffffkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhggggggggggggggggggggggggggggggggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeccccccccccccccccccccccccccccccccccccttttttttttttttttttttttttttttttttttttuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyymmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwsssssssssssssssssssssssssssssssssssspppppppppppppppppppppppppppppppppppp";

//	private static final String a = "hvqszojwatdirfubkpycxmngle";
//	private static final String b = "lojdbvrnizfkhgaectuqymxwsp";


//	private static final String a = "wwqqwqqeettt";
//	private static final String b = "tewwqwqttqee";


//	private static final String a = "aaa";
//	private static final String b = "aaa";
	
	
	
	private static class RemoveCharactersCounterAux {
		public String a;
		public String b;
		public int n;
		private StringBuilder touchedChars;
		
		public RemoveCharactersCounterAux(String a, String b) {
			this.a = a;
			this.b = b;
			n = 0;
			touchedChars = new StringBuilder();
			if(a.equals(b)) {
				return;
			}
			performOptimization();
			processCollisionMatrix(generateCollisionMatrix());
			n = touchedChars.toString().length();
			printCurrentResult();
		}
		
		public String getTouchedChars() {
			return touchedChars.toString();
		}
		
		private void performOptimization() {
			for(char c = 'a'; c <= 'z'; c++) {
				int originalLenSA = a.length();
				int originalLenSB = b.length();
				String tmpA = a.replaceAll(String.valueOf(c), "");
				String tmpB = b.replaceAll(String.valueOf(c), "");
				if((originalLenSA - tmpA.length()) != (originalLenSB - tmpB.length())) {
					a = tmpA;
					b = tmpB;
					touchedChars.append(String.valueOf(c));
					n++;
				}
			}
		}
		
		private boolean[][] generateCollisionMatrix() {
			boolean[][] m = new boolean[26][26];
			for(char c = 'a'; c <= 'z'; c++) {
				for(char d = c; d <= 'z'; d++) {
					StringBuilder tmpA = new StringBuilder();
					StringBuilder tmpB = new StringBuilder();
					for(char ca : a.toCharArray()) {
						if(ca == c || ca == d) {
							tmpA.append(ca);
						}
					}
					for(char cb : b.toCharArray()) {
						if(cb == c || cb == d) {
							tmpB.append(cb);
						}
					}
					if(!tmpA.toString().equals(tmpB.toString())) {
						m[c-'a'][d-'a'] = true;
						m[d-'a'][c-'a'] = true;
					}
				}
			} 
			return m;
		}
		
		private void processCollisionMatrix(boolean[][] m) {
			int[] collisions = countCollisionsMatrix(m);
			//System.out.println(Arrays.toString(collisions));
			for(int i = 0; i < m.length; i++) {
				if(collisions[i] <= 0) continue;
				int indexMaxFound = i;
				for(int j = 0; j < m[i].length; j++) {
					if(m[i][j] && collisions[j] > collisions[indexMaxFound]) {
						indexMaxFound = j;						
					}
				}
				for(int j = 0; j < m[i].length; j++) {
					if(m[indexMaxFound][j]) {
						collisions[j]--;
					}
				}
				collisions[indexMaxFound] = 0;
				char c = (char) (indexMaxFound + 'a');
				touchedChars.append(String.valueOf(c));
			}
			return;
		}
		
		private int[] countCollisionsMatrix(boolean[][] m) {
			int collisions[] = new int[26];
			for(int row = 0; row < m.length; row++) {
				for(int col = 0; col < m[row].length; col++) {
					if(m[row][col]) {
						collisions[row]++;
					}
				}
			}
			return collisions;
		}
		
		private void printCurrentResult() {
			String tmpA = a;
			String tmpB = b;
			for(char c : touchedChars.toString().toCharArray()) {
				tmpA = tmpA.replace(String.valueOf(c), "");
				tmpB = tmpB.replace(String.valueOf(c), "");
			}
			System.out.println("Current String A: " + tmpA);
			System.out.println("Current String B: " + tmpB);
		}
	}

    public static void main(String[] args) {
		System.out.println("String A: " + a);
		System.out.println("String B: " + b);
//		System.out.println("result: " + removeCharactersCounterPoor(a,b));
		long startTime = System.currentTimeMillis();
		System.out.println("TopCoder result: " + minimalDistinct(a,b));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time for TopCoder solution: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
		System.out.println();
		System.out.println();
		startTime = System.currentTimeMillis();
		RemoveCharactersCounterAux rcc = new RemoveCharactersCounterAux(a,b);
		duration = System.currentTimeMillis() - startTime;
		System.out.println("Using my method: " + rcc.n);
		System.out.println("Chars touched: " + rcc.getTouchedChars());
		System.out.println();
		System.out.print("Processing time for my new solution: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
	// My first poor & extremely naive attempt to solve this issue.
	/*
	private static int removeCharactersCounterPoor(String a, String b) {
		String lettersTouched = "";
		Set charsUsedA = returnCharsUsedPoor(a);
		Set charsUsedB = returnCharsUsedPoor(b);
		Set<Character> chars = new LinkedHashSet<Character>(charsUsedA);
		Set<Character> temp = new LinkedHashSet<Character>(charsUsedB);
		
		chars.removeAll(charsUsedB);
		temp.removeAll(charsUsedA);
		chars.addAll(temp);
		
		int i = 0;
		for (Character c : chars) {
			a = a.replaceAll(String.valueOf(c), "");
			b = b.replaceAll(String.valueOf(c), "");
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

		return removeCharactersCounterPoor(a,b,chars,i,cache,workingSequence);
	}
	
	private static int removeCharactersCounterPoor(String a, String b, Set<Character> chars, int i, Map<String, String[]> cache, Set<String> workingSequence) {
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
				replacedStrings[0] = currentStrings[0].replaceAll(String.valueOf(c), "");
				replacedStrings[1] = currentStrings[1].replaceAll(String.valueOf(c), "");
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

		return removeCharactersCounterPoor(a, b, chars, i+1, cache, nextWorkingSequence);
	}
	
	private static Set<Character> returnCharsUsedPoor(String s) {
		Set<Character> chars = new HashSet<Character>();
		while(s.length() > 0) {
			char c = s.charAt(0);
			chars.add(c);
			s = s.replaceAll(String.valueOf(c), "");
		}
		return chars;
	}*/

	// Alien powered solution shown on TopCoder
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
				//	System.out.println("SubA: " + a + " , SubB: " + b + " , Char c: " + c + " , Char d: " + d);
		            f[c - 'a'] |= 1 << (d - 'a');
		            f[d - 'a'] |= 1 << (c - 'a');
		        }
		    }
		}
		//for(int i = 0; i < 26; i++) {
		//	System.out.println("letter " + i + " is \t" + Integer.toBinaryString(f[i]));
		//}
		int ans = 0;
		all:
		for (int i = 0; i < 1 << 26; i++) {
			//if(i % 10000 == 0) System.out.println(Integer.bitCount(i));
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
