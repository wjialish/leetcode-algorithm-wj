package com.wj.leetcode.sort;

import java.math.BigDecimal;

public class ReversePairs_493 {

	/*
	 * 493. Reverse Pairs
	 * 
	 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3

	 */

	
	public int reversePairs(int[] nums) {
		int n = nums.length;
		
		return mergeSort(nums, 0 , n-1);
	}
	

	private int mergeSort(int[] nums, int low, int high) {
        if(low >= high) return 0;
		int res = 0;
		int mid = low + (high - low)/2;
		res += mergeSort(nums, low, mid);
		res += mergeSort(nums, mid+1, high);
		res += merge(nums,low, mid, high);
		return res;
	}


	private int merge(int[] nums, int low, int mid, int high) {
        int count = 0;
        int temp[] = new int[high-low+1];
        //分成左右两个数组，左边数组的范围是[low,mid] 右边数组的范围是[mid+1,high]
        int p = low;
        int q = mid+1;
        int index = 0; //temp数组的index
        
        //归并排序前进行统计
        while(p<=mid && q <= high) {
        	//这里为了防止integer overflow 记得将他们强制转换成long
        	if((long)nums[p] > 2 * (long)nums[q]) {
        		/*
        		 * [1,5,6] 前半数组
        		 * [1,2]  后半数组
        		 *  假设这里 5 > 2 * 2,那么5后面的所有的数都符合这个序数对，即count = mid(左半数组的最后一位) - p(当前5所在的位置为p) + 1
        		 * 
        		 */
        		count += mid - p + 1;
        		q++;//后半数组继续向后移动
        	}else {
        		p++;
        	}
        }
        
        //正常的归并排序
        p = low;
        q = mid+1;
        while(p <= mid && q <= high) {
        	if(nums[p] < nums[q]) {
        		temp[index++] = nums[p++];
        	}else {
        		temp[index++] = nums[q++];
        	}
        }
       
        
        while(p <= mid) {
        	temp[index++] = nums[p++];
        }
        
        while(q <= high) {
        	temp[index++] = nums[q++];
        }
        
        System.arraycopy(temp, 0, nums, low, high - low + 1);
        
		return count;
	}


	public static void main(String[] args) {
		int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
		long a = 2147483647;
		long tt = Long.MAX_VALUE;
		long b = 2*a;
		System.out.println(b);
		System.out.println(tt);
		System.out.println(reversePairs2(nums));
	}
	
	
	public static int reversePairs2(int[] nums) {

		int res = 0;
		for(int i = 0;i<nums.length;i++) {
			for(int j= i+1;j<nums.length;j++) {
				long tmp = nums[j];
				long tmp2 = 2 * tmp;
				System.out.println(tmp2);
				if(nums[i]> tmp2) {
					System.out.println(nums[i]);
					System.out.println(tmp2);
					res++;
				}
			}
		}
		
		return res;
    }
	
}
