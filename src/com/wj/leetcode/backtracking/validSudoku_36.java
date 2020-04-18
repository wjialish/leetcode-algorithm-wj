package com.wj.leetcode.backtracking;

public class validSudoku_36 {

	/*
	 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.


	 */
	
	
	/*
	 * 判断数独是否有效?
	 * 思路：依次将已有的数计算后填如到row col and box 数组，
	 *      最后判断该数组中的数除了1和 ‘.’还有别的数，则该数独无效
	 *     
	 */
	
	char[][] board;
	
	int n = 3;
	int N = n*n;
	
	int[][] row = new int[N][N+1];
	int[][] col = new int[N][N+1];
	int[][] box = new int[N][N+1];
	
	public boolean isValidSudoku(char[][] board) {
        this.board = board; 
		
		for(int i=0;i<N;i++) {
			for(int j= 0;j<N; j++) {
				if(board[i][j] != '.') {
					int d = Character.getNumericValue(board[i][j]);
					int idx = i/n*n + j/n;
					this.row[i][d]++;
					this.col[j][d]++;
					this.box[idx][d]++;
					
//					System.out.println("this.row[i][d]---------"+this.row[i][d]);
//					System.out.println("this.col[j][d]---------"+this.col[j][d]);
//					System.out.println("this.box[idx][d]---------"+this.box[idx][d]);
//					

					if(this.row[i][d] > 1 || this.col[j][d] > 1 || this.box[idx][d] > 1) {
						return false;
					}
				}
			}
		}
		return true;
    }
	
	
	
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
		    { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
			{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, 
			{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
			{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, 
			{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
			{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, 
			{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
			{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, 
			{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		validSudoku_36 sudu = new validSudoku_36();
		sudu.printBoard(board);
		System.out.println(sudu.isValidSudoku(board));
		System.out.println("--------------------------");
		sudu.printBoard(board);
	}
	
	
//	[["8","3",".",".","7",".",".",".","."],
//	 ["6",".",".","1","9","5",".",".","."],
//	 [".","9","8",".",".",".",".","6","."],
//	 ["8",".",".",".","6",".",".",".","3"],
//	 ["4",".",".","8",".","3",".",".","1"],
//	 ["7",".",".",".","2",".",".",".","6"],
//	 [".","6",".",".",".",".","2","8","."],
//	 [".",".",".","4","1","9",".",".","5"],
//	 [".",".",".",".","8",".",".","7","9"]]
}
