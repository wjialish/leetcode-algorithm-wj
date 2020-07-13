package com.wj.leetcode.BinarySearch;

public class SearchRange {


    public static void main(String[] args) {
        int[] nums = new int[]{2,2};

        int[] res = searchRange(nums, 2);
        for(int i :res){
            System.out.println(i);
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        // if(nums.length==0){
        //     return new int[]{-1,-1};
        // }

        // if(nums.length==1 && nums[0] == target){
        //     return new int[]{0,0};
        // }

        int[] res = new int[2];
        res[0] = searchLowBound(nums, target, 0, nums.length-1);
        res[1] = searchUpperBound(nums, target, 0, nums.length-1);

        return res;

    }

    public static int searchLowBound(int[] nums, int target,int low,int high){
        if(low>high){
            return -1;
        }

        //不要使用mid = (low+high)/2; 因为这样可能导致溢出
        int mid = low + (high - low)/2;

        if(nums[mid] == target && mid == 0 || nums[mid] == target && nums[mid-1]<target){
            return mid;
        }

        if(target<=nums[mid]){
            return searchLowBound(nums, target, low, mid-1);
        }else{
            return searchLowBound(nums, target, mid+1, high);
        }
    }


    public static int searchUpperBound(int[] nums, int target,int low,int high){
        if(low>high){
            return -1;
        }

        //不要使用mid = (low+high)/2; 因为这样可能导致溢出
        int mid = low + (high - low)/2;

        if(nums[mid] == target && mid == nums.length-1 || nums[mid] == target && nums[mid+1]>target){
            return mid;
        }

        if(target<nums[mid]){
            return searchUpperBound(nums, target, low, mid-1);
        }else{
            return searchUpperBound(nums, target, mid+1, high);
        }

    }
    
}