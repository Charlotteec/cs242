package cs242;

import java.util.ArrayList;
import java.util.Scanner;

/*************************************************************************
 *
 * Pace University Fall 2018 Algorithms
 *
 * Course: CS 242 Author: Charlotte Coffin Collaborators: Sammy Chen Li
 * References: assignment 2
 *
 * Assignment: 1 Problem: TIme difference of brute force vs. divide and conquer
 * Description: This program implements two algorithms to find the Max Sum Sub
 * array
 *
 * Input: array size Output: running times to find maximum sum of a subarray
 *
 * Visible data fields: none.
 *
 * Visible methods: public static void main(String[] args) 
 * public static void quickSort(int[] A, int p, int r) 
 * public static ArrayList<Integer> BucketSort(int[] A) 
 * public static int partition(int[]A, int p, int r) 
 * public static ArrayList<Integer> sort(ArrayList<Integer>, A)
 * public static void TestOne(int size, int r)
 * public static void TestTwo()
 * 
 * 
 * QuickSort: 
 * | 	|10		| 100 	| 1000	| 100000 | 1000000 | 10000000 |
 * | 2	|7213	|109399 |220687	|1028397 |14136764 |170109216 |
 * | 6	|12825	|260899	|224674	|1281858 |16716468 |202665828 |
 * | 18	|12700	|222462	|185437	|2012731 |24957489 |286705602 |
 * | 54	|12987	|449774	|300799	|3775262 |94065726 |786964309 |
 * |162 |12800	|66525	|554148	|10765376|161386420|1633895952|
 * |486 |13374	|68462	|4906696|34368056|491218948|3416503287|
 * 
 * BucketSort:
 * | 	|10		| 100 	| 1000	| 100000 | 1000000 | 10000000 |
 * | 2	|176375	|1233458|5018870|13217905|111579829|2138726561|
 * | 6	|134487	|5182195|3365139|11062037|95771621 |1436702488|
 * | 18	|426111	|941597 |2416142|6552840 |23553369 |356019190 |
 * | 54	|157387 |607110 |1720782|7211400 |43094077 |338868924 |
 * |162 |264474 |668873 |2789766|5311507 |47008351 |338345338 |
 * |486 |122225 |529623 |2951577|5735256 |15824920 |279226291 |
 *
 * Remarks:
 * 
 * This table shows some interesting things. I would expect that quickSort and BucketSort both would
 * increase in time as the n increased, which is shown in this data. Additionally, I expected both sorts 
 * to increase in time as r increased. However, while quick sort acted as expected, bucket sort got faster 
 * the more repetitions there were. It did follow my expectations and get slower with respect to n, but this
 * is an interesting observation. As we sort each individual list in bucket sort though, it makes sense 
 * it would be faster to sort lists that are all the same number, than lists of different numbers. 
 * 
 * Sources:
 * https://www.csc.depauw.edu/~dharms/CS1-Myro-Java/BlueJ_Projects-F11/SortingAndSearching/InsertionSort.java
 *
 *************************************************************************/

public class AssignmentThree {

	private static ArrayList<Integer> sort(ArrayList<Integer> arr) {
		int val;
		int holePos;

		for (int k = 1; k < arr.size(); k++) {
			val = arr.get(k);
			holePos = k;
			while (holePos > 0 && arr.get(holePos - 1) > val) {
				arr.set(holePos, arr.get(holePos - 1));
				holePos--;
			}
			arr.set(holePos, val);
		}
		return arr;
	}

