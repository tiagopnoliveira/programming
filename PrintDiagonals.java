import java.util.*;

public class PrintDiagonals {
	
	private static int[][] m = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
	
	public static void PrintDiagonals(int[][] m) {
		List<String> diagonals = new ArrayList<String>();
		for(int i = 0; i < m[0].length; i++) {
			diagonals.add(buildDiagonal(m, i, 0));
		}
		for(int j = 1; j < m.length; j++) {
			diagonals.add(buildDiagonal(m, m.length-1, j));
		}
		
		for(String s : diagonals) {
			System.out.println(s);
		}
	} 

	private static String buildDiagonal(int[][] m, int i, int j) {
		StringBuilder str = new StringBuilder();
		int k = 0;
		while (true) {
			int row = i-k;
			int col = j+k;
			if(row < 0 || col > m[i].length-1) {
				break;
			}
			str.append(m[row][col] + " ");
			k++;
		}
		str.setLength(str.length() - 1);
		return str.toString();
	}

    public static void main(String[] args) {
		PrintDiagonals(m);
    }

}
