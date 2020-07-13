package com.wj.leetcode.sort;

public class FindKthLargest215_quickSort {

	/*
	 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

	 */
	
	public static void main(String[] args) {
		int[] arr = {3,2,3,1,2,4,5,5,6};
		int res = findKthLargest(arr, 4);
		System.out.println(res);
	}
	
	public static int findKthLargest(int[] nums, int k) {
       quickSort(nums, 0, nums.length-1, k);
       return nums[k-1];
   }
	
	public static void quickSort(int[] nums,int low,int high, int k) {
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
			quickSort(nums, low, l-1, k);
		}else {
			quickSort(nums, l+1, high, k-(l-low+1));
		}
	}
	
}
