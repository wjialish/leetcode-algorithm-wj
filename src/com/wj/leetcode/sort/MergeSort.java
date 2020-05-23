package com.wj.leetcode.sort;

public class MergeSort {
	
	public static void main(String[] args) {
		int[] arr = new int[] {2, 7, 8, 3, 1, 6, 9, 0, 5, 4};
		int[] helper = new int[arr.length];
		int[] res = mergeSort(arr, 0, arr.length-1,helper);
		for(int t :res) {
			System.out.println(t);
		}
	}

	public static int[] mergeSort(int[] arr,int low,int high,int[] helper) {
		if(low>=high) {
			return null;
		}
		
		int mid = low + (high - low)/2;
		mergeSort(arr, low, mid, helper);
		mergeSort(arr, mid+1, high, helper);
		
		int i = low;
		int j = mid+1;
		int k = 0;
		while(i<=mid && j<=high) {
			if(arr[i]<= arr[j]) {
				helper[k++] = arr[i++];
			}else {
				helper[k++] = arr[j++];
			}
		}
		
		//以上条件没跑完的跑完
		while(i<=mid) {
			helper[k++] = arr[i++];
		}
		
		while(j<=high) {
			helper[k++] = arr[j++];
		}
		
		System.out.println("low-----"+low);
//		for(int t = 0 ;t<helper.length;t++) {
//			System.out.println("low2222-----"+helper[t]);
//			arr[t] =  helper[t];
//		}

		for(int m=low,n=0;m<=high;m++,n++) {
			arr[m] = helper[n];
		}
		return arr;
	}
	
	
	
	public static int[] mergeSort(int[] arr,int low,int high) {
		int mid = (low+high)/2;
		//int mid = low + (high - low)/2; //prevent integer out of bounds
		if(low<high) {
			//left
			mergeSort(arr, low, mid);
			//right
			mergeSort(arr, mid+1, high);
			//left and right merge
			merge(arr,low,mid,high);
		}
		return arr;
	}
	
	
	private static void merge(int[] arr,int low,int mid,int high) {
		int[] temp = new int[high-low+1];  //mergeSort has high space complexity， cause it need another array to mergesort
		int i = low; //left pointer
		int j = mid+1; //right pointer
		int k=0;
		
		//put smaller num to the temp arr
		while(i<=mid && j<=high) {
			if(arr[i] < arr[j]) {
				temp[k++] = arr[i++];
			}else {
				temp[k++] = arr[j++]; 
			}
		}
		
		//removing the remaining number on the left into the arr
		while(i <= mid) {
			temp[k++] = arr[i++];
		}
		
		//removing the remaining number on the right into the arr
		while(j <= high) {
			temp[k++] = arr[j++];
		}
		
		
		//overwirter the original arr with the new arr
//		for(int t = 0;t<temp.length;t++) {
//			
//			//notice here:arr[t+low]
//			arr[t+low] = temp[t];
//		}
		
		System.arraycopy(temp, 0, arr, low, high-low+1);
	}
	
}
