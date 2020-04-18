package com.wj.leetcode.backtracking;

public class SudoKu_37 {

	/*
	 * 37. Sudoku Solver Write a program to solve a Sudoku puzzle by filling the
	 * empty cells.
	 * 
	 * A sudoku solution must satisfy all of the following rules:
	 * 
	 * Each of the digits 1-9 must occur exactly once in each row. Each of the
	 * digits 1-9 must occur exactly once in each column. Each of the the digits 1-9
	 * must occur exactly once in each of the 9 3x3 sub-boxes of the grid. Empty
	 * cells are indicated by the character '.'.
	 * 
	 */

	/*
	 * 题解详细解法：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-
	 * leetcode/
	 */

	
	
	/*
	 * 回溯法基本就是穷举，原理：
	 *    从第0行0列开始，依次往里面填入1-9这几个数字，然后判断该数字是否可以放进去（该行该列和该九宫格是否有重复的数字）
	 *    如果能放进去，就继续用1-9去试该行的下一列；一直到该行的最后一列，然后换行继续重复上面的步骤；
	 *    如果放不进去，就退回到上一步骤，把刚才放的数字移除，继续尝试下一数字
	 *    直到最后一个格，也就是i=8,j=8的时候，所有都满足要求，输出正确的解即可。
	 *    
	 */
	
	
	private void printBoard(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
//		char[][] board = new char[][] { 
//			    { '.', '.', '4', '.', '.', '.', '8', '.', '2' },
//				{ '3', '5', '7', '.', '8', '.', '.', '9', '.' }, 
//				{ '8', '.', '2', '.', '.', '.', '.', '.', '.' },
//				{ '.', '2', '.', '9', '4', '5', '.', '.', '.' }, 
//				{ '.', '.', '.', '.', '.', '6', '.', '.', '4' },
//				{ '9', '.', '.', '.', '.', '8', '2', '.', '7' }, 
//				{ '.', '.', '.', '.', '.', '9', '.', '6', '.' },
//				{ '5', '.', '.', '3', '6', '.', '4', '.', '.' }, 
//				{ '.', '6', '.', '5', '.', '.', '.', '.', '.' } };
		char[][] board = new char[][] { 
		    { '8', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '3', '6', '.', '.', '.', '.', '.' }, 
			{ '.', '7', '.', '.', '9', '.', '2', '.', '.' },
			{ '.', '5', '.', '.', '.', '7', '.', '.', '.' }, 
			{ '.', '.', '.', '.', '4', '5', '7', '.', '.' },
			{ '.', '.', '.', '1', '.', '.', '.', '3', '.' }, 
			{ '.', '.', '1', '.', '.', '.', '.', '6', '8' },
			{ '.', '.', '8', '5', '.', '.', '.', '1', '.' }, 
			{ '.', '9', '.', '.', '.', '.', '4', '.', '.' } };
		SudoKu_37 sudu = new SudoKu_37();
		sudu.printBoard(board);
		sudu.solveSudoku(board);
		System.out.println("--------------------------");
		sudu.printBoard(board);
	}
	
//	
//	public static void main(String[] args) {
//		char num ='.';
//		int d = Character.getNumericValue(num);
//		System.out.println("d="+d);
//		//d=-1
//		
//		char board = (char)(d + '0');//board = '/'
//		System.out.println("board-----"+board);
//		
//		//char board = (char)(d + '1'); --> board = '0'
//		//char board = (char)(d + '2'); --> board = '1'
//		
//	}


	
	/*
	 * 方案一
	 */

	private char[][] board;

	int n = 3;
	int N = n * n;

	int[][] row = new int[N][N + 1];
	int[][] col = new int[N][N + 1];
	int[][] box = new int[N][N + 1];

	private boolean sudokuSolved = false;
	
	
	public boolean couldPlace(int d, int row, int col) {
		// check if one could place a number d in (row,col) cell
		// idx block index
		int idx = row / n * n + col / n;

		return this.row[row][d] + this.col[col][d] + box[idx][d] == 0;
	}

	public void placeNumber(int d, int row, int col) {
		// Place a number d in (row,col) cell
		int idx = row / n * n + col / n;

		this.row[row][d]++;
		this.col[col][d]++;
		this.box[idx][d]++;
		board[row][col] = (char) (d + '0');
	}

	public void removeNumber(int d, int row, int col) {
		// remove a number which didn't lead to a solution
		int idx = row / n * n + col / n;

		this.row[row][d]--;
		this.col[col][d]--;
		box[idx][d]--;
		board[row][col] = '.';
	}

