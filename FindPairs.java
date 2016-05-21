import java.util.ArrayList;

/*
 * File: FindPairs.java
 * -----------------------------
 * This class finds all pairs of integers in a list that, when operated upon, result in the goal.
 */

public class FindPairs {
	
	private static int goal = 24; // Enter a number.
	private static String operation = "multiply"; // Enter either "add" or "multiply."
	
	public static void main(String[] args) {
		ArrayList<Integer> list = arrayInit();
		findPairs(list, goal, operation); // {(8, 3), (2, 12)}
	}
	
	public static ArrayList<Integer> arrayInit() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(2);
		list.add(4);
		list.add(8);
		list.add(3);
		list.add(12);
		list.add(15);
		list.add(12);
		list.add(9);
		return list;
	}
	
	// Finds all pairs of integers in a list that, when operated upon, result in the goal.
	public static void findPairs(ArrayList<Integer> list, int goal, String operation) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<String> displayed = new ArrayList<String>();
		int pairCount = 0, computation = 0;
		System.out.print("{");
		for (int i = 0; i < list.size(); i++) {
			if (operation == "add") computation = goal - list.get(i);
			if (operation == "multiply") {
				computation = goal / list.get(i);
				double computationAsDouble = 1.0 * goal / list.get(i);
				if (computationAsDouble != computation) continue;
				computation = goal / list.get(i);
			}
			String a = "" + computation + "," + list.get(i);
			String b = "" + list.get(i) + "," + computation;
			if (visited.contains(computation) && !displayed.contains(a) && !displayed.contains(b)) {
				if (pairCount > 0) System.out.print(", ");
				System.out.print("(" + computation + ", " + list.get(i) + ")");
				displayed.add(a);
				displayed.add(b);
				pairCount++;
			}
			if (!visited.contains(list.get(i))) visited.add(list.get(i));
		}
		System.out.print("}");
	}

}