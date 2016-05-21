import java.util.Scanner;

/*
 * File: Anagram.java
 * -----------------------------
 * This class determines if two strings are anagrams of each other.
 */

public class Anagram {
	
	private static String option = "n"; // Enter either "n^2" or "n."
	
	public static void main(String[] args) {
		System.out.print("::: Anagram Detection :::\n\n");
		int[] counter = new int[1];
		counter[0] = 0;
		String string1 = stringInit(counter);
		String string2 = stringInit(counter);
		boolean result;
		if (option.equals("n")){ // Runtime: O(n)
			result = histogramCheck(string1, string2);
			show(result, string1, string2);
		} else if (option.equals("n^2")) { // Runtime: O(n^2)
			result = isAnagram(string1, string2) && isAnagram(string2, string1);
			show(result, string1, string2);
		}
	}
	
	// Frequency Histogram Method - [Runtime: O(n)]
	// Based on http://web.stanford.edu/class/cs9/lectures/06/Anagrams.pdf
	public static boolean histogramCheck(String first, String second) {
		if (first.length() != second.length()) return false;
		int[] frequencies = new int[26];
		for (int i = 0; i < first.length(); i++) {
			frequencies[first.charAt(i) - 'a']++;
		}
		for (int i = 0; i < second.length(); i++) {
			if (frequencies[second.charAt(i) - 'a'] == 0) return false;
			frequencies[second.charAt(i) - 'a']--;
		}
		return true;
	}
	
	// Determines if every character in string1 exists in string2 - [Runtime: O(n^2)].
	public static boolean isAnagram(String string1, String string2) {
		if (string1.length() != string2.length()) {
			return false;
		}
		for (int i = 0; i < string1.length(); i++) {
			boolean charMatch = false;
			for (int j = 0; j < string2.length(); j++) {
				if (string1.charAt(i) == string2.charAt(j)) {
					charMatch = true;
					break;
				}
			}
			if (charMatch == false) {
				return false;
			}
		}
		return true;
	}
	
	// Asks the user to enter a word.
	public static String stringInit(int[] counter) {
		if (counter[0] == 0) {
			System.out.print("Enter a word: ");
		} else {
			System.out.print("Enter another word: ");
		}
		counter[0]++;
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
	
	// Outputs whether or not string1 and string2 are anagrams of each other.
	public static void show(boolean result, String string1, String string2) {
		if (result == true) {
			System.out.print("\nResult: Yes, " + string1 + " and " + string2 + " are anagrams of each other.");
		} else {
			System.out.print("\nResult: No, " + string1 + " and " + string2 + " are not anagrams of each other.");
		}
	}
	
}