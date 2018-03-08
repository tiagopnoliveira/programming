// https://leetcode.com/problems/reverse-integer/description/
public class ReverseInteger {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		int x = -1234;
		int edge = 1534236469; // should return 0 as result will overflow 32 bits.
		System.out.println(reverse(x));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }

    public static int reverse(int x) {
        int oom = findOOM(x);
        int res = 0;
        while(x != 0) {
            int v = (x % 10) * ((int) Math.pow(10,--oom));
            if(oom > 9 || (oom == 9 && Math.abs(x % 10) > 2) || checkOverflow(res,v)) {
                return 0;
            }
            res += v;
            if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }
    
    private static int findOOM(int x) {
        int n = 0;
        while(x != 0) {
            n++;
            x /= 10;
        }
        return n;
    }
    
    private static boolean checkOverflow(int a, int b) {
        if(a > 0 && b > 0) {
            return a > (Integer.MAX_VALUE - b);
        } else if(a < 0 && b < 0) {
            return a < (Integer.MIN_VALUE - b);
        }
        return false;
    }   
}
