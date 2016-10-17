import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BSTImpl {
	private static class BST {
		ArrayList<Integer> a;
		
		public BST() {
			this.a = new ArrayList<Integer>();
			
			a.add(null);
			a.add(8);
			a.add(3);
			a.add(10);
			a.add(1);
			a.add(6);
			a.add(null);
			a.add(14);
			a.add(null);
			a.add(2);
			a.add(4);
			a.add(7);
			a.add(13);
			
			//new int[] {getNull(), 8, 3, 10, 1, 6, getNull(), 14, getNull(), getNull(), 4, 7, 13};
		}
		
		public BST(int[] a) {
			this.a = new ArrayList<Integer>();
			for(int i = 0; i < a.length; i++) {
				this.a.add(a[i]);
			}
		}
		
		public BST(BST bst) {
			this.a = new ArrayList<Integer>();
			for(Integer i : bst.a) {
				this.a.add(i);
			}
		}
		
		public int getRoot() {
			return 1;
		}
		
		public int getNull() {
			return -1;
		}
		
		public int getMinValue() {
			if(a.size() < 1) {
				return getNull();
			}
			return getValue(getNodeMinValue(getRoot()));
		}
		
		private int getNodeMinValue(int startingNode) {
			int n = startingNode;
			while(true) {
				int ln = getLeftNode(n);
				if(isOutOfBounds(ln) || isEmpty(ln)) {
					return n;
				}
				n = ln;
			}
		}
		
		public int getMaxValue() {
			if(a.size() < 1) {
				return getNull();
			}
			int n = getRoot();
			while(true) {
				int rn = getRightNode(n);
				if(isOutOfBounds(rn) || isEmpty(rn)) {
					return a.get(n);
				}
				n = rn;
			}
		}
		
		public int getKthMinValue(int k) {
			if(k < 1 || k > a.size()) {
				return -1;
			}
			int n = getNodeMinValue(getRoot());
			ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
			while(k > 1) {
				k--;
				visitedNodes.add(n);
				int rn = getRightNode(n);
				if(!isOutOfBounds(rn) && !isEmpty(rn)) {
					n = getNodeMinValue(rn);
					continue;
				}
				while(visitedNodes.contains(n)) {
					n = getParent(n);
				}
			}
			//System.out.println(":( " + n);
			return getValue(n);
		}
		
		public int getValue(int n) {
			return a.get(n);
		}
		
		public void deleteNode(int n) {
			if(isOutOfBounds(n) || isEmpty(n)) {
				return;
			}
			a.set(n,null);
			int oln = getLeftNode(n);
			int orn = getRightNode(n);
			if(!isOutOfBounds(oln) && !isEmpty(oln)) {
				transferNode(oln, n);
				n = oln;
			}
			if(!isOutOfBounds(orn) && !isEmpty(orn)){
				transferNode(orn, n);
			}
		}
		
		private void transferNode(int origin, int destination) {
			a.set(destination, a.get(origin));
			int oln = getLeftNode(origin);
			int orn = getRightNode(origin);
			if(!isOutOfBounds(oln) && !isEmpty(oln)) {
				transferNode(oln, getLeftNode(destination));
			}
			if(!isOutOfBounds(orn) && !isEmpty(orn)) {
				transferNode(orn, getRightNode(destination));
			}
		}
		
		public int getLeftNode(int n) {
			return n*2;
		}

		public int getRightNode(int n) {
			return n*2 + 1;
		}
		
		public int getParent(int n) {
			return n/2;
		}
		
		public boolean isEmpty(int n) {
			return a.get(n) == null;
		}

		public boolean isOutOfBounds(int n) {
			return n < 1 || n >= a.size();
		}
	}
	
	

	//private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		BST bst = new BST();
		System.out.println(bst.getMinValue());
		System.out.println("K = 1: " + bst.getKthMinValue(1));
		System.out.println("K = 2: " + bst.getKthMinValue(2));
		System.out.println("K = 3: " + bst.getKthMinValue(3));
		System.out.println("K = 4: " + bst.getKthMinValue(4));
		System.out.println("K = 5: " + bst.getKthMinValue(5));
		System.out.println("K = 6: " + bst.getKthMinValue(6));
		System.out.println("K = 7: " + bst.getKthMinValue(7));
		System.out.println("K = 8: " + bst.getKthMinValue(8));
		System.out.println("K = 9: " + bst.getKthMinValue(9));
		System.out.println("K = 10: " + bst.getKthMinValue(10));

		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }

}
