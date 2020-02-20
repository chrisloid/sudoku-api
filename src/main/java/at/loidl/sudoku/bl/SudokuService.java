package at.loidl.sudoku.bl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import at.loidl.sudoku.model.Sudoku;

public class SudokuService {
	
	private static final int GAME_SIZE = 9;
	private static final int MAX_CELL_INDEX = GAME_SIZE*GAME_SIZE;
	
	private int cells[][];
	private SudokuSet rows[];
	private SudokuSet columns[];
	private SudokuSet areas[];
	private int layout[][];
	
	public Sudoku solve(Sudoku sudoku) {
		init(sudoku.getValues(), sudoku.getLayout());
		
		long start = System.currentTimeMillis();
		boolean solved = findNextCellValue(0);
		long end = System.currentTimeMillis();
		
		Sudoku result = new Sudoku(cells, layout);
		result.setSolved(solved);
		result.setDurationSolvedInMillis(end-start);
		result.updateValuesAsStringArray();
		return result;
	}
	
	private void init(int knownValues[][] , int layout[][]) {
		this.cells = new int[GAME_SIZE][GAME_SIZE]; 
		
		this.layout = layout;
		
		this.columns = SudokuSet.createArray(GAME_SIZE);
		this.rows = SudokuSet.createArray(GAME_SIZE);
		this.areas = SudokuSet.createArray(GAME_SIZE);
		

		for (int row=0; row<GAME_SIZE; row++) {
			for (int col=0; col<GAME_SIZE; col++) {
				setCellValue(row,col,knownValues[row][col]);
			}
		}
	}

	private void setCellValue(int rowIndex, int colIndex, int value) {
		int areaIndex = calculateAreaIndex(rowIndex,colIndex);
		SudokuSet currentRow = this.rows[rowIndex];
		SudokuSet currentColumn = this.columns[colIndex];
		SudokuSet currentArea = this.areas[areaIndex];
		cells[rowIndex][colIndex] = value;
		currentRow.add(value);
		currentColumn.add(value);
		currentArea.add(value);
	}
	
	private boolean findNextCellValue(int cellIndex) {
		if (cellIndex >= MAX_CELL_INDEX) return true;
		
		int rowIndex = cellIndex/GAME_SIZE;
		int colIndex = cellIndex%GAME_SIZE;
		int areaIndex = calculateAreaIndex(rowIndex,colIndex);

		if (cells[rowIndex][colIndex]!=0) {
			return findNextCellValue(cellIndex + 1);
		} else {
			
			SudokuSet currentRow = this.rows[rowIndex];
			SudokuSet currentColumn = this.columns[colIndex];
			SudokuSet currentArea = this.areas[areaIndex];
	
			Set<Integer> possible = new HashSet<Integer>();
			for (int i=1; i<=9; i++) {
				possible.add(i);
			}
			possible.removeAll(currentRow.getValues());
			possible.removeAll(currentColumn.getValues());
			possible.removeAll(currentArea.getValues());
			
			if (possible.size()==0) return false;
			
			Iterator<Integer> it = possible.iterator();
			
			while (it.hasNext()) {
				Integer value = it.next();
				cells[rowIndex][colIndex] = value.intValue();
				currentRow.add(value);
				currentColumn.add(value);
				currentArea.add(value);
				
				if (findNextCellValue(cellIndex + 1)) {
					return true;
				} else {
					cells[rowIndex][colIndex] = 0;
					currentRow.remove(value);
					currentColumn.remove(value);
					currentArea.remove(value);
				}
			}
			return false;
		}
	}

	private int calculateAreaIndex(int rowIndex, int colIndex) {
		if (this.layout==null) {
			return (rowIndex/3)*3 + (colIndex/3);
		} else {
			return layout[rowIndex][colIndex] -1;
		}
	}
	
}
