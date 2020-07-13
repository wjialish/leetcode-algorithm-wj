import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 */

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new ArrayList<List<String>>();

        if(n<1){
            return res;
        }

        
        List<Integer> list = new ArrayList<Integer>();

        backtracking(0,n,list,res);

        return res;
    }


    public void backtracking(int row, int n,List<Integer> list, List<List<String>> res){
         if(row == n){

            List<String> resList = new ArrayList<String>();
            for(Integer num:list){
                char[] c= new char[n];
                Arrays.fill(c, '.');
                c[num] = 'Q';
                /*
                字符串数组整个转成一个字符串
                char[] c= new char[n];
                Arrays.fill(c, '.');
                c[num] = 'Q';
                String s = new String(c);
                */
                resList.add(new String(c));
            }
            
            res.add(new ArrayList<String>(resList));
            return;
         }

         //尝试将皇后放在当前行的每一列
         for(int col=0;col<n;col++){
            //检查当前列是否合法
            if(!list.contains(col)){
                //左斜和右斜是否合法
                if(isValid(list,col)){
                    list.add(col);
                    backtracking(row+1, n, list, res);
                    //backtracking
                    list.remove(list.get(list.size()-1));
               }
            }
             

         }

    }


    public boolean isValid(List<Integer> currentQueen, int i){
        int currentRow = currentQueen.size();
        int currentCol = i;

        //判断每一行的皇后情况
        for(int row = 0; row<currentQueen.size();row++){
            //左上角的对角线和右上角的对角线，差要么相等，要么互为相反数，直接写成绝对值
            if(Math.abs(currentRow - row) == Math.abs(currentCol - currentQueen.get(row))){
                return false;
            }
        }
        
        return true;
    }
}
// @lc code=end

