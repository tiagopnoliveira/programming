import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

// https://www.topcoder.com/stat?c=problem_statement&pm=14807
public class Halving {

//	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
//	private static int[] a = {13, 13, 7, 11, 13, 11};
//	private static int[] a = {1, 2, 3, 4, 5, 6, 7};
//	private static int[] a = {315461279, 447297969, 153123822, 526780548, 378920322, 677297622, 429962081, 251944383, 377920255, 485550273, 445055383, 568926630, 338867875, 713954131, 605216012, 75920122, 313865306, 283308000, 91172709, 284341083, 22942201, 223336115, 474895734, 93321486, 713112215, 365877280, 51064304, 165150259, 608585843, 19868569, 71352895, 760461001, 557972562, 28980741, 252788794, 500061803, 286156050, 606077588, 149820544, 113652026, 270852241, 239904480, 197007221, 18514433, 67973989, 623225738, 316399714, 412859854, 799480216, 415643240};
//	private static int[] a = {1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000};
  private static int[] a = {1, 125387416, 407456958, 681542493, 913057001, 194544296, 332513754, 972751016, 145906011, 378686835, 10987091, 226480213, 543106121, 125036298, 438929287, 192254391, 161710939, 147414552, 320499865, 495099010, 446149916, 659038814, 408656647, 699392023, 559675251, 537119683, 183238762, 224309097, 686974156, 903636290, 705043005, 391127256, 392574100, 410713327, 22946130, 551606640, 991039580, 787152820, 636677434, 509369540, 235531669, 264912905, 588932169, 806630974, 42842696, 237668475, 709011804, 878568074, 739551429, 145972960};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(halving(a));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int halving(int[] a) {
		int s = 0;
		ArrayList<HashMap<Integer, Integer>> stepMaps = generateStepsMap(a);
		int maxCommonValue = findMaxCommonValue(stepMaps);
		System.out.println("Best common val: " + maxCommonValue);
		for(HashMap<Integer, Integer> map : stepMaps) {
			s += map.get(maxCommonValue);
		}
		return s;
	}
	
	private static int findMaxCommonValue(ArrayList<HashMap<Integer, Integer>> stepMaps) {
		int maxFound = Integer.MIN_VALUE;
		HashSet<Integer> keys = new HashSet<Integer>(stepMaps.get(0).keySet());
		for(int i = 1; i < stepMaps.size(); i++) {
			keys.retainAll(stepMaps.get(i).keySet());
		}
		for(Integer v : keys) {
			if(v > maxFound) {
				maxFound = v;
			}
		}
		return maxFound;
	}
	
	private static ArrayList<HashMap<Integer, Integer>> generateStepsMap(int[] a) {
		ArrayList<HashMap<Integer, Integer>> res = new ArrayList<HashMap<Integer, Integer>>(a.length);
		for(int i = 0; i < a.length; i++) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			generateStepsMap(a[i],0,map);
			res.add(map);
		}
		return res;
	}
	
	private static void generateStepsMap(int v, int s, HashMap<Integer, Integer> map) {
		if(v < 1 || (map.get(v) != null && map.get(v) <= s)) {
			return;
		}
		map.put(v,s);
		if(v == 1) {
			return;
		}
		if(v % 2 == 0) {
			generateStepsMap(v/2,s+1,map);
		} else {
			generateStepsMap((v+1)/2,s+1,map);
			generateStepsMap((v-1)/2,s+1,map);
		}
	}
}
