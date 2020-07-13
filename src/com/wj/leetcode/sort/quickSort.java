package com.wj.leetcode.sort;

public class quickSort {
	
	
	public static void main(String[] args) {
		int[] arr = new int[]{3,3,3,7,9,122344,4656,34,34,4656,5,6,7,8,9,343,57765,23,12321};  
		int[] res = quickSort2(arr, 0, arr.length-1);
		for(int i : res) {
			System.out.print(i+"  ");
		}
		
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

}
