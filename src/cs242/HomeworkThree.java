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
 * Visible methods: public static void main(String[] args) public static void
 * quickSort(int[] A, int p, int r) public static ArrayList<Integer>
 * BucketSort(int[] A) public static int partition(int[]A, int p, int r) public
 * static ArrayList<Integer> sort(ArrayList<Integer>, A)
 * 
 *
 *
 * Remarks I will do the rest soon 
 *
 * 
 * 
 * Sources:
 * https://www.csc.depauw.edu/~dharms/CS1-Myro-Java/BlueJ_Projects-F11/SortingAndSearching/InsertionSort.java
 *
 *************************************************************************/

public class HomeworkThree {

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

	public static void main(String[] args) {

		// input array size from user
		Scanner input = new Scanner(System.in);
		System.out.print("Enter array size: ");
		int size = input.nextInt();
		System.out.println();
		System.out.print("Enter repititions: ");
		int r = input.nextInt();
		System.out.println();
		

		// create the array
		double[] numbers = new double[size];
		int[] numberz = new int[size];

		int i = 0;
		int j = 0;
		while (i < size) {
			// generate a random number between -10 and 10 and store it in array
			int temp = (int) (Math.random() * (size));
//			System.out.println("temp: " + temp);
			// generate a random number for amount of repititions
			int reps = (int) (Math.random() * r);
//			System.out.println("r: " + reps);
			while(j<reps && i <size) {
				numbers[i] = temp;
				numberz[i] = temp;
//				System.out.println( numberz[i]);
				j++;
				i++;
			}
			j=0;
		}
		// store the time now
		long startTime = System.nanoTime();
		// quickSort the numbers
		quickSort(numberz, 0, numberz.length - 1);

		// display the time elapsed
		long now = System.nanoTime();

		System.out.println("The time taken by QuickSort is " + (now - startTime) + " nanoseconds.");

		// prepare to measure the time elapsed again
		startTime = System.nanoTime();
		BucketSort(numbers);
		// display the time elapsed
		System.out.println("The time taken by BucketSort is " + (System.nanoTime() - startTime) + " nanoseconds.");

	}

}
