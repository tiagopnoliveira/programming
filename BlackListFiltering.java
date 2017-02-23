// https://careercup.com/question?id=5651434751131648
public class BlackListFiltering {
	private static final TrieNode trieHead = new TrieNode();
	
	private static class TrieNode {
		public TrieNode[] next = new TrieNode['z' - 'a' + 2];
		public boolean terminates = false;
		
		public TrieNode() {
		}
		
		private int translateChar(char c) {
			if(c == '*') {
				return 0;
			}
			if(c < 'a' || c > 'z') {
				return -1;
			}
			return c - 'a' + 1;
		}
		
		public TrieNode getNext(char c) {
			return this.next[translateChar(c)];
		}
		
		public void setNext(TrieNode node, char c) {
			this.next[translateChar(c)] = node;
		}
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		addFilter("ab*cd");
		addFilter("ab*ef");
		addFilter("*g");
		String s;
		s = "abcd";
		System.out.println(s + " ? " + isInBlackList(s));
		s = "abbbbcd";
		System.out.println(s + " ? " + isInBlackList(s));
		s = "abfg";
		System.out.println(s + " ? " + isInBlackList(s));
		s = "a";
		System.out.println(s + " ? " + isInBlackList(s));
		s = "acd";
		System.out.println(s + " ? " + isInBlackList(s));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void addFilter(String filter) {
		TrieNode node = trieHead;
		boolean foundStar = false;
		for(int i = 0; i < filter.length(); i++) {
			char c = filter.charAt(i);
			if(node.getNext(c) == null) {
				node.setNext(new TrieNode(), c);
			}
			node = node.getNext(c);
			if(c == '*') {
				foundStar = true;
				break;
			}
		}
		for(int i = filter.length() - 1; i > 0; i--) {
			char c = filter.charAt(i);
			if(c == '*') {
				break;
			}
			if(node.getNext(c) == null) {
				node.setNext(new TrieNode(), c);
			}
			node = node.getNext(c);
		}
		System.out.println("ADDED FILTER: " + filter);
		node.terminates = true;
	}
	
	public static boolean isInBlackList(String s) {
		TrieNode node = trieHead;
		for(int i = 0; i < s.length(); i++) {
			TrieNode starNode = node.getNext('*');
			if(node.terminates || (starNode != null && checkReversedString(s,starNode,i))) {
				return true;
			}
			char c = s.charAt(i);
			node = node.getNext(c);
			if(node == null) {
				return false;
			}
		}
		return false;
	}
	
	private static boolean checkReversedString(String s, TrieNode node, int min) {
		if(node.terminates) {
			return true;
		}
		for(int i = s.length() - 1; i >= min; i--) {
			char c = s.charAt(i);
			node = node.getNext(c);
			if(node == null) {
				return false;
			}
			if(node.terminates) {
				return true;
			}
		}
		return false;
	}   
}
