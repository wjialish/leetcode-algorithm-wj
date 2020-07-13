import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=673 lang=java
 *
 * [673] 最长递增子序列的个数
 */


 
// @lc code=start
class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,5,3,6,4,8,9,7};
        System.out.println(findNumberOfLIS(nums));
        
    }


    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if(n<=1){
            return n;
        }

        int[] dp = new int[n];
        int[] count = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int max = 0;
        for(int i =0; i< n;i++){
            //dp[i] = 1;
            //count[i] = 1;
            for(int j = 0;j<i;j++){
                if(nums[j] < nums[i]){
                    if(dp[i] < dp[j]+1){
                        dp[i] = dp[j]+1;
                        count[i] = count[j];
                    }else if(dp[i] == dp[j]+1){
                        count[i] += count[j];
                    }
                }
            }
            max = Math.max(dp[i], max);
        }
        

        int res = 0;
        for(int i = n-1;i>=0;i--){
            if(dp[i] == max)
            res += count[i];
        }


        return res;

    }
}
// @lc code=end

