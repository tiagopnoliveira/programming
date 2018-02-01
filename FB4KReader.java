import java.util.*;

// The one that got away..
public class FB4KReader {
	private static class Reader4K {
		int size;
		public Reader4K(int size) {
			this.size = size;
		}
		
		public int read4K(char[] data) {
			if(size > 4096) {
				size -= 4096;
				return 4096;
			} else {
				int tmp = size;
				size = 0;
				return tmp;
			}
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public int getSize() {
			return size;
		}
	}

    private static Reader4K reader4K = new Reader4K(40000);
    
    private static Queue<Character> buffer = new LinkedList<Character>();

    public static int read(Character[] data, int size) {
		LinkedList<Character> dataList = new LinkedList<Character>();
		while(!reader4K.isEmpty() && dataList.size() < size) {
			if(buffer.isEmpty()) {
				char[] tmpBuffer = new char[4096];
				int tmpSize = reader4K.read4K(tmpBuffer);
				for(int i = 0; i < tmpSize; i++) {
					buffer.add(tmpBuffer[i]);
				}
			}
			while(!buffer.isEmpty() && dataList.size() < size) {
				dataList.add(buffer.poll());
			}
		}
		data = dataList.toArray(new Character[0]);
		return dataList.size();
	}

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println("Current size: " + reader4K.getSize());
		Character[] data = null;
		System.out.println("Read : " + read(data, 1200));
		System.out.println("Current size: " + reader4K.getSize());
		System.out.println("Read : " + read(data, 2400));
		System.out.println("Current size: " + reader4K.getSize());
		System.out.println("Read : " + read(data, 3000));
		System.out.println("Current size: " + reader4K.getSize());
		System.out.println("Read : " + read(data, 8000));
		System.out.println("Current size: " + reader4K.getSize());
		System.out.println("Read : " + read(data, 10000));
		System.out.println("Current size: " + reader4K.getSize());
		System.out.println("Read : " + read(data, 10000));
		System.out.println("Current size: " + reader4K.getSize());
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
}
