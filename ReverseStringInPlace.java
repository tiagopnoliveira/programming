import java.util.Arrays;

public class ReverseStringInPlace {

    public static void main(String[] args) {
		System.out.println(reverseStringInPlace("abcd"));
    }
	
	private static String reverseStringInPlace(String s) {
		char[] l = s.toCharArray();
		for(int i = 0; i < l.length/2; i++) {
			int o = l.length-i-1;
			l[i] ^= l[o];
			l[o] ^= l[i];
			l[i] ^= l[o];
		}
		return new String(l);
	}
}
