package com.wj.leetcode.DynamicProgramming;

import java.util.Arrays;

public class FindNumberOfLIS {
	
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


    //以下方法不对
    // public static int findNumberOfLIS(int[] nums) {
    //     int n = nums.length;
    //     //1. 生成长度为n的数组dp，dp[i]表示在以数组nums[i]结尾的情况下，arr[0...i]中的最大递增子序列的长度
    //     int[] dp = new int[n];

    //     //2. 初始化dp[i] = 1
    //     for(int i = 0;i<n;i++){
    //         dp[i] = 1;
    //      /*3. 如果最长递增子序列以arr[i]结尾，那么所有在arr[0..i]中比arr[i]小的数都可以作为嘴擦好难过递增子序列的倒数第二个数      
    //         在那么多倒数第二个数的选择中，以哪个结尾的最长递增子序列更大，就选哪个数
    //     */ 
    //          for(int j = 0;j<i; j++){
    //              if(nums[j] < nums[i]){
    //                  dp[i] = Math.max(dp[i], dp[j] + 1);
    //              }
    //          }
    //     }
        
    //     Arrays.sort(dp);
    //     int max = dp[dp.length-1];


    //     int  res = 0;
    //     for(int i : dp){
    //         if(i == max){
    //             res++;
    //         }
    //     }


    //     return res;
    // }

}
