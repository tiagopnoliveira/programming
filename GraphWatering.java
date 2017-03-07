public class GraphWatering {
	protected static class GraphVolumeIntermediateResult {
		public int posMaxLeft;
		public int posMaxRight;
		public int volume;
		
		public GraphVolumeIntermediateResult(int l, int r, int v) {
			this.posMaxLeft = l;
			this.posMaxRight = r;
			this.volume = v;
		}
	}

	private static int[] a = {0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0};
//	private static int[] a = {0,0,4,0,0,6,0,7,0,0,6,0,0,3,0,5,0,1,0,0,0};
//	private static int[] a = {1,0,0,1};

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println(calcGraphWateringElegantSolution(a));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static int calcGraphWatering(int[] a) {
		GraphVolumeIntermediateResult intermediateResult = calcGraphWateringIntermediateResult(a);
		int volume = intermediateResult.volume;
		volume += calcGraphWatering(a,0,intermediateResult.posMaxLeft);
		volume += calcGraphWatering(a,a.length-1,intermediateResult.posMaxRight);
		return volume;
	}
	
	private static GraphVolumeIntermediateResult calcGraphWateringIntermediateResult(int[] a) {
		int posMaxLeft = 0;
		int posMaxRight = 0;
		int volume = 0;
		int maxFound = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i++) {
			if(a[i] > maxFound) {
				posMaxLeft = i;
				posMaxRight = i;
				maxFound = a[i];
			} else if(a[i] == maxFound) {
				posMaxRight = i;
			}
		}
		if(posMaxLeft+1 < posMaxRight) {
			int usedSpace = 0;
			for(int i = posMaxLeft+1; i < posMaxRight; i++) {
				usedSpace += a[i];
			}
			volume = calcVolume(posMaxLeft, posMaxRight, maxFound) - usedSpace;
		}
		return new GraphVolumeIntermediateResult(posMaxLeft, posMaxRight, volume);
	}
	
	private static int calcGraphWatering(int[] a, int s, int e) {
		boolean goingRight = s <= e;
		int how = 0, pow = 0, vol = 0, usedSpace = 0, i = s;
		while(i != e) {
			int p = i;
			int v = a[i];
			if(goingRight) {
				i++;
			} else {
				i--;
			}
			if(how == 0) {
				how = v;
				pow = p;
				continue;
			}
			if(v < how) {
				usedSpace += v;
			} else {
				vol += calcVolume(pow, p, how) - usedSpace;
				pow = p;
				how = v;
				usedSpace = 0;
			}
		}
		vol += calcVolume(pow, e, how) - usedSpace;
		return vol;
	}
	
	private static int calcVolume(int p1, int p2, int minHeight) {
		int d = Math.abs(p1-p2) - 1;
		if(d <= 0) {
			return 0;
		}
		return d * minHeight;
	}
	
	public static int calcGraphWateringElegantSolution(int[] a) {
		int[] maxLeftSweep = new int[a.length];
		maxLeftSweep[0] = a[0];
		for(int i = 1; i < maxLeftSweep.length; i++) {
			if(a[i] > maxLeftSweep[i-1]) {
				maxLeftSweep[i] = a[i];
			} else {
				maxLeftSweep[i] = maxLeftSweep[i-1];
			}
		}
		
		int maxRightSweep = a[a.length-1];
		int res = 0;
		for(int i = a.length-2; i >= 1; i--) {
			maxRightSweep = Math.max(a[i],maxRightSweep);
			int min = Math.min(maxLeftSweep[i], maxRightSweep);
			res += Math.max(0,min-a[i]);
		}
		return res;
	}

}
