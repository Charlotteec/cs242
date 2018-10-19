package cs242;

/*************************************************************************
 *
 *  Pace University
 *  Fall 2018
 *  Algorithms
 *
 *  Course: CS 242
 *  Author: Charlotte Coffin
 *  Collaborators: none
 *  References: assignment 1
 *
 *  Assignment: 1
 *  Problem: TIme difference of brute force vs. divide and conquer
 *  Description: This program implements two algorithms to find the 
 *  			Max Sum Sub array
 *
 *  Input: array size
 *  Output: running times to find maximum sum of a subarray
 *
 *  Visible data fields:
 * none.
 *
 *  Visible methods:
 * public static void main(String[] args)
 * public static boolean bruteForce(int[] a)
 * public static boolean maxSubArray(int[] a, int high, int low)
 *
 *
 *   Remarks
 *   -------
 *
 *   Chart of running times observed in nanoseconds:
 *
 *   Size     |    Brute Force Time   	|    Divide and Conquer Time
 *  -------------------------------------------------------------------
 *  10	      |     158334              |    49722
 *  -------------------------------------------------------------------
 *  100       |     707800              |    204501
 *  -------------------------------------------------------------------
 *  1000      |     8326661             |    939392
 *  -------------------------------------------------------------------
 *  10000     |     69840512            |    2590432
 *  -------------------------------------------------------------------
 *  
 *  
 *  These numbers show that the Brute Force algorithm is increasing significantly 
 *  faster than the Divide and Conquer algorithm. Each time we ad more 0's to the 
 *  size of the array the brute force algorithm is at increasingly approximately 
 *  quadratically, where Divide and Conquer is increasing at a much slower rate. 
 *  Additionally, the difference in times between the two algorithms increases as
 *  we increase the size, indicating that brute force is growing much faster. 
 *
 *************************************************************************/

import java.util.Scanner;

public class HomeworkTwo {

	public static int bruteForce(int[] arr) {

		int sum = 0;
		int sMax = 0;
		//for every number in the array reset sum to 0
		for (int i = 0; i < arr.length; i++) {
			sum = 0;
			//add every other number to the first letter, thus giving every possible sum
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				//sMax = the maximum sum at this point
				sMax = Math.max(sum, sMax);
			}
		}
		//return the max when the loops are done 
		return sMax;

	}

	public static int maxSubArray(int[] arr, int low, int high) {
		if (high == low) {//base case
			return arr[low];
		} else {//recursive step
			int mid = (low + high) / 2;
			int leftSum = maxSubArray(arr, low, mid);
			int rightSum = maxSubArray(arr, mid + 1, high);
			int crossSum = maxCrossingSubArray(arr, low, mid, high);
			//return the largest sum
			return Math.max(Math.max(leftSum, rightSum), crossSum);
		}
	}

	//assistant for maxSubArray
	public static int maxCrossingSubArray(int[] arr, int low, int mid, int high) {
		int leftSum = 0;
		int sum = 0;
		for (int i = mid; i >= low; i--) {
			sum = sum + arr[i];
			if (sum >= leftSum) {
				leftSum = sum;
			}
		}
		int rightSum = 0;
		int sum2 = 0;
		for (int j = mid + 1; j <= high; j++) {
			sum2 = sum2 + arr[j];
			if (sum2 >= rightSum) {
				rightSum = sum2;
			}
		}
		return leftSum + rightSum;

	}

	public static void main(String[] args) {

		// input array size from user
		Scanner input = new Scanner(System.in);
		System.out.print("Enter array size: ");
		int size = input.nextInt();
		System.out.println();

		// create the array 
		int[] numbers = new int[size];
		for (int i = 0; i < numbers.length; i++) {
			//generate a random number between -10 and 10 and store it in array
			numbers[i] = (int) (Math.random() * 20) - (20 / 2);
		}

		// store the time now
		long startTime = System.nanoTime();
		//show max sum
		System.out.println("brute force: " + bruteForce(numbers));
		// display the time elapsed
		System.out.println("The time taken by Brute Force is " + (System.nanoTime() - startTime) + " nanoseconds.");

		// prepare to measure the time elapsed again
		startTime = System.nanoTime();
		//Show max sum
		System.out.println("divide and conquer: " + maxSubArray(numbers, 0, size - 1));
		// display the time elapsed
		System.out.println(
				"The time taken by Divide and Conquer is " + (System.nanoTime() - startTime) + " nanoseconds.");
	}

}
