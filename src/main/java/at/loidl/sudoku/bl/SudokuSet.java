package at.loidl.sudoku.bl;

import java.util.HashSet;
import java.util.Set;

public class SudokuSet {
	
	private Set<Integer> values = new HashSet<Integer>();
	
	public Set<Integer> getValues() {
		return values;
	}
	
	public boolean contains(int value) {
		return values.contains(value);
	}
	
	public static SudokuSet[] createArray(int size) {
		SudokuSet result[] = new SudokuSet[size];  
		for (int i=0; i<size; i++) {
			result[i] = new SudokuSet();
		}
		return result;
	}

	public void add(Integer value) {
		values.add(value);
	}

	public void remove(Integer value) {
		values.remove(value);

	}
}
