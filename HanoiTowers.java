import java.util.Stack;
import java.util.Arrays;

public class HanoiTowers {
	
	private static class Hanoi {
		Stack<Integer> t1;
		Stack<Integer> t2;
		Stack<Integer> t3;
		int n;
		int moves;
		
		public Hanoi(int n) {
			this.n = n;
			this.moves = 0;
			t1 = new Stack<Integer>();
			t2 = new Stack<Integer>();
			t3 = new Stack<Integer>();
			for(int i = n; i > 0; i--) {
				t1.push(i);
			}
			if(t1.empty()) {
				System.out.println("Not much to solve.");
			} else {
				System.out.println("Hanoi Towers generated as follows:");
				printCurrentState();
				System.out.println();
			}
		}
		
		public void moveDisc(Stack<Integer> src, Stack<Integer> tgt) {
			if(src.empty()) {
				System.out.println("Illegal move: Trying to move from empty tower.");
				return;
			}
			if(!tgt.empty() && src.peek() > tgt.peek()) {
				System.out.println("Illegal move: Trying to move larger piece " + src.peek() + " on top of smaller piece " + tgt.peek() + ".");
				printCurrentState();
				return;
			}
			tgt.push(src.pop());
			moves++;
			printCurrentState();
			if(t1.empty() && t2.empty()) {
				System.out.println("Hanoi Towers are solved!");
			}
		}
		
		public void printCurrentState() {
			System.out.println("t1: " + Arrays.toString(t1.toArray()));
			System.out.println("t2: " + Arrays.toString(t2.toArray()));
			System.out.println("t3: " + Arrays.toString(t3.toArray()));
			System.out.println(moves + " moves done so far.");
		}
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		solveHanoiTowers(new Hanoi(8));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void solveHanoiTowers(Hanoi hanoi) {
		int n = hanoi.n;
		solveHanoiTowers(hanoi, n, hanoi.t1, hanoi.t3, hanoi.t2);
	}
	
	private static void solveHanoiTowers(Hanoi hanoi, int n, Stack<Integer> src, Stack<Integer> tgt, Stack<Integer> tmp) {
		if(n <= 0) return;
		if(n == 1) {
			hanoi.moveDisc(src, tgt);
			return;
		}
		solveHanoiTowers(hanoi, n-1, src, tmp, tgt);
		solveHanoiTowers(hanoi, 1  , src, tgt, tmp);
		solveHanoiTowers(hanoi, n-1, tmp, tgt, src);
	}

}
