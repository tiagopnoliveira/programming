public class AbsPathImageFinder {

	private static String s = "/usr\n /local\n  profile.jpg\n  /bin\n   config.txt\n   dest.png\n  /rbin\n image.gif\n/sys\n /re\n tmp\n  pic.jpg";
	private static final int MAX_DIR_DEPTH = 2;

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		absPathImageFinder(s);
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
    
    public static void absPathImageFinder(String s) {
		String[] lines = s.split("\n");
		String[] dirs = new String[MAX_DIR_DEPTH];
		int lvl = 0;
		for(int i = 0; i < lines.length; i++) {
			String l = lines[i];
			int newlvl = countLvl(l);
			// in case it is an empty line
			if(newlvl < 0) {
				continue;
			}
			lvl = newlvl;
			l = l.trim();
			if(isDir(l)) {
				if(lvl >= MAX_DIR_DEPTH) {
					System.out.println("Cannot exceed dir depth of " + MAX_DIR_DEPTH);
					return;
				}
				dirs[lvl] = l.substring(1,l.length());
				lvl++;
				continue;
			}
			if(isImage(l)) {
				System.out.print("/");
				for(int j = 0; j < lvl; j++) {
					System.out.print(dirs[j] + "/");
				}
				System.out.println(l);
			}
		}
	}
	
	private static boolean isDir(String s) {
		return s.startsWith("/");
	}

	private static boolean isImage(String s) {
		return s.endsWith(".jpg") || s.endsWith(".png") || s.endsWith(".gif");
	}
	
	private static int countLvl(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != ' ') {
				return i;
			}
		}
		return -1;
	}
}
