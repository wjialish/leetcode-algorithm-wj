/*
 * @lc app=leetcode.cn id=198 lang=java
 *
 * [198] 打家劫舍
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        //1. 处理一些特殊情况,当数组为空 或者只有一个元素的情况
        if(n == 0) return 0;
        if(n == 1) return nums[0];


        //2. 初始化dp[0] 和 dp[1]
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        //3. 计算dp[i]
        for(int i = 2; i<n; i++){

            //对于nums[i]有两种结果，选还是不选，然后取最大值
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }

        //4. 返回dp[n-1]即所求结果
        return dp[n-1];
    }
}
// @lc code=end

