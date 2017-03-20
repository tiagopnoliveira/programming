import java.util.*;

// https://careercup.com/question?id=6303130607157248
public class WorkOptimizer {
	public static class Heap {
		ArrayList<Integer> h;
		
		public Heap() {
			h = new ArrayList<Integer>();
			h.add(null);
		}
		
		public Heap(int[] a) {
			for(int i = 0; i < a.length; i++) {
				add(a[i]);
			}
		}
		
		public int root() {
			return 1;
		}
		
		public int left(int p) {
			return p*2;
		}
		
		public int right(int p) {
			return (p*2) + 1;
		}
		
		public int parent(int p) {
			return p/2;
		}
		
		public void add(int v) {
			h.add(v);
			bubbleUp(h.size()-1);
		}
		
		private void bubbleUp(int p) {
			while(!isEmpty(p) && !isEmpty(parent(p))) {
				if(get(parent(p)) < get(p)) {
					swap(p,parent(p));
				}
				p = parent(p);
			}
		}
		
		public void addValueToMin(int v) {
			h.set(root(),h.get(root()) + v);
			bubbleDown(root());
			System.out.println(Arrays.toString(h.toArray()));
		}
		
		public int getMaxValue() {
			int max = h.get(1);
			for(int i = 2; i < h.size(); i++) {
				if(h.get(i) > max) {
					max = h.get(i);
				}
			}
			return max;
		}
		
		public int getMinValue() {
			return h.get(1);
		}
		
		private void bubbleDown(int p) {
			while(!isEmpty(p)) {
				int v = get(p);
				int nextP = -1;
				if(!isEmpty(left(p)) && get(left(p)) < v) {
					nextP = left(p);
					v = get(nextP);
				}
				if(!isEmpty(right(p)) && get(right(p)) < v) {
					nextP = right(p);
				}
				if(nextP > 0) {
					swap(p,nextP);
				}
				p = nextP;
			}
		}
		
		private int get(int p) {
			return h.get(p);
		}
		
		private void swap(int p1, int p2) {
			if(p1 == p2) {
				return;
			}
			Integer t = h.get(p1);
			h.set(p1, h.get(p2));
			h.set(p2, t);
		}
		
		public boolean isEmpty(int p) {
			return p < 1 || p >= h.size() || h.get(p) == null;
		}
	}

	private static int[] a = {2,2,3,7,1};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(getMini(a,2));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    private static void descendingSort(int[] a) {
		descendingQuickSort(a,0,a.length-1);
	}
	
	private static void descendingQuickSort(int[] a, int s, int e) {
		if(s >= e) {
			return;
		}
		int p = a[e];
		int l = s;
		int r = e;
		while(l <= r) {
			while(a[l] > p) {
				l++;				
			}
			while(a[r] < p) {
				r--;
			}
			if(l <= r) {
				int t = a[l];
				a[l++] = a[r];
				a[r--] = t;
			}
		}
		descendingQuickSort(a,s,r);
		descendingQuickSort(a,l,e);
	}
	
	public static int getMini(int[] a, int k) {
		descendingSort(a);
		Heap workAssigned = new Heap();
		for(int i = 0; i < k; i++) {
			workAssigned.add(0);
		}
		
		for(int i = 0; i < a.length; i++) {
			workAssigned.addValueToMin(a[i]);
		}
		return workAssigned.getMaxValue();
	}
}
