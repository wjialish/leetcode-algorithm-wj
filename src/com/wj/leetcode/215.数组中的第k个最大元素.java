package com.wj.leetcode;
/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 */

// @lc code=start
class Solution {



    public static int findKthLargest(int[] nums, int k) {
        //quickSort2(nums, 0, nums.length-1, k);
        return nums[k-1];
    }
     
    public static void main(String[] args) {
		int[] arr = new int[]{3,3,3,7,9,122344,4656,34,34,4656,5,6,7,8,9,343,57765,23,12321};  
		quickSort3(arr, 0, arr.length-1,7);
		for(int i : arr) {
			System.out.print(i+"  ");
        }
        System.out.println();
        System.out.println(arr[6]);
		
	}

    public static int[] quickSort2(int[] arr,int low, int high) {
		
		int l = low;
		int h = high;
		int pivot = arr[low];
		
		
		while(l<h) {
			while(l<h && pivot <= arr[h]) {
				h--;
			}
			if(l<h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				l++;
			}
			
			
			
			while(l<h && pivot >= arr[l]) {
				l++;
			}
			if(l<h) {
				int temp = arr[l];
				arr[l] = arr[h];
				arr[h] = temp ;
				h--;
			}
		}
		
		
		
		if(l>low) {
			quickSort2(arr, low, l-1);
		}
		
		if(h<high) {
			quickSort2(arr, l+1, high);
		}
		
		
		return arr;
    }
    

     public static void quickSort3(int[] nums,int low,int high, int k) {
         int pivot = nums[low];
         //判断最后所在的基准值是不是k就好了
 //		if(pivot == k) {
 //			return nums[k];
 //		}
         
         int l = low;
         int h = high;
         
         while(l < h) {
             while(l<h && pivot<=nums[h]) {
                 h--;
             }
             if(l<h) {
                 int temp = nums[h];
                 nums[h] = nums[l];
                 nums[l] = temp;
                 l++;
             }
             
             while(l<h && pivot>=nums[l]) {
                 l++;
             }
             if(l<h) {
                 int temp = nums[l];
                 nums[l] = nums[h];
                 nums[h] = temp;
                 h--;
             }
             
         }
         
         
         if(l == k-1) {
             return;
         }else if(l > k-1){
             quickSort3(nums, low, l-1, k);
         }else {
             quickSort3(nums, l+1, high, k-(l-low+1));
         }
     }

     
}

// @lc code=end





