import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=873 lang=java
 *
 * [873] 最长的斐波那契子序列的长度
 */

// @lc code=start
class Solution {
    public static int lenLongestFibSubseq(int[] A){
        int n = A.length;

        int[][] dp = new int[n][n];

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        for(int i = 0;i<n;i++){
            map.put(A[i], i);
            for(int j =0;j<n;j++){
                dp[i][j] = 2;
            }
           
        }

        int max = 0;
        for(int i = 1;i<n-1;i++){
            for(int j = i+1;j<n;j++){
                int resKey = A[j] - A[i];
                if(map.containsKey(resKey)){
                    int keyValue = map.get(resKey);
                    if(keyValue < i){
                        dp[i][j] = dp[keyValue][i] + 1; 
                    }
                }

                max = Math.max(max, dp[i][j]);
            }
  
        }

        return max>2?max:0;
    }
}
// @lc code=end

