import java.util.concurrent.ThreadLocalRandom;

public class GenerateLargeIntArray {

    public static void main(String[] args) {
			int min = 0;
			int max = 100;
			int size = 50;
		try {
			min = Integer.parseInt(args[0]);
			max = Integer.parseInt(args[1]);
			size = Integer.parseInt(args[2]);
		} catch (Exception e) {
			System.out.println("usage is: java GenerateLargeIntArray <min> <max> <size>");
			return;
		}
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < size; i++) {
			int rand = randInt(min, max);
			result.append(rand + ",");
		}
		result.deleteCharAt(result.length()-1);
		System.out.println(result.toString());
    }

	private static int randInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(min,max);
		return rand;
	}

	
}
