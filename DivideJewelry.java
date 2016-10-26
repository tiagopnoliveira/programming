import java.lang.Math;
import java.util.Random;
import java.util.Arrays;
import java.util.Hashtable;

public class DivideJewelry {
	
/* https://community.topcoder.com/stat?c=problem_statement&pm=14384

Problem Statement
Fox Ciel and Fox Jiro just found a box of jewelry. There are n pieces of jewelry in the box. The pieces are numbered 0 through n-1. You are given a int[] x with n elements. For each i, the value of piece i is x[i]. 



Ciel and Jiro want to divide the jewelry in a fair way. Each fox must take a non-empty subset of the jewelry. Obviously, the two subsets must be disjoint: each piece of jewelry can only be taken by at most one fox. The total value of jewelry taken by Ciel must be exactly the same as the total value of jewelry taken by Jiro. 



If there is no valid solution, return an empty int[]. If there are multiple valid solutions, you may choose any of them. Return a int[] res with n elements. For each i:
res[i]=1 denotes that Ciel should take the piece number i
res[i]=-1 denotes that Jiro should take the piece number i
res[i]=0 denotes that piece number i should be left in the box
 
Definition
    	
Class:	DivideJewelry
Method:	divide
Parameters:	int[]
Returns:	int[]
Method signature:	int[] divide(int[] x)
(be sure your method is public)
    
 
Constraints
-	x will contain between 2 and 1,000 elements, inclusive.
-	Each element in x will be between 1 and 1,000,000, inclusive.
 
Examples
0)	
    	
{1,2,3}
Returns: {1, 1, -1 }
One fox should take pieces 0 and 1, and the other fox should take piece 2. The total value of jewelry taken by each fox will be 3. You may return either {1, 1, -1} or {-1, -1, 1}.
1)	
    	
{1,2}
Returns: { }
It is impossible to divide these two pieces evenly. Note that each fox must take at least one piece of jewelry.
2)	
    	
{1,1,2,4,8,16,32}
Returns: {1, -1, 0, 0, 0, 0, 0 }
Here, one easy solution is that one fox takes piece 0 and the other fox takes piece 1. This is a fair division because both pieces have the same value.
3)	
    	
{1,2,4,8,16,32}
Returns: { }
4)	
    	
{534,260,643,230,450,560,430,210}
Returns: {0, 0, 0, 1, -1, 0, 1, -1 }

*/

//	private static int[] a = {445,38,446,153,404,174,118,413,170,400,96,389,14,183,25,19,174,10,208,172,254,94,304,39,319,41,428,22,310,66,302,365,128,478,479,10,73,435,122,333,229,137,318,183,10,96,409,259,181,194,272,7,212,464,446,163,37,209,151,155,21,84,171,421,153,464,428,195,15,44};
	private static int[] a = {5,4,3,6};
//	private static int[] a = {42,4,6,10,6,4,5,7};
	private static int[] b = {469644, 1000000, 999999, 999998, 999996, 999993, 999987, 999976, 999956, 999916, 999839, 999691, 999406, 998836, 997716, 995516, 991193, 982695, 965699, 931992, 865148, 732580};
	private static int[] c = {2048, 2, 32768, 8, 256, 64, 65536, 262144, 16, 8192, 32, 131072, 524287, 4096, 1, 4, 1024, 16384, 128, 512};
	private static int[] lol = {310450, 698064, 446620, 380646, 304936, 364395, 411982, 437271, 763558, 677213, 531193, 434377, 969970, 767746, 743779, 400221, 745143, 943475, 997651, 920505, 520012, 269718, 780445, 762487, 825472, 994659, 453982, 548607, 591152, 215413, 664179, 388177, 620620, 586230, 150250, 409221, 865619, 403991, 755025, 394839, 957077, 901000, 971580, 656756, 977977, 388046, 772078, 225137, 683495, 253111, 710838, 754479, 345509, 675376, 88415, 722169, 767109, 736380, 347640, 41125, 199128, 306752, 753279, 625206, 347430, 141241, 734064, 977550, 363426, 46645, 496141, 154941, 498742, 694071, 192780, 290148, 885003, 2228, 99465, 826941, 989403, 744565, 613802, 533001, 13578, 960296, 485154, 937466, 148375, 318534, 46518, 360941, 135351, 680310, 540558, 4962, 295832, 468896, 856275, 888388, 885251, 245960, 842482, 537702, 566635, 175782, 634190, 668464, 240572, 442030, 705567, 273243, 590216, 567141, 67661, 38245, 444, 745230, 768989, 402560, 973958, 162608, 897882, 15930, 655688, 654104, 396230, 104179, 744275, 304132, 898376, 486978, 387849, 901253, 11073, 517339, 414430, 60173, 774307, 668041, 967624, 732800, 387375, 170966, 716556, 742039, 860374, 223092, 475162, 17888, 958013, 612160, 164261, 982936, 820789, 290134, 984232, 502659, 244766, 373819, 550239, 540453, 458635, 172301, 796492, 25169, 148162, 597620, 934176, 461663, 389871, 8874, 447322, 29570, 900179, 882206, 222324, 505484, 759118, 229864, 575026, 223692, 479429, 625437, 604772, 393638, 679724, 789859, 287470, 543606, 901007, 693777, 583200, 601933, 376618, 599751, 463234, 520830, 332435, 950691, 650520, 457839, 519168, 734624, 189352, 549212, 9641, 233595, 924936, 302330, 479601, 10925, 868584, 945648, 276851, 253204, 810272, 846865, 242155, 376876, 258679, 416267, 520270, 538555, 751755, 252948, 600186, 663840, 650739, 869693, 241741, 274990, 815068, 693348, 78511, 699579, 83490, 878628, 33064, 872181, 991356, 734593, 140377, 524130, 384001, 122963, 942394, 151257, 288606, 518139, 487852, 427419, 986594, 657778, 750426, 870464, 827055, 601569, 831659, 575545, 39628, 648080, 828179, 470009, 992451, 675700, 803114, 326979, 349231, 673534, 298258, 911912, 552739, 487632, 715045, 461336, 661510, 372963, 36667, 570331, 296252, 511379, 104724, 828403, 333120, 63554, 960421, 222334, 887034, 189970, 959622, 972852, 567652, 187027, 632718, 820308, 25230, 430998, 947998, 167667, 112789, 286058, 134727, 420182, 256529, 558591, 178463, 213248, 399984, 679328, 950904, 463502, 328620, 754810, 46855, 385982, 433557, 841004, 90279, 247925, 1319, 146419, 740292, 311650, 176502, 213538, 44794, 309903, 971641, 45318, 991543, 138427, 274184, 641513, 754640, 837325, 315943, 765023, 793262, 128905, 741635, 199447, 414187, 869675, 968349, 596662, 372440, 738366, 348554, 737716, 820946, 946627, 120355, 964314, 342578, 291732, 866287, 92735, 899657, 733871, 758552, 125214, 78133, 572001, 246807, 709484, 735026, 817149, 371733, 369993, 120886, 365836, 404659, 186629, 592689, 226940, 331210, 853052, 673443, 504915, 66227, 264678, 714460, 30466, 687265, 576451, 663585, 859925, 518926, 514469, 682050, 700716, 998130, 172360, 927213, 702549, 542132, 367334, 384646, 732309, 956894, 954842, 800509, 955214, 147486, 144256, 130218, 73220, 270121, 878534, 557294, 579808, 192778, 860999, 34354, 579345, 712038, 988517, 787310, 512922, 59652, 112564, 281955, 919978, 730397, 130853, 489950, 479956, 847424, 273835, 683057, 92242, 972373, 764536, 949817, 596807, 459931, 381902, 798817, 210378, 436250, 346810, 625956, 768553, 934037, 916364, 53988, 306287, 187170, 157013, 972512, 187847, 963028, 413987, 62018, 860114, 15410, 551020, 619679, 137041, 243871, 82705, 192465, 146742, 426352, 330487, 192940, 579909, 685293, 674327, 69624, 652629, 117761, 281833, 391825, 426696, 35844, 82681, 355317, 70234, 532594, 324787, 323676, 219329, 553518, 151198, 120366, 692410, 416718, 13614, 306436, 758976, 707133, 62458, 431453, 141804, 86314, 659921, 311564, 801977, 614385, 139569, 232457, 778295, 686621, 21931, 660348, 537340, 316308, 623064, 548401, 792654, 21627, 842471, 3451, 521626, 839484, 922548, 583936, 586149, 124195, 579075, 581810, 282037, 516461, 91706, 577169, 866204, 394269, 775170, 717042, 123992, 772442, 680072, 664259, 568178, 738082, 45730, 559685, 100132, 409642, 514375, 437377, 319389, 279457, 94198, 439133, 936557, 151825, 509033, 118299, 135624, 580827, 962058, 361768, 948457, 651327, 178856, 615983, 757049, 819642, 841371, 505419, 974746, 957132, 152284, 732707, 283772, 981830, 485391, 355889, 422415, 6815, 464004, 666768, 847071, 580107, 882190, 905307, 981439, 142964, 303903, 392659, 984303, 603345, 807430, 446746, 423012, 919585, 789409, 617424, 280516, 573270, 805377, 779719, 942197, 206728, 644198, 23800, 809209, 843934, 239690, 516061, 435954, 945480, 873020, 890134, 865210, 849927, 550089, 490601, 927355, 656380, 24061, 301830, 563191, 244572, 856009, 119926, 14589, 414513, 411845, 817893, 714292, 912070, 550601, 951907, 893009, 323686, 722427, 77103, 734717, 290570, 24348, 282114, 375934, 972029, 519559, 741409, 636724, 978502, 246094, 479165, 29399, 850743, 334747, 835070, 266931, 130621, 639719, 110078, 811817, 333059, 475855, 202933, 727869, 709263, 62181, 146042, 76638, 300172, 874287, 347522, 821836, 479580, 749587, 193924, 553706, 282665, 449089, 805544, 765103, 18178, 498646, 77085, 176321, 548578, 914944, 122826, 395147, 257596, 766390, 669784, 808474, 276753, 320096, 552600, 754780, 424892, 608125, 23024, 343600, 686574, 355625, 550539, 154495, 772474, 694885, 251805, 194008, 251242, 845189, 267295, 68975, 692736, 640675, 117762, 260800, 244828, 497394, 592483, 499516, 298996, 597421, 484249, 280913, 833703, 591887, 191556, 315550, 211350, 424124, 624516, 13769, 697042, 729709, 337369, 87361, 659668, 182016, 267170, 116534, 707668, 98445, 297539, 965300, 496147, 706027, 388156, 916314, 280721, 12764, 618271, 12474, 636825, 721042, 277578, 542893, 146977, 16329, 33063, 32560, 584811, 655734, 240270, 306733, 42927, 261144, 867494, 188027, 762352, 64799, 39384, 797583, 544555, 508464, 439673, 77059, 202084, 786420, 439360, 43976, 384505, 482919, 863422, 686016, 391940, 362348, 762094, 706196, 941994, 594243, 511844, 547421, 435225, 449512, 269008, 810733, 485167, 511630, 143065, 788133, 990080, 447563, 431219, 388125, 646401, 324074, 903918, 286619, 767160, 385327, 181700, 10286, 338413, 545113, 531780, 969348, 972579, 171473, 338604, 168801, 217990, 851929, 415877, 56713, 664993, 50113, 518110, 795071, 255345, 408300, 406990, 158244, 451616, 667577, 590970, 31570, 519653, 671592, 294546, 452556, 211531, 489066, 772034, 303006, 291100, 399633, 315916, 636596, 999374, 214986, 9690, 535418, 132937, 618905, 473179, 326347, 722238, 497815, 600027, 17902, 241136, 368046, 547089, 716709, 51938, 726630, 48503, 865338, 464453, 551252, 948068, 465158, 668072, 188030, 240601, 552471, 250080, 329623, 941364, 307795, 576633, 658253, 402567, 72301, 8221, 265606, 358722, 605809, 705286, 335931, 288381, 11030, 214004, 703786, 788937, 834347, 622103, 83550, 191954, 780652, 435897, 547150, 17993, 550642, 446385, 340653, 257992, 189881, 316233, 255824, 492197, 445270, 703692, 255088, 637874, 873219, 201162, 459421, 455546, 151949, 557417, 748640, 972437, 421541, 736102, 35726, 997791, 640924, 875515, 207318, 828031, 130048, 638735, 978877, 402252, 145934, 207621, 518322, 615310, 471633, 214769, 578820, 132712, 866855, 710596, 826666, 975976, 313600, 118954, 231868, 104182, 233321, 556777, 803263, 416333, 361269, 496134, 209319, 761838, 725042, 954835, 14885, 52574, 428710, 997772, 309856, 868537, 599077, 835940, 423466, 538834, 958659, 299271};

