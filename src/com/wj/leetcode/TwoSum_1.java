package com.wj.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] res = new int[2];
        for(int i = 0; i<nums.length;i++){
            int another = target-nums[i];
            map.put(nums[i], i);
            if(map.containsKey(another)){
                if(nums[i] != another){
                    res[0] = Math.min(map.get(nums[i]),map.get(another));
                    res[1] = Math.max(map.get(nums[i]),map.get(another));
                }
            }
            
        }

        return res;
    }


    public static void main(String[] args) {
        int[] a = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(twoSum(a, target));
        
    }
    
}