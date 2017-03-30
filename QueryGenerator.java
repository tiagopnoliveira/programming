import java.util.concurrent.ThreadLocalRandom;

public class QueryGenerator {
	private static final String[] words = {"cat", "dog", "par", "aaa", "tri", "clo", "art", "car"};
	private static final String separator = " ";
	
	private static final int minWords = 1;
	private static final int maxWords = 5;
	private static final double chanceSpawnWord = 0.6;
	private static final double chanceSpawnNextWordModifier = 0.4;
	
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		for(int i = 0; i < 1000; i++) {
			System.out.println(generateQuery(minWords, maxWords, chanceSpawnWord, chanceSpawnNextWordModifier, words));
		}
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static String generateQuery(int minWords, int maxWords, double chanceSpawnWord, double chanceSpawnNextWordModifier, String[] words) {
		int nWords = 0;
		StringBuilder result = new StringBuilder();
		while(nWords < maxWords && (nWords < minWords || ThreadLocalRandom.current().nextDouble(0,1) < chanceSpawnWord)) {
			result.append(words[ThreadLocalRandom.current().nextInt(0,words.length-1)] + separator);
			if(nWords >= minWords) {
				chanceSpawnWord *= chanceSpawnNextWordModifier;
			}
			nWords++;
		}
		return result.toString().substring(0,result.length()-separator.length());
	}
// BEANSHELL

//~ String[] words = new String[]{"cat", "dog", "par", "aaa", "tri", "clo", "art", "car"};
//~ String separator = "%20";
	
//~ int minWords = 1;
//~ int maxWords = 5;
//~ double chanceSpawnWord = 0.6;
//~ double chanceSpawnNextWordModifier = 0.4;
//~ Random random = new Random();

//~ int nWords = 0;
//~ StringBuilder result = new StringBuilder();
//~ while(nWords < maxWords && (nWords < minWords || random.nextDouble() < chanceSpawnWord)) {
	//~ result.append(words[random.nextInt(words.length-1)] + separator);
	//~ if(nWords >= minWords) {
		//~ chanceSpawnWord *= chanceSpawnNextWordModifier;
	//~ }
	//~ nWords++;
//~ }
//~ vars.put("randomOption", result.toString().substring(0,result.length()-separator.length()));

}
