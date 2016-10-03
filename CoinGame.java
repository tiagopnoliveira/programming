import java.util.Arrays;

public class CoinGame {
	
	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};

    public static void main(String[] args) {
		CoinGame cg = new CoinGame();
		cg.coinGame(a);
    }
    
    public void coinGame(int[] a) {
		int scoreP1 = 0, scoreP2 = 0;
		boolean turnP1 = true;
		while(a.length > 0) {
			System.out.print("Coins [] = " + Arrays.toString(a) + " ,");
			int nextMove = decideNextMove(a);
			if(turnP1) {
				System.out.print(" Alice ");
				scoreP1 += a[nextMove];
			} else {
				System.out.print(" Bob ");
				scoreP2 += a[nextMove];
			}
			System.out.println("picks " + a[nextMove]);
			a = removeCoin(a, nextMove);
			turnP1 = !turnP1;
		}
		System.out.print("Alice ");
		if(scoreP1 > scoreP2) {
			System.out.print("wins ");
		} else {
			System.out.print("loses ");
		}
		System.out.println("with " + scoreP1 + " points, Bob had " + scoreP2 + " points.");
	}
	
	private int decideNextMove(int[] a) {
		if(a.length == 0) return -1;
		if(a.length == 1) return 0;
		if(a.length == 2) {
			if(a[0] > a[1]) return 0;
			else return 1;
		}
		if(a.length == 3) {
			if(a[0] > a[2]) return 0;
			else return 2;
		}
		int wFirst = a[0] - a[1];
		int wLast = a[a.length-1] - a[a.length-2];
		if(wFirst > wLast) return 0;
		else return a.length-1;		
	}
	
	private int[] removeCoin(int[] a, int pos) {
		int res[] = new int[a.length-1];
		int j = 0;
		for(int i = 0; i < a.length; i++) {
			if(i == pos) continue;
			res[j++] = a[i];
		}
		return res;
	}
}
