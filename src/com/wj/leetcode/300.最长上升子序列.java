/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 */

// @lc code=start
class Solution {
     
    public int lengthOfLIS(int[] nums) {
        if(nums.length<=1){
            return nums.length;
        }
         //1. 生成长度为n的数组dp，dp[i]表示在以数组nums[i]结尾的情况下，arr[0...i]中的最大递增子序列的长度
        int[] dp = new int[nums.length];
        int max = 1;
        for(int i=0;i<nums.length;i++){
            //2. 初始化dp[i] = 1
            dp[i] = 1;
            /*3. 如果最长递增子序列以arr[i]结尾，那么所有在arr[0..i]中比arr[i]小的数都可以作为嘴擦好难过递增子序列的倒数第二个数      
            在那么多倒数第二个数的选择中，以哪个结尾的最长递增子序列更大，就选哪个数
             */
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    
}



// class Solution{

//     //static int  max;
//     public int lengthOfLIS(int[] nums) {
//         //max = 1;
//         int n = nums.length;
        
//         return f(nums, n);
//     }


//     public int f(int[] nums,int n){
//         //当数组长度为0 时，没有上升子序列
//         //当数组长度为1 时，最长上升子序列的长度为1
//         if(n<=1){
//             return n;
//         }

//         int result = 0;
//         //maxEndinghere 包含当前最后一个元素的情况下，最长的上升子序列的长度是
//         int maxEndinghere = 1;

//         //从头遍历数组
//         for(int i = 1; i<n;i++){
//             //递归求出以每个点为结尾的子数组中最长子序列的长度
//             result = f(nums, i);

//             //判断一下，如果该数比最后一个数要小，就能构成一个新的上升子序列，这个子序列又可能成为最终答案
//             if(nums[i-1] < nums[n-1] && result+1 > maxEndinghere){
//                 maxEndinghere = result+1;
//             }
//         }

//         // if(max < maxEndinghere){
//         //     max = maxEndinghere;
//         // }

//         //返回以当前数结尾的上升子序列的最长长度
//         return maxEndinghere;
//     }
// }


// @lc code=end

