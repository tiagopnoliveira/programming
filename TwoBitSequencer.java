import java.math.BigInteger;

// https://careercup.com/question?id=5735144200601600
public class TwoBitSequencer {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		//~ for(int i = 1; i <= 10; i++) {
			//~ System.out.println("for n = " + i + " , v = " + getNthTwoBitSequencer(i));
		//~ }
		System.out.println("for n = " + 1000000 + " , v = " + getNthTwoBitSequencer(1000000));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static BigInteger getNthTwoBitSequencer(int n) {
		if(n < 1) {
			return BigInteger.ZERO;
		}
		for(int l = 1; l < Integer.MAX_VALUE; l++) {
			for(int r = 0; r < l; r++) {
				n--;
				if(n == 0) {
					BigInteger vl = new BigInteger("2");
					BigInteger vr = new BigInteger("2");
					vl = vl.pow(l);
					vr = vr.pow(r);
					return vl.add(vr);
				}
			}
		}
		return BigInteger.ZERO;
	}

}
