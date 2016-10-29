import java.util.Hashtable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StackBoxes {
	public static class Box {
		int w;
		int h;
		int d;
		
		public Box(int w, int h, int d) {
			this.w = w;
			this.h = h;
			this.d = d;
		}
		
		public boolean canStack(Box ot) {
			return w > ot.w && h > ot.h && d > ot.d;
		}
	}
	
	public static class Solution {
		int h;
		ArrayList<Box> stack;
		
		public Solution() {
			this.h = 0;
			this.stack = new ArrayList<Box>();
		}
		
		public Solution(Box bb, Solution ts) {
			this();
			this.h = bb.h + ts.h;
			this.stack.addAll(ts.stack);
			this.stack.add(bb);
		}

		public Solution(Box b) {
			this();
			this.h = b.h;
			this.stack.add(b);
		}
		
		public static Solution compareBestSolution(Solution s1, Solution s2) {
			if(s1.h > s2.h) {
				return s1;
			} else {
				return s2;
			}
		}
		
		public void print() {
			System.out.println("Stack height: " + h);
			int c = 1;
			for(Box b : stack) {
				System.out.println("Position " + c++ + ": { w: " + b.w + ", d: " + b.d + ", h: " + b.h + "}");
			}
		}
	}
	
	private static boolean[][] stackMatrix;
	private static Box[] boxes;
	private static Hashtable<Integer,Solution> memo;

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		generateBoxes(10000,1,90);
		Solution s = stackBoxes(boxes);
		s.print();
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static Solution stackBoxes(Box[] boxes) {
		memo = new Hashtable<Integer,Solution>();
		stackMatrix = generateStackMatrix(boxes);
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		for(int ob = 0; ob < boxes.length; ob++) {
			solutions.add(stackBoxesAux(ob));
		}
		Solution bestSolution = new Solution();
		for(Solution s : solutions) {
			bestSolution = Solution.compareBestSolution(bestSolution, s);
		}
		return bestSolution;
	}
	
	private static Solution stackBoxesAux(int lb) {
		if(memo.containsKey(lb)) {
			return memo.get(lb);
		}
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		for(int ot = 0; ot < stackMatrix.length; ot++) {
			if(stackMatrix[lb][ot]) {
				solutions.add(stackBoxesAux(ot));
			}
		}
		Solution bs = new Solution();
		for(Solution sc : solutions) {
			bs = Solution.compareBestSolution(bs, sc);
		}
		bs = new Solution(boxes[lb],bs);
		memo.put(lb,bs);
		return bs;
	}
	
	private static boolean[][] generateStackMatrix(Box[] boxes) {
		boolean[][] res = new boolean[boxes.length][boxes.length];
		for(int ob = 0; ob < boxes.length; ob++) {
			for(int ot = 0; ot < boxes.length; ot++) {
				res[ob][ot] = boxes[ob].canStack(boxes[ot]);
			}
		}
		return res;
	}
	
	private static void generateBoxes(int n, int min, int max) {
		boxes = new Box[n];
		while(n-- > 0) {
			int w = randInt(min,max);
			int h = randInt(min,max);
			int d = randInt(min,max);
			boxes[n] = new Box(w+d,h,d+w);
		}
	}

	private static int randInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(min,max);
		return rand;
	}

}
