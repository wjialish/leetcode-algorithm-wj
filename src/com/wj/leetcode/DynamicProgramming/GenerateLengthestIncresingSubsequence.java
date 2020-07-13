package com.wj.leetcode.DynamicProgramming;

public class GenerateLengthestIncresingSubsequence {



    //求最长递增子序列是什么（求得的是序列）
    /*
    方案一：时间复杂度为O（n）
    */
    //求最长递增子序列的长度
    public int[] getdp(int[] nums) {
         //1. 生成长度为n的数组dp，dp[i]表示在以数组nums[i]结尾的情况下，arr[0...i]中的最大递增子序列的长度
        int[] dp = new int[nums.length];
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
        }

        return dp;
    }




    
    /*
    接下来解释如何根据求得的dp数组得到最长递增子序列，假设nums=[2,1,5,3,6,4,8,9,7] 
         求出的数组dp=[1,1,2,2,3,3,4,5,4]
        1. 遍历dp数组，找出最大值以及位置，在本例中，最大值为5 其所在位置为7,说明最终最终最长递增子序列的长度为5,
           并且应该是以nums[7] = 9 这个数结尾
        2. 从nums 数组位置为7开始从右向左开始遍历,如果某一个位置i 既有nums[i] < nums[7] 又有dp[i] == dp[7] - 1 
           这里有nums[6] < nums[7] 还有dp[6] = dp[7]-1, 所以8 应该是最长递增子序列的倒数第二个数
        3. 从数组nums位置为6开始继续从右向左继续遍历，按照同样的方法找到倒数第3个数，在本题中, 位置5满足 nums[5] < nums[6]
           并且dp[5] == dp[6] - 1 , 同时位置4 也可以，所以选这两个数都可以
        4。 重复这样的过程，直到所有的数都找出来


    */

     public int[] generateLIS(int[] nums, int[] dp){

        int len = 0;
        int index = 0;
        for(int i = 0;i<dp.length;i++){
            if(dp[i] > len){
                len = dp[i];  //这里for循环结束之后 len = 5 index = 7
                index = i;
            }
        }

        //最终的最长递增子序列
        int[] list = new int[len];

        //list[5] = 9; index = 7
        list[--len] = nums[index];
        for(int i = index;i >= 0;i--){

            if(nums[index]> nums[i] && dp[index] == dp[i] + 1){
                list[--len] = nums[i];
                index = i;
            }
        }

        return list;


     }







    /*方案二： 时间复杂度为O(NlogN)
          时间复杂度为O(NlogN)生成dp数组的过程是利用二分查找来进行的优化。
          先生成一个长度为N的数组ends，起初时ends[0] = nums[0] 其他位置上的值为0  生成整形变量right，初识时right=0
          从左到右遍历nums数组的过程中，求解dp[i] 的过程需要用到ends数组和right变量
          遍历的过程中， ends[0...right]为有效区， ends[right+1 ...N-1]为无效区
          对于有效区的位置b，如果有end[b] == c 则表示遍历到目前为止，在所有长度为b+1的递增子序列中，最早的结尾数是c
    */

    public int[] generateLIS2(int[] nums){ 
        
        int n = nums.length;

        int[] dp2 = new int[n];
        int[] ends= new int[n];

        ends[0] = nums[0];

        dp2[0] = 1;

        int l = 0;
        int r = 0;
        int m =0;

        int right = 0;
        for(int i = 1; i<n; i++){
              l = 0;
              r = right;
              while(l<r){
                  m = (l+r)/2;

                  if(nums[i] > ends[m]){
                      l = m +1;
                  }else{
                      r = m - 1;
                  }
              }
              right = Math.max(right, l);
              ends[l] = nums[i];
              dp2[i] = l +1;
        }

        return dp2;
    }






    //求最长递增子序列的长度
    public int lengthOfLIS(int[] nums) {
        if(nums.length<=1){
            return nums.length;
        }
         //1. 生成长度为n的数组dp，dp[i]表示在以数组nums[i]结尾的情况下，arr[0...i]中的最大递增子序列的长度
        int[] dp = new int[nums.length];
        int max = 1;  //为什么这里设了一个max，非常重要！！！
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