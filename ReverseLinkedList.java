public class ReverseLinkedList {
	protected static class LinkedNode<T> {
		public LinkedNode<T> next;
		T t;
		
		public LinkedNode(LinkedNode<T> next, T t) {
			this.next = next;
			this.t = t;
		}
		
		public boolean hasNext() {
			return next != null;
		}
		
		public LinkedNode<T> reverseList() {
			LinkedNode<T> prevNode = null;
			LinkedNode<T> currNode = this;
			while(currNode.hasNext()) {
				LinkedNode<T> tmp = currNode.next;
				currNode.next = prevNode;
				prevNode = currNode;
				currNode = tmp;
			}
			currNode.next = prevNode;
			return currNode;
		}
	}


    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		LinkedNode<Integer> h = new LinkedNode<Integer>(null, 4);
		h = new LinkedNode<Integer>(h, 3);
		h = new LinkedNode<Integer>(h, 2);
		h = new LinkedNode<Integer>(h, 1);
		printList(h);
		h = h.reverseList();
		printList(h);
		h = h.reverseList();
		printList(h);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
	public static void printList(LinkedNode n) {
		while(n.hasNext()) {
			System.out.print(n.t);
			System.out.print(" -> ");
			n = n.next;
		}
		System.out.println(n.t);
	}

}
