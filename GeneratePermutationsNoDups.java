import java.util.Set;
import java.util.HashSet;

public class GeneratePermutationsNoDups {

	private static String s = "abcdefgh";

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Set<String> perms = generatePermutationsNoDups(s);
		for(String w : perms) {
			System.out.println(w);
		}
		System.out.println("Total of permutations: " + perms.size());
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static Set<String> generatePermutationsNoDups(String s) {
		if(s.length() > 0) {
			Set<String> res = new HashSet<String>();
			res.add(s.substring(0,1));
			return generatePermsAux(s.substring(1), res);
		}
		return new HashSet<String>();
	}
	
	private static Set<String> generatePermsAux(String s, Set<String> res) {
		if(s.length() == 0) {
			return res;
		}
		Set<String> newRes = new HashSet<String>();
		char c = s.charAt(0);
		for(String w : res) {
			for(int i = 0; i <= w.length(); i++) {
				String perm = (new StringBuilder(w)).insert(i,c).toString();
				newRes.add(perm);
			}
		}
		return generatePermsAux(s.substring(1), newRes);
	}

}
