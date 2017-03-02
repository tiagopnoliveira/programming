import java.util.*;

public class HeapImpl {
	protected static class MaxHeap<T extends Comparable<T>> extends MinHeap<T> {
		public MaxHeap() {
			super();
		}
		
		public MaxHeap(Collection<T> c) {
			super(c);
		}

		public MaxHeap(T[] t) {
			super(t);
		}

		protected boolean compare(int p1, int p2) {
			if(t.get(p1).compareTo(t.get(p2)) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	protected static class MinHeap<T extends Comparable<T>> {
		protected ArrayList<T> t = new ArrayList<T>(1);
		
		public MinHeap() {
			this.t.add(null);
		}
		
		public MinHeap(Collection<T> c) {
			this();
			Iterator<T> i = c.iterator();
			while(i.hasNext()) {
				this.insert(i.next());
			}
		}

		public MinHeap(T[] t) {
			this();
			for(int i = 0; i < t.length; i++) {
				this.insert(t[i]);
			}
		}
		
		public void clear() {
			this.t.clear();
			this.t.add(null);
		}
		
		public T peek() {
			return this.t.get(root());
		}
		
		public int size() {
			return this.t.size() - 1;
		}
		
		public T extractTop() {
			T top = peek();
			swap(root(), size());
			this.t.remove(size());
			siftDown(root());
			return top;
		}
		
		public void insert(T t) {
			this.t.add(t);
			siftUp(size());
		}
		
		protected void siftDown(int p) {
			while(left(p) < t.size()) {
				int minLeaf = left(p);
				if(right(p) < t.size() && compare(right(p), minLeaf))	{
					minLeaf = right(p);					
				}
				if(compare(p, minLeaf)) {
					return;
				}
				swap(p,minLeaf);
				p = minLeaf;
			}
		}
		
		protected void siftUp(int p) {
			while(p > root()) {
				if(compare(parent(p), p)) {
					return;
				}
				swap(p, parent(p));
				p = parent(p);
			}
		}
		
		protected void swap(int p1, int p2) {
			T tmp = t.get(p1);
			t.set(p1, t.get(p2));
			t.set(p2, tmp);
		}
		
		protected boolean compare(int p1, int p2) {
			if(t.get(p1).compareTo(t.get(p2)) < 0) {
				return true;
			} else {
				return false;
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
	}

	private static Integer[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		MaxHeap<Integer> h = new MaxHeap<Integer>(a);
		for(int i = 0; i < 50; i++) {
			System.out.println(h.extractTop());
		}
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
}
