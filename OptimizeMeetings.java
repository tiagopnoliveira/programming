import java.util.*;

public class OptimizeMeetings {
	private static class Meeting implements Comparable<Meeting> {
		int s;
		int e;
		int w;
		
		public Meeting(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		public int compareTo(Meeting m) {
			return this.e - m.e;
		}
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		ArrayList<Meeting> ms = new ArrayList<Meeting>();
		ms.add(new Meeting(1,10,6));
		ms.add(new Meeting(0,2,2));
		ms.add(new Meeting(2,3,1));
		ms.add(new Meeting(2,3,2));
		ms.add(new Meeting(3,4,2));
		ms.add(new Meeting(6,7,2));
		ms.add(new Meeting(7,8,1));
		ms.add(new Meeting(8,11,2));
		//ms.add(new Meeting(2,9,12));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println(optimizeMeetings(ms));
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int optimizeMeetings(ArrayList<Meeting> meetings) {
		Collections.sort(meetings);
		int[] accWeight = new int[meetings.size()];
		for(int i = 0; i < meetings.size(); i++) {
			int k = i - 1;
			while(k >= 0 && meetings.get(k).e > meetings.get(i).s) {
				k--;
			}
			int startWeight = 0;
			if(k >= 0) {
				startWeight = accWeight[k];
			}
			int prevWeight = 0;
			if(i > 0) {
				prevWeight = accWeight[i-1];
			}
			int currWeight = meetings.get(i).w;
			accWeight[i] = Math.max(currWeight + startWeight, prevWeight);
		}
		return accWeight[meetings.size()-1];
	}

}
