package com.wj.leetcode.sort;

public class BubbleSort {
	
	
	public static void main(String[] args) {
		int[] arr = {2,1,7,9,5,8};
		int[] res= bubbleSort(arr);
		for(int a : res) {
			System.out.println(a + "  ");
		}
	}

	public static int[] bubbleSort(int[] arr) {
		
		for(int i = arr.length-1;i>0;i--) {
			for(int j= 0;j<i;j++) {
				if(arr[j+1]<arr[j]) {
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		return arr;
	}
	
	
	/*
	 * 判断上一次的比较中，有没有发生两两交换，如果一次交换都没有，就说明已经排好序了
	 * 
	 */
	
    public static int[] bubbleSort2(int[] arr) {
    	boolean ischanged = false;
		
		for(int i = arr.length-1;i>0 && ischanged;i--) {
			ischanged = false;
			for(int j= 0;j<i;j++) {
				if(arr[j+1]<arr[j]) {
					ischanged=true;
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		return arr;
		
		return arr;
	}
	
}
