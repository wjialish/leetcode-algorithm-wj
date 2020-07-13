package com.wj.leetcode.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LengthOfLongthestFabonacciSubsequence_873 {
    
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
    //以下方法不对，但是是自己一开始的思考过程
    // public static int lenLongestFibSubseq(int[] A) {

    //     int n = A.length;
    //     int[] dp = new int[n];

    //     Map<Integer,Integer> map = new HashMap<Integer,Integer>();

    //     for(int i = 0;i<n;i++){
    //         map.put(A[i],i);
    //     }
        
    //     int max = 1;
    //     Arrays.fill(dp,2);

    //     for(int i= 2;i<n;i++){ 
    //         for(int j=0;j<i;j++){
    //             int res = A[i] - A[j];
    //             if(map.containsKey(res)){
    //                 if(map.get(res) < j){
    //                     if(dp[i] < dp[j]+1){
    //                         dp[i] = dp[j]+1;
    //                     }
                       
    //                 }
                   
    //             }
    //             max = Math.max(max,dp[i]);
    //         }
            
    //     }
        
    //     return max>=3?max:0;
        
    // }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        int res = LengthOfLongthestFabonacciSubsequence_873.lenLongestFibSubseq(arr);
        System.out.println(res);
    }
}