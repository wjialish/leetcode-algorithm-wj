package com.wj.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestSumAfterKNegeations {
/*
 * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)

Return the largest possible sum of the array after modifying it in this way.

 

Example 1:

Input: A = [4,2,3], K = 1     
Output: 5
Explanation: Choose indices (1,) and A becomes [4,-2,3].

Example 2:

Input: A = [3,-1,0,2], K = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].

Example 3:

Input: A = [2,-3,-1,5,-4], K = 2
Output: 13
Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].

 

Note:

    1 <= A.length <= 10000
    1 <= K <= 10000
    -100 <= A[i] <= 100

[-2,9,9,8,4]
5
[8,-7,-3,-9,1,9,-6,-9,3]
8
Output
49
Expected
53
 */
	
	public static void main(String[] args) {
		int[] A= {8,-7,-3,-9,1,9,-6,-9,3};  //9 8 3 1 -3 -6 -7 -9 -9
		int K=8;
		System.out.println(largestSumAfterKNegations3(A, K));
	}
	
	 public static int largestSumAfterKNegations(int[] A, int K) {
	 
		 int res=0;
		 
		 //Arrays.sort(A);
		 
		 List<Integer> list =new ArrayList<Integer>();
		 
		 for(int i=0;i<A.length;i++) {
			 list.add(A[i]);
		 }
		 
		 Collections.sort(list);
		 
		 
		 
		 for(int i = 0; i < K; i++) {	
			/* if(list.get(i)==0 && ((i == (K-1)) || ( (K-i)%2 == 0 ))) {
				 break;		
			 }
			 
			 if(list.get(i)==0) {
				 i=K-1;				
			 }*/
			 if(list.get(i)==0) {
				 break;		
			 }
			 
				
			 if(list.get(i)>0 && (i != 0)) {
				 if((K-i)%2 == 0) {
					 break;
				 }else {
					 if(list.get(i)>= Math.abs(0-list.get(i-1))) {
						 list.set(i-1, 0-list.get(i-1));
					 }else {
						 list.set(i, 0-list.get(i));
					 }
					 
					 i=K-1;
				 }
			 }else {
				 list.set(i,0-(list.get(i)));
			 }
			 
			 
						
		 }
		
		 for(int i=0;i<list.size();i++) {
			 res+=list.get(i);
		 }
		 
		 return res;
		 
	 }
	 
	 public static int largestSumAfterKNegations2(int[] A, int K) {
		 
		 int res=0;
		 
		 //Arrays.sort(A);
		 
		 List<Integer> list =new ArrayList<Integer>();
		 
		 for(int i=0;i<A.length;i++) {
			 list.add(A[i]);
			 res+=A[i];
		 }
		 
		 Collections.sort(list);
		 
		 
		 
		 for(int i = 0; i < K; i++) {	
			/* if(list.get(i)==0 && ((i == (K-1)) || ( (K-i)%2 == 0 ))) {
				 break;		
			 }
			 
			 if(list.get(i)==0) {
				 i=K-1;				
			 }*/
			 if(list.get(i)==0) {
				 break;		
			 }
			 
				
			 if(list.get(i)>0 && (i != 0)) {
				 if((K-i)%2 == 0) {
					 break;
				 }else {
					 if(list.get(i)>= Math.abs(0-list.get(i-1))) {
						 res-=list.get(i-1);
						 list.set(i-1, 0-list.get(i-1));
						 res+=list.get(i-1);
					 }else {
						 res-=list.get(i);
						 list.set(i, 0-list.get(i));
						 res+=list.get(i);
					 }
					 
					 i=K-1;
				 }
			 }else {
				 res-=list.get(i);
				 list.set(i,0-(list.get(i)));
				 res+=list.get(i);
			 }
			 
			 
						
		 }
	
		 
		 return res;
		 
	 }
	 
	 
    public static int largestSumAfterKNegations3(int[] A, int K) {
		 
		 int res=0;
		 
		 Arrays.sort(A);
		 
		 		 
		 for(int i = 0; i < K; i++) {	
			/* if(list.get(i)==0 && ((i == (K-1)) || ( (K-i)%2 == 0 ))) {
				 break;		
			 }
			 
			 if(list.get(i)==0) {
				 i=K-1;				
			 }*/
			 if(A[i]==0) {
				 break;		
			 }
			 
				
			 if(A[i]>0 && (i != 0)) {
				 if((K-i)%2 == 0) {
					 break;
				 }else {
					 if(A[i]>= Math.abs(A[i-1])) {
						 A[i-1] = -A[i-1];
					 }else {
						 A[i] = -A[i];
					 }
					 
					 i=K-1;
				 }
			 }else {
				 A[i] = -A[i];
			 }
			 						
		 }
	
		 for(int i=0;i<A.length;i++) {
			 res+=A[i];
		 }
		 
		 return res;
		 
	 }
}
