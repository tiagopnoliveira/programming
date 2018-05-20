import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

public class SparseSimilarDocuments {

//	private static int[][] documents = { { 4 , 2 , 1 , 8, 6 }, { 2 , 5 , 12 }, { 6 , 20 , 21 , 22 }, { 4 , 1 , 8 , 6 , 2 , 5 , 12 }, { 2 , 5 , 12 } };

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		int[][] documents = generateRandomDocuments(100,4,10,50);
		//for(int i = 0; i < documents.length; i++) {
			//System.out.println(Arrays.toString(documents[i]));
		//}
		listSparseSimilarDocuments(documents);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static void listSparseSimilarDocuments(int[][] documents) {
		HashMap<Integer,HashSet<Integer>> reverseIndex = new HashMap<>();
		for(int docIndex = 0; docIndex < documents.length; docIndex++) {
			HashMap<Integer,Integer> prevDocsIntersections = new HashMap<Integer,Integer>();
			for(int wordIndex = 0; wordIndex < documents[docIndex].length; wordIndex++) {
				int word = documents[docIndex][wordIndex];
				if(reverseIndex.get(word) == null) {
					reverseIndex.put(word, new HashSet<Integer>());
				} else {
					for(Integer prevDoc : reverseIndex.get(word)) {
						if(prevDoc == docIndex) {
							continue;
						}
						prevDocsIntersections.put(prevDoc, prevDocsIntersections.getOrDefault(prevDoc,0) + 1);
					}
				}
				reverseIndex.get(word).add(docIndex);
			}
			printSimilarity(documents,docIndex,prevDocsIntersections);
		}
	}
	
	private static void printSimilarity(int[][] documents, int doc2, HashMap<Integer,Integer> prevDocsIntersections) {
		for(Integer doc1 : prevDocsIntersections.keySet()) {
			int intersection = prevDocsIntersections.get(doc1);
			int union = documents[doc1].length + documents[doc2].length - intersection;
			double similarityFactor = intersection / ((double) union);
			//System.out.println(doc1 + " , " + doc2 + " : " + similarityFactor);
		}
	}
	
	private static int[][] generateRandomDocuments(int n, int minWords, int maxWords, int dictionarySize) {
		int[][] documents = new int[n][0];
		while(--n >= 0) {
			int words = randInt(minWords,maxWords);
			documents[n] = new int[words];
			while(--words >= 0) {
				documents[n][words] = randInt(1,dictionarySize);
			}
		}
		return documents;
	}
	
	private static int randInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(min,max);
		return rand;
	}
}
