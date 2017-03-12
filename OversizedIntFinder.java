import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class OversizedIntFinder {
	private static final String FILENAME = "test.txt";

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		generateFile(FILENAME, (int) 10e6, 2);
		System.out.println(findFreeInt(FILENAME, (int) 10e6, (int) 10e3));
//		generateFile("test.txt", (int) 10e2, 3);
//		System.out.println(findFreeInt("test.txt", (int) 10e2, 10));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static void generateFile(String f, int n, int expectedMissing) {
		BufferedWriter output = null;
		double missingChance = (double) expectedMissing / n;
		try {
			File file = new File(f);
            output = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i <= n; i++) {
				if(randDouble(0,1) <= missingChance) {
					System.out.println("uh oh.. missed " + i);
				} else {
					output.write(i + "\r\n");
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try { output.close(); } catch (Exception e) {}
		}
		System.out.println("Generated file with " + n + " ints");
	}
	
	private static int randInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(min,max);
		return rand;
	}
	
	private static double randDouble(int min, int max) {
		double rand = ThreadLocalRandom.current().nextDouble(min,max);
		return rand;
	}
	
	public static int findFreeInt(String filename, int maxInt, int maxIntsMemory) {
		int blocks[] = buildBlocks(filename, maxInt, maxIntsMemory);
		int blockMissingInt = findBlockMissingInt(blocks, maxInt, maxIntsMemory);
		byte[] b = constructBitSetArrayForRange(blockMissingInt, filename, maxInt, maxIntsMemory);
		return findMissingIntFromBitSetArray(b, blockMissingInt, maxInt, maxIntsMemory);
	}
	
	private static int[] buildBlocks(String filename, int maxInt, int maxIntsMemory) {
		int blocks[] = new int[(maxInt / maxIntsMemory) + 1];
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filename));
			while (sc.hasNextInt()) {
				int i = sc.nextInt();
				blocks[i/maxIntsMemory]++;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return null;
		} finally {
			sc.close();
		}
		return blocks;
	}
	
	private static int findBlockMissingInt(int[] blocks, int maxInt, int maxIntsMemory) {
		for(int i = 0; i < blocks.length-1; i++) {
			if(blocks[i] < maxIntsMemory) {
				return i;
			}
		}
		if(blocks[blocks.length-1] < maxInt % maxIntsMemory) {
			return blocks.length-1;
		}
		return -1;		
	}
	
	private static byte[] constructBitSetArrayForRange(int blockN, String filename, int maxInt, int maxIntsMemory) {
		byte[] b = new byte[(maxIntsMemory / 8) + 1];
		int min = blockN * maxIntsMemory;
		int max = min + maxIntsMemory;
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filename));
			while (sc.hasNextInt()) {
				int i = sc.nextInt();
				if(i >= min && i < max) {
					i -= min;
					b[i/8] |= (1 << (i % 8));
				}
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return null;
		} finally {
			sc.close();
		}
		return b;
	}
	
	private static int findMissingIntFromBitSetArray(byte[] b, int blockN, int maxInt, int maxIntsMemory) {
		int v = -1;
		for(int i = 0; i < b.length; i++) {
			if(b[i] != ~0) {
				int mask = 1;
				for(int j = 0; j < 8; j++) {
					if((b[i] ^ ~mask) == 0) {
						v = (i * 8) + j + (blockN * maxIntsMemory);
						break;
					}
					mask <<= 1;
				}
			}
		}
		if(v > maxInt) {
			return -1;
		}
		return v;
	}

}
