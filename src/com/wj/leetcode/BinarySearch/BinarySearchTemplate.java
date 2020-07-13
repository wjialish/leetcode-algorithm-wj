package com.wj.leetcode.BinarySearch;

public class BinarySearchTemplate {

    //递归写法和非递归写法都要掌握
    //递归写法
    public int binarySearchRecursion(int[] nums, int target,int low,int high){
        if(low>high){
            return -1;
        }

        //不要使用mid = (low+high)/2; 因为这样可能导致溢出
        int mid = low + (high - low)/2;

        if(nums[mid] == target){
            return mid;
        }

        if(target<nums[mid]){
            return binarySearchRecursion(nums, target, low, mid-1);
        }else{
            return binarySearchRecursion(nums, target, mid+1, high);
        }
    }
    

    //非递归写法
    public int binarySearch(int[] nums, int target,int low,int high){
        while(low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                return mid;
            }

            if(target<nums[mid]){
                high = mid-1;
            }else{
                low = mid +1;
            }
        }

        return -1;
    }
}