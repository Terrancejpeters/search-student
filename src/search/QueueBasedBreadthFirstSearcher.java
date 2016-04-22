package search;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		if (solution != null)
			return solution;
		Queue<T> Q = new LinkedList<T>();
		List<T> Potential = new ArrayList<T>();
		T current;
		List<T> Visited = new ArrayList<T>();
		Q.add(searchProblem.getInitialState());
		while(!Q.isEmpty()){
			current = Q.remove();
			Visited.add(current);
			if (searchProblem.isGoal(current)){
				Potential.add(current);
				break;
			}
			List<T> childs = searchProblem.getSuccessors(current);
			if (childs.size() > 0){
				Potential.add(current);
				for (int i =0; i <childs.size();i++){
					if (!Visited.contains(childs.get(i))){
						Q.add(childs.get(i));
					}
				}
			}
		}
		int i = Potential.size()-1;
		while ( i > 0){
			current = Potential.get(i);
			T prev = Potential.get(i-1);
			if (!searchProblem.getSuccessors(prev).contains(current)){
				Potential.remove(prev);
			}
			i--;
		}
		return Potential;
	}
}
