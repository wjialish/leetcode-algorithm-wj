package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Permutations_46 {

	/*
	 * Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


	 */
	
	public static void main(String[] args) {
		int[] nums = {1,2,3};
		List<List<Integer>> resList = new ArrayList<List<Integer>>();
		Permutations_46 permu = new Permutations_46();
		resList = permu.permute(nums);
		for(List<Integer> list : resList) {
			System.out.println(list);
		}
		
	}
	
	
	List<List<Integer>> resList = new ArrayList<List<Integer>>();
	
	
	
	
	
	public List<List<Integer>> permute(int[] nums) {

		List<Integer> num_list = new ArrayList<>();
		for(int num:nums) {
			num_list.add(num);
		}
		
		int n = num_list.size();
		
		backtracking(n,num_list,0);
		
		return resList;
    }
	
	
	public void backtracking(int n, List<Integer> num_list,int first) {
		//if all integer are used up, put the num_list into resList,end recursion
		if(first == n) {
			//resList.add(num_list);//XXXX有问题
			resList.add(new ArrayList<>(num_list));
		}
		
		for(int i = first;i<n;i++) {
			//place i-th integer first
			//in the current permutations
			Collections.swap(num_list, first, i);
			//use the next integer to complete the permutation
			backtracking(n, num_list, first+1);
			//backtrack
			Collections.swap(num_list, first, i);
		}
	}
	
	
	
}
