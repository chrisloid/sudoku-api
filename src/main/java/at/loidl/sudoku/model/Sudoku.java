package at.loidl.sudoku.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sudoku {

	private int values[][];
	private int layout[][];
	private long durationSolvedInMillis;
	private boolean solved;
	private String valuesAsStringArray[];

	public Sudoku(int[][] values, int[][] layout) {
		super();
		this.values = values;
		this.layout = layout;
	}
	
	public int[][] getValues() {
		return values;
	}
	public int[][] getLayout() {
		return layout;
	}

	public long getDurationSolvedInMillis() {
		return durationSolvedInMillis;
	}

	public void setDurationSolvedInMillis(long durationSolvedInMillis) {
		this.durationSolvedInMillis = durationSolvedInMillis;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	

	public String[] getValuesAsStringArray() {
		return valuesAsStringArray;
	}

	public void updateValuesAsStringArray() {
		this.valuesAsStringArray = new String[81];
		int index=0;
		for (int i=0; i<9;i++) {
			for (int j=0;j<9;j++) {
				this.valuesAsStringArray[index]= ""+values[i][j];
				index++;
			}
		}

	}

	public static int[][] convertToArray(String input) {
		try {
			int result[][] = new int[9][9];
			
					
			int index=0;
			for (int i=0; i<9;i++) {
				for (int j=0;j<9;j++) {
					if (index>=input.length()) return result;
					result[i][j]=Integer.parseInt(input.substring(index, index+1));
					index++;
				}
			}
			return result;
		} catch (NumberFormatException ex) {
			return null;
		}
		
	}
	
	
}
