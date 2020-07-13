/*
 * @lc app=leetcode.cn id=516 lang=java
 *
 * [516] 最长回文子序列
 */

// @lc code=start
class Solution {
    public int longestPalindromeSubseq(String s) {

        /*
        最长回文可以出现在区间的任何位置，如果我们知道其中任何一段区间的最长回文用dp[i][j]表示(从字符i到字符j区间的最长回文)
        这个时候，我们可以尝试比较这个区间外的两个字符，如果他们相等，加上他们肯定可以构成新的回文
        递归公式：
        dp[0][n-1] 表示从位置0到位置n-1区间的最长回文数
        当首位两个数相等时 dp[0][n-1] = dp[1][n-2] + 2;
        当首位两个数不相等时 dp[0][n-1] = Math.max(dp[0,n-2],dp[1,n-1])
        */

        //1. 定义dp矩阵 dp[i][j]表示(从字符i到字符j区间的最长回文)

        int n = s.length();
        int[][] dp = new int[n][n];

        //2. 初始化dp矩阵，将对角线元素设为1，表示单个字符最长回文数长度是1
        for(int i = 0;i<n;i++){
            dp[i][i] = 1;
        }

        //3. 长度为2时，尝试将区间扩大，一直扩大到n
        for(int len = 2; len <= n; len++){

        //4. 在扩大的过程中，每次都得出区间的起始位置i 和结束位置j
            for(int i = 0;i<n-len+1; i++){
                int j = i + len -1;
                //5. 比较以下区间首尾的字符是否相等
                if(s.charAt(i) == s.charAt(j)){
                    //6. 如果相等 就+2
                    dp[i][j] = (len == 2 ? 0 : dp[i+1][j-1]) + 2;
                }else{
                    //7。如果不相等的话，就从规模更小的字符串中得出最长的回文长度
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                }
             
            }
        }

        return dp[0][n-1];

    }
}
// @lc code=end

