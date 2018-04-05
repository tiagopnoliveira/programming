import java.util.*;

public class BSTImpl {
	private static class IllegalBSTAccessException extends Exception {
		public IllegalBSTAccessException() {
			super();
		}
	}
	
	protected static class BST<T extends Comparable<T>> {
		protected ArrayList<T> t;
		
		public BST() {
			this(1);
		}
		
		private BST(int n) {
			t = new ArrayList<T>(n);
			t.add(null);			
		}

		public BST(T[] t) {
			this(Arrays.asList(t));
		}
		
		public BST(List<T> t) {
			this(t.size());
			Collections.sort(t);
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(0);
			queue.add(t.size()-1);
			while(!queue.isEmpty()) {
				int s = queue.poll(), e = queue.poll(), m = (s+e)/2;
				this.insert(t.get(m));
				if(s < m) {
					queue.add(s);
					queue.add(m-1);
				}
				if(m < e) {
					queue.add(m+1);
					queue.add(e);
				}
			}
		}
		
		protected int root() {
			return 1;
		}
		
		protected int left(int p) {
			return p*2;
		}
		
		protected int right(int p) {
			return (p*2)+1;
		}
		
		protected int parent(int p) {
			return p/2;
		}
		
		protected boolean check(int p) {
			if(p < 1 || p >= this.t.size() || this.t.get(p) == null) {
				return false;
			}
			return true;
		}
		
		public void insert(T t) {
			int p = root();
			while(check(p)) {
				if(less(t,get(p))) {
					p = left(p);
				} else {
					p = right(p);
				}
			}
			while(this.t.size() <= p) {
				this.t.add(null);
			}
			this.t.set(p,t);
		}
		
		public void delete(int p) {
			if(!check(p)) {
				return;
			}
			
			// node to be removed has 2 children. in this case, we should find
			// the min element in the right side of the tree and replace the
			// element to remove with this one.
			if(check(left(p)) && check(right(p))) {
				int minP = right(p);
				while(check(left(minP))) {
					minP = left(minP);
				}
				this.t.set(minP, this.t.get(p));
				if(check(right(minP))) {
					move(right(minP),minP);
				} else {
					t.set(minP,null);
				}
			} else if(check(left(p))) {
				move(left(p),p);
			} else if(check(right(p))) {
				move(right(p),p);
			} else {
				this.t.set(p,null);
			}
		}
		
		public void move(int o, int d) {
			if(!check(o)) {
				t.set(d,null);
				return;
			}
			this.t.set(d,get(o));
			this.t.set(o,null);
			if(check(left(o))) {
				move(left(o),left(d));
			}
			if(check(right(o))) {
				move(right(o), right(d));
			}
		}
		
		public T getMin() {
			int p = root();
			while(check(left(p))) {
				p = left(p);
			}
			return get(p);
		}
		
		public T getMax() {
			int p = root();
			while(check(right(p))) {
				p = right(p);
			}
			return get(p);
		}
		
		protected boolean less(T t1, T t2) {
			if(t1.compareTo(t2) < 0) {
				return true;
			}
			return false;
		}
		
		protected T get(int p) {
			return t.get(p);
		}
		
		public void printArray() {
			System.out.println(Arrays.toString(this.t.toArray()));
		}
		
		public float ratio() {
			int f = 0;
			int n = 0;
			for(int i = 1; i < this.t.size(); i++) {
				if(this.t.get(i) == null) {
					n++;
				} else {
					f++;
				}
			}
			return ((float) f/n);
		}
		
		public int distanceBetweenValues(T v1, T v2) {
			if(!less(v1,v2)) {
				T tmp = v1;
				v1 = v2;
				v2 = tmp;
			}
			int p = root();
			while(check(p)) {
				if(less(v1,t.get(p)) && less(v2,t.get(p))) {
					p = left(p);					
				} else if(!less(v1,t.get(p)) && !less(v2,t.get(p))) {
					p = right(p);
				} else {
					break;
				}
			}
			if(!check(p)) {
				return -1;
			}
			
			int pv1 = p;
			int d = 0;
			while(check(pv1)) {
				if(v1.compareTo(t.get(pv1)) == 0) {
					break;
				}
				if(less(v1,t.get(pv1))) {
					pv1 = left(pv1);
				} else {
					pv1 = right(pv1);
				}
				d++;
			}
			
			if(!check(pv1)) {
				return -1;
			}
			
			int pv2 = p;
			while(check(pv2)) {
				if(v2.compareTo(t.get(pv2)) == 0) {
					break;
				}
				if(less(v2,t.get(pv2))) {
					pv2 = left(pv2);
				} else {
					pv2 = right(pv2);
				}
				d++;
			}

			if(!check(pv2)) {
				return -1;
			}
			
			return d;
		}
		
		public boolean checkBalanced() {
			int l = this.t.size() - 1;
			while(this.t.get(l) == null) {
				l--;
			}
			int k = (int) (Math.log(l) / Math.log(2));
			int m = (int) (Math.pow(2,k) + Math.pow(2,k+1) - 1) / 2;
			boolean checkLeft = (l > m);
			return compareTreeSides(k-1, checkLeft);
		}
		
		protected boolean compareTreeSides(int k, boolean checkLeft) {
			int leftExtreme = (int) Math.pow(2,k-1);
			int rightExtreme = (int) Math.pow(2,k) - 1;
			int middle = (leftExtreme + rightExtreme) / 2;
			int s,e;
			if(checkLeft) {
				s = leftExtreme;
				e = middle;
			} else {
				s = middle;
				e = rightExtreme;
			}
			for(int i = s; i <= e; i++) {
				if(this.t.get(i) != null) {
					return true;
				}
			}
			return false;
		}
	}

//	private static Integer[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
//	private static Integer[] a = {50,35,28,12,32,14,4,68,41,21,7,55,16,1};
//	private static Integer[] a = {50,35,28,12};
//	private static Integer[] a = {7,3,8,1,4};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		//~ BST<Integer> bst = new BST<Integer>(a);
		BST<Integer> bst = new BST<Integer>();
		bst.insert(6);
		bst.insert(5);
		bst.insert(9);
		bst.insert(4);
		bst.insert(8);
		bst.insert(14);
		bst.insert(10);
		bst.insert(15);
		bst.insert(11);

		bst.printArray();
		
		//~ bst.delete(3);
		//~ bst.printArray();
		System.out.println(bst.distanceBetweenValues(5,11));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }

}
