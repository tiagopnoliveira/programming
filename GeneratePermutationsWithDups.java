import java.util.Set;
import java.util.LinkedHashSet;

public class GeneratePermutationsWithDups {

	private static String s = "aaaaaabbbbbbbbcd";

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Set<String> perms = generatePermutationsWithDups(s);
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
    
    public static Set<String> generatePermutationsWithDups(String s) {
		Set<String> res = new LinkedHashSet<String>();
		res.add("");
		return generatePermsAux(s, res);
	}
	
	private static Set<String> generatePermsAux(String s, Set<String> aux) {
		if(s.isEmpty()) {
			return aux;
		}
		char c = s.charAt(0);
		int n = countOccurrencesChar(c,s);
		aux = generatePermutations(c,n,0,aux);
		return generatePermsAux(s.replace(String.valueOf(c),""), aux);
	}
	
	private static Set<String> generatePermutations(char c, int n, int start, Set<String> aux) {
		if(n == 0) {
			return aux;
		}
		Set<String> newRes = new LinkedHashSet<String>();
		for(String w : aux) {
			for(int i = start; i <= w.length(); i++) {
				String perm = (new StringBuilder(w)).insert(i,c).toString();
				newRes.add(perm);
			}
		}
		return generatePermutations(c, n-1, start+1, newRes);
	}
	
	private static int countOccurrencesChar(char c, String s) {
		String tmp = s.replace(String.valueOf(c),"");
		return s.length() - tmp.length();
	}

}
