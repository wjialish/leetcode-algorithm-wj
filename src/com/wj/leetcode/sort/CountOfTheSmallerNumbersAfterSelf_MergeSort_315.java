package com.wj.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

public class CountOfTheSmallerNumbersAfterSelf_MergeSort_315 {
	
	/*
	 * 
	 * 315. Count of Smaller Numbers After Self
	 * 
	 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]     [1,2,5,6]   [3,1,0,2]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

	 */
	
	public List<Integer> countSmaller(int[] nums){
		List<Integer> resList = new ArrayList<Integer>();
		
		int len = nums.length;
		if(len == 0 || nums == null) {
			return resList;
		}
		
		int[] helper = new int[nums.length];
		int[] mapping = new int[nums.length];
		
		for(int i = 0;i<nums.length;i++) {
			mapping[i] = i;
			resList.add(0);
		}
		
		mergeSort(resList, nums, 0, nums.length - 1, helper, mapping);
		
		
		return resList;
	}
//	           [2,5,6,1]     
//排序后应为：   [1,2,5,6] 
//对应的原来的索引 [3,0,1,2]
// 即索引的映射关系从一开始的[0,1,2,3] 变成了[3,0,1,2] --加入mapping
	//通过引用mapping对他进行排序的话，就不会影响到arr里面的值
	
	
	private void mergeSort(List<Integer> resList,int[] nums,int low,int high,int helper[],int[] mapping) {
		if(low>=high) {
			return;
		}
		
		int mid = low + (high - low) /2;
		mergeSort(resList, nums, low, mid, helper, mapping);
		mergeSort(resList, nums, mid+1, high, helper, mapping);
		
		int i = low;
		int j = mid+1;
		int k = 0;
		while(i<= mid && j<= high) {
			/*
			 * 这里比较的时候就不是nums[i] <= nums[j] 来进行比较了，比较的时候就应该是通过mapping去取出它的位置 
			 */
			if(nums[mapping[i]] <= nums[mapping[j]]) {
				//helper 也不是对nums这个数组进行保存了，实际上是对mapping这个里面的index进行保存
				/*
				 * helper[k++] = mapping[i++];是一个从小到大的排序
				 * 从小到大的排序，我们每次发现j那个位置比i那个位置数要小的时候，我们还要多一次循环，把i位置后面所有的加1
				 * 这样我们考虑从大到小的排序，即把helper[k++] = mapping[i++]; 改为helper[k++] = mapping[j++];
				 * [    x     ] [      y     ]
				 * 从大到小的排序，当我们发现y比x小的时候，我们知道y后面所有的数都比x小，只需要在x的index上加上y+后面所有的
				 * 这样用o(1)的时间就可以完成
				 * 
				 */
				helper[k++] = mapping[j++];
			}else {
				int update = high - j + 1;
				resList.set(mapping[i], resList.get(mapping[i]) + update);
				
				helper[k++] = mapping[i++];
			}
		}
		
		while(i<= mid) {
			helper[k++] = mapping[j++];
		}

		while(j<=high) {
			helper[k++] = mapping[i++];
		}
		
		for(i = low,k=0; i<=high; i++,k++) {
			mapping[i] = helper[k];
		}
		
	}
	
	

}
