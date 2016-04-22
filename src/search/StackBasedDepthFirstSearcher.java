package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search, storing
 * the list of next states in a Stack. This results in a depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

	private final List<T> predecessors;

	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);

		predecessors = new ArrayList<T>();
	}

	@Override
	public List<T> findSolution() {
		if (solution != null)
			return solution;
		List<T> toReturn = new ArrayList<T>();
		Stack<T> toExplore = new Stack<T>();
		toExplore.push(searchProblem.getInitialState());
		while (!toExplore.isEmpty()) {
			T currentState = toExplore.peek();
			visited.add(currentState);
			//get all immediate neighbors
			if (searchProblem.isGoal(currentState)){
				break;
			}
			List<T> neighbors = searchProblem.getSuccessors(currentState);
			if (neighbors.size() == 0){
				toExplore.pop();
			}
			else{
				int visitedCheck = 0;
				for (int i = 0; i < neighbors.size(); i++){
					if (!visited.contains(neighbors.get(i))){
						toExplore.push(neighbors.get(i));
						break;
					}
					else{
						visitedCheck++;
					}
				}
				//visitedCheck adds one for every visited neighbor
				//if visitedCheck == neighbors.size, then every neighbor is visited, therefore you need to remove the currentState from stack
				if (visitedCheck == neighbors.size()){
					toExplore.pop();
				}
			
			}
		}
		while(!toExplore.isEmpty()){
			toReturn.add(0, toExplore.pop());
		}
		return toReturn;
	}
	/*
	 * push A to stack while(stack not empty) { v = stack.pop(); print/visit v
	 * mark v as visited iterate through v’s neighbors { if a neighbor is not
	 * marked yet push it to stack } }
	 */
}
