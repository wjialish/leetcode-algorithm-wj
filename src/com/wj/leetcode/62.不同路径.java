import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=62 lang=java
 *
 * [62] 不同路径
 */

// @lc code=start
class Solution {
    public int uniquePaths(int m, int n) {

        /*
        要抵达右下角，必然会经过它上面的格子和左面的格子
        如果我们分别知道要抵达这两个格子的总步数
        就会得到我们想要的结果
        得到地推公式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        对于第一行 dp[0][j] 和第一列dp[j][0] 都是边界 都是1
        */
        /*
        时间复杂度：O(m*n)

        空间复杂度：O(m * n)
        62/62 cases passed (0 ms)
Your runtime beats 100 % of java submissions
Your memory usage beats 6.35 % of java submissions (36.3 MB)
        */
        // int[][] dp = new int[m][n];

        // for(int i = 0; i< m;i++){
        //     dp[i][0] = 1;
        // }

        // for(int i = 0; i< n;i++){
        //     dp[0][i] = 1;
        // }

        // for(int i = 1;i<m;i++){
        //     for(int j=1;j<n;j++){
        //         dp[i][j] = dp[i-1][j] + dp[i][j-1];
        //     }
        // }

        // return dp[m-1][n-1];
        



        //优化 空间复杂度为O(n)
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 1; i<m;i++){
            for(int j = 1;j< n;j++){
                dp[j] += dp[j-1];
            }
        }

        return dp[n-1];


       


       

    }
}
// @lc code=end

