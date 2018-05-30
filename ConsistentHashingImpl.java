import java.util.*;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class ConsistentHashingImpl {
	private static class ConsistentHashing {
		ArrayList<Long> nodes;
		
		public ConsistentHashing(int slices) {
			nodes = new ArrayList<>();
			long div = Integer.MAX_VALUE / slices;
			for(long s = 0; slices > 0; slices--) {
				nodes.add(s);
				s += div;
			}
		}
		
		public boolean addSlice(long s) {
			int p = Collections.binarySearch(nodes,s);
			if(p >= 0) {
				return false;
			}
			p = ~p;
			nodes.add(p,s);
			return true;
		}
		
		public boolean removeSlice(long s) {
			int p = Collections.binarySearch(nodes,s);
			if(p < 0) {
				return false;
			}
			nodes.remove(p);
			return true;
		}
		
		public long getSlice(long v) {
			int p = Collections.binarySearch(nodes,v);
			if(p < 0) {
				p = ~p - 1;
			}
			if(p < 0) {
				p = nodes.size() - 1; // thus circular
			}
			return nodes.get(p);
		}
	}
	
	private static int[] A = {6 , 2 , 5 , 9 , 0 , 4 , 1};
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		String test = "abcdef";
		ConsistentHashing ch = new ConsistentHashing(4);
		System.out.println(ch.getSlice(getCRC32("sdoioijsdfjnkjlsnas511a212fh"))); // goes to slice 0
		ch.removeSlice(0); // 0 goes down
		System.out.println(ch.getSlice(getCRC32("abcdef")));
		System.out.println(ch.getSlice(getCRC32("hello")));
		System.out.println(ch.getSlice(getCRC32("123123")));
		System.out.println(ch.getSlice(getCRC32("abcdef")));
		System.out.println(ch.getSlice(getCRC32("3y984wyhifuwasehruifh")));
		System.out.println(ch.getSlice(getCRC32("gx db e ")));
		System.out.println(ch.getSlice(getCRC32("world")));
		System.out.println(ch.getSlice(getCRC32("yjedygnh")));
		System.out.println(ch.getSlice(getCRC32("fdsag")));
		System.out.println(ch.getSlice(getCRC32("3tg feyhuiresdhfujasn")));
		System.out.println(ch.getSlice(getCRC32("43528uw49r894535jrtf")));
		System.out.println(ch.getSlice(getCRC32("vsdchuvuidsya97ysd")));
		System.out.println(ch.getSlice(getCRC32("5432543rfaewsz")));
		System.out.println(ch.getSlice(getCRC32("sdafsdfjdsujf89o")));
		System.out.println(ch.getSlice(getCRC32("acssacscnm")));
		System.out.println(ch.getSlice(getCRC32("3tq43r90i4e0r9fealfdsdjf89sdhjzfdsnafjn")));
		System.out.println(ch.getSlice(getCRC32("vca")));
		System.out.println(ch.getSlice(getCRC32("s")));
		System.out.println(ch.getSlice(getCRC32("47yrcfvb")));
		System.out.println(ch.getSlice(getCRC32("2345678ertyufghj")));
		
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static long getCRC32(String s) {
		byte[] byteArray = s.getBytes();
		Checksum checksum = new CRC32();
		checksum.update(byteArray,0,byteArray.length);
		return checksum.getValue();
	}
    
    public static int[] generateRandomArray(int n, int r) {
		int[] res = new int[n];
		for(int i = 0; i < n; i++) {
			res[i] = ((int) Math.floor((Math.random() * ((double) r+1))));
		}
		return res;
	}

	public static void swapElementsArray(int[] A, int p1, int p2) {
		if(p1 == p2 || A[p1] == A[p2]) {
			return;
		}
		A[p1] ^= A[p2];
		A[p2] ^= A[p1];
		A[p1] ^= A[p2];
	}
}
