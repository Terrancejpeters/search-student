package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
	
	private List<Integer> initial;
	private List<Integer> solved;
	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	public EightPuzzle(List<Integer> startingValues) {
		// TODO
		initial = startingValues;
		solved = Arrays.asList(new Integer[] { 1, 2, 3,
				4, 5, 6, 7, 8, 0 });
	}

	@Override
	public List<Integer> getInitialState() {
		// TODO
		return initial;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> ListOLists = new ArrayList<List<Integer>>();
		int pos = currentState.indexOf(0);
		System.out.println("0 is at " + pos);
		if (pos == 0){
			ListOLists.add(swap(0,1,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(0,3,currentState));
			toString(ListOLists.get(1));
		}
		else if (pos == 1){
			ListOLists.add(swap(1,2,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(1,4,currentState));
			toString(ListOLists.get(1));
			ListOLists.add(swap(1,0,currentState));
			toString(ListOLists.get(2));
		}
		else if (pos == 2){
			ListOLists.add(swap(2,1,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(2,5,currentState));
			toString(ListOLists.get(1));
		}
		else if (pos == 3){
			ListOLists.add(swap(3,0,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(3,4,currentState));
			toString(ListOLists.get(1));
			ListOLists.add(swap(3,6,currentState));
			toString(ListOLists.get(2));
		}
		else if (pos == 4){
			ListOLists.add(swap(4,1,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(4,3, currentState));
			toString(ListOLists.get(1));
			ListOLists.add(swap(4,5,currentState));
			toString(ListOLists.get(2));
			ListOLists.add(swap(4,7,currentState));
			toString(ListOLists.get(3));
		}
		else if (pos == 5){
			ListOLists.add(swap(5,2,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(5,4,currentState));
			toString(ListOLists.get(1));
			ListOLists.add(swap(5,8,currentState));
			toString(ListOLists.get(2));
		}
		else if (pos == 6){
			ListOLists.add(swap(6,3,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(6,7,currentState));
			toString(ListOLists.get(1));
		}
		else if (pos == 7){
			ListOLists.add(swap(7,4,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(7,6,currentState));
			toString(ListOLists.get(1));
			ListOLists.add(swap(7,8,currentState));
			toString(ListOLists.get(2));
		}
		else if (pos == 8){
			ListOLists.add(swap(8,5,currentState));
			toString(ListOLists.get(0));
			ListOLists.add(swap(8,7,currentState));
			toString(ListOLists.get(1));
		}
		for (int i = 0; i < ListOLists.size(); i++){
			System.out.println(ListOLists.get(i));
		}
		return ListOLists;
	}
	
	private List<Integer> swap(int a, int b, List<Integer> input){
		List<Integer> tList = input;
		int temp = tList.get(a);
		tList.set(a, tList.get(b));
		tList.set(b, temp);
		return tList;
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		if (state.equals(solved)){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
	
	public void toString(List<Integer> state){
		System.out.println(" | " +state.get(0) + " | " + state.get(1) +" | "  + state.get(2)+ " | ");
		System.out.println(" | " +state.get(3) + " | " + state.get(4) +" | " + state.get(5)+  " | " );
		System.out.println(" | " +state.get(6) + " | " + state.get(7) +" | " +  state.get(8) + " | ");
		System.out.println();
	}
}
