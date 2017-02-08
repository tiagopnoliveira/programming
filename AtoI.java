public class AtoI {

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(atof("56.32"));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int atoi(String s) {
		int mult = 1;
		int res = 0;
		for(int i = s.length()-1; i >= 0; i--) {
			char c = s.charAt(i);
			if(c < '0' || c > '9') {
				if(c == '-' && i == 0) {
					return res * -1;
				}
				// return error
				return 0;
			}
			int v = c - '0';
			res += v * mult;
			mult *= 10;
		}		
		return res;
	}

    public static double atof(String s) {
		double res = 0;
		boolean negative = false;
		boolean pastDecimalInflexion = false;
		int mult = 1;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c < '0' || c > '9') {
				if(c == '-' && i == 0) {
					negative = true;
					continue;
				}
				if(c == '.') {
					pastDecimalInflexion = true;
					continue;
				}
				// return error
				return 0;
			}
			int v = c - '0';
			if(pastDecimalInflexion) {
				mult *= 10;
				res += ((double) v / mult);
			} else {
				res *= 10;
				res += v;
			}
		}		
		return res;
	}
}
