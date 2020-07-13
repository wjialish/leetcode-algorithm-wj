/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 */

// @lc code=start
class Solution {
    public int coinChange(int[] coins, int amount) {

        if(coins == null || coins.length == 0){
            return -1;
        }

        String s ="";
        if(amount <= 0){
            return 0;
        }

        int[] dp = new int[amount+1];
        for(int i =1;i<dp.length;i++){
            dp[i] = Integer.MAX_VALUE;
        }

        for(int res = 1;res<dp.length;res++){
            for(int i =0;i<coins.length;i++){
                if(coins[i] <= res){
                    int sub = dp[res - coins[i]];
                    if(sub != Integer.MAX_VALUE){
                        dp[res] = Math.min(sub+1, dp[res]);
                    }
                }
            }
        }

        return dp[dp.length-1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }
}
// @lc code=end