	private static int[] killer = {79,519611,786418,784564,614337,640641,775822,257637,468385,873509,832999,539570,954873,78780,186684,277090,397399,628276,392912,111513,427228,56829,762190,100803,386650,734700,960554,498911,281123,763230};

  static int n; 
  static int x[]; 
  static int sets[]; 
  static int perfCounter = 0;

  public static int[] divideTopCoder(int[] x) { 
    n = Math.min(x.length, 25); 
    DivideJewelry.x = x; 
    sets = new int[n * 1000000 + 1]; 
    Arrays.fill(sets, -1); 
    int res[] = go(0, 0, 0); 
    return res == null ? new int[0] : res; 
  } 

  static int[] go(int pos, int cset, int csum) { 
    if (pos < n) { 
      int[] res = go(pos + 1, cset, csum); 
      if (res != null) { 
        return res; 
      } 
      res = go(pos + 1, cset | (1 << pos), csum + x[pos]); 
      return res; 
    } else { 
      if (sets[csum] == -1) { 
        sets[csum] = cset; 
        return null; 
      } else { 
        int nset = sets[csum]; 
        int res[] = new int[x.length]; 
        for (int i = 0; i < n; i++) { 
          res[i] = (((cset & (1 << i)) != 0) ? 1 : 0) - (((nset & (1 << i)) != 0) ? 1 : 0); 
        } 
        return res; 
      } 
    } 
  } 

    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		System.out.println("Array: " + Arrays.toString(killer));
		int[] res = divideTopCoder(killer);
		System.out.println("Result TC: " + Arrays.toString(res));
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time TC: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
		
