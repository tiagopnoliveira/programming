public class URLify {

	private static String s = "My name is Tiago      ";
	private static int size = 16;

    public static void main(String[] args) {
		System.out.println("String: " + s);
		String res = URLify(s, size);
		System.out.println("result: " + res);
    }
	
	private static String URLify(String s, int size) {
		char[] charArray = s.toCharArray();
		for(int i = charArray.length - 1, j = size - 1; i > j; i--,j--) {
			if(charArray[j] == ' ') {
				charArray[i--] = '0';
				charArray[i--] = '2';
				charArray[i] = '%';
			} else {
				charArray[i] = charArray[j];
			}
		}
		return new String(charArray);
	}

}
