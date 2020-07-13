/*
 * @lc app=leetcode.cn id=52 lang=java
 *
 * [52] N皇后 II
 */

// @lc code=start
class Solution {
    public int totalNQueens(int n) {
      
        if(n<1){
            return 0;
        }

        int[] columns = new int[n];
        return backtracking(0,columns,n);

    }


    public int backtracking(int row, int[] columns,int n){
        if(row == n){
            return 1;
        }

        int res = 0;

        for(int col =0; col<n;col++){
             if(isValid(columns, row, col)){
                columns[row] = col;
                res += backtracking(row+1, columns, n);
             }
        }

        return res;
    }


    public boolean isValid(int[] columns, int row,int col){
        for(int k = 0; k < row;k++){
            //当前所放的皇后是否在那一列上，或者在对角线上
            if(Math.abs(row-k) == Math.abs(col-columns[k]) || col == columns[k]){
                return false;
            }
            
        }
        return true;
    }
}
// @lc code=end

