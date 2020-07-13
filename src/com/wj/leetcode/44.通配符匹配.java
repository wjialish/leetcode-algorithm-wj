/*
 * @lc app=leetcode.cn id=44 lang=java
 *
 * [44] 通配符匹配
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {

        /**
         * dp思想
         * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
           '?' 可以匹配任何单个字符。
           '*' 可以匹配任意字符串（包括空字符串）。
            两个字符串完全匹配才算匹配成功。
         */
        int m = s.length();
        int n = p.length();

        //定义一个二维数组dp
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;

        for(int j=1;j<=n;j++){
            
            if(p.charAt(j-1)!='*'){
                break;
            }else{
                dp[0][j]=true;
            }
        }

       //接下来对二维矩阵填表
       for(int i=1;i<=m;i++){
           for(int j=1;j<=n;j++){
               if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1)=='?'){
                   dp[i][j] = dp[i-1][j-1];
               }else if(p.charAt(j-1)=='*'){
                   /**
                    * 星号可以匹配零个或者任意多个小写字母
                    * 因此可以分为两种情况，即
                    * 1. 不使用星号dp[i][j-1]
                    * 2. 使用星号dp[i-1][j]
                    */
                   dp[i][j] = dp[i-1][j] || dp[i][j-1];
               }else{
                   dp[i][j]=false;
               }

           }
       }
       
       return dp[m][n];

    }

}
// @lc code=end

