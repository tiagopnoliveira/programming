import java.util.*;
import java.lang.Math;

public class Burner {
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here


		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
}
