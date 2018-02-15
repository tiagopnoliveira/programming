import java.util.Stack;
import java.util.ArrayList;

public class StackPlates {
	public static class StackOfPlates {
		int maxSize = 3;
		ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
		public StackOfPlates(int maxSize) {
			this.maxSize = maxSize;
		}
		
		public void push(int v) {
			int i = 0;
			for(Stack<Integer> s : stacks) {
				if(s.size() < maxSize) {
					s.push(v);
					return;
				}
				i++;
			}
			stacks.add(new Stack<Integer>());
			stacks.get(stacks.size()-1).push(v);
		}
		
		public int pop() {
			if(stacks.size() == 0) {
				return -1;
			}
			Stack<Integer> lastStack = stacks.get(stacks.size()-1);
			int v = lastStack.pop();
			if(lastStack.isEmpty()) {
				stacks.remove(lastStack);
			}
			return v;
		}
		
		public void printContents() {
			int n = 1;
			for(Stack<Integer> s : stacks) {
				System.out.print("Stack " + n + " : ");
				for(Integer i : s) {
					System.out.print(i + " ");
				}
				System.out.println();
				n++;
			}
		}
	}

	//private static int[] a = {1,2,3,4};
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		StackOfPlates sp = new StackOfPlates(3);
		sp.push(1);
		sp.push(2);
		sp.push(3);
		sp.push(4);
		sp.push(5);
		sp.push(6);
		sp.push(7);
		sp.push(8);
		sp.push(9);
		sp.push(10);
		sp.push(11);
		sp.push(12);
		sp.push(13);
		sp.push(14);
		sp.push(15);
		sp.push(16);
		sp.push(17);
		sp.push(18);
		sp.push(19);
		sp.push(20);
		sp.push(21);
		sp.push(22);
		
		sp.printContents();
		sp.pop();
		sp.pop();
		sp.pop();
		sp.pop();
		sp.pop();
		sp.push(23);
		sp.push(24);
		System.out.println();
		sp.printContents();

		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
}
