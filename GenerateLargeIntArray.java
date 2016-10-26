import java.util.concurrent.ThreadLocalRandom;

public class GenerateLargeIntArray {

	static int min = 1;
	static int max = 1000000;
	static int mult = 1;
	static int size = 25;

    public static void main(String[] args) {
		
		//~ try {
			//~ min = Integer.parseInt(args[0]);
			//~ max = Integer.parseInt(args[1]);
			//~ mult = Integer.parseInt(args[2]);
			//~ size = Integer.parseInt(args[3]);
		//~ } catch (Exception e) {
			//~ System.out.println("usage is: java GenerateLargeIntArray <min> <max> <mult> <size>");
			//~ return;
		//~ }
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < size; i++) {
			int rand = randInt(min, max);
			result.append(rand + ",");
		}
		result.deleteCharAt(result.length()-1);
		System.out.println(result.toString());
    }

	private static int randInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(min,max) * mult;
		return rand;
	}

	
}
