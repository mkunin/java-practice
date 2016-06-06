import java.io.*;
import java.util.Scanner;

/*
 * File: ReverseGame.java
 * --------------------------------------------------
 * https://www.hackerrank.com/challenges/reverse-game
 * --------------------------------------------------
 * Input:
 * 2
 * 3 1
 * 5 2
 * 
 * Output:
 * 2
 * 4
 */

public class ReverseGame {

	private static String nameOfFile = "input00.txt";
	private static String inputSource = "file"; // Enter either "STDIN" or "file."
	
	public static void main(String[] args) {
		if (inputSource == "file") {
			useFile();
		} else if (inputSource == "STDIN") {
			useSTDIN();
		}
	}
	
	// Gets input using a text file.
	public static void useFile() {
		BufferedReader aBuffReader = null;
		try {
			aBuffReader = new BufferedReader(new FileReader(nameOfFile));
			int numOfTests = Integer.parseInt(aBuffReader.readLine());
			for (int i = 0; i < numOfTests; i++) {
				String line = aBuffReader.readLine();
				String[] result = line.split(" ");
				int n = Integer.parseInt(result[0]);
				int k = Integer.parseInt(result[1]);
				playGame(n, k);
				if (i+1 < numOfTests) System.out.print("\n");
			}
			aBuffReader.close();
		} catch (IOException e) {
			System.out.print("Error: " + e);
		}
	}
	
	// Gets input using STDIN.
	public static void useSTDIN() {
		Scanner input = new Scanner(System.in);
		int numOfTests = input.nextInt();
		for (int i = 0; i < numOfTests; i++) {
			int n = input.nextInt();
			int k = input.nextInt();
			playGame(n, k);
			if (i+1 < numOfTests) System.out.print("\n");
		}
	}
	
	// Creates an array.
	public static int[] arrayInit(int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}
		return array;
	}
	
	// Plays the Reverse Game and displays the result.
	public static void playGame(int n, int k) {
		int[] array = arrayInit(n);
		reverseGame(array, 0);
		displayOutput(array, n, k);
	}
	
	// Plays the Reverse Game.
	public static void reverseGame(int[] array, int startIndex) {
		int i = startIndex, j = 0, m = 0, temp = startIndex;
		int[] backendArray = new int[array.length-startIndex];
		while (j < backendArray.length) {
			backendArray[j] = array[i];
			i++;
			j++;
		}
		backendArray = reverseAllOf(backendArray);
		while (temp < array.length) {
			array[temp] = backendArray[m];
			m++;
			temp++;
		}
		if (startIndex < array.length-1) reverseGame(array, startIndex+1);
	}
	
	// Reverses an array.
	public static int[] reverseAllOf(int[] backendArray) {
		int[] secondaryArray = new int[backendArray.length];
		int pos = backendArray.length-1;
		for (int i = 0; i < backendArray.length; i++) {
			secondaryArray[pos] = backendArray[i];
			pos--;
		}
		return secondaryArray;
	}
	
	// Displays the output of the Reverse Game.
	public static void displayOutput(int[] array, int n, int k) {
		for (int i = 0; i < n; i++) {
			if (array[i] == k) {
				System.out.print(i);
				break;
			}
		}
	}
	
}