		startTime = System.currentTimeMillis();
		res = divide(killer);
		duration = System.currentTimeMillis() - startTime;
		System.out.println("Result my own: " + Arrays.toString(res));
		System.out.println();
		System.out.print("Processing time my own: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
    
    public static int[] divide(int[] x) {
		int totalSum = sumAllArray(x);
		Boolean[] initVisitedNodes = new Boolean[x.length];
		// Current speed & memory bottleneck in my solution.
		// TopCoder builds the entire visited node in bitwise int[], not a bad idea since x value is limited to 1 million,
		// so a solution permutation must fit in 20 bits in any case. TopCoder solution also stores the sum collision table
		// in an array instead of a hashtable. This can be done with the caveat that we will require 20 x 1M slots
		// (as we can have a case of 999999+999998+etc)
		Arrays.fill(initVisitedNodes, false);
		
		int[] res = divideAux(new Hashtable<Integer, Boolean[]>(), x, initVisitedNodes, 0, 0, totalSum);
		if(res == null) {
			res = new int[x.length];
			Arrays.fill(res, 0);
		}
		return res;
	}
	
	private static int[] divideAux(Hashtable<Integer, Boolean[]> res, int[] x, Boolean[] visitedNodes, int pos, int currentSum, int remainingSum) {
		// The detail that kills in this exercise. The problem statement only allows values from 1 to 1 million. All possible permutations in this range
		// for adding (or not) the elements are exhausted with only 20 elements (2^n - 1).
		// Checking only one permutation, we only get the each value (1:1). Checking 2 permutations, we have 3 possible values (10/01/11).
		// Checking 3 permutations, we have 7 possible values (100/010/001/110/101/011/111). Checking 20 permutations, we have 1MB - 1 possible values, thus
		// covering the entire range stated in the problem exercise.
		n = Math.min(x.length, 20); 
		
		if(currentSum > remainingSum || pos >= n) {
			return null;
		}
		int newSum = currentSum + x[pos];
		int[] finalRes = divideAux(res, x, visitedNodes, pos+1, currentSum, remainingSum);
		
		if(finalRes != null) {
			return finalRes;
		}
		
		Boolean[] newVisitedNodes = Arrays.copyOf(visitedNodes, visitedNodes.length);
		newVisitedNodes[pos] = true;
		if(res.containsKey(newSum)) {
			finalRes = processMatchFound(res.get(newSum), newVisitedNodes);
			if(finalRes != null) {
				return finalRes;
			}
		}
		res.put(newSum, newVisitedNodes);
		
		return divideAux(res, x, newVisitedNodes, pos+1, newSum, remainingSum-x[pos]);
	}
	
	private static int[] processMatchFound(Boolean[] visitedNodes1, Boolean[] visitedNodes2) {
		perfCounter++;
		if(perfCounter % 100 == 0) {
			System.out.println(perfCounter);
		}
		int[] res = new int[visitedNodes1.length];
		for(int i = 0; i < visitedNodes1.length; i++) {
			if(visitedNodes1[i] && visitedNodes2[i]) {
				return null;
			}
			if(visitedNodes1[i]) {
				res[i] = -1;
			} else if(visitedNodes2[i]) {
				res[i] = 1;
			} else {
				res[i] = 0;
			}
		}
		return res;
	}
	
	private static int sumAllArray(int[] x) {
		int sum = 0;
		for(int i = 0; i < x.length; i++) {
			sum += x[i];
		}
		return sum;
	}
	
}