	public void placeNextNumbers(int row, int col) {
		/*
		 * Call backtrack function in recursion to continue to place numbers till the
		 * moment we have a solution
		 */

		/*
		 * If we're in the last cell that means we have the solution
		 */
		if ((col == N - 1) && (row == N - 1)) {
			sudokuSolved = true;
		} else { // if not yet
			// if we're in the end of the row
			// go to the next row
			if (col == N - 1) {
				// go to the next column
				backtrack(row + 1, 0);
			} else {
				backtrack(row, col + 1);
			}
		}
	}

	public void backtrack(int row, int col) {
		// if the cell is empty
		if (board[row][col] == '.') {
			// iterate over all numbers from 1 to 9
			for (int d = 1; d < 10; d++) {
				if (couldPlace(d, row, col)) {
					placeNumber(d, row, col);
					placeNextNumbers(row, col);
					// if sudoku is solved,there is no need to backtrack
					// since the single unique is promised
					if (!sudokuSolved) {
						removeNumber(d, row, col);
					}
				}
			}
		} else {
			placeNextNumbers(row, col);
		}
	}
	
	public void solveSudoku(char[][] board) {
		this.board = board;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char num = board[i][j];
				if (num != '.') {
					int d = Character.getNumericValue(num);
					placeNumber(d, i, j);
				}
			}
		}
		backtrack(0, 0);
	}
	
	
	
	
	
//
//	public void backtrack2(int row,int col) {
//		//if the cell is empty
//		if(board[row][col] == '.') {
//			//iterator over all number from 1-9
//			for(int d=1;d<10;d++) {
//				int idx = row/n*n + col/n;
//				if(this.row[row][d] + this.col[col][d] + this.box[idx][d] == 0) {
//					this.row[row][d]++;
//					this.col[col][d]++;
//					this.box[idx][d]++;
//					//put 1-9 any number to the board(that's why d's range is from 1-9)
//					this.board[row][col] = (char)(d + '0');
//					//if we're in the end of the cell it means we have the solution
//					if( (row == (N-1)) && (col == (N-1))) {
//						sudokuSolved = true;
//					}else { //if not yet
//						// if we're in the end of the row, go to the next row
//						if(col == (N-1)) {
//							backtrack2(row+1, 0);
//						}else {
//							backtrack2(row, col+1);
//						}
//					}
//					// if sudoku is solved, there is no need to backtrack
//					//since the single unique is promised
//					if(!sudokuSolved) {
//						int idx2 = row /n*n + col/n;
//						this.row[row][d]--;
//						this.col[col][d]--;
//						this.box[idx2][d]--;
//						this.board[row][col] = '.';
//					}
//				}
//			}
//		}else {
//			 if((row == (N-1)) && (col == (N-1))) {
//				 sudokuSolved = true;
//			 }else {
//				 if(col == (N-1)) {
//					 backtrack2(row+1, 0);
//				 }else {
//					 backtrack2(row, col+1);
//				 }
//			 }
//		}
//	}
//
//	
//
//	
//	public void solveSudoku3(char[][] board) {
//		 this.board = board;
//		 
//		 for(int i =0;i<N;i++) {
//			 for(int j=0;j<N;j++) {
//				 char num = board[i][j];
//				 //当num='.' --> d=-1
//				 if(num != '.') {
//					 int d = Character.getNumericValue(num);
//					 int idx = i/n*n + j/n;
//					 this.row[i][d]++;
//					 this.col[j][d]++;
//					 this.box[idx][d]++;
//					 this.board[i][j] = (char)(d + '0');
//				 }
//			 }
//		 }
//		 
//		backtrack2(0, 0);
//		 
//	}
	
	
	
	/*
	 * 方案二：
	 */
	public void solveSudoku2(char[][] board) {
		// 记录某行，某位数字是否已经被摆放，boolean类型的默认值是false
		boolean[][] row = new boolean[9][9];
		// 记录某列，某位数字是否被摆放
		boolean[][] col = new boolean[9][9];
		// 记录某3*3的九宫格内，某位数字是否已经被摆放
		boolean[][] block = new boolean[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int num = board[i][j] - '1';
					row[i][num] = true;
					col[j][num] = true;
					block[i / 3 * 3 + j / 3][num] = true;
				}
			}
		}
		dfs(board, row, col, block, 0, 0);
	}

	private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean block[][], int i, int j) {
		// 寻找空位置
		while (board[i][j] != '.') {
			if (++j >= 9) {
				i++;
				j = 0;
			}
			if (i >= 9) {
				return true;
			}
		}

		for (int num = 0; num < 9; num++) {
			int blockIndex = i / 3 * 3 + j / 3;
			if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
				// 递归
				board[i][j] = (char) ('1' + num);
				row[i][num] = true;
				col[j][num] = true;
				block[blockIndex][num] = true;
				if (dfs(board, row, col, block, i, j)) {
					return true;
				} else {
					// 回溯
					row[i][num] = false;
					col[j][num] = false;
					block[blockIndex][num] = false;
					board[i][j] = '.';
				}
			}
		}
		return false;
	}
}
