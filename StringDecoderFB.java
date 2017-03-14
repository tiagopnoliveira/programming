import java.util.*;

// https://careercup.com/question?id=5187607743954944
public class StringDecoderFB {
	
	private static final String s = "001111771110112999";

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		HashSet<String> res = combinatorStringDecoderFB(s);
		for(String s : res) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println(res.size());
		System.out.println();
		System.out.println(stringDecoderFB(s));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int stringDecoderFB(String s) {
		int acc = 0;
		int res = 1;
		int prevRes = 0;
		boolean alreadyDoubled = false;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int v = atoi(c);
			if((acc*10 + v) > 9 && (acc*10 + v) <= 27) {
				int t = res;
				if(alreadyDoubled) {
					res += prevRes;
				} else {
					alreadyDoubled = true;
					res *= 2;
				}
				prevRes = t;
			} else {
				alreadyDoubled = false;
			}
			acc = v;
		}
		return res;
	}
    
    public static HashSet<String> combinatorStringDecoderFB(String s) {
		HashSet<String> res = new HashSet<String>();
		combinatorStringDecoderFB(s,"",0,res);
		return res;
	}

    public static void combinatorStringDecoderFB(String s, String r, int acc, HashSet<String> res) {
		if(s.length() == 0) {
			if(acc == 0) {
				res.add(r);
			}
			return;
		}
		char c = s.charAt(0);
		acc += atoi(c);
		if(acc > 27) {
			return;
		}
		if(acc > 0) {
			combinatorStringDecoderFB(s.substring(1,s.length()), r + (char) (acc-1 + 'a'), 0, res);
		}
		combinatorStringDecoderFB(s.substring(1,s.length()), r, acc*10, res);
	}
	
	private static int atoi(char c) {
		return (int) c-'0';
	}

}
