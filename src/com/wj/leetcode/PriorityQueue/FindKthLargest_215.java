package com.wj.leetcode.PriorityQueue;

import java.util.PriorityQueue;

public class FindKthLargest_215 {

	
	/*
	 * 215. 数组中的第K个最大元素
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
	 */
	
	
	public int findKthLargest(int[] nums, int k) {
         //求数组排序后的第k个最大的元素--小根堆
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for(int i = 0; i<nums.length;i++) {
			if(minHeap.size()!=k) {
				minHeap.offer(nums[i]);
			}else {
				if(nums[i]>minHeap.peek()) {
					minHeap.poll();
					minHeap.offer(nums[i]);
				}
			}
		}
		
		return minHeap.peek();
    }
}