	public static int partition(int[] A, int p, int r) {

		int x = A[r];
		int i = p - 1;
		for (int j = p; j <= (r - 1); j++) {
			if (A[j] <= x) {
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		int temp = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp;
		return i + 1;
	}

	public static void quickSort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);
		}

	}

	public static ArrayList<Integer> BucketSort(double[] A) {
		int n = A.length;
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>(n + 1);
		for (int i = 0; i < n; i++) {
			A[i] = A[i] / n;
		}
		for (int i = 0; i < n + 1; i++) {
			B.add(i, new ArrayList<Integer>());
		}
		for (int i = 0; i <= (n - 1); i++) {
			ArrayList<Integer> temp = B.get((int) (n * A[i]));
			temp.add((int) ((int) n * A[i]));
			B.set((int) (n * A[i]), temp);
		}

		for (int i = 0; i < n; i++) {
			sort(B.get(i));
			sorted.addAll(B.get(i));

		}
		return sorted;
	}

	public static void printArray(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i] + ", ");
		}
	}

	public static void printArray(double[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i] + ", ");
		}
	}

	public static void testOne(int size, int r) {
		// create the array
		double[] numbers = new double[size];
		int[] numberz = new int[size];
		int i = 0;
		int j = 0;
		while (i < size) {
			// generate a random number between -10 and 10 and store it in array
			int temp = (int) (Math.random() * (size));
			// System.out.println("temp: " + temp);
			// generate a random number for amount of repititions
			int reps = (int) (Math.random() * r);
			// System.out.println("r: " + reps);
			while (j < reps && i < size) {
				numbers[i] = temp;
				numberz[i] = temp;
				// System.out.println( numberz[i]);
				j++;
				i++;
			}
			j = 0;
		}
		System.out.println("n: " + size + " r: " + r);
		long startTime = System.nanoTime();
		// quickSort the numbers
		quickSort(numberz, 0, numberz.length - 1);

		// display the time elapsed
		long now = System.nanoTime();
		System.out.println("QuickSort: " + (now - startTime));

		// System.out.println("The time taken by QuickSort with size " + size+ " and
		// repititions "+ r + " is " + (now - startTime) + " nanoseconds.");

		// prepare to measure the time elapsed again
		startTime = System.nanoTime();
		BucketSort(numbers);
		// display the time elapsed
		now = System.nanoTime();
		System.out.println("BucketSort: " + (now - startTime));
	}

	public static void testTwo() {
		int size = 10;
		for (int i1 = 0; i1 < 6; i1++) {

			int r =2;
			for (int i2 = 0; i2 < 6; i2++) {

				// create the array
				double[] numbers = new double[size];
				int[] numberz = new int[size];

				int i = 0;
				int j = 0;
				while (i < size) {
					// generate a random number between -10 and 10 and store it in array
					int temp = (int) (Math.random() * (size));
					// System.out.println("temp: " + temp);
					// generate a random number for amount of repititions
					int reps = (int) (Math.random() * r);
					// System.out.println("r: " + reps);
					while (j < reps && i < size) {
						numbers[i] = temp;
						numberz[i] = temp;
						// System.out.println( numberz[i]);
						j++;
						i++;
					}
					j = 0;
				}
				System.out.println("n: " + size + " r: " + r);
				// store the time now
				long startTime = System.nanoTime();
				// quickSort the numbers
				quickSort(numberz, 0, numberz.length - 1);

				// display the time elapsed
				long now = System.nanoTime();
				System.out.println("QuickSort: " + (now - startTime));

				// System.out.println("The time taken by QuickSort with size " + size+ " and
				// repititions "+ r + " is " + (now - startTime) + " nanoseconds.");

				// prepare to measure the time elapsed again
				startTime = System.nanoTime();
				BucketSort(numbers);
				// display the time elapsed
				now = System.nanoTime();
				System.out.println("BucketSort: " + (now - startTime));

				// System.out.println("The time taken by BucketSort is " + (System.nanoTime() -
				// startTime) + " nanoseconds.");
				r = r * 3;

			}
			size = size * 10;

		}

	}

	public static void main(String[] args) {

		// input array size from user
		Scanner input = new Scanner(System.in);
		System.out.print("Enter array size: ");
		int size = input.nextInt();
		System.out.println();
		System.out.print("Enter repititions: ");
		int r = input.nextInt();
		System.out.println();
		
		System.out.println("test one: ");
		testOne(size, r);
		
		//System.out.println("test two: ");
		//testTwo();

	}
}
