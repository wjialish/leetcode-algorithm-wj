package com.wj.leetcode.DynamicProgramming;

import java.util.Arrays;

public class DPTemplate {

    public int LIS(int[] nums, int n){

        //1. 用一个以为数组cache来存储计算结果
        int[] cache = new int[n];

        //2.初始化cache中的每个值为1: 表示以每个元素作为结尾的最长递增子序列的长度初始化为1
        Arrays.fill(cache, 1);

        int max = 1;
        //3.自底向上地求解每个问题的最优解
        for(int i = 0;i<n;i++){
            //4. 拿遍历中遇到的每个元素nums[j] 和nums[i]比较
            for(int j=0;j<i;j++){
                //5. 如果发现nums[j]< nums[i]说明nums[i]有机会构成上升序列
                if(nums[j] < nums[i]){
                    //6. 如果新的上升序列比之前计算的要长 即cache[j]+1 > cache[i]
                    if(cache[j]+1 > cache[i]){
                      //7. 更新一下，保存到数组中
                      cache[i] = cache[j]+1;
                     }
                } 
            }

            //8. 当前计算好的长度于全局最大值比较
            max = Math.max(max, cache[i]);

        }

        //9. 最后得出最长的递增子序列
        return max;

    }
    
}