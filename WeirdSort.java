import java.util.Arrays;

public class WeirdSort {

	private static int[] a = {434,-268,-365,-394,-473,-451,105,-472,-185,-486,-454,61,223,454,-193,-67,481,-232,431,330,-325,-307,250,-64,181,-406,-13,159,-293,-196,470,-318,-70,-302,-246,353,-226,455,-383,-433,-141,338,359,-213,464,-361,-24,-391,427,364,-168,-21,-295,122,-18,482,203,-440,-326,-345,364,-340,-427,382,322,-335,-48,122,-115,-157,-225,47,-203,75,178,179,85,-393,-427,-187,-477,218,-5,-18,372,456,-444,-233,70,-321,472,354,209,-266,376,-219,-132,-38,240,-277};

    public static void main(String[] args) {
		System.out.println("Before: " + Arrays.toString(a));
		a = weirdSort(a);
		System.out.println("After: " + Arrays.toString(a));
	}
	
	private static int[] weirdSort(int a[]) {
		int result[] = new int[a.length];
		int countN = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] < 0) {
				countN++;
			}
		}
		int itN = 0, itP = countN;
		for(int i = 0; i < a.length; i++) {
			if(a[i] < 0) {
				result[itN++] = a[i];
			} else {
				result[itP++] = a[i];
			}
		}
		return result;
	}
	
		
}
