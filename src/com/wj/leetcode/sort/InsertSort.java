package com.wj.leetcode.sort;

public class InsertSort {
	
/*
 * 算法：
1.从第一个元素开始，该元素可以认为已经被排序；
2.取出下一个元素，在已经排序的元素序列中从后往前扫描；
3如果该元素（已排序）大于新元素，则将该元素移到下一位置
4.重复3，直到找到已排序的元素小于或者等于新元素的位置
5.将新元素插入到该位置中
6重复2
 */
	
	public static void main(String[] args) {
		int[] arr = {2,1,7,9,5,8,1,3,2,9,3,11,15};
		insertSort(arr);
		for(int a : arr) {
			System.out.println(a + "  ");
		}
	}
	
	public static int[] insertSort(int[] arr) {
		for(int i= 1; i<arr.length;i++) {
			for(int j= i;j>0;j--) {
				if(arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		return arr;
	}
}